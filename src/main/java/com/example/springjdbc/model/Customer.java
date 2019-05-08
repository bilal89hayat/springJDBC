package com.example.springjdbc.model;

public class Customer {

	private String firstName;
	private String lastName;
	private int age;
	private int empid;

	public Customer() {
	}

	public Customer(String firstName, String lastName, int age, int empid) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.empid = empid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	/*
	 * public Customer() { }
	 * 
	 * public Customer(String firstName, String lastName) { this.firstName =
	 * firstName; this.lastName = lastName; }
	 * 
	 * public String getFirstName() { return firstName; }
	 * 
	 * public void setFirstName(String firstName) { this.firstName = firstName; }
	 * 
	 * public String getLastName() { return lastName; }
	 * 
	 * public void setLastName(String lastName) { this.lastName = lastName; }
	 */
}
