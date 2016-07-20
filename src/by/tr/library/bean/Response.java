package by.tr.library.bean;

import java.util.ArrayList;

public class Response {
	
	private ArrayList<Book> listBook;
	private Book book;
	private String errorMessage;
	private String message;
	
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public ArrayList<Book> getListBook() {
		return listBook;
	}
	
	public void setListBook(ArrayList<Book> listBook) {
		this.listBook = listBook;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public void printListBook(){
		if (listBook != null){
			for (int i = 0; i < listBook.size(); i++){
				System.out.println(listBook.get(i));
			}
		}
	}
	
}
