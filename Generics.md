# Generics

## Generics을 왜 사용하는가?

> **본 문서는 parameter(매개변수)와 argument가 다름으로 이에 집중해서 봐야한다!!**

간단히 말해서, 제네릭은 클래스나, 인터페이스, 메소드를 정의할 때 타입(클래스 및 인터페이스)을 parameter가 되게 합니다. 메소드 선언에서 사용하는 친숙한 형식의 parameter와 유사하게, type parameter는 다른 입력값을 가진 같은 코드를 재사용하는 방법을 제공해 줍니다. **일반적인 매개변수의 입력은 값인 반면에 type parameter의 입력은 타입이다.**

제네릭을 사용하는 코드는 제네릭을 사용하지 않는 코드보다 많은 장점을 가지고 있다. 이는 다음과 같다.

- **컴파일 동안에 강력한 타입 검사를 한다.** (현업에서 가장 중요한 팁이다.)
  자바 컴파일러는 제네릭 코드에 강력한 타입 검사를 하고 type safety에 위반된다면 에러를 발생시킨다.
  **컴파일시 발생하는 오류를 수정하는 것이 런타임 동안에 발생하는 에러를 수정하는 것 보다 훨씬 쉽기 때문에 권장된다.**

- **캐스팅 제거**
  다음의 코드는 제네릭이 존재하지 않는 캐스팅을 요구하는 코드이다.

  ```java
  List list = new ArrayList();
  list.add("hello");
  String s = (String) list.get(0);
  ```

   제네릭을 사용해서 다시 코드를 작성하면, 캐스팅이 필요하지 않게 된다.

  ```java
  List<String> list = new ArrayList<String>();
  list.add("hello");
  String s = list.get(0);	// no cast
  ```

- **프로그래머가 제네릭 알고리즘을 구현할 수 있게 한다.**
  제네릭을 사용함으로써, 프로그래머는 다른 타입의 컬렉션에서 동작하는 제네릭 알고리즘을 구현하고 사용자 지정할 수 있으며 type safe하고 읽기 쉽습니다.

---

## Generic Types

제네릭 타입은 타입에 대해서 매개변수화 한 제네릭 클래스 또는 인터페이스 입니다. 다음의 `Box`클래스는 개념을 입증하기 위해서 수정되어질 것입니다.

### A Simple Box Class

모든 타입의 객체에서 동작하는 제네릭을 사용하지 않는 `Box` 클래스를 조사 해 보자. 이 클래스는 오직 2개의 메서드를 가진다 하나는 객체를 box에 추가하는 메소드, 다른 하나는 이러한 객체를 검색하는 메소드이다.

```java
public class Box {
	
   priavte Object object;
   
   public void set(Object object) { this.object = object;}
   
   public Object get() { return object; }
}
```

이 메소드가 `Object`를 받고 리턴하기 때문에, 원시 타입중의 하나가 아닌 경우 객체를 자유롭게 전달할 수 있습니다. 컴파일 하는 동안에 어떻게 클래스가 사용되는지에 대한 어떠한 입증된 방법도 존재하지 않습니다. box에서 코드의 한 부분에 `Integer`이 위치하고 `Integer`을 얻을수 있지만 반면에 코드의 다른 부분에 String이 건네어지면 **런타임 에러**를 발생할 것입니다.

### A Generic Version of the Box Class

제네릭 클래스는 다음과 같은 형식으로 정의되어집니다.

```java
class name<T1, T2, ..., Tn> { /*...*/}
```

**angle brackets(<>)로 구분되어진 type parameter 부분은 클래스 이름 다음에 옵니다.** 이것은 type parameter(type variables라고도 불린다) `T1`, `T2`, ..., and `Tn` 을 명시합니다.

`Box` 클래스를 제네릭으로 사용하기 위해 업데이트 하기 위해서, 개발자는 `public class Box`를 `public class Box<T>`로 바꾸어야 합니다. 클래스 내부 어디에서나 사용할 수 있는 타입 변수 `T` 에 대해서 소개합니다.

이 변경으로 `Box` 클래스는 다음과 같습니다.

```java
/* 
 * Box class의 제네릭 버젼
 */
public class Box<T> {
   // T는 "Type"을 나타낸다
   private T t;
   
   public void set(T t) { this.t = t; }
   public T get() { return t; }
}
```

너가 볼 수 있다 싶이, 모든 `Object`는 `T`로 대체 되었다. type parameter는 명시하는 모든 **non-primitive** 타입이 될 수 있다 : 모든 클래스 타입, 인터페이스 타입, 배열 타입, 심지어는 다른 타입 변수도 가능하다.

이와 동일한 기술은 제네릭 인터페이스를 만드는데에도 적용될 수 있다.

### Type Parameter Naming Conventions

**규칙으로부터, 타입 변수 이름은 한개이고, 대문자이다**. 이것은 이미 알고 있는 변수 명명 규칙과 뚜렷한 대조를 이룹니다. 이러한규칙 없이, 타입 매개변수와 일반적인 클래스 또는 인터페이스 name과의 차이를 말하는데에는 어려움이 따른다.

가장 일반적으로 타입 매개변수로 사용되어지는 name은 다음과 같다.

- E - Element (Java Collection 프레임워크에 의해서 광범위하게 사용된다.)
- K - Key
- N - Number
- T - Type
- V - Value
- S, U, V etc. - 2nd, 3rd, 4th types

Java SE API 와 다른 document 전체에서 이러한 이름이 사용되어지는 것을 볼 수 있을 것입니다.

### Invoking and Instantiating Generic Type

**코드로 부터 제네릭 `Box` 클래스를 참조하기 위해서, `T` 값을 구체적인 `Integer`과 같은 값으로 대체하는 제네릭 타입 호출을 사용해야만 합니다.**

```java
Box<Integer> integerBox;
```

일반적인 메소드 호출과 비슷하게 제네릭 타입 호출을 생각할 수도 있습니다. 그러나 메소드에 인자를 건내주는 대신에 **type argument** (Integer과 같은)을 주어야 합니다.

> **Type Parameter and Type Argument Terminology**
>
> 많은 개발자들은 "Type parameter" 과 "Type argument"를 상호교환할 수 있다고 생각하고 실제로 사용한다. 그러나 이러한 용어들은 동일하지 않다. 코딩할 때 type argument를 매개변수화된 타입을 생성하기 위해서 제공한다. 그러므로 **`Foo<T>`의 `T`는 type parameter이고 `Foo<String>`의 `String`은 type argument이다.** 이 document에서는 이러한 용어들을 사용할 때 이러한 정의를 준수한다.

