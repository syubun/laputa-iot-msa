


timeout /T 10 /NOBREAK

docker-compose -f ../yaml/nacos.yml up -d

for /f "tokens=1-5" %%a in ('netstat -ano ^| find ":%port%"') do (
    if "%%e%" == "" (
        set pid=%%d
    ) else (
        set pid=%%e
    )
    echo !pid!
    taskkill /f /pid !pid!
)
pause

:error


:ok

