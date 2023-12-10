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
	@Override
	public String toString() {
		return "ReviewInfo [reviewId=" + reviewId + ", bookId=" + bookId + ", ratings=" + ratings + ", comments="
				+ comments + "]";
	}
	public ReviewInfo(long reviewId, long bookId, double ratings, String comments) {
		super();
		this.reviewId = reviewId;
		this.bookId = bookId;
		this.ratings = ratings;
		this.comments = comments;
	}
	public long getReviewId() {
		return reviewId;
	}
	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public double getRatings() {
		return ratings;
	}
	public void setRatings(double ratings) {
		this.ratings = ratings;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
