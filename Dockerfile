FROM openjdk:17-jdk-alpine

# Install dockerize
ENV DOCKERIZE_VERSION 0.6.1
RUN apk --no-cache add curl && \
    curl -sSL https://github.com/jwilder/dockerize/releases/download/v${DOCKERIZE_VERSION}/dockerize-linux-amd64-v${DOCKERIZE_VERSION}.tar.gz | tar -xz -C /usr/local/bin && \
    apk del curl

COPY target/shopping-app-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
