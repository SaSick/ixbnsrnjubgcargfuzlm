# ixbnsrnjubgcargfuzlm

CRUD телефония которая взаимодействует с SQL и NoSQL 
базами данных как ***PostgreSQL*** *и* ***MongoDB***

___

## Технологический стэк

1. Java 17
2. 


___

## Установка 

1. Склонируйте репозиторий с ***github*** 
   + Откройте терминал или командную строку и вставьте туда эту команду
        * **Внимание**! Если вы хотите, чтобы репозиторий был склонирован в определенную папку, укажите путь после URL:
            ```shell
            git clone https://github.com/SaSick/ixbnsrnjubgcargfuzlm.git path/to/directory
            ```
2. Или же открыв репозиторий просто скачайте проект в формате ***.zip***
3. После того как скачаете и откроете проект, проверьте
    файл - *application.properties*
4. Если у вас не существует база данных под названием *postgres* (если есть как есть оставляете), придётся создать вручную в ***PostgreSQL***
    также обратите внимание на поля username и password они у вас должны соответствовать вашим настройкам
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
    spring.datasource.username=postgres
    spring.datasource.password=password
    ```
5. В папке ***resources*** находится коллекция postman которую надо импортировать непосредственно в *postman*
6. Проект создавался в среде разработки IntelliJ
    
