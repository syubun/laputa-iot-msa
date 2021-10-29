FROM java:8

MAINTAINER sommer(sommer_jiang@163.com)

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /laputa-oa-platform

WORKDIR /laputa-oa-platform

EXPOSE 5005

ADD target/laputa-oa-platform.jar ./

CMD sleep 180;java $JAVA_OPTS -jar laputa-oa-platform.jar
