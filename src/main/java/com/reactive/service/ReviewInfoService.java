package com.reactive.service;

import java.util.List;

import com.reactive.domain.BookInfo;
import com.reactive.domain.ReviewInfo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReviewInfoService {

	public Flux<ReviewInfo> getReviewsInfo(Long bookId) {
		
		var reviewList = List.of( 
				new ReviewInfo(1,bookId,9.1,"Good Book"),
				new ReviewInfo(2,bookId,8.6,"Worth read")
				);
		return Flux.fromIterable(reviewList);
	}
	
}
