package _02.domain;

public abstract class DefaultField implements TestField {

    private final String fieldName;

    public DefaultField(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String getFieldName() {
        return this.fieldName;
    }
}
