FROM openjdk:17-alpine
COPY target/

ENTRYPOINT ["top", "-b"]