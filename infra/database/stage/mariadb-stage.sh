#최초 실행 시
docker run -d \
  --name testdb \
  -e MYSQL_ROOT_PASSWORD=1234 \
  -e MYSQL_DATABASE=testdb \
  -e MYSQL_USER=test \
  -e MYSQL_PASSWORD=1234 \
  -p 3307:3306 \
  -v testmariadb:/var/lib/mysql \
  mariadb:latest

#이후 실행시
docker start testdb

#DB 접속
docker exec -it testdb mariadb -uroot -p1234

#사용할 디비 선택
use testdb;



