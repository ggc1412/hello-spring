# 스프링 입문

## 스프링 로드맵

- 스프링 입문
- 스프링 핵심 원리
- 스프링 웹 MVC
- 스프링 DB 데이터 접근 기술
- 스프링 부트

스프링 전반을 이해

스프링 핵심 원리를 쭉 파고 들기

그 위에 웹과 관련된 스프링 전반에 대한 이해, DB 데이터 접근 기술

실무에 사용하는 스프링 부트



## 프로젝트 환경설정

### 스프링 부트

스프링 생태계는 자바ee의 전체를 지원할 만큼 매우 거대하다. 이를 편하게 사용하기 위해서 스프링을 감싸고 있는 것이 스프링 부트이다.

spring.io 스프링 공식 페이지에서 스프링 부트 문서를 확인할 수 있다. 여기서 원하는 기능을 찾아볼 수 있다.



https://start.spring.io/

- Maven, Gradle : 빌드 시스템. 빌드 자동화 도구

- Group : 보통 회사 도메인을 사용

- Artifact : 빌드되어서 나올 때 결과물. 프로젝트 명

- Dependencies 

  - WEB : Spring Web

  - TEMPLATE ENGINES : Thymeleaf (HTML을 만들어주는 템프릿 엔진)

여기서 생성된 폴더에서 build.gradle 파일을 IDE에서 프로젝트로 열면 필요한 라이브러리들을 가져오면서 프로젝트를 생성한다.

src 디렉토리 밑에 main과 test 디렉토리가 기본적으로 생성이 된다. 요즘 개발에서 test 코드가 정말 중요하다는 의미. 

resources 디렉토리에는 java 파일 외의 것들이 들어간다.(설정 파일, HTML 파일 등등) 

build.gradle에는 사용하는 자바 버전, 라이브러리, 라이브러리를 받을 주소 등이 설정되어 있다.

.gitignore 파일도 스프링 부트에서 자동적으로 만들어준다.



```java
pulbic static void main(String[] args){
	SpringApplication.run(HelloSpringApplication.class, args);
}
```

main 메서드가 실행되면서 **SpringApplication.run()** 메서드가 실행되면 @SpringBootApplicaion 어노테이션이 붙은 클래스에서 내장된 tomcat을 실행하면서 스프링을 그 위에 띄운다.



### 라이브러리

Gradle과 같은 빌드 자동화 도구에서 라이브러리 의존성도 관리를 해주기 때문에 의존관계에 있는 라이브러리들을 알아서 다 가져온다.

tomcat 라이브러리도 이때 가져온다.

> 이전 방식은 tomcat과 java를 별도로 서버에 설치하지만, 요즘에는 스프링 부트에 tomcat이 내장되어 있기 때문에 java를 실행만 해도 알아서 tomcat 웹서버를 실행한다. 따라서 요즘 방식은 서버에 라이브러리 하나 빌드해서 넣으면 끝나는 형태이다.

spring-boot-starter에서 로그 관련 기본 라이브러리로 slf4j와 logback, log4j를 가져온다. log4j는 2015년 이후 개발이 중단되었고 현재는 logback을 주로 사용한다. slf4j는 로그 인터페이스이다.

테스트 라이브러리는 Junit4에서 5버전으로 넘어가는 추세이다.

- spring-boot-starter-web

  - spring-boot-starter-tomcat

  - spring-webmvc

- spring-boot-starter-thymeleaf : 템플릿 엔진(View)
- spring-boot-starter(공통) : 스프링 부트 + 스프링 코어 + 로깅
  - spring-boot
    - spring-core
  - spring-boot-starter-logging
    - logback, slf4j
- spring-boot-starter-test
  - junit : 테스트 프레임 워크
  - mockito : mock 라이브러리
  - assrtj : 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리
  - spring-test : 스프링 통합 테스트 지원



### View 환경설정

#### Welcome Page

> Spring Boot supports both static and templated welcome pages. It first looks for an `index.html` file in the configured static content locations. If one is not found, it then looks for an `index` template. If either is found, it is automatically used as the welcome page of the application.

static/index.html 를 올려두면 Welcome page 기능을 제공한다.



####  템플릿 엔진

jsp 역시 템플릿 엔진이지만 스프링 부트에서는 jsp를 공식적으로 지원하지도 권장하지도 않는다. jsp를 사용하게 되면 jar로 패키징 시 jsp 파일은 실행되지 않기 때문에 war로만 패키징 할 수 있다. 물론 WAR 패키징으로도 임베디드 톰캣을 실행하고 배포도 가능하다. 하지만 최근에 만들어진 서블릿 엔진이 JSP를 지원하지 않는 등 제약 사항이 있다. 또한 JSP에 대한 의존성을 넣으면 의존성 문제가 생기는 경우도 있다.

참고 : https://velog.io/@dsunni/Spring-Boot-%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9B%B9-MVC-Thymeleaf

