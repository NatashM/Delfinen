package entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CompetitorMember extends Member {
    private List<Result> trainingResults;
    private String team;

    public CompetitorMember(String name, int age, String birthDay, String address, boolean isActive, String grade, String swimType,String trainingTime) {
        super(name, age, birthDay, address, isActive, grade, swimType,trainingTime);
        this.trainingResults = new ArrayList<>();
        this.team = "";
    }

    public List<Result> getTrainingResults() {
        return trainingResults;
    }

    public void addTrainingResult(Result result) {
        trainingResults.add(result);
    }

    public Result getBestTrainingResult() {

        return trainingResults.stream()
                .min(Comparator.comparing(Result::getDate))
                .orElse(null);
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
