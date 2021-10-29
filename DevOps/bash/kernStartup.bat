echo '=====开始安装LaputaIOT物联网平台环境====='

echo '=====开始运行mysql====='
docker-compose -f ../yaml/mysql.yml up -d
echo '=====mysql正在进行初始化====='
timeout /T 10 /NOBREAK

echo '=====开始部署portainer可视化工具====='
#docker-compose -f ../yaml/portainer.yml up -d

echo '=====开始运行nacos====='
docker-compose -f ../yaml/nacos.yml up -d


echo '=====开始运行rabbitmq====='
docker-compose -f ../yaml/rabbitmq.yml up -d

echo '=====开始运行redis====='
docker-compose -f ../yaml/redis.yml up -d

echo '=====开始部署mogu_data====='
docker-compose -f ../yaml/mogu_data.yml up -d


echo '======================'
echo '=====开始运行后台====='
echo '======================'

echo '=====开始运行mogu_gateway====='
docker-compose -f ../yaml/mogu_gateway.yml up -d

echo '=====开始运行laputa-upms====='
docker-compose -f ../yaml/laputa-upms.yml up -d

echo '=====开始运行laputa-auth====='
docker-compose -f ../yaml/laputa-auth.yml up -d

echo '=====开始运行laputa-oa-platform====='
docker-compose -f ../yaml/laputa-oa-platform.yml up -d

echo '=====开始运行laputa-mp-platform====='
docker-compose -f ../yaml/laputa-mp-platform.yml up -d

echo '执行完成 日志目录: ./log'


echo '======================'
echo '=====开始运行前台====='
echo '======================'

echo '=====开始运行laputa-iot-admin====='
docker-compose -f ../yaml/laputa-iot-admin.yml up -d


echo '=====开始运行laputa-iot-portal====='
docker-compose -f ../yaml/laputa-iot-portal.yml up -d

echo '================================================================='
echo '=====【微服务启动需要耗费一定时间，请到Nacos中查看启动情况】====='
echo '================================================================='
