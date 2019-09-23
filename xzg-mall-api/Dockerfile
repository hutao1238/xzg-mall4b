FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER hutao(hutaobad@126.com)

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /opt/projects/mall

WORKDIR /opt/projects/mall

EXPOSE 8086

ADD ./xzg-mall-api/target/xzg-mall-api-1.0.0.jar ./

CMD java -jar -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms1024m -Xmx1024m -Xmn256m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -Dspring.profiles.active=docker xzg-mall-api-1.0.0.jar