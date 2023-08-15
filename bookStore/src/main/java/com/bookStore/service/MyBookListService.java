package com.bookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.entity.MyBookList;
import com.bookStore.repository.MyBookRepositry;

@Service
public class MyBookListService {
	
	@Autowired
	private MyBookRepositry myBookRep;
	
	
	public void saveMyBooks(MyBookList book) {
		myBookRep.save(book);
	}
	
	public List<MyBookList> getAllMyBooks(){
		return myBookRep.findAll();
	}
	
	public void deleteById(int id) {
		myBookRep.deleteById(id);
	}

}
