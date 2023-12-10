package com.reactive.service;

import java.util.List;

import com.reactive.domain.Book;
import com.reactive.domain.BookInfo;
import com.reactive.domain.ReviewInfo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
		}).log()
				;
	}

	public Mono<Book> getBookById(Long bookId) {
		var books = bookInfoService.getBookInfoById(bookId);
		var reviews = reviewInfoService.getReviewsInfo(bookId)
				.collectList();
		 return books.zipWith(reviews, (book,review) -> new Book(book,review));
		
	}
}
