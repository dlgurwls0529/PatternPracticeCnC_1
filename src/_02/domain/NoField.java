package _02.domain;

import java.util.HashMap;

public class NoField extends DefaultField {

    private final int no;

    public NoField(int no) {
        super("시험 번호");
        this.no = no;
    }

    @Override
    public String getFieldValueToString() {
        return String.valueOf(no);
    }

    @Override
    public HashMap<String, Object> getFieldToHashMap() {
        final HashMap<String, Object> resultMap = new HashMap<>();

        resultMap.put(getFieldName(), no);

        return resultMap;
    }
}
