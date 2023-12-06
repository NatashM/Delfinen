package comparators;

import entity.Member;

import java.util.Comparator;

    public class GradeComparator implements Comparator<Member> {
        @Override
        public int compare(Member member1, Member member2) {
            return member1.getGrade().compareToIgnoreCase(member2.getGrade());
        }
}
