# IOC와 DI
## IoC(Inversion of Control)
* 개념
  * 객체의 생성,생명주기의 관리까지 모든 객체에 대한 제어권이 프레임워크의 컨테이너로 바뀌었다는 것을 의미

* 분류
  * IoC
    * DL
    * DI
      * Setter Injection
      * Constructor Injection
      * Method Injection


## DI
* 각 클래스간의 의존관계를 빈 설정 정보를 바탕으로 컨테이너가 자동으로 연결해주는 것을 말함
  

## Spring DI 컨테이너
* Spring DI 컨테이너 개념
  * Spring DI 컨테이너가 관리하는 객체를 빈(bean)이라고 하고, 이 빈들을 관리한다는 의미로 컨테이너를 빈 팩토리(BeanFactory)라고 부른다
  * Bean Factory에 여러가지 컨테이너 기능을 추가하여 Application Context라고 부름
  * 인터페이스 BeanFactory <- 인터페이스 ApplicationContext



* BeanFactory와 ApplicationContext
  * BeanFactory
    * Bean을 등록,생성,조회,반환 관리함
    * 보통은 BeanFactory를 바로 사용하지 않고, 이를 확장한 ApplicationContext를 사용함
    * getBean() 메서드가 정의되어 있음
  * ApplicationContext
    * 위와 동일
    * Spring의 각종 부가 서비스를 추가로 제공
    * Spring이 제공하는 ApplicationContext 구현클래스가 여러가지 존재
    * DI 컨테이너 역할을 한다.
***
    
## xml방식 Bean 의존관계 설정
* Setter Injection 
  * ``<property>`` 태그
    * Setter 메소드를 통해 의존관계가 있는 Bean을 주입
    * 속성
      * ref : 빈 이름을 이용해 주입할 빈을 찾는다
      * value : 단순 값 또는 빈이 아닌 객체를 주입할 때 사용
  * ``<constructor-arg>`` 태그
    * 생성자를 통해 의존관계가 있는 빈을 주입할 때 사용
    * 생성자의 파라미터를 이용하기 때문에 한번에 여러 개의 객체를 주입 가능
    * 속성
      * index : 생성자 파라미터 순서로 지정(0부터)
      * name : 파라미터 이름으로 지정
      * value
      * ref
### <property> 값 설정 방법
* 컬렉션 타입의 값 주입
  * List 타입,Set 타입
    * ``<list>``,``<set>``
      * ``<value>``
    ```xml
    <!--Hello라는 클래스에 List 타입 필드 names가 존재할 경우(Setter방식) -->
    <!--Set타입은 <list>를 <set>으로만 바꿔주면 된다. -->
      <bean id="hello" class="~.Hello">
         <property name="names">
              <list>  
                 <value>Spring</value>
                 <value>IoC</value>
              </list>
         </property>
      </bean>
    ```
    
  * Map 타입
    * ``<map>``
      * ``<entry>``
    ```xml
      <!-- Hello 클래스에 Map<String,Integer> ages; 가 있을 경우    -->
      <bean id="hello" class="~.Hello">
         <property name="ages">
              <map>  
                 <entry key="kim" value="30"/>
                 <entry key="Lee" value="20"/>
              </map>
         </property>
      </bean>
    ```
    
  * Properties 타입
    * 프로퍼티 값이 자주 바뀔수 있는 경우 그 값을 properties 파일로 분리하는 것이 좋다.
    * 방법은 그냥 key=value 형식으로 작성하면된다.
    * 프로퍼티 파일로 분리한 정보는 ``${}``을 이용하여 설정
      * 이 기능을 쓰려면 <context:property-placeholder location="classpath:프로퍼티 위치"/> 를 xml에 삽입
    ```xml
    <!--database.properties라는 파일을 만들어서 거기에 키=밸류 형식으로 값을 담아둔 상태라고 치자. -->
    <context:property-placeholder location="classpath:database.properties"/>
    
    <!-- db.driverClass 라는 키로 값을 저장해둔걸 ${}로 가져와서 쓰는 것 -->
    <bean id="dataSource" class="~.DataSource">
        <property name="driverClass" value="${db.driverClass}"/>
    </bean>
    ```
      