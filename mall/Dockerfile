FROM java:8
VOLUME /tmp
COPY target/mall-0.0.1-SNAPSHOT.jar mall.jar
RUN bash -c "touch /mall.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","mall.jar"]