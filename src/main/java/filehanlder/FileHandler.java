package filehanlder;
import entities.Member;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class FileHandler {
    private final File CSVFile = new File("swimData2.csv");

    public ArrayList<Member> loadAllData() {
        ArrayList<Member> MemberDataList = new ArrayList<>();
        Scanner filescanner;
        try {
            filescanner = new Scanner(CSVFile, StandardCharsets.ISO_8859_1);
            filescanner.nextLine();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        Member swimMembers = null;
        while (filescanner.hasNext()) {
            String line = filescanner.nextLine();
            String[] attributes = line.split(";");

            if (attributes.length >= 8) {
                Member swimMemberData1 = new Member (
                        attributes[0],
                        Integer.parseInt(attributes[1]),
                        attributes[2],
                        attributes[3],
                        Boolean.parseBoolean(attributes[4]),
                        attributes[5],
                        attributes[6],
                        attributes[7]

                );
                MemberDataList.add(swimMemberData1);

            }
        }
        return MemberDataList;
    }


    public void saveMembers(ArrayList<Member> listOfMembersToSave, File file) throws FileNotFoundException {
        PrintStream saveToFile = new PrintStream(file);

        for (Member members : listOfMembersToSave) {
            saveToFile.println(members.getName() + "," +
                    members.getAge() + "," +
                    members.getBirthday() + "," +
                    members.getAddress() + "," +
                    members.getIsActive() + "," +
                    members.getGrade() + "," +
                    members.getSwimType() + "," +
                    members.getTrainingTime() +"," +
                    members.getPaidDues());

        }
        saveToFile.close();
    }


}

