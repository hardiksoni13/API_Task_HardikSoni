We have created API automation framework using which we can automate APIs provided in PETSHOP domain.

Here is the Document Link --> https://gorest.co.in/

1. **Technology stack**
   1. Maven : For Decency management
   1. Java : As Programming language
   1. RestAssured: Library for client-server communication. Can also be replaced easy with other library as well without affecting single test
   1. JUnit 5/jupiter
   1. Log4j : For logging purpose
   1. GSON : Gson for serializing/deserializing String to Class and vice-versa
   1. IDE: IntelIj or similar Java capable IDE would be good.
    
1. **How to Use this framework:**
    1. For any request/response, we have to create pojo classes in main's dto package.
       This will help to create request/response for particular API.
    1. Add API URL into URLManagement.java file   
    2. Once you have created Pojo, please create below methods:
        1. Getter classes for all variables : To achieve Encapsulation and data hiding
        1. Builder methods : Builder patter will help to create object easily.
        1. Override below methods (On need basis):
            1. toString
            1. hashCode
            1. equals
    1. Once you are ready with pojo classes and other configuration:
        1. Create package for your API in src/test/java
        1. Add testcases as per your API.
            1. If its POST/PUT API then create FactoryMethod which will help to give sample payload.
            1. As per test you can modify such payload.
        1. Make sure to add proper loggs and method commenting for better understanding.
        1. If you are working on POST API, make sure you delete the data as well using DELETE API or directly from Database
   1. We have added below details in property file, so that we can run same test in different env without changing single line of a code.
        1. Host
        1. Maximum Response time, we want.
        1. authorizeToken
   1. Using this approach it will be very easy to marinate, if request parameters are changed, or even we have to do assertions of large dataset.
   1. We have tried to capture document at each method/class level, if you are not able to understand something, do let me know.
        Would be more than happy to assist.
1. **Suite**
   1. We have created test suits using 'Tags' in junit. Here we have created 'SmokeTestSuite' which will
    execute all the testcases having 'smoke' as tags.
1. **Open Points** 
    1. Somehow i was not able to figure out request for Comment section, hence kept it with skeleton only



        **-------------Feedback is always welcome-------------**