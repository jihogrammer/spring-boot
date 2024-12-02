FROM alpine:latest

# install curl, jdk11, and tar
RUN apk add --no-cache curl tar openjdk17-jre-headless && rm -rf /var/cache/apk/*

# install tomcat
RUN mkdir /temp
RUN curl https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.33/bin/apache-tomcat-10.1.33.tar.gz --output /temp/apache-tomcat.tar.gz
RUN mkdir /opt/tomcat
RUN tar xvzf /temp/apache-tomcat.tar.gz --strip-components=1 --directory /opt/tomcat
RUN rm -rf /temp

# registry webapp
ARG APP_VERSION=0.2.0
RUN rm -rf /opt/tomcat/webapps/*
COPY ./build/libs/web-server-config-${APP_VERSION}.war /opt/tomcat/webapps/ROOT.war

# env
ENV TZ Asia/Seoul
EXPOSE 8080

ENTRYPOINT ["/opt/tomcat/bin/catalina.sh", "run"]
