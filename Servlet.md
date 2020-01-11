# What is Servlet

**Servlet**은 웹 기반의 어플리케이션을 빌드하기 위해 <u>CGI(Common Gateway Interface : 웹 서버 상에서 사용자 프로그램을 동작시키기 위한 조합)</u> 제한없이 component 기반의 플랫폼 독립적인 메소드를 제공하는 클래스이다.

**Servlet**은 전체 Java API에 접근할 수 있고, 기업형 데이터베이스에 접근할 수 있는 JDBC API에도 접근할 수 있다.

---

## why to Learn Servlet?

**Servlet을 사용함으로 인해서 웹 페이지 폼을 통해 사용자로부터 입력을 수집할 수 있고, 데이터베이스나 다른 소스로부터 records를 표시할 수 있고, 동적인 웹 페이지를 생성할 수 있다.**

Java Servlet은 종종 CGI를 사용하여 구현 된 프로그램과 동일한 목적으로 사용되지만 몇가지의 장점을 더 제공한다.

- Performance가 훨씬 더 좋다.
- Servlet은 웹 서버의 주소공간 내에서 실행된다. client의 요청을 다루기 위해 process를 분리해서 생성할 필요가 없다.
- Servlet은 자바로 작성되어 있음으로 Platform 독립적이다.
- 서버의 Java Security Manager가 리소스를 보호하기 위해 제한을 걸어두기 때문에 Servlet은 믿을 수 있다.
- Java 클래스 라이브러리의 모든 기능은 Servlet에서 사용할 수 있습니다. Servlet은 applet, database, 다른 소프트웨어와 socket과 RMI 방법을 활용하여 통신할 수 있다.

> *applet : 애플릿은 플러그인의 하나로서 더 큰 프로그램 범위 내에서 실행되는 특정한 작업을 수행하는 조그마한 응용 프로그램을 말한다.

---

## Application of Servlet

- Application of Servlet은 client로부터 보내진 데이터를 읽는다.
   여기서 client는 웹 페이지의 HTML Form, applet, HTTP Client Program을 의미한다.
- Appliaction of Servlet은 클라이언트가 보낸 데이터를 읽을 수 있습니다.
   이 데이터에는 cookies, media types, 브라우저가 이해할 수 있는 compression schemes등이 포함된다.
- Application of Servlet은 data를 처리하고 결과를 생성한다.
   이러한 프로세스는 database를 접근해야 될 수도 있고, RMI, CORBA call 실행, 웹 서비스 호출 또는 응답을 직접적으로 계산해야 할 수도 있다.
- Application of Servlet은 함축된 HTTP Response를 clients에게 보낸다.
   이 HTTP Response는 client에게 어떤 타입의 document(setting cookies and caching parameters 된)가 응답되었는지를 알려준다.

---

## What are Servlets?

**Java Servlet**은 Web 또는 Application Server 에서 실행하는 program이다.

**Servlet**은 브라우저나, HTTP client로부터 오는 request와 HTTP server의 어플리케이션과 database 사이의 **middle layer**의 역할을 수행한다.

### Servlets Architecture

Web Application에서 Servlet의 위치를 보여준다.

<img src="/Users/baejongjin/Library/Application Support/typora-user-images/image-20200110205747479.png" alt="image-20200110205747479" style="zoom:50%;" />

### Servlets Tasks

**서블릿이 수행하는 주요 역할은 다음과 같다.**

- **Servlet은 client로부터 보내진 데이터를 읽습니다.**
   여기서 client는 웹 페이지의 HTML Form, applet, HTTP Client Program을 의미한다.
- **Servlet은 클라이언트가 보낸 데이터를 읽습니다.**
   이 데이터에는 cookies, media types, 브라우저가 이해할 수 있는 compression schemes 등이 포함됩니다.
- **Servlet은 data를 처리하고 결과를 생성한다.**
   이러한 프로세스는 database를 접근해야 될 수도 있고, RMI, CORBA call 실행, 웹 서비스 호출 또는 응답을 직접적으로 계산해야 할 수도 있다.
- **Servlet은 함축된 HTTP Response를 clients에게 보내준다.**
   이 HTTP Response는 client에게 어떤 타입의 document(setting cookies and caching parameters 된)가 응답되었는지를 알려준다.
