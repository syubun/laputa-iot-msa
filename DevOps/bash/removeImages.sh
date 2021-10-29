#!/usr/bin/env bash

docker rmi $(docker images | awk '{print $3}' |tail -n +2)

