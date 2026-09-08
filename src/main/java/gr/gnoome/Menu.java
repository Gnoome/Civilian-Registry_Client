package gr.gnoome;


import java.util.Scanner;

public class Menu {

    private static Scanner scan =new Scanner(System.in);
    private static String Input;


    private static void viewAllCivilians() {
        System.out.println("You have selected to view all civilians");
        try {
            HTTP_Handler.viewAllCivilians();
        } catch (Exception e) {
            System.out.println("Error occurred while viewing civilians: " + e.getMessage());
        }
    }

      private static void searchCivilian() {

        Person person = new Person();

        System.out.println("You have selected to search for a civilian");
        System.out.println("Do you wish to search by ID? (Type the id or leave it blank)");
        Input=scan.nextLine();
        if (!Input.isEmpty() && Input.length()!=8){

            System.out.println("Invalid Id Number format");
            return;

        }
        person.id = Input;
        
        System.out.println("Do you wish to search by Name? (Type the name or leave it blank)");
        Input=scan.nextLine();
        if (!Input.isEmpty()){
            person.name = Input;
        }
            
          System.out.println("Do you wish to search by Surname? (Type the surname or leave it blank)");
        Input=scan.nextLine();
        if (!Input.isEmpty()){
            person.surname = Input;
        }

          System.out.println("Do you wish to search by Gender? (Type the gender(M or F) or leave it blank)");
        Input=scan.nextLine();
        if (Input.equalsIgnoreCase("m") || Input.equalsIgnoreCase("f")){
            person.gender = Input;
        }

          System.out.println("Do you wish to search by Birthdate? (Type the birthdate(dd-mm-yyyy) or leave it blank)");
        Input=scan.nextLine();
        if (Input.matches("\\d{2}-\\d{2}-\\d{4}")){
            person.birthdate = Input;
        }

           System.out.println("Do you wish to search by address? (Type the address or leave it blank)");
        Input=scan.nextLine();
        if (!Input.isEmpty()){
            person.address = Input;
        }

           System.out.println("Do you wish to search by Tax Number? (Type the tax number(9 digits) or leave it blank)");
        Input=scan.nextLine();
        if (Input.matches("\\d{9}")){
            person.tax = Input;
        }
         
       try {
            HTTP_Handler.searchCivilian(person);
        } catch (Exception e) {
            System.out.println("Error occurred while searching for civilian: " + e.getMessage());
        }
        
    }


    private static void addCivilian() {
        Person person =new Person();

        System.out.println("\nYou have selected to add a new Civilian");
        System.out.println("Please provide the following...");

        System.out.print("Civilian ID Number (8 characters): ");
        Input=scan.nextLine();
    

        if (Input.length()!=8 || Input.isEmpty()){
            System.out.println("Invalid Id Number format");
            return;
        }
        person.id = Input;
        

        System.out.print("Name: ");
        Input= scan.nextLine();
        if( Input.isEmpty()){
            System.out.println("Invalid format");
            return;
        }
        person.name = Input;
        
        System.out.print("Surname: ");
         Input= scan.nextLine();
        if( Input.isEmpty()){
            System.out.println("Invalid format");
            return;
        }
        person.surname = Input;

        System.out.print("Gender(M or F): ");
         Input= scan.nextLine();
        if(!Input.equalsIgnoreCase("m") && !Input.equalsIgnoreCase("f")){
            System.out.println("Invalid format");
            return;
        }
        person.gender = Input;

         System.out.print("Birthdate(dd-mm-yyyy): ");
         Input= scan.nextLine();
        if(!Input.matches("\\d{2}-\\d{2}-\\d{4}")){
            System.out.println("Invalid format");
            return;
        }
        person.birthdate = Input;

         System.out.print("Address(optional): ");
         Input= scan.nextLine();
        if(Input.isEmpty()){
            Input=null;
        }
        person.address = Input;

         System.out.print("Tax_Nummber(optional 9 digits): ");
         Input= scan.nextLine();
        if(!Input.isEmpty() && !Input.matches("\\d{9}")){
            System.out.println("Invalid format");
            return;
        }
        person.tax = Input;
        System.out.println();

        try {
            HTTP_Handler.sendCivilian(person);
        } catch (Exception e) {
            System.out.println("Error occurred while adding civilian: " + e.getMessage());
        }

    }

    private static void removeCivilian(){
        System.out.println("You have selected to remove a civilian");

        System.out.print("Please provide the Civilian ID Number (8 characters): ");
        Input=scan.nextLine();
        if (Input.length()!=8 || Input.isEmpty()){
            System.out.println("Invalid Id Number format");
            return;
        }

        try {
            HTTP_Handler.deleteCivilian(Input);
        } catch (Exception e) {
            System.out.println("Error occurred while deleting civilian: " + e.getMessage());
        }
        
    }

    private static void updateCivilian(){
        System.out.println("You have selected to update a civilian");

        System.out.print("Please provide the Civilian ID Number (8 characters): ");
        Input=scan.nextLine();
        if (Input.length()!=8 || Input.isEmpty()){
            System.out.println("Invalid Id Number format");
            return;
        }
        System.out.println("Id format is valid");
        System.out.println("Do you wish to update the civilian's address? If yes type the new address, if not leave it blank and press enter");
        String address = scan.nextLine();
        if (address.isEmpty()) {
            address = null;
        }

        System.out.println("Do you wish to update the civilian's tax number? If yes type the new tax number, if not leave it blank and press enter");
        String tax = scan.nextLine();
        if (!tax.isEmpty() && !tax.matches("\\d{9}")) {
            System.out.println("Invalid tax number format");
            return;
        }
        if (tax.isEmpty()) {
            tax = null;
        }

        Person person = new Person();
        person.id = Input;
        person.address = address;
        person.tax = tax;

        try {
            HTTP_Handler.updateCivilian(person);
        } catch (Exception e) {
            System.out.println("Error occurred while updating civilian: " + e.getMessage());
        }

    }

    private static void start() {

        System.out.println("Welcome to the Database");
        System.out.println("Please select one of the following options:");

        System.out.println("1. Add a new civilian");
        System.out.println("2. View all civilians");
        System.out.println("3. Update a civilian");
        System.out.println("4. Delete a civilian");
        System.out.println("5. Search for a civilian");
        System.out.println("6. Exit");
        System.out.print("Enter your choice (1-6): ");

        Scanner scan = new Scanner(System.in);
        switch (scan.nextInt()) {

            case 1:
                addCivilian();
                break;
            case 2:
                viewAllCivilians();
                break;
            case 3:
                updateCivilian();
                break;
            case 4:
                removeCivilian();
                break;
            case 5:
                searchCivilian();
                break;
            default:
                scan.close();
                System.exit(0);
                break;
        }

    }



    public static void main(String[] args) {
        

        boolean flag = true;
        while (flag) {
            start();
        }
        

    }
}
