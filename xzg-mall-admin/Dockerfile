FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER hutao(hutaobad@126.com)

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /opt/projects/mall4b

WORKDIR /opt/projects/mall4b

ADD ./xzg-mall-admin/target/xzg-mall-admin-1.0.0.jar ./

EXPOSE 8085

CMD java -jar -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=128m -Xms512m -Xmx512m -Xmn128m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -Dspring.profiles.active=docker,quartz xzg-mall-admin-1.0.0.jar