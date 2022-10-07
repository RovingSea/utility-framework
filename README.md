
# Utility-framework-springboot: *S*caffold to *E*nhance and *D*ecouple _Controller_

## What does it do

_Utility-framework-springboot_ is an easy-to-use framework 
designed to help programmers focus on response, validating
parameters and exception management and handling, 
rather than just staying at the three-tier design of 
controller, service and mapper.

_Utility-framework-springboot_ provides three major functions.

* **Response**

  Replacing encapsulation with configuration, and the response
  header and response body are uniformly configured.

  In addition, this also supports to configure the response
  in case of exception.

* **Validating parameter**

  This function is extracted separately as a hierarchical module
  to avoid coupling the Controller module with any method to achieve
  the parameter verification function.

  The use method is similar to that of Spring MVC,
  basically, there is no cost of learning

  This enhances the function of the original Controller module,
  increases the programmer's attention to the parameter verification
  function, and reduces the complexity and maintenance cost of the
  Controller module through _Utility-framework-springboot_

* **Exception management and handling**

  Replacing try/catch with AOP, and uniformly manage and handle
  exceptions of application layer and business layer.

  This is similar to throwing garbage in life.
  How will garbage be dealt with in the end will not be related 
  to what we are busy with.

  After use, programmers only need to keep the concept of predictability
  and unpredictability in the code to throw exceptions.

  * Predictable exception, that is the exception manually thrown by
    the programmer in response to an abnormal situation. 

    When an exception is thrown, the response will be returned based on
    the response configuration, the content and the exception code defined
    by the programmer.

  * Unpredictable exception(Bug), that is an exception that the programmer
    didn't notice was thrown during the code running.
    _Utility-framework-springboot_ exception module will set
    the response code to 500 and the corresponding response body by default, 
    which also supports the programmer to manually configure.


## Quick Start
  todo

## Contributing
  todo

## Contact

* Email: 1262917629@qq.com
* Wechat: rovingsea
