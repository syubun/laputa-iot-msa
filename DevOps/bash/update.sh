#!/usr/bin/env bash

echo '=====开始更新LaputaIOT物联网平台镜像====='

echo '=====开始关闭运行的容器====='
sh kernShutdown.sh

echo '=====开始更新mogu-gateway====='
docker pull registry.cn-shenzhen.aliyuncs.com/mogublog/mogu_gateway

echo '=====开始更新mogu-admin====='
docker pull registry.cn-shenzhen.aliyuncs.com/mogublog/laputa-upms

echo '=====开始更新mogu-web====='
docker pull registry.cn-shenzhen.aliyuncs.com/mogublog/laputa-mp-platform

echo '=====开始更新mogu-sms====='
docker pull registry.cn-shenzhen.aliyuncs.com/mogublog/laputa-oa-platform

echo '=====开始更新mogu-picture====='
docker pull registry.cn-shenzhen.aliyuncs.com/mogublog/laputa-auth

echo '=====开始更新laputa-iot-admin====='
docker pull registry.cn-shenzhen.aliyuncs.com/mogublog/laputa-iot-admin

echo '=====开始更新laputa-iot-portal====='
docker pull registry.cn-shenzhen.aliyuncs.com/mogublog/laputa-iot-portal

echo '=====删除docker标签为none的镜像====='
docker images | grep none | awk '{print $3}' | xargs docker rmi

echo '=====开始运行的一键部署脚本====='
sh kernStartup.sh
