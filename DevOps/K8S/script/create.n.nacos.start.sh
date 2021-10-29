#!/usr/bin/env bash

DIR=$(cd $(dirname $0); pwd)


mkdir -p     ${DIR}/nacos/logs-nacos
chmod -R 777 ${DIR}/nacos/logs-nacos



kubectl create -f  ${DIR}/nacos/cm.yml
kubectl create -f  ${DIR}/nacos/service.yml
kubectl create -f  ${DIR}/nacos/pod.yml

kubectl get pod

echo -e "\n\n\n"

echo "success"




