---
title: "Java and Microservices"
date: 2020-08-06T10:27:36-04:00
draft: true
---

## Java
### Overview
Java is a general-purpose programming language that is often used in large enterprises for creating APIs. This is likely because it has been around for a long time, and has been developed over many years to meet many business-critical requirements such as resilience, security, and performance.

Another benefit of Java is that it is platform-independent. Since it executes code on its own virtual machine, we are afforded the benefit that it can be written once, but run anywhere. A Java program will be compiled down to a format called **bytecode** that can be run as a sequence of commands in the virtual machine. Since the virtual machine can run on almost any platform, Java is truly portable.

### OOP

One of the most central principles in Java programming is that **everything is a class**. Java is built on a pattern of programming called **OOP** (object oriented programming), where data and its representation is at the core of a program's execution. You manage the state of your code flow via **invoking methods on instances of objects**.

Objects will contain one or more **fields** that hold data. You can **construct** an **instance** of an object to **initialize** all of its fields. You can then invoke a **method** on an object to process some information using its fields and any **arguments** that were passed to the method.

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
