FROM java:8
VOLUME /tmp
COPY /docker-dockerfile-start.sh /
RUN chmod +x /docker-dockerfile-start.sh
EXPOSE 15000
ADD /target/development-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["/docker-dockerfile-start.sh"]
## 每次执行 前 先到linux 中 ，执行 docker-mydemo-stop...sh 脚本文件 删除以及停止运行中的镜像 否则容易造成多个镜像文件
# 在容器内部 不能再执行 docker 命令 所以 不自动化了
# 访问的时候 直接连接服务的 ip 比如 172.16.147.136：15000/。。。。