**다른 모든 변수 선언과 같이, 이러한 코드는 실제로 `Box` 객체를 새로 생성하지 않는다. `integerBox`가 `Box<Integer>`를 읽는방법인 Box of Integer에 대한 참조를 보유한다고 선언합니다.** 

제네릭 타입의 호출은 일반적으로 매개변수화된 타입이라고 알려져 있습니다.

이러한 클래스를 인스턴스화 하기 위해서 `new` 키워드를 사용해야 하고 클래스 이름과 괄호(parthensis)사이에 `<Integer>`를 위치시켜야 한다.

```
Box<Integer> integerBox = new Box<Integer>();
```

Java SE7 부터 클래스 인스턴스화 하기 위해서 다음과 같이 <>만 사용할 수 있습니다.

```java
Box<Integer> integerBox = new Box<>();
```

다음과 같은 **Multiple Type parameters**도 가능하다.

```java
public interface Pair<K, V> {
   public K getKey();
   public V getValue();
}

public class OrderedPair<K, V> implements Pair<K, V> {
   private K key;
   private V value;
   
   public OrderedPair(K key, V value){
      this.key = key;
      this.value = value;
   }
   
   public K getKey() { return key;}
   public V getValue() { return value;}
}
```

```
Pair<String, Integer> p1 = new OrderedPair<String, Integer>("Even", 8);
Pair<String, String> p1 = new OrderedPair<String, String>("Even", "world");

Pair<String, Integer> p1 = new OrderedPair<>("Even", 8);
Pair<String, String> p1 = new OrderedPair<>("Even", "world");
```

### Parameterized Types

type parameter(ex. `k` or `v`)을 매개변수화 된 타입(ex. `List<String>`)으로 대체 할 수 있습니다.
다음의 예제는 `OrderedPair<K, V>`를 사용하는 예제이다.

```java
OrderedPair<String, Box<Integer>> p = new OrderedPair<>("primes", new Box<Integer>(...));
```

---

## Raw Types

**raw type은 어떠한 type arguments가 존재하지 않는 제네릭 클래스 또는 인터페이스를 가르킨다.** 
예로들어, 주어진 제네릭 `Box` 클래스가 다음과 같을 때

```java
public class Box<T> {
   public void set(T t) { /* ... */}
   //...
}
```

`Box<T>`에 대한 매개변수화된 타입을 생성하기 위해선, 실제 type argument를 공식적인 type parameter `T`를 위해서 제공해주어야 한다.

```java
Box<Integer> intBox = new Box<>();
```

만약 실제 type argument가 생략되었다면, `Box<T>`에 대한 raw type을 생성하는 것이다.

```java
Box rawBox = new Box<>();
```

그러므로, `Box`는 제네릭 타입 `Box<T>`의 raw type이다. 하지만 제네릭이 아닌 클래스 또는 인터페이스 타입은 raw type이 아니다.

JDK 5.0 이전에는 Collection classes와 같은 많은 API 클래스들이 제네릭이 아니였기 때문에 Raw types는 레거시 코드에 표시되었다. raw types를 사용할 때, 제네릭의 이전 동작이 발생합니다. `Box`는 `Object`s를 제공하고. 이전 버전과의 호환성을 위해서, 매개 변수화된 type을 해당 raw type에 할당할 수 있습니다.

```java
Box<String> stringBox = new Box();
BOx rawBox = stringBox;	// OK
// 매개 변수화된 type을 해당 raw type에 할당하는건 가능하다.
```

그러나 만약 raw type을 매개변수화된 type에 배정한다면, Java는 warning을 보낼것이다.

```java
Box rawBox = new Box();			// rawBox is a raw type of Box<T>
Box<Integer> intBox = rawBox;	// warning: unchecked conversion (중요)
```

raw type을 사용하는 것을 피해야 한다.

Type Erasure 부분에서 어떻게 Java compiler가 raw types를 사용하는지에 대해서 보다 더 많은 설명을 제공한다.

### Uncheckd Error Messages

이전에 언급 했듯이, 제네릭 코드를 가진 레거시 코드를 혼합할 때, 아마 다음과 비슷한 예상치 못한 에러가 발생 될 것이다.

```
Note: Example.java uses unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.
```

**"unchecked" 라는 단어는 컴파일러가 type safety를 확실히 하기 위해 필요한 모든 타입 체크를 수행하는데 필요한 타입 정보가 충분하지 않다는 것을 의미한다. 컴파일러가 힌트를 제공하지만 기본적으로 "unchecked" 경고는 비활성화되어 있습니다. 모든 "unchecked" 경고를 보려면, -Xlint:unchecked로 다시 컴파일 하십시요.**

`-Xlint:unchecked`로 이전의 예제를 다시 컴파일하면 다음의 추가적인 정보를 보여줍니다.

```
WarningDemp.java:4: warning: [unchecked] unchecked conversion
found		: Box
required	: Box<java.lang.Integer>
			bi = createBox();
								^
1 warning
```

unchecked warnings를 완전히 비활성화 하고 싶다면, `-Xlint:-unchecked` flag를 사용해라. `@SuppressWarnings("unchecked")` 애노테이션은 unchecked warnings를 금지할것입니다(suppresses).

---

## Generic Methods

**제네릭 메소드는 자신의 type parameters를 도입하는 메소드입니다. 제네릭 타입을 선언하는 것과 비슷하지만, type parameter의 범위는 선언되어진 범위에 국한됩니다.** Static 또는 non-static 제네릭 메소드가 허용될 뿐만 아니라 제네릭 클래스 생성자도 허용됩니다.

**제네릭 메소드를 위한 문법은 type parameters에 대한 리스트, 메소드의 리턴타입 이전에 나타나는 inside angle brackets(<>)를 포함합니다. 정적인 제네릭 메소드를 위해, type parameter 부분은 메소드의 리턴타입 이전에 나타내 집니다.**

`Util` 클래스는 2개의 `pair` 객체들을 비교하는 제네릭 메소드인 `compare`을 포함한다.

```java
public class Util {
   public static <K, V> boolean compar(Pair<K, V> p1, Pair<K, V> p2){
      return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
   }
}

public class Pair<K, V> {
   
   private K key;
   private V value;
   
   public Pair(K key, V value) {
      this.key = key;
      this.value = value;
   }
   
   public void setKey(K key) { this.key = key; }
   public void setValue(V value) { this.value = value; }
   public K getKey() { return key; }
   public V getValue() { return value; }
}
```

이러한 메소들르 호출하기 위한 완벽한 문법은 다음과 같다. (처음 본다 매우 중요하다 !! 한번 머리 속에 넣어 보자)

