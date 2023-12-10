package com.reactive.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	private BookInfo bookInfo;
	private List<ReviewInfo> listReviewInfo;
	public BookInfo getBookInfo() {
		return bookInfo;
	}
	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}
	public List<ReviewInfo> getListReviewInfo() {
		return listReviewInfo;
	}
	public void setListReviewInfo(List<ReviewInfo> listReviewInfo) {
		this.listReviewInfo = listReviewInfo;
	}
	public Book(BookInfo bookInfo, List<ReviewInfo> listReviewInfo) {
		super();
		this.bookInfo = bookInfo;
		this.listReviewInfo = listReviewInfo;
	}
	@Override
	public String toString() {
		return "Book [bookInfo=" + bookInfo + ", listReviewInfo=" + listReviewInfo + "]";
	}
	
	
}
