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
	 * トップページを表示
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
	 * 管理者ログインページへ
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		return "entry/login";
	}
	
	/**
	 * 読んだ本を登録する画面へ遷移
	 */
	@RequestMapping("/insert-page")
	public String insertPage() {
		return "userview/insertPage";
	}
	
	/**
	 * 読んだ本を登録
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
	 * 読んだ本を検索
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
	 * Google Books Api ページへ遷移
	 */
	@RequestMapping("/googleBooksApi")
	public String googleBooksApi() {
		return "googleBooksApi";
		
		
//		index.htmlを試す為に変更
//		return "index";
	}
	
	
	/**
	 * メールを送るページへ遷移
	 */
	@RequestMapping("/sendEmailPage")
	public String sendEmailPage() {
		return "userview/sendEmailPage";
	}
	
	
	/**
	 * 本の詳細ページへ遷移
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
				bookStyle = "単行本";
			} else if(bookStyle.equals("2")) {
				bookStyle = "新書";
			} else if(bookStyle.equals("3")) {
				bookStyle = "文庫";
			} else if(bookStyle.equals("4")) {
				bookStyle = "マンガ";
			}
			model.addAttribute("bookStyle", bookStyle);
			
			if(bigGenre.equals("1")) {
				bigGenre = "自己啓発";
			} else if(bigGenre.equals("2")) {
				bigGenre = "小説";
			} else if(bigGenre.equals("3")) {
				bigGenre = "評論";
			}
			model.addAttribute("bigGenre", bigGenre);
			
			if(smallGenre.equals("1")) {
				smallGenre = "株";
			} else if(smallGenre.equals("2")) {
				smallGenre = "ミステリー";
			} else if(smallGenre.equals("3")) {
				smallGenre = "恋愛";
			} else if(smallGenre.equals("4")) {
				smallGenre = "マーケティング";
			} else if(smallGenre.equals("5")) {
				smallGenre = "歴史";
			} else if(smallGenre.equals("6")) {
				smallGenre = "マネジメント";
			} else if(smallGenre.equals("7")) {
				smallGenre = "語学";
			} else if(smallGenre.equals("8")) {
				smallGenre = "文学";
			}
			model.addAttribute("smallGenre", smallGenre);
			
			if(snsPush.equals("1")) {
				snsPush = "未投稿";
			} else if(snsPush.equals("2")) {
				snsPush = "投稿済";
			}
			model.addAttribute("snsPush", snsPush);
		}
		
		return "userview/bookDetail";
	}
	
}
