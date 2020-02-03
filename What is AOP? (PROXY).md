# What is AOP(Aspect-Oriented Programming)

### AOP란?

**AOP는 애플리케이션 전체에 걸쳐 사용되는 기능을 재사용하도록 지원하는 것이다.** AOP란 단어를 번역하면 관점 지향 프로그래밍으로 되는데 쉽게 얘기하면 프로젝트 구조를 바라보는 관점을 **제 3자의 관점으로** 바꿔보자는 이야기입니다.

어플리케이션의 핵심적인 기능에서 부가적인 기능을 분리해서 애스팩트라는 독특한 모듈로 만들어서 설계하고 개발하는 방법을 이야기합니다.

![image-20200203134442282](/Users/baejongjin/Library/Application Support/typora-user-images/image-20200203134442282.png)

기존에 OOP에서 바라보던 관점을 다르게 하여 부가기능적인 측면에서 보았을 때 공통된 요소를 추출하자는 것입니다. 이때 가로 (횡당) 영역의 공통된 부분을 잘라냈다고 하여, AOP를 **크로스 컷팅(Cross Cutting)**이라고 불리기도 합니다.

**OOP : 비지니스 로직의 모듈화**

- 모듈화의 핵심 단위는 비즈니스 로직

**AOP : 인프라 혹은 부가기능의 모듈화**

- 대표적 예 : 로깅, 트랜잭션, 보안 등
- 각각의 모듈들의 주 목적 외에 필요한 부가적인 기능들

**AOP의 장점**

- 어플리케이션 전체에 흩어진 공통 기능이 하나의 장소에서 관리된다는 점
- 다른 서비스 모듈들이 본인의 목적에만 충실하고 그외 사항들은 신경쓰지 않아도 된다는 점

### AOP 용어

**타겟(Target)**

- 부가기능을 부여할 대상을 얘기합니다.
- 핵심 기능을 담당하는 getBoards 혹은 getUsers를 하는 Service들을 얘기합니다.

**애스팩트(Aspect)**

- 객체지향 모듈을 오브젝트라 부르는 것과 비슷하게 부가기능 모듈을 애스펙트라고 부르며, 핵심기능에 부가되어 의미를 갖는 특별한 모듈이라고 생각하면 된다.
- 애스펙트는 부가될 기능을 정의하는 어드바이스(Advice)와 어디바이스를 어디에 적용할지를 결정하는 포인트컷(PointCut)을 함께 갖고 있습니다.

**어드바이스(Advice)**

- 실질적으로 부가기능을 담은 구현체를 가리킵니다.
- 어드바이스의 경우 타켓 오브젝트에 종속되지 않기 때문에 순순하게 부가기능에만 집중할 수 있습니다.
- 어드바이스는 애스펙트가 '무엇'을 '언제'할지를 정의하고 있습니다.

**포인트컷(PointCut)**

- 부가기능이 적용될 대상(메소드)를 선정하는 방법을 얘기합니다. 즉, 어드바이스를 적용할 조인트포인트를 선별하는 기능을 정의한 모듈을 가리킵니다.

**조인포인트(JoinPoint)**

- 어드바이스가 적용될 수 있는 위치를 가리킵니다.
- 다른 AOP 프레임워크와 달리 Spring에서는 메소드 조인포인트만 제공하고 있습니다.

**프록시(Proxy)**

- 타켓을 감싸서 타켓의 요청을 대신 받아주는 랩핑(Wrapping) 오브젝트입니다.
- 호출자(클라이언트)에서 타켓을 호출하게 되면 타켓이 아닌 타켓을 감싸고 있는 프록시가 호출되어, 타켓 메소드 실행전에 전처리, 타켓 메소드 실행 후, 후처리를 실행시키도록 구성되어 있습니다.

<img src="/Users/baejongjin/Library/Application Support/typora-user-images/image-20200203142444152.png" alt="image-20200203142444152" style="zoom:50%;" />

**인트로덕션 (Introduction)**

- 타켓 클래스에 코드 변경없이 신규 메소드나 멤버변수를 추가하는 기능을 가리킵니다.

**위빙 (Weaving)**

- 지정된 객체에 애스팩트를 적용해서 새로운 프록시 객체를 생성하는 과정을 가리킵니다.
- Spring AOP는 런타임에서 프록시 객체가 생성 됩니다.

### 예제

제일 먼저 BoardService에 애스펙트르 적용해 보겠습니다.

**Performance.java**

```java
@Aspect
public class Performance {

    @Around("execution(* com.blogcode.board.BoardService.getBoards(..))")
    public Object calculatePerformanceTime(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        try {
            long start = System.currentTimeMillis();
            result = proceedingJoinPoint.proceed();
            long end = System.currentTimeMillis();

            System.out.println("수행 시간 : "+ (end - start));
        } catch (Throwable throwable) {
            System.out.println("exception! ");
        }
        return result;
    }
}
```

