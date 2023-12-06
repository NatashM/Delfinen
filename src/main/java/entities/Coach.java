package entities;

import java.util.List;

public class Coach {
    public void displayBestTrainingResults(List<CompetitorMember> members) {
        for (CompetitorMember member : members) {
            Result bestResult = member.getBestTrainingResult();

            System.out.println("Swimmer: " + member.getName());
            if (bestResult != null) {
                System.out.println("Best Training Result: " +
                        bestResult.getDate() + " seconds on " + bestResult.getDate());
            } else {
                System.out.println("No training results available.");
            }

            System.out.println();
        }
    }
}
