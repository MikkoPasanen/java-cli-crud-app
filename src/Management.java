import java.util.Scanner;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.*;

/**
*This class has the methods that do the actual work when called 
*/

public class Management {

    static Scanner sc = new Scanner(System.in);
    static List<ContactsInfo> myObjList = new ArrayList<>();

    /**
    *Adds object into arraylist and then into the .csv file.
    *Checks the entered infos validation
    */
    public static void addContact() {
        System.out.println("""
            Add a contact
            -------------
            """);

        String firstName = ""; 
        String lastName = "";
        String personalID = "";
        String phoneNumber = "";
        String address = "";
        String email = "";

        ContactsInfo myInfo = new ContactsInfo(firstName,
                                                lastName,
                                                personalID,
                                                phoneNumber,
                                                address,
                                                email);

            while(true){ // Enter personal ID
                boolean found = false;
                System.out.println("Set the persons personal ID (Mandatory):");
                    personalID = sc.nextLine();
                        for(int j = 0; j < myObjList.size(); j++) {
                            ContactsInfo contactID = myObjList.get(j);
                            if(contactID.getPersonalID().equals(personalID)){
                                found = true;
                                    break;
                            }
                        }
                if(found == true) {
                    System.out.println("Already a contact with that ID!");
                }
                if(found == false && myInfo.setPersonalID(personalID)) {
                    break;
                }
            }

            while(true){ // Enter first name
                System.out.println("Set the persons first name (Mandatory):");
                    firstName = sc.nextLine();
                        if(myInfo.setFirstName(firstName)){
                            break;
                        }
            }

            while(true){ // Enter last name
                System.out.println("Set the persons last name (Mandatory):");
                    lastName = sc.nextLine();
                        if(myInfo.setLastName(lastName)){
                            break;
                        }
            }
            
            while(true){ // Enter phone number
                System.out.println("Set the persons phone number (Mandatory):");
                    phoneNumber = sc.nextLine();
                        if(myInfo.setPhoneNumber(phoneNumber)) {
                            if(phoneNumber.startsWith("04") ||
                                phoneNumber.startsWith("050")) {
                                break;
                            }
                            else {
                                System.out.print("The phonenumber");
                                System.out.print(" must start with");
                                System.out.print(" 04 or 050!");
                                System.out.println();
                                
                            }
                       }
            }

            while(true){ // Enter address
                System.out.println("Set the persons address (Optional): ");
                    address = sc.nextLine();
                        if(address.isEmpty()) {
                            myInfo.setAddress(address);
                                break; 
                        } else {
                            myInfo.setAddress(address);
                                break;
                        }
            }

            while(true){ // Enter email
                System.out.println("Set the persons email (Optional): ");
                    email = sc.nextLine();
                        if(email == null || email.isEmpty() ) {
                            myInfo.setEmail(email);
                                break;
                        }else if(myInfo.setEmail(email)) {
                            myInfo.setEmail(email);
                                break;
                        } else {
                            System.out.println("Invalid email address!");
                        }
            }

            try(FileWriter writer = new FileWriter("database.csv", true)) {
                PrintWriter print = new PrintWriter(writer);

                print.println(myInfo.getPersonalID()+","
                            +myInfo.getFirstName()+","
                            +myInfo.getLastName()+","
                            +myInfo.getPhoneNumber()+","
                            +myInfo.getAddress()+","
                            +myInfo.getEmail());
            }
            catch(IOException io) {
            }

            myObjList.add(new ContactsInfo(myInfo.getPersonalID(), 
                                            myInfo.getFirstName(), 
                                            myInfo.getLastName(), 
                                            myInfo.getPhoneNumber(),
                                            myInfo.getAddress(),
                                            myInfo.getEmail()));
            System.out.println();
            System.out.println("Contact added succesfully!");
            System.out.println();
    }
    
    /**
    *Deletes the object from the arraylist if
    *the entered id matches any id in the list 
    */
    public static void deleteContact() {
        System.out.println("""
            Delete a contact
            ----------------
            """);
        System.out.println("Whats the ID of the person you want to delete?: ");
            String delID = sc.next();
            ContactsInfo contactDel;
            boolean found = false;

            for(int i = 0; i < myObjList.size(); i++) {
                ContactsInfo contact = myObjList.get(i);
                    if(contact.getPersonalID().equals(delID)) {
                        contactDel = contact;
                            myObjList.remove(contactDel);
                            System.out.println("Contact deleted succesfully");
                                found = true;
                                    break;
                    }
            }
            if(found == false) {
                System.out.println();
                System.out.println("Can't find contact with that ID!");
            }
        System.out.println();
    }

