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

	// 新增資料
	@Override
	public BookResponse addData(BookRequest req) {

		// 蒐集錯誤清單
		List<Book> errorList = new ArrayList<>();
		// 前端增加的書單
		List<Book> bookList = req.getBooklist();

		for (Book item : bookList) {

			// 如果String 沒有填寫基本資料作回傳錯誤
			if (!StringUtils.hasText(item.getName()) || !StringUtils.hasText(item.getIsbn())
					|| !StringUtils.hasText(item.getAuthor()) || !StringUtils.hasText(item.getClassify())) {
				return new BookResponse("404,name,ISBN,author,classify is empty");
			}
			// 用isbn確認，拒絕重複，加入錯誤清單
			if (bookdao.existsById(item.getIsbn())) {
				errorList.add(item);
			}

		}
		// 錯誤清單存在值，跳出訊息。
		if (!errorList.isEmpty()) {
			return new BookResponse(errorList, "ISBN existed");

		}
		// 沒錯誤訊息跳出，作資料新增，及訊息丟回
		bookdao.saveAll(bookList);
		return new BookResponse("add,successed!!");

	}

	@Override
	public BookResponse alterData(BookRequest req) {
		// 基本資料的需要先填寫
		String reqIsbn = req.getIsbn();
		String reqName = req.getName();
		String reqAuthor = req.getAuthor();

		// 後面要更新的資料，更新請求可個別提供資料更新
		String newIsbn = req.getNewIsbn();
		String newAuthor = req.getNewAuthor();
		String newName = req.getNewName();
		String newClassify = req.getClassify();
		Integer newPrice = req.getPrice();
		Integer newStock = req.getStock();
		Integer newSaleAmount = req.getSaleAmount();

		// 針對資料庫NON NULL基本資料作確認。
		if (!StringUtils.hasText(reqIsbn) || !StringUtils.hasText(reqName) || !StringUtils.hasText(reqAuthor)) {
			return new BookResponse("404,name,ISBN,author,basic info is empty");
		}
		// 根據isbn作資料搜尋
		Optional<Book> op = bookdao.findById(reqIsbn);
		// 確認主鍵拉出的資料，是否存在
		if (!op.isPresent()) {
			return new BookResponse("404,object is null");
		}
		Book result = op.get();// 獲取單筆資料改

		// 如果有下新資料請求，且前面的基本資料有打，就讓他作資料新增
		if ((StringUtils.hasText(newAuthor) && !(result.getAuthor().equals(newAuthor)))) {
			result.setAuthor(newAuthor);
			bookdao.save(result);
		}
		// 如果有下新資料請求，且前面的基本資料有打，就讓他作資料新增
		if ((StringUtils.hasText(newClassify) && !(result.getClassify().equals(newClassify)))) {
			result.setClassify(newClassify);
			bookdao.save(result);
		}
		// 如果有下新資料請求，且前面的基本資料有打，就讓他作資料新增
		if ((StringUtils.hasText(newName) && !(result.getName().equals(newName)))) {
			result.setName(newName);
			bookdao.save(result);
		}

		// 如果有下新資料請求，且前面的基本資料有打，就讓他作資料新增
		if ((StringUtils.hasText(newIsbn)) && !(result.getIsbn().equals(newIsbn))) {
			bookdao.deleteById(reqIsbn);
			result.setIsbn(newIsbn);
			bookdao.save(result);
		}

		// 如果有下新資料請求，且前面的基本資料有打，就讓他作資料新增
		if (existednum(req, result)) {
			result.setPrice(newPrice);
			bookdao.save(result);
		}

		// 如果有下新資料請求，且前面的基本資料有打，就讓他作資料新增
		if (existednum(req, result)) {
			result.setSaleAmount(newSaleAmount);
			bookdao.save(result);
		}
		// 如果有下新資料請求，且前面的基本資料有打，就讓他作資料新增
		if (existednum(req, result)) {
			result.setStock(newStock);
			bookdao.save(result);
		}

		return new BookResponse("update info");

	}

	// 銷售，庫存數量，價格，不允許與過去重複
	private boolean existednum(BookRequest req, Book book) {
		boolean result = !(req.getPrice() == book.getPrice()) || !(req.getSaleAmount() == book.getSaleAmount())
				|| !(req.getStock() == book.getStock());

		return result;
	}

