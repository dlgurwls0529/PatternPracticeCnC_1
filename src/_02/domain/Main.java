package _02.domain;

import _02.repository.MemoryTestDataRepository;

import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        memoryRepositoryTest();
    }

    public static void fieldHierarchyStringTest() {
        TestField nameField = new NameField("이혁진");
        TestField noField = new NoField(1);
        TestField personalField = new PersonalField();

        ((PersonalField)personalField).addField(nameField);
        ((PersonalField)personalField).addField(noField);

        TestField markField = new MarkField(new int[]{3, 4, 5, 6, 9});

        TestField fieldBundle = new FieldBundle();
        ((FieldBundle)fieldBundle).addField(markField);
        ((FieldBundle)fieldBundle).addField(personalField);

        System.out.println(
                fieldBundle.getFieldValueToString()
        );
    }

    public static void fieldHierarchyHashMapTest() {
        TestField nameField = new NameField("이혁진");
        TestField noField = new NoField(1);
        PersonalField personalField = new PersonalField();

        personalField.addField(nameField);
        personalField.addField(noField);

        TestField markField = new MarkField(new int[]{3, 4, 5, 6, 9});

        FieldBundle fieldBundle = new FieldBundle();
        fieldBundle.addField(markField);
        fieldBundle.addField(personalField);

        HashMap<String, Object> hashMap = fieldBundle.getFieldToHashMap();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");

        for(String key : hashMap.keySet()) {
            stringBuilder.append(key).append(" : ");
            stringBuilder.append(hashMap.get(key)).append(", ");
        }

        stringBuilder.append(")");

        System.out.println(stringBuilder.toString());

        /*
        FieldBundleBuilder fieldBundleBuilder = ne..
        fb = fbb.addField(new NameField("이혁진"))
            .addField(new NoField(1))
            .build();
         */
    }

    public static void memoryRepositoryTest() {
        TestField fieldBundle1 = new FieldBundle()
                .addField(new MarkField(new int[]{3, 4, 5, 6, 9}))
                .addField(
                        new PersonalField()
                                .addField(new NameField("이혁진"))
                                .addField(new NoField(1))
                                .addField(new LevelField(7))
                                .build()
                )
                .build();

        TestField fieldBundle2 = new FieldBundle()
                .addField(new MarkField(new int[]{3, 5, 6, 9, 10}))
                .addField(
                        new PersonalField()
                                .addField(new NameField("이우진"))
                                .addField(new NoField(3))
                                .addField(new LevelField(10))
                                .build()
                )
                .build();

        MemoryTestDataRepository memoryTestDataRepository =
                new MemoryTestDataRepository();

        memoryTestDataRepository.save(fieldBundle1);
        memoryTestDataRepository.save(fieldBundle2);

        List<TestField> list = memoryTestDataRepository.findAll();
        for(TestField testField : list) {
            HashMap<String, Object> hashMap =
                    ((FieldBundle)testField).getFieldToHashMap();

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("(");

            for(String key : hashMap.keySet()) {
                stringBuilder.append(key).append(" : ");
                stringBuilder.append(hashMap.get(key)).append(", ");
            }

            stringBuilder.append(")");

            System.out.println(stringBuilder.toString());
        }

    }
}
