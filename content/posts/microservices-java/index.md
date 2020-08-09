---
title: "Microservices: Java"
date: 2020-08-07T02:09:08-04:00
draft: false
tags:
  - enterprise-microservices
---

{{< toc >}}

## Java
### Overview
Java is a general-purpose programming language that is often used in large enterprises for creating APIs. This is likely because it has been around for a long time, and has been developed over many years to meet many business-critical requirements such as resilience, security, and performance.

Another benefit of Java is that it is platform-independent. Since it executes code on its own virtual machine, we are afforded the benefit that it can be written once, but run anywhere. A Java program will be compiled down to a format called **bytecode** that can be run as a sequence of commands in the virtual machine. Since the virtual machine can run on almost any platform, Java is truly portable.

### OOP

One of the most central principles in Java programming is that **everything is a class**. Java is built on a pattern of programming called **OOP** (object oriented programming), where data and its representation is at the core of a program's execution. You manage the state of your code flow via **invoking methods on instances of objects**.

Objects will contain one or more **fields** that hold data. You can **construct** an **instance** of an object to **initialize** all of its fields. You can then invoke a **method** on an object to process some information using its fields and any **arguments** that were passed to the method.

Here's a brief dissection of a simple Java class:

```java
package com.tasteofindia.sample.car;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.Slf4j;

/**
 * This is a simple class that represents a Car.
 */
@Getter // this is an "annotation," which can dynamically add functionality to your class at compile-time.
@Setter
@Slf4j // injects a "logging" client
@ToString // injects an override of the toString method that gives a human-readable representation of our Car class and its fields
public class Car {
    private String model; //fields will have a "scope," "type," and "name."
    private int year;
    private int miles;

    /**
     * This is a constructor for the Car class.
     * This constructor takes in arguments, and uses them to populate
     * the fields in this class. You can see this constructor called in
     * the main function below, when we create a "new" Car.
     * 
     * @param model the model of the car
     * @param year the year in which the car was made
     */
    public Car(String model, int year) {
        this.model = model;
        this.year = year;
        // not every field has to be initialized with a constructor argument
        this.miles = 0;
    }

    /**
     * This is a method, that represents adding some miles to the cars
     * odometer.
     * 
     * Methods typically consume some arguments, and use them to change 
     * some state of the instance of your class.
     */ 
    public void drive(int miles) {
        if (miles < 0) {
            // throwing an exception ends the execution of your method. If
            // it is not caught, it crashes your application with a
            // message.
            log.error("Passed in a negative number of miles: {}", miles);
            throw new IllegalArgumentException("Must drive a positive number of miles");
        }
        // the setMiles and getMiles methods were automatically provided
        // by the @Setter and @Getter annotations.
        // lombok provides lots of useful annotations like this.
        log.info("Driving {} miles", miles);
        this.setMiles(this.getMiles() + miles);
    }
    
    /**
     * This is the main method. Typically, your application will have one
     * "entrypoint" defined by this main function. This is the function 
     * that runs when someone executes your compiled application with the
     * java runtime environment (JRE).
     * 
     * @param args an array of command line arguments (if any) passed to
            the program
     */
    public static void main(String[] args) {
        Car volvo = new Car("volvo", 2020);
        volvo.drive(20);
        volvo.drive(10);
        log.info("Car representation: {}", volvo);
    }
}
```


### Java Modules
We can create a maintainable project structure by grouping all of our classes by either domain association or technical responsiblity. The classes are grouped into **packages**, which can optionally contain **subpackages**. When you have a lot of different Java applications, you will want to centralize them under your organization as a **group**. A **group** can contain multiple **artifacts** (one or more packages that are meant to be consumed as a single unit). An artifact can have multiple **versions**.

Your Java modules can then be hosted on a centralized repository for download and consumption. One such public repository is [Maven central](https://mvnrepository.com). Large enterprises will typically host their own internal repositories. Each module can be uniquely defined by the combination of its group, artifact name, and version (**GAV**). For example, you can get the base spring boot web application dependencies with the GAV: `org.springframework.boot:sprint-boot-starter-web:2.3.2.RELEASE`.

### Building and Running
At "compile time" (when Java needs to be translate into bytecode), we leverage the **JDK** (Java Development Kit). At runtime, all we need is the JRE (the Java Runtime Environment, which has the ability to create instances of the JVM and run bytecode commands against it).

Java typically lumps large amount of bytecode into a compressed unit called a **JAR** or **jar** *(Java ARchive)*.

When Java applications get more complex (like client-server applications), we will start importing dependencies to make the code easier. When we start importing large amounts of code, we leverage dependency management and build tools to organize and declare what we're importing, from where, and encode the steps required to build and package our application for it to be used at runtime.

The most common build tools for Java applications are **maven** and **gradle**.


#### Maven
Maven's build system packages dependencies, runs tests, and builds applications based on instructions defined in a `pom.xml` file in the application root.

### Testing
In all programming languages, testing our applications is critical. The easiest way to get started is with **unit tests**, which test a small, independent unit of your application, providing or mocking all dependencies that it doesn't have.

In Java, the most common **unit testing framework** is JUnit. JUnit can be run against your code and be used to tell you if your application conforms to certain behavior under certain pre-defined conditions and assumptions.

## API Frameworks in Java
For a REST API to work, it needs to be able to open a connection once a request is received from a client, create a thread for handling that request, determine if the request is authenticated and formatted properly, parse the request, handle the request, and return a response, all conforming to some standards and format.

Because doing this is a lot of work, and all of the pattens involved have some standardization, most developers leverage **REST API frameworks** that make the syntax and code-flow easier.

Some common REST API frameworks for Java are **Spring**, **Play**, and **Grails**.

### Abstraction of REST API frameworks
Most API frameworks will abstract away all the work described in the previous section into very tangible sequences. The idea is that you write **handlers** for **HTTP verbs** hitting different **endpoints**.

A handler is simply a method in your class that will be invoked any time someone makes an HTTP request to an endpoint of a certain pattern. For example, if you have an endpoint like `POST /user`, it is likely that you will have a method like `addNewUser(User u)` as its handler. A good REST API framework will automatically be able to convert the content of the request body into an instance of your `User` object and pass it in as an argument to your handler when someone hits your endpoint.
