package comparators;

import entities.Member;

import java.util.Comparator;

    public class TrainingTimeComparator implements Comparator<Member> {
        @Override
        public int compare(Member member1, Member member2) {
            return member1.getTrainingTime().compareToIgnoreCase(member2.getTrainingTime());
        }
    }

