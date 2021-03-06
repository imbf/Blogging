# MVC 패턴이란 무엇인가?

MVC 디자인 패턴은 **Model-View-Controller**로써 알려져 있다. 이것은 어플리케이션의 구조와 인터페이스를 생성하고 디자인하는데 사용되어지는 흔한 아키텍처 패턴이다.

**이 패턴은 어플리케이션을 서로 의존하고 연결되어있는 3가지의 부분(Model, View, Controller)으로 나눕니다.** <u>이러한 디자인 패턴은 GUI를 개발하기 위해 또는 웹 어플리케이션을 개발하기 위해 자주 사용됩니다.</u>

---

## Understanding MVC Design Pattern

이러한 디자인 패턴을 이해하는 것은 쉽고 간단하다. MVC 이론은 Mode-View-Controller 패턴을 나타낸다. 이 3가지 부분의 기능은 다음과 같다.

1. **Model** : 디자인 패턴에서 모델은 가장 기본 부분이고 **순수한 어플리케이션 정보를 포함**한다. **모델은 어떻게 사용자에게 데이터를 보내는지에 대한 정보는 포함하지 않는다.** 모델은 사용자 인터페이스와 독립적이다. **모델은 어플리케이션의 로직과 규칙을 통제한다.**
2. **View** : **뷰는 사용자가 모델의 데이터를 볼 수 있게 도와주는 부분이다. 뷰의 가장 중요한 관심사는 모델의 데이터에 접근하는 것입니다.** 뷰는 정보를 표현하기 위해서 차트, 테이블, 다이어그램등을 사용한다. 뷰는 유사한 데이터를 표시할 수 있고 또 다른 목적으로 막대 그래프와 테이블을 사용할 수 있습니다. **뷰는 어플리케이션이 포함하는 정보를 가시화 합니다.**
3. **Controller** : 작업의 대부분은 Controller에 의해서 수행 됩니다. **Controller는 입력을 위해 지원해주고 어플리케이션을 위해 입력을 명령어로 변환합니다. Controller는 모델과 뷰 사이에서 사용되어집니다.** 모델과 뷰 부분이 상호 연결되어 있음으로 뷰 부분에 실행이 반영됩니다.

**다음의 그림은 아주 중요한 MVC 패턴의 콤포넌트(Movel-View-Controller)들을 유기적으로 설명하는 그림이다.**

![image-20200219100808798](/Users/baejongjin/Library/Application Support/typora-user-images/image-20200219100808798.png)

---

## How does MVC Design Pattern make working so easy?

**오늘날의 대부분의 어플리케이션은 MVC 패턴을 따릅니다.** MVC 디자인 패턴은 코드 재사용과 병렬(parallel)개발에 도움을 줍니다. MVC 패턴은 작업을 쉽고 간단하게 만듭니다. MVC 패턴을 통해서 생성된 구성요소들은 본래 <u>서로 독립적</u>입니다. **이러한 특징은 개발자가 구성요소들과 코드를 쉽게 재사용할 수 있게 도와줍니다.**

데이터는 뷰에 의해서 모니터링되고 사용자에게 표시되는 방식으로 제어됨으로써 개발자는 다른 어플리케이션을 위해 다른 데이터를 가진 비슷한 뷰를 사용할 수 있습니다. **이러한 것들은 개발자의 시간과 노력을 많은 부분 절약해 줍니다.**

---

## Top MVC Design Pattern Companies

MVC 디자인 패턴을 사용하는 유명한 회사들은 다음과 같다.

- Microsoft
- Go Daddy
- Dell
- Visual Studio
- Wild Tangent

---

## What can you do with MVC Design Pattern?

MVC 디자인 패턴들은 일반적으로 웹 어플리케이션에 의해서 사용되거나 인터페이스를 디자인하기 위해서 사용됩니다. C#, Python, PHP, Java 등의 몇가지의 인기 있는 언어들은 MVC 이론을 적용합니다. 디자인 패턴은 코드를 관리하는데 도와줍니다. **MVC의 구성요소의 분리는 가독성이 좋고 재사용 가능한 코드를 개발할 수 있습니다.** MVC 이론은 Java Swing, Apple's Cocoa, MFC library와 같은 UI Toolkits에서 사용되어집니다.

---

## Working with MVC Design Pattern

MVC 디자인 패턴은 웹 어플리케이션에 종종 사용됩니다. 이러한 어플리케이션의 뷰는 어플리케이션으로부터 생성된 <u>HTML or XHTML files</u> 입니다.

