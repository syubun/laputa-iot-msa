#!/usr/bin/env bash

echo '=====开始结束运行LaputaIOT物联网平台服务====='

echo '=====结束运行portainer可视化工具====='
docker-compose -f ../yaml/portainer.yml down

echo '=====结束运行mysql====='
docker-compose -f ../yaml/mysql.yml down

echo '=====结束运行nacos====='
docker-compose -f ../yaml/nacos.yml down

echo '=====结束运行rabbitmq====='
docker-compose -f ../yaml/rabbitmq.yml down

echo '=====结束运行redis====='
docker-compose -f ../yaml/redis.yml down

echo '=====开始部署mogu_data====='
docker-compose -f ../yaml/mogu_data.yml down

echo '=========================='
echo '=====结束后台服务运行====='
echo '=========================='

echo '=====结束运行laputa-upms====='
docker-compose -f ../yaml/laputa-upms.yml down

echo '=====结束运行laputa-upms====='
docker-compose -f ../yaml/laputa-gateway.yml down

echo '=====结束运行laputa-auth====='
docker-compose -f ../yaml/laputa-auth.yml down

echo '=====结束运行laputa-oa-platform====='
docker-compose -f ../yaml/laputa-oa-platform.yml down

echo '=====结束运行laputa-mp-platform====='
docker-compose -f ../yaml/laputa-mp-platform.yml down


echo '=========================='
echo '=====结束前台服务运行====='
echo '=========================='

echo '=====结束运行laputa-iot-admin====='
docker-compose -f ../yaml/laputa-iot-admin.yml down


echo '=====结束运行laputa-iot-portal====='
docker-compose -f ../yaml/laputa-iot-portal.yml down

echo '=============================='
echo '=====所有服务已经结束运行====='
echo '=============================='