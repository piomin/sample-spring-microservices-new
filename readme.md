# Microservices with Spring Cloud Advanced Demo Project [![Twitter](https://img.shields.io/twitter/follow/piotr_minkowski.svg?style=social&logo=twitter&label=Follow%20Me)](https://twitter.com/piotr_minkowski)

In this project I'm demonstrating you the most interesting features of [Spring Cloud Project](https://spring.io/projects/spring-cloud) for building microservice-based architecture.

## Getting Started 
Currently you may find here some examples of microservices implementation using different projects from Spring Cloud. All the examples are divided into the branches and described in a separated articles on my blog. Here's a full list of available examples:
1. Using Spring Cloud Netlix **Eureka** as a discovery server, **Zuul** as a gateway, **OpenFeign** for communication and Spring Cloud Config Server. The example is available in the branch [master](https://github.com/piomin/sample-spring-microservices-new/tree/master). A detailed guide may be find in the following article: [Quick Guide to Microservices with Spring Boot 2.0, Eureka and Spring Cloud](https://piotrminkowski.com/2018/04/26/quick-guide-to-microservices-with-spring-boot-2-0-eureka-and-spring-cloud/)
2. Using Spring Cloud Alibaba **Nacos** as a discovery and configuration server, **Zuul** and **OpenFeign**. The example is available in the branch [alibaba](https://github.com/piomin/sample-spring-microservices-new/tree/alibaba). A detailed guide may be find in the following article: [Microservices with Spring Cloud Alibaba](https://piotrminkowski.com/2018/11/15/microservices-with-spring-cloud-alibaba/)
3. Using Spring Cloud with Spring Boot support for **GraphQL** for building microservices, **Apollo** for inter-service communication and **Eureka** as a discovery server. The example is available in the branch [graphql](https://github.com/piomin/sample-spring-microservices-new/tree/graphql). A detailed guide may be find in the following article: [GraphQL – The Future of Microservices?](https://piotrminkowski.com/2018/08/16/graphql-the-future-of-microservices/)
4. Using Spring Boot and partially Spring Cloud for building microservices deployed on **OpenShift** with **Source-2-Image** mechanism. The example is available in the branch [openshift](https://github.com/piomin/sample-spring-microservices-new/tree/openshift). A detailed guide may be find in the following article: [Running Java Microservices on OpenShift using Source-2-Image](https://piotrminkowski.com/2019/01/08/running-java-microservices-on-openshift-using-source-2-image/)
5. Using [Trampoline](http://ernestort.github.io/Trampoline/) for managing group of Spring Boot microservices locally. The example is available in the branch [trampoline](https://github.com/piomin/sample-spring-microservices-new/tree/trampoline). A detailed guide may be find in the following article: [Managing Spring Boot apps locally with Trampoline](https://piotrminkowski.com/2018/06/08/managing-spring-boot-apps-locally-with-trampoline/)

### Usage

In the most cases you need to have Maven and JDK8+. In the fourth example with OpenShift you will have to run **Minishift** on your machine. The best way to run the sample applications is with IDEs like IntelliJ IDEA or Eclipse.  

## Architecture

Our sample microservices-based system consists of the following modules:
- **gateway-service** - a module that Spring Cloud Netflix Zuul for running Spring Boot application that acts as a proxy/gateway in our architecture.
- **config-service** - a module that uses Spring Cloud Config Server for running configuration server in the `native` mode. The configuration files are placed on the classpath.
- **discovery-service** - a module that depending on the example it uses Spring Cloud Netflix Eureka or Spring Cloud Netlix Alibaba Nacos as an embedded discovery server.
- **employee-service** - a module containing the first of our sample microservices that allows to perform CRUD operation on in-memory repository of employees
- **department-service** - a module containing the second of our sample microservices that allows to perform CRUD operation on in-memory repository of departments. It communicates with employee-service. 
- **organization-service** - a module containing the third of our sample microservices that allows to perform CRUD operation on in-memory repository of organizations. It communicates with both employee-service and organization-service.

The following picture illustrates the architecture described above.

<img src="https://piotrminkowski.files.wordpress.com/2018/04/spring-cloud-1.png" title="Architecture"><br/>

