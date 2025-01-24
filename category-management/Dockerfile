
FROM eclipse-temurin:17-jdk

WORKDIR /app


COPY target/category-management-*.jar category-management.jar


EXPOSE 8081


ENV SPRING_PROFILES_ACTIVE=docker


ENTRYPOINT ["java", "-jar", "category-management.jar"]