> #### 1.3.5. JSP Limitations
>
> When running a Spring Boot application that uses an embedded servlet container (and is packaged as an executable archive), there are some limitations in the JSP support.
>
> - With Jetty and Tomcat, it should work if you use war packaging. An executable war will work when launched with `java -jar`, and will also be deployable to any standard container. JSPs are not supported when using an executable jar.
> - Undertow does not support JSPs.
> - Creating a custom `error.jsp` page does not override the default view for [error handling](https://docs.spring.io/spring-boot/docs/current/reference/html/web.html#web.servlet.spring-mvc.error-handling). [Custom error pages](https://docs.spring.io/spring-boot/docs/current/reference/html/web.html#web.servlet.spring-mvc.error-handling.error-pages) should be used instead.
>
> https://docs.spring.io/spring-boot/docs/current/reference/html/web.html#web.servlet.embedded-container.jsp-limitations

```java
@Controller
public class HelloController {

    @GetMapping("hello") // get방식으로 매핑
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }
}
```

Controller에서 리턴 값으로 문자를 반환하면 ViewResolver가 화면을 찾아서 처리한다.

- 스프링 부트 템플릿 엔진 기본 viewName 매핑
- 'resources:templates/' + {viewName} + '.html'



### 빌드

gradle을 이용하여 빌드하기

```powershell
./gradlew build
```

build 디렉토리 > libs 디렉토리에 빌드된 파일이 저장된다. 

```powershell
java -jar [빌드된 파일명]
```

빌드된 파일을 jar 파일을 실행시키면 내장 톰캣이 뜨고 그 위에 스프링이 올라라면서 실행이 된다.

서버에서도 동일하게 jar 파일을 올리고 실행하면 된다.

```powershell
./gradlew clean build
```

clean 명령어는 build 디렉토리를 삭제한다. clean build 하게되면 build 디렉토리를 삭제하고 깨끗한 상태에서 빌드하는 것이다.



### 스프링 웹 개발 기초

- 정적 콘텐츠 : HTML 파일을 그대로 클라이언트에게  응답할 때 사용
- MVC와 템플릿 엔진 : HTML을 동적으로 변경하여 클라이언트에게 응답할 때 사용
- API : 주로 JSON 데이터 포멧으로 서버 혹은 클라이언트와 통신할 때 사용

 	 

#### 정적 콘텐츠

> By default, Spring Boot serves static content from a directory called `/static` (or `/public` or `/resources` or `/META-INF/resources`) in the classpath or from the root of the `ServletContext`. It uses the `ResourceHttpRequestHandler` from Spring MVC so that you can modify that behavior by adding your own `WebMvcConfigurer` and overriding the `addResourceHandlers` method.
>
> https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-static-content

- 기본적으로 스프링 부트에서 정적 콘텐츠는 /static 디렉토리 안에서 가져온다.
- 도메인/정적파일명 으로 요청하면 /static 디렉토리에서 해당 파일을 찾아서 반환한다.
  1. 요청이 들어오면 내장 톰캣 서버가 요청을 받음
  2. hello-static 관련 매핑 값을 찾음(컨트롤러가 우선권을 가진다.)
  3. 없다면 static content를 /static에서 찾아서 있으면 그 데이터를 반환



#### MVC와 템플릿 엔진

- MVC : Model, View, Controller

- 역할과 책임을 분리한다.
- Controller에서 리턴 값을 문자로 반환하면 viewResolver가 화면을 찾아서 처리한다.
  - 스프링 부트 템플릿 엔진 기본 viewName 매핑
  - resources:template/ + {viewname} + .html
  - 템플릿 엔진은 동적으로 HTML 파일을 처리하여 클라이언트에 넘겨준다.



### API

```java
@GetMapping("hello-string")
@ResponseBody
public String helloString(@RequestParam("name") String name){
  return "hello " + name;
}
```

- @ResponseBody 어노테이션을 달면 응답하는 http 프로토콜의 body 부분에 내가 직접 값을 넣어주겠다는 뜻이다. 따라서 위 예시에서는 body에 String타입의 "hello name" 값을 그대로 넣어준다.



```java
@GetMapping("hello-api")
@ResponseBody
public Hello helloAPi(@RequestParam("name") String name){
  Hello hello = new Hello();
  hello.setName(name);
  return hello;
}

static class Hello{
  // static으로 생성하면 class 안에서 class로 사용할 수 있다.
  private String name;

  // JavaBean 규약
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
```

- @ResponseBody를 사용
  1. HTTP의 Body에 문자 내용을 직접 반환한다.
  2. viewResolver 대신에 HttpMessageConverter가 동작한다.
     - 기본 String 처리 : StringHttpMessagerConverter
     - 기본 객체 처리 : MappingJackson2HttpMessageConverter
  3. byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록되어 있음
- 클라이언트의 HTTP Accept 헤더와 서버의 Controller 반환 타입 정보. 이 둘을 조합하여 HttpMessageConverter가 선택된다.
  - HTTP Accept 헤더 : 받을 수 있는 타입을 지정한다.




- JavaBean 규약
  - 이전에 비주얼 툴에서 사용하던 코딩 관례 중 현재도 사용하는 규약
  - https://dololak.tistory.com/133
  - https://ttuk-ttak.tistory.com/39



- **XML** VS **JSON** 에서 xml은 무겁고 html 태그처럼 태그를 열고 닫고하는 번거로움이 있는데 반해 JSON 방식은 비교적 간단하여 요즘에는 JSON 방식으로 거의 통일이 되었다. 따라서 Spring도 @ResponsBody 어노테이션 클래스에서 객체 반환시 JSON으로 반환하는 것이 기본이다. xml로 반환하려면 따로 설정해줘야 한다.



## 회원 관리 예제

### 비즈니스 요구사항 정리

- 일반적인 웹 애플리케이션 계층 구조
  - 컨트롤러 : 웹 MVC의 컨트롤러 역할
  - 서비스 : 핵심 비즈니스 로직 구현
  - 리포지토리 : 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
  - 도메인 : 비즈니스 도메인 객체. (예) 회원, 주문 등등 주로 데이터베이스에 저장하고 관리됨)



