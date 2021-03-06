# JPA(Java Persistence API)

**JPA (Java Persistence API)란 지속적으로 거대한 양의 데이터를 데이터 베이스에 저장하기 위한 클래스 또는 메소드의 집합을 의미한다.**

---

## JPA - Introduction

**기업에서 사용하는 어플리케이션은 방대한 양의 데이터를 검색 또는 저장함으로써 데이터베아스 연산을 수행한다.**
저장공간 관리를 위한 모든 이용가능한 기술이 있음에도 불구하고, 개발자들은 데이터베이스 연산을 효율적으로 수행하기 위해 노력중이다.

### Mismatches between relational and object models

Relational Objects는 테이블(tabular) 형식으로 나타내어진다. 반면에 Object models은 객체의 상호연결된 그래프로 표시되어집니다.

관계형 데이터베이스로부터 object model을 검색하고 저장하는 동안, 약간의 mismatch는 다음과 같은 이유에 의해서 발생합니다.

- **Granularity :** Object model은 관계형 모델보다 더 많은 단위(granularity)를 가집니다.
- **Subtypes :** Subtypes는 모든 종류의 관계형 데이터베이스에서 지원하지 않습니다.
- **Identity** : 객체 모델과 마찬가지로, 관계형 모델은 등식을 작성하는 동안에 정체를 노출시키지 않습니다.,
- **Associations :** 관계형 모델은 object domain model을 조사하는 동안 여러 관계를 결정할 수 없다.
- **Data navigation :** 객체 네트워크에서 객체간의 Data navigation이 다르다.

### Where to use JPA?

관계형 객체를 관리하기 위해 코드를 쓰는 수고(burden)을 감소하기 위해, 개발자는 JPA Provider 프레임워크를 따른다. JPA Provider 프레임워크는 데이터베이스 인스턴스간 상호관계를 쉽게 해준다. 여기서 필요한 프레임워크는 JPA에 의해 대신 처리된다.

<img src="/Users/baejongjin/Library/Application Support/typora-user-images/image-20200112194721418.png" alt="image-20200112194721418" style="zoom:50%;" />

### JPA History

이전 버젼의 EJB에서, 정의된 persistence 단계에서 business 로직 단계를 javax.ejb.EntitiyBean 인터페이스를 사용해서 결합했다.

- EJB 3.0을 도입하는 동안, persistence 계층이 분리되고 JPA(Java Persistence API) 1.0으로 구체화 되었습니다.
- JPA 2.0은 2009년 10월 10일에 JAVA EE6에서 출시하였다.

### JPA Providers

JPA는 오픈소스 API이다. Oracle, Redhat, Eclipse 등과 같은 다양한 엔터프라이즈 공급업체는 JPA Persistence 기능을 새로운 제품에 추가해서 사용자들에게 공급한다.

이러한 제품의 예로는 : Hibernate, Eclipselink, Toplink, Spring Data JPA, etc

---

## JPA - Architecture

JPA는 business 엔티티를 relational 엔티티로써 저장하는 소스이다.

### Class Level Architecture

<img src="/Users/baejongjin/Library/Application Support/typora-user-images/image-20200112235505507.png" alt="image-20200112235505507" style="zoom:50%;" />

위의 이미지는 클래스 레벨의 JPA 아키텍처를 보여준다. 위의 이미지는 JPA의 핵심 클래스와 인터페이스를 보여준다.

**JPA의 핵심 클래스 및 인터페이스 소개**

- **EntitiyManagerFactory** : EntitiyManager 클래스의 Factory 클래스이다. 이 클래스는 다수의 EntitiyManager 인스턴스를 생성 및 관리한다.
- **EntitiyManager** : 인터페이스이다. 객체에서 연산을 관리해주고, Query 인스턴스를 위해 공장처럼 작동한다.
- **Entity :** 엔티티들은 Persistence 객체이고 데이터베이스에 저장된다.
- **EntitiyTransaction** : EntityManager와 1:1 관계를 가진다. 각 EntitiyManager를 위해서 연산들이 관리되어진다.
- **Persistence :** EntitiyManagerFactory 인스턴스를 얻기 위한 정적 메소드를 가지고 있는 클래스 입니다.
- **Query :** 각 JPA Vendor에 의해서 조건에 충족하는  객체를 얻기위해 구현되는 인터페이스이다.

**위에서 소개하는 클래스 및 인터페이스들은 프로그래머들이 데이터베이스에 데이터를 저장하기 위해 코드를 쓰는 수고로움을 덜어주는 것을 도와준다.**

### JPA Class Relationships

<img src="/Users/baejongjin/Library/Application Support/typora-user-images/image-20200113001335319.png" alt="image-20200113001335319" style="zoom:50%;" />

위에서 보이는 아키텍처에서는 javax.persistence.package에 속해있는 클래스와 인터페이스 간의 관계를 나타낸다.

---

## JPA - ORM Components

