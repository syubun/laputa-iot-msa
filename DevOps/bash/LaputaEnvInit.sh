#!/usr/bin/env bash


echo "############判断是否安装了docker##############"
if ! type docker >/dev/null 2>&1; then

	cat >/etc/yum.repos.d/docker.repo<<EOF
[docker-ce-edge]
name=Docker CE Edge - \$basearch
baseurl=https://mirrors.aliyun.com/docker-ce/linux/centos/7/\$basearch/edge
enabled=1
gpgcheck=1
gpgkey=https://mirrors.aliyun.com/docker-ce/linux/centos/gpg
EOF

    echo 'docker 未安装';
	echo '开始安装Docker....';	
	yum -y install docker-ce
	
	echo '配置Docker开启启动';
	systemctl enable docker
	systemctl start docker	

cat >> /etc/docker/daemon.json << EOF
{
  "registry-mirrors": ["https://b9pmyelo.mirror.aliyuncs.com"]
}
EOF

	systemctl restart docker
	
else
    echo 'docker 已安装';
fi

echo "############判断是否安装了wget##############"
if ! type wget >/dev/null 2>&1; then
    echo 'wget 未安装';
	echo '开始安装wget....';	
	yum -y install wget
	
else
    echo 'wget 已安装';
fi

echo "############判断是否安装了dos2unix##############"
if ! type dos2unix >/dev/null 2>&1; then
    echo 'dos2unix 未安装';
	echo '开始安装dos2unix....';	
	yum -y install dos2unix*
	
else
    echo 'dos2unix 已安装';
fi

echo "############判断是否安装了docker-compose##############"
if ! type docker-compose >/dev/null 2>&1; then
    echo 'docker-compose 未安装';
	echo '开始安装docker-compose....';		
	wget https://sommer78.oss-cn-shanghai.aliyuncs.com/docker-compose-Linux-x86_64
	mv docker-compose-Linux-x86_64  docker-compose
	chmod +x docker-compose
	mv docker-compose /usr/local/bin/
	docker-compose -v
	
else
    echo 'docker-compose 已安装';
fi


echo "############判断是否安装了unzip解压##############"
if ! type unzip >/dev/null 2>&1; then
    echo 'unzip 未安装';
	echo '开始安装unzip....';	
	yum -y install unzip
	
else
    echo 'unzip 已安装';
fi


echo '创建docker网络';
docker network create laputa

echo '下载maven';

wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo

yum -y install apache-maven

yum install -y git

# wget https://mogublog-v2.oss-cn-guangzhou.aliyuncs.com/software/docker-compose.zip


#unzip docker-compose.zip

# 进入目录
#cd docker-compose
# 添加执行权限
#chmod +x bin/kernStartup.sh
#chmod +x bin/kernShutdown.sh
#chmod +x bin/update.sh
#chmod +x bin/wait-for-it.sh

# 进入到bin目录
#cd bin

# 修改编码
echo "修改编码...."
#dos2unix kernStartup.sh
#dos2unix kernShutdown.sh
#dos2unix update.sh
#dos2unix wait-for-it.sh

# 执行脚本
#python2 replaceIp.py

#sh kernStartup.sh