- **Servlet은 명확한 데이터(document와 같은)를 Client에게 보내준다.**
   이 document는 HTML, XML, GIF images, Excel, ...와 같이 다야한 형식으로 보내질 수 있다.

### Servlet Packages

Java Servlets는 Java Servlet specification 을 지원하는 interpreter를 가진 웹서버에 의해 동작되는 Java class이다.

Servlet은 javax.servlet 과 javax.servlet.http 패키지를 사용해 생성되어진다.javax.servlet과 javax.servlet.http는 Java's enterprise edition의 표준 부분이다.이러한 클래스들은 Java Servlet과 Java Server Page(JSP) specifications를 implement한다.

Java Servlet은 다른 Java class와 같이 컴파일 되고 생성된다. servlet package를 설치한 후 컴퓨터의 Classpath에 추가하면 JDK의 Java compiler와 같이 servlet을 컴파일 할 수 있다.

---

## Servlets - Life Cycle

 **Servlet life cycle은 전체 프로세스에서 servlet의 생성부터 소멸까지 정의되어진다.**

- init() 메소드에 의해서 servlet은 생성된다.
- service() 메소드에 의해서 client의 요청을 처리한다.
- destory() 메소드에 의해서 servlet은 소멸된다.
- servlet은 JVM의 garbage collector에 의해서 수집되어지는 garbage가 된다. 

### The init() Method

init 메서드는 서블릿이 생성되어질 때 한번 호출되고, 차후에 어떤 사용자의 요청에도 호출되지 않는다. 그래서 init 메서드는 one-time initializations라고 불리고, applets의 init 메서드와 비슷하다. 

**서블릿은 일반적으로 사용자가 처음 서블릿에 대응하는 URL을 호출할 때 생성된다.** 하지만 서버가 처음 시작할 때 서버가 로드되도록 지정할 수 있다.

사용자가 servlet을 호출할 때, 각 servlet의 단일 인스턴스가 생성되며, 동시에 각 사용자의 요청의 결과로 새로운 스레드가 doGet 또는 doPost로 적절히 전달됩니다. init 메서드는 단순히 서블릿 수명동안에 사용될 데이터를 작성하거나 로드합니다.

```java
public void init() throws ServletException{
	// Initialization code...
}
```

### The service() Method

service 메서드는 실제 작업을 수행하는 메인 메서드이다. **servlet container(ex. web server)** 는 클라이언트로 부터 오는 요청을 처리하기 위해, 정형화된 응답을 클라이언트에게 작성하기 위해 service() 메서드를 호출합니다.

**서버가 servlet을 위한 요청을 받을 때마다, 서버는 새로운 스레드를 생성하고(spawn) service 메서드를 호출합니다.** service 메서드는 HTTP request type(GET, POST, PUT, DELETE, etc.)등을 체크하고 적절히 doGet, doPost, doPut, doDelete, 등의 메서드를 호출합니다.

```java
public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

}
```

컨테이너가 service 메서드를 호출하고, service 메서드가 doGet, doPost, doPut, doDelete, 등의 메서드를 적절히 호출한다. 그래서 개발자들은 service 메서드를 호출할 필요가 없고 클라이언트로부터 받는 요청의 타입에 따라서  doGet메서드나, doPost메서드를 override해서 servlet을 사용하면 된다.

**doGet, doPost 메서드는 각 서비스 요청에서 가장 자주 사용하는 메서드입니다.**

### The doGet() Method

GET 요청은 URL을 통한 일반적인 요청, METHOD를 명시하지 않는 HTML폼으로 부터 생성된 요청들에 결과로 생성되며, 이것은 doGet() 메서드에 의해 핸들링 되어진다.

```java
public void doGET(HttpServletRequest request, HttpServletResponse response){
  throws ServletException, IOException{
    // Servlet code
  }
}
```

### The doPost() Method

POST 요청은 POST를 메서드로써 구체적으로 나열하는 HTML 폼으로부터 생성되며 POST 요청은 doPost() 메서드에의해 핸들링 되어진다.

```java
public void doPost(HttpSErvletRequest request, HttpServletResponse response) 
  throws ServletException, IOException{
	// Servlet code
}
```

### The destory() Method

