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
            System.out.println("\n Welcome to the dolphin swim club!");
            System.out.println(" Choose a way to access the system:  ");
            System.out.println("1. New member? then please proceed to sign up!");
            System.out.println("2. Dolphin clubs chairman");
            System.out.println("3. Coach Whale");
            System.out.println("4. Accountant");
            System.out.println("9. Exit program");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        controller.createMembers();
                        break;

                    case 2:
                        System.out.println("\n 1. Show all members");
                        System.out.println(" 2. Sorted by name");
                        System.out.println(" 3. Sort by active or passive members:");
                        System.out.println(" 4. Sort by grade: ");
                        System.out.println(" 5. Sort by competition status:");
                        System.out.println(" 6. Remove a member from the club");

                        controller.sortedOptionsForChairman();
                        break;
                    case 3:
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
                        break;
                    case 4:
                        System.out.println("1.All members expected payment");
                        //lav første og andet comparator
                        System.out.println("2. ... ");
                        controller.sortedOptionsForAccountant();
                        break;
                    case 5:
                        controller.searchSwimmer();
                        break;

                    // Training result for each swimmer
                    case 6:
                        System.out.println("Enter the name of the swimmer:");
                        String swimmerName = scanner.nextLine();
                        Result bestResult = controller.getBestTrainingResultForSwimmer(swimmerName);
                        if (bestResult != null) {
                            System.out.println("Best Training Result for " + swimmerName + ":");
                            System.out.println("Date: " + bestResult.getDate());
                            System.out.println("Discipline: " + bestResult.getSwimmingDiscipline());
                            // Add more details if needed
                        } else {
                            System.out.println("No training results available for " + swimmerName + ".");
                        }
                        break;

                    case 7:
                        System.out.println("Training times for all swimmers:");
                        controller.getBestTrainingResultForSwimmer("idk");
                        break;


                    case 9:
                        System.out.println("The program is closing down");
                        exitProgram = true;


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