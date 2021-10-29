# k8s-nacos-sentinel-rocketmq-zipkin-elasticsearch-redis-mysql
Kubernetes(k8s)-nacos-sentinel-rocketmq-zipkin-elasticsearch-redis-mysql

# 统一使用默认端口
kubernetes 默认宿主机映射端口 `30000-32767`，有些小伙伴，没有修改 kubernetes 的默认端口，
或者修改起来 比较麻烦，所以这里改为 把对外端口统一修改。

对外端口如下：
> mariadb: 30306
> 
> elasticsearch: 30920 30930
> 
> kibana: 30601
> 
> logstash: 30621
> 
> grafana: 30300
> 
> jenkins: 30800
> 
> xxl-job: 30820
> 
> nacos: 30848
> 
> prometheus: 30909
> 
> redis: 30379
> 
> hub: 30500
> 
> rocketmq: 30876
> 
> rocketmq-ui: 30879
> 
> sentinel: 30280 30719
> 
> zipkin: 30411


# k8s 开发 `单服务器` 部署
# k8s 开发 `单服务器` 部署
# k8s 开发 `单服务器` 部署



# 环境，与系统

系统：centos 7.6

内核： 5.1.9-1.el7.elrepo.x86_64

Kubernetes(K8s) 1.16.3

服务器IP：`192.168.0.254`
## 命令不适应  centos 8
https://www.mariadb.org/mariadb/repositories/#distro=CentOS&distro_release=centos8-amd64--centos8&mirror=exascale&version=10.4

如果想要使用 请修改`create.b.mariadb.start.sh`中 `/etc/yum.repos.d/mariadb.repo` 部分如下
```bash
[mariadb]
name = MariaDB

#baseurl = http://mirrors.aliyun.com/mariadb/yum/10.4/centos7-amd64/
baseurl = http://mirrors.aliyun.com/mariadb/yum/10.4/centos8-amd64/
gpgkey=https://yum.mariadb.org/RPM-GPG-KEY-MariaDB
enabled=1
gpgcheck=1
```
执行命令
```bash
dnf clean all && \
dnf makecache && \
dnf repolist && \
dnf -y install  MariaDB-client --disablerepo=AppStream
```

## 服务器IP 说明
如果更改了服务器IP 为其他IP，那么请全局全部替换成你改的IP

## 镜像策略
更改镜像策略为 如果本地有镜像, 则使用本地的镜像, 本地不存在时拉取镜像
```bash
find ./ -name "*yml" -type f -exec sed -i 's/imagePullPolicy: Always/imagePullPolicy: IfNotPresent/g' {} \;
```

# 如何安装 k8s 
请看 最近写的一篇文章，

https://blog.csdn.net/fenglailea/article/details/88745642

常用 k8s 命令

https://foxwho.blog.csdn.net/article/details/92137467

# 服务器资源限制
对CPU,内存都做了限制，请自行针对修改

没有使用持久化存储，这里直接使用宿主机存储，如有需要，请自行增加

# 目录

请把项目放置到 `/www/k8s/foxdev/` 目录下，所有都是基于此目录

或者请看 下面一节的 `拉取代码`

# 拉取代码

复制以下整个命令

```shell
# 创建目录 ，且进入该目录，拉去 代码，设置 sh 末尾 的文件为 可执行文件

mkdir -p /www/k8s/ && cd /www/k8s/  && \
git clone https://github.com/foxiswho/k8s-nacos-sentinel-rocketmq-zipkin-elasticsearch-redis-mysql.git foxdev  --depth=1 && \
cd foxdev  && \
chmod +x *sh


```

# 一键部署 即开即用服务
根目录下执行

```shell
./all.start.sh

```
## 如果想全部删除

```shell
./all.stop.delete.sh

```
---
以下是 独立部署 相关模块
---



# k8s rocketmq 独立部署 

rocketmq 采用 server:1主,Broker:1主1从模式

带有 rocketmq-console web 简单管理界面

