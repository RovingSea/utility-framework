# Utility-framework-springboot: *S*caffold to *E*nhance and *D*ecouple _Controller_

[![projectName](https://img.shields.io/badge/Utilityframework-Springboot-brightgreen)](https://github.com/RovingSea/utility-framework-springboot)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

-------

## What does it do

_Utility-framework-springboot_ is an easy-to-use framework designed to help programmers focus on response, validating
parameters and exception management and handling, rather than just staying at the three-tier design of controller,
service and mapper.

_Utility-framework-springboot_ provides three major functions.

* **Response**

  Replacing encapsulation with configuration, and the response header and response body are uniformly configured.

  In addition, this also supports to configure the response in case of exception.

* **Validating parameter**

  This function is extracted separately as a hierarchical module to avoid coupling the Controller module with any method
  to achieve the parameter verification function.

  The use method is similar to that of Spring MVC, basically, there is no cost of learning

  This enhances the function of the original Controller module, increases the programmer's attention to the parameter
  verification function, and reduces the complexity and maintenance cost of the Controller module through _
  Utility-framework-springboot_

* **Exception management and handling**

  Replacing try/catch with AOP, and uniformly manage and handle exceptions of application layer and business layer.

  This is similar to throwing garbage in life. How will garbage be dealt with in the end will not be related to what we
  are busy with.

  After using, programmers only need to keep the concept of predictability and unpredictability in the code to throw
  exceptions.

    * Predictable exception, that is the exception manually thrown by the programmer in response to an abnormal
      situation.

      When an exception is thrown, the response will be returned based on the response configuration, the content and
      the exception code defined by the programmer.

    * Unpredictable exception(Bug), that is an exception that the programmer didn't notice was thrown during the code
      running.
      _Utility-framework-springboot_ exception module will set the response code to 500 and the corresponding response
      body by default, which also supports the programmer to manually configure.

## Quick Start

### Increasing Maven dependency

First, you need to `utility-spring-boot-starter` Maven dependent on added to your project `pom.xml` file:

```xml

<dependency>
    <groupId>io.github.rovingsea.utilityframework</groupId>
    <artifactId>utility-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Configuration response

Second, you need to implement two interfaces,
`ControllerReturnResponse` and `ControllerExceptionResponse`, as bean:

```java

@Configuration
public class ControllerResponseConfiguration {

    @Bean
    public ControllerExceptionResponse controllerExceptionResponse() {
        return new ControllerExceptionResponse() {

            private final Logger logger = LoggerFactory.getLogger(getClass());

            @Override
            public void setResponseBody(Map<String, Object> responseBody, UtilityException e, HttpServletRequest request, HttpServletResponse response) {
                Throwable rootCause = NestedExceptionUtils.getRootCause(e);
                logger.error(NestedExceptionUtils.buildMessage(e.getMessage(), rootCause));
                responseBody.put("code", e.getCode());
                responseBody.put("message", e.getMessage());
            }

            @Override
            public void setResponseHeader(Map<String, String> responseHeader, UtilityException e, HttpServletRequest request, HttpServletResponse response) {
                ControllerExceptionResponse.super.setResponseHeader(responseHeader, e, request, response);
            }
        };
    }

    @Bean
    public ControllerReturnResponse controllerReturnResponse() {
        return new ControllerReturnResponse() {
            @Override
            public void setResponseBody(Map<String, Object> responseBody, Object returnValue, ServerHttpRequest request, ServerHttpResponse response) {
                responseBody.put("code", 200000);
                responseBody.put("message", "success");
                responseBody.put("data", returnValue);
                responseBody.put("time", new Date());
            }

            @Override
            public void setResponseHeader(Map<String, String> responseHeader, Object returnValue, ServerHttpRequest request, ServerHttpResponse response) {
                ControllerReturnResponse.super.setResponseHeader(responseHeader, returnValue, request, response);
            }
        };
    }

}
```

### Injection validator

Last, use `@Validator` and `@ValidateMapping` to complete path binding and injection validator.

For example, there is such a controller:

```java

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/queryStudentById/{id}")
    public Student queryStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @RequestMapping("/queryStudentsByAge/{age}")
    public List<Student> queryStudentsByAge(@PathVariable int age) {
        return studentService.getStudentsByAge(age);
    }

}
```

Suppose you need to validate the `name` and `age` of `SampleEntity`, then you can do this:

```java

@Validator("/student")
public class StudentValidator {

    @ValidateMapping("/queryStudentById")
    public void queryStudentById(int id) {
        if (id < 0) {
            Throw.badRequest(StudentError.QUERY_BY_ID);
        }
    }

    @ValidateMapping("/queryStudentsByAge")
    public void queryStudentsByAge(int age) {
        if (age < 0 || age > 150) {
            Throw.badRequest(StudentError.QUERY_BY_AGE);
        }
    }

}

```

## Contributing

todo

## Contact

* Email: 1262917629@qq.com
* Wechat: rovingsea
