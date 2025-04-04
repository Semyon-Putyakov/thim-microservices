# Last Project

Проект на Spring Boot, реализующий REST API для работы с измерениями и датчиками.

## Структура проекта

Проект организован по стандартной структуре Spring Boot:

- `controllers/` - REST контроллеры
- `services/` - бизнес-логика
- `repositories/` - интерфейсы для работы с базой данных
- `models/` - сущности
- `dto/` - объекты передачи данных
- `validations/` - валидаторы
- `util/` - утилитные классы

## Технологии

- Java 17
- Spring Boot 3.4.4
- Spring Data JPA
- Spring Validation
- PostgreSQL
- Thymeleaf
- ModelMapper 3.2.2

## Требования

- Java 17 или выше
- Maven
- PostgreSQL 13 или выше

## Конфигурация базы данных

Проект использует PostgreSQL. Настройки подключения:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/lastProject
spring.datasource.username=postgres
spring.datasource.password=root
```

## Запуск проекта

1. Клонируйте репозиторий
2. Убедитесь, что у вас установлена Java 17 или выше
3. Создайте базу данных PostgreSQL с именем `lastProject`
4. Выполните сборку проекта:
   ```bash
   mvn clean install
   ```
5. Запустите приложение:
   ```bash
   mvn spring:boot run
   ```

## API Endpoints

### Датчики (Sensors)

- `GET /sensors` - получить список всех датчиков
- `GET /sensors/{id}` - получить датчик по ID
- `POST /sensors/registration` - зарегистрировать новый датчик
- `PUT /sensors/{id}` - обновить информацию о датчике
- `DELETE /sensors/{id}` - удалить датчик

### Измерения (Measurements)

- `GET /measurements` - получить список всех измерений
- `GET /measurements/{id}` - получить измерение по ID
- `POST /measurements/add` - добавить новое измерение
- `PUT /measurements/{id}` - обновить измерение
- `DELETE /measurements/{id}` - удалить измерение

## Тестирование

Проект включает модульные тесты, использующие Spring Boot Test. Для запуска тестов выполните:

```bash
mvn test
```

## Развертывание

1. Соберите JAR-файл:
   ```bash
   mvn clean package
   ```
2. Запустите приложение:
   ```bash
   java -jar target/lastProject-0.0.1-SNAPSHOT.jar
   ```

## Дополнительные зависимости

- `spring-boot-starter-data-jpa` - для работы с базой данных
- `spring-boot-starter-thymeleaf` - для шаблонизации
- `spring-boot-starter-validation` - для валидации данных
- `spring-boot-starter-web` - для REST API
- `postgresql` - драйвер PostgreSQL
- `modelmapper` - для маппинга между DTO и сущностями

