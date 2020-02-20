# Google Java Convention

소프트웨어 개발 시에 코딩 가이드를 준수하는 목적은 **다수의 개발자들이 상호간의 소스 코드에 대한 가독성 및 이해도를 높이고, 해당 표준에 따라 개발함으로써 프로젝트 품질의 일관성을 유지하여 프로젝트 완료 이후의 원활한 시스템 유지보수를 지원**할 수 있도록 하는데 있다.

---

## 소스파일 기본

- 파일이름 : 클래스 이름과 동일하게 대소문자 구별해서 작성한다. 확장자는 당연히 **java**이다.

  ex) DigitalCamera class가 구현된 소스 파일의 이름 => DigitalCamera.java

- 소스파일의 인코딩은 **UTF-8**로 통일한다.

- 공백문자는 '스페이스키'만 허용한다.

- 코드 내에서 특수 문자를 표현할 때 **'\b', '\n','\\\\'** 을 사용한다. 8진법(\012), 유니코드 표현 등을 사용하지 않는다.

- 코드의 가독성을 높일 수 있다면 유니코드를 사용해도 무방하다.

---

## 소스파일 구조

<img src="/Users/baejongjin/Library/Application Support/typora-user-images/image-20200219191124098.png" alt="image-20200219191124098" style="zoom:50%;" />

소스코드는 위 형태로 진행되어야 하며 **각 세션 사이에는 공백 라인이 하나** 들어가야한다.

```java
/*
 * Copyright (c) 1999, 2020, 저작권
 */

package 패키지;

import 패키지.클래스;

public class 클래스명 {
   
}
```

---

## 포맷팅

- **중괄호**

  - 중괄호는 본문이 비어있고 단일문만 포함되는 경우에도 반복문, 조건문과 함께 사용한다.

    - **-K & R 스타일** : 중괄호 제거가 가능해도 무조건 쓴다!

      - **여는 중괄호 뒤에는 다른 코드가 없어야 한다.**

      - **닫는 중괄호 앞에는 다른 코드가 없어야 한다.**

      - 문장에 닫는 중괄호만 있는 케이스는 함수가 끝나거나 제어문이 끝날 때이다.

        ```java
        if (fragment instanceof DialpadFragment) { 
           mDialpadFragment = (DialpadFragment) fragment; 
        } else if (fragment instanceof CallLogFragment) {
           mCallLogFragment = (CallLogFragment) fragment; }
        else if (fragment instanceof PhoneFavoriteFragment) {
           mPhoneFavoriteFragment = (PhoneFavoriteFragment) fragment; }
        else if (fragment instanceof PhoneNumberPickerFragment) {
           mSearchFragment = (PhoneNumberPickerFragment) fragment; 
        }
        
        ```

      - 하지만 코드가 없는 메소드의 경우에는 그냥 닫아도 괜찮다. (더 간결하니까)

        ```java
        void doNothing() {}
        ```

- **Line - wrapping**

  - 코드의 길이가 페이지 넓이를 넘어갈 때, 하나의 문장을 두 문장 이상으로 나눠서 표현하는 것.

  - **절대적인 법칙은 없고 상황에 따라 적절하게 **사용하면 된다.

  - 가끔은 Line-wrapping을 시도하기 전에 리팩토링을 실시하는 것이 더 올바른 선택일 수 있다.

  - 과도한 들여쓰기로 발생하는 코드의 경우 멤버 변수화 시킴으로써 해결할 수 있다.

  - Line-wrapping된 문장은 기본적으로 **2번 이상의 들여쓰기(4개의 space 이상)**를 해야한다.

  - 여러 문장이 연속해서 내려올 경우 **첫 번째 내려온 문장과 동일한 들여쓰기를 유지**한다.

  - 문장을 대입 연산자가 아닌 곳에서 잘라야 할 경우 심볼(연산자(?)) 앞에서 내린다.

    ```java
    this.someString = new StringBuffer()
       				 .append("humans")
       				 .append("are")
       				 .append("intelligent")
       				 .append("apes.");
    
    this.someString = "Humans"
       				 + "are"
       				 + "intelligent"
       				 + "Apes.";
    ```

  - 대입 연산자에서 잘라야 할 경우 대입 연산자 뒤에서 문장을 내린다.

    ```java
    private final OnPhoneNumberActionListener mPhoneNumberActionListener =
       new OnPhoneNumberActionListener() {
       
    }
    ```

  - 함수 호출의 경우 '('는 첫 문장에 두고 나머지를 다음 문장으로 내린다.

    ```java
    addContactOptionMenuItem.setIntent(
    	new Intent(Intent.Action_INERSET, Contacts.CONTENT_URL));
    ```

  - 콤마 (',')의 경우, 앞의 식별자와 동일한 단어로 취급한다.

    ```java
    public void onLayoutChange(View v, int left, int top, int right, int bottom,
                              int oldTop, int oldRight, int oldBottom) {}
    ```

