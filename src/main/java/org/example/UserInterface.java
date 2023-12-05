package org.example;

import org.example.Controller;

import java.sql.SQLOutput;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    Controller controller = new Controller();

    public void startProgram() {
        boolean exitProgram = false;
        while (!exitProgram) {
            System.out.println("\nWelcome to the dolphin swim club!");
            System.out.println("""
                    -------------------------------------------
                    How would you like to access the system :
                    --------------------------------------------
                    1. New member? please proceed to sign up!
                    2. Dolphin clubs chairman
                    3. Coach Whale
                    4. Accountant\s
                    8. Exit program
                    """);

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> controller.createMembers();
                    case 2 -> { // List for chairman
                        System.out.println("\n 1. Show all members");
                        System.out.println(" 2. Sorted by name");
                        System.out.println(" 3. Sort by active or passive members:");
                        System.out.println(" 4. Sort by grade: ");
                        System.out.println(" 5. Sort by competition status:");
                        System.out.println(" 6. Remove a member from the club");
                        controller.sortedOptionsForChairman();
                    }
                    case 3 -> { // List for coach Whale
                        System.out.println("1. Sort by active or passive members:");
                        System.out.println("2. Sort by name: ");
                        System.out.println("3. Sort by grade:");
                        System.out.println("4. Sort by competition status:");
                        System.out.println("5. Search for a swimmer");
                        System.out.println("6. Training times for all swimmers");
                        System.out.println("7. Training time for each swimmer");
                        System.out.println("8. Top 5 best junior swimmer ");
                        System.out.println("9. Top 5 best senior swimmer ");
                        controller.SortedOptionsForCoach();
                    }
                    case 4 -> {// List for accountant
                        System.out.println("1. Show all the membership fees");
                        System.out.println("2. Display individual fees for members");
                        System.out.println("3. View all members in arrears");
                        System.out.println("4. display all information for individual member");
                        System.out.println("5. Update member dues");
                        controller.sortedOptionsForAccountant();
                    }
                    case 5 -> controller.searchSwimmer();

                    // Training result for each swimmer
                    case 6 -> {
                        System.out.println("Enter the name of the swimmer:");
                        String swimmerName = scanner.nextLine();
                        Result bestResult = controller.getBestTrainingResultForSwimmer(swimmerName);
                        if (bestResult != null) {
                            System.out.println("Best Training Result for " + swimmerName + ":");
                            System.out.println("Date: " + bestResult.getDate());
                            System.out.println("Discipline: " + bestResult.getSwimmingDiscipline());
                        } else {
                            System.out.println("No training results available for " + swimmerName + ".");
                        }
                    }
                    case 7 -> {
                        System.out.println("Training times for all swimmers:");
                        controller.getBestTrainingResultForSwimmer("idk");
                    }
                    case 8 -> {
                        System.out.println("The program is closing down");
                        exitProgram = true;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Type in a valid value.");
                scanner.nextLine();
            } catch (DateTimeParseException e) {
                System.out.println("Error: wrong date format. Use only YYYY.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("An error has occurred: " + e.getMessage());
                scanner.nextLine();
            }

        }
    }

}