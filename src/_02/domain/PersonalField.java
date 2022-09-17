package _02.domain;

import java.util.HashMap;

public class PersonalField extends DefaultField implements FieldComposite {

    private final HashMap<String, TestField> map;

    public PersonalField() {
        super("인적 사항");
        map = new HashMap<>();
    }

    @Override
    public String getFieldValueToString() {
        StringBuilder stringBuilder = new StringBuilder();

        for(String key : map.keySet()) {
            stringBuilder.append(map.get(key).getFieldValueToString()).append(", ");
        }

        return stringBuilder.toString();
    }

    @Override
    public FieldComposite addField(TestField testField) {
        map.put(testField.getFieldName(), testField);
        return this;
    }

    @Override
    public void removeField(String fieldName) {
        map.remove(fieldName);
    }

    @Override
    public TestField build() {
        return this;
    }

    @Override
    public HashMap<String, Object> getFieldToHashMap() {
        final HashMap<String, Object> resultMap = new HashMap<>();

        for(String key : map.keySet()) {
            resultMap.putAll(map.get(key).getFieldToHashMap());
        }

        return resultMap;
    }
}
