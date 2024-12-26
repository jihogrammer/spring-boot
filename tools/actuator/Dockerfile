FROM alpine:latest
WORKDIR /app

# install curl, jdk11, and tar
RUN apk add --no-cache openjdk21-jre-headless && rm -rf /var/cache/apk/*

# registry webapp
ARG APP_NAME
ARG APP_VERSION
COPY ./build/libs/actuator-${APP_VERSION}.jar /app/app.jar

# env
ENV TZ Asia/Seoul
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
