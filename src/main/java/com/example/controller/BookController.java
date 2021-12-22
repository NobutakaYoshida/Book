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
	public String index(Model model) {
		List<Book> bookList = bookRepository.findAll();
		model.addAttribute("bookList", bookList);
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
		return "userview/insertPage";
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
		return "redirect:/book/ok";
	}
	
	@RequestMapping("/ok")
	public String ok() {
		return "userview/ok";
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
		
		
//		index.html�������ׂɕύX
//		return "index";
	}
	
	
	/**
	 * ���[���𑗂�y�[�W�֑J��
	 */
	@RequestMapping("/sendEmailPage")
	public String sendEmailPage() {
		return "userview/sendEmailPage";
	}
	
	
	/**
	 * �{�̏ڍ׃y�[�W�֑J��
	 */
	@RequestMapping("/detail")
	public String detail(Integer id, Model model) {
		List<Book> bookList = bookRepository.findById(id);
		model.addAttribute("bookList", bookList);
		
		for (Book book : bookList) {
			String bookStyle = book.getBookStyle().toString();
			String bigGenre = book.getBigGenre().toString();
			String smallGenre = book.getSmallGenre().toString();
			String snsPush = book.getSnsPush().toString();
			
			if(bookStyle.equals("1")) {
				bookStyle = "�P�s�{";
			} else if(bookStyle.equals("2")) {
				bookStyle = "�V��";
			} else if(bookStyle.equals("3")) {
				bookStyle = "����";
			} else if(bookStyle.equals("4")) {
				bookStyle = "�}���K";
			}
			model.addAttribute("bookStyle", bookStyle);
			
			if(bigGenre.equals("1")) {
				bigGenre = "���Ȍ[��";
			} else if(bigGenre.equals("2")) {
				bigGenre = "����";
			} else if(bigGenre.equals("3")) {
				bigGenre = "�]�_";
			}
			model.addAttribute("bigGenre", bigGenre);
			
			if(smallGenre.equals("1")) {
				smallGenre = "��";
			} else if(smallGenre.equals("2")) {
				smallGenre = "�~�X�e���[";
			} else if(smallGenre.equals("3")) {
				smallGenre = "����";
			} else if(smallGenre.equals("4")) {
				smallGenre = "�}�[�P�e�B���O";
			} else if(smallGenre.equals("5")) {
				smallGenre = "���j";
			} else if(smallGenre.equals("6")) {
				smallGenre = "�}�l�W�����g";
			} else if(smallGenre.equals("7")) {
				smallGenre = "��w";
			} else if(smallGenre.equals("8")) {
				smallGenre = "���w";
			}
			model.addAttribute("smallGenre", smallGenre);
			
			if(snsPush.equals("1")) {
				snsPush = "�����e";
			} else if(snsPush.equals("2")) {
				snsPush = "���e��";
			}
			model.addAttribute("snsPush", snsPush);
		}
		
		return "userview/bookDetail";
	}
	
}
