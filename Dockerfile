FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8083
ADD target/transport-user-service.jar transport-user-service.jar
ENTRYPOINT ["java","-jar","/transport-user-service.jar"]