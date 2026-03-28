package adminmangementsystem.com;

import java.util.List;
import java.util.Scanner;
import adminmangementsystem.com.model.Doctor;

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

    public static String getValidUpdateDoc(Scanner sc, String msg, String currentValue) {
        while (true) {
            System.out.print(msg + " [" + currentValue + "]: "); // show current value
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                // User pressed Enter, keep the current value
                return currentValue;
            } else if (input.length() < 2) {
                System.out.println("Invalid input! Must be at least 2 characters.");
            } else {
                return input;
            }
        }
    }

    public static String getValidDeleteDoc(Scanner sc, List<Doctor> doctors, String msg) {
        while (true) {
            System.out.print(msg);
            String id = sc.nextLine().trim();

            if (id.isEmpty()) {
                System.out.println("Doctor ID cannot be empty!");
                continue;
            }

            boolean exists = doctors.stream()
                    .anyMatch(d -> d.getId().equalsIgnoreCase(id));
            if (exists) {
                return id; // valid ID to delete
            } else {
                System.out.println("Doctor ID not found! Please enter a valid ID.");
            }
        }
    }

    public static double getValidUpdateSalary(Scanner sc, String msg, double currentSalary) {
        while (true) {
            System.out.print(msg + " [" + currentSalary + "]: ");
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                return currentSalary; // keep current value
            }

            try {
                double salary = Double.parseDouble(input);
                if (salary <= 0) {
                    System.out.println("Invalid salary! Must be greater than 0.");
                } else {
                    return salary;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary! Enter a numeric value.");
            }
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

    public static String getDoctorPositionWithShortcut(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("S") || input.equalsIgnoreCase("Surgeon")) {
                return "Surgeon";
            }

            if (input.equalsIgnoreCase("C") || input.equalsIgnoreCase("Cardiologist")) {
                return "Cardiologist";
            }

            if (input.equalsIgnoreCase("N") || input.equalsIgnoreCase("Nurse")) {
                return "Nurse";
            }

            System.out.println("Invalid position. Use S/C/N or Surgeon/Cardiologist/Nurse.");
        }
    }


}