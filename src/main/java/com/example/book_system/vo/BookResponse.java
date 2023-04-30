package com.example.book_system.vo;

import java.util.List;
import java.util.Map;

import com.example.book_system.entity.Book;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponse {

	@JsonProperty("book_info")
	private Book book; // 回傳單筆book所有欄位
	@JsonProperty("book_list")
	private List<Book> booklist; // 回傳多筆book所有欄位

	@JsonProperty("buy_list")
	private Map<String, Integer> buymap;
	
	private Integer totalPrice;

	private String classify;// 分類
	private String message;// 回傳訊息
	private String isbn;// isbn
	private String name;// 姓名
	private String author;// 作者
	private Integer price;// 價格
	private Integer saleAmount;// 銷售量
	private Integer stock;// 庫存量
	private Integer num;// 最後所有購買書籍的數量總和

	public Map<String, Integer> getBuymap() {
		return buymap;
	}

	public BookResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookResponse(String message) {
		super();
		this.message = message;
	}

	public BookResponse(Book book, String message) {
		super();
		this.book = book;
		this.message = message;
	}

	public BookResponse(String classify, String message) {
		super();
		this.classify = classify;
		this.message = message;
	}

	public BookResponse(List<Book> booklist, String message) {
		super();
		this.booklist = booklist;
		this.message = message;
	}

	public BookResponse(String isbn, String name, String author, Integer price) {
		super();
		this.isbn = isbn;
		this.name = name;
		this.author = author;
		this.price = price;
	}

	public BookResponse(String isbn, String name, String author, Integer price, Integer saleAmount, Integer stock) {
		super();
		this.isbn = isbn;
		this.name = name;
		this.author = author;
		this.price = price;
		this.saleAmount = saleAmount;
		this.stock = stock;
	}

	public BookResponse(String isbn, String name, String author, Integer price, Integer stock) {
		super();
		this.isbn = isbn;
		this.name = name;
		this.author = author;
		this.price = price;
		this.stock = stock;
	}

	public BookResponse(String message, String isbn, String name, String author, Integer price, Integer stock) {
		super();
		this.message = message;
		this.isbn = isbn;
		this.name = name;
		this.author = author;
		this.price = price;
		this.stock = stock;
	}

	public BookResponse(List<Book> booklist, Map<String, Integer> buymap, Integer num, Integer totalPrice) {
		super();
		this.booklist = booklist;
		this.buymap = buymap;
		this.num = num;
		this.totalPrice = totalPrice;
	}

	public BookResponse(Map<String, Integer> buymap, String message, String name, String author, Integer price,
			Integer num, Integer totalPrice) {
		super();
		this.buymap = buymap;
		this.message = message;
		this.name = name;
		this.author = author;
		this.price = price;
		this.num = num;
		this.totalPrice = totalPrice;
	}

	public BookResponse(Map<String, Integer> buymap, String message, Integer totalPrice) {
		super();
		this.buymap = buymap;
		this.message = message;

		this.totalPrice = totalPrice;
	}

	public void setBuymap(Map<String, Integer> buymap) {
		this.buymap = buymap;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public List<Book> getBooklist() {
		return booklist;
	}

	public void setBooklist(List<Book> booklist) {
		this.booklist = booklist;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(Integer saleAmount) {
		this.saleAmount = saleAmount;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getClassify() {
		return classify;
	}

//	private List<String> classlist;
//	public void setClasslist(List<String> classlist) {
//	this.classlist = classlist;
//}
//
//public String getClassify() {
//	return classify;
//}
//	public List<String> getClasslist() {
//	return classlist;
//}

}
