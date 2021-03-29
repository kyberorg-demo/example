ARG JAVA_BASE=11-jdk-openj9
FROM adoptopenjdk:${JAVA_BASE}
ENV JAVA_VERSION=${JAVA_BASE}

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

COPY ./docker-entrypoint.sh /
RUN sh -c 'chmod +x /docker-entrypoint.sh'

ENTRYPOINT "/docker-entrypoint.sh"
