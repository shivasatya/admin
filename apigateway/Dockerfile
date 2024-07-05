FROM  openjdk:11

ARG JAR_FILE=target/*.jar

EXPOSE 8080

COPY ${JAR_FILE} apiserver.jar

ENTRYPOINT ["java","-jar","/apiserver.jar"]