- **공백처리**

  - 공백라인

    1. 클래스 멤버들을 구별하는 데 사용된다. (메소드, 생성자, 멤버변수)
    2. 메소드 내부에서 논리적으로 그룹핑 되는 부분에서 필요하다.

  - 공백문자

    1. if, while, for, swtich, catch 와 **그 다음에 오는** '(' 사이

       ```java
       if (true) {
       
       }
       ```

    2. else, catch와 그 이전에 오는 '}' 사이

       ```java
       } else }
       ```

    3. 쉼표와 세미콜론 뒤

       ```java
       for ( int i = 0; i < 10; i++) {
       	//
       }
       ```

    4. 연산자 앞 뒤. (+ 연산자와 비슷한 심볼 앞뒤)

       ```java
       float var = a + i * (4 / Math.pow(0.5, x)) - 45.0f;
       ```

- **기타**

  - Switch문

    1. Switch문에서도 들여쓰기를 적용해야 한다.
    2. 아무 처리가 없더라도 **default**는 꼭 써야한다.

    ```java
    switch (input) {
       case 1:
       case 2:
          prepareOneOrTwo();
          // fall through - 이런식으로 주석을 달아달라고 한다.
       case 3:
          handleOneTwoOrThree();
          break;
       default:
          handleLargeNumber(input);
    }
    ```

  - 들여쓰기는 스페이스키 2개로 정의한다.

  - 한 문장에는 하나의 **statement** 만을 쓴다.

  - 한 문장에는 하나의 변수 만을 선언한다.

  - **변수 선언은 함수 처음에 하지 않는다.** 최대한 변수가 사용되는 위치 근처에서 선언하여 변수의 스코프를 최소화 시킨다.

  - 한 라인의 문자는 80개 혹은 100개만 쓴다. 그 보다 길 경우 다음 라인으로 내린다.

---

## 네이밍

모든 식별자들은 ASCII와 숫자 값만 사용해야 한다. 식별자에 prefix나 suffixes는 사용하지 않는다.

- **Type 명에 대한 규칙**

  - package 명
    모두 소문자로 기술한다. 단어가 달라지더라도 무조건 소문자를 사용한다.

    ```java
    com.example.deepspace
    ```

  - class 명
    명사 혹은 명사구를 사용하고, 대문자로 시작하고 단어가 바뀌면 다시 대문자로 표시한다. ex) AccountManagerInfo
    테스트 클래스의 경우 마지막에 Test로 끝나도록 한다. ex) HashIntegrationTest

  - Method 명
    동사를 사용하고, 소문자로 시작해서 단어가 바뀌면 다시 대문자로 표시한다. ex) sendMessage
    테스트 클래스 내의 메소드의 경우 **test<테스트할 메소드>_<상태>** 이런 식으로 작성한다. ex) testPoP_emptyStack

  - 상수명
    명사나 명사구를 사용하고, 모두 대문자를 사용하여 단어 사이에 밑줄을 표시한다. (CONTACT_CASE)

  - 멤버변수명/ 인자명/ 로컬변수명
    lowerCarmelCase를 사용한다. 메소드명과 다른점은 동사가 아닌 명사라는 점. 한문자는 피하자!!

- **CamelCase란?**

  - 우선 ASCII 문자로 모두 바꾼다. [' "" '] 등의 문자를 제거한다.
  - 단어로 구별해서 단어 사이에 스페이스를 하나 둔다.
  - 각 단어의 첫 문자 외에는 모두 소문자로 바꾼다.
  - 단어들을 모두 합친다. (스페이스 제거)

---

## 프로그래밍 관례

- **@Override 는 필수**
  오버라이딩이 된 모든 경우에 @Override는 필수로 기입한다. 부모 클래스의 메소드를 재정의 하거나 인터페이스를 구현했을 때도 마찬가지이다. 다만 부모 쪽에서 @Deprecated를 선언했을 경우에는 자식 쪽에서 @Override를 생략할 수 있다.
- **예외처리**
  모든 예외는 무시하지 말고 처리한다. 만약 예외를 처리하지 않을 거라면 그 이유에 대해서 명확하게 주석을 달아야한다. 당연히 테스트 코드에서는 필요시 무시해도 된다.
- **Static 멤버 접근**
  반드시 클래스명으로 접근해야 한다.
- **Finalizers**
  - 언제 실행될 지 알 수 없다. 일반적으로 가비지 콜랙터에 의해서 호출이 되기 때문에 임의의 시점에 실해오딘다. 따라서 실행 시간이 중요한 코드는 finalizer에 두지 않는다.
  - finalizer의 실행 주기는 전적으로 JVM 구현에 달려 있어서 JVM 마다 다른 양상을 띈다.
  - 자바 언어 명세에는 finalizer의 반드시 실행될 것인지도 보장하지 않는다.
  - finalizer 수행 중에 예외가 발생하면 예외가 무시된다.
  - 엄청난 성능 저하를 일으킨다. 객체 소멸이 약 430배 느려진다고 주장된다.
  - **대안 :** 각 인스턴스에서 자신의 종료 여부를 유지 관리야 한다.

































































