package org.steinko.rest;

public class Person {
	private Integer id;
	
	private String firstName;
	private String familyName;
	
	public Person() { }
	
	public Person(Integer id, String firstName, String familyName) {
		this.id = id;
		this.firstName = firstName;
		this.familyName = familyName;
	}
	
	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familiyName) {
		this.familyName = familiyName;
	}

}
