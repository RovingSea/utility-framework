
# Utility framework: *H*andling exception, *Ch*ecking parameter and *R*esponse *F*ramework

## What does it do

Utility is an easy-to-use framework designed to help
programmers focus on handling exception, check parameters
and responses, rather than just staying at the three-tier
design of controller, service and mapper.

Utility provides three major functions.

* **Handling exception**

  In combination with spring's native ideas, specify which 
  exceptions need to be managed by annotations,
  after being specified by @Error, it will be treated as
  an error object by the utility, and it is regarded as a
  prototype bean in the spring container.
  
  When this exception occurs, the programmer can perform 
  additional handling on it by @ErrorPostProcessor, 
  customize the content of the response and special handling
  via @ErrorHandler.

* **Checking parameter**

  I hope it can be used to check parameters like the 
  @Controller in spring Web

* **Response**

  I hope to be able to determine the content of the response
  through configuration like spring cloud gateway.

## Quick Start
  todo

## Contributing
  todo

## Contact

* Email: 1262917629@qq.com
* Wechat: rovingsea