```java
Pair<Integer, String> p1 = new Pair<>(1, "apple");
Pair<Integer, String> p2 = new Pair<>(1, "pear");
boolean same = Util.<Integer, String>compare(p1, p2);
```

굵게 표시된 부분에서 보여지듯이 타입이 명백히 제공되었습니다. 일반적으로 이러한 것들은 생략할(left out) 수 있으며, 컴파일러는 필요한 타입을 유추할 수 있습니다.

```java
Pair<Integer, String> p1 = new Pair<>(1, "apple");
Pair<Integer, String> p2 = new Pair<>(1, "pear");
boolean same = Util.compare(p1, p2);
```

타입 추론(inference)라고 불리는 이러한 기능은 제네릭 메소드를 평범한 메소드처럼 angle brackeys(<>)사이에 타입을 명시하는 것 없이 호출할 수 있도록 허락 해 줍니다. 다음 섹션에서 Type inference에 대해서 좀 더 논의를 진행 해 보겠습니다.

---

## Bounded Type parameters

**매개변수화된 타입에서 type arguments로써 사용하기 위한 타입을 제한하기 원하는 경우가 있습니다. 예로들어, 숫자에서 작동하는 메서드는 Number에 대한 인스턴스 또는 해당 서브클래스만 허용하려고 합니다. 이것이 `bounded type parameters`의 대상입니다.**

bounded type parameters를 선언하기 위해서, type parameter's 이름을 나열하고 `extends` 키워드와 상한(upper bound)을 나열합니다. 이 예제에서는 `Number ` 입니다. 이 문맥에서 extends는 일반적으로 클래스에서 "extends"의미로 또는 인터페이스에서 "implements"의미로 사용되어집니다.

```java
public class Box<T> {
   
   private T t;
   
   public void set(T t) {
      this.t = t;
   }
   
   public T get() {
      return t;
   }
   
   public <U extends Number> void inspect(U u) {
      System.out.println("T: " + t.getClass().getName());
      System.out.println("U: " + u.getClass().getName());
   }
   
   public static void main(String[] args) {
      Box<Integer> integerBox = new Box<Integer>();
      integerBox.set(new Inteher(10));
      integer.inspect("some test");	// error : this is still String!
   }
}
```

우리의 제네릭 메소드를 bounded type parameter를 포함하기 위해 수정함으로써, 여전히 String을 포함하는 inspect를 호출했기 때문에 컴파일러는 지금 실패할 것이다.

```
Box.java:21: <U>inspect(U) in Box<java.lang.Integer> cannot
	be applied to (java.lang.String)
								integerBox.inspect("10");
											 ^
1 error
```

**제네릭 타입을 인스턴스화 하기 위해 사용할 수 있는 타입을 제한하는것 외에도, bounded type parameters는 bounds 에서 정의된 메소드를 호출하는 것을 허락한다.**

```java
public class NaturalNumber<T extends Integer> {
	
   private T n;
   
   public NaturalNumber(T n) { this.n = n;}
   
   public boolean isEven() {
      return n.intValue() % 2 ==0;	// bound에 정의된 메소드 : Integer.intValue
   }
   //...
}
```

`isEven` 메소드는  n을 통해 `Integer` 클래스에 정의된 `intValue` 메소드를 호출한다.

### Multiple Bounds

이전 예제에서는 단일 bound와 함께 사용되는 type parameter에 대해 묘사했다. 그러나 type parameter는 여러 bounds를 가질 수 있다.

```java
<T extends B1 & B2 & B3>
```

여러 bounds를 포함하는 type variable는 bound에 표시된 모든 타입 리스트의 서브타입이다. **만약 bounds중 하나가 클래스라면 이러한 것들은 첫 번째로 표시되어야만 한다** 예를 들어

```java
class A { /* ... */ }
interface B { /* ... */ }
interface C { /* ... */ }

class D <T extends A & B & C> { /* ... */}

// 만약 Bound A 가 첫번째로 명시되어 있지 않다면, compile-time 에러가 발생될 것이다.
class D <T extends B & A & C> { /* ... */ } // compile-time error
```

---

## Generic Methods and Bounded Type Parameters

제네릭 알고리즘을 구현하기 위해서 Bounded type parameters는 중요하다. 배열 `T[]`에서 명시된 요소 `elem`보다 큰 요소의 숫자를 세는 다음의 메소드를 고려 해 봐라

```java
public static <T> int counterGreaterThan(T[] anARray, T elem) {
	int count = 0;
	for (T e : anArray)
		if (e > elem) // compiler error
         ++count;
   return count;
}
```

**메소드의 구현은 간단하지만(straightforward), 그러나 비교 연산자(>)는 오직 primitive 타입인 shor, int, double, long, float, byte, char 에만 적용되어질 수 있기 때문에 컴파일 에러가 발생합니다.** 객체를 비교는 비교 연산자(>)를 사용할 수 없습니다. 이러한 문제를 고치기 위해서, `comparable<T>` 인터페이스에 의해 bounded 되어진 type parameter를 사용하면 됩니다.

```java
public interface Comparable<T> {
	public int compareTo(T o);
}
```

결과 코드는 다음과 같습니다.

```java
public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
   int count = 0;
   for (T e : anArray)
      if(e.compareTo(elem) > 0 )
         ++count;
   return count;
}
```

---

## Generics, Inheritance, and Subtypes

이미 알고 있듯이, **유형이 호환되는 경우 한 유형의 객체를 다른 유형의 객체에 할당 할 수 있습니다**. 예로들어, Object는 Integer의 슈퍼타입이기 때문에 Integer를 Object에 할당할 수 있습니다.

```
Object someObject = new Object();
Integer someInteger = new Integer(10);
someObject = someInteger;	// OK
```

객체 지향 언어에서, 이러한 것들을 "is a" 관계라고 부릅니다. Integer는 Object의 한 종류이기 때문에 이러한 할당이 가능합니다. 그러나 Integer 또한 Number의 한 종류 이기 때문에, 다음의 코드 또한 유효합니다.

```java
public void someMethod(Number n) { /* ... */ }

someMethod(new Integer(10));	// OK
someMethod(new Double(10.1));	// OK
```

제네릭도 마찬가지 입니다. 개발자는 해당 type argument로써 Number을 전달하면서 제네릭 타입 호출을 수행할 수 있습니다. 인수가 Number와 호환되는 경우 이후의(subsequent) add 호출이 허용됩니다.

```java
Box<Number> box = new Box<Number>();
box.add(new Integer(10));	//OK
box.add(new Double(10.1));	//OK
```

다음의 메소드를 고려해 보아라.

