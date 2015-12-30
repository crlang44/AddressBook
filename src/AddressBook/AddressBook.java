package AddressBook;

import java.io.*;

import org.apache.commons.io.*;
import org.apache.commons.lang3.*;

public class AddressBook implements ContactList {
	private Contact top;
	private Contact current;
	private int count;

	public int getCount() {
		return this.count;
	}

	public Contact getCurrent() {
		return this.current;
	}

	public AddressBook() {
		top = null;
		current = null;
		count = 0;
	}

	/**
	 * Deletes the current contact. Current is then set to the next contact in
	 * the list, unless there is no next, in which case the previous contact
	 * becomes the current contact.
	 */
	public void delete() {
		if (top == null) { /* Trying to delete from empty list, do nothing" */
		} else if (current.prev == null) { /*
											 * Delete from beginning of
											 * non-empty list
											 */
			if (current.next == null) { /* Delete ONLY contact from list */
				top = null;
				current = null;
				count--;
			} else { /*
					 * Delete from beginning of non-empty list with more than
					 * one contact
					 */
				current.next.prev = null;
				current = current.next;
				top = current;
				count--;
			}
		} else if (current.next == null) { /* Delete from end of non-empty list */
			current.prev.next = null;
			current = current.prev;
			count--;
		} else { /* Delete from middle of non-empty list */
			current.next.prev = current.prev;
			current.prev.next = current.next;
			current = current.next;
			count--;
		}
	}

	/**
	 * Searches for the first contact whose last name is lastName. Ignore case.
	 * If found, it becomes the current contact. If not found, current is not
	 * changed.
	 * 
	 * @param lastName
	 * @return true if found, false if not.
	 */
	public boolean goContact(String lastName) {
		Contact holder = current;
		boolean foundContact = false;
		goFirst();
		while (current.next != null) {
			goNext();
			if (current.getLastName().equalsIgnoreCase(lastName)) {
				foundContact = true;
				break;
			}
		}
		if (!foundContact)
			current = holder;
		return foundContact;

	}

	/**
	 * Makes the first contact the current contact.
	 * 
	 * @return The current contact (after the change).
	 */
	public Contact goFirst() {
		current = top;
		return current;
	}

	/**
	 * Makes the last contact the current contact.
	 * 
	 * @return The current contact (after the change).
	 */
	public Contact goLast() {
		while (current.next != null)
			current = goNext();
		return current;
	}

	/**
	 * Makes the next contact the current contact. This method "wraps around",
	 * so goLast(), then goNext() would be equivalent to goFirst().
	 * 
	 * @return The current contact (after the change).
	 */
	public Contact goNext() {
		/* End of list, go to first */
		if (current.next == null)
			current = top;
		/* Not end of list, go to next */
		else
			current = current.next;
		return current;
	}

	/**
	 * Makes the previous contact the current contact. This method
	 * "wraps around", so goFirst(), then goPrevious() would be equivalent to
	 * goLast().
	 * 
	 * @return The current contact (after the change).
	 */
	public Contact goPrevious() {
		/* Beginning of list, go to last */
		if (current.prev == null)
			current = goLast();
		/* Not beginning of list, go to prev */
		else
			current = current.prev;
		return current;
	}

	/**
	 * Inserts contact after the current contact. Current is set to the newly
	 * added contact.
	 * 
	 * @param contact
	 */
	public void insert(Contact contact) {
		if (contact != null) {
			if (top == null) {
				top = contact;
				count++;
			} else if (current.next == null) {
				current.next = contact;
				contact.prev = current;
				count++;
			} else {
				contact.next = current.next;
				contact.prev = current;
				current.next.prev = contact;
				current.next = contact;
				count++;
			}
			current = contact;
		}
	}

	/**
	 * Inserts contact at the beginning of the list. Current is set to the newly
	 * added contact (i.e. the first contact).
	 * 
	 * @param contact
	 */
	public void insertBeforeFirst(Contact contact) {
		if (top == null) { /* Inserting into empty list */
			top = contact;
			count++;
		} else { /* Insert at beginning of non-empty list */
			contact.prev = null;
			contact.next = top;
			contact.next.prev = contact;
			top = contact;
			count++;
		}
		current = contact;
	}

	/**
	 * Clears the current contact list and replaces it with contacts in
	 * filename. Contacts are stored in the same order as in the file. After
	 * loading the current contact is set to the first contact.
	 * 
	 * @param filename
	 *            Filename/path to the XML contacts file.
	 */
	public void loadFile(String filename) throws Exception {
		AddressBook ab = new AddressBook();
		String fileString = FileUtils.readFileToString(new File(filename));
		String[] contacts = StringUtils.substringsBetween(fileString,
				"<contacts>", "</contacts>");
		for (String contact : contacts) {
			String lastname = StringUtils.substringBetween(contact, "<last>",
					"</last>");
			String firstname = StringUtils.substringBetween(contact, "<first>",
					"</first>");
			String phone = StringUtils.substringBetween(contact, "<phone>",
					"</phone>");
			String email = StringUtils.substringBetween(contact, "<email>",
					"</email>");
			Contact curCont = new Contact(lastname, firstname, phone, email);
			ab.insert(curCont);
		}
	}

	/**
	 * Saves the contact list in XML format to the specified file.
	 * 
	 * @param filename
	 */
	public void saveFile(String filename) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("<contacts>\n");
		Contact cursor = top;
		while (cursor != null) {
			sb.append("<contact>");
			sb.append("<last>");
			sb.append(cursor.getLastName());
			sb.append("</last>");
			sb.append("<first>");
			sb.append(cursor.getFirstName());
			sb.append("</first>");
			sb.append("<phone>");
			sb.append(cursor.getPhoneNumber());
			sb.append("</phone>");
			sb.append("<email>");
			sb.append(cursor.getEmail());
			sb.append("</email>");
			sb.append("</contact>");
			cursor = cursor.next;
		}
		sb.append("</contacts>");
		String returnString = sb.toString();
		FileUtils.writeStringToFile(new File(filename), returnString);
	}

	/**
	 * Sorts the list of contacts based on their last names. NOTE: The current
	 * contact does not change as a result of the sort, though its previous and
	 * next contacts may.
	 */
	public void sortOnLastName() {
		// TODO - implement AddressBook.sortOnLastName
		throw new UnsupportedOperationException();
	}

}