# Тестирование сервиса по покупке тура
Для тестирования предоставлен сервис по покупке тура двумя способами:
* покупка по карте
* покупка в кредит

## Начало работы
Для запуска на локальном ПК необходимо клонировать проект командой git clon git@github.com:SSS5900/diplom.git

## Предварительные условия
На ПК необходимо установить и настроить:
* Браузер Google Chrome
* IntelliJ IDEA
* Docker Desktop
* DBeaver или аналог (не обязательно)

## Установка и запуск
1. Открыть проект в IntelliJ IDEA
2. В Terminal запустить контейнеры командой\
docker-compose up --build
3. В новой вкладке в Terminal запустить приложение:
* для MySQL командой\
java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar


* для PostgreSQL командой\
  java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar
4. В новой вкладке в Terminal запустить автотесты:
* для MySQL командой\
  ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"


* для PostgreSQL командой\
  ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"
5. После прогона автотестов, запустить отчет о тестировании с помощью Allure командой\
   ./gradlew allureServe \
После завершения формирования отчета, остановить работу Allure нажатием сочетания клавишь Ctrl+C\
На запрос "Завершить выполнение пакетного файла [Y(да)/N(нет)]?" ввести "y"


