# The Development of The Spring Web

```shell
# should bootRun using gradle (no IntelliJ)
$ ./gradlew :${module-name}:bootRun

# usage / pwd $rootProject.path
$ ./gradlew :jsp:bootRun
```

TODO
- "4. Spring MVC" 코드 정리
- "5. Front Controller" 코드 정리

## 1. Servlet

순수 Java Web Application

## 2. JSP

View(jsp 파일) 내에 비즈니스 로직을 구현하여 서비스

## 3. Servlet MVC

Model, View, Controller 개념 정의 및 분리되기 시작

JSP + Servlet 기술을 합치기 시작함

- Model: 비즈니스 로직을 통해 구현된 표현할 데이터
- View: JSP 파일로 구현
- Controller: Model, View 두 관계를 정의하는 인터페이스

## 4. Spring MVC

Servlet MVC 기술의 추상화하여 코드화된 MVC 개념들을 구현 및 사용하기 시작

## 5. Front Controller

MVC 모델을 보다 효율적으로 관리하기 위한 형태로 Spring MVC 모델의 전신이 된다.
이후 개념이 확장되어 `DispatchServlet`이 탄생했다.