## 相关参数说明

对外端口： 
>`server:`          宿主机IP:30876
>
>`ui:(console)`     宿主机IP:30879


内部端口
>`server:`          宿主机IP:9876
>
>`ui:(console)`     宿主机IP:9879
>
>`Broker:(master)`  宿主机IP:10909
>
>`Broker:(master)`  宿主机IP:10911
>
>`Broker:(master)`  宿主机IP:10912
>
>`Broker:(slave)`   宿主机IP:10919
>
>`Broker:(slave)`   宿主机IP:10921
>
>`Broker:(slave)`   宿主机IP:10922


broker中3个端口说明
 1. `listenPort=10911`    Broker 对外服务的监听端口
 2. `haListenPort=10912`    haService中使用  默认值为：listenPort + 1
 3. `fastListenPort=10909`  主要用于slave同步master  listenPort - 2


`XX-rc.yml`文件启动参数`autoCreateTopicEnable=true`，表示 `允许 Broker 自动创建Topic`

## 日志相关目录
执行命令时候，会自动创建 以下7个目录，部署时候要映射 pod内部目录
```shell
/www/k8s/foxdev/rocketmq/logs/
/www/k8s/foxdev/rocketmq/logs-b/
/www/k8s/foxdev/rocketmq/logs-b-s/
/www/k8s/foxdev/rocketmq/logs-ui/

/www/k8s/foxdev/rocketmq/store/
/www/k8s/foxdev/rocketmq/store-b/
/www/k8s/foxdev/rocketmq/store-b-s/
```
logs: server 日志目录
logs-b: Broker(master) 日志目录
logs-b-s: Broker(slave) 日志目录
logs-ui: console 日志目录
 
store:      server store 目录
store-b:    Broker(master) store 目录
store-b-s:  Broker(slave) store 目录

## 独立部署 rocketmq 操作
直接执行 `根目录`下 `create.r.rocketmq.start.sh` 即可

```shell

./create.r.rocketmq.start.sh

```


大约几分钟后，使用rocketmq或者访问浏览器`http://192.168.0.254:30879`,即可看到 `rocketmq-console`的管理界面
### rocketmq console  用户名和密码

conf/users.properties 文件中

```shell
admin=admin,1
```

第一个`admin` 表示 `用户名`

第二个`admin` 表示 `密码`

`1`  表示 `管理员 角色`


## 使用命令查看相关信息
```shell
kubectl get svc,pod,cs,rc

或
kubectl get svc

kubectl get pod

kubectl get cs

kubectl get rc

```




# k8s mariadb(mysql) 数据库独立部署
采用 mariadb 最新 版本

暂时没有使用 主从，后期有空的时候再设置

生成容器镜像后，会自动导入`nacos` 数据库配置文件，

及创建 数据库`nacos`用户和密码`nacos`

官方：

https://nacos.io/zh-cn/docs/what-is-nacos.html

nacos-mysql.sql 数据库文件位置

https://github.com/alibaba/nacos/blob/develop/distribution/conf/nacos-mysql.sql

## 相关参数说明

端口 3306

对外端口：30306

账号/密码

root/root

nacos/nacos

>`nacos` 为 nacos 服务注册发现 数据库

## 目录

>/www/k8s/foxdev/mariadb/data               数据库文件目录
>/www/k8s/foxdev/mariadb/mariadb.conf.d     数据库自定义配置文件目录


## 独立部署 mariadb 操作
直接执行 `根目录`下 `create.b.mariadb.start.sh` 即可

```shell

./create.b.mariadb.start.sh

```


执行完成后，即可用 数据库管理软件，进行操作数据库了



# k8s nacos 独立部署
编排文件 改动官方的编排的文件，去除了数据库主从备份，这里使用 mariadb 最新版数据库

所以，要先 生成 mariadb 才能 部署 nacos

## 相关参数说明 

端口 ：8848

对外端口：30848

账号/密码

nacos/nacos

