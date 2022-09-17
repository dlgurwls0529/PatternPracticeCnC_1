package _02.repository;

import _02.domain.TestField;

import java.util.List;
import java.util.Optional;

public interface TestDataRepository {

    public void save(TestField testField);
    List<TestField> findAll();
}
