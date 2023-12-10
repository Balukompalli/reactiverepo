package com.reactive.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.reactive.domain.BookInfo;

import reactor.test.StepVerifier;

class BookInfoServiceTest {

	private BookInfoService bookInfoService = new BookInfoService();
	private ReviewInfoService reviewInfoService = new ReviewInfoService();
	private BookService bookService = new BookService(bookInfoService, reviewInfoService);
	
	@Test
	void testGetBooks() {
		var books = bookService.getBooks();
		StepVerifier.create(books)
		.assertNext(book -> {
			assertEquals("One", book.getBookInfo().getTitle());
			assertEquals(2, book.getListReviewInfo().size());
		})
		.assertNext(book -> {
			assertEquals("Two", book.getBookInfo().getTitle());
			assertEquals(2, book.getListReviewInfo().size());
		})
		.assertNext(book -> {
			assertEquals("Three", book.getBookInfo().getTitle());
			assertEquals(2, book.getListReviewInfo().size());
		})
		.verifyComplete();
	}

	@Test
	void testGetBookInfoById() {
		var books = bookService.getBookById(1L);
		StepVerifier.create(books)
		.assertNext(book -> {
			assertEquals("One", book.getBookInfo().getTitle());
			assertEquals(2, book.getListReviewInfo().size());
		}).verifyComplete();
		
	}

}
