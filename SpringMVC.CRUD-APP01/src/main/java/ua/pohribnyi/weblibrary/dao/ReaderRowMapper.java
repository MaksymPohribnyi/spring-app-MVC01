package ua.pohribnyi.weblibrary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ua.pohribnyi.weblibrary.model.Reader;

public class ReaderRowMapper implements RowMapper<Reader> {

	@Override
	public Reader mapRow(ResultSet rs, int rowNum) throws SQLException {
		Reader reader = new Reader();
		reader.setId(rs.getInt("reader_id"));
		reader.setName(rs.getString("name"));
		reader.setBirth_date(rs.getString("birth_date"));
		return reader;
	}

}
