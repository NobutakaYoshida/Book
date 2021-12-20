package com.example.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.domain.Book;
import com.example.form.BookInsertForm;

@Repository
public class BookRepository {

	@ModelAttribute
	public BookInsertForm setUpForm() {
		return new BookInsertForm();
	}
	@Autowired
	private NamedParameterJdbcTemplate template;

	public static final RowMapper<Book> BOOK_ROW_MAPPER = (rs, i) -> {
		Book book = new Book();
		book.setId(rs.getInt("id"));
		book.setTitle(rs.getString("title"));
		book.setAuthor(rs.getString("author"));
		book.setFinishedDate(rs.getDate("finished_date"));
		book.setPublisher(rs.getString("publisher"));
		book.setPages(rs.getInt("pages"));
		book.setPrice(rs.getInt("price"));
		book.setSnsPush(rs.getInt("sns_push"));
		book.setBookStyle(rs.getInt("book_style"));
		book.setBigGenre(rs.getInt("big_genre"));
		book.setSmallGenre(rs.getInt("small_genre"));
		book.setImage(rs.getString("image"));
		book.setComment(rs.getString("comment"));
		return book;
	};
	
	public List<Book> findAll() {
		String sql = "SELECT * FROM books";
		List<Book> bookList = template.query(sql, BOOK_ROW_MAPPER);
		return bookList;
	}

	public List<Book> search(String title) {
		String sql = "SELECT * FROM books WHERE title = :title";
		SqlParameterSource param = new MapSqlParameterSource().addValue("title", title);
		List<Book> bookList = template.query(sql, param, BOOK_ROW_MAPPER);
		return bookList;
	}
	
	public void insert(BookInsertForm bookInsertForm) {
		String title = bookInsertForm.getTitle();
		String author = bookInsertForm.getAuthor();
		String publisher = bookInsertForm.getPublisher();
		Integer pages = bookInsertForm.getPages();
		Integer price = bookInsertForm.getPrice();
		Date finishedDate = bookInsertForm.getFinishedDate();
		Integer snsPush = bookInsertForm.getSnsPush();
		String insertSql = "INSERT INTO books (title, author, publisher, pages, price, finished_date, sns_push) VALUES (:title, :author, :publisher, :pages, :price, :finishedDate, :snsPush)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("title", title).addValue("author", author).addValue("publisher", publisher).addValue("pages", pages).addValue("price", price).addValue("finishedDate", finishedDate).addValue("snsPush", snsPush);
		template.update(insertSql, param);
	}
}
