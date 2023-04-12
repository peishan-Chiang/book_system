package com.example.book_system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.book_system.entity.Book;
import com.example.book_system.repository.BookDao;
import com.example.book_system.service.ifs.BookService;
import com.example.book_system.vo.BookRequest;
import com.example.book_system.vo.BookResponse;
import com.example.book_system.vo.BookbuyerRequest;

@Service
public class BookServiceimpl implements BookService {

	@Autowired
	private BookDao bookdao;

	@Override
	public BookResponse addData(BookRequest req) {

		List<Book> errorlist = new ArrayList<>();
		List<Book> booklist = req.getBooklist();

		for (Book item : booklist) {
			if (!StringUtils.hasText(item.getName()) || !StringUtils.hasText(item.getIsbn())
					|| !StringUtils.hasText(item.getAuthor()) || !StringUtils.hasText(item.getClassify())) {
				return new BookResponse("404,name,ISBN,author,classify is empty");
			}

			if (bookdao.existsById(item.getIsbn())) {
				errorlist.add(item);
			}

		}
		if (!errorlist.isEmpty()) {
			return new BookResponse(errorlist, "ISBN existed");

		}
		bookdao.saveAll(booklist);
		return new BookResponse("add,successed!!");

	}

	@Override
	public BookResponse alterData(BookRequest req) {

		
			
			
			//基本資料
			String reqisbn = req.getIsbn();
			String reqname = req.getName();
			String reqauthor = req.getAuthor();
			
			//request update info
			String newreqisbn = req.getNewIsbn();
			String newAuthor = req.getNewAuthor();
			String newName = req.getNewName();
			String newclassify = req.getNewclassify();
			

			
			Integer reqprice = req.getPrice();
			Integer reqstock = req.getStock();
			Integer reqsaleAmount = req.getSaleAmount();
			String reqclassify = req.getClassify();

			if (!StringUtils.hasText(reqisbn) || !StringUtils.hasText(reqname) || !StringUtils.hasText(reqauthor)) {
				return new BookResponse("404,name,ISBN,author,classify is empty");
			}

			Optional<Book> op = bookdao.findById(reqisbn);
			if (!op.isPresent()) {
				return new BookResponse("404,object is null");
			}
			Book result = op.get();// 獲取單筆資料改
//			result.setAuthor(reqauthor);
//			result.setClassify(reqclassify);
//			result.setName(reqname);
//			result.setPrice(reqprice);
//			result.setSaleAmount(reqsaleAmount);
//			result.setStock(reqstock);

			if (existed(req, result) && (StringUtils.hasText(newAuthor))) {
				result.setAuthor(newAuthor);
				bookdao.save(result);
			}

			if (existed(req, result) && (StringUtils.hasText(newclassify))) {
				result.setClassify(newclassify);
				bookdao.save(result);
			}
			if (existed(req, result) && (StringUtils.hasText(newName))) {
				result.setName(newName);
				bookdao.save(result);
			}
			if (existed(req, result) && (StringUtils.hasText(newreqisbn))) {
				bookdao.deleteById(reqisbn);
				result.setIsbn(newreqisbn);
				bookdao.save(result);
			}
			if (existednum(req, result)) {
				result.setPrice(reqprice);
				bookdao.save(result);
			}
			if (existednum(req, result)) {
				result.setSaleAmount(reqsaleAmount);
				bookdao.save(result);
			}
			if (existednum(req, result)) {
				result.setStock(reqstock);
				bookdao.save(result);
			}

			return new BookResponse("update info");

		}

		private boolean existed(BookRequest req, Book book) {
			boolean result = StringUtils.hasText(req.getName()) || StringUtils.hasText(req.getAuthor())
					 || StringUtils.hasText(req.getIsbn());

			return result;
		}

		private boolean existednum(BookRequest req, Book book) {
			boolean result = !(req.getPrice() == book.getPrice()) || !(req.getSaleAmount() == book.getSaleAmount())
					|| !(req.getStock() == book.getStock());

			return result;
		}


	@Override
	public BookResponse muticlassify(BookRequest req) {
		List<String> classlist = new ArrayList<>();
		classlist = req.getClasslist();

//		!StringUtils.hasText(req.getName()) ||

		Optional<Book> op = bookdao.findById(req.getIsbn());
		if (!op.isPresent()) {
			return new BookResponse("404,object is null");
		}
		Book book = op.get();// 獲取單筆資料改
		if (classlist.isEmpty()) {
			return new BookResponse("404,non Update");
		}
		String string = " ";
		for (String classifyitem : classlist) {
			String str = classifyitem.toString();
			string = str + ";" + string;
		}

		book.setClassify(string);
		bookdao.save(book);

//		
		return new BookResponse(string, "Update the classifyinfo");
	}

	@Override
	public List<Book> findByClassifyContaining(String classify) {
		List<Book> result = new ArrayList<>();
		List<Book> booklist = bookdao.findByClassifyContaining(classify);

		for (Book item : booklist) {
			item.setSaleAmount(null);
			item.setClassify(null);
			result.add(item);
		}

		return result;

	}

