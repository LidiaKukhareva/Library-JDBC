package by.tr.library.bean;

public class Book {
	
	private String title;
	private String author;
	private int price;

	public Book(){
		this.title = "";
		this.author = "";
		this.price = 0;
	}

	
	public Book(String title, String author, int price){
		this.title = title;
		this.author = author;
		this.price = price;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String toString(){
		return (title + " " + author + " " + price);
	}

}
