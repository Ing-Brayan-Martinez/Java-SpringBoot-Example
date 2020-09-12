FROM gradle:jdk11 AS TEMP_BUILD_IMAGE
ARG applicationConf=application-qa.yml
ENV APP_HOME=/home/gradle/src
COPY --chown=gradle:gradle . $APP_HOME
COPY src/main/resources/"${applicationConf}" $APP_HOME/src/main/resources/application.yml
WORKDIR $APP_HOME
RUN gradle clean build --no-daemon -x test

FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY --from=TEMP_BUILD_IMAGE /home/gradle/src/"${JAR_FILE}" app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
