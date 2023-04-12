package com.example.book_system.vo;

import java.util.List;

import com.example.book_system.entity.Book;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookRequest {
	@JsonProperty("book_info")
	private Book book;
	
	@JsonProperty("book_list")
	private List<Book> booklist;

	@JsonProperty("classify_list")
	private List<String> classlist;
	private String newIsbn;
	private String newAuthor;
	private String newName;
	private String newclassify;
	private String isbn;
	

	private String name;
	
	private String author;
	

	private int price;
	

	private int stock;


	private int saleAmount;
	

	private String classify;
	
	
	private int newPrice;

	
	private int newStock;
	
	
	

	public BookRequest(Book book, List<String> classlist2) {
		super();
		this.book = book;
		this.classlist = classlist2;
	}

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

	

	

	

	

	public BookRequest(List<String> classlist, String classify) {
		super();
		this.classlist = classlist;
		this.classify = classify;
	}

	public List<String> getClasslist() {
		return classlist;
	}

	public void setClasslist(List<String> classlist) {
		this.classlist = classlist;
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



	public int getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(int newPrice) {
		this.newPrice = newPrice;
	}

	
	public int getNewStock() {
		return newStock;
	}

	public void setNewStock(int newStock) {
		this.newStock = newStock;
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

	public String getNewclassify() {
		return newclassify;
	}

	public void setNewclassify(String newclassify) {
		this.newclassify = newclassify;
	}

	
	
}

