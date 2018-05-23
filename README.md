# redis消息发布订阅，消息队列demo

#### 项目介绍
使用redis实现消息的发布订阅
消息队列这两个简单的demo

#### 软件架构
springboot
redis


#### 安装教程
1.本地安装redis 端口号为默认，如果redis配置有修改，请修改项目配置文件，使得一致
2. 在properties中修改port为8080和8081分别打包
3. java -jar ***.jar运行两个工程

#### 使用说明

1. curl -d "message=queuemsg" http://localhost:8080/add-queue 可以测试消息队列的处理，在控制台查看结果，两个工程随机一个处理了这条消息
2. curl -d "message=topicmsg" http://localhost:8080/add-topic 的post请求可以测试消息发布订阅的处理，在控制台查看结果，两个工程都会收到这条消息

