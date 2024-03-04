#!bin/bash

ps -ef | grep java | grep zxrail-app | awk '{print $2}' | xargs kill -9

sleep 1

JAVA_OPTS="-Xms256m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m"

nohup java -jar $JAVA_OPTS -Dspring.profiles.active=dev -Dserver.port=8078 zxrail-app-food.jar &