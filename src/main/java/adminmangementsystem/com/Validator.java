package adminmangementsystem.com;

import java.util.Scanner;

public class Validator {

    public static String getNonEmpty(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine();
            if(input != null && !input.trim().isEmpty()) return input;
            System.out.println("Invalid input. Please enter a non-empty value.");
        }
    }

    public static String getPhoneNumberLength(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine();

            if (input.matches("\\d{8,10}")) {
                return input;
            }

            System.out.println("Invalid input. Phone number must be 8–10 digits.");
            }
    }

    public static int getPositiveInteger(Scanner sc, String msg) {
        while (true) {
            try {
                System.out.print(msg);
                int num = Integer.parseInt(sc.nextLine());
                if (num > 0) return num;
                System.out.println("Invalid input. Please enter a positive number.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    public static String getValidPatient(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine();
            if (input == null || input.trim().isEmpty()) {
                System.out.println("Patient ID cannot be empty.");
            } else if (input.startsWith("-")) {
                System.out.println("Invalid input. Patient ID cannot start with a negative sign.");
            } else if (!input.matches("^[a-zA-Z0-9 ]+$")) {
                System.out.println("Invalid input. Patient ID must contain only letters, numbers, and spaces.");
            } else {
                return input;
            }
        }
    }

    public static String getOnlyLetter(Scanner sc, String msg){
        while(true){
            System.out.print(msg);
            String input = sc.nextLine().trim();
            if(input.matches("^[a-zA-Z ]+$")){
                return input;
            }else System.out.println("Invalid only letter are accepted");
        }
    }

    public static String getValidDateFomart(Scanner sc, String msg){
        while(true){
            System.out.print(msg);
            String input = sc.nextLine().trim();
            if(input.matches("\\d{2}/\\d{2}/\\d{4}")){
                return input;
            }else System.out.println("Invalid DOB! Format must be dd/mm/yyyy.");
        }
    }

}