destory() 메서드는 서블릿의 수명주기가 끝날 때 한번 호출된다. destory() 메서드는 서블릿에게 database 연결을 끊을 기회와, background 쓰레드를 중지할 기회, 쿠키 목록을 작성하고, 디스크에 hit count를 작성할 기회, 그리고 또 다른 정리 작업을 수행할 기회를 제공해준다. 

destory() 메서드가 호출되고 난 후, 서블릿 객체는 garbage collection을 위해서 마킹 되어진다.

```java
public void destory(){
  // Finalization code...
}
```

### Architecture Diagram

다음 그림은 서블릿 수명주기 시나리오를 묘사합니다.

- 첫번째 서버로 들어오는 HTTP request가 servlet container에 위임(delegate)되어진다.
- servlet container는 service()메서드를 호출하기 전에 servlet을 로드합니다.
- servlet container는 멀티 스레드를 생성함으로써 다중의 requests들을 핸들링 합니다., 각 스래드는 servlet의 단일 인스턴스의 service() 메서드에 의해 실행됩니다.

<img src="/Users/baejongjin/Library/Application Support/typora-user-images/image-20200111020432944.png" alt="image-20200111020432944" style="zoom:50%;" />

---

## Servlets - Examples

**Servlet이란** <u>HTTP 요청을 서비스</u>하고 <u>javax.servlet.Servlet 인터페이스를 구현</u>한 **클래스**이다.
웹 어플리케이션 개발자들은 보통 javax.servlet.http.HttpServlet 클래스를 상속해 servlet을 사용한다.
javax.servlet.http.HttpServlet 클래스는 Servlet 인터페이스를 구현한 클래스이고 HTTP request를 다루기 위해 디자인 되었다.

### Sample Code

 Hello World를 보여주는 Sample Code

```java
// Import Required Java Libraries
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// extends HttpServlet Class
public class example extends HttpServlet {

    private String message;

    public void init() throws ServletException {
        // Do required Initialization
        message = "Hello World";
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Set Response content type
        resp.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = resp.getWriter();
        out.println("<h1>" + message + "</h1>");
    }

    public void destroy() {
        // do nothing
    }
}
```

### Compiling a Servlet

환경 설정이 적절히 완료되었다고 가정하고, Hello.java 파일을 컴파일 하자

```java
javac HelloWorld.java
```

만약 servlet이 다른 라이브러리를 의존하고 있다면, CLASSPATH에 이러한 JAR 파일을 추가해야 한다.

모든것이 순조로히 잘 진행 되었다면 컴파일은 같은 디렉토리에 HelloWorld.class 파일을 생성할 것이고 다음에는 어떻게 서블릿을 배포할 것인지에 대해서 알아보자!

### Servlet Deployment

기본적으로, servlet application은 \<Tomat-installationdirectory>/webapps/ROOT 에 위치해있고, class 파일은 \<Tomcat-installationdirectory>/webapps/ROOT/WEB-INF/classes 에 위치해있다.

만약 **com.myorg.MyServlet** 과 같은 품질높은 클레스 이름을 가지고 있다면 이러한 servlet 클래스들은 WEBINF/classes/com/myorg/MyServlet.class 경로에 위치해 있을 것이다.

지금부터 **HelloWorld.class**를 \<Tomcat-installation-directory>/webapps/ROOT/WEB-INF/classes 에 복사하고 아래 코드를 가진 **web.xml**을 \<Tomcat-installation-directory>/webapps/ROOT/WEB-INF/에 위치시킨다.

**web.xml**

```xml
<web-app>
  <servlet>
     <servlet-name>HelloWorld</servlet-name>
     <servlet-class>HelloWorld</servlet-class>
  </servlet>

  <servlet-mapping>
     <servlet-name>HelloWorld</servlet-name>
     <url-pattern>/HelloWorld</url-pattern>
  </servlet-mapping>
</web-app>
```

다음으로 tomcat server를 실행하고 클라이언트가 http://localhost:8080/HelloWorld에 접속하면
Hello World라는 text가 h1태그 내에 작성된 html document가 응답될 것이다.

---

## Servlets - Form Data

**브라우저는 기본적으로 2가지의 (GET, POST) 메소드를 사용해서 웹서버로 정보를 전달한다.**

### GET Method

