package com.reactive.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewInfo {

	private long reviewId;
	private long bookId;
	private double ratings;
	private String comments;
	
	
}
