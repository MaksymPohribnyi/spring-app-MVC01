package ua.pohribnyi.weblibrary.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ua.pohribnyi.weblibrary.model.Book;

@Component
public class BookDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public BookDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Book> index() {
		return jdbcTemplate.query("SELECT * FROM book", new BookRowMapper());
	}

	public Book show(int id) {
		return jdbcTemplate.query("SELECT * FROM book WHERE book_id = ?", new BookRowMapper(), id).stream().findAny()
				.orElse(null);
	}

	public List<Book> showReaderBooks(int readerId) {
		return jdbcTemplate.query("SELECT * FROM book WHERE reader_id = ?", new BookRowMapper(), readerId);
	}

}
