FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER hutao(hutaobad@126.com)

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /opt/projects/mall

WORKDIR /opt/projects/mall

ADD ./xzg-mall-api-1.0.0.jar ./

EXPOSE 8086

CMD java -jar xzg-mall-api-1.0.0.jar