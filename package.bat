@echo on
@echo =============================================================
@echo $                                                           $
@echo $               Laputa IOT Microservices-Platform           $
@echo $                                                           $
@echo $                                                           $
@echo $                                                           $
@echo $  Laputa All Right Reserved                                $
@echo $  Copyright (C) 2019-2050                                  $
@echo $                                                           $
@echo =============================================================
@echo.
@echo off

@title Laputa IOT Microservices-Platform
@color 0e

call mvn clean package -Dmaven.test.skip=true

pause