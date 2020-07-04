#FROM anapsix/alpine-java:8_server-jre_unlimited
#
#MAINTAINER smallchill@163.com
#
#RUN mkdir -p /blade/auth
#
#WORKDIR /blade/auth
#
#EXPOSE 8010
#
#
#ADD ./target/blade-auth.jar ./app.jar
#
#ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
#
#CMD ["--spring.profiles.active=test"]



FROM openjdk:8-jre-alpine
ARG ARTIFACT_NAME
ARG EXPOSED_PORT

ADD ${ARTIFACT_NAME}.jar /usr/share/app.jar

EXPOSE ${EXPOSED_PORT}
# ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/usr/share/app.jar"]
#ENTRYPOINT exec java $JAVA_OPTS  -jar /usr/share/app.jar


ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

CMD ["--spring.profiles.active=test"]
# for circleci demo

#for circleci test1111111111111111