요즘 어플리케이션들은 데이터를 저장하기 위해서 관계형 데이터베이스를 사용한다. 최근에 많은 업체들이 데이터를 유지하는데 개발자의 수고를 덜기 위해서 Object Database로 변경하였다. 이러한 사실은 Object database 또는 Object Relational Technologies가 데이터를 저장하고, 검색하고, 업데이트하고, 유지하고 있음을 의미한다. Object Relational Technologies의 핵심 부분은 orm.xml 파일에 맵핑하는 것이다. xml에는 컴파일이 필요하지 않음으로 관리가 적은 여러 데이터 소스를 쉽게 변경할 수 있습니다.

### Object Relational Mapping

**ORM이란 데이터를 객체 타입에서 관계형 타입으로 또는 그 반대로 바꾸는 프로그래밍 기술을 의미한다.**

ORM에서의 가장 중요한 특징은 객체를 데이터베이스 내의 데이터와 바인딩 또는 맵핑하는 것이다.
맵핑하는 동안에 데이터, 데이터 유형, 다른 테이블의 자체 엔티티 또는 엔티티와의 관계를 고려해야한다.

### Advanced Feature

- **Idiomatic persistence :** 객체지향 클래스를 사용하여 persistence class를 작성할 수 있도록 한다.
- **High Performance :** 많은 fetch 기술과 lock 기술을 가지고 있습니다.
- **Reliable :** 많은 현업의 프로그래머들이 사용하고 있다. 그리고 매우 안정적이고 잘 알려져(eminent) 있다.

### ORM Architecture

<img src="/Users/baejongjin/Library/Application Support/typora-user-images/image-20200113003058918.png" alt="image-20200113003058918" style="zoom:50%;" />

위 아키텍처는 어떻게 객체 data가 관계형 데이터베이스에 저장되는지 3단계의 과정으로 설명 해 준다.

#### 1 단계

Object data 단계는 POJO(Plain Old Java Object)와 서비스 인터페이스, 클래스를 포함한다.
이 단계는 business 로직 연산과 속성을 포함하는 주요 business 요소 단계이다.

- 예로들어 작업자 POJO 클래스는 ID, name, salary, ... 등의 속성을 포함하고, setter와 getter등의 다양한 메서드를 포함한다.
- 예로들어 작업자 DAO/Service 클래스는 create employee, find employee, delete employee와 같은 다양한 서비스 메소드를 포함한다.

#### 2단계

2단계는 맵핑 또는 persistence 단계라고 불리우며 JPA provider, mapping file(ORM.xml), JPA Loader, Object Grid가 포함된다.

- **JPA Provider :** JPA flavor(javax.persistence)를 포함하는 공급 업자이다. (ex. Eclipselink, Toplink, Hibernate, etc)
- **Mapping file :** 맵핑파일은 POJO 클래스의 데이터와 relational database의 데이터 사이의 맵핑 설정을 포함한다.
- **JPA Loader :** 관계형 grid data를 로드할 수 있는 캐시 메모리같은 역할을 수행한다. JPA Loader는 POJO data를 위한 service class와 상호작용하기위해 database를 복제와 같은 역할을 수행한다.
- **Object Grid :** 관계형 데이터의 복사본이 저장할 수 잇는 캐시메모리와 같은 일시적인 공간을 의미한다.

#### 3단계

3단계는 관계형 데이터 단계이다. 3단계에서는 business component와 논리적으로 연결된 관계형 데이터를 포함한다.
business 요소가 데이터를 commit할 때 데이터들은 물리적인 데이터베이스에 저장된다. 그때까지 수정된 데이터는 그리드 형식으로 캐시 메모리에 저장됩니다. 데이터를 얻는 과정도 마찬가지입니다.

### Mapping.xml

mapping.xml 파일은 데이터베이스의 테이블들과 함께 entitiy 클래스들을 매핑하도록 JPA vendor를 지시합니다.

**Employee.java** (POJO 클래스의 Employee Entitiy)

```
public class Employee {

   private int eid;
   private String ename;
   private double salary;
   private String deg;

   public Employee(int eid, String ename, double salary, String deg) {
      super( );
      this.eid = eid;
      this.ename = ename;
      this.salary = salary;
      this.deg = deg;
   }

   public Employee( ) {
      super();
   }

   public int getEid( ) {
      return eid;
   }
   
   public void setEid(int eid) {
      this.eid = eid;
   }
   
   public String getEname( ) {
      return ename;
   }
   
   public void setEname(String ename) {
      this.ename = ename;
   }

   public double getSalary( ) {
      return salary;
   }
   
   public void setSalary(double salary) {
      this.salary = salary;
   }

   public String getDeg( ) {
      return deg;
   }
   
   public void setDeg(String deg) {
      this.deg = deg;
   }
}
```

**mapping.xml**

