package _02.domain;

import java.util.HashMap;

public class NameField extends DefaultField {

    private final String name;

    public NameField(String name) {
        super("학생 이름");
        this.name = name;
    }

    @Override
    public String getFieldValueToString() {
        return name;
    }

    @Override
    public HashMap<String, Object> getFieldToHashMap() {
        final HashMap<String, Object> resultMap = new HashMap<>();

        resultMap.put(getFieldName(), name);

        return resultMap;
    }
}
