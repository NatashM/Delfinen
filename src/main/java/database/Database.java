package database;

import comparators.*;
import entity.CompetitorMember;
import entity.Member;
import entity.Result;
import filehandler.FileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class Database {
    private ArrayList<Member> members;
    private final ArrayList<Member> senior = new ArrayList<>();
    private final ArrayList<Member> junior = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    private File CSVFile = new File("swimData2.csv");
    private FileHandler filehandler = new FileHandler();

    public Database() {
        try {
            this.members = filehandler.loadAllData();
            splitMembers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void splitMembers() {
        for (Member member : members) {
            if (member.getAge() < 18) {
                junior.add(member);
            } else {
                senior.add(member);
            }
        }
    }



    public void addMember(String name, int age, String birthday, String address, boolean isActive, String grade, String swimType, String trainingTime) {
        Member newMember = new Member(name, age, birthday, address, isActive, grade, swimType, trainingTime);
        try {
            members.add(newMember);
            filehandler.saveMembers(members, CSVFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean removeMembers(String name) {
        for (Member swimMembers : members) {
            if (swimMembers.getName().equalsIgnoreCase(name)) {
                members.remove(swimMembers);
                System.out.println("The swim member with the name " + name + " got removed from the swim club");
                System.out.println("Here is the list of the members" + members + "\n");
                return true;
            }
        }
        System.out.println("The members with the name " + name + " is not in the club");
        return false;

    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void CreateMembers() {
        boolean correctAnswer = false;

        do {
            try {

                System.out.println("\nEnter full name");
                String Name = scanner.next();

                System.out.println("\nEnter age");
                int Age = scanner.nextInt();

                System.out.println("\nEnter birthday ");
                String birthday = scanner.next();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
                LocalDate dateNow = LocalDate.now();

                LocalDate date = LocalDate.parse(birthday, formatter);
                int age = dateNow.getYear() - date.getYear();

                String grade = "";

                if (age <= 18) {
                    System.out.println("You will be joining the junior team");
                    grade = "junior";

                } else {
                    System.out.println("You will be joining the senior team");
                    grade = "senior";
                }

                System.out.println("\nEnter address: ");
                String address = scanner.next();

                boolean isActive;
                do {
                    System.out.print("Would you like an active membership Yes or No: ");
                    String userInput = scanner.next().toLowerCase();

                    if (userInput.startsWith("")) {
                        isActive = true;
                        break;
                    } else if (userInput.startsWith("No")) {
                        isActive = false;
                        break;
                    }

                } while (true);



                if (isActive) {
                    System.out.println("Enter desired swim discipline: Butterfly,Crawl,Backstroke or Breaststroke");
                    String swimType = scanner.next();

                    System.out.println("Enter training time");
                    String trainingTime = scanner.next();

                    Member member = new Member(Name, Age, birthday, address, isActive, grade, swimType, trainingTime);

                    addMember(Name, Age, birthday, address, isActive, grade, swimType, trainingTime);

                    System.out.println(member);

                } else {

                    String swimType = "non active member";
                    String trainingTime = "00:00";

                    Member member = new Member(Name, Age, birthday, address, isActive, grade, swimType, trainingTime);

                    addMember(Name, Age, birthday, address, isActive, grade, swimType, trainingTime);

                    System.out.println(member);

                }
                correctAnswer = true;

            } catch (InputMismatchException e) {
                System.out.println("Error: Type in your age using numeric characters");
                scanner.nextLine();
            } catch (DateTimeParseException e) {
                System.out.println("Error: wrong date format. Use only ddMMyy.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("An error has occurred: " + e.getMessage());
                scanner.nextLine();
            }
        } while (!correctAnswer);
    }

    public void NameComparator() {
        NameComparator comparison = new NameComparator();
        members.sort(comparison);
        for (Member member : members) {
            System.out.println(member.getName());
        }
    }

    public void ShowAllMembers() {
        if (members != null) {
            NameComparator comparison = new NameComparator();
            members.sort(comparison);
            System.out.println("A list of all members:");
            for (Member member : members) {
                System.out.println(member);
            }
        } else {
            System.out.println("Try again");
        }

    }

    public void trainingTimeForEachSwimmer() {

        System.out.println("Enter the name of the swimmer,to find their training time:");
        String userInput = scanner.nextLine();

        ArrayList<Member> searchResult = getMembers();

        if (searchResult.isEmpty()) {
            System.out.println("No training results available for this person.");
        } else if (searchResult.size() > 1) {
            int counter = 0;

            for (Member member : members) {
                if (member.getName().startsWith(userInput)) {
                    System.out.println("Training time: " + " " + member.getTrainingTime());


                }
            }
        }
    }

    public void ActiveComparator() {
        activeComparator comparator = new activeComparator();
        members.sort(comparator);

        for (Member member : members) {
            if (member.getIsActive())
                return;
            System.out.println(member.getName() + " = " + member.getIsActive());

        }
    }

    public void GradeComparator() {
        GradeComparator gradeComparator = new GradeComparator();
        members.sort(gradeComparator);

        System.out.println("Junior Members:");
        for (Member juniorMember : junior) {
            System.out.println(juniorMember.getName());
        }
        System.out.println("\nSenior Members:");
        for (Member seniorMember : senior) {
            System.out.println(seniorMember.getName());
        }

    }


    public void SwimTypeComparator() {
        SwimTypeComparator swimTypeComparator = new SwimTypeComparator();
        members.sort(swimTypeComparator);

        for (Member member : members) {
            System.out.println(member.getName() + " = " + member.getSwimType());
        }
    }

    public void trainingTimeComparator() {
        TrainingTimeComparator trainingTimeComparator = new TrainingTimeComparator();
        members.sort(trainingTimeComparator);

        for (Member member : members) {
            System.out.println(member.getName() + " = " + member.getTrainingTime());
        }
    }

    public void SearchSwimmer() {
        System.out.println("Write the name of the Swimmer");
        String userInput = scanner.nextLine();
        ArrayList<Member> searchResult = getMembers();
        if (searchResult.isEmpty()) {
            System.out.println("There is no swimmer with that name in the club");
        } else if (searchResult.size() > 1) {

            int counter = 0;
            for (Member member : searchResult) {
                if (member.getName().startsWith(userInput)) {
                    System.out.println("Member ID:" + " " + counter++ + "\n" +
                            "Name:" + " " + member.getName() + "\n" +
                            "Name:" + " " + member.getAge() + "\n" +
                            "Birthday:" + " " + member.getBirthday() + "\n" +
                            "\nThe swimmer lives in " +
                            member.getAddress() + "\nThe swimmer is either active or non-active based on (true/false):" + member.getIsActive()
                            + "\nThe selected swimmer is a " +
                            member.getSwimType() + "\n "

                    );
                }

            }
        }

    }

    public CompetitorMember findSwimmerByName(String swimmerName) {
        for (Member member : members) {
            if (member instanceof CompetitorMember && member.getName().equalsIgnoreCase(swimmerName)) {
                return (CompetitorMember) member;
            }
        }
        return null;
    }

    public Result getBestTrainingResultForSwimmer(String swimmerName) {
        CompetitorMember swimmer = findSwimmerByName(swimmerName);
        if (swimmer != null) {
            return swimmer.getBestTrainingResult();
        }
        return null;
    }


    public void trainingTimeForTopFiveSeniorAndJunior(String swimType) {
        ArrayList<Member> swimTypeJunior = new ArrayList<>();
        ArrayList<Member> swimTypeSenior = new ArrayList<>();

        for (Member member : members) {
            if (member.getSwimType().equalsIgnoreCase(swimType) && member.getAge() < 18) {
                swimTypeJunior.add(member);
            } else if (member.getSwimType().equals(swimType) && member.getAge() > 18) {
                swimTypeSenior.add(member);

            }
        }
        swimTypeSenior.sort(new TrainingTimeComparator());
        swimTypeJunior.sort(new TrainingTimeComparator());

        for (int i = 0; i < Math.min(5, swimTypeSenior.size()); i++) {
            Member member1 = swimTypeSenior.get(i);
            System.out.println("Seniors");
            System.out.println("Swimmer: " + member1.getName());
            System.out.println("Swim type: " + member1.getSwimType());
            String trainingTime = member1.getTrainingTime();
            if (trainingTime != null && !trainingTime.isEmpty()) {
                System.out.println("Best Training Time: " + trainingTime + " seconds");
            } else {
                System.out.println("No training results available.");
            }
            System.out.println();
        }

        for (int i = 0; i < Math.min(5, swimTypeJunior.size()); i++) {
            Member member1 = swimTypeJunior.get(i);
            System.out.println("Juniors");
            System.out.println("Swimmer: " + member1.getName());
            System.out.println("Swim type: " + member1.getSwimType());
            String trainingTime = member1.getTrainingTime();
            if (trainingTime != null && !trainingTime.isEmpty()) {
                System.out.println("Best Training Time: " + trainingTime + " seconds");
            } else {
                System.out.println("No training results available.");
            }
            System.out.println();
        }
    }


    public void displayAllTrainingTimes() {
        for (Member member : members) {
            if (member instanceof CompetitorMember competitorMember) {
                System.out.println("Swimmer: " + competitorMember.getName());
                Result bestResult = competitorMember.getBestTrainingResult();
                if (bestResult != null) {
                    System.out.println("Best Training Result: " +
                            bestResult.getTime + " seconds on " + bestResult.getDate());
                } else {
                    System.out.println("No training results available.");
                }
                System.out.println();
            }
        }
    }

    public void viewAllMembersDuesStatus() {

        System.out.println("Membership Dues Information:");
        for (Member member : members) {
            displayMemberDuesInformation(member);
            System.out.println();
        }
    }

    public double customizeExpectedDues(Member member, double expectedDues) {
        if (!member.getIsActive()) {
            return 500;
        }
        return expectedDues; //Inactive members fee
    }

    public double customizePaidDues(Member member, double paidDues) {
        return paidDues;
    }

    private String calculateDuesStatus(double paidDues, double expectedDues) {
        return paidDues >= expectedDues ? "Paid in Full" : "In Arrears";
    }

    private double getAmountPaid(Member member) {
        return member.getPaidDues();
    }

    public double MembershipFee(Member member) {
        int age = member.getAge();
        boolean isActive = member.getIsActive();
        double base = 0;
        if (isActive) {
            if (age < 18) {
                base = 1000;
                if (member.getGrade().equalsIgnoreCase("Junior")) {
                }
            } else if (age >= 18 && age <= 60) {
                base = 1600;
                if (member.getGrade().equalsIgnoreCase("Senior")) {
                }
            } else if (age > 60) {
                base = (1600 / 4.0) * 3;
            }
        } else {
            base = 500;
        }

        return base;
    }

    public void MemberFee() {
        double totalFees = 0;

        GradeComparator gradeComparator = new GradeComparator();
        members.sort(gradeComparator);

        for (Member member : members) {
            double fee = MembershipFee(member);
            totalFees += fee;
            String membershipStatus = member.getIsActive() ? "active" : "inactive";

            System.out.println("Name: " + member.getName() + ", Grade: " + member.getGrade() + ", Age: " + member.getAge() +
                    ", Membership Status: " + membershipStatus + ", Membership Fee: " + fee + " kr.");
        }

        System.out.println("\nExpected Yearly Turnover: " + totalFees + " kr.");

    }

    public void displayMemberDuesInformation(Member member) {
        double expectedDues = customizeExpectedDues(member, MembershipFee(member));
        double paidDues = customizePaidDues(member, getAmountPaid(member));

        System.out.println("Name: " + member.getName() +
                ", Age: " + member.getAge() +
                ", Member states: " + (member.getIsActive() ? "Active" : "Inactive") +
                ", Grade: " + member.getGrade() +
                ", Expected Dues: " + expectedDues + " kr." +
                ", Paid Dues: " + paidDues + " kr." +
                ", Dues Status: " + calculateDuesStatus(paidDues, expectedDues));
    }


    public void updatePaidDuesForMember() {
        System.out.println("Enter the name of the member:");
        String memberName = scanner.nextLine();

        System.out.println("Enter the new paid dues:");
        double newPaidDues = scanner.nextDouble();

        Member foundMember = findMemberByName(memberName);

        if (foundMember != null) {
            foundMember.setPaidDues(newPaidDues);
            try {
                filehandler.saveMembers(members, CSVFile);
                System.out.println("Paid dues updated");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("data could not be saved");
            }

            displayMemberDuesInformation(foundMember);
        } else {
            System.out.println("Member not found.");
        }
    }

    private Member findMemberByName(String memberName) {
        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(memberName)) {
                return member;
            }
        }
        return null;
    }

    public void sortedOptionsForCoach() {
        int categorized = scanner.nextInt();
        scanner.nextLine();
        switch (categorized) {
            case 1 -> ActiveComparator();
            case 2 -> NameComparator();
            case 3 -> GradeComparator();
            case 4 -> SwimTypeComparator();
            case 5 -> SearchSwimmer();
            case 6 -> sortedOptionsForTopFiveJunior();
            case 7 -> sortedOptionsForTopFiveSenior();
            default -> System.out.println("Unable to understand your command.");
        }
    }
    public void sortedOptionsForTopFiveJunior() {
        System.out.println("""
                LIST OF TOP FIVE JUNIOR FOREACH SWIM DISCIPLINE
                                    
                1. Top five for Butterfly:\s
                2. Top five for Crawl:\s
                3. Top five for Backstroke:\s
                4. Top five for Breaststroke:\s
                          
                """);
        int categorized = scanner.nextInt();
        scanner.nextLine();

        switch (categorized) {
            case 1 -> trainingTimeForTopFiveSeniorAndJunior("Butterfly");
            case 2 -> trainingTimeForTopFiveSeniorAndJunior("Crawl");
            case 3 -> trainingTimeForTopFiveSeniorAndJunior("BackStroke");
            case 4 -> trainingTimeForTopFiveSeniorAndJunior("BreastStroke");

            default -> System.out.println("Unable to understand your command");

        }
    }
    public void sortedOptionsForTopFiveSenior() {

        System.out.println("""
                LIST OF TOP FIVE SENIOR FOREACH SWIM DISCIPLINE
                                    
                1. Top five for Butterfly:\s
                2. Top five for Crawl:\s
                3. Top five for Backstroke:\s
                4. Top five for Breaststroke:\s
                              
                """);

        int categorized = scanner.nextInt();
        scanner.nextLine();
        switch (categorized) {
            case 1 -> trainingTimeForTopFiveSeniorAndJunior("Butterfly");
            case 2 -> trainingTimeForTopFiveSeniorAndJunior("Crawl");
            case 3 -> trainingTimeForTopFiveSeniorAndJunior("BackStroke");
            case 4 -> trainingTimeForTopFiveSeniorAndJunior("BreastStroke");

            default -> System.out.println("Unable to understand your command");
        } }

    public void sortedOptionsForChairman() {
        int categorized = scanner.nextInt();
        scanner.nextLine();
        switch (categorized) {
            case 1 -> ShowAllMembers();
            case 2 -> NameComparator();
            case 3 -> ActiveComparator();
            case 4 -> GradeComparator();
            case 5 -> SwimTypeComparator();
            case 6 -> {
                System.out.println("Enter the name of the member to be removed: ");
                String memberNameToRemove = scanner.nextLine();
                removeMembers(memberNameToRemove);
            }
            default -> System.out.println("Unable to understand your command");
        }
    }

    public void sortedOptionsForAccountant() {
        int sortedOptions = scanner.nextInt();
        scanner.nextLine();
        switch (sortedOptions) {
            case 1 -> MemberFee();
            case 2 -> {
                System.out.println("Enter the name of the member:");
                String memberName = scanner.nextLine();
                Member foundMember = findMemberByName(memberName);
                double membershipFee = MembershipFee(foundMember);
                System.out.println("Membership Fee for " + foundMember.getName() + ": " + membershipFee + " kr.");
            }
            case 3 -> viewAllMembersDuesStatus();
            case 4 -> {
                System.out.println("Enter the name of the member:");
                String memberName = scanner.nextLine();
                Member foundMember = findMemberByName(memberName);
                displayMemberDuesInformation(foundMember);
            }
            case 5 -> updatePaidDuesForMember();
            default -> System.out.println("Unable to understand your command");
        }

    }
}
