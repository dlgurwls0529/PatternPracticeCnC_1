package _02.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.*;

public class MarkField extends DefaultField {

    private final int[] arrMark;

    public MarkField(int[] arrMark) {
        super("학생 답안");

        this.arrMark = copyOf(arrMark, arrMark.length);
    }

    @Override
    public String getFieldValueToString() {
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < arrMark.length; i++) {
            stringBuilder.append(arrMark[i]).append(", ");
        }

        return stringBuilder.toString();

    }

    @Override
    public HashMap<String, Object> getFieldToHashMap() {
        final HashMap<String, Object> resultMap = new HashMap<>();

        resultMap.put(getFieldName(), arrMark);

        return resultMap;
    }
}
