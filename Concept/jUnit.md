#jUnit
### 특징
  * TDD 창시자인 kent Beck과 디자인 패턴 책의 저자인 Erich Gammar가 작성
  * 단정(assert) 메서드로 테스트 케이스의 수행 결과를 판별한다.
    * assertEquals(예상 값,실제 값) 등
  * jUnit4 부터는 테스트를 지원하는 어노테이션을 제공한다.
    * @Test
    * @Before
    * @After
  * 각 @Test 메서드가 호출할 때 마다 새로운 인스턴스를 생성하여 독립적인 테스트가 이루어지도록 한다.

### jUnit을 사용한 DI 테스트 클래스
#### jUnit에서 테스트를 지원하는 어노테이션
  * @Test
    * @Test가 선언된 메소드는 테스트를 수행하는 메소드가 된다
    * jUnit은 각각의 테스트가 서로 영향을 주지 않고 독립적으로 실행됨을 원칙으로 하므로 @Test마다 객체를 생성
  * @Ignore
    * @Ignore가 선언된 메소드는 테스트를 실행하지 않게한다.
  * @Before
    * @Before 메소드는 @Test 메소드가 실행되기 전에 반드시 실행되어 진다.
    * @Test 메소드에서 공통으로 사용하는 코드를 @Before 메소드에 선언하여 사용하면 된다.
  * @After
    * @After가 선언된 메서드는 @Test 메소드가 실행된 후 실행된다.
  * @BeforeClass
    * @Test 메소드보다 먼저 한번만 수행되어야 할 경우에 사용하면 된다.
  * @AfterClass
    * @Test 메소드보다 나중에 한번만 수행되어야 할 경우에 사용

  
#### 테스트 결과를 확인하는 단정(assert) 메소드
* 종류
  * assertArrayEquals(a,b) : 배열 a와 b 가 일치함을 확인
  * assertEquals(a,b) : 객체 a와 b 가 일치함을 확인(값이 같은지)
  * assertNotNull(a) : 객체 a가 null이 아님을 확인
  * assertSame(a,b) : 객체 a와 b가 같은 객체임을 확인(레퍼런스가 같은지)
  * assertTrue(a) : 조건 a가 참인가를 확인
  * 위의 메소드 말고도 더 있다.

### Spring-Test를 사용한 DI 테스트 클래스
#### Spring-test 에서 테스트를 지원하는 어노테이션 
* @RunWith(SpringJUnit4ClassRunner.class)
  * @RunWith는 jUnit 프레임워크의 테스트 실행방법을 확장할 때 사용하는 어노테이션
  * SpringJUnit4ClassRunner라는 클래스를 지정해주면 jUnit이 테스트를 진행하는 중에 ApplicationContext를 만들고 관리하는 작업을 진행해준다.
  * 어노테이션은 각가의 테스트 별로 객체가 생성되더라도 싱글톤의 ApplicationContext를 보장한다.
* @ContextConfiguration
  * 스프링 빈 설정 파일의 위치를 지정할 때 사용되는 어노테이션
* @Autowired
  * 스프링 DI에서 사용되는 특별한 어노테이션
  * 해당 변수에 자동으로 빈을 매핑해준다.
  * 스프링 빈 설정 파일을 읽기위해 굳이 xml을 사용할 필요가 없다.