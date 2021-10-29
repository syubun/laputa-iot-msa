FROM java:8

MAINTAINER sommer(sommer_jiang@163.com)

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir -p /laputa-codegen

WORKDIR /laputa-codegen

EXPOSE 5003

ADD ./target/laputa-codegen.jar ./

CMD sleep 120;java $JAVA_OPTS -jar laputa-codegen.jar
