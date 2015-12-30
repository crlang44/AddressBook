package AddressBook;

public class Main {

	/**
	 * 
	 * @param String
	 */
	public static void main(String[] args) {
		AddressBook list = new AddressBook();
		Contact node = new Contact("lang", "chris", "8053452940",
				"crlang@bcp.org");
		Contact a = new Contact("aang", "ahris", "8053452940", "crlang@bcp.org");
		Contact b = new Contact("bang", "bhris", "8053452940", "crlang@bcp.org");
		Contact c = new Contact("cang", "chris", "8053452940", "crlang@bcp.org");
		list.insert(a);
		list.insert(b);
		list.insert(c);
		System.out.println((list.goContact("bang") ? "Contact found"
				: "Contact not found"));
		System.out.println(list.goFirst().getEmail());
	}

}