//===========================================================================
//    //針對多筆分類作輸入，如果有多個分類需要作格式【;】，併寫入欄位
//	@Override
//	public BookResponse muticlassify(BookRequest req) {
//		
//		//new清單放我的分類資料
//		List<String> classlist = new ArrayList<>();
//		classlist = req.getClasslist();
//
//
//		//根據isbn作資料搜尋
//		Optional<Book> op = bookdao.findById(req.getIsbn());
//		//判斷是否搜尋到資料
//		if (!op.isPresent()) {
//			return new BookResponse("404,object is null");
//		}
//		Book book = op.get();// 獲取單筆資料改
//		
//		//如果我沒下分類資料，我就不更新
//		if (classlist.isEmpty()) {
//			return new BookResponse("404,non Update");
//		}
//		//有下資料，協助改格式【A;B;C;】
//		String string = " ";
//		for (String classifyitem : classlist) {
//			String str = classifyitem.toString();
//			string = str + ";" + string;
//		}
//		//完成格式用更新填入
//		book.setClassify(string);
//		bookdao.save(book);
//
//
//		return new BookResponse(string, "Update the classifyinfo");
//	}
//    
//	===================================================================
	// 針對分類欄位去搜尋關鍵字
	@Override
	public List<Book> findByClassifyContaining(String classify) {
		// new 空白清單，後面放要提供的結果
		List<Book> result = new ArrayList<>();
		// 將我查詢到的分類放到list <book>格式
		List<Book> bookList = bookdao.findByClassifyContaining(classify);
		// 我將分類+銷售額資料設定null，放到result清單
		for (Book item : bookList) {
			item.setSaleAmount(null);
			item.setClassify(null);
			result.add(item);
		}

		return result;

	}
//====================================================================================

	// 定義一個方法，如果消費者作A回復，如果書商作B回復
	private BookResponse getInfoByUserOrBookseller(BookRequest req, boolean isUser) {
		// new 空白清單，後面放要提供的結果
		List<Book> result = new ArrayList<>();
		// 針對姓名+isbn+作者，其中任一欄位下關鍵字都搜尋
		List<Book> bookList = bookdao.findByNameContainingOrIsbnContainingOrAuthorContaining(req.getName(),
				req.getIsbn(), req.getAuthor());

		// 消費者情況
		if (isUser) {
			// 將庫存數量、銷售量、分類作null，加到新清單，讓他看不到
			for (Book item : bookList) {
				item.setStock(null);
				item.setSaleAmount(null);
				item.setClassify(null);
				result.add(item);
			}
			// 書名、ISBN、作者、價格
			return new BookResponse(result, "User info");
//			全部欄位:(書名、ISBN、作者、價格、庫存數量、銷售量、分類)
		}
		// 不是消費者情況(書商)
		for (Book item : bookList) {
			item.setClassify(null);
			result.add(item);
		}
		// 書名、ISBN、作者、價格、銷售量、庫存量
		return new BookResponse(result, "publiser info");
	}

//	將定義方法userOrBookseller，使用到【消費端】getUserSearchInfo動作方法上
	@Override
	public BookResponse getUserBookInfo(BookRequest req) {
		return getInfoByUserOrBookseller(req, true);
	}

//	將定義方法userOrBookseller，使用到【書商端】getPublisherSearchInfo動作方法上
	@Override
	public BookResponse getPublisherBookInfo(BookRequest req) {
		return getInfoByUserOrBookseller(req, false);
	}

//	製作一個Dao方法，提供給userOrBookseller的方法使用，針對書名，isbn，作者作模糊搜尋
//	@Override
//	public List<Book> findByNameContainingOrIsbnContainingOrAuthorContaining(String name, String isbn, String author) {
//		return bookdao.findByNameContainingOrIsbnContainingOrAuthorContaining(name, isbn, author);
//	}
//============================================================================
	// 定義一個判斷方法，是【更新庫存】或是【更新價格】
	private BookResponse updateStockOrPrice(BookRequest req, boolean isStock) {

		String reqName = req.getName();// 書名
		String reqIsbn = req.getIsbn();// isbn

		Integer reqNewStock = req.getStock();// 更新的庫存。

		Integer reqNewPrice = req.getPrice();// 更新的價格。

		// 輸入的值不能為空
		if (!StringUtils.hasText(reqName) || !StringUtils.hasText(reqIsbn)) {
			return new BookResponse("404, NAME and ISBN are null");
		}
		// key值找受否存在
		Optional<Book> op = bookdao.findById(reqIsbn);
		Book book = op.get();
//		【更新庫存】
		if (isStock) {
			// 過去與更新資料相同，不跑更新
			if (reqNewStock.equals(book.getStock())) {
				return new BookResponse("Warning: same purchase amount, update unneeded!");
			}
			// 進貨需要新的數字>舊數字
			if ((int) reqNewStock < (int) book.getStock()) {
				return new BookResponse("Warning: same purchase amount, less than original!");
			}
			// 進貨，不存在更新成為零情況
			if ((int) reqNewStock == 0) {
				return new BookResponse("Warning: irrational purchase amount,zero!");
			}
			// 以上狀況，無，作更新變數
			book.setStock(reqNewStock);
			// 變數更新SQL對應欄位
			bookdao.save(book);
			// 呈現結果
			return new BookResponse("purchase info,update", book.getIsbn(), book.getName(), book.getAuthor(),
					book.getPrice(), book.getStock());
		}
		// 【更新價格】
		// 如果與過去相同，不執行
		if (reqNewPrice.equals(book.getPrice())) {
			return new BookResponse("Warning: same price,update unneeded!");
		}
		// 如果價格為零，不執行
		if ((int) reqNewPrice == 0) {
			return new BookResponse("Warning: irrational price, zero!");
		}

		// 以上狀況，無，作更新變數
		book.setPrice(reqNewPrice);
		// 變數更新SQL對應欄位
		bookdao.save(book);
		// 呈現結果
		return new BookResponse("price info,update", book.getIsbn(), book.getName(), book.getAuthor(), book.getPrice(),
				book.getStock());
	}

	// 根據stockOrPrice方法，使用再updatePurchaseInfo，並指出為【進貨，更新庫存】
	@Override
	public BookResponse updateStockInfo(BookRequest req) {

		return updateStockOrPrice(req, true);
	}

	// 根據stockOrPrice方法，使用再updatePriceInfo，並指出為【更新價格】
	@Override
	public BookResponse updatePriceInfo(BookRequest req) {
		return updateStockOrPrice(req, false);
	}