    /**
    *Gives user possibility to update the selected contact.
    *Also checks the validation of the new info
    */
    public static void updateContact() {
        System.out.println("""
        Update a contact
        ----------------
        """);

        String firstName = ""; 
        String lastName = "";
        String personalID = "";
        String phoneNumber = "";
        String address = "";
        String email = "";
        int input = 0;
        
        System.out.println("Whats the ID of the person you want to update?");
        String updId = sc.nextLine();
        boolean found = false;
        boolean found1 = false;
        
        for(int i = 0; i < myObjList.size(); i++) {
            ContactsInfo contact = myObjList.get(i);
            if(contact.getPersonalID().equals(updId) ) {
                while(input != 7) {
                    System.out.println("""

                    Choose what info you want to change:
                    1. ID
                    2. First name
                    3. Last name
                    4. Phonenumber
                    5. Address
                    6. Email)
                    7. Exit to main menu
                    """);

                    System.out.print("Input: ");
                        input = sc.nextInt();
                            sc.nextLine();

                switch(input) {
                
                case 1:
                while(true){ // Enter new personal ID
                    found1 = false;
                    System.out.println("Set the new personal ID (Mandatory)");
                        personalID = sc.nextLine();
                            for(int j = 0; j < myObjList.size(); j++) {
                                ContactsInfo contact1 = myObjList.get(j);
                                if(contact1.getPersonalID().equals(personalID)){
                                    found1 = true;
                                        break;
                                }
                            }
                    if(found1 == true) {
                        System.out.println("Already a contact with that ID");
                    }
                    if(found1 == false &&
                        contact.setPersonalID(personalID)) {
                        break;
                    }
                                
                }
                break;

                case 2:
                while(true){ // Enter new first name
                    System.out.println("Set the new first name (Mandatory)");
                        firstName = sc.nextLine();
                            if(contact.setFirstName(firstName)){
                                break;
                            }
            
                }
                break;

                case 3:
                while(true){ // Enter new last name
                    System.out.println("Set the new last name (Mandatory)");
                        lastName = sc.nextLine();
                            if(contact.setLastName(lastName)){
                                break;
                            }
                }
                break;

                case 4:
                while(true){ // Enter new phone number
                    System.out.println("Set the new phone number (Mandatory)");
                        phoneNumber = sc.nextLine();
                            if(contact.setPhoneNumber(phoneNumber)) {
                                if(phoneNumber.startsWith("04") ||
                                    phoneNumber.startsWith("050")) {
                                        break;
                                }
                                else {
                                    System.out.print("The phonenumber");
                                    System.out.print(" must start with");
                                    System.out.print(" 04 or 050!");
                                    System.out.println();
                                }
                            }
                }
                break;

                case 5:
                while(true){ // Enter new address
                    System.out.println("Set the new address (Optional)");
                        address = sc.nextLine();
                            if(address.isEmpty() || address == null) {
                                contact.setAddress(null);
                                    break;
                            } else {
                                contact.setAddress(address);
                                    break;
                            }
                }
                break;

                case 6:
                while(true){ // Enter new email
                    System.out.println("Set the new email (Optional)");
                        email = sc.nextLine();
                            if(email.isEmpty() || email == null) {
                                contact.setEmail(null);
                                    break;
                            }else if(contact.setEmail(email)) {
                                contact.setEmail(email);
                                    break;
                            } else {
                                System.out.println("Invalid email address");
                            }
                            
                }
                }
                if(input != 7) {
                System.out.println("Contact updated succesfully!");
                    found = true;
                }
            }
            }
        }
            if(!found) {
                System.out.println();
                System.out.println("Can't find contact with that ID!");
                System.out.println();
            }
    }

    /**
    *Showcases every object and their info that is in the arraylist 
    */
    public static void readContact() {
        System.out.println("""
            View contacts
            ----------------
            """);

        for(int i=0; i<myObjList.size(); i++) {
            System.out.println(myObjList.get(i));
        }
        System.out.println();
    }

    /**
    *Showcases the info of a spesific object if the searched id matches
    *any id in the list 
    */
    public static void searchContact() {
           System.out.println("""
            Search a contact
            ----------------
            """);
        System.out.println("Whats the ID of the person you want to search?: ");
            String searchID = sc.next();
            ContactsInfo contactSrc;
            boolean found = false;
            System.out.println();

            for(int i = 0; i < myObjList.size(); i++) {
                ContactsInfo contact = myObjList.get(i);
                    if(contact.getPersonalID().equals(searchID)) {
                        contactSrc = contact;
                            System.out.println(contactSrc);
                                found = true;
                                    break;
            
                    }
            }
            if(found == false) {
                System.out.println("Can't find contact with that ID"); 
            }
        System.out.println();
    
    }
}

       
        

