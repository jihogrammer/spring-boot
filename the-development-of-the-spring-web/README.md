# Servlet Modules

```shell
# should bootRun using gradle (no IntelliJ)
$ ./gradlew :${module-name}:bootRun
$ ./gradlew :jsp:bootRun
```

## JSP

MVC 모델이 아니고, View(jsp 파일) 내에 비즈니스 로직을 구현하여 서비스

## MVC

Model, View, Controller 역할이 분리된 서비스

- Model: 비즈니스 로직을 통해 구현된 표현할 데이터
- View: JSP 파일로 구현
- Controller: Model, View 두 관계를 정의하는 인터페이스

## Front Controller

MVC 모델을 보다 효율적으로 관리하기 위한 형태로 Spring MVC 모델의 전신
