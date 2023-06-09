package com.example.book_system.entity;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "book_sell")

public class Book {
	@Id
	@Column(name="isbn")
	private String isbn;//書籍isbn
	
	@Column(name="name")
	private String name;//書名
	
	@Column(name="author")
	private String author;//作者
	
	@Column(name="price")
	private Integer price;//價格
	
	@Column(name="stock")
	private Integer stock;//庫存

	@Column(name="sale_amount")
	private Integer saleAmount;//數本銷售量
	
	@Column(name="classify")
	private String classify;//書籍分類

	
//	private List<String> classifyList;
	
	@JsonProperty("classitfy_List")
	@Transient
	private List<String> classifyList;

	public Book(String isbn, String name, String author, Integer price, Integer stock, Integer saleAmount, String classify) {
		super();
		this.isbn = isbn;
		this.name = name;
		this.author = author;
		this.price = price;
		this.stock = stock;
		this.saleAmount = saleAmount;
		this.classify = classify;
	}
	
	public Book(String isbn, String name, String author, String classify) {
		super();
		this.isbn = isbn;
		this.name = name;
		this.author = author;
		this.classify = classify;
	}

	public Book() {
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(Integer saleAmount) {
		this.saleAmount = saleAmount;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public List<String> getClassifyList() {
		return classifyList;
	}

	public void setClassifyList(List<String> classifyList) {
		this.classifyList = classifyList;	
	
	
	}
	
}
