package ua.pohribnyi.weblibrary.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ua.pohribnyi.weblibrary.model.Reader;

@Component
public class ReaderDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ReaderDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Reader> index() {
		return jdbcTemplate.query("SELECT * FROM Reader", new ReaderRowMapper());
	}

	public Reader show(int id) {
		return jdbcTemplate.query("SELECT * FROM Reader WHERE reader_id = ?", new ReaderRowMapper(), id).stream()
				.findAny().orElse(null);
	}

	public void update(int id, Reader reader) {
		jdbcTemplate.update("UPDATE Reader SET name = ?, birth_date = CAST(? AS DATE) WHERE reader_id = ?",
				reader.getName(), reader.getBirth_date(), id);
	}

	public Optional<Reader> show(String name) {
		return jdbcTemplate.query("SELECT * FROM Reader WHERE name = ?", new ReaderRowMapper(), name).stream()
				.findAny();
	}

	public void save(Reader reader) {
		jdbcTemplate.update("INSERT INTO Reader (name, birth_date) VALUES(?, CAST(? AS DATE))", reader.getName(),
				reader.getBirth_date());
	}

	public void delete(int id) {
		jdbcTemplate.update("DELETE FROM Reader WHERE reader_id = ?", id);
	}

}
