package adminmangementsystem.com;

public class Menu {
    
private static final String LINE = "+--------------------------------------------------------+";
    private static final String SEP  = "----------------------------------------------------------";

    public static void printDashboardMenu() {
        System.out.println(LINE);
        System.out.printf("| %-54s |\n", "          Hospital Admin Management System");
        System.out.println(SEP);
        System.out.printf("| %-54s |\n", " 1.Doctor Management System");
        System.out.println(SEP);
        System.out.printf("| %-54s |\n", " 2.Patient Management System");
        System.out.println(SEP);
        System.out.printf("| %-54s |\n", " 3.Schedule Appointment");
        System.out.println(SEP);
        System.out.printf("| %-54s |\n", " 4.View Appointment");
        System.out.println(SEP);
        System.out.printf("| %-54s |\n", " 5.View Doctor List");
        System.out.println(SEP);
        System.out.printf("| %-54s |\n", " 6.View Patient List");
        System.out.println(SEP);
        System.out.printf("| %-54s |\n", " 7.Exit");
        System.out.println(LINE);
    }

    public static void printAdminLoginMenu(){
        String line = "+-----------------------------------------------------------+";
        String separator = "-------------------------------------------------------------";
        System.out.println(line);
        System.out.printf("| %-57s |\n", "             Hospital Admin Management System");
        System.out.println(separator);
        System.out.printf("| %-57s |\n", " 1.Login as admin");
        System.out.println(separator);
        System.out.printf("| %-57s |\n", " 2.Exit");
        System.out.println(line);
    }

    public static void appointmentMenu(){
        System.out.println("\n\t\t------Appointment Menu------");
        System.out.println("\t1. Schedule Appointment");
        System.out.println("\t2. View Appointments");
        System.out.println("\t3. Exit");
        
    }

    public static void patientMenu(){
        System.out.println("\n\t\t------Patient Management System------");
        System.out.println("\t1. Add Patient");
        System.out.println("\t2. Update Patient Info");
        System.out.println("\t3. Delete Patient");
        System.out.println("\t4. Search Patient by Name or ID");
        System.out.println("\t5. Exit Patient Management");
        
    }
}