```
public void boxTest(Box<Number> n) { /* ... */ }
```

어떤 타입의 인자를 수용할 수 있는가? 해당 signature(메소드 이름 + 인자)를 보면, 이 메소드는 타입이 `Box<Number>`인 단일 인자를 수용할 수 있다는 것을 볼 수 있다. 그러면 이러한 것들이 무엇을 의미하는가? **예상대로 `Box<Integer>` 또는 `Box<Double>`을 전달할 수 있습니까? 정답은 "no" 이다. 왜냐하면 `Box<Integer>` 과 `Box<Double>`은 `Box<Number>`의 서브타입이 아니기 때문이다.**

이러한 것들은 제네릭을 활용한 프로그래밍에 관한 일반적이 오해이고 꼭 배워야할 중요한 개념이다.

<img src="/Users/baejongjin/Library/Application Support/typora-user-images/image-20200216193859286.png" alt="image-20200216193859286" style="zoom:50%;" /> 

> **Note**
>
> 구체적인 타입 A 와 B(예로들어, `Number` and `Integer`)가 주어졌다면 `MyClass<A>`와 `MyClass<B>`는 `A` 와 `B`가 관계가 있는지와는 관계없이 어떠한 관계가 존재하지 않는다. **일반적으로 `MyClass<A>` 와 `MyClass<B>`의 부모는 `Object` 이다.**
>
> type parameters가 관계가 있을 때 2개의 제네릭 클래스 사이에 서브타입과 같은 관계를 생성하는 방법에 대한 정보를 얻고 싶다면, Wildcards and Subtyping을 참고해라

### Generic Classes and Subtyping

**만약 이러한 클래스를 구현하거나 상속함으로써 제네릭 클래스 또는 인터페이스를 서브타입화 할 수 있다.** 한 클래스 또는 인터페이스의 type parameters와 다른 type parameters 사이의 관계는 extends 및 implements 절(clauses)에 의해서 결정됩니다.

`collections` 클래스들을 예제로써 사용하자면, `ArrayList<E>`는 `List<E>` 구현하고 `List<E>`는 `Collection<E>`를 상속한다. 그래서 `ArrayList<String>`은 `Collection<String>` 의 서브타입인 `List<String>`의 서브타입 입니다. type argument를 바꾸지(vary) 않는 한(so long as), 타입 사이의 서브 타입 관계는 유지되어 집니다.

<img src="/Users/baejongjin/Library/Application Support/typora-user-images/image-20200216200014433.png" alt="image-20200216200014433" style="zoom:50%;" />

사용자 리스트 인터페이스인 `PayloadList`를 정의하기 원한다면 상상해 보아라. `PayloadList`는 제네릭 유형 `P`의 선택적 값을 각 요소와 연관시킨다. 해당 선언은 다음과 같다.

```java
interface payloadList<E, P> extends List<E> {
	void setPayload(int index, P val);
	//...
}
```

`PayloadList`의 다음 매개 변수화는 `List<String>`의 서브타입 입니다.

- Payload<String, String>
- Payload<String, Integer>
- Payload<String, Exception>

<img src="/Users/baejongjin/Library/Application Support/typora-user-images/image-20200216200804499.png" alt="image-20200216200804499" style="zoom:50%;" />

---

## Type Inference

**타입 추론은 호출에 적용할 수 있는 type argument( 또는 argument )를 결정하기 위해 각 메소드를 호출 및 선언을 보는 컴파일러의 능력입니다.** 호출 알고리즘은 인수의 유형과 (사용 가능한 경우) 결과가 지정되거나 리턴되는 유형을 결정합니다. **결국 추론 알고리즘은 모든 arguments와 같이 동작할 수 있는 가장 특정한 타입을 찾기위해 노력합니다.**

이 마지막 점을 설명하기 위해 예제에서 추론은 `pick` 메소드에 전달되어지는 두 번째 argument가 `Serializable` 유형인 것으로 판단합니다. (좀 더 이해가 필요하다)

```java
static <T> T pick(T a1, T a2) { return a2; }
Serializable s = pick("d", new ArrayList<String>());
```

### Type Inference and Generic Methods

**Generic Methods에서는 타입 추론에 관해서 설명 했습니다. 타입 추론은 angle brackets(<>)사이에 타입을 명시하지 않고 일반 메서드와 마찬가지로 제네릭 메서드를 호출할 수 있도록 합니다.** 다음의 예제인 `Box` 클래스를 요구하는 `BoxDemo`에 대해서 고려 해 보아라.

```java
public class BoxDemo {
	
   public static <U> void addBox(U u, java.util.List<Box<U>> boxes) {
      Box<U> box = new Box<>();
      box.set(u);
      boxes.add(box);
   }
   
   public static <U> void outputBoxes(java.util.List<Box<U>> boxes) {
      int counter = 0;
      for(Box<U> box: boxes) {
         U boxContents = box.get();
         System.out.println("Box #" + counter + " contains [" + boxContents.toString() + "]");
         counter++;
      }
   }
   
   public static void main(String[] args) {
      java.util.ArrayList<Box<Integer>> listOfIntegerBoxes = new java.util.ArrayList<>();
      BoxDemo.<Integer>addBox(Integer.valueOf(10), listOfIntegerBoxes);
      BoxDemo.addBox(Integer.valueOf(20), listOfIntegerBoxes);
      BoxDemo.addBox(Integer.valueOf(30), listOfIntegerBoxes);
      BoxDemo.outputBoxes(listOfIntegerBoxes);
   }
}
```

이 예제의 출력 결과는 다음과 같다.

```
Box #0 contains [10]
Box #1 contains [20]
Box #2 contains [30]
```

제네릭 메소드인 `addBox`는 이름이 U인 type parameter 하나를 정의한다. 일반적으로, 자바 컴파일러는 제네릭 메소드 호출에 대한 type parameter을 유추할 수 있습니다. 결과적으로 대부분의 경우에, 개발자는 type arguments들을 명시해 줄 필요가 없습니다. 예로들어, 제네릭 메소드인 addBox를 호출하는 경우, 개발자는 다음과 같이 타입 witness와 함께 type parameter를 명시할 수 있습니다.

```java
BoxDemo.<Integer>addBox(Integer.valueOf(10), listOfIntegerBoxes));
```

대안적으로 만약 type witness를 생략 했다면, 자바 컴파일러는 메소드 인자로부터 type parameter인 `Integer`를 자동으로 유추합니다.

```java
BoxDemo.addBox(Integer.valueOf(20), listOfIntegerBoxes);
```

### Type Inference and Instantiation of Generic Classes

