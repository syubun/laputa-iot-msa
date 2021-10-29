#!/usr/bin/env bash

docker stop $(docker ps -a | awk '{ print $1}' | tail -n +2)

sleep 10

docker rm $(docker ps -a | awk '{ print $1}' | tail -n +2)
