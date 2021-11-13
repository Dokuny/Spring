# 빈 스코프
* 인스턴스의 생성을 한개만 할지 여러개 할지를 제어하기 위해 사용
* ``<bean>`` 엘리먼트의 ``scope``속성을 사용해 정의
### 종류
* 싱글턴(Singleton) 스코프 -> 기본값
* 프로토타입(Prototype) 스코프

### 싱글턴 
* 빈을 공유하기 위한 인스턴스를 단 하나만 만드는 것


* 싱글턴 스포크 빈은 기본적으로 사전-인스턴스화된다.(스프링 컨테이너 인스턴스를 생성할 때 싱글턴 스코프 빈의 인스턴스도 생성)
  * 지연 생성도 가능
  * ``lazy-init``속성 이용, 값이 true면 빈을 처음 요청받을 때 인스턴스를 초기화


* ``<bean>``에서 scope를 지정하지 않으면 싱글턴 스코프임.


* 싱글턴 스코프 빈 인스턴스는 그 빈에 의존하는 모든 빈 사이에서 공유된다.



* 스프링 컨테이너는 각각 본인의 싱글턴 스코프 빈 인스턴스를 갖는다.(컨테이너끼리 공유 X)
  * 빈의 영역이 단일 스프링 IoC 컨테이너로 제한되기 때문
  * 하나의 컨테이너 안에 동일한 클래스로 빈을 두개 만들면 그 두개는 서로 다른 인스턴스다.(빈 정의마다 싱글턴 스코프 빈이 하나씩 생성된다)

### 프로토타입
* 빈이 요청될 때마다 새로운 빈 인스턴스를 생성하는 것