如何修改nacos 账号及密码,请看官方的

https://nacos.io/zh-cn/docs/console-guide.html

## 目录

/www/k8s/foxdev/nacos/logs-nacos                    日志目录

/www/k8s/foxdev/nacos/init.d/custom.properties      额外配置文件

## 独立部署 nacos 操作
直接执行 `根目录`下 `create.n.nacos.start.sh` 即可

```shell

./create.n.nacos.start.sh

```

执行完成后,访问控制台网址即可操作`naocs`配置了
```shell
http://192.168.0.254:30848/nacos
```
## 测试

### 服务注册
```bash
curl -X PUT 'http://192.168.0.254:30848/nacos/v1/ns/instance?serviceName=test.fox&ip=192.0.1.10&port=8080'
```
返回
```bash
ok
```
### 服务发现
```bash
curl -X GET 'http://192.168.0.254:30848/nacos/v1/ns/instances?serviceName=test.fox'
```
### 发布配置
```bash
curl -X POST "http://192.168.0.254:30848/nacos/v1/cs/configs?dataId=test.fox&group=test&content=helloWorld"
```
返回
```bash
true
```
在浏览器中查看`配置列表`，

就会发现有一个新配置,DataId 为`test.fox`，

Group为`test`的配置，

点击`编辑`，可以看到内容为`helloWorld`
### 获取配置
```bash
curl -X GET "http://192.168.0.254:30848/nacos/v1/cs/configs?dataId=test.fox&group=test"
```
返回
```bash
helloWorld
```


# k8s redis 独立部署
采用 redis 最新 版本

官方 docker redis

https://hub.docker.com/_/redis

## 相关参数说明

端口 6379

对外端口： 30379

## 目录

>/www/k8s/foxdev/redis/data               数据文件
>/www/k8s/foxdev/redis/conf            配置文件目录


## 独立部署 redis 操作
直接执行 `根目录`下 `create.r.redis.start.sh` 即可

```shell

./create.r.redis.start.sh

```


执行完成后，即可用 redis 缓存了


# k8s elasticsearch 独立部署
当前版本已经安装了词库,词库内的配置文件，

默认没有启用映射（默认把它给注释掉），如需要自己配置，那么只要开启映射即可。



## 词库
常用的词库

https://github.com/medcl/elasticsearch-analysis-ik

## 相关参数说明 
端口：9200

端口：9300

对外端口：30920

对外端口：30930



## 目录说明

elasticsearch/logs      日志目录

elasticsearch/data      数据目录

如果你有更多的插件需要加载，那么 请自行配置相关目录


## 独立部署 elasticsearch 操作
直接执行 `根目录`下 `create.elk.elasticsearch.start.sh` 即可

```shell

./create.elk.elasticsearch.start.sh

```


执行完成后，即可用 elasticsearch 了
## 案例
### 创建索引
```bash
curl -XPUT http://192.168.0.254:30920/index
```
输出
```bash
{"acknowledged":true,"shards_acknowledged":true,"index":"index"}
```
### 创建mapping
```bash
curl -XPOST http://192.168.0.254:30920/index/_mapping -H 'Content-Type:application/json' -d'
{
        "properties": {
            "content": {
                "type": "text",
                "analyzer": "ik_max_word",
                "search_analyzer": "ik_smart"
            }
        }

}'
```
输出
```bash
{"acknowledged":true}
```
### 添加几条数据
```bash
curl -XPOST http://192.168.0.254:30920/index/_create/1?pretty -H 'Content-Type:application/json' -d'
{"content":"美国留给伊拉克的是个烂摊子吗"}
'
```
输出略

```bash
curl -XPOST http://192.168.0.254:30920/index/_create/2?pretty -H 'Content-Type:application/json' -d'
{"content":"公安部：各地校车将享最高路权"}
'
```

```bash
curl -XPOST http://192.168.0.254:30920/index/_create/3?pretty -H 'Content-Type:application/json' -d'
{"content":"中韩渔警冲突调查：韩警平均每天扣1艘中国渔船"}
'
```


