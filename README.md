## Building

Prerequisites for building Flink:

* git
* Maven (we recommend version 3.0.4)
* Java 8

```
git clone https://github.com/jjaengge/review.git
cd review
mvn clean; mvn install -DskipTests
java -Dspring.profiles.active=local -jar review-1.0-SNAPSHOT.jar
```


