package _01.domain;

public class Mark extends DefaultTestField {

    public Mark(int no) {
        super(no);
    }

    @Override
    public TestField read(String path, String sheet, int row) {
        return null;
    }

    @Override
    public TestField validate(TestField testField) {
        return null;
    }

    @Override
    public TestField find() {
        System.out.println("Mark");
        return null;
    }
}
