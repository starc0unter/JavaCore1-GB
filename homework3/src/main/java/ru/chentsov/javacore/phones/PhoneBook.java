package ru.chentsov.javacore.phones;

import java.util.*;

/**
 * @author Chentsov Evgenii
 * Current class contains persons last name and their phone number.
 * All the entries are stored in a HashMap, where the key is the last name and
 * the value is an ArrayList of phones. Such data structure was cretated
 * to deal with namesakes.
 */
public class PhoneBook {

    private Map<String, List<String>> phoneBook = new HashMap<>();

    public List<String> get(String lastName) {
        if (phoneBook.keySet().contains(lastName)) return phoneBook.get(lastName);
        else return null;
    }

    public void add(String lastName, String phoneNumber) {
        if (!phoneBook.keySet().contains(lastName)) {
            phoneBook.put(lastName, new ArrayList<>());
        }
        phoneBook.get(lastName).add(phoneNumber);
    }

    public Set<String> getAllLastNames() {
        return phoneBook.keySet();
    }

    public void printPhoneByPerson(String lastName) {
        List<String> phoneNumbers = this.get(lastName);
        if (phoneNumbers != null) {
            System.out.print("Phone numbers for " + lastName + ": ");
            System.out.println(phoneNumbers);
        } else {
            System.out.println("There is no such person as " + lastName + " in the phoneBook");
        }
    }

}
