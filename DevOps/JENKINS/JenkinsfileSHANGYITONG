pipeline {
    agent {
        node {
            label 'maven'
        }

    }
    stages {
        stage('拉取代码') {
            agent none
            steps {
                container('maven') {
                    git(url: 'https://gitee.com/leifengyang/yygh-parent.git', credentialsId: 'gitee-id', branch: 'master', changelog: true, poll: false)
                    sh 'ls -al'
                }

            }
        }

        stage('项目编译') {
            agent none
            steps {
                container('maven') {
                    sh 'ls'
                    sh 'mvn clean package -Dmaven.test.skip=true'
                    sh 'ls hospital-manage/target'
                }

            }
        }

        stage('default-2') {
            parallel {
                stage('构建hospital-manage镜像') {
                    agent none
                    steps {
                        container('maven') {
                            sh 'ls hospital-manage/target'
                            sh 'docker build -t hospital-manage:latest -f hospital-manage/Dockerfile  ./hospital-manage/'
                        }

                    }
                }

                stage('构建server-gateway镜像') {
                    agent none
                    steps {
                        container('maven') {
                            sh 'ls server-gateway/target'
                            sh 'docker build -t server-gateway:latest -f server-gateway/Dockerfile  ./server-gateway/'
                        }

                    }
                }

                stage('构建service-cmn镜像') {
                    agent none
                    steps {
                        container('maven') {
                            sh 'ls service/service-cmn/target'
                            sh 'docker build -t service-cmn:latest -f service/service-cmn/Dockerfile  ./service/service-cmn/'
                        }

                    }
                }

                stage('构建service-hosp镜像') {
                    agent none
                    steps {
                        container('maven') {
                            sh 'ls service/service-hosp/target'
                            sh 'docker build -t service-hosp:latest -f service/service-hosp/Dockerfile  ./service/service-hosp/'
                        }

                    }
                }

                stage('构建service-order镜像') {
                    agent none
                    steps {
                        container('maven') {
                            sh 'ls service/service-order/target'
                            sh 'docker build -t service-order:latest -f service/service-order/Dockerfile  ./service/service-order/'
                        }

                    }
                }

                stage('构建service-oss镜像') {
                    agent none
                    steps {
                        container('maven') {
                            sh 'ls service/service-oss/target'
                            sh 'docker build -t service-oss:latest -f service/service-oss/Dockerfile  ./service/service-oss/'
                        }

                    }
                }

                stage('构建service-sms镜像') {
                    agent none
                    steps {
                        container('maven') {
                            sh 'ls service/service-sms/target'
                            sh 'docker build -t service-sms:latest -f service/service-sms/Dockerfile  ./service/service-sms/'
                        }

                    }
                }

                stage('构建service-statistics镜像') {
                    agent none
                    steps {
                        container('maven') {
                            sh 'ls service/service-statistics/target'
                            sh 'docker build -t service-statistics:latest -f service/service-statistics/Dockerfile  ./service/service-statistics/'
                        }

                    }
                }

                stage('构建service-task镜像') {
                    agent none
                    steps {
                        container('maven') {
                            sh 'ls service/service-task/target'
                            sh 'docker build -t service-task:latest -f service/service-task/Dockerfile  ./service/service-task/'
                        }

                    }
                }

                stage('构建service-user镜像') {
                    agent none
                    steps {
                        container('maven') {
                            sh 'ls service/service-user/target'
                            sh 'docker build -t service-user:latest -f service/service-user/Dockerfile  ./service/service-user/'
                        }

                    }
                }

            }
        }

        stage('default-3') {
            parallel {
                stage('推送hospital-manage镜像') {
                    agent none
                    steps {
                        container('maven') {
                            withCredentials([usernamePassword(credentialsId : 'aliyun-docker-registry' ,usernameVariable : 'DOCKER_USER_VAR' ,passwordVariable : 'DOCKER_PWD_VAR' ,)]) {
                                sh 'echo "$DOCKER_PWD_VAR" | docker login $REGISTRY -u "$DOCKER_USER_VAR" --password-stdin'
                                sh 'docker tag hospital-manage:latest $REGISTRY/$DOCKERHUB_NAMESPACE/hospital-manage:SNAPSHOT-$BUILD_NUMBER'
                                sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/hospital-manage:SNAPSHOT-$BUILD_NUMBER'
                            }

                        }

                    }
                }

                stage('推送server-gateway镜像') {
                    agent none
                    steps {
                        container('maven') {
                            withCredentials([usernamePassword(credentialsId : 'aliyun-docker-registry' ,usernameVariable : 'DOCKER_USER_VAR' ,passwordVariable : 'DOCKER_PWD_VAR' ,)]) {
                                sh 'echo "$DOCKER_PWD_VAR" | docker login $REGISTRY -u "$DOCKER_USER_VAR" --password-stdin'
                                sh 'docker tag server-gateway:latest $REGISTRY/$DOCKERHUB_NAMESPACE/server-gateway:SNAPSHOT-$BUILD_NUMBER'
                                sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/server-gateway:SNAPSHOT-$BUILD_NUMBER'
                            }

                        }

                    }
                }

                stage('推送service-cmn镜像') {
                    agent none
                    steps {
                        container('maven') {
                            withCredentials([usernamePassword(credentialsId : 'aliyun-docker-registry' ,usernameVariable : 'DOCKER_USER_VAR' ,passwordVariable : 'DOCKER_PWD_VAR' ,)]) {
                                sh 'echo "$DOCKER_PWD_VAR" | docker login $REGISTRY -u "$DOCKER_USER_VAR" --password-stdin'
                                sh 'docker tag service-cmn:latest $REGISTRY/$DOCKERHUB_NAMESPACE/service-cmn:SNAPSHOT-$BUILD_NUMBER'
                                sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/service-cmn:SNAPSHOT-$BUILD_NUMBER'
                            }

                        }

                    }
                }

                stage('推送service-hosp镜像') {
                    agent none
                    steps {
                        container('maven') {
                            withCredentials([usernamePassword(credentialsId : 'aliyun-docker-registry' ,usernameVariable : 'DOCKER_USER_VAR' ,passwordVariable : 'DOCKER_PWD_VAR' ,)]) {
                                sh 'echo "$DOCKER_PWD_VAR" | docker login $REGISTRY -u "$DOCKER_USER_VAR" --password-stdin'
                                sh 'docker tag service-hosp:latest $REGISTRY/$DOCKERHUB_NAMESPACE/service-hosp:SNAPSHOT-$BUILD_NUMBER'
                                sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/service-hosp:SNAPSHOT-$BUILD_NUMBER'
                            }

                        }

                    }
                }

                stage('推送service-order镜像') {
                    agent none
                    steps {
                        container('maven') {
                            withCredentials([usernamePassword(credentialsId : 'aliyun-docker-registry' ,usernameVariable : 'DOCKER_USER_VAR' ,passwordVariable : 'DOCKER_PWD_VAR' ,)]) {
                                sh 'echo "$DOCKER_PWD_VAR" | docker login $REGISTRY -u "$DOCKER_USER_VAR" --password-stdin'
                                sh 'docker tag service-order:latest $REGISTRY/$DOCKERHUB_NAMESPACE/service-order:SNAPSHOT-$BUILD_NUMBER'
                                sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/service-order:SNAPSHOT-$BUILD_NUMBER'
                            }

                        }

                    }
                }

                stage('推送service-oss镜像') {
                    agent none
                    steps {
                        container('maven') {
                            withCredentials([usernamePassword(credentialsId : 'aliyun-docker-registry' ,usernameVariable : 'DOCKER_USER_VAR' ,passwordVariable : 'DOCKER_PWD_VAR' ,)]) {
                                sh 'echo "$DOCKER_PWD_VAR" | docker login $REGISTRY -u "$DOCKER_USER_VAR" --password-stdin'
                                sh 'docker tag service-oss:latest $REGISTRY/$DOCKERHUB_NAMESPACE/service-oss:SNAPSHOT-$BUILD_NUMBER'
                                sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/service-oss:SNAPSHOT-$BUILD_NUMBER'
                            }

                        }

                    }
                }

                stage('推送service-sms镜像') {
                    agent none
                    steps {
                        container('maven') {
                            withCredentials([usernamePassword(credentialsId : 'aliyun-docker-registry' ,usernameVariable : 'DOCKER_USER_VAR' ,passwordVariable : 'DOCKER_PWD_VAR' ,)]) {
                                sh 'echo "$DOCKER_PWD_VAR" | docker login $REGISTRY -u "$DOCKER_USER_VAR" --password-stdin'
                                sh 'docker tag service-sms:latest $REGISTRY/$DOCKERHUB_NAMESPACE/service-sms:SNAPSHOT-$BUILD_NUMBER'
                                sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/service-sms:SNAPSHOT-$BUILD_NUMBER'
                            }

                        }

                    }
                }

                stage('推送service-statistics镜像') {
                    agent none
                    steps {
                        container('maven') {
                            withCredentials([usernamePassword(credentialsId : 'aliyun-docker-registry' ,usernameVariable : 'DOCKER_USER_VAR' ,passwordVariable : 'DOCKER_PWD_VAR' ,)]) {
                                sh 'echo "$DOCKER_PWD_VAR" | docker login $REGISTRY -u "$DOCKER_USER_VAR" --password-stdin'
                                sh 'docker tag service-statistics:latest $REGISTRY/$DOCKERHUB_NAMESPACE/service-statistics:SNAPSHOT-$BUILD_NUMBER'
                                sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/service-statistics:SNAPSHOT-$BUILD_NUMBER'
                            }

                        }

                    }
                }

                stage('推送service-task镜像') {
                    agent none
                    steps {
                        container('maven') {
                            withCredentials([usernamePassword(credentialsId : 'aliyun-docker-registry' ,usernameVariable : 'DOCKER_USER_VAR' ,passwordVariable : 'DOCKER_PWD_VAR' ,)]) {
                                sh 'echo "$DOCKER_PWD_VAR" | docker login $REGISTRY -u "$DOCKER_USER_VAR" --password-stdin'
                                sh 'docker tag service-task:latest $REGISTRY/$DOCKERHUB_NAMESPACE/service-task:SNAPSHOT-$BUILD_NUMBER'
                                sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/service-task:SNAPSHOT-$BUILD_NUMBER'
                            }

                        }

                    }
                }

                stage('推送service-user镜像') {
                    agent none
                    steps {
                        container('maven') {
                            withCredentials([usernamePassword(credentialsId : 'aliyun-docker-registry' ,usernameVariable : 'DOCKER_USER_VAR' ,passwordVariable : 'DOCKER_PWD_VAR' ,)]) {
                                sh 'echo "$DOCKER_PWD_VAR" | docker login $REGISTRY -u "$DOCKER_USER_VAR" --password-stdin'
                                sh 'docker tag service-user:latest $REGISTRY/$DOCKERHUB_NAMESPACE/service-user:SNAPSHOT-$BUILD_NUMBER'
                                sh 'docker push  $REGISTRY/$DOCKERHUB_NAMESPACE/service-user:SNAPSHOT-$BUILD_NUMBER'
                            }

                        }

                    }
                }

            }
        }

        stage('default-4') {
            parallel {
                stage('hospital-manage - 部署到dev环境') {
                    agent none
                    steps {
                        kubernetesDeploy(configs: 'hospital-manage/deploy/**', enableConfigSubstitution: true, kubeconfigId: "$KUBECONFIG_CREDENTIAL_ID")
                    }
                }

                stage('server-gateway - 部署到dev环境') {
                    agent none
                    steps {
                        kubernetesDeploy(configs: 'server-gateway/deploy/**', enableConfigSubstitution: true, kubeconfigId: "$KUBECONFIG_CREDENTIAL_ID")
                    }
                }

                stage('service-cmn - 部署到dev环境') {
                    agent none
                    steps {
                        kubernetesDeploy(configs: 'service/service-cmn/deploy/**', enableConfigSubstitution: true, kubeconfigId: "$KUBECONFIG_CREDENTIAL_ID")
                    }
                }

                stage('service-hosp - 部署到dev环境') {
                    agent none
                    steps {
                        kubernetesDeploy(configs: 'service/service-hosp/deploy/**', enableConfigSubstitution: true, kubeconfigId: "$KUBECONFIG_CREDENTIAL_ID")
                    }
                }

                stage('service-order - 部署到dev环境') {
                    agent none
                    steps {
                        kubernetesDeploy(configs: 'service/service-order/deploy/**', enableConfigSubstitution: true, kubeconfigId: "$KUBECONFIG_CREDENTIAL_ID")
                    }
                }

                stage('service-oss - 部署到dev环境') {
                    agent none
                    steps {
                        kubernetesDeploy(configs: 'service/service-oss/deploy/**', enableConfigSubstitution: true, kubeconfigId: "$KUBECONFIG_CREDENTIAL_ID")
                    }
                }

                stage('service-sms - 部署到dev环境') {
                    agent none
                    steps {
                        kubernetesDeploy(configs: 'service/service-sms/deploy/**', enableConfigSubstitution: true, kubeconfigId: "$KUBECONFIG_CREDENTIAL_ID")
                    }
                }

                stage('service-statistics - 部署到dev环境') {
                    agent none
                    steps {
                        kubernetesDeploy(configs: 'service/service-statistics/deploy/**', enableConfigSubstitution: true, kubeconfigId: "$KUBECONFIG_CREDENTIAL_ID")
                    }
                }

                stage('service-task - 部署到dev环境') {
                    agent none
                    steps {
                        kubernetesDeploy(configs: 'service/service-task/deploy/**', enableConfigSubstitution: true, kubeconfigId: "$KUBECONFIG_CREDENTIAL_ID")
                    }
                }

                stage('service-user - 部署到dev环境') {
                    agent none
                    steps {
                        kubernetesDeploy(configs: 'service/service-user/deploy/**', enableConfigSubstitution: true, kubeconfigId: "$KUBECONFIG_CREDENTIAL_ID")
                    }
                }

            }
        }

        //1、配置全系统的邮件：                   全系统的监控
        //2、修改ks-jenkins的配置，里面的邮件；   流水线发邮件
        stage('发送确认邮件') {
            agent none
            steps {
                mail(to: '17512080612@163.com', subject: '构建结果', body: "构建成功了  $BUILD_NUMBER")
            }
        }

    }
    environment {
        DOCKER_CREDENTIAL_ID = 'dockerhub-id'
        GITHUB_CREDENTIAL_ID = 'github-id'
        KUBECONFIG_CREDENTIAL_ID = 'demo-kubeconfig'
        REGISTRY = 'registry.cn-hangzhou.aliyuncs.com'
        DOCKERHUB_NAMESPACE = 'lfy_hello'
        GITHUB_ACCOUNT = 'kubesphere'
        APP_NAME = 'devops-java-sample'
        ALIYUNHUB_NAMESPACE = 'lfy_hello'
    }
    parameters {
        string(name: 'TAG_NAME', defaultValue: '', description: '')
    }
}