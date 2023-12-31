package entity;

import java.util.ArrayList;

public class Member {
    private ArrayList<Member> members = new ArrayList<>();
    private String name;
    private int age;
    private String birthDay;
    private String address;
    private boolean isActive;
    private String grade;
    private String swimType;
    private String trainingTime;
    private double paidDues;




    public Member(String name,int age, String birthDay, String address, boolean isActive, String grade, String swimType, String trainingTime) {
        this.name = name;
        this.age = age;
        this.birthDay = birthDay;
        this.address = address;
        this.isActive = isActive;
        this.grade = grade;
        this.swimType = swimType;
        this.trainingTime = trainingTime;


    }

    public String getName() {
        return name;
    }

    public int getAge(){
        return age;
    }

    public double getPaidDues() {
        return paidDues;
    }


    public String getBirthday() {
        return birthDay;
    }


    public String getAddress() {
        return address;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public String getGrade() {
        return grade;
    }

    public String getSwimType() {
        return swimType;
    }

    public String getTrainingTime() {
        return trainingTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPaidDues(double newpaidDues) {
        this.paidDues = newpaidDues;
    }

    public double getRemainingDues() {
        return paidDues;
    }


    public void setBirthDay(String birthday) {
        this.birthDay = birthday;
    }


    public void setAddress(String address) {
        this.address = address;
    }



    @Override
    public String toString() {
        String membershipStatus = isActive ? "Membership = active" : "Membership = inactive";

        return "\n" +
                " Name: " + name +
                ", Birthday = " + birthDay +
                ", Address = " + address + " " +
                "," + membershipStatus +
                ", Swim discipline = " + swimType +
                ", Team category = " + grade +
                ", Training time =" + trainingTime;


    }
}


