@echo off
set SPRING_DATASOURCE_URL=jdbc:mysql://docker.unicauca.edu.co:3307/computacion
set SPRING_DATASOURCE_USERNAME=computacion
set SPRING_DATASOURCE_PASSWORD=DB-C0mput4c10N
set SPRING_SERVER_ARCHIVOS_PORT=8020

mvn clean package
