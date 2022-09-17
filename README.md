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
  
클래스 B, C, D가 어떤 메소드 안에서 A의 생성을 수행하고 있다. 
    

    
