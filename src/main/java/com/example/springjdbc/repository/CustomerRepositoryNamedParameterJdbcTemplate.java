package com.example.springjdbc.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.springjdbc.model.Customer;


@Repository
public class CustomerRepositoryNamedParameterJdbcTemplate {

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void save() {
		String query = "insert into customer values(:firstName, :lastName)";

		Map<String, Object> map = new HashMap<>();

		map.put("firstName", "NamedParameter");
		map.put("lastName", "Jdbc");

		namedParameterJdbcTemplate.execute(query, map, new PreparedStatementCallback() {

			@Override
			public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

				return ps.executeUpdate();
			}
		});
	}

}
