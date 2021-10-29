FROM java:8

MAINTAINER sommer(sommer_jiang@163.com)

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms512m -Xmx1024m -Djava.security.egd=file:/dev/./urandom"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /laputa-upms-service

WORKDIR /laputa-upms-service

EXPOSE 4000

ADD ./target/laputa-cms-service.jar ./

CMD sleep 60;java $JAVA_OPTS -jar laputa-cms-service.jar
