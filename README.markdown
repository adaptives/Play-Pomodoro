This is a simple [Pomodoro](http://en.wikipedia.org/wiki/Pomodoro_Technique) project made using the [Play Framework](http://www.playframework.org)

The purpose of this project is to introduce the Play Framework to someone who is not familiar with it.

Important Files:

+ Bootstrap.java (This file is a job which is called by virtue of it's annotation, at application startup)
+ app/controllers/Application.java (This is the main and only controller in the project. A controller handles an incoming request by processing it with the appropriate action method)
+ app/controllers/Security (This controller redfines certain methods provided by the security module)
+ The remaining files in the controllers package all end with an 's' and are CRUD files
+ Each file in app/models represents a persistent Entity
+ Files in app/views represent view templates. Note that the templates are organized into sub directories by controller name. So the directory, app/views/Application contains view templates which correspond to various action methods in the Application controller class
+ Files in app/views/tags contain view tags, which are reusable view components
+ Files in app/views/errors contain error files which will be displayed in response to various HTTP error codes
+ The test directory contains test scripts (unit, functional, and Selenium) 

