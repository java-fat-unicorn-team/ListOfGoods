# ListOfGoods

## Main topic mof project
It is just simple project which provides ability to manage the list of goods in your basket

by default basket is stored in memory, but you can choose to store it in a file
	
## Technologies
Project is created with:
* Java 11
* Spring core 5.1.7
* Maven 3.5+
* Git 2+
* JUnit 5
	
## Deploy
To run this project, install and run it locally using maven:

```
$ mvn clean install
```
## Execute
Default: basket is stored in memory
```
$ mvn exec:java
```
Basket is stored in file
```
$ mvn exec:java -Dspring.profiles.active=fromFile
```