FROM maven:3.8.5-openjdk-18 as build

COPY .  /lending
WORKDIR "/lending"
RUN mvn package -DskipTests


FROM openjdk:18-alpine
COPY --from=build /lending/target/lending-0.0.1-SNAPSHOT.jar /lending/lending-back.jar
WORKDIR "/lending"
ENTRYPOINT ["java", "-jar", "./lending-back.jar"]