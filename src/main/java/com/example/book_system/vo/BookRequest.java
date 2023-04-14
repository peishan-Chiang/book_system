package com.example.book_system.vo;

import java.util.List;

import com.example.book_system.entity.Book;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookRequest {


	@JsonProperty("book_info")
	private Book book; //(輸入)請求單筆book所有欄位資料

	@JsonProperty("book_list")
	private List<Book> booklist;//(輸入)請求多筆book所有欄位資料

	
	private String newIsbn;//個別更新全部資料，所需要的新isbn(與基本資料輸入分開)
	private String newAuthor;//個別更新全部資料，所需要的新作者(與基本資料輸入分開)
	private String newName;//個別更新全部資料，所需要的新書名(與基本資料輸入分開)
	
	
	private String isbn;//isbn(用於基本資料輸入，所以如果遇到更新動作，會搭配new+變數區隔)
	private String name;//姓名(用於基本資料輸入，所以如果遇到更新動作，會搭配new+變數區隔)
	private String author;//作者(用於基本資料輸入，所以如果遇到更新動作，會搭配new+變數區隔)
	private String classify;//書籍分類
	private int price;//【更新價格】所需要更新價格
	private int stock;//【更新庫存數量】所需要更新庫存數量
	private int saleAmount;//書籍銷售量



	public BookRequest(String isbn, String classify) {
		super();
		this.isbn = isbn;
		this.classify = classify;
	}

	public BookRequest() {
		super();
		// TODO Auto-generated constructor stub
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(int saleAmount) {
		this.saleAmount = saleAmount;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<Book> getBooklist() {
		return booklist;
	}

	public void setBooklist(List<Book> booklist) {
		this.booklist = booklist;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}



	public String getNewIsbn() {
		return newIsbn;
	}

	public void setNewIsbn(String newIsbn) {
		this.newIsbn = newIsbn;
	}

	public String getNewAuthor() {
		return newAuthor;
	}

	public void setNewAuthor(String newAuthor) {
		this.newAuthor = newAuthor;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

//	@JsonProperty("classify_list")
//	private List<String> classlist;
//
//	public BookRequest(Book book, List<String> classlist2) {
//		super();
//		this.book = book;
//		this.classlist = classlist2;
//	}

//	public BookRequest(List<String> classlist, String classify) {
//	super();
//	this.classlist = classlist;
//	this.classify = classify;
//}
//
//public List<String> getClasslist() {
//	return classlist;
//}
//
//public void setClasslist(List<String> classlist) {
//	this.classlist = classlist;
//}

}
