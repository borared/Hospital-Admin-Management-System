package adminmangementsystem.com;

import java.util.Scanner;


public class Validator {

    public static String getNonEmpty(Scanner sc, String msg) {
        String input;
        do {
            System.out.print(msg);
            input = sc.nextLine();
        } while (input.isEmpty());
        return input;
    }

    public static double getPositiveDouble(Scanner sc, String msg) {
        while (true) {
            try {
                System.out.print(msg);
                double d = Double.parseDouble(sc.nextLine());
                if (d > 0) return d;
            } catch (Exception ignored) {}
            System.out.println("Invalid input.");
        }
    }
}

