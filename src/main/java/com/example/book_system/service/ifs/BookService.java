package com.example.book_system.service.ifs;

import java.util.List;

import com.example.book_system.entity.Book;
import com.example.book_system.vo.BookRequest;
import com.example.book_system.vo.BookResponse;
import com.example.book_system.vo.BookbuyerRequest;

public interface BookService {
 
	public BookResponse addData(BookRequest req) ;//基本資料建置
    public BookResponse alterData(BookRequest req) ;//修改資料
	public List <Book> findByClassifyContaining(String classify);//根據分類去作搜尋(DAO模糊收尋:種類)
	public BookResponse getUserBookInfo(BookRequest req) ; //書籍搜尋(消費者)
	public BookResponse getPublisherBookInfo(BookRequest req) ;//書籍搜尋(書籍商)
	public BookResponse updateStockInfo(BookRequest req) ;//更新庫存資料(進貨)
	public BookResponse updatePriceInfo(BookRequest req) ;//更新價格資料
	public BookResponse bookSell(BookbuyerRequest req) ;//書本銷售
	public BookResponse rankTop5Sale() ;//銷售量前5名
	
//	public List<Book> findTop5ByOrderBySaleAmountDesc();
//	public BookResponse  muticlassify(BookRequest req) ;//針對多數種類作修改新增
//	public List <Book> findByNameContainingOrIsbnContainingOrAuthorContaining(String name, String isbn,String author);//Dao搜尋名字跟ISBN
	
	
	
	
	
	
}

