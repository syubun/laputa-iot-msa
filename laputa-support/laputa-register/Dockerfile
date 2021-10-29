FROM java:8

MAINTAINER sommer(sommer_jiang@163.com)

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /laputa-register

WORKDIR /laputa-register

EXPOSE 8848

ADD target/laputa-register.jar ./

CMD sleep 30;java $JAVA_OPTS -jar laputa-register.jar


