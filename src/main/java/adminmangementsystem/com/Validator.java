package adminmangementsystem.com;

import java.util.Scanner;

public class Validator {

    public static String getNonEmpty(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine();
            if (input != null && !input.trim().isEmpty())
                return input;
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
                if (num > 0)
                    return num;
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

    public static String getOnlyLetter(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim();
            if (input.matches("^[a-zA-Z ]+$")) {
                return input;
            } else
                System.out.println("Invalid only letter are accepted");
        }
    }

    public static String getValidDateFomart(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim();
            if (input.matches("\\d{2}/\\d{2}/\\d{4}")) {
                return input;
            } else
                System.out.println("Invalid DOB! Format must be dd/mm/yyyy.");
        }
    }

    public static String getValidAddress(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim();

            if (!input.isEmpty()
                    && input.length() <= 100
                    && input.matches("[a-zA-Z0-9 ,./-]+")) {
                return input;
            } else {
                System.out.println("Invalid address! Format must be letters, numbers, spaces, and , . / - only.");
            }
        }
    }

    public static String getValidDoctor(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine();

            if (input == null || input.trim().isEmpty()) {
                System.out.println("Doctor ID cannot be empty.");
            } else if (input.startsWith("-")) {
                System.out.println("Invalid input. Doctor ID cannot start with a negative sign.");
            } else if (!input.matches("^[a-zA-Z0-9 ]+$")) {
                System.out.println("Invalid input. Doctor ID must contain only letters, numbers, and spaces.");
            } else {
                return input;
            }
        }
    }

    public static String getDocPosition(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Doctor position cannot be empty.");
            } else if (!input.matches("[a-zA-Z ]{1,50}")) {
                System.out.println("Invalid position. Use letters and spaces only (max 50 characters).");
            } else {
                return input;
            }
        }
    }

    public static double getValidSalary(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim();

            try {
                double salary = Double.parseDouble(input);

                if (salary <= 0) {
                    System.out.println("Invalid salary! Salary must be greater than 0.");
                } else {
                    return salary;
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid salary! Please enter a numeric value.");
            }
        }
    }

}