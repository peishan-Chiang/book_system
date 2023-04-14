package com.example.book_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.book_system.entity.Book;

@ Repository
public interface BookDao extends JpaRepository<Book,String> {
		


//		void save(List<String> classlist);
		
//	    分類欄位模糊搜尋
		List <Book> findByClassifyContaining(String classify);
//		姓名，isbn，作者欄位模糊搜尋
		List <Book> findByNameContainingOrIsbnContainingOrAuthorContaining(String name, String isbn,String author);
//		銷售量由大到小排名且取前五筆
		List<Book> findTop5ByOrderBySaleAmountDesc();
		
	}
