import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ContactBook {
    private ArrayList<Contact> contacts;

    public ContactBook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(String name, String phone) {
        Contact newContact = new Contact(name, phone);
        contacts.add(newContact);
        System.out.println("Contact added successfully!");
    }

    public void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }
        System.out.println("List of Contacts:");
        sortContacts(); // Sort contacts before displaying
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public void searchContact(String searchTerm) {
        boolean found = false;
        System.out.println("Search Results:");
        for (Contact contact : contacts) {
            if (contact.contains(searchTerm)) {
                System.out.println(contact);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No contacts found with the search term.");
        }
    }

    private void sortContacts() {
        contacts.sort(Contact::compareTo);
    }

    static class Contact implements Comparable<Contact> {
        private String name;
        private String phone;

        public Contact(String name, String phone) {
            this.name = name;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Phone: " + phone;
        }

        @Override
        public int compareTo(Contact otherContact) {
            return this.name.compareTo(otherContact.name);
        }

        public boolean contains(String searchTerm) {
            return this.name.toLowerCase().contains(searchTerm.toLowerCase());
        }
    }

    public void exportContactsToCsv(String csvFileName) {
        try (FileWriter writer = new FileWriter(csvFileName)) {
            writer.write("Name,Phone\n");
            
            for (Contact contact : contacts) {
                writer.write(contact.toString() + "\n");
            }
            
            System.out.println("Contacts exported to " + csvFileName + " successfully!");
        } catch (IOException e) {
            System.out.println("Error exporting contacts to CSV: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactBook contactBook = new ContactBook();
        while (true) {
            System.out.println("1. Add Contact");
            System.out.println("2. Display Contacts");
            System.out.println("3. Search Contacts");
            System.out.println("4. Export Contacts to CSV");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline left-over
            switch (choice) {
                case 1:
                    System.out.print("Enter Contact Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Contact Phone: ");
                    String phone = scanner.nextLine();
                    contactBook.addContact(name, phone);
                    break;
                case 2:
                    contactBook.displayContacts();
                    break;
                case 3:
                    System.out.print("Enter search term (name): ");
                    String searchTerm = scanner.nextLine();
                    contactBook.searchContact(searchTerm);
                    break;
                case 4:
                    System.out.print("Enter CSV file name to export to (e.g: contacts.csv): ");
                    String csvFileName = scanner.nextLine();
                    contactBook.exportContactsToCsv(csvFileName);
                    break;
                case 5:
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}