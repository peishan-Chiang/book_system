package com.example.book_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.book_system.entity.Book;
import com.example.book_system.service.ifs.BookService;
import com.example.book_system.vo.BookRequest;
import com.example.book_system.vo.BookResponse;
import com.example.book_system.vo.BookbuyerRequest;

@RestController
public class BookController {
	@Autowired
	private BookService bookservice;
	
	@PostMapping("/addData")
	public BookResponse addData(@RequestBody BookRequest req) {
		return bookservice.addData(req);

	}
	
	@PostMapping("/muticlassify")
	public BookResponse muticlassify(@RequestBody BookRequest req) {
		return bookservice.muticlassify(req);

	}
	
	@PostMapping("/alterData")
	public BookResponse alterData(@RequestBody BookRequest req) {
		return bookservice.alterData(req);

	}
	
	@PostMapping("/find_By_Classify_Containing")
	public  List <Book> find_By_Classify_Containing(@RequestBody BookRequest req) {
		return bookservice.findByClassifyContaining(req.getClassify());

	}
	
	
	@PostMapping("/get_User_Search_Info")
	public  BookResponse  getUserSearchInfo (@RequestBody BookRequest req) {
		return bookservice.getUserSearchInfo(req);

	}
	
	
	@PostMapping("/update_Purchase_Info")
	public  BookResponse  updatePurchaseInfo (@RequestBody BookRequest req) {
		return bookservice.updatePurchaseInfo(req);

	}
	
	
	@PostMapping("/update_Price_Info")
	public  BookResponse  updatePriceInfo (@RequestBody BookRequest req) {
		return bookservice.updatePriceInfo(req);

	}
	
	@PostMapping("/booksell")
	public  BookResponse  booksell (@RequestBody BookbuyerRequest req) {
		return bookservice.booksell(req);

	}
	
	@PostMapping("/find_Top5_ByOrder_BySaleAmountDesc")
	public  List <Book> findTop5ByOrderBySaleAmountDesc() {
		return bookservice.findTop5ByOrderBySaleAmountDesc();

	}
	
	
	@PostMapping("/rank_Top5_Sale")
	public  BookResponse rankTop5Sale() {
		return bookservice.rankTop5Sale();

	}
	
	
	
}
