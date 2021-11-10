# SpringJDBC
## 데이터 액세스 공통 개념
### DAO 패턴
* 데이터 액세스 계층은 DAO패턴을 사용하여 비즈니스 로직과 데이터 액세스 로직은 분리하는 것이 원칙
* DAO 패턴은 서비스 계층에 영향을 주지않고 데이터 액세스 기술을 변경할 수 있는 장점이 있다.

### 커넥션 풀링을 지원하는 DataSource
* 커넥션 풀링이란 미리 정해진 개수만큼의 DB 커넥션을 풀(Pool)에 준비해두고, 애플리케이션이 요청할 때 마다 Pool에서 꺼내 하나씩 할당하고 사용이 끝나면 다시 돌려받아 Pool에 넣는 기법
* Spring에서는 DataSource를 공유 가능한 Spring Bean으로 등록해 주어 사용할 수 있도록 해준다.

#### Data Source 구현 클래스 종류
* 테스트 환경을 위한 DataSource
  * ``SimpleDriverDataSource``
    * 가장 단순한 DataSource 구현 클래스
    * getConnection()을 호출할 때마다 매번 DB 커넥션을 새로 만들고 따로 풀을 관리하지 않으므로 단순한 테스트용으로만 사용
  * ``SingleConnectionDriverDataSource``
    * 순차적으로 진행되는 통합테스트에서는 사용 가능(멀티쓰레드환경에선 부적합)
    * 매번 DB커넥션을 생성하지 않기 때문에 SimpleDriverDataSource보다 빠르다

#### DataSource 종류
* 오픈 소스 DataSource
  * Apache Commons DBCP
    * 가장 유명한 오픈소스 DB 커넥션 풀 라이브러리
    * setter 메소드 제공으로 Spring Bean 등록해서 사용하기 편리
  * c3p0 JDBC/DataSource Resource Pool
    * JDBC 3.0 스펙을 준수하는 Connection 과 Statement 풀을 제공하는 라이브러리
    * setter 메소드 제공으로 Spring Bean 등록해서 사용하기 편리
    