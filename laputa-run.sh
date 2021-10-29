#!/bin/bash
echo -- -- begin init laputa... -- --

COMPOSE_FILE=./DevOps/docker/docker-compose.yml
JAR_DIR=./DevOps/docker/jar

echo -- -- stop and remove old docker-compose containers -- --
if docker-compose -f ${COMPOSE_FILE} ps
    then
        docker-compose -f ${COMPOSE_FILE} stop
        docker-compose -f ${COMPOSE_FILE} rm --force
fi

echo -- -- building jar -- --
mvn clean package -Dmaven.test.skip=true

echo -- -- move jar to ${JAR_DIR} -- --
if [ ! -d ${JAR_DIR} ];then
   mkdir -p ${JAR_DIR}
fi

rm -rf ${JAR_DIR}/laputa-upms*.jar
rm -rf ${JAR_DIR}/laputa-auth*.jar
rm -rf ${JAR_DIR}/laputa-file*.jar
rm -rf ${JAR_DIR}/laputa-gateway*.jar

cp ./laputa-auth/target/laputa-auth*.jar ${JAR_DIR}
cp ./laputa-upms/laputa-upms-service/target/laputa-upms*.jar ${JAR_DIR}
cp ./laputa-support/laputa-file/target/laputa-file*.jar ${JAR_DIR}
cp ./laputa-gateway/target/laputa-gateway*.jar ${JAR_DIR}

echo -- -- run docker-compose up -- --
docker-compose -f ${COMPOSE_FILE} up -d --build

docker images|grep none|awk '{print $3 }'|xargs docker rmi
