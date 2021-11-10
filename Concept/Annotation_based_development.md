# Annotation 기반 개발
## @Component
* 클래스에 선언하며 스프링 컨테이너에 스프링 빈이라는 것을 알려주는 어노테이션(빈=스프링 컴포넌트)


* 컨트롤러,서비스,DAO에는 특별한 @Component 사용
  * @Controller
  * @Service
  * @Repository


* 이름 설정
  ```java
    @Component
    @Component("dokuny")
    @Component(value="dokuny")   // 이 세개의 어노테이션은 빈의 이름을 동일하게 설정한다.
    public class Dokuny{
    }
    // 이름을 지정하지 않으면 클래스 이름에서 첫 번째 글자를 소문자로 바꾼 이름을 빈 이름으로 지정
    // 위의 예시에선 @Component만 쓰면 저절로 "dokuny"라는 이름으로 스프링 컨테이너에 등록된다.
  ```
  

* 클래스 경로 스캐닝(컴포넌트 스캐닝)
  * 빈을 스프링 컨테이너에 등록하기 위해서 해야함
  * xml방식
  ```xml
    <context:component-scan base-package="패키지경로">
      <!-- include-filter는 자동 등록 시킬 컴포넌트 클래스 지정,exclude-filter는 제외한 컴포넌트 클래스 지정-->
      <!-- type속성은 빈 클래스를 걸러낼 때 사용할 전략을 지정 -->
      <!-- expression 속성은 걸러낼 때 사용할 식을 지정 -->
  
      <!-- 어노테이션으로 걸러낼 것이고 그 어노테이션은 MyAnnotation이니 MyAnnotation선언한 클래스를 자동으로 스프링 컨테이너에 등록하겠다 라는 뜻 -->
      <context:include-filter type="annotation" expression="example.annotation.MyAnnotation"/>  
      <!-- 정규표현식으로 걸러낼 거고 Details로 끝나는 빈 클래스를 등록에서 제외하겠다는 뜻 -->
      <context:exclude-filter type="regex" expression =".*Details"/>
  
    </context:component-scan>
  ```
  * 자바 기반 @Configuration 방식
  ```java
    // @Configuration이 선언된 클래스에 선언
    // 컴포넌트들을 스캔할 패키지를 지정한다
    // 해당 패키지와 하위 패키지에서 컴포넌트 스캔
    @ComponentScan("컴포넌트를 스캔할 패키지 경로") 
    @ComponentScan(basePackages={"com.firstpackage","com.secondpackage"})
    @ComponentScan({"com.my.package.first","com.my.package.second"})
  
    // MyMarker 클래스가 존재하는 패키지가 빈 스캐닝의 기준 패키지가 된다
    // MyMarker 클래스가 존재하는 패키지와 하위 패키지를 빈 스캐닝한다
    @ComponentScan(basePackageClasses=MyMarker.class)
  ```
  

## @Autowired
* 객체의 ``타입``으로 자동연결


* 사용하는 곳
  * ``생성자``
    * 빈 클래스에 생성자가 단 하나만 있는 경우에는 생성자에 @Autowired 를 쓸 필요없다. 스프링 컨테이너가 디폴트로 자동연결을 수행한다.
  * ``메서드``
  * ``필드``
  * 위의 셋다 public일 필요는 없다.

* ``required`` 속성
  * false이면 자동연결에 필요한 타입과 일치하는 빈이 없더라도 예외가 발생하지 않는다.
  * 디폴트 값은 true
  * 생성자의 required를 true로 설정하면 다른 생성자에는 @Autowired르 설정할 수 없음.
  * 생성자들이 모두 false값을 가지면, 의존관계 개수가 가장 큰 생성자를 선택

## @Qualifier