package com.reactive.domain;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Service
public class BookInfo {

	
	private Long bookId;
	private String title;
	private String author;
	private String isbn;
	@Override
	public String toString() {
		return "BookInfo [bookId=" + bookId + ", title=" + title + ", author=" + author + ", isbn=" + isbn + "]";
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public BookInfo(Long bookId, String title, String author, String isbn) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
	}
	

}
