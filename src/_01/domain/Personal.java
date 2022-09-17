package _01.domain;

// Leaf
public class Personal extends DefaultTestField {

    public Personal(int no) {
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
        System.out.println("Personal");
        return null;
    }
}
