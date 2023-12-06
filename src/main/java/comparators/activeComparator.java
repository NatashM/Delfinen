package comparators;

import entity.Member;

import java.util.Comparator;


public class activeComparator implements Comparator<Member> {
    @Override
    public int compare(Member member1, Member member2) {
        return Boolean.compare(member1.getIsActive(), member2.getIsActive());
    }
}



