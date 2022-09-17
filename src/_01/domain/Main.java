package _01.domain;

public class Main {
    public static void main(String[] args) {
        TestField mark = new Mark(2);
        TestField personal = new Personal(2);
        TestField testInfo = new TestInfo(2);

        ((TestInfo)testInfo).addField("mark", mark);
        ((TestInfo)testInfo).addField("personal", personal);

    }
}
