## Building

Prerequisites for building Review:

* git
* Maven
* Java 8
* mySQL 5.7

```
git clone https://github.com/jjaengge/review.git
cd review
mvn clean; mvn install -DskipTests
java -Dspring.profiles.active=local -jar review-1.0-SNAPSHOT.jar
```

## Test

```
http://localhost:18080/swagger-ui.html
```


