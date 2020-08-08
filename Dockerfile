#FROM openjdk:8
FROM tomcat:8.5.20-jre8-alpine

USER root
RUN rm -f /usr/local/tomcat/conf/tomcat-users.xml
##RUN cp tomcat-users.xml /usr/local/tomcat/conf/
RUN rm -f /usr/share/tomcat8-admin/manager/META-INF/context.xml
##RUN cp context.xml /usr/share/tomcat8-admin/manager/META-INF/
ADD tomcat-users.xml /usr/local/tomcat/conf/
ADD context.xml /usr/share/tomcat8-admin/manager/META-INF/

#ADD target/restfull-0.0.1-SNAPSHOT.war restfull.jar
ADD target/restfull-0.0.1-SNAPSHOT /usr/local/tomcat/webapps/

EXPOSE 8182

#ENTRYPOINT ["java", "-jar", "restfull.jar"]
CMD ["catalina.sh", "run"]



# Use a minimal image as parent
#FROM openjdk:8-jdk-alpine
# Environment variables
#ENV TOMCAT_MAJOR=8 \
#    TOMCAT_VERSION=8.5.37 \
#    CATALINA_HOME=/opt/tomcat
# init
#RUN apk -U upgrade --update && \
#    apk add curl && \
#    apk add ttf-dejavu
#RUN mkdir -p /opt
# install tomcat
#RUN curl -jkSL -o /tmp/apache-tomcat.tar.gz http://archive.apache.org/dist/tomcat/tomcat-${TOMCAT_MAJOR}/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz && \
#    gunzip /tmp/apache-tomcat.tar.gz && \
#    tar -C /opt -xf /tmp/apache-tomcat.tar && \
#    ln -s /opt/apache-tomcat-$TOMCAT_VERSION $CATALINA_HOME
# cleanup
#RUN apk del curl && \
#    rm -rf /tmp/* /var/cache/apk/*
#EXPOSE 8080
#COPY startup.txt /opt/startup.sh
#ENTRYPOINT /opt/startup.sh
#WORKDIR $CATALINA_HOME
