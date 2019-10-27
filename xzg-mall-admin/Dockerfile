FROM openjdk:8

MAINTAINER hutao(hutaobad@126.com)

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /opt/projects/mall

WORKDIR /opt/projects/mall

ADD ./xzg-mall-admin-1.0.0.jar ./

EXPOSE 8085

CMD java -jar xzg-mall-admin-1.0.0.jar