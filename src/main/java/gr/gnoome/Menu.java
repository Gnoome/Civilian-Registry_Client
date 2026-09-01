package gr.gnoome;


import java.util.Scanner;

public class Menu {

    private static Scanner scan =new Scanner(System.in);
    private static String Input;

      private static void SearchCivilian() {
            
}

    private static void AddCivilian() {
        Person person =new Person();

        System.out.println("\nYou have selected to add a new Civilian");
        System.out.println("Please provide the following...");

        System.out.print("Civilian ID Number (8 characters): ");
        Input=scan.next();
       // System.out.println(Input);
        if (Input.length()!=8 || Input.isEmpty()){
            System.out.println("Invalid Id Number format");
            return;
        }
        person.Id = Input;
        

        System.out.print("Name: ");
        Input= scan.next();
        if( Input.isEmpty()){
            System.out.println("Invalid format");
            return;
        }
        person.Name = Input;
        
        System.out.print("Surname: ");
         Input= scan.next();
        if( Input.isEmpty()){
            System.out.println("Invalid format");
            return;
        }
        person.Surname = Input;

        System.out.print("Gender(M or F): ");
         Input= scan.next();
        if(!Input.equalsIgnoreCase("m") && !Input.equalsIgnoreCase("g")){
            System.out.println("Invalid format");
            return;
        }
        person.Gender = Input;

         System.out.print("Birthdate(dd-mm-yyyy): ");
         Input= scan.next();
        if(!Input.matches("\\d{2}-\\d{2}-\\d{4}")){
            System.out.println("Invalid format");
            return;
        }
        person.Birthdate = Input;

         System.out.print("Address(optional): ");
         Input= scan.next();
        if(Input.isEmpty()){
            Input=null;
        }
        person.Address = Input;

         System.out.print("Tax_Nummber(optional 9 digits): ");
         Input= scan.next();
        if(Input.isEmpty()|| !Input.matches("\\d{9}")){
            Input=null;
        }
        person.Tax = Input;
        System.out.println();

        HTTP_Handler.SendCivilian(person);    
       
    }

    private static void RemoveCivilian(){
        
        
    }

    private static void UpdateCivilian(){
        System.out.println("You have selected to update a civilian");
       
    }

    private static void Start() {

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
                AddCivilian();
                break;
            case 2:
                
                break;
            case 3:
                UpdateCivilian();
                break;
            case 4:
                RemoveCivilian();
                break;
            case 5:
                SearchCivilian();
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
            Start();
        }
        

    }
}
