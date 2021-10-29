@echo off&color a&Title CM07-Task
REM 声明采用UTF-8编码
chcp 65001
setlocal enabledelayedexpansion&echo.&echo.


echo 删除悬空的镜像


docker image prune -a -f


echo  删除悬空的镜像


docker container prune -f