**Controller는 입력 폼으로부터 입력을 받고 입력을 모델로 관리하고 처리합니다. 모델은 특정한 작업을 수행하기위한 프로세스의 규칙과 데이터를 포함합니다.**

높은 수준이 아닌 모델 코드의 중복은 다른 사용자 인터페이스 구현에서 제거됩니다. MVC 패턴은 모든 이슈에 관해 핵심적인 솔루션을 제공하고 이러한 솔루션을 각 machine에 적용하는 것을 도와줍니다.

---

## Advantages

<u>MVC 디자인 패턴을 사용하는 장점</u>은 다음과 같다.

- 모델 하나로 여러개의 View가 만들어 질 수 있다.
- 업무의 분리(어플리케이션 업무의 Model-View-Controller로의 분리)는 개발자가 유지보수와 리팩토링 하는데 도와준다.
- MVC 이론 적용은 Models, views, Controllers 간에 저수준의 행동결합을 가진다.
- 여러 개발자가 모델, 뷰 및 컨트롤러에서 동시에 작업할 수 있습니다.
- 요구하는 모델을 위한 여러개의 뷰들이 그룹화 될 수 있습니다.

---

## Required Skills

MVC 디자인 패턴은 웹 어플리케이션에서 사용되어지는 아키텍처 패턴입니다. 웹 어플리케이션 또는 프로그래밍에 대한 사전 지식은 사용자에게 유리할 것입니다. coding, scripting, 기본 언어의 지식은 학습자와 개발자에게 큰 도움이 될것입니다. MVC는 완벽한 어플리케이션이 아니며 보통 service layer, data access layer, logic layer이 필요합니다.

---

## Who is the right audience for learning MVC Design Pattern technologies?

MVC 패턴은 프로그래밍 플랫폼에서 사용되고 이러한 패턴들을 배워야 하는 사람들은 개발자와 프로그래머들이다. 학습자들은 디자인 패턴을 배우고, 사용하고, 자신의 프로젝트에 적용하는데에 열정을 가져야한다.

---

## MVC Pattern 예제 - Java

```java
// Model
class Student
{
    private String rollNo;
    private String name;

    public String getRollNo()
    {
        return rollNo;
    }

    public void setRollNo(String rollNo)
    {
        this.rollNo = rollNo;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}

// View
class StudentView
{
    public void printStudentDetails(String studentName, String studentRollNo)
    {
        System.out.println("Student: ");
        System.out.println("Name: " + studentName);
        System.out.println("Roll No: " + studentRollNo);
    }
}

// Controller
class StudentController
{
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view)   // 생성자를 통한 초기화
    {
        this.model = model;
        this.view = view;
    }

    public void setStudentName(String name) // model 인스턴스의 name 멤버 설정
    {
        model.setName(name);
    }

    public String getStudentName()
    {
        return model.getName();
    }

    public void setStudentRollNo(String rollNo)
    {
        model.setRollNo(rollNo); // model 인스턴스의 rollNo 멤버 설정
    }

    public String getStudentRollNo()
    {
        return model.getRollNo();
    }

    public void updateView()
    {
        // 모델's Information print method
        view.printStudentDetails(model.getName(), model.getRollNo());   
    }
}

// Application (Main Method)
public class MVCPattern
{
    public static void main(String[] args)
    {
        Student model  = retriveStudentFromDatabase();

        StudentView view = new StudentView();

        StudentController controller = new StudentController(model, view);

        controller.updateView();

        controller.setStudentName("Vikram Sharma");

        controller.updateView();
    }

    private static Student retriveStudentFromDatabase()
    {
        Student student = new Student();
        student.setName("Lokesh Sharma");
        student.setRollNo("15UCS157");
        return student;
    }

}

/* 실행 결과 
        Student:
        Name: Lokesh Sharma
        Roll No: 15UCS157
        Student:
        Name: Virkram Sharma
        Roll No: 15UCS157
 */
```

---

## Conclusion

MVC 디자인 패턴을 이해하는것은 중요한 기술이다. 이 기술을 사용하면 쉽게 업그레이드 할 수 있는 재사용 가능한 개별 모델을 만들 수 있습니다. 어플리케이션을 개발하는 걸리는 시간이 줄어들고 개발자는 효과적인 어플리케이션을 생성할 수 있습니다. MVC 이론은 컴퓨터 프로그래밍의 기본 개념이고 여러 웹 개발 서비스와 프로젝트를 도와줄 것입니다.





