위와 같이 애스펙트를 선언 후, Spring 컨테이너의 Bean으로 등록하겠습니다.

**Application.java**

```java
    @Bean
    public Performance performance() {
        return new Performance();
    }
```

Bean으로 등록된 애스펙트의 프록시를 생성하고, 애노테이션을 해석할 수 있도록 설정을 추가하겠습니다.

**Application.java**

```java
@SpringBootApplication
@RestController
@EnbaleAspectJAutoProxy // 오토 프록시
public class Application implements CommandLineRunner {
   // ...
}
```

이렇게 실행을 하게 된다면 BoardServicePerformnance 없이도 수행시간이 출력되는 것을 확인할 수 있다.
여기서 중요한 것은 핵심로직을 담당하는 BoardServiceImpl과 BoardService는 전혀 변경이 없었다는 것입니다.
우리가 그토록 원했던 핵심기능과 보조기능이 함께있지만, 코드는 완전히 분리가 된 상태가 된 것입니다.

### 사용법

**@Around("execution(* com.blogcode.board.BoardService.getBoards(..))")**
**public Object calculatePerformanceTime(ProceedingJoinPoint proceedingJoinPoint) {}**  에 대해서 분석 해 보자.

- **@Around**는 **어드바이스(애스펙트가 "무엇을", "언제" 할지를 의미)**입니다. "무엇"은 calculatePerformanceTime() 메소드를 나타냅니다. 그리고 "언제"는 @Around가 되는데, 이 언제 라는 시점의 경우 @Around만 존재하지 않고 총 5가지의 타입이 존재합니다.

  - @Before(이전)
    - 어드바이스 타겟 메소드(BoardService.getBoards())가 호출되기 전에 어드바이스 기능을 수행
  - @After(이후)
    - 타켓 메소드의 결과에 관계없이(즉 성공, 예외 관계없이) 타켓 메소드가 완료 되면 어드바이스 기능을 수행
  - @AfterReturning (정상적 반환 이후)
    - 타켓 메소드가 성공적으로 결과값을 반환 후에 어드바이스 기능을 수행
  - @AfterThrowing (예외 발생 이후)
    - 타켓 메소드가 수행 중 예외를 던지게 되면 어드바이스 기능을 수행
  - @Around (메소드 실행 전후)
    - 어드바이스가 타켓 메소드를 감싸서 타켓 메소드 호출 전과 후에 어드바이스 기능을 수행

  예로들어, 타켓 메소드의 이전 시점에만 어드바이스 메소드를 수행하고 싶다면,

  ```java
  @Before("포인트컷 표현식")
  public void 어드바이스메소드() {
     // ....
  }
  ```

- 다음은 @Around 다음에 작성된 `"execution(* com.blogcode.board.BoardService.getBoards(..))"`  입니다. 어드바이스의 value로 들어간 이 문자열을 **포인트컷 표현식** 이라고 합니다. `execution`을 **지정자**라고 부르며 ` (* com.blogcode.board.BoardService.getBoards(..))`는 **타겟 명세**라고 합니다. 포인트 컷 지정자는 execution을 포함하여 총 9가지가 있습니다.

  - args()
    - 메소드의 인자가 타겟 명세에 포함된 타입일 경우
    - ex) args(java.io.Serializable) : 하나의 파라미터를 갖고, 그 인자가 Serializable 타입인 모든 메소드
  - @args()
    - 메소드의 인자가 타켓 명세에 포함된 어노테이션 타입을 갖는 경우
    - ex) @args(com.blogcode.session.User) : 하나의 파라미터를 갖고, 그 인자의 타입이 @User 어노테이션을 갖는 모든 메소드(@User User user 같이 인자 선언된 메소드)
  - execution()
    - 접근제한자, 리턴타입, 인자타입, 클래스/인터페이스, 메소드명, 파라미터타입, 예외타입 등을 전부 조합가능한 가장 세심한 지정자
    - 이전 예제와 같이 풀캐기지에 메소드명까지 직접 지정할 수도 있으며, 아래와 같이 특정 타입내의 모든 메소드를 지정할 수도 있다.
    - ex) execution(*com.blogcode.service.AccountService.\*(..)) : Account 인터페이스의 모든 메소드
  - within()
    - execution 지정자에서 클래스/ 인터페이스까지만 적용된 경우
    - 즉, 클래스 혹은 인터페이스 단위까지 범위 지정이 가능하다.
    - ex) within(com.blogcode.service.*) : service 패키지 아래의 클래스와 인터페이스가 가진 모든 메소드
    - ex) within(com.blogcode.service..) : service 아래의 모든 **하위패키지까지** 포함한 클래스와 인터페이스가 가진 메소드
  - @wthin()
    - 주어진 어노테이션을 사용하는 타입으로 선언된 메소드
  - this()
    - 터켓 메소드가 지정된 Bean 타입의 인스턴스인 경우
  - target()
    - this와 유사하지만 빈 타입이 아닌 타입의 인스턴스인 경우
  - @target()
    - 타겟 메소드를 실행하는 객체의 클래스가 타켓 명세에 지정된 타입의 어노테이션이 있는 경우
  - @annotation
    - 타켓 메소드에 특정 어노테이션이 지정된 경우
    - ex) @annotation(org.springframework.transaction.annotation.Transactional) : Transactional 어노테이션이 지정된 메소드 전부

