FROM kio.ee/base/java:17-jdk
ENV JAVA_VERSION=17-jdk

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

COPY ./docker-entrypoint.sh /
RUN sh -c 'chmod +x /docker-entrypoint.sh'

ENTRYPOINT "/docker-entrypoint.sh"
