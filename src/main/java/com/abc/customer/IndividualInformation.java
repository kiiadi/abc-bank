package com.abc.customer;

/**
 * 
 * @author Sanju Thomas
 *
 */
public class IndividualInformation {
	
	private String firstName;
	private String lastName;
	
	public IndividualInformation(final String firstName, final String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
}