	private BookResponse userOrBookseller(BookRequest req, boolean isUser) {

		List<Book> result = new ArrayList<>();
		List<Book> booklist = bookdao.findByNameContainingOrIsbnContainingOrAuthorContaining(req.getName(),
				req.getIsbn(), req.getAuthor());
		if (isUser) {
			for (Book item : booklist) {
				item.setStock(null);
				item.setSaleAmount(null);
				item.setClassify(null);
				result.add(item);
			}
			return new BookResponse(result, "User info");
			// 書名、ISBN、作者、價格(書名、ISBN、作者、價格、庫存數量、銷售量、分類)
		} // 書名、ISBN、作者、價格、銷售量、庫存量

		for (Book item : booklist) {
			item.setClassify(null);
			result.add(item);
		}
		return new BookResponse(result, "publiser info");
	}

	@Override
	public BookResponse getUserSearchInfo(BookRequest req) {
		return userOrBookseller(req, true);
	}

	@Override
	public BookResponse getPublisherSearchInfo(BookRequest req) {
		return userOrBookseller(req, false);
	}

	@Override
	public List<Book> findByNameContainingOrIsbnContainingOrAuthorContaining(String name, String isbn, String author) {
		return bookdao.findByNameContainingOrIsbnContainingOrAuthorContaining(name, isbn, author);
	}

	private BookResponse stockOrPrice(BookRequest req, boolean isStock) {
		String reqName = req.getName();
		String reqIsbn = req.getIsbn();

		Integer reqNewStock = req.getNewStock();// 更新的庫存。

		Integer reqNewprice = req.getNewPrice();

		if (!StringUtils.hasText(reqName) || !StringUtils.hasText(reqIsbn)) {
			return new BookResponse("404, NAME and ISBN are null");
		}
		Optional<Book> op = bookdao.findById(reqIsbn);
		Book book = op.get();
		if (isStock) {
			if (reqNewStock.equals(book.getStock())) {
				return new BookResponse("Warning: same purchase amount, update unneeded!");
			}

			if ((int) reqNewStock < (int) book.getStock()) {
				return new BookResponse("Warning: same purchase amount, less than original!");
			}
			if ((int) reqNewStock == 0) {
				return new BookResponse("Warning: irrational purchase amount,zero!");
			}

			book.setStock(reqNewStock);
			bookdao.save(book);
			return new BookResponse("purchase info,update", book.getIsbn(), book.getName(), book.getAuthor(),
					book.getPrice(), book.getStock());
		} // String isbn, String name, String author, Integer price, Integer stock

		if (reqNewprice.equals(book.getPrice())) {
			return new BookResponse("Warning: same price,update unneeded!");
		}

		if ((int) reqNewprice == 0) {
			return new BookResponse("Warning: irrational price, zero!");
		}
		book.setPrice(reqNewprice);
		;
		bookdao.save(book);
		return new BookResponse("price info,update", book.getIsbn(), book.getName(), book.getAuthor(), book.getPrice(),
				book.getStock());
	}

	@Override
	public BookResponse updatePurchaseInfo(BookRequest req) {

		return stockOrPrice(req, true);
	}

	@Override
	public BookResponse updatePriceInfo(BookRequest req) {
		return stockOrPrice(req, false);
	}

	@Override
	public BookResponse booksell(BookbuyerRequest req) {
		List<Book> result = new ArrayList<>();
		List<Book> result1 = new ArrayList<>();
		List<Book> result2 = new ArrayList<>();

		Map<String, Integer> buylist = new HashMap<>();
		buylist = req.getBuymap();
//		<String, Integer> result = new HashMap<String, Integer>();
		Book book;
		int totalnum = 0;
		int count = 0;
		int totalPrice = 0;
		int left = 3;
		int num = 0;
		for (Entry<String, Integer> item : buylist.entrySet()) {

			String listItem = item.getKey();// 抓key
			num = item.getValue();
			left -= num;

			if (left <= 0) {
				item.setValue(num + left);
				num = (num + left);
			}

			Optional<Book> op = bookdao.findById(listItem);// 確認是存在
			if (!op.isPresent()) {
				return new BookResponse("404,primary key is null");
			}
			book = op.get();

			result.add(book);

			// for帳面資料
			int itemTotalPrice = 0;
			int price = book.getPrice();

			itemTotalPrice = price * item.getValue();
			totalPrice += itemTotalPrice;
			totalnum += num;

		}

		for (Book bookitem : result) {
			int newSale = bookitem.getSaleAmount() + num;
			bookitem.setSaleAmount(newSale);
			int newStock = bookitem.getStock() - num;
			bookitem.setStock(newStock);

			if (newStock < 0) {
				return new BookResponse("404,non stock");
			}

			bookdao.save(bookitem);

			result1.add(bookitem);

		}

		for (Book item : result1) {
			item.setStock(null);
			item.setSaleAmount(null);
			item.setClassify(null);
			result2.add(item);
		}

		return new BookResponse(result2, buylist, totalnum, totalPrice);

	}

	@Override
	public List<Book> findTop5ByOrderBySaleAmountDesc() {
		return bookdao.findTop5ByOrderBySaleAmountDesc();
	}

	@Override
	public BookResponse rankTop5Sale() {
		List<Book> result = new ArrayList<>();
		List<Book> booklist = bookdao.findTop5ByOrderBySaleAmountDesc();
		for (Book item : booklist) {
			item.setSaleAmount(null);
			item.setClassify(null);
			item.setClassify(null);
			result.add(item);
		}

		return new BookResponse(result, "Rank Top5 info");

	}

}
