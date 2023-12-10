package com.reactive.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reactive.domain.BookInfo;
import com.reactive.domain.ReviewInfo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class BookInfoService {

	public Flux<BookInfo> getBooksInfo() {
		
		var books = List.of( 
				new BookInfo(1L,"One","A1","123"),
				new BookInfo(2L,"Two","A2","456"),
				new BookInfo(3L,"Three","A3","789")
				
				);
		
		return Flux.fromIterable(books);
	}
	
	public Mono<BookInfo> getBookInfoById(Long bookId) {
		var book =  new BookInfo(bookId, "One","A1","123");
		return Mono.just(book).log();
	}
	
	
}
