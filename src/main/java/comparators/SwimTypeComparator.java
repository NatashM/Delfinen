package comparators;

import entity.Member;

import java.util.Comparator;


    public class SwimTypeComparator implements Comparator<Member> {
        @Override
        public int compare(Member member1, Member member2) {
            return member1.getSwimType().compareToIgnoreCase(member2.getSwimType());
        }
    }


