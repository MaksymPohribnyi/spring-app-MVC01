package ua.pohribnyi.weblibrary.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Reader {

	private int id;

	@NotEmpty(message = "Name should be not empty")
	@Size(min = 2, max = 30, message = "Name should be more than 2 but not more than 30 characters")
	private String name;

	@NotEmpty(message = "Birth date should be not empty")
	@Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "Birth date should be a DATE type and format like YYYY-MM-DD")
	private String birth_date;

	public Reader() {

	}

	public Reader(int id, String name, String birth_date) {
		this.id = id;
		this.name = name;
		this.birth_date = birth_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

}
