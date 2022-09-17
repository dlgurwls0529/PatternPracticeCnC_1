package _01.domain;

import java.util.HashMap;

public class TestInfo extends DefaultTestField {

    private final HashMap<String, TestField> map = new HashMap<>();

    public TestInfo(int no) {
        super(no);
    }

    public void addField(String fieldName, TestField testField) {
        map.put(fieldName, testField);
    }

    public void removeField(String fieldName) {
        map.remove(fieldName);
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
        for(String key : map.keySet()) {
            map.get(key).find();
        }
        return null;
    }
}
