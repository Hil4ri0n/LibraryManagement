
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/elements-management-*.jar /app/elements-management.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/app/elements-management.jar"]
