Серверная часть приложения для выборов.
Использует базу данных PostgreSQL, а также Flyway для миграций.
В папке ElectionsServer\src\main\resources лежит файл application.yml, в нём нужно указать адрес базы данных для spring и flyway.

Для развёртывания базы данных можно воспользоваться Docker, необходимо ввести в командной строке:
docker run --name elections-db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -e POSTGRES_DB=electionsDB -p 5432:5432 -d postgres:latest

Проект собирается и запускается в среде Apache NetBeans IDE 20.

В системе изначально присутствует один админ:
login: admin
password: admin
Можно наделить полномочиями админа другого пользователя, указав его логин, либо же отобрать полномочия.
Пароль должен состоять из латинских букв, цифр и подчеркиваний и быть не короче 5 символов. Логин - тоже

Для работы проекта необходимо запустить также [ElectionsClient](https://github.com/stepanozo/ElectionsClient)
