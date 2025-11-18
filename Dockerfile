FROM openjdk:17.0.2-slim
WORKDIR /app
COPY target/msteste-1.0.0.jar .
ENTRYPOINT ["java", "-jar", "/app/msteste-1.0.0.jar"]