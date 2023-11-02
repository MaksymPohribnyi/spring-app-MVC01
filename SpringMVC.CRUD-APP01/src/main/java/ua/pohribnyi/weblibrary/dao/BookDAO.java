package ua.pohribnyi.weblibrary.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.validation.Valid;
import ua.pohribnyi.weblibrary.model.Book;
import ua.pohribnyi.weblibrary.model.Reader;

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

	public void save(Book book) {
		jdbcTemplate.update("INSERT INTO book (title, author, year_of_release) VALUES (?, ?, ?)", book.getTitle(),
				book.getAuthor(), book.getYearOfRelease());
	}

	public void update(int id, Book updatedBook) {
		jdbcTemplate.update("UPDATE book SET title = ?, author = ?, year_of_release = ? WHERE book_id = ?",
				updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYearOfRelease(), id);
	}

	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM book WHERE book_id = ?", id);
	}

	public void releaseBook(int bookId) {
		jdbcTemplate.update("UPDATE book SET reader_id = NULL WHERE book_id = ?", bookId);
	}

	public void assignReader(int readerId, int bookId) {
		jdbcTemplate.update("UPDATE book SET reader_id = ? WHERE book_id = ?", readerId, bookId);
	}

}
