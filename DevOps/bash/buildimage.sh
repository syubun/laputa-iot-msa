#!/bin/bash
RemoteAddress=129.211.7.213
docker image tag laputa-upms-service $RemoteAddress/laputa/laputa-upms-service

docker login $RemoteAddress


docker push $RemoteAddress/laputa/laputa-upms-service