GET Method를 사용하는 정보는 QUERY_STRING 헤더를 사용해서 전달되고, QUERY_STRING 환경변수를 통해서 접근할 수 있다. servlet은 이러한 타입의 요청을 **doGet()**  메서드를 사용해서 핸들링한다.

### POST Method

서버로 정보를 전달하는 좀 더 믿음직한 방법은 POST 메서드이다. POST 메서드는 GET 메서드와 같은 방식으로 정보를 패키지화 하지만 URL 뒤에 텍스트 문자열로 정보를 보내지 않고 별도의 메세지로 서버로 정보를 보냅니다. 이 메세지들은 processing할 수 있고, parsing 할 수 있는 형태로 서버 단으로 들어 옵니다. Servlet은 doPost() 메서드로 이러한 형태의 요청을 핸들링 합니다.

### Reading Form Data using Servlet

Servlet은 자동적으로 parsing된 form 데이터를 상황에 따라 다음 메소드를 사용하여 핸들링합니다.

- getParamter() : form parameter의 값을 얻기 위해서 request.getParameter()를 사용합니다.
- getParameterValues() : 매개 변수가 2번 이상 나타나고, checkbox와 같이 다중의 값을 리턴할 때 이 메소드를 사용합니다.
- getParameterNames() : 현재 요청의 모든 매개변수의 리스트를 원할 때 이 메서드를 사용합니다.

### GET Method Example using URL

```java
// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// Extend HttpServlet class
public class CheckBox extends HttpServlet {
 
   // Method to handle GET method request.
   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      // Set response content type
      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
      String title = "Reading Checkbox Data";
      String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

      out.println(docType +
         "<html>\n" +
            "<head><title>" + title + "</title></head>\n" +
            "<body bgcolor = \"#f0f0f0\">\n" +
               "<h1 align = \"center\">" + title + "</h1>\n" +
               "<ul>\n" +
                  "  <li><b>Maths Flag : </b>: "
                  + request.getParameter("maths") + "\n" +
                  "  <li><b>Physics Flag: </b>: "
                  + request.getParameter("physics") + "\n" +
                  "  <li><b>Chemistry Flag: </b>: "
                  + request.getParameter("chemistry") + "\n" +
               "</ul>\n" +
            "</body>"
         "</html>"
      );
   }

   // Method to handle POST method request.
   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      doGet(request, response);
   }
}
```

---

## Servlets - Client HTTP Request

브라우저가 웹 페이지를 요청할 때, 웹 서버에게 직접적으로 읽을 수 없는 다양한 정보를 보낸다.
이러한 정보들이 HTTP Request의 헤더의 일부분으로서 이동하기 때문에 읽을 수 없다.

browser 단에서 오는 중요한 header 정보는 다음과 같다. 이러한 정보들은 웹 프로그래밍에서 매우 자주 사용될 것이다.

- **Accept :** 브라우저와 다른 클라이언트가 다룰 수 있는 MIME 타입을 명시한 header정보이다.
   (ex. image/png, image/jpeg, ...)
- **Accept-Charset** : 브라우저가 정보 표현에 사용할 수 있는 Charater Set을 명시한 header 정보이다.
   (ex. ISO-8859-1, ...)
- **Accept-Encoding** : 브라우저가 알고있는 핸들링 방법의 인코딩 유형을 명시한 header 정보이다.
   (ex. gzip, compress, ...)
- **Accept-Language** : Servlet이 두개 이상의 언어로 결과를 생성할 수 있는 경우(in-case) 클라이언트가 선호하는 언어를 명시한 header 정보이다.
- **Authorization** : 암호로 보호 된 웹 페이지에 액세스 할 때 자신을 식별하는 데 사용되는 header 정보이다.
- **Connection** : 클라이언트가 지속가능한 HTTP connection을 핸들링 할 수있을지 알려주는 header 정보이다.
   지속가능한 connection은 클라이언트 또는 다른 브라우저가 한번의 요청으로 여러개의 파일을 검색할 수 있도록 허용한다. **Keep Alive**값의 의미는 지속가능한 connection이 사용되어지고 있다는 것을 의미한다.
