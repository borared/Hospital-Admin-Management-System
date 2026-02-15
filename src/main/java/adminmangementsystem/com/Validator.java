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

    //Update patient ID
        public static String getValidUpdatePatientId(Scanner sc, String msg) {
            while (true) {
                System.out.print(msg);
                String input = sc.nextLine().trim();
                if (input.isEmpty()) {
                    return null; // Allow empty input to keep existing value
                } else if (input.startsWith("-")) {
                    System.out.println("Invalid input. Patient ID cannot start with a negative sign.");
                } else if (!input.matches("^[a-zA-Z0-9 ]+$")) {
                    System.out.println("Invalid input. Patient ID must contain only letters, numbers, and spaces.");
                } else {
                    return input;
                }
            }
        }

        //Get Email Address format
        public static String getValidEmail(Scanner sc, String msg) {
           while (true) {
              System.out.print(msg);
              String input = sc.nextLine().trim();

              // Simple professional email regex
           if (input.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
              return input;
            }

           System.out.println("Invalid email format. Example: example@gmail.com");
           }
        }

        //Get only number
        public static String getNumberOnly(Scanner sc, String msg) {
            while (true) {
               System.out.print(msg);
               String input = sc.nextLine().trim();

               if (input.matches("\\d+")) {
                  return input;
               }

            System.out.println("Invalid input. Numbers only.");
            }
        }

        public static double getPositiveDouble(Scanner sc, String msg) {
    while (true) {
        System.out.print(msg);
        String input = sc.nextLine().trim();

        try {
            double value = Double.parseDouble(input);
            if (value > 0) {
                return value;
            }
        } catch (Exception ignored) {}

        System.out.println("Invalid input. Enter a positive number.");
    }
}


}