package _02.domain;

public interface FieldComposite {

    public FieldComposite addField(TestField testField);
    public void removeField(String fieldName);
    public TestField build();
}
