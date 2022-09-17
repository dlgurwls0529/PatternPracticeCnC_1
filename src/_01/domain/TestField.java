package _01.domain;

// component interface
public interface TestField {

    public TestField read(String path, String sheet, int row);
    public TestField validate(TestField testField);
    public TestField find();
}

// 컴포지트 : 필드의 확장성. find 및 read 의 일관성
// 브릿지 : Excel 을 객체로 혹은 Json 을 객체로 같은 Parser 를 세부 계층으로 분리