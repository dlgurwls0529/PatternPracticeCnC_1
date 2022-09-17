## 객체지향_연습하기_C&CProject_1
우정이형과 다겸이와 하는 프로젝트를 하면서 직면했던 문제점 중 하나는 '요구사항의 변화'였다. 개발 초기에는 의존 관계가 복잡하지 않아서, 요구사항이 변화하면 해당되는 부분만 수정하면 되었다. 계속해서 개발이 진행되면서 프로그램의 크기가 점점 커졌고 코드 간의 의존 관계가 복잡해졌다. 우정이형이 매번 요구했던 사항은 그다지 오래 걸리는 작업이 아니었지만, 위의 이유로 인해서 요구사항을 반영하는 것이 매우 부담스러운 일이 되었다.  

여기에서 의존 관계란 다음을 말한다. (https://code-lab1.tistory.com/122)

>의존관계는 의존 대상 B가 변하면, 그것이 A에 영향을 미칠 때 A는 B와 의존관계라고 한다. 쉽게 말해서 B가 변할 때 그 영향이 A에 미치는 관계를 말한다.  
  
가장 단편적인 예시를 들면
  
    public class Songwoojeong {
        private Leehyeokjin leehyeokjin;
        
        public Songwoojeong() {
            leehyeokjin = new Leehyeokjin();
        }
        
        public void order() {
            leehyeokjin.work();
        }
    }
    
Songwoojeong은 Leehyeokjin에 의존한다. 왜냐하면, Leehyeokjin이 변화함에 따라(클래스가 사라지거나 work 자체의 출력 타입이나 매개변수가 추가되는 등) Songwoojeong역시 고쳐야 하기 때문이다.  

이러한 의존 관계가 많아지는 것의 문제점은 어떤 코드를 수정할 때, 그 코드에 의존하는 코드를 연쇄적으로 수정해줘야 해서 많은 시간과 노력이 필요하다는 점이다.  

가령

    A a = new A();
    B b = new B(a);
    C c = new C(b);
    D d = new D(c);
    
A의 생성자가 private으로 수정되어 new를 사용할 수 없는 상황이라고 하자. 그러면 맨 위 줄인 "A a = new A();"에서 오류가 뜨게 된다. a가 생성되지 않으니 a를 받아서 생성되는 b 역시도 생성되지 않는다. 이처럼 세 번째와 네 번째 줄에서도 오류가 뜨게 된다. 

이처럼 의존 관계가 얽혀있는 경우 약간의 수정만으로도 연쇄적인 다량의 코드 오류가 발생한다.  

이러한 의존 관계 중에서도 다루기 어려운 경우 첫 번째는 의존 관계가 분산되어있을 때 발생한다.  

    public class B {
        ...
        A a = new A();
        ...
    }
  
    public class C {
        ...
        A a = new A();
        ...
    }
  
    public class D {
        ...
        A a = new A();
        ...
    }
  
클래스 B, C, D가 어떤 메소드 안에서 A의 생성을 수행하고 있다. 이 경우에도 객체 a의 생성이 불가능해지는 경우 클래스 B, C, D에서 모두 오류가 발생하게 된다. 이렇게 의존 관계가 여러 클래스에 분산되어있는 경우, 각 오류를 해결하기 위해 작업하기 매우 부담스러워진다. 이전의 예시처럼 의존 관계가 응집되어있는 경우에는 한 곳에서 수정이 가능하므로 부담이 덜해진다. 이러한 의존관계가 분산된 경우를 응집도가 낮다고 하고, 응집된 경우를 응집도가 높다고 한다.  

두 번째 문제는 의존 관계가 너무 강하게 연결되어있는 경우 발생한다. 의존 관계가 강하다는 것은 다른 것에 의존하는 대상이 의존 당하는 대상의 변화에 따라 영향을 받는 정도를 말한다. 

가령, Leehyeokjin 클래스와 Yeodakyum 클래스가 Worker 클래스를 상속한다고 해보자.

    public void work(Worker worker) {
        ...
    }
    
    work(new Worker());
    
    public void work(Leehyeokjin leehyeokjin) {
        ...
    }
    
    work(new Leehyeokjin());
    
두 메소드 work를 비교해보자. 첫 번째 메소드는 호출 시에 new Worker() 대신에 new Leehyeokjin() 이나 new Yeodakyum() 이 들어와도 수정 없이 작동한다. 반면 두 번째 메소드는 new LeeHyeokjin() 대신에 new Yeodakyum() 을 넣으면 코드를 수정해야지만 돌아가게 된다. 이처럼 의존 관계 중에서도, 의존 당하는 대상이 변하더라도 의존하는 대상에 미치는 영향은 제각각이다. 이렇게 의존관계가 강할수록 결합도가 크다고 하고, 약할수록 결합도가 작다고 한다. 강한 의존관계일수록 의존하는 대상에 큰 영향을 미치기 때문에 수정 작업이 더 필요하기 때문에 개발자에게 큰 부담이 된다.

내가 아직 아는 바가 없어서, 위의 설명은 응집도와 결합도의 정의와는 조금 다를 수 있다. 해당 개념에 대한 내 생각을 담은 것이므로 정확한 자료를 찾아보는 것이 좋을 것 같다. 

아무튼 요구사항 변화에 쉽게 대응하기 위해서는 결합도를 낮추고 응집도를 높이는 것이 보통 좋다고 할 수 있겠다. 객체지향적인 프로그래밍을 한다는 것도 역시 이러한 목적을 향하고 있다. 이는 클래스의 캡슐화, 상속, 다형성을 통해서 응집도와 결합도를 조정하고, 궁극적으로는 해당 시스템의 수정 작업을 쉽게한다.

나는 현재 진행하는 프로젝트의 문제점인 복잡한 의존 관계를 해결하기 위해서 객체지향의 방법을 활용해보았다. 이에 앞서, 기존의 요구사항 변화를 분석했다. 가장 많았던 것은 시험 데이터의 형태 수정에 관한 것이었다.

>"혁진아, 점수 등수에 따른 학원 내 백분위도 추가해줘."  
"혁진아, 점수를 총점 말고도 유형별 점수로도 해주면 좋겠어."  
"혁진아, 백분위를 시험 유형별 점수로도 추가해줘."  
"혁진아, 그 백분위랑 수능 등급 있잖아 그거처럼 등급도 추가해줘."  
"혁진아, 점수를 같은 레벨끼리 등수를 매긴 백분위도 추가해줘."  
"혁진아, 같은 레벨에서 유형별 등수를 매긴 백분위도 추가해줘."  
"혁진아, 학원 내 백분위로 추정한 전국 백분위도 추가해줘."  
  
결국 가장 큰 목표는 자동적으로 "시험 데이터의 확장이 유연할 것"이 되겠다. 조금 더 구체적으로 말하자면 시험 데이터를 수정할 때, OCP(https://steady-coding.tistory.com/378)  를 만족하면서도 최대한 수정이 적도록 해야 한다.  

첫 번째로 생각했던 것은 팩토리 메소드 패턴이었다. 초기 시험 데이터 형태를 연쇄적으로 상속하여 요구된 데이터를 추가한 클래스를 만들고, 해당되는 팩토리 클래스에 생성을 위임하는 것이다. OCP를 만족하는 방법이지만, 그것은 다음과 같은 문제가 있었다.  

- 재활용이 어렵다. 재활용을 할려면 추가 요구에 따른 경우의 수만큼 서브클래싱이 필요하게 된다. 확장을 하더라도 완전 같은 상황이 아니면 재활용이 힘들다.  
- 서브클래스가 너무 많아진다. 시험 데이터가 많아짐에 따라서 팩토리 클래스와 해당 데이터가 추가된 클래스를 서브클래싱해야 한다.  

두 번째로 생각했던 것은 브릿지 패턴과 컴포지트 패턴을 같이 활용하는 것이었다. 컴포지트 패턴을 통해서 필드를 트리 형태로 구현하고(메인 계층), 해당 데이터를 읽고 쓰는 방식(엑셀이냐 데이터베이스냐 JSON이냐 등, 서브 계층)을 연결하는 브릿지 패턴을 적용하고자 했다. 하지만, 이 클래스는 단순히 시험 데이터를 감싸는 도메인이지 결코 읽고 쓰는 책임까지 맡을 필요는 없었다. 그래서 읽고 쓰는 방법에 대한 계층은 Repository에 넘기기로 했다. 

최종적으로는 컴포지트 패턴만을 적용하게 되었다. 데이터의 세부 컬럼들이 트리 구조로 연결되어있기도 하고, OCP를 만족하는 기능 확장이 가능하며 서브클래싱이 팩토리 메소드 패턴보다 덜 하기 때문이다.  

    PersonalField personalField = new PersonalField();
    personalField.addField(new NameField("이혁진"));
    personalField.addField(new NoField(1));

    FieldBundle fieldBundle = new FieldBundle();
    fieldBundle.addField(new MarkField(new int[]{3, 4, 5, 6, 9}));
    fieldBundle.addField(personalField);

    HashMap<String, Object> hashMap = fieldBundle.getFieldToHashMap();

결과만 보면 이렇게 쓸 수 있다. 만약, 인적 사항에 Level 필드가 추가된다면, Level 클래스를 추상 클래스인 DefaultField를 상속한 뒤,

    PersonalField personalField = new PersonalField();
    personalField.addField(new NameField("이혁진"));
    personalField.addField(new NoField(1));
        
    // personalField.addField(new LevelField(7));

    FieldBundle fieldBundle = new FieldBundle();
    fieldBundle.addField(new MarkField(new int[]{3, 4, 5, 6, 9}));
    fieldBundle.addField(personalField);

    HashMap<String, Object> hashMap = fieldBundle.getFieldToHashMap();

"personalField.addField(new LevelField(7));" 이거 한 줄만 추가해주면 된다. 만약 TestData 클래스 하나에 모든 필드를 다 넣었다면, 필드 변화 시에 그것에 의존하는 다른 코드가 그럴 때마다 변하겠지만, 위에서 보았듯이 개선된 코드는 기존의 로직을 유지한 채로 한줄과 클래스 하나만 추가해서 끝낼 수 있다.  

만약 "시험 점수의 유형별 합계를 추가해라" 라는 요구가 추가된다면, 트리 구조를 활용해 쉽게 대응할 수 있다.

    ScoreOfCategoryField scoreOfCategoryField = new ScoreOfCategoryField();
    scoreOfCategoryField.addField(new LcGenScore(12));
    scoreOfCategoryField.addField(new LcDedScore(12));
    scoreOfCategoryField.addField(new GrScore(12));
    scoreOfCategoryField.addField(new RcGenScore(12));
    scoreOfCategoryField.addField(new RcDedScore(12));
    scoreOfCategoryField.addField(new RcSatScore(12));
    
    fieldBundle.addField(scoreOfCategoryField);
    HashMap<String, Object> hashMap = fieldBundle.getFieldToHashMap();

하지만, 생성 과저이 지나치게 복잡하고 많은 줄을 소모한다. 이 경우에는 addField가 자기 자신을 리턴하도록 하여 생성을 편리하게 한다. (메소드 체이닝 : https://my-devblog.tistory.com/5) 이를 활용해서 빌더 패턴 역시 고려할 수 있지만, 객체 생성 절차를 필드 주입과 분리하여 얻을 수 있는 이점이 별로 없다 생각하여 메소드 체이닝만을 활용하였다.

    TestField fieldBundle = new FieldBundle()
                .addField(new MarkField(new int[]{3, 4, 5, 6, 9}))
                .addField(
                        new PersonalField()
                                .addField(new NameField("이혁진"))
                                .addField(new NoField(1))
                                .addField(new LevelField(7))
                                .build()
                )
                .build();
                
사실 별 차이 없지만, 조금 더 편한 느낌이다. 다음으로 진행할 것은 Repository이다. 레포지토리에서 예상되는 변화는 해당 레포지토리가 메모리에 저장되냐, DB에 저장되냐, 엑셀에 저장되느냐 하는 저장 방식에 대한 것이다. 이에, 인터페이스를 우선적으로 선언한다.  

    public interface TestDataRepository {

        public void save(TestField testField);
        List<TestField> findAll();
    }
    
이후에 

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
    
단순히 메모리에 올리는 레포지토리는 이렇게 구현하고, 

    memoryTestDataRepository.save(fieldBundle1);
    memoryTestDataRepository.save(fieldBundle2);
    memoryTestDataRepository.save(fieldBundle3);
    ...
    List<TestField> list = memoryTestDataRepository.findAll();
    
이렇게 쓸 수 있다. 아직은 Service 단이 구현되지 않아서 해당 인터페이스에 의한 다형성 효과는 잘 보기 어렵지만, 이것 때문에 저장 방식이 달라지더라도 같은 방식으로 수정 없이 활용할 수 있을 것이다.

    TestDataRepository testDataRepository = new MemoryTestDataRepository();
    ...
    testDataRepository.save(fieldBundle1);
    testDataRepository.save(fieldBundle2);
    testDataRepository.save(fieldBundle3);
    ...
    List<TestField> list = testDataRepository.findAll();
    
    TestDataRepository testDataRepository = new JdbcTestDataRepository();
    ...
    testDataRepository.save(fieldBundle1);
    testDataRepository.save(fieldBundle2);
    testDataRepository.save(fieldBundle3);
    ...
    List<TestField> list = testDataRepository.findAll();
    
이렇게 상위타입인 TestDataRepository에 주입하는 인스턴스를 달리해줘도 save와 findAll은 수정 없이 잘 작동한다.  

확장성 있는 구조를 만들었지만, 아직 new를 통해 객체를 주입하는 부분이 매우 거슬린다. 아주 조금이지만 Client가 많아지는 경우 해당 의존 관계의 응집도가 낮아지게 될 수도 있다. 해결하기 위해서는 따로 의존성을 주입해주는 컨테이너 클래스를 만들수도 있겠다. 이건 나중에 생각해보자.  

전체적인 클래스 관계를 쓰면 다음과 같다.

    <domain.TestField.interface>
    + getFieldValueToString() : String
    + getFieldName() : String
    + getFieldToHashMap() : HashMap<String, Object>
    
    <domain.FieldComposite.interface>
    + addField(TestField) : FieldComposite    // 메소드 체이닝
    + removeField(String) : void
    + build() : TestField
    
    <domain.DefaultField.abstract class implements TestField>
    - fieldName : String
    
    <domain.FieldBundle.class extends DefaultTestField implements FieldComposite>
    - map : HashMap<String, TestField>
    
    <domain.PersonalField.class extends DefaultTestField implements FieldComposite>
    - map : HashMap<String, TestField>
    
    <domain.FieldBundle.class extends DefaultTestField>
    - level : int
    
    <domain.MarkField.class extends DefaultTestField>
    - arrMark : int[]
    
    <domain.NameField.class extends DefaultTestField>
    - name : String
    
    <domain.NoField.class extends DefaultTestField>
    - no : int
    
전체 코드는 깃허브(https://github.com/dlgurwls0529/PatternPracticeCnC_1.git) 에 있다. 

다음에는 해당 코드에 컨테이너로 new를 통해 생성하는 부분을 좀 줄여보고, 로컬 데이터베이스를 활용한 레포지토리를 구현해보겠다.

처음 디자인 패턴이랑 객체지향 공부할 때는 이걸 어떻게 써야 하나 감이 오지 않아서 재미가 없었는데, 계속 하다보니 느낌이 오는 것도 같아 나름 뿌듯하다. 
