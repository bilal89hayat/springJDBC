package com.example.springjdbc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import com.example.springjdbc.model.Customer;

@Repository
public class CustomerRepositoryJdbcTemplate {

	@Autowired
	private JdbcTemplate jdbctemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static final String SQL = "select * from customer";
	private static final String sql1 = "select * from customer where age>10 and age<17";

	public List<Customer> isData() {
		List<Customer> customers = new ArrayList<>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(SQL);// important

		for (Map<String, Object> row : rows) {
			Customer customer = new Customer();
			customer.setFirstName((String) row.get("firstName"));
			customer.setLastName((String) row.get("lastName"));
			customers.add(customer);
		}
		return customers;
	}

	public int rowCount() {
		return jdbctemplate.queryForObject("select count(*) from customer", Integer.class);
	}

	public int sumOfAges() {

		return jdbctemplate.queryForObject("select sum(age) from customer", Integer.class);
	}

	public List<Customer> getwithrange() {

		List<Customer> customers = new ArrayList<>();
		List<Map<String, Object>> rows = jdbctemplate.queryForList(sql1);

		for (Map<String, Object> row : rows) {
			Customer customer = new Customer();
			customer.setFirstName((String) row.get("firstName"));
			customer.setLastName((String) row.get("lastName"));
			customers.add(customer);
		}

		return customers;
	}

	public Customer getCustomerbyAge(int age) {

		String sql2 = "select * from customer where age=" + age;

		return jdbctemplate.query(sql2, new ResultSetExtractor<Customer>() {

			@Override
			public Customer extractData(ResultSet rs) throws SQLException, DataAccessException {

				if (rs.next()) {
					Customer customer = new Customer();
					customer.setFirstName(rs.getString("firstName"));
					customer.setLastName(rs.getString("lastName"));
					return customer;
				}

				return null;
			}
		});

		// jdbctemplate.queryForObject("select * from customer where age=?" + age, new
		// Object[] { age }, Customer.class);
	}

	public Customer getByEMPID(String str) {

		String sql3 = "select * from customer where empid=" + str;

		return jdbctemplate.query(sql3, new ResultSetExtractor<Customer>() {

			@Override
			public Customer extractData(ResultSet rs) throws SQLException, DataAccessException {

				if (rs.next()) {
					Customer customer = new Customer();
					customer.setFirstName(rs.getString("firstName"));
					customer.setLastName(rs.getString("lastName"));
					customer.setEmpid(rs.getInt(4));
					customer.setAge(rs.getInt(3));
					return customer;
				}

				return null;
			}
		});
	}

	public Customer getCallWithRowMapper(String age, String empid) {

		String sql4 = "select * from customer where age =:age";

		SqlParameterSource paramSource = new MapSqlParameterSource().addValue("age", Integer.valueOf(age));

		return namedParameterJdbcTemplate.queryForObject(sql4, paramSource, new RowMapper<Customer>() {

			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
				if (rs.next()) {
					Customer customer = new Customer();
					customer.setFirstName(rs.getString("firstName"));
					return customer;
				}

				return null;
			}
		});

		/*
		 * return jdbctemplate.query(sql4, new ResultSetExtractor<Customer>() {
		 * 
		 * @Override public Customer extractData(ResultSet rs) throws SQLException,
		 * DataAccessException {
		 * 
		 * if (rs.next()) { Customer customer = new Customer();
		 * customer.setFirstName(rs.getString("firstName")); return customer; }
		 * 
		 * return null; } });
		 */
	}
}
