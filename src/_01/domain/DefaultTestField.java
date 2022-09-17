package _01.domain;

public abstract class DefaultTestField implements TestField{

    protected int no;
    protected TestReader testReader;

    protected DefaultTestField(int no) {
        this.no = no;
    }

    public DefaultTestField(int no, TestReader testReader) {
        this.no = no;
        this.testReader = testReader;
    }
}
