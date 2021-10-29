#!/usr/bin/env bash
echo　”开始编译＂
cd ../..
mvn clean install -Dmaven.test.skip=true 