```bash
curl -XPOST http://192.168.0.254:30920/index/_create/4?pretty -H 'Content-Type:application/json' -d'
{"content":"中国驻洛杉矶领事馆遭亚裔男子枪击 嫌犯已自首"}
'
```

```bash
curl -XPOST http://192.168.0.254:30920/index/_create/5?pretty -H 'Content-Type:application/json' -d'
{"content":"考验特朗普的时候到了！美盟友率先出手，普京紧跟2记“重拳”"}
'
```


### 查询
```bash
curl -XPOST http://192.168.0.254:30920/index/_search?pretty  -H 'Content-Type:application/json' -d'
{
    "query" : { "match" : { "content" : "中国" }},
    "highlight" : {
        "pre_tags" : ["<tag1>", "<tag2>"],
        "post_tags" : ["</tag1>", "</tag2>"],
        "fields" : {
            "content" : {}
        }
    }
}
'
```
输出
```bash
{
  "took" : 8,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 2,
      "relation" : "eq"
    },
    "max_score" : 0.6650044,
    "hits" : [
      {
        "_index" : "index",
        "_type" : "_doc",
        "_id" : "3",
        "_score" : 0.6650044,
        "_source" : {
          "content" : "中韩渔警冲突调查：韩警平均每天扣1艘中国渔船"
        },
        "highlight" : {
          "content" : [
            "中韩渔警冲突调查：韩警平均每天扣1艘<tag1>中国</tag1>渔船"
          ]
        }
      },
      {
        "_index" : "index",
        "_type" : "_doc",
        "_id" : "4",
        "_score" : 0.6308529,
        "_source" : {
          "content" : "中国驻洛杉矶领事馆遭亚裔男子枪击 嫌犯已自首"
        },
        "highlight" : {
          "content" : [
            "<tag1>中国</tag1>驻洛杉矶领事馆遭亚裔男子枪击 嫌犯已自首"
          ]
        }
      }
    ]
  }
}

```

# k8s kibana 独立部署
必须先 部署好  elasticsearch


官方 kibana docker 说明

https://www.elastic.co/guide/en/kibana/current/docker.html
## 相关说明

端口  5601

对外端口：30601

## 独立部署 kibana 操作
直接执行 `根目录`下 `create.elk.kibana.start.sh` 即可

```shell

./create.elk.kibana.start.sh

```
## 访问 
部署成功后就可以直接用浏览器访问了
```bash
http://192.168.0.254:30601
```

# k8s sentinel 独立部署

## 相关说明
端口：8280   web

端口：8719   api server

对外端口：30280

对外端口：30719


用户名和密码： sentinel/sentinel
>如果要自定义用户名和密码，请修改sentinel/cm.yml 中的相关参数

sentinel/logs   日志目录

## 独立部署 sentinel 操作
直接执行 `根目录`下 `create.s.sentinel.start.sh` 即可

```shell

./create.s.sentinel.start.sh

```
## 访问

直接用浏览器访问
```bash
http://192.168.0.254:30280
```

# k8s zipkin 独立部署

https://github.com/openzipkin/zipkin

https://github.com/openzipkin/zipkin/tree/master/zipkin-server


使用 elasticsearch 作为数据存储

## 相关说明
端口：9411

对外端口：30411

## 独立部署 zipkin 操作
直接执行 `根目录`下 `create.z.zipkin.start.sh` 即可

```shell

./create.z.zipkin.start.sh

```
## 访问

直接用浏览器访问
```bash
http://192.168.0.254:30411
```

# k8s grafana 独立部署

## 相关说明
端口：3000

对外端口：30300

## 独立部署 grafana 操作
直接执行 `根目录`下 `create.g.grafana.start.sh` 即可

```shell

./create.g.grafana.start.sh

```
## 访问

直接用浏览器访问
```bash
http://192.168.0.254:30300
```

# k8s prometheus 独立部署

## 相关说明
端口：9090

对外端口：30909

prometheus/conf/prometheus-cluster.yaml 配置文件

## 独立部署 prometheus 操作
直接执行 `根目录`下 `create.p.prometheus.start.sh` 即可

```shell

./create.p.prometheus.start.sh

```
## 访问

直接用浏览器访问
```bash
http://192.168.0.254:30909
```

#   k8s ingress nginx 独立部署
官方

https://github.com/kubernetes/ingress-nginx/blob/nginx-0.24.1/deploy/mandatory.yaml


版本： 0.24.1
## 相关说明
端口：80

端口：443

## 独立部署 ingress nginx 
直接执行 `根目录`下 `create.i.ingress.start.sh` 即可

```shell

./create.i.ingress.start.sh

```

## 查看 ingress pods
```bash
kubectl get pods --namespace=ingress-nginx  --watch

或

kubectl get pods --namespace=ingress-nginx

```

## 查看 ingress
```bash

kubectl get ingress
```
## 测试案例
默认已经安装 nginx 测试案例

在 `192.168.0.254` 服务器中 设置 hosts
```bash
echo "192.168.0.254 test.nginx.ingress " >> /etc/hosts
```
### 查看

在 `192.168.0.254` 测试使用
```bash
curl http://test.nginx.ingress
```

在本机使用
```bash
curl -v http://192.168.0.254 -H 'host: test.nginx.ingress'
```


# 任务调度 xxl-job k8s 独立部署

http://www.xuxueli.com/xxl-job/

http://www.xuxueli.com/xxl-job/#/?id=%e3%80%8a%e5%88%86%e5%b8%83%e5%bc%8f%e4%bb%bb%e5%8a%a1%e8%b0%83%e5%ba%a6%e5%b9%b3%e5%8f%b0xxl-job%e3%80%8b

## 相关说明
对外端口：30820


容器内端口 ：8080

用户名密码：admin/123456


## 独立部署 xxl-job 操作
直接执行 `根目录`下 `create.job.xxl-job.start.sh` 即可

```shell

./create.job.xxl-job.start.sh

```
## 访问

直接用浏览器访问
```bash
http://192.168.0.254:30820/xxl-job-admin
```

## 查看 pods
```bash
kubectl get pods 

```




# k8s 安装 IngressRoute【traefik】
官方文档

https://docs.traefik.io/

https://github.com/containous/traefik/

需要高版本的 Kubernetes(k8s) IngressRoute 的 自定义资源类型 才能使用

这里使用的是 k8s v1.16.3 或 更新版本

## 端口

80   ： 直接使用服务器80 端口 提供http 服务

5580 ： traefik UI 界面

443 未开启，未配置，如需开启，请自行配置
## 一键部署
```bash
./create.i.ingress.route.start.sh
```
## 命令部署
```bash
kubectl apply -f  traefik-rbac-middleware.yaml
kubectl apply -f  traefik-deployment.yaml

#以下是案例
kubectl apply -f  demo.yaml
kubectl apply -f  demo-ingress-route.yaml
```

## 查看 demo 返回信息
```bash
curl -x 192.168.0.254:80 http://your.domain.com/notls 
```
返回
```nashorn js
Hostname: whoami-bd6b677dc-gkhmh
IP: 127.0.0.1
IP: 10.244.0.191
RemoteAddr: 10.244.0.190:33406
GET /notls HTTP/1.1
Host: your.domain.com
User-Agent: curl/7.64.1
Accept: */*
Accept-Encoding: gzip
X-Forwarded-For: 10.244.0.1
X-Forwarded-Host: your.domain.com
X-Forwarded-Port: 80
X-Forwarded-Proto: http
X-Forwarded-Server: traefik-74dfb956c5-2zdl2
X-Real-Ip: 10.244.0.1
```
## UI 界面 
浏览器访问
```angular2
http://192.168.0.254:5580
```
如图
![avatar](doc/images/traefik.png)
