#!/usr/bin/env bash
mvn clean install site
if [ $? -eq 1 ]
then
    mvn site
fi
chmod -R 777 ./target /root/.m2 ./.allure