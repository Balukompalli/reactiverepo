package com.reactive.service;

import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;

import com.reactive.domain.Book;
import com.reactive.domain.BookInfo;
import com.reactive.domain.ReviewInfo;
import com.reactive.exception.BookException;

import lombok.extern.slf4j.Slf4j;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.Retry.RetrySignal;
import reactor.util.retry.RetryBackoffSpec;

@Slf4j
public class BookService {

	private BookInfoService bookInfoService;
	private ReviewInfoService reviewInfoService;
	
		
	public BookService(BookInfoService bookInfoService, ReviewInfoService reviewInfoService) {
		super();
		this.bookInfoService = bookInfoService;
		this.reviewInfoService = reviewInfoService;
	}

	public Flux<Book> getBooks() {
		var allBooks = bookInfoService.getBooksInfo();
		return allBooks.flatMap(bookInfo -> {
		Mono<List<ReviewInfo>> reviewListMono=	reviewInfoService.getReviewsInfo(bookInfo.getBookId()).collectList();
			return reviewListMono.map(review -> new Book(bookInfo, review));
		})
				.onErrorMap(throwable -> {
					//Logger.error("Exception is : "+throwable);
					return new BookException("Exception while fetching books.."+throwable);
				})
				.log()
				;
	}

	public Flux<Book> getBooksRetryWhen() {
		var allBooks = bookInfoService.getBooksInfo();
		var retrySpecs = getRetrybackoffSpec();
		return allBooks.flatMap(bookInfo -> {
		Mono<List<ReviewInfo>> reviewListMono=	reviewInfoService.getReviewsInfo(bookInfo.getBookId()).collectList();
			return reviewListMono.map(review -> new Book(bookInfo, review));
		})
				.onErrorMap(throwable -> {
					//Logger.error("Exception is : "+throwable);
					return new BookException("Exception while fetching books.."+throwable);
				})
				.retryWhen(retrySpecs)
				.log()
				;
	}

	private RetryBackoffSpec getRetrybackoffSpec() {
		return Retry.backoff(3, Duration.ofMillis(1000))
		.filter(ex-> ex instanceof BookException)
				.onRetryExhaustedThrow((retrybackOffSpec, retrySignal) -> Exceptions.propagate(retrySignal.failure()));
	}
	
	public Flux<Book> getBooksRetry() {
		var allBooks = bookInfoService.getBooksInfo();
	
		return allBooks.flatMap(bookInfo -> {
		Mono<List<ReviewInfo>> reviewListMono=	reviewInfoService.getReviewsInfo(bookInfo.getBookId()).collectList();
			return reviewListMono.map(review -> new Book(bookInfo, review));
		})
				.onErrorMap(throwable -> {
					//Logger.error("Exception is : "+throwable);
					return new BookException("Exception while fetching books.."+throwable);
				})
				.retry(3)
	
				.log()
				;
	}
	
	
	public Mono<Book> getBookById(Long bookId) {
		var books = bookInfoService.getBookInfoById(bookId);
		var reviews = reviewInfoService.getReviewsInfo(bookId)
				.collectList();
		 return books.zipWith(reviews, (book,review) -> new Book(book,review));
	}
}
