FROM mysql:5.7

MAINTAINER hutao(hutaobad@126.com)

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

COPY ./db/xzg_shop.sql /docker-entrypoint-initdb.d