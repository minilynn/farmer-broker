/**
 * 
 */
package com.gul.farmerbroker.hateoas.base;

import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author Lynn
 *
 */
public class PersonResource extends ResourceSupport {
	private String firstName;
	private String lastName;
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
