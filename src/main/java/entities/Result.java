package entities;

import java.time.LocalDate;

public class Result {

    public String getTime;
    private LocalDate date;
    private String swimmingDiscipline;
    private CompetitorMember competitorMember;

    public Result(LocalDate date, String swimmingDiscipline, CompetitorMember competitorMember) {
        this.date = date;
        this.swimmingDiscipline = swimmingDiscipline;
        this.competitorMember = competitorMember;
    }

    // Getters and setters
    public LocalDate getDate() {
        return date;
    }

    public String getSwimmingDiscipline() {
        return swimmingDiscipline;
    }

    public CompetitorMember getCompetitorMember() {
        return competitorMember;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setSwimmingDiscipline(String swimmingDiscipline) {
        this.swimmingDiscipline = swimmingDiscipline;
    }

    public void setCompetitorMember(CompetitorMember competitorMember) {
        this.competitorMember = competitorMember;
    }

    @Override
    public String toString() {
        return "Result{" +
                "Date " + date +
                ", Discipline " + swimmingDiscipline +
                ", CompetitorMember " + competitorMember +
                "}";
    }
}
