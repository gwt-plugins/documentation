# Super DevMode with CodeServer (Recommended)
Super Dev Mode is used to launch a process to compile the application and provide source maps for any modern browser. 
A web server will be needed to launch with this process, except for mobile client debugging.

* The code server does not have a built in Jetty web server like DevMode does.

## Reference

* [CodeServer Options Reference](../reference/CodeServer.html)

### launcherDir
Use the `-launcherDir output/to/warDir` to direct the compiler to put the output in the war directory. 
This works well with WTP server runtime configurations.
This prevents from having to use the bookmarklets.


## Launching
Launching the CodeServer is a server that listens for compile requests. 
It compiles the application when requested. 

### Creating and Launching
Create a launcher by right clicking on the project and going to the GWT Menu and launching Super Dev Mode (with CodeServer).
Reuse the newly created launcher by visiting the debug menu at the top and clicking on the launcher shortcut.