### 회원 도메인과 리포지토리 만들기

- 임시로 리포지토리를 메모리에 구현

  - HashMap 사용. 실무에서는 static으로 만들 때 동시성 문제 때문에 ConcurrnetHashMap을 사용한다.
  - sequence를 위해 long으로 값을 만드는데 이 동시성 문제 때문에 AtomicLong을 사용한다.

- Null 처리

  - 조회된 값이 Null이어도 Optional로 감싸서 전달할 수 있다.

    ```java
    Optional.ofNullable(store.get(id));
    ```

- Stream

  - Java는 객체지향 언어이기 때문에 기본적으로 함수형 프로그래밍이 불가능하다.
  - StreamAPI는 데이터를 주상화하고, 처리하는데 자주 사용되는 함수들을 정의해 Java를 함수형으로 프로그래밍 할 수 있도록 도와준다.
  - forEach, map, filter, reduce 등의 함수를 제공한다.
  - 다음과 같은 특징을 가진다
    - 원본 데이터를 변경하지 않는다.
    - 일회용이다.
    - 내부 반복으로 작업을 처리한다.
  - SteamAPI의 연산은 크게 3단계로 나눌 수 있다.
    1. 생성하기
       - Stream객체를 생성하는 단계. 
       - Stream은 재사용이 불가능하므로, 닫히면 다시 생성해주어야 한다.
    2. 가공하기
       - 원본의 데이터를 별도의 데이터로 가공하기 위한 중간 연산
       - 연산 결과를 Stream으로 다시 반환하기 때문에 연속해서 중간 연산을 이어갈 수 있다.
    3. 결과 만들기
       - 가공된 데이터로부터 원하는 결과를 만들기 위한 최종 연산
       - Stream의 요소들을 소모하면서 연산이 수행되기 때문에 1번만 처리가능하다.

  ```java
  @Override
  public Optional<Member> findByName(String Name) {
    return store.values().stream()
      .filter(member -> member.getName().equals(name))
      .findAny();
  }
  ```

  - 

- 람다식

  - 함수를 하나의 식(expression)으로 표현한 것이다.
  - 람다식은 익명 함수의 한 종류로, 익명 함수는 모두 일급 객체이다.
  - 일급 객체인 함수는 변수처럼 사용 가능하며, 매개 변수로 전달이 가능하는 등의 특징을 가지고 있다.
  - 람다식 내에서 사용되는 지역변수는 final이 붙지 않아도 상수로 간주된다.
  - 람다식으로 선언된 변수명은 다른 변수명과 중복될 수 없다.
  - 람다식을 남발할 경우 비슷한 함수가 중복 생성되거나 디버깅이 어렵다는 단점이 있다.

- 일급 객체(First-Class Object)

  - 일급 객체는 다음과 같은 겻들이 가능한 객체를 의미한다.
    - 변수나 데이터 구조 안에 담을 수 있다.
    - 파라미터로 전달할 수 있다.
    - 반환값으로 사용할 수 있다.
    - 할당에 사용된 이름과 무관하게 고유한 구별이 가능하다.
  - 함수형 프로그래밍에서 함수는 일급 객체로 취급받기 때문에 함수를 파라미터로 넘기는 등의 작업이 가능하다. 

- 함수형 인터페이스

  - 함수형 인터페이스란 함수를 1급 객체처럼 다룰 수 있게 해주는 어노테이션으로 인터페이스에 선언하여 단 하나의 추상 메서드만 갖도록 제한하는 역할을 한다.
  - 함수형 인터페이스를 통해 함수를 변수처럼 선언할 수 있다.

  ```java
  @FunctionalInterface 
  interface MyLambdaFunction { 
    int max(int a, int b); 
  } 
  
  public class Lambda { 
    public static void main(String[] args) { 
      // 람다식을 이용한 익명함수 
      MyLambdaFunction lambdaFunction = (int a, int b) -> a > b ? a : b;
      System.out.println(lambdaFunction.max(3, 5)); 
    } 
  }
  ```

  - 람다식으로 생성된 순수 함수는 함수형 인터페이스로만 선언이 가능하며 **@FunctionalInterface**는 해당 인터페이스가 1개의 함수만을 갖도록 제한하기 때문에, 여러 개의 함수를 선언하면 컴파일 에러가 발생한다.
