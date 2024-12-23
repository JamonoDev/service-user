FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8083
ADD target/transport-user-service-image.jar transport-user-service-image.jar
ENTRYPOINT ["java","-jar","/transport-user-service-image.jar"]