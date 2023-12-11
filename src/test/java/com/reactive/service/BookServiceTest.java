package com.reactive.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.reactive.exception.BookException;

import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

	@InjectMocks
	private BookService bookService;
	
	@Mock
	private BookInfoService bookInfoService;
	
	@Mock
	private ReviewInfoService reviewInfoService;
	
	@Test
	void testGetBooks() {
		when(bookInfoService.getBooksInfo()).thenCallRealMethod();
		when(reviewInfoService.getReviewsInfo(anyLong())).thenCallRealMethod();
		
		var books = bookService.getBooks();
		StepVerifier.create(books)
		.expectNextCount(3)
		.verifyComplete();
	}

	@Test
	void testGetBookById() {
	
	}

	@Test
	void testGetBooksOnError() {
		when(bookInfoService.getBooksInfo()).thenCallRealMethod();
		when(reviewInfoService.getReviewsInfo(anyLong())).thenThrow(new IllegalStateException("exception using test"));
		
		var books = bookService.getBooks();
		StepVerifier.create(books)
		.expectError(BookException.class)
		.verify();
	}
	
	@Test
	void testGetBooksOnErrorRetry() {
		when(bookInfoService.getBooksInfo()).thenCallRealMethod();
		when(reviewInfoService.getReviewsInfo(anyLong())).thenThrow(new IllegalStateException("exception using test"));
		
		var books = bookService.getBooksRetry();
		StepVerifier.create(books)
		.expectError(BookException.class)
		.verify();
	}
	
	@Test
	void testGetBooksOnErrorRetryWhen() {
		when(bookInfoService.getBooksInfo()).thenCallRealMethod();
		when(reviewInfoService.getReviewsInfo(anyLong())).thenThrow(new IllegalStateException("exception using test"));
		
		var books = bookService.getBooksRetry();
		StepVerifier.create(books)
		.expectError(BookException.class)
		.verify();
	}
	
}