---

## 다이내믹 프록시와 팩토리 빈

<img src="/Users/baejongjin/Library/Application Support/typora-user-images/image-20200203150552851.png" alt="image-20200203150552851" style="zoom:50%;" />

- 부가기능은 자신이 핵심기능을 가진 것처럼 꾸며서 클라이언트가 자신을 거쳐 핵심기능을 사용하도록 했다.
- 그렇게 하기 위해 인터페이스를 통해 부가, 핵심기능을 접근하도록 하였다.

이렇게 마치 자신이 클라이언트가 사용하려고 하는 실제 대상인 것처럼 위장해서 클라이언트의 요청을 받아주는 것을 대리자, 대리인과 같은 역할을 한다고 하여 **프록시(Proxy)라 부른다.** 

프록시를 통해 최종적으로 요청을 위임받아 처리하는 실제 오브젝트를 **타깃(target)** or **실체(real subject)**라고 부른다.

- 프록시의 특징은 타깃과 같은 인터페이스를 구현했다는 것과 프록시가 타깃을 제어할 수 있다는 것이다.,
- 프록시는 사용 목적에 따라 두 가지로 구분할 수 있다.
  1. 클라이언트가 타깃에 접근하는 방법을 제어하기 위해서다.
  2. 타깃에 부가적인 기능을 부여해주기 위해서이다.

<img src="/Users/baejongjin/Library/Application Support/typora-user-images/image-20200203150901645.png" alt="image-20200203150901645" style="zoom:50%;" />

### 데코레이션 패턴

- 데코레이션 패턴은 타깃에 **부가적인 기능을 런타임 시 다이내믹하기 부여해주기 위해 프록시를 사용**하는 패턴을 말한다.
- 프록시를 여러 개 쓸 수 있고 순서를 정해서 단계적으로 위임하면 된다.
- 데코레이터 패턴은 타깃의 코드에 손대지 않고, 클라이언트가 호출하는 방법도 변경하지 않은 채로 새로운 기능을 추가할 때 유용하다.

### 프록시 패턴

- **일반적으로 말하는 프록시는 클라이언트와 사용 대상 사이의 대리 역할을 맡은 오브젝트를 두는 방법이다.**
- 프록시 패턴의 프록시는 프록시를 사용하는 방법 중에서 **타깃에 대한 접근 방법을 제어**하려는 목적을 가진 경우를 말한다.
- 클라이언트가 타깃에 접근하는 방식을 변경해준다.
- 타깃 오브젝트를 필요한 시점까지 생성하지 않고 있다가 타깃 오브젝트에 대한 레퍼런스가 필요하면 프록시 패턴을 적용하면 된다.(지연 생성)
- **클라이언트에게 타깃에 대한 레퍼런스를 넘겨야 하는데 실제 타깃 오브젝트 대신 프록시를 넘긴다.**
- 해당 타깃을 사용하려고 할 떄 프록시가 타깃 오브젝트를 생성하고 요청을 위임해 주는 식이다.

### 프록시 클래스 예제

```java
interface Hello {
   String sayHello(String name);
}
```

```java
// 구현한 타깃 클래스
public class HelloTarget implements Hello {
   public String sayHello(String name){
      return "Hello" + name;
   }
}
```

```java
// 인터페이스를 구현한 프록시
public class HelloUppercase implements Hello {
   Hello hello; //위임할 타깃 오브젝트(다른 프록시 접근을 위해 인터페이스로 접근)
   
   public HelloUpperCase(Hello hello){
      this.hello = hello;
   }
   
   public String sayHello(String name) {
      return hello.sayHello(name).toUpperCase();	//위임과 부가기능 적용
   }
}
```

```java
@Test
public void simpleProxy(){
   // 클라이언트에게 타깃에 대한 레퍼런스를 넘겨야 하는데 실제 타깃 오브젝트 대신 프록시를 넘긴다. (연결만 시켜준다)
   Hello proxiedHello = new HelloUppercase(new HelloTarget());
   // 프록시 레퍼런스에 대한 Test 
   asserThat(proxiedHello.sayHello("Havi"), is("HELLO HAVI"));
}
```













