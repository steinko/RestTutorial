package org.steinko.rest;

public class Person {
	private String id;
	
	private String firstName;
	private String familyName;
	
	public Person() { }
	
	public Person(String id, String firstName, String familyName) {
		this.id = id;
		this.firstName = firstName;
		this.familyName = familyName;
	}
	
	public String getId() {
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
	
	@Override
	public String toString() { 
		return "Id: " + id.toString() + "firstName: " + firstName + "familyName: " + familyName;
	}

}
