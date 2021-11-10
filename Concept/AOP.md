# AOP(Aspect Oriented Programing,관점 지향 프로그래밍)
* 여러 클래스에 나뉜 책임을 aspect라고 부르는 별도의 클래스에 캡슐화 하는 접근방식
* 여러 클래스에 걸쳐 있는 책임은 횡단 관심사(cross-cutting concern)이라 부른다.
* 횡단 관심사의 예로는 로깅,트랜잭션 관리,캐싱,보안 등이 있다.
* 간단히 말하면 다수의 모듈에 공통적으로 나타나는 부문을 횡단 관심사라하고 이러한 횡단 관심사 로직을 하나로 만들어 주입하는 것


## 로직을 주입하는 위치(어드바이스)
* cross-cutting concern을 구현하는 aspect의 메서드를 advice라고 부른다.
* advice 위치(종류)
  * Before : 메서드 시작 직후, core concern 시작 전
  * After returning : core concern 정상 종료 후
  * After Throwing : core concern 에서 예외가 발생하면서 종료된 후
  * Around : core concern 시작 전과 종료 후