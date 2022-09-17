package _01.domain;

public interface TestReader {

    public TestField read(Object sourceRow);
    public TestField validate(TestField testField);
}
