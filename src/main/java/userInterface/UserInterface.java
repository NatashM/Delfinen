package userInterface;

import entity.Result;
import controller.Controller;

import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    Controller controller = new Controller();

    public void startProgram() {
        boolean exitProgram = false;
        while (!exitProgram) {
            System.out.println("\nDOLPHIN CLUB!");
            System.out.println("""
                    -------------------------------------------
                    HOW WOULD YOU LIKE TO ACCESS THE SYSTEM :
                    --------------------------------------------
                    1. New member? please proceed to sign up!
                    2. Dolphin clubs chairman
                    3. Coach Whale
                    4. Accountant\s
                    10. Exit program
                    """);
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> controller.createMembers();
                    case 2 -> {
                        System.out.println("""
                                -------------------------------------------
                                LIST FOR CHAIRMAN OF THE CLUB
                                --------------------------------------------
                                \n1. Show all member
                                2. Sorted list of the members names:\s
                                3. Sorted list of active or passive members:
                                4. Sorted list divided into age group:\s
                                5. Sorted list of competition status:\s
                                6. Remove a member from the system:\s
                                """);

                        controller.sortedOptionsForChairman();
                    }
                    case 3 -> {
                        System.out.println("""
                                -------------------------------------------
                                LIST FOR COACH WHALE
                                --------------------------------------------
                                \n1. Sorted list of active or passive members
                                2. Sorted list of names:\s
                                3. Sorted list divided by age group:
                                4. Sorted list of competition status:\s
                                5. Search for a swimmer:\s
                                6. Top 5 best junior foreach swim discipline:s
                                7. Top 5 best senior foreach swim discipline:s
                            
                          
                                """);
                        controller.SortedOptionsForCoach();
                    }
                    case 4 -> {
                        System.out.println("""
                                -------------------------------------------
                                LIST FOR ACCOUNTANT
                                --------------------------------------------
                                \n1. Show all membership fees:\s
                                2. Display individual fees for members:\s
                                3. View all members that is in arrears:\s
                                4. Display all information for individual a member:\s
                                5. Update member dues:\s
                                   """);
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

                    case 10 -> {
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