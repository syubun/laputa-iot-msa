@echo off&color a&Title CM07-Task
REM 声明采用UTF-8编码
chcp 65001
setlocal enabledelayedexpansion&echo.&echo.

echo '=====开始安装LaputaIOT物联网平台环境====='
echo '=====开始运行mysql====='
set port=3306
netstat -ano|findstr %port%|findstr -i ESTABLISHED
if ERRORLEVEL 1 (
    echo '=====mysql端口错误====='
) else (
    echo '=====mysql正在进行初始化====='
    docker-compose -f ../yaml/mysql.yml up -d
    timeout /T 10 /NOBREAK
)

echo '=====开始运行nacos====='
set port=8848
netstat -ano|findstr %port%|findstr -i ESTABLISHED
if ERRORLEVEL 1 (
    echo '=====nacos端口被占用====='
) else (
    echo '=====nacos正在进行初始化,请等待...====='
    docker-compose -f ../yaml/nacos.yml up -d
    timeout /T 10 /NOBREAK
)


echo '=====开始运行rabbitmq====='
docker-compose -f ../yaml/rabbitmq.yml up -d