//	=======================================================================
	// 銷售書籍
	@Override
	public BookResponse bookSell(BookbuyerRequest req) {
		List<Book> finalSelectList = new ArrayList<>();// 確定購入清單
		List<Book> stockAndSalesRecordList = new ArrayList<>();// 更新庫存銷售量結果
		List<Book> withoutStockSalesClassifyList = new ArrayList<>();

		Map<String, Integer> buylist = new HashMap<>();// new map清單，放 isbn:數量
		buylist = req.getBuymap();

		Book book;
		int totalNum = 0;// 真的買的購書總數量
		int totalPrice = 0;// 累計價格
		int left = 3;// 最多三本，去控制迴圈回合數
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

			finalSelectList.add(book);// 將選購的資料，加入到購入清單

			// for帳面資料
			int itemTotalPrice = 0;// 買完總價 :(單價*數量)
			int price = book.getPrice();// 單價

			itemTotalPrice = price * item.getValue();//單一品項的總價
			totalPrice += itemTotalPrice;//不同品項間的累加總價
			totalNum += num;//最後總共買下(不同品項間)的書數量

		}

		// 計算個別書名的庫存減少與增加
		for (Book bookitem : finalSelectList) {
			//表示該品項，原銷售量新增當下銷售的數量，為新銷售量
			int newSale = bookitem.getSaleAmount() + num;
			bookitem.setSaleAmount(newSale);
			
			//表示該品項，原庫存量扣除當下銷售的數量，為新庫存量
			int newStock = bookitem.getStock() - num;
			bookitem.setStock(newStock);
			// 如果沒庫存，不讓銷售
			if (newStock < 0) {
				return new BookResponse("404,non stock");
			}
			// 如果有庫存，將新庫存資料及銷售量資料更新
			bookdao.save(bookitem);
			// 將新庫存資料及銷售量資料更新後，加入新清單，去作呈現的格式調整
			stockAndSalesRecordList.add(bookitem);

		}
		// 將新每筆的庫存，銷售量，分類作null，不讓畫面呈現
		for (Book item : stockAndSalesRecordList) {
			item.setStock(null);
			item.setSaleAmount(null);
			item.setClassify(null);
			withoutStockSalesClassifyList.add(item);
		}

		return new BookResponse(withoutStockSalesClassifyList, buylist, totalNum, totalPrice);

	}

//    ========================================================================
	// 針對銷售額，搜尋前五筆，大至小排序的Dao方法(建議註解調)
//	@Override
//	public List<Book> findTop5ByOrderBySaleAmountDesc() {
//		return bookdao.findTop5ByOrderBySaleAmountDesc();
//	}
	// 將findTop5ByOrderBySaleAmountDesc()，應用到rankTop5Sale()
	@Override
	public BookResponse rankTop5Sale() {
		List<Book> result = new ArrayList<>();// 更改呈現結果格式的清單
		List<Book> bookList = bookdao.findTop5ByOrderBySaleAmountDesc();// 搜尋的結果清單

		// 將每筆的庫存，銷售量，分類作null，不讓畫面呈現
		for (Book item : bookList) {
			item.setSaleAmount(null);
			item.setStock(null);
			item.setClassify(null);
			result.add(item);
		}

		return new BookResponse(result, "Rank Top5 info");

	}

}
