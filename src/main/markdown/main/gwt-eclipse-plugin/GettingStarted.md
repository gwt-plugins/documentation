# Getting Started with GWT Eclipse Plugin
This covers getting sarted with using the GWT Eclipse Plugin to debug GWT application.


## Installing
First install the GWT Eclipse Plugin. 

* [GWT Eclipse Plugin Download and Install](./Download.html)


## Choose the Debugging technique 
There are several ways you can debug an application.
Choose the best method depending on which type of web server or mobile client your developing your application for.  


### 1. Super DevMode with the CodeServer (*Recommended)
This uses the `CodeServer` entrypoint.
It runs the `CodeServer` and listens for compile requests from the browser. 
And it will compile the application in Super Dev Mode.
This is the best choice for running with any web server or mobile client configuration. 
Break points, browser insepction and stack trace isnepction can be added with the SDBG plugin.
 
* [Find out how to run Super DevMode with the CodeServer](./devmodes/SuperDevMode.html)


### 2. Super DevMode
This uses the `DevMode` entrypoint.
By default it runs a built in Jetty web server to host the web application. 
And uses the `CodeServer` to run another server that will compile the application in Super Dev Mode.
While the default web server can be turned off, it's better to use the CodeServer to launch Super Dev Mode.
Break points, browser insepction and stack trace isnepction can be added with the SDBG plugin. 

* [Find out how to run Super DevMode](./devmodes/SuperDevMode.html)


### 3. Classic DevMode (*Deprecated)
This uses `DevMode` as the entrypoint and with the argument `-nosuperDevMode (GWT 2.7)`. And it is deprecated.  
Classic DevMode allows for IDE integration for break points, variable inspection, and stack traces.
It can only be run on Firefox < 26, IE 8 - IE 10, although it's not supported in the modern browers.   
It's recommended to switch to using Super Dev Mode (#1 or #2 above).    

* [Find out how to run Classic DevMode](./devmodes/ClassicDevMode.html)

