package com.bookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookListService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookSer;
	
	
	@Autowired
	private MyBookListService myBookSer;
	
	
	@GetMapping("/")
	public String home() {
		return"home";
	}
	
	@GetMapping("/sub-book")
	public String Submot_bookLis() {
		return"bookList";
	}
	
//	@GetMapping("/book_register")
//		public String bookRegister(Model model) {
//		model.addAttribute("regiBook",new Book());
//			return "bookRegister";
//		}
	
	@GetMapping("/book_register")
	public String bookRegister() {
		return "bookRegister";
	}
	
	@GetMapping("/available_books")
	public ModelAndView getAllBook() {
		List<Book> list=bookSer.getAllBook();
		ModelAndView m=new ModelAndView();
		m.setViewName("bookList");
		m.addObject("book",list);
		return new ModelAndView("bookList","book",list);
	}
	
	
//	@GetMapping("/available_books")
//	public String getAllBook(Model model) {
//		model.addAttribute("book",bookSer.getAllBook());
//		return "available_books";
//	}
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b) {
		bookSer.save(b);
		return "redirect:/available_books";
	}
	
	@GetMapping("/my_books")
	public String getMyBooks(Model model) {
		List<MyBookList> list=myBookSer.getAllMyBooks();
		model.addAttribute("book",list);
	return "mybooks";	
	}
	
//	@RequestMapping("mylist/{id}")
//	public String getMylist(@PathVariable("id")int id) {
//		Book b=bookSer.getBookById(id);
//		MyBookList md=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
//		myBookSer.saveMyBooks(md);
//		return"redirect:/mybooks";
//	}
	
	
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id) {
		Book b=bookSer.getBookById(id);
		MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
		myBookSer.saveMyBooks(mb);
		return"redirect:/my_books";
	}
	
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id,Model model) {
		Book b=bookSer.getBookById(id);
		model.addAttribute("book", b);
		return"bookEdit";
	}
	
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id")int id) {
		 bookSer.deleteById(id);
		return"redirect:/available_books";
	}
	
	@RequestMapping("/deletMyList/{id}")
	public String deleteMyList(@PathVariable("id")int id) {
		myBookSer.deleteById(id);
		return "redirect:/my_books";
	}
	
	
	
	
	

}
