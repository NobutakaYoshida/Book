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
		Date finishedDate = bookInsertForm.getFinishedDate();
		String publisher = bookInsertForm.getPublisher();
		Integer pages = bookInsertForm.getPages();
		Integer price = bookInsertForm.getPrice();
		Integer snsPush = bookInsertForm.getSnsPush();
		Integer bookStyle = bookInsertForm.getBookStyle();
		Integer bigGenre = bookInsertForm.getBigGenre();
		Integer smallGenre = bookInsertForm.getSmallGenre();
		String image = bookInsertForm.getImage();
		String comment = bookInsertForm.getComment();
		
		String insertSql = "INSERT INTO books (title, author, finished_date, publisher, pages, price, sns_push, book_style, big_genre, small_genre, image, comment)"
				+ " VALUES (:title, :author, :finishedDate, :publisher, :pages, :price, :snsPush, :bookStyle, :bigGenre, :smallGenre, :image, :comment)";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("title", title).addValue("author", author)
				.addValue("finishedDate", finishedDate).addValue("publisher", publisher)
				.addValue("pages", pages).addValue("price", price)
				.addValue("snsPush", snsPush).addValue("bookStyle", bookStyle)
				.addValue("bigGenre", bigGenre).addValue("smallGenre", smallGenre)
				.addValue("image", image).addValue("comment", comment);
		template.update(insertSql, param);
	}
	
	public List<Book> findById(Integer id){
		String sql = "SELECT * FROM books WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Book> bookList = template.query(sql, param, BOOK_ROW_MAPPER);
		return bookList;
	}
}
