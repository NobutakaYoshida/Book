package com.example.domain;

import java.util.Date;

public class Book {

	private Integer id;
	private String title;
	private String author;
	private String publisher;
	private Integer pages;
	private Integer price;
	private Date finishedDate;
	private Integer snsPush;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getFinishedDate() {
		return finishedDate;
	}

	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
	}

	public Integer getSnsPush() {
		return snsPush;
	}

	public void setSnsPush(Integer snsPush) {
		this.snsPush = snsPush;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publisher=" + publisher + ", pages="
				+ pages + ", price=" + price + ", finishedDate=" + finishedDate + ", snsPush=" + snsPush + "]";
	}

}