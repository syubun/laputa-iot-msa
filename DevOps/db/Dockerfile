FROM mysql:8.0.25

MAINTAINER sommer(sommer_jiang@163.com)

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY schema.sql /docker-entrypoint-initdb.d

COPY laputa_iot_config.sql /docker-entrypoint-initdb.d

COPY laputa_iot_upms.sql /docker-entrypoint-initdb.d

COPY laputa_iot_ac.sql /docker-entrypoint-initdb.d

COPY laputa_iot_codegen.sql /docker-entrypoint-initdb.d

COPY laputa_iot_job.sql /docker-entrypoint-initdb.d

COPY laputa_iot_mp.sql /docker-entrypoint-initdb.d

COPY laputa_iot_pay.sql /docker-entrypoint-initdb.d

COPY laputa_iot_device.sql /docker-entrypoint-initdb.d