**문맥으로부터 type arguments가 컴파일러에 의해서 추론될 수 있는 한 <u>제네릭 클래스의 생성자를 호출</u>하기 위해 요구되어지는 type arguments를 type parameters의 빈 세트(<>)로 대체할 수 있습니다.** angle brackets의 쌍은 비공식적으로 diamond라고 불리웁니다.

예로들어, 다음의 변수 선언에 대해서 고려해 보아라.

```java
Map<String, List<String>> myMap = new HashMap<String, List<String>>();
```

개발자는 타입 매개변수의 일련의 빈 세트로(<>) 생성자의 매개변수화된 타입을 대체할 수 있습니다.

```
Map<String, List<String>> myMap = new HashMap<>();
```

**제네릭 클래스 인스턴스 동안에 타입 추론을 사용할 때, 개발자는 틀림없이 diamond(<>)를 사용해야 합니다.** 다음의 예제에서 컴파일러는 HashMap() 생성자가 Map<String, List\<String>>타입이 아닌 <u>HashMap raw type을 참조</u>하기 때문에 unchecked conversion warining을 발생시킵니다.

```java
Map<String, List<String>> myMap = new HashMap(); // unchecked conversion warning
```

### Type Inference and Generic Constructors of Generic and Non-Generic Classes

**제네릭 및 non-제네릭 클래스에서 생성자는 제네릭(즉, 자체 type 매개변수 선언)이 될 수 있습니다.** 다음 예제를 고려 해 보자.

```java
class MyClass<X> {
	<T> MyClass(T t) {
      // ...
   }
}
```

다음의 MyClass에 대한 인스턴스화를 고려하면

```
new MyClass<Integer>("")
```

이러한 명령문은 매개변수화된 유형 `MyClass<Integer>`의 인스턴스를 생성합니다. 이 명령문은 공식적인 type parameter인   제네릭 클래스 `MyClass<X>`의 X를 위해서 타입 `Integer`를 명백히 명시하였습니다. 이 제네릭 클래스의 생성자는 공식적인 타입 매개변수 `T`를 포함합니다. 컴파일러는 공식적인 type parameter인 T 즉, 해당 제네릭 클래스의 생성자에 대한 T를 위해서 String 타입을 추론합니다.

Java SE 7 이전에 출시된 컴파일러는 제네릭 생성자와 비슷하게 제네릭 메소드의 실제 type parameters도 유추할 수 잇었습니다. 그러나 Java SE 7 이상의 컴파일러는 diamond(<>)를 사용하면 인스턴스화 되어지는 제네릭 클래스의 실제 type parameter를 추론할 수 있습니다. 다음 예제를 고려해 보아라.

```java
MyClass<Integer> myObject = new MyClass<>("");
```

이 예제에서, 컴파일러는 제네릭 클래스 `MyClass<X>`의 X인 공식적인 type parameter를 위해서 Integer 타입을 추론합니다. 

> **Note**
>
> **추론 알고리즘은 오직 arguments 호출, 타겟 타입, 타입을 유추하기 위한 명백한 리턴 타입에만 사용된다는 것을 명심해라!!** 추론 알고리즘은 나중의 프로그램의 결과를 사용하지 않습니다.

### Target Types

자바 컴파일러는 제네릭 메소드 호출의 type parameters를 추론하기 위해 target typing을 사용한다. 표현식의 target type은 표현식이 나타나는 위치에 의존해서 컴파일러가 예상하는 데이터 타입 입니다. 다음에 선언되어지는 Collections.emptyList 메소드에 대해서 고려해 보자.

```java
static <T> List<T> emptyList();
```

다음의 예제를 고려 해 보아라.

```java
List<String> listOne = Collections.emptyList();
```

위 명령어는 `List<String>` 인스턴스를 예측한다; 이 데이터 타입은 **target type**이다. <u>**emptyList는 `List<T>` 타입의 값을 리턴하는 메소드 이기 때문에, 컴파일러는 type arugment `T` 가 String 값이 무조건 되도록 한다.**</u> 이러한 기능은 Java SE 7 과 SE 8에서 동작한다. 대안적으로 개발자는 다음과 같이 type witness를 사용 할 수 있고 T 값을 명시할 수 있다. 

```java
List<String> listOne = Collections.<String>emptyList();
```

그러나, 이러한 것들은 이 문맥에서 필요하지 않다. 이러한 것들은 다른 문맥에서 필요하다 다음의 메소드를 고려 해 보자.

```java
void processStringList(List<String> stringList) {
	// process stringList
}
```

empty 리스트와 함께 processStringList 메소드를 호출하길 원한다고 가정 하자. Java SE 7 에서, 다음의 명령어는 컴파일 되지 않는다.

```java
processStringList(Collections.emptyList());
```

Java SE 7 컴파일러는 다음과 비슷한 에러 메세지를 발생시킨다.

```
List<Object> cannot be converted to List<String>
```

<u>컴파일러는 type argument T를 위한 값을 필요로 하므로 Object 값으로 시작한다.</u> 결과적으로 Collections.emptyList의 호출은 `List<Object>` 타입의 값을 호출한다. 이러한 것들은 prcessStringList와 호환되지 않는다. 그러므로 Java SE 7 에서, 개발자는 다음과 같이 type argument의 값에 대한 값을 명시해야만 한다.

```java
processStringList(Collections.<String>emptyList());
```

**이러한 것들은 Java SE 8에서 더이상 필요 없다. target type의 개념은 processStringList 메소드에 대한 argument와 같은 메소드 argument를 포함하도록 확장 되었습니다.** 이러한 경우에서, processStringList는 `List<String>` 타입에 대한 인자를 포함한다. Collections.emptyList 메소드는 `List<T>`에 대한 값을 리턴하고 `List<String>`에 대한 타겟 타입을 사용하면, <u>컴파일러는 type argument T가 String값을 가지도록 추론한다.</u> 그러므로 자바 8에서는, 다음의 명령문을 컴파일한다.

```java
processStringList(Collections.emptyList());
```

---

## Wildcards

**제네릭 코드에서, 물음표 마크(?)는 알지 못하는 타입을 표현하는 wildcard라고 불리운다. 와일드 카드는 type parameter, filed, local variable과 같은 다양한 상황에서 사용되어질 수 있다.** 때때로는 리턴타입으로써(보다 구체적으로 작성하는 것이 더 나은 프로그래밍 관행일지라도) 사용되어질 수 있다. 와일드 카드는 제네릭 메소드 호출을 위한 type argument, 제네릭 클래스 인스턴스 생성, 서브타입을 위해서 사용되어질 수 없다.

다음의 섹션은 upper bounded 와일드 카드, lower bounded wildcards, wildcard capture를 포함해서 좀 더 자세한 와일드 카드에 대해서 논의할 것이다.

---

## Upper Bounded Wildcards

**개발자는 변수에 대한 제한을 완화하기위해 <u>upper bounded 와일드 카드</u>를 사용할 수 있습니다.** 예로들어, `List<Integer>`, `List<Double>`, `List<Number>`에서 동작하는 메소드를 작성하고 싶다면, 개발자는 **upper bounded wildcard**를 사용함으로써 이러한 바람을 이룰 수 있습니다.

**upper-bounded wildcard를 선언하기 위해서, 와일드 카드 문자('`?`') 뒤에 `extends` 키워드 뒤에 해당 `upper bound` 를  사용하면 된다. 이 문맥에서 `extends`는 일반적으로 클래스에서 extends 또는 인터페이스에서는 implements의 의미로 사용되어 집니다.**

`Number` 의 리스트와 `Number`의 서브타입인 `Integer`, `Double`, `Float` 에서 동작하게 메소드를 작성하기 위해서는, `List<? extends Number>`를 명시할 수 있습니다. `List<Number>` 용어는 `List<? extends Number>`보다는 더 제한적입이다. 왜냐하면 앞에 있는 `List<Number>`은 오직 `Number` 타입의 리스트만 일치하고 후자는 `Number` 또는 `Number의 서브 클래스 모두의 리스트`에 일치합니다.

다음의 `process` 메소드를 고려 해 보아라

```
public static void process(List<? extends Foo> list) { /* ... */}
```

upper bounded wildcard, `<? extends Foo>, ` 여기서 Foo는 Foo 와 Foo의 모든 서브타입을 의미한다. **`process` 메소드는 <u>Foo 타입으로써 리스트 요소에 접근할</u> 수 있다.**

```java
public static void process(List<? extnds Foo> list) {
	for (Foo elem : list) {
      // ...
   }
}
```

**`foreach` 절에서, elem 변수는 리스트의 각 요소를 반복합니다.** `Foo` 클래스에서 정의된 모든 메소드들은 이제 elem에서 사용되어질 수 있습니다.

`sumOfList` 메소드는 리스트에서 숫자의 합을 리턴합니다.

```java
public static double sumOfList(List<? extends Number> list){
	double s = 0.0;
   for (Number n : list)
      s += n.doubleValue();	//doubleValue는 Number 클래스에서 정의된 메소드이다.
   return s;
}
```

다음의 코드는 `Integer` 객체의 리스트를 사용해서, `sum = 6.0`을 프린트합니다.

```java
List<Integer> li = Array.asList(1,2,3);
System.out.println("Sum = " + sumOfList(li));
```

`Double` 값의 리스트는 같은 `sumOfList` 메소드를 사용할 수 있습니다. 다음의 코드는 `sum = 7.0`을 프린트 합니다.

```java
List<Double> ld = Arrays.asList(1.2, 2.3, 3.5);
System.out.println("sum = " + sumOfList(ld));
```

---

## Unbounded Wildcards

unbounded wildcard 타입은 와일드 카드인( '`?`' )를 사용해서 명시되어진다. 예로들어, `List<?>`와 같다. 이것은 알려지지 않은 타입의 리스트라고 불린다. unbounded wildcard가 유용한 접근법인 2가지의 시나리오가 존재합니다.

- **구현되어지는 메소드가  `Object` 클래스에서 제공되어지는 기능을 사용하는 경우**
- **type parameter에 의존하지 않는 제네릭 클래스의 메소드를 사용하는 코드의 경우**
  예로들어, `List.size` 또는 `List.clear`이다. 실제로 `Class<T>`의 대부분 메소드가 `T`에 의존하지 않기 때문에 `Class<?>`가 자주 사용됩니다. 

다음의 `printList` 메소드를 고려 해 보자.

```java
public static void printList(List<Object> list) {
	for(Object elem : list)
      System.out.println(elem + " ");
   System.out.println();
}
```

`printList` 의 목표는 모든 타입의 리스트를 프린트 하는것이다. 그러나 이 `printList`는 오직 `Object` 인스턴스의 리스트만 프린트하고 이것은 `List<Integer>`, `List<String>`, `List<Double>`, ...을 프린트 하지 못한다. 왜냐하면 `List<Integer>`, `List<String>`, `List<Double>`은 `List<Object>`의 서브타입이 아니기 때문이다. 제네릭  `printList` 메소드를 사용하기 위해서, `List<?>`를 사용해야한다.

```java
public static void printList(List<?> list) {
	for (Object elem: list)
      System.out.print(elem + " ");
   System.out.println();
}
```

구체적인 타입 A에 대해서 `List<A>`는 `List<?>`의 서브타입이기 때문에, 개발자는 `printList`를 사용해서 모든 타입의 리스트를 프린트 할 수 있습니다.

```java
List<Integer> li = Array.asList(1,2,3);
List<String> ls = Array.asList("one", "two", "three");
printList(li);
printList(ls);
```

> **Note**
>
> Arrays.asList 메소드는 전체 문서의 예제에서 사용되어진다. 이 정적 팩토리 메소드는 특정한 배열을 변환해 정해진 사이즈의 리스트를 리턴한다.

`List<Object>` 와 `List<?>` 는 같지 않다는 것을 명심하는 것이 중요하다. 개발자는 `Object` 또는 `Object`의 서브타입을 `List<Object>`에 삽입할 수 있다. 그러나 `null`은 오직 `List<?>` 에만 넣을 수 있다. 주어진 상황에서 어떠한 종류의 와일드 카드를 결정하는지에 대한 더 많은 정보는 Guidelines for Wildcard Use 부분에 나와있다.

---

## Lower Bounded Wildcards

**Upper Bounded Wildcards 부분은 알려지지 않는 타입(Unknown Type)을 특정한 타입 또는 해당 타입의 서브타입이 되도록 제한하는 것을 `extends` 키워드를 사용해서 표현함으로써 보여준다.** 비슷한 방법으로, **lower bounded wildcard는 알려지지 않은 타입(Unknown Type)이 특정한 타입 또는 해당 타입의 슈퍼타입이 되도록 제한합니다.**

lower bounded wildcard는 와일드 카드 문자 ( '`?`' ) 다음에 super 키워드 다음에 해당 lower bound 가 위치함으로써 `<? super A>` 와 같이 표현된다.

> **Note**
> 개발자는 와일드 카드를 위해서 upper bound 명시할 수 있거나 또는 lower bound를 명시할 수 있고, 둘 다 명시하지 않아도 된다.

`Integer` 객체를 리스트에 두게끔 메소드를 사용하고 싶다면. 융통성을 최대화 하기 위해, 개발자는 `List<Integer>`, `List<Number>`, `List<Object>`, `Integer` 값을 포함할 수 있는 무언가에서 동작하는 메소드에서 작동하려고 합니다.

**메소드가 `Integer` 리스트와 `Integer`의 슈퍼타입(`Integer`, `Number`, `Object`)에서 동작하도록 작성하기 위해 개발자는 `List<? super Integer>`를 명시해야 한다.** `List<Integer>`는 `List<? super Integer>` 보다 더 제한적이다 왜냐하면 전자는 오직 `Integer` 타입의 리스트에만 일치하지만 반면에 후자는 `Integer`의 슈퍼타입의 모든 타입의 리스트들과 일치한다.

다음의 코드는 1부터 10까지의 숫자를 리스트에 추가하는 코드이다.

```java
public static void addNumbers(List<? super Integer> list) {
   for(int i = 1; i<=10; i++)
      list.add(i);
}
```

Guidelines for Wildcard Use 부분에서 upper bounded 와일드 카드와 lower bounded wildcards를 사용할 시기에 대한 지침을 제공합니다.

---

## Wildcards and Subtyping

제네릭, 상속, 서브타입에서 설명 된 것처럼, 제네릭 클래스들 또는 인터페이스들은 관련이 없습니다. 그러나 개발자는 제네릭 클래스들과 인터페이스 간에 관계를 생성하기 위해서 와일드 카드를 사용할 수 있습니다.

주어진 2개의 일반 (제네릭이 아닌) 클래스들은 다음과 같습니다.

```java
class A { /* ... */}
class B extends A { /* ... */ }
```

다음의 코드를 작성하는 것은 합리적 입니다.

```
B b = new B();
A a = b;
```

위의 예제는 일반 클래스의 상속은 서브타이핑의 이러한 규칙을 따른다는 것을 보여준다. 클래스 `B`가 클래스 `A`를 상속한다면 `B`는 `A`의 서브타입 입니다. 이러한 규칙은 제네릭 타입에 적용되어질 수 없습니다.

```java
List<B> lb = new ArrayList<>();
List<A> la = lb;	// 컴파일 에러
```

`Number`의 서브타입이 `Integer` 이다. 그렇다면 `List<Integer>`과 `List<Number>`의 관계는 어떻게 되는가?

<img src="/Users/baejongjin/Library/Application Support/typora-user-images/image-20200218084650657.png" alt="image-20200218084650657" style="zoom:50%;" />

비록 `Integer`은 `Number`의 서브타입 이긴 하지만, `List<Integer>`은 `List<Number>`의 서브타입이 아니고 두 타입은 관계 없다. `List<Number>` 과 `List<Integer>`의 공통 부모는 `List<?>`이다.

`List<Integer>`의 요소들을 통해서 `Number`의 메소드에 접근하기 위해 이러한 클래스들의 관계를 upper bounded wildcard를 사용해서 생성할 수 있다.

```java
List<? extends Integer> intList = new ArrayList<>();
List<? extends Number> numList = intList;	
// List<? extends Integer> 은 List<? extends Number>의 서브타입이기 때문에 잘 실행된다.
```

`Integer`은 `Number`의 서브타입이고, `numList`는 `Number`의 리스트 객체이기 때문에 `intList`와 `numList` 사이의 관계는 존재한다. 다음의 다이어 그램은 upper 또는 lower bounded wildcards와 함께 선언된 여러 `List` 클래스들 사이의 관계를 보여준다.

<img src="/Users/baejongjin/Library/Application Support/typora-user-images/image-20200218085424643.png" alt="image-20200218085424643" style="zoom:50%;" />

The Guidelines for Wildcard Use 섹션에는 upper and lower bounded wildcards의 사용으로 인한 영향(ramification)에 대해 더 자세한 설명을 가지고 있습니다.

---

## Wildcard Capture and Helper Methods

**몇몇의 경우에, Compiler는 와일드 카드의 타입을 추론한다.** 예로들어, 리스트가 `List<?>`로 정의되어지면 표현식을 평가할 때 컴파일러는 코드로부터 특정한 타입을 추론합니다. **이 시나리오를 wildcard capture 라고 합니다.**

많은 부분에서, 개발자는 **wildcard capture**에 대해서 걱정할 필요가 없습니다. "capture of" 문구가 포함된 에러 메세지를 볼 경우를 제외하고

`WildcardError` 예제는 컴파일 할 때 capture 에러를 발생합니다.

```java
import java.util.List;

public class WildcardError {
	
   void foo(List<?> i) {
      i.set(0, i.get(0));
   }
}
```

**이 예제의 경우, <u>컴파일러는 `i` 입력 parameter를 `Object` 타입으로써 처리합니다.</u> `foo` 메소드가 List.set(int, E)를 호출할 때, 컴파일러는 리스트 내에 삽입되어지는 객체의 타입을 확인할 수 없고 에러를 발생합니다.** 이러한 타입의 에러는 컴파일러가 변수에 잘못 타입이 할당 되었을 때 발생시킵니다. 컴파일 시간에 type safe를 강요하기 위해 자바에 제네릭이 추가되었습니다.

Oracle의 JDK 7 javac 구현으로부터 컴파일 되어질 때 `WildcardError` 예제는 다음과 같은 에러를 발생시킵니다.

```
WildcardError.java:6: error: method set in interface List<E> cannot be applied to given types;
    i.set(0, i.get(0));
     ^
  required: int,CAP#1
  found: int,Object
  reason: actual argument Object cannot be converted to CAP#1 by method invocation conversion
  where E is a type-variable:
    E extends Object declared in interface List
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
1 error
```

이 예제에서, 코드는 안전한 작업을 수행하려고 시도하므로 컴파일러 오류를 어떻게 해결할 수 있습니까? **개발자는 wildcard를 캡쳐하는 private helper method를 작성함으로써 이러한 상황을 해결할 수 있습니다.** 이러한 경우에 WildcardFixed 예제에서 보이는 것처럼 fooHelper인 private helper method를 생성함으로써 이러한 문제를 해결할 수 있습니다.

```java
public class WildcardFixed {
	
   void foo(List<?> i) {
      fooHelper(i);
   }
   
   // helper 메소드는 타입추론을 통해서 와일드 카드가 캡쳐되어질 수 잇도록 도와준다.
   private <T> void fooHelper(List<T> l) {
      l.set(0, 1.get(0));
   }
}
```

helper method 덕분에, 컴파일러는 T를 CAP#1으로 결정하는 추론을 사용한다. 이 예제는 성공적으로 컴파일 합니다.

규칙으로부터, helper methods는 일반적으로 `originmalMethodNameHelper` 라고 불립니다.

더 복잡한 에러인 WildcardErrorBad를 고려해 보자.

```java
import java.util.List;

public class WildcardErrorBad {
	void swapFirst (List<? extends Number> l1, List<? extends Number> 12) {
		Number temp = l1.get(0);
      l1.set(0, l2.get(0)); // expected a CAP#1 extends Number,
                            // got a CAP#2 extends Number;
                            // same bound, but different types
      l2.set(0, temp);	    // expected a CAP#1 extends Number,
                            // got a Number
	}
}
```

이 예제에서, 코드는 안전하지 못한 연산을 수행하려고 합니다. 예로들어 다음 swapFirst 메소드에 대한 예제를 고려해봐라

```java
List<Integer> li = Arrays.asList(1,2,3);
List<Double> ld = Arrays.asList(10.10, 20.20, 30.30);
swapFirst(li,ld);
```

`List<Integer>` 과 `List<Double>` 모두 `List<? extends Number>` 의 조건을 만족하지만, Integer 값의 리스트로부터 아이템을 가져와서 Double 값의 리스트에 두는 것은 바람직하지 못합니다.

이 코드를 Oracle's JDK `javac` 컴파일러로 컴파일 하면 다음과 같은 에러를 발생할 것이다.

```
WildcardErrorBad.java:7: error: method set in interface List<E> cannot be applied to given types;
      l1.set(0, l2.get(0)); // expected a CAP#1 extends Number,
        ^
  required: int,CAP#1
  found: int,Number
  reason: actual argument Number cannot be converted to CAP#1 by method invocation conversion
  where E is a type-variable:
    E extends Object declared in interface List
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Number from capture of ? extends Number
WildcardErrorBad.java:10: error: method set in interface List<E> cannot be applied to given types;
      l2.set(0, temp);      // expected a CAP#1 extends Number,
        ^
  required: int,CAP#1
  found: int,Number
  reason: actual argument Number cannot be converted to CAP#1 by method invocation conversion
  where E is a type-variable:
    E extends Object declared in interface List
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Number from capture of ? extends Number
WildcardErrorBad.java:15: error: method set in interface List<E> cannot be applied to given types;
        i.set(0, i.get(0));
         ^
  required: int,CAP#1
  found: int,Object
  reason: actual argument Object cannot be converted to CAP#1 by method invocation conversion
  where E is a type-variable:
    E extends Object declared in interface List
  where CAP#1 is a fresh type-variable:
    CAP#1 extends Object from capture of ?
3 errors
```

이러한 메소드를 해결하기 위한 어떠한 helper method도 존재하지 않습니다. 그 이유는 이 코드는 기본적으로 잘못되어있기 때문입니다.

---

## Guidelines for Wildcard Use

**제네릭을 사용해서 프로그래밍을 학습할 때 더 혼란스러운 측면 중 하나는 upper bounded wildcard 와 lower bounded wildcard를 사용하는 시기를 결정하는 것입니다.** 이 페이지에서는 코드를 디자인할 때 따라야할 몇 가지 지침을 제공하고 있스빈다.

이 논의를 위해, 변수를 두 가지 기능 중 하나를 제공하는 것이라고 생각하면 도움이 됩니다.

- **An "In" Variable** : <u>"in" 변수는 코드에 데이터를 제공(serve up)합니다.</u> `copy(src, dest)`라는 두 개의 arguments가 있는 복사 방법을 생각 해 보십시요. `src` argument는 복사할 데이터를 제공하므로 "in" parameter 입니다.
- **An "Out" Variable** : "out" 변수는 다른곳에서 사용을 위해 데이터를 저장합니다. copy 예제에서 `dest` argument는 data를 보유합니다. 그래서 "out" parameter 입니다.

물론, 몇가지의 변수들은 "in"과 "out" 모두의 목적으로 사용됩니다 - 이 시나리오 또한 이번 지침에서 다루어 질 것입니다.

**와일드 카드를 사용하고 어떠한 와일드 카드의 타입이 적절할지 결정할 때  "in"과 "out" 원칙을 사용할 수 잇습니다.** 다음의 리스트는 따라야할 적절한 지침을 제공하고 있습니다. 

> **Wildcard Guidelines**
>
> - "in" 변수는 upper bounded wildcard로써 정의되어야 한다. (extends 키워드 사용)
> - "out" 변수는 lower bounded wildcard로써 정의되어야 한다. (super 키워드 사용)
> - `Object` 클래스에 정의 된 메소드를 사용하여 "in" 변수에 접근할 수 있는 경우 unbounded wilcard를 사용하세요
> - "in"과 "out" 모두의 변수로써 변수에 접근하기 원하는 경우, 와일드 카드를 사용하지 마세요.

이 지침은 메소드의 리턴 타입에 적용하지 마세요. 와일드 카드를 리턴 유형으로 사용하면 프로그래머가 와일드 카드를 처리하게끔 강요됨으로 피해야 합니다.

**`List<? extends ...>`로 정의된 리스트는 비공식적으로 읽기 전용으로 생각할 수 있지만 엄격한 보증은 아닙니다.**

다음의 두 클래스가 있다고 가정하세요.

```java
class NaturalNumber {
	
   private int i;
   
   public NaturalNumber(int i) {
      this.i =i;

   }
   // ...
}

class EvenNumber extends NaturalNumber {
   
   public EvenNumber(int i) {
      super(i);
   }
   // ...
}
```

다음의 코드를 고려해라.

```java
List<EvenNumber> le = new ArrayList<>();
List<? extends NaturalNumber> ln = le;
ln.add(new NaturalNumber(35));	// compile-time error	
```

`List<EvenNumber>`가 `List<? extends NaturalNumber>`의 서브타입 이기 때문에, `le`를 `ln`에 할당할 수 있다. **하지만 자연수를 짝수 리스트에 추가하기 위해서 `ln`을 사용할 수 없다.**

리스트에선 다음의 연산이 가능하다.

- null을 추가할 수 있다.
- clear를 호출할 수 있다.
- iterator를 얻을 수 있고 remove를 호출할 수 있다.
- wildcard를 캡처할 수 있고, 리스트로 부터 읽을 elements를 작성할 수 있다.

**`List<? extends NaturalNumber>`로 정의된 리스트는 단어의 엄격한 의미(strictest sense of the word)에서 읽기 전용이 아니지만, 새로운 요소를 저장하거나 리스트에 존재하는 요소를 변경할 수 없으므로 그렇게 생각할 수 있다.**

---

## Type Erasure









































