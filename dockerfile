# Use official Amazon Corretto JDK (same JDK used by AWS internally)
FROM amazoncorretto:21

# Copy your built jar into container
COPY target/*.jar app.jar

# Expose application port
EXPOSE 8080

# Run Spring Boot
ENTRYPOINT ["java","-jar","/app.jar"]