```xml
<? xml version="1.0" encoding="UTF-8" ?>

<entity-mappings xmlns="http://java.sun.com/xml/ns/persistence/orm"
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation="http://java.sun.com/xml/ns/persistence/orm    
   http://java.sun.com/xml/ns/persistence/orm_1_0.xsd"
   version="1.0">
      
   <description> XML Mapping file</description>
      
   <entity class="Employee">        
      <table name="EMPLOYEETABLE"/>
      <attributes>
      
         <id name="eid">
            <generated-value strategy="TABLE"/>
         </id>

         <basic name="ename">
            <column name="EMP_NAME" length="100"/>
         </basic>
         
         <basic name="salary">
         </basic>
         
         <basic name="deg">
         </basic>
         
      </attributes>
   </entity>
   
</entity-mappings>
```

- **\<entity-mappings> :** 엔티티 태그를 xml 파일로 허용하는 스키마를 정의한다.
- **\<description> :** 어플리케이션에 관한 description을 정의하는 태그
- **\<entitiy> :** 데이터베이스에서 테이블로 변환하기 원하는 엔티티 클래스를 작성하는 태그이다. 
- **\<table> :** 테이블 이름을 정의하는 태그
- **\<attribute> :** 속성을 정의하는 태그 (테이블 내의 필드)
- **\<id> :** 테이블의 기본키를 정의하는 태그
- **\<basic> :** 테이블의 남은 속성을 정의하는 태그
- **\<column-name> :** 정의된 테이블의 필드 네임을 정의하는 태그

### Annotations

일반적으로 XML 파일은 특정한 요소를 환경설정하기 위해 사용되거나, 두개의 다른 요소간의 specification을 맵핑하기 위해 사용된다.

우리의 경우는 xml파일을 프레임워크와 분리시켜야 한다.
XML파일을 프레임워크와 분리시키지 않는다면, 맵핑 xml파일을 작성하는 동안에 POJO 클래스를 mapping.xml 파일에서의 entitiy 태그와 비교해야한다. **이것은 매우 비효율적이다.**

이것을 해결하기위해 클래스를 정의할 때 우리는 환경설정의 일부분으로 annotation을 사용할 수 있다.
annotation은 클래스, 속성, 메소드에 사용이 가능하다. 어노테이션은 '@'와 같은 기호로 시작한다.
어노테이션은 클래스와, 속성, 메소드가 선언되기 전에 선언되어진다.
JPA의 모든 어노테이션은 javax.persistence 패키지에 정의되어있다.

**Annotation**

- @Entitiy : 엔티티 또는 테이블로써 클래스를 선언하는 어노테이션을 의미한다.
- @Table : 테이블의 이름을 선언하는 어노테이션을 의미한다.
- @Id : 클래스의 식별자를 위해 사용하는 속성을 선언하는 어노테이션을 의미한다.
- @ManyToMany : 연결된 테이블간에 다:다 관계를 정의하는 어노테이션이다.
- @JoinColumn : 다:다, 1:다 연관에 사용되는 엔티티 컬렉션 또는 엔티티 연관을 명시하는 어노테이션이다.
- etc

```java
import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
   @Id @GeneratedValue
   @Column(name = "id")
   private int id;

   @Column(name = "first_name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Column(name = "salary")
   private int salary;  

   public Employee() {}
   
   public int getId() {
      return id;
   }
   
   public void setId( int id ) {
      this.id = id;
   }
   
   public String getFirstName() {
      return firstName;
   }
   
   public void setFirstName( String first_name ) {
      this.firstName = first_name;
   }
   
   public String getLastName() {
      return lastName;
   }
   
   public void setLastName( String last_name ) {
      this.lastName = last_name;
   }
   
   public int getSalary() {
      return salary;
   }
   
   public void setSalary( int salary ) {
      this.salary = salary;
   }
}
```

### Java Bean Standard

Java Class는 인스턴스의 속성과 메소드를 객체라고 부르는 하나의 단위로 캡슐화 한다.
**Java Bean 일시적인 저장소이고, component와 객체에 의해 재사용할 수 있다.**
Bean은 인스턴스 속성을 개별적으로 초기화할 수 있도록 getter & setter 메소드와 기본 생성자를 가진 직렬화 가능한 메소드이다.

### Bean Conventions (Java Bean의 규칙)

- **Bean은 기본 생성자 또는 직렬화된 인스턴스를 포함하는 파일을 가져야 한다.** 따라서 Bean은 Bean을 인스턴스화 할 수 있다.
- 빈의 속성은 Boolean 속성과 non-Boolean 속성으로 분리(segregate)될 수 있어야 한다.
- Non-Boolean property로는 getter과 setter 메소드를 포함합니다.
- Boolean property로는 setter과 is메소드를 포함합니다.
- Getter 메서드는 소문자 get으로 시작하고 field네임(첫 문자는 대문자)으로 이어갑니다.
- Setter 메서드는 소문자 set으로 시작하고 filed네임(첫 문자는 대문자)으로 이어갑니다.





**직렬화(Serialize)**

- 자바 시스템 내부에서 사용되는 Object 또는 Data를 외부의 자바 시스템에서도 사용할 수 잇도록 byte 형태로 변환하는 기술

- JVM의 메모리에 상주(힙 또는 스택)되어 있는 객체 데이터를 바이트 형태로 변화하는 기술

   

   



















