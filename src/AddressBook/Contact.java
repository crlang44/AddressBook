package AddressBook;

public class Contact {
	Contact prev;
	Contact next;

	private String lastName;
	private String firstName;
	private String phoneNumber;
	private String email;

	public String getLastName() {
		return this.lastName;
	}

	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * 
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @param lastName
	 * @param firstName
	 * @param phoneNumber
	 * @param email
	 */
	public Contact(String lastName, String firstName, String phoneNumber, String email) {
		this.prev = null;
		this.next = null;
		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

}