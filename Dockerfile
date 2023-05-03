FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=qwertyuiop

COPY ./storeeverything.sql /docker-entrypoint-initdb.d/

EXPOSE 3306