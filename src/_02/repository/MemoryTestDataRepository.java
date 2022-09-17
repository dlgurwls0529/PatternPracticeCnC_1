package _02.repository;

import _02.domain.TestField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryTestDataRepository implements TestDataRepository {

    private static final List<TestField> list = new ArrayList<>();

    @Override
    public void save(TestField testField) {
        list.add(testField);
    }

    @Override
    public List<TestField> findAll() {
        return new ArrayList<>(list);
    }
}
