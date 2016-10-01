# Super DevMode
Super Dev Mode is used to launch a process to compile the application and source maps for modern browsers. 
A web server will be needed to launch with this process when debugging in a browser. 
Such as Dev Mode built in Jetty for the web server.  

* DevMode will run the Super DevMode CodeServer by default in GWT 2.7.0+.
* Mobile debugging uses the web server on the device.

## Reference

* [DevMode Options Reference](../reference/DevMode.html)

### launcherDir
Use the `-launcherDir output/to/warDir` to direct the compiler to put the output in the war directory. 
This prevents from having to use the book marklets.

### bindAddress
Use the `-bindAddress 0.0.0.0` to bind on every IP.


## Launching
Create a Super DevMode launcher by right clicking on the project and going to the GWT menu. 
Then click on the Super Dev Mode launcher. 


### Create and Launch Super DevMode
Using the DevMode entry point to launch Super Dev Mode. 
Reuse the newly created launcher by going to the debug launcher menu at the top and select it. 

