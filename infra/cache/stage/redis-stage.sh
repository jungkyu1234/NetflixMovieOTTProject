#최초 레디스 기동 시
docker run -d --name redis-cache -p 6379:6379 redis

#이후 실행 시
docker start redis-cache