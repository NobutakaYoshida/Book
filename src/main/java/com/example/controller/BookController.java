package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Book;
import com.example.form.BookInsertForm;
import com.example.form.BookSearchForm;
import com.example.repository.BookRepository;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@ModelAttribute
	public BookSearchForm setUpBookSearchForm() {
		return new BookSearchForm();
	}
	
	@ModelAttribute
	public BookInsertForm setUpBookInsertForm() {
		return new BookInsertForm();
	}
	
	
	/**
	 * �g�b�v�y�[�W��\��
	 * 
	 * @return
	 */
	@RequestMapping("")
	public String index() {
		return "userview/home";
	}
	
	
	/**
	 * �Ǘ��҃��O�C���y�[�W��
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		return "entry/login";
	}
	
	/**
	 * �ǂ񂾖{��o�^�����ʂ֑J��
	 */
	@RequestMapping("/insert-page")
	public String insertPage() {
		return "book";
	}
	
	/**
	 * �ǂ񂾖{��o�^
	 * 
	 * @param bookInsertForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(BookInsertForm bookInsertForm, Model model) {
		bookRepository.insert(bookInsertForm);
		List<Book> bookList = bookRepository.findAll();
		model.addAttribute("bookList", bookList);
		return "book";
	}
	
	
	/**
	 * �ǂ񂾖{������
	 * 
	 * @param bookSearchForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/search")
	public String search(BookSearchForm bookSearchForm, Model model) {
		List<Book> bookList = bookRepository.findAll();
		if(!("".equals(bookSearchForm.getTitle()))) {
			bookList = bookRepository.search(bookSearchForm.getTitle());
			model.addAttribute("bookList", bookList);
			return "book";
		}
		model.addAttribute("bookList", bookList);
		return "book";
	}
	
	
	/**
	 * Google Books Api �y�[�W�֑J��
	 */
	@RequestMapping("/googleBooksApi")
	public String googleBooksApi() {
		return "googleBooksApi";
	}
	
}
