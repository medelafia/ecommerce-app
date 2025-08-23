FROM tomcat:10-jdk17-corretto 
VOLUME /tmp 
EXPOSE 8080

COPY ./ROOT.war /usr/local/tomcat/webapps/ROOT.war
