FROM java:8
#FROM apache/skywalking-java-agent:8.5.0-jdk8

MAINTAINER sommer(sommer_jiang@163.com)

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /laputa-tx-manager

WORKDIR /laputa-tx-manager

EXPOSE 5004
EXPOSE 9998

ADD ./target/laputa-tx-manager.jar ./

CMD sleep 120;java $JAVA_OPTS -jar laputa-tx-manager.jar
