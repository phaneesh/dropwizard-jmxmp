# Dropwizard JMXMP Bundle [![Travis build status](https://travis-ci.org/phaneesh/dropwizard-jmxmp.svg?branch=master)](https://travis-ci.org/phaneesh/dropwizard-jmxmp)

This bundle adds support for jmx over jmxmp which is the only way to make jmx work on marathon 
This bundle compiles only on Java 8.
 
## Usage
This makes it possible to use JMX over JMXMP.
 
### Build instructions
  - Clone the source:

        git clone github.com/phaneesh/dropwizard-jmxmp

  - Build

        mvn install

### Maven Dependency
* Use the following maven dependency:
```
<dependency>
    <groupId>io.dropwizard.jmxmp</groupId>
    <artifactId>dropwizard-jmxmp</artifactId>
    <version>1.3.5-1</version>
</dependency>
```

### Using Oor bundle

#### Bootstrap
```java
    @Override
    public void initialize(final Bootstrap...) {
        bootstrap.addBundle(new JmxMpBundle() {
            protected int port() {
                return 9010;
            }    
        });
    }
```

## Note
* Make sure that the port that is being used is exposed in Dockerfile
* Make sure you define port in marathon deployment descriptor