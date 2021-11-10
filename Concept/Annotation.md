# 스프링 어노테이션
## Annotation
* ``@Service``
* ``@Repository``
* ``@Component``
* ``@Autowired``
* ``@Transactional``
* ``@Scope``


## Spring MVC Annotations
* ``@Controller``
* ``@RequestMapping``
* ``@PathVariable``
* ``@RequestParam``
* ``@ModelAttribute``
* ``@SessionAttributes``


## Spring Security Annotaions
* ``@PreAuthorize``

***
# Annotation
## @Component
* 사용전 xml에 ``<context:component-scan base-package="패키지명"/>`` 선언
* 클래스에다 사용

* @Component로 등록한 빈 가져오기
```java
// 자바 기반 스프링 컨테이너 설정
ApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfigClass.class); 

// 빈 가져오기 (getBean이용)
TestBean t = ctx.getBean(TestBean.class); // 클래스 자체로 가져오기
TestBean t = (TestBean) ctx.getBean("testBean"); // Component시의 이름으로 가져오기, 형변환 해줘야함
TestBean t = ctx.getBean("testBean", TestBean.class);  // 매개변수로 이름과 클래스를 지정해서 형변환이 필요 없음
```


## @Autowired
* xml에서 빈을 찾아서 사용하려면?
  * ``<context:annotation-config/>``를 xml에 삽입하여 사용
  * 컴포넌트 애너테이션 사용 시엔 위의 내용이 필요 없음


* 동작 방식
  * 1순위 : 필요한 의존 객체의 ``타입``에 해당하는 빈을 찾아 주입한다.
  * 2순위 : 동일한 타입의 빈이 존재할 땐,빈의 id(이름)와 ``@Autowired``가 사용된 대상의 매개변수 이름이 동일한 빈을 찾는다.
    * 매개변수의 이름을 빈 이름과 동일하게 하고 싶은 경우
      * 매개변수 이름을 바꿔주기 -> 비추천
      * ``@Qualifier("빈 이름")`` 사용
        * 주로 @Qualfier 애너테이션 사용(애너테이션 안에 들어가는 이름을 빈이름과 동일하게 적어서 사용) 


* required 속성
  * required 속성은 기본값이 true
    * autowired가 되어있으면 빈을 찾아서 무조건 넣으려고 한다
    * 빈이 없을 경우 에러가 발생
  * false로 해놓으면 빈이 없더라도 에러 발생 X -> null값으로 실행할 때 이용하는 등
    @Autowired(required = false)
    ```


* 사용가능 대상(어노테이션을 통한 인젝션 방법)
  * 생성자
  * setter
  * 필드


* 생성자 방식
  * 생성자에 @Autowired를 선언,권고되는 방법 중 하나
  * ``@Qualifier``를 쓰려면 생성자 매개변수에 각자 지정해야한다.
  * 장점
    * 필수적으로 사용해야 하는 레퍼런스 없이는 인스턴스를 만들지 못하도록 강제함
    * Spring 4.3 이상부터는 생성자가 하나인 경우 @Autowired를 사용하지 않아도 됨
    * Circular Dependency / 순환 참조2 의존성을 알아 차릴 수 있음 
    * 생성자에 점차 많은 의존성이 추가 될 경우 리팩토링 시점을 감지 할 수 있음
    * 의존성 주입 대상 필드를 final로 불편 객체 선언할 수 있음
    * 테스트 코드 작성시 생성자를 통해 의존성 주입이 용이함
  * 단점
    * 어쩔 수 없는 순환 참조는 생성자 주입으로 해결하기 어려움
      * 이러한 경우에는 나머지 주입 방법 중에 하나를 사용하고 되도록이면 순환 참조가 발생하지 않도록 해야함


* 세터 방식
  * setter 메소드에 @Autowired annotation을 선언하여 주입받는 방법
  * 장점
    * 의존성이 선택적으로 필요한 경우에 사용
    * 생성자에 모든 의존성을 기술하면 과도하게 복잡해질 수 있는 것을 선택적으로 나눠 주입 할 수 있게 부담을 덜어줌
    * 생성자 주입 방법과 Setter 주입 방법을 적절하게 상황에 맞게 분배하여 사용
  * 단점
    * 의존성 주입 대상 필드가 final 선언 불가


* 필드 방식
  * 멤버 필드에 어노테이션 선언
  * 기본생성자를 호출할 때 필드에 인젝션이 된다.
    * 기본 생성자는 다른 방식으로 실행하더라도 인스턴스를 만들어서 인젝션 해주기 때문에 항상 실행된다(생성자방식 제외)
    * 필드에 Autowired 쓰려면 기본 생성자가 반드시 필요하다.
  * 장점
    * 가장 간단한 선언 방식
  * 단점
    * 의존 관계가 눈에 잘 보이지 않아 추상적이고, 이로 인해 의존성 관계가 과도하게 복잡해질 수 있음
    * 이는 SRP / 단일 책임 원칙에 반하는 안티패턴
    * DI Container와 강한 결합을 가져 외부 사용이 용이하지 않음
      * 단위 테스트시 의존성 주입이 용이하지 않음
    * 의존성 주입 대상 필드가 final 선언 불가

***
