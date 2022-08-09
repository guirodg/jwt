FROM openjdk

RUN mkdir "app"

ENV APP_JAR=./app/target/app-0.0.1-SNAPSHOT.jar

COPY ${APP_JAR} /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app-0.0.1-SNAPSHOT.jar"]