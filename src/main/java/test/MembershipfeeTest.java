package test;

import database.Database;
import entities.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MembershipfeeTest {

    @Test
    public void testMembershipFee() {
        Database database = new Database();

        Member juniorMember = new Member("Katinka", 16, "270207", "Helsinki", true, "Junior", "Backstroke", "02:06");
        Assertions.assertEquals(1000, database.MembershipFee(juniorMember));

        Member seniorMember = new Member("Halima", 20, "030303", "Athen", true, "Senior", "Breast stroke", "01:06");
        Assertions.assertEquals(1600, database.MembershipFee(seniorMember));


        Member seniorMemberOver60 = new Member("Sebastian", 62, "080361", "Mogadishu", true, "Senior", "Butterfly", "05:46");
        Assertions.assertEquals((1600 / 4.0) * 3, database.MembershipFee(seniorMemberOver60));

        Member inactiveMember = new Member("Regular", 25, "080398", "Oslo", false, "Senior", "crawl", "02:06");
        Assertions.assertEquals(500, database.MembershipFee(inactiveMember));

    }
}