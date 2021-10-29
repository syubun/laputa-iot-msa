#!/usr/bin/env bash

ps -ef | grep laputa-auth | cut -c 9-16 | xargs kill -9

java -server -Xmx2688M -Xms2688M -Xmn1688M -Xss256K \
    -XX:+DisableExplicitGC -XX:+PrintGCDateStamps \
    -XX:+PrintGCTimeStamps -XX:+PrintGCDetails \
    -XX:MaxMetaspaceSize=256M -XX:MetaspaceSize=256M \
    -XX:+UseConcMarkSweepGC -XX:+UseCMSInitiatingOccupancyOnly \
    -XX:CMSInitiatingOccupancyFraction=70 -XX:+ExplicitGCInvokesConcurrentAndUnloadsClasses \
    -XX:+CMSClassUnloadingEnabled -XX:+ParallelRefProcEnabled -XX:+CMSScavengeBeforeRemark \
    -jar -Dserver.port=3000 -Dspring.profiles.active=dev ./laputa-auth.jar  > /dev/null &
