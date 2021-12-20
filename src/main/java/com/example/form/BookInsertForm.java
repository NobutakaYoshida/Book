package com.example.form;

import java.sql.Date;

public class BookInsertForm {

	private String title;
	private String author;
	private Date finishedDate;
	private String publisher;
	private Integer pages;
	private Integer price;
	private Integer snsPush;
	private Integer bookStyle;
	private Integer bigGenre;
	private Integer smallGenre;
	private String image;
	private String comment;
	
	
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
	public Date getFinishedDate() {
		return finishedDate;
	}
	public void setFinishedDate(Date finishedDate) {
		this.finishedDate = finishedDate;
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
	public Integer getSnsPush() {
		return snsPush;
	}
	public void setSnsPush(Integer snsPush) {
		this.snsPush = snsPush;
	}
	public Integer getBookStyle() {
		return bookStyle;
	}
	public void setBookStyle(Integer bookStyle) {
		this.bookStyle = bookStyle;
	}
	public Integer getBigGenre() {
		return bigGenre;
	}
	public void setBigGenre(Integer bigGenre) {
		this.bigGenre = bigGenre;
	}
	public Integer getSmallGenre() {
		return smallGenre;
	}
	public void setSmallGenre(Integer smallGenre) {
		this.smallGenre = smallGenre;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	@Override
	public String toString() {
		return "BookInsertForm [title=" + title + ", author=" + author + ", finishedDate=" + finishedDate
				+ ", publisher=" + publisher + ", pages=" + pages + ", price=" + price + ", snsPush=" + snsPush
				+ ", bookStyle=" + bookStyle + ", bigGenre=" + bigGenre + ", smallGenre=" + smallGenre + ", image="
				+ image + ", comment=" + comment + "]";
	}
	
}