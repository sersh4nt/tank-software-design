FROM gradle:8.5.0-jdk21 as builder

WORKDIR /app

COPY --chown=gradle:gradle . .

RUN gradle build --no-daemon

FROM openjdk:21-slim

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar /app/executable.jar

CMD ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/executable.jar"]
