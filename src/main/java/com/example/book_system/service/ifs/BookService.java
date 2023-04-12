package com.example.book_system.service.ifs;

import java.util.List;

import com.example.book_system.entity.Book;
import com.example.book_system.vo.BookRequest;
import com.example.book_system.vo.BookResponse;
import com.example.book_system.vo.BookbuyerRequest;

public interface BookService {
 
	public BookResponse addData(BookRequest req) ;//基本資料建置
	
	public BookResponse  muticlassify(BookRequest req) ;//針對多數種類作修改新增
 
	public BookResponse alterData(BookRequest req) ;
	
	public List <Book> findByClassifyContaining(String classify);//DAO模糊收尋:種類
	
	public List <Book> findByNameContainingOrIsbnContainingOrAuthorContaining(String name, String isbn,String author);//Dao搜尋名字跟ISBN
	
	public BookResponse getUserSearchInfo(BookRequest req) ;
	public BookResponse getPublisherSearchInfo(BookRequest req) ;
	
	
	public BookResponse updatePurchaseInfo(BookRequest req) ;
	public BookResponse updatePriceInfo(BookRequest req) ;
	
	public BookResponse booksell(BookbuyerRequest req) ;
	
	public List<Book> findTop5ByOrderBySaleAmountDesc();
	
	public BookResponse rankTop5Sale() ;
	
	
	
	
}