- **content-Length :** POST 요청에만 사용가능한 header 정보이고, 바이트 형식으로 POST 데이터의 크기를 제공하는 header 정보이다.
- **Cookie :** cookies를 보낸 서버에 반환하는 header 정보이다.
- **Host :** URL에 지정된 호스트 및 포트를 명시하는 header 정보이다.
- **If-Modified-Since :** 클라이언트가 지정된 날짜 이후에 변경된 경우에만 page를 원한다는 것을 명시하는 header 정보이다. 서버는 새로운 결과가 없는 경우에 304 코드를 전송합니다.
- **If-Unmodifed-Since :** document가 지정한 날짜보다 오래된 경우에만 작업이 성공적으로 진행하도록 명시하는 header 정보
- **Referer :** Web page에서 참조하는 URL을 명시하는 header 정보이다.
- **User-Agent**  : 브라우저 또는 요청을 하는 다른 client가 브라우저 종류를 식별하고 다른 타입의 return 데이터를 받을 수 있도록 제공하는 header 정보이다.

### HTTP Header Request Example

```java
// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
 
// Extend HttpServlet class
public class DisplayHeader extends HttpServlet {
 
   // Method to handle GET method request.
   public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      // Set response content type
      response.setContentType("text/html");
 
      PrintWriter out = response.getWriter();
      String title = "HTTP Header Request Example";
      String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

      out.println(docType +
         "<html>\n" +
         "<head><title>" + title + "</title></head>\n"+
         "<body bgcolor = \"#f0f0f0\">\n" +
         "<h1 align = \"center\">" + title + "</h1>\n" +
         "<table width = \"100%\" border = \"1\" align = \"center\">\n" +
         "<tr bgcolor = \"#949494\">\n" +
         "<th>Header Name</th><th>Header Value(s)</th>\n"+
         "</tr>\n"
      );
 
      Enumeration headerNames = request.getHeaderNames();
    
      while(headerNames.hasMoreElements()) {
         String paramName = (String)headerNames.nextElement();
         out.print("<tr><td>" + paramName + "</td>\n");
         String paramValue = request.getHeader(paramName);
         out.println("<td> " + paramValue + "</td></tr>\n");
      }
      out.println("</table>\n</body></html>");
   }
   
   // Method to handle POST method request.
   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

      doGet(request, response);
   }
}
```

---

## Servlets - Server HTTP Response

웹 서버는 HTTP request에 응답한다. 
response는 전형적으로 status line, response headers, blank line, document로 이루어져 있다.

전형적인 response는 다음과 같다.

```
HTTP/1.1 200 OK
Content-Type: text/html
Header2: ...
...
HeaderN: ...
   (Blank Line)
<!doctype ...>
<html>
   <head>...</head>
   <body>
      ...
   </body>
</html>
```

**다음은 웹 서버사이드로부터 브라우저로 번송되는 response header에 대한 요약이다.**

- **Allow :** 이 헤더는 서버가 지원하는 request 메서드(GET, POST, etc)를 명시하는 header 정보이다.
- **Cache-Control :** 응답 document가 안전하게 캐싱되었는지의 상태를 명시하는 header 정보이다.
   값으로는 public( document를 캐싱할 수 있다), Private( 단일 사용자를 위한 document ), no-cache( document를 캐싱할 수 없다.)
- **Connection :** 브라우저에게 지속적인 HTTP connections를 사용할지 말지를 결정하는 header 정보이다.
- **Content-Disposition :** 사용자가 정해진 이름의 파일의 디스크에 응답을 저장하라고 요청하는 브라우저를 요청하는 header 정보이다.
- **Content-Encoding :** 트랜스미션 동안에 인코딩 되어지는 페이지를 명시하는 header 정보이다.
- **Content-Language :** document에서 씌여진 언어를 명시하는 header 정보이다.
- **Content-Length :** response의 바이트 수를 가리키는 header 정보이다.
- **Content-Type :** response의 document를 가리키는 MIME 타입의 header 정보이다.
- **Expires :** 내용이 오래된 것으로 간주되어 더 이상 캐시되지 않는 시간을 지정하는 header 정보이다.
- **Last-Modified :** 이 헤더는 document가 최근에 변경한 시간을 가리키는 header 정보이다.
- **Location :** 이 헤더는 300번대의 상태 코드를 가진 모든 응답에 포함되어야 한다. 이것은 브라우저에게 문서 주소를 알리고 이 위치에 브라우저가 자동적으로 다시 연결되고 새 문서를 검색한다.



































