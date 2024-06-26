package ua.pohribnyi.weblibrary.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {

	@NotEmpty(message = "Title should be not empty")
	@Size(min = 2, max = 30, message = "Title should be more than 2 but not more than 30 characters")
	private String title;
	@NotEmpty(message = "Author should be not empty")
	private String author;
	@Min(value = 1850, message = "Year of release should be greater then 1850")
	private int yearOfRelease;

	private int id;
	private int reader_id;

	public Book() {

	}

	public Book(String title, String author, int yearOfRelease, int id, int reader_id) {
		this.title = title;
		this.author = author;
		this.yearOfRelease = yearOfRelease;
		this.id = id;
		this.reader_id = reader_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYearOfRelease() {
		return yearOfRelease;
	}

	public void setYearOfRelease(int yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReader_id() {
		return reader_id;
	}

	public void setReader_id(int reader_id) {
		this.reader_id = reader_id;
	}

}
