# Фильтр точек на карте

## Задание

Дан JSON-объект в файле *pins.json*. Объект содержит массив "сервисов" и массив точек с координатами. Каждая точка относится к какому-либо сервису.

##### Необходимо написать приложение, которое:
1. Умеет преобразовывать заданный JSON-объект в Kotlin-/Java- объект.
2. Содержит экран с картой (*MapActivity*), на которой отображаются точки. 
3. На экране с картой есть кнопка, которая открывает второй экран со списком сервисов (*FilterActivity*). 
4. На *FilterActvity* можно выбрать сервисы (один или несколько), по которым будут отображаться точки на *MapActivity*.

##### Примечания:
1. Задание желательно выполнять на Kotlin.
2. Неважно какие карты использовать (Google, Яндекс или что-то другое).
3. Библиотека для десериализации JSON в объект выбирается на ваше усмотрение.
4. Выполненное задание нужно загрузить на github.

## Реализация приложения
1. При запуске приложения на карте отображаются абсолютно все сервисы, находящиеся в "pins" в *pins.json*.
2. В зависимости от количества сервисов в "services" в *pins.json* динамически создаются кнопки в списке сервисов.
3. Существует возможность выбирать для отображения как один, так и несколько сервисов сразу (в последнем случае точки каждого сервиса на карте будут иметь свой цвет).
4. Была использована карта Google.
5. Библиотека для десериализации JSON - Moshi.
6. Скриншоты приложения:
![image](https://github.com/Katushkahey/Avito_map/blob/master/Screenshots/Screenshot_0.png)
![image](https://github.com/Katushkahey/Avito_map/blob/master/Screenshots/Screenshot_1.png)
![image](https://github.com/Katushkahey/Avito_map/blob/master/Screenshots/Screenshot_2.png)

