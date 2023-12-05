package test;

import org.example.Member;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MemberTest {
    @Test
    public void removeMember(){
        ArrayList<Member> members = new ArrayList<>();
// arrange
        Member member = new Member("Jane", 19, "230904", "Stockholm",true, "Senior", "Non-competitor", "90");
        members.add(member);

// act
        members.remove(member);
//assert
        ArrayList<Member> expected = new ArrayList<>();
        assertEquals(members, expected);
    }

}


//Add member
// competitor member
//kontigentbetalinger