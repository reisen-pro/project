查找 rabbitmq
docker search rabbitmq:management

拉取rabbitmq的镜像
docker pull rabbitmq:management

运行rabbitmq 15672端口
docker run -d -p 5672:5672 -p 15672:15672 --name rabbitmq rabbitmq:management

