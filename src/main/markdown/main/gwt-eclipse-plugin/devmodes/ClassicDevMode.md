# Classic DevMode (Deprecated)
Classic DevMode is used to launch a process which compiles the application, 
run a web server and uses a process OOHPM to connect to the browser. 
The OOPHM process is no longer supported in the modern browsers.

* DevMode will run the Super DevMode CodeServer by default in GWT 2.7.0+.
* Classic DevMode Launcher will create a program argument "-nosuperDevMode" in GWT 2.7.0+.   

## Reference

* [DevMode Options Reference](../reference/DevMode.html)

### bindAddress
Use `-bindAddress 0.0.0.0` to bind the web server to every available host ip address.


## Launching
Start the Classic DevMode by right clicking on the project and going to the GWT shortcuts. 


###Create and Launch Classic DevMode
Create a classic Dev Mode Launcher by right clicking on the project, then going to the GWT Menu. 
Select GWT Classic Dev Mode Launcher. 
Resuse the newly createdlauncher by going to the debug launchers menu and starting it. 

<img src="images/classicdevmode.png" />

