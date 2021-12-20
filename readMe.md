Запустить докер

1)docker build -t  api-diplom -f Dockerfile .
2)docker images
3)docker run -d -p 8080:8080 -t api-diplom
4)curl http://localhost:8080/tickets/getAll

