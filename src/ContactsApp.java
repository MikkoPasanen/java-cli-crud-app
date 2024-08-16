import java.util.Scanner;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
*The class ContactsApp runs the whole application by calling the wanted methods
*
*This is a contact management application that has the
*basic create, read, update and delete selections with validation.
*It also saves the info into a .csv file.
*
*@author Mikko Pasanen
*/

//Screencast link is in the README file
public class ContactsApp extends Management {

    /**
    *Runs the applications menu where the user selects what to do
    */
    public static void displayMenu() {
        System.out.println("""
        Please choose what you want to do
        ---------------------------------
        1. Add a contact
        2. Delete a contact
        3. Update a contact
        4. View all contacts
        5. Search a contact
        6. Quit
        """);
    }

    public static void main(String[] args) throws IOException {

        
        /*Read the .csv file when starting the application
        *and store the info into object and then into an arraylist.
        *Also checks if the contact has email and address or not
        */
        String file = "database.csv";
        String line;
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                while((line = br.readLine()) != null){
                    String[] split = line.split(",");
                    ContactsInfo contact = new ContactsInfo();
                        contact.setPersonalID(split[0]);
                        contact.setFirstName(split[1]);
                        contact.setLastName(split[2]);
                        contact.setPhoneNumber(split[3]);
                            if(split.length > 4) contact.setAddress(split[4]);
                            if(split.length > 5) contact.setEmail(split[5]); 
        
                    myObjList.add(contact);
                }
            } catch (Exception e){
                System.out.println(e);
            }

        
        /*This loop runs the menu and then calls the correct method
        based on what the user wants to do
        */
        while(true) {
            displayMenu();
                System.out.print("Input: ");
                    int input = sc.nextInt();
            sc.nextLine();

        if(input == 6) {
            System.out.println("Goodbye!");
            break;
        }
        switch(input) {
            case 1:
                addContact();
                break;

            case 2:
                deleteContact();
                break;
            
            case 3:
                updateContact();
                break;
            
            case 4:
                readContact();
                break;

            case 5:
                searchContact();
                break;
            
            default:
                System.out.println("Invalid input!");
                break;
        }
        }
            
            /*Overwrite the .csv file with the current data from the arraylist 
            when closing the application
            */
            try(FileWriter writer = new FileWriter("database.csv", false)) {
                    PrintWriter print = new PrintWriter(writer);
                        for(int i = 0; i < myObjList.size(); i++) {
                            ContactsInfo myInfo = myObjList.get(i);
                                print.println((myInfo.getPersonalID())+","
                                                +(myInfo.getFirstName())+","
                                                +(myInfo.getLastName())+","
                                                +(myInfo.getPhoneNumber())+","
                                                +(myInfo.getAddress())+","
                                                +(myInfo.getEmail()));
                        }
                }
                catch(IOException io) {
                    System.out.println(io);
                }
    }
}
    