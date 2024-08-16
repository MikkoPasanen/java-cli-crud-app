import java.util.Scanner;
import java.io.*;
import java.util.regex.*;

/**
*This class containts everything regarding the objects.
*
*Here we have the objects attributes, constructors, setters and getters
*and also the validation methods for the contacts infos
*/
public class ContactsInfo {

    //Objects attributes
    private String firstName; 
    private String lastName;
    private String personalID;
    private String phoneNumber;
    private String address;
    private String email;
    
    //Constructor with the info that the object needs
    ContactsInfo(String personalID, 
                String firstName, 
                String lastName, 
                String phoneNumber,
                String address,
                String email) {

        this.personalID = personalID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }
    //Empty constructor
    ContactsInfo(){
    }

    /**
    *Getter for the personal ID
    *
    *@return get the objects attribute info
    */
    public String getPersonalID() {
        return this.personalID;
    }

    /**
    *Getter for the first name
    *
    *@return get the objects attribute info
    */
    public String getFirstName() {
        return this.firstName;
    }

    /**
    *Getter for the last name
    *
    *@return get the objects attribute info
    */
    public String getLastName() {
        return this.lastName;
    }

    /**
    *Getter for the phone number
    *
    *@return get the objects attribute info
    */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
    *Getter for the address
    *
    *@return get the objects attribute info
    */
    public String getAddress() {
        return this.address;
    }

    /**
    *Getter for the email
    *
    *@return get the objects attribute info
    */
    public String getEmail() {
        return this.email;
    }

    //Overriding toString method just in case
    @Override
    public String toString() {
        return ("(" + this.personalID + ", " 
                + this.firstName+ ", " 
                + this.lastName + ", " 
                + this.phoneNumber + ", "
                + this.address + ", "
                + this.email +")");
    }

    /** 
    *Checks the validation of the personal ID
    *
    *Calls the ID validation method to check the entered ID
    *and returns true if the validation goes trough
    *
    *@param personalID the ID we want to validate
    *@return true or false depending if the validation goes trough 
    */
    public boolean setPersonalID(String personalID) {
        if(validID(personalID)) {
            this.personalID = personalID;
                return true;
        } else {
            System.out.println("Invalid ID!");
                return false;
        }
    }

    /** 
    *Checks the validation of the first name
    *
    *Calls the Letters method and checks if the entered name
    *contains only letters, goes trough the first name and
    *adds +1 into a counter and if the counter matches the
    *first names length, we have valid name
    *
    *@param firstName the name we want to validate
    *@return true or false depending if the validation goes trough 
    */
    public boolean setFirstName(String firstName) {
        int numCounter = 0;
        for(int i = 0; i < firstName.length(); i++){
            char chr = firstName.charAt(i);

            if(Letters(chr)){
                numCounter++;
            }
        }
        if(numCounter == firstName.length()){
            this.firstName = firstName;
                return true;
        } else {
            System.out.println("Invalid first name!");
                return false;    
        }

    }

    /** 
    *Checks the validation of the last name
    *
    *Calls the Letters method and checks if the entered name
    *contains only letters, goes trough the last name and
    *adds +1 into a counter and if the counter matches the
    *last names length, we have valid name
    *
    *@param lastName the name we want to validate
    *@return true or false depending if the validation goes trough 
    */
    public boolean setLastName(String lastName) {
        int numCounter = 0;
        for(int i = 0; i < lastName.length(); i++){
            char chr = lastName.charAt(i);

            if(Letters(chr)){
                numCounter++;
            }
        }
        if(numCounter == lastName.length()){
            this.lastName = lastName;
                return true;
        } else {
            System.out.println("Invalid last name!");
                return false;    
        }
    }

    /** 
    *Checks the validation of the phone number
    *
    *Phone number is entered as a string,
    *if the String is able to be parsed into a int value,
    *then the phone number is validated
    *
    *@param phoneNumber the number we want to validate
    *@return true or false depending if the validation goes trough 
    *@throws NumberFormatException if the phonenumber cant be parsed into int
    */
    public boolean setPhoneNumber(String phoneNumber) {
        try {
            int intValue = Integer.parseInt(phoneNumber);
                this.phoneNumber = phoneNumber;
                    return true;
        } 
        catch (NumberFormatException nfe) {
            System.out.println("Invalid phone number!");
                return false;
        }
    }

    /** 
    *No validation needed on the address
    *it just adds the address into the objects attribute
    *if entered
    *
    *@param address the address to be put into attribute 
    *@return true
    */
    public boolean setAddress(String address) {
        this.address = address;
            return true;
    }

    /** 
    *Checks the validation of the email
    *
    *Calls the validEmail method to check if the email is valid,
    *returns true if the validation goes trough, also 
    *works if the entered email is null because
    *email is not mandatory information
    *
    *@param email the name we want to validate
    *@return true or false depending if the validation goes trough 
    */
    public boolean setEmail(String email) {
        if(email == null || validEmail(email)) {
            this.email = email;
                return true;
        }
        return false;
    }
    
    /**
    *Checks if a charecter is between A-Z 
    *
    *@param chr the charecter to be checked
    *@return returns true or false depening on if the chr is between A-Z
    */
    public static boolean Letters(char chr) {
        chr = Character.toUpperCase(chr);
        return (chr >= 'A' && chr <= 'Z');
    }
    
    /**
    *Checks if the entered email contains '@' sign
    *
    *@param email the email that is to be validated
    *@return true or false depending on if the email contains @ 
    */
    public static boolean validEmail(String email) {
        String emailRegex = "^(.+)@(.+)$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    /**
    *Checks if the entered ID is up to the Finnish ID standards
    *
    *@param personalID the ID that is to be valitated
    *@return true or false depending on if the ID is up to Finnish ID standards
    */
    public static boolean validID(String personalID) {
        //I didnt indent this because it would look so stupid
        String idRegex = "([0-3][0-9])([0-1][0-9])([0-9]{2})(\\-|[A]|\\+)([0-9]{3})([0-9]|[A-Z])";

        Pattern pat = Pattern.compile(idRegex);
        return pat.matcher(personalID).matches();
    }
}  