package adminmangementsystem.com.view;

import java.util.Scanner;
import adminmangementsystem.com.util.Validator;
import adminmangementsystem.com.entity.Cardiologist;

public class CardiologistView {

    public static Cardiologist getCardiologistInput(Scanner sc) {
        System.out.println("\t\t------Add Cardiologist------");

        String id = Validator.getValidPatient(sc, "Enter Cardiologist ID: ");
        String name = Validator.getOnlyLetter(sc, "Enter name: ");
        String dob = Validator.getValidDateFomart(sc, "Enter Date of Birth (dd/mm/yyyy): ");
        String address = Validator.getNonEmpty(sc, "Enter address: ");
        String email = Validator.getValidEmail(sc, "Enter email: ");
        String position = Validator.getOnlyLetter(sc, "Enter position (e.g., Interventional): ");
        double salary = Validator.getPositiveDouble(sc, "Enter salary: ");
        String doe = Validator.getValidDateFomart(sc, "Enter date of entry (dd/mm/yyyy): ");
        String fellowship = Validator.getNonEmpty(sc, "Enter fellowship: ");

        return new Cardiologist(id, name, dob, address, email, position, salary, doe, fellowship);
    }
}