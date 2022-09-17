package _01.Test;

import _01.domain.Mark;
import _01.domain.Personal;
import _01.domain.TestField;
import _01.domain.TestInfo;

public class MyTest {
    public static void main(String[] args) {
        testInfoPseudoFindTest();
    }

    public static void testInfoPseudoFindTest() {
        TestField mark = new Mark(3);
        TestField personal = new Personal(3);
        TestInfo testInfo = new TestInfo(3);

        testInfo.addField("mark", mark);
        testInfo.addField("personal", personal);

        testInfo.find();
        mark.find();
        personal.find();
    }
}
