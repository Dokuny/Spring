# 자바기반 컨테이너 설정
## @Configuration 과 @Bean 으로 빈 설정하기
* 방식
```java
@Configuration
@ComponentScan(basePackages ="com.dokuny")   
public class AppConfiguration{  // xml처럼 사용할 자바 클래스 선언 후 @Configuration 달기
    
    @Bean(name="service")          // @Bean 애너테이션 달기,name은 컨테이너에 등록할 이름
    public Service service(){      // 빈으로 만들 클래스에 관한 메서드 작성
        return new ServiceImpl();  // 빈인스턴스를 생성해 반환하면서 스프링 컨테이너에 name값으로 등록
        
        // <bean id="service" class="~.ServiceImpl"/> 과 동일하다.
    }
}
```

* 주의사항
  * ``@Configuration``을 설정한 클래스를 ``final``로 정의해서는 안된다.
  * 해당 클래스 안에는 반드시 ``인수가 없는 생성자``를 제공해야 한다.


### @Bean
* ``@Bean``의 속성
  * ``name`` : 반환하는 빈 인스턴스를 스프링 컨테이너에 등록할 때 사용하는 이름 (id와 동일)
    * name 속성을 지정하지 않으면 메서드 이름을 빈 이름으로 간주한다.
  * ``autowire`` : ``<bean>`` 엘리먼트의 autowire 속성과 동일
  * ``initMethod`` : ``<bean>`` 엘리먼트의 init-method 속성과 동일,커스텀 초기화 메서드 지정
  * ``destroyMethod`` : ``<bean>`` 엘리먼트의 destroy-method 속성과 동일,커스텀 정리 메서드 지정


* @Bean 을 설정한 메서드에 ``@Lazy``, ``@DependsOn``, ``@Primary``, ``@Scope`` 를 덧붙여 설정할 수도 있다.


* ``@Component``나 ``JSR 330 @Named`` 를 설정한 클래스 안에서 @Bean 을 정의할 수 있다.
  * 빈 클래스에 있는 다른 일반 메서드를 호출하는 것처럼 @Bean을 설정한 메서드가 호출될 경우도 있으니 @Configuration 클래스 사용을 권장.


### @ComponentScan
* ``<component-scan>`` 엘리먼트와 같은 역할로 빈을 검색하고 등록해준다.
* 속성
  * ``basePackages`` 
    * ``@Component``를 설정한 클래스를 찾기 위해 검색해야하는 패키지를 지정
    * 복수 지정 시엔 배열로 ``basePackages={"패키지","패키지"}``하거나 @ComponentScan을 여러개 붙인다.
  * ``excludeFilters`` 
  * ``includeFilters`` 


### 클래스경로 스캔 대신 컴포넌트 인덱스 사용
* 스프링5에서 새로 생긴 spring-context-indexer 모듈을 사용하면 컴파일 시점에 스프링 컴포넌트 인덱스를 생성하는 기능을 프로젝트에 추가 가능



* 애플리케이션을 시작할 때 클래스경로 검색 대신 생성된 인덱스를 사용해 스프링 컴포넌트를 로드한다.
  * ``CandidateComponentIndexer``는 스프링 ``@Indexed``를 설정한 클래스를 모두 인덱스에 넣는데 인덱스는 ``spring.components`` 파일에 들어있다.
  * 스프링 5에서는 ``@Configuration``과 ``@Component``에 ``@Indexed``를 메타 애너테이션으로 설정하므로 모두 인덱스에 들어간다.
  * ``@ComponentScan``은 무시되고 spring.components 파일로부터 컴포넌트를 로드하므로 클래스경로 스캔이 필요가 없어지면서 애플리케이션이 커질 시 시작시간을 상당히 줄일 수 있다.



* 활성화 방법순서
  1. 프로젝트 설정에 spring-contextindext의 의존관계 추가
  2. CandidateComponentsIndexer 애너테이션 프로세서를 메이븐 컴파일러 플러그인과 함께 설정
***
## 빈 의존 관계 주입하기
* @Bean 메서드가 생성하는 빈의 의존 관계를 만족시킬 방법
  * @Bean 메서드 호출
  * @Bean 메서드의 매개변수(인수)로 의존관계를 지정
  * 빈 클래스에서 @Autowired,@Inject,@Resource 등을 사용해 의존 관계를 자동연결


## 스프링 컨테이너 설정하기
```java
public class App{
  public static void main(String[] args) {
      // 여러 @Configuration 에 @Bean 메서드를 나눠 정의했다면, 밑의 생성자에 모든 @Configuration 클래스를 전달한다.
      // 생성자에 @Component 나 @Named를 설정한 클래스나 @Bean 메서드를 통해 정의한 빈 클래스를 등록할 수도 있다.
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
    
    // context.register(MyConfig.class); 이런식으로 ApplicationContext의 register메소드를 사용해서 설정할 수도 있다.
    // 혹은 context.scan("패키지명","패키지명"); 으로 패키지단위째 컴포넌트를 스캔할 수도 있다.(@ComponentScan과 같은 역할)
    
    context.fresh(); // 반드시 refresh 메서드를 호출해서 위의 인스턴스가 등록된 클래스의 소유권을 확보하도록 해야한다.
  }
}
```

## 자바 기반 설정 임포트하기
* ``@Import`` 사용
  * beans 스키마의 ``<import>``와 같은 역할
```java
@Configuration
@Import({DaosConfig.class,OtherObjects.class})  // 하나 이상의 @Configure 파일을 합치려면 @Import를 사용
public class ServicesConfig{                    // @Component 클래스도 @Import를 통해 @Configuration 클래스 안으로 임포트 할 수 있다.
    
}
```
* 의존 관계 해결하기
  * 여러 @Configuration에 정의한 빈 사이의 상호 의존 관계는 다음 접근 방법을 사용해 풀 수 있다.
    * @Bean 메서드의 인수로 빈 의존 관계를 지정
    * 임포트한 @Configuration 클래스를 자동 연결하고, 그 안에 @Bean 메서드를 호출해서 의존 관계를 가져온다. -> 추천
  ```java
  @Configuration
  @Import({BankDaosConfig.class,BankOtherObjects.class})
  public class BankServicesConfig{
  
    @Autowired   // @Configuration 클래스를 자동 연결, @Configuration 클래스도 다른 빈클래스와 마찬가지로 취급하기 때문에 자동연결가능
    private BankDaosConfig bankAppDao;
   
    @Bean(name="accountStatementService")  // @Bean 메서드의 인수로 의존관계 설정
    public AccountStatementService accountStatementService(AccountStatementDao accountStatementDao){
        AccountStatementServiceImpl accountStatementServiceImpl = new AccounStatementServiceImpl();
        
        accountStatementServiceImpl.setAccountStatementDao(accountStatementDao);
        
        return accountStatementServiceImpl;
    }
  
    @Bean(name="fixedDeposiService")   
    public FixedDepositService fixedDepositService(){
        return new FixedDepositServiceImpl(bankAppDao.fixedDepositDao());  
        // @Configuration 클래스인 BankDaosConfig의 @Bean 메서드를 호출
    }
  }
  ```