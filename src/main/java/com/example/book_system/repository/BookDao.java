package com.example.book_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.book_system.entity.Book;

@ Repository
public interface BookDao extends JpaRepository<Book,String> {
		


		void save(List<String> classlist);
		
		List <Book> findByClassifyContaining(String classify);
		
		List <Book> findByNameContainingOrIsbnContainingOrAuthorContaining(String name, String isbn,String author);
		
		List<Book> findTop5ByOrderBySaleAmountDesc();
		
	}
