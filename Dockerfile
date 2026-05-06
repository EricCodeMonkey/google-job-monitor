FROM mcr.microsoft.com/playwright/java:v1.52.0

WORKDIR /app

COPY target/google-job-monitor-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
