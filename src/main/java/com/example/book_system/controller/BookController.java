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

	@PostMapping("/addData") // 新增資料
	public BookResponse addData(@RequestBody BookRequest req) {
		return bookservice.addData(req);

	}

	@PostMapping("/alterData") // (更新)修改個別欄位內容
	public BookResponse alterData(@RequestBody BookRequest req) {
		return bookservice.alterData(req);

	}

	@PostMapping("/find_By_Classify_Containing") // 針對分類欄位模糊搜尋
	public List<Book> findByClassifyContaining(@RequestBody BookRequest req) {
		return bookservice.findByClassifyContaining(req.getClassify());

	}

	@PostMapping("/get_User_Book_Info") // 消費者搜尋方法
	public BookResponse getUserBookInfo(@RequestBody BookRequest req) {
		return bookservice.getUserBookInfo(req);

	}

	@PostMapping("/get_Publisher_Book_Info") // 書商搜尋方法
	public BookResponse getPublisherBookInfo(@RequestBody BookRequest req) {
		return bookservice.getPublisherBookInfo(req);

	}

	@PostMapping("/update_Stock_Info") // 進貨更新
	public BookResponse updateStockInfo(@RequestBody BookRequest req) {
		return bookservice.updateStockInfo(req);

	}

	@PostMapping("/update_Price_Info") // 價格更新
	public BookResponse updatePriceInfo(@RequestBody BookRequest req) {
		return bookservice.updatePriceInfo(req);

	}

	@PostMapping("/book_Sell") // 書籍銷售
	public BookResponse bookSell(@RequestBody BookbuyerRequest req) {
		return bookservice.bookSell(req);

	}

	@PostMapping("/rank_Top5_Sale") // 銷售量排名
	public BookResponse rankTop5Sale() {
		return bookservice.rankTop5Sale();

	}
//	@PostMapping("/muticlassify")//多筆分類更新(我另外寫的，分類欄位拉出來協助分類格式寫入)
//	public BookResponse muticlassify(@RequestBody BookRequest req) {
//		return bookservice.muticlassify(req);
//
//	}

}
