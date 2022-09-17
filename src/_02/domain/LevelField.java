package _02.domain;

import java.util.HashMap;

public class LevelField extends DefaultField {

    private final int level;

    public LevelField(int level) {
        super("레벨");
        this.level = level;
    }

    @Override
    public String getFieldValueToString() {
        return String.valueOf(level);
    }

    @Override
    public HashMap<String, Object> getFieldToHashMap() {
        final HashMap<String, Object> resultMap = new HashMap<>();

        resultMap.put(getFieldName(), level);

        return resultMap;
    }
}
