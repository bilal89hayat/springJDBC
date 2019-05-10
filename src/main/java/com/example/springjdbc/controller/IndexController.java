package com.example.springjdbc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springjdbc.model.Customer;
import com.example.springjdbc.repository.CustomerRepositoryJdbcTemplate;

@RestController
public class IndexController {

	/*
	 * @Autowired Xebia xebia;
	 * 
	 * @GetMapping("/home") // using query string, this method is returning json
	 * object. public Customer insertQuery(@RequestParam(value = "name",
	 * defaultValue = "bilal") String param) {
	 * 
	 * return new Customer(1, param); }
	 * 
	 * @GetMapping("/homealone") // using query string, this method is returning
	 * json object. public Xebia insertQueryAlone(@RequestParam(value = "name",
	 * defaultValue = "bilal") String param) {
	 * 
	 * xebia.setUser_id(1); xebia.setUser_name(param);
	 * 
	 * return xebia; }
	 */

	@Autowired
	public CustomerRepositoryJdbcTemplate customerRepository;

	@GetMapping("/getCustInfo")
	public List<Customer> customerInformation() {
		System.out.println("in /getCustInfo");
		List<Customer> customers = customerRepository.isData();
		return customers;
	}

	@GetMapping("/getNoOfRows")
	public int getRowCount() {
		return customerRepository.rowCount();
	}

	@GetMapping("/hello")
	public String getCustomer() {
		System.out.println("in /hello");
		return " ==== hello ==== ";
	}

	@GetMapping("/getSumOfAges")
	public int getSumOfAges() {
		return customerRepository.sumOfAges();
	}

	@GetMapping("/getWithinRange")
	public List<Customer> getCustomersWithinRange() {
		List<Customer> customers = customerRepository.getwithrange();
		return customers;
	}

	@GetMapping("/byAge/{age}") // using pathvariable
	public Customer getByAge(@PathVariable int age) {
		return customerRepository.getCustomerbyAge(age);
	}

	@GetMapping("/byEmpId") // using request parameter
	public Customer getByEmpId(@RequestParam(value = "empId") String str) {
		return customerRepository.getByEMPID(str);
	}

	@GetMapping("/byAge/{age}/empid/{empid}") // using path variable
	public Customer getUsingRowMapper(@PathVariable String age, @PathVariable String empid) {

		return customerRepository.getCallWithRowMapper(age, empid);
	}

	@PostMapping("/")
	public void insertInCustomer() {

	}

}
