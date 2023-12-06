package comparators;

import entities.Member;

import java.util.Comparator;


public class NameComparator implements Comparator<Member> {
    @Override
    public int compare(Member member1, Member member2) {
        return member1.getName().compareToIgnoreCase(member2.getName());
    }
}

