# Super Dev Mode
Super Dev Mode is used to launch a process to compile the application and source maps for modern browsers. 
A server will be needed to launch with this process. 
Such as Dev Mode built in Jetty for the web server.  


## Reference

* [Super Dev Mode Guide](http://www.gwtproject.org/articles/superdevmode.html)


## Options 

* GWT 2.6.1

		CodeServer [-[no]allowMissingSrc] [-bindAddress address] [-[no]compileTest] [-compileTestRecompiles count] [-[no]failOnError] [-[no]precompile] [-port port] [-src dir] [-X[no]enforceStrictResources] [-workDir dir] [-launcherDir] [-[no]incremental] [-sourceLevel [auto, 1.6, 1.7]] [-logLevel (ERROR|WARN|INFO|TRACE|DEBUG|SPAM|ALL)] [-XjsInteropMode (NONE|JS|CLOSURE)] [-XmethodNameDisplayMode (NONE|ONLY_METHOD_NAME|ABBREVIATED|FULL)] [module]
		
		where
		  -[no]allowMissingSrc          Allows -src flags to reference missing directories. (defaults to OFF)
		  -bindAddress                  The ip address of the code server. Defaults to 127.0.0.1.
		  -[no]compileTest              Exits after compiling the modules. The exit code will be 0 if the compile succeeded. (defaults to OFF)
		  -compileTestRecompiles        The number of times to recompile (after the first one) during a compile test.
		  -[no]failOnError              Stop compiling if a module has a Java file with a compile error, even if unused. (defaults to OFF)
		  -[no]precompile               Precompile modules. (defaults to ON)
		  -port                         The port where the code server will run.
		  -src                          A directory containing GWT source to be prepended to the classpath for compiling.
		  -X[no]enforceStrictResources  EXPERIMENTAL: Don't implicitly depend on "client" and "public" when a module doesn't define any dependencies. (defaults to OFF)
		  -workDir                      The root of the directory tree where the code server willwrite compiler output. If not supplied, a temporary directorywill be used.
		  -launcherDir                  An output directory where files for launching Super Dev Mode will be written. (Optional.)
		  -[no]incremental              Compiles faster by reusing data from the previous compile. (defaults to ON)
		  -sourceLevel                  Specifies Java source level (defaults to auto:1.7)
		  -logLevel                     The level of logging detail: ERROR, WARN, INFO, TRACE, DEBUG, SPAM or ALL (defaults to INFO)
		  -XjsInteropMode               EXPERIMENTAL: Specifies JsInterop mode: NONE, JS or CLOSURE (defaults to NONE)
		  -XmethodNameDisplayMode       EXPERIMENTAL: Specifies method display name mode for chrome devtools: NONE, ONLY_METHOD_NAME, ABBREVIATED or FULL (defaults to NONE)
		and
		  module                        The GWT modules that the code server should compile. (Example: com.example.MyApp)

### launcherDir
Use the `-launcherDir output/to/warDir` to direct the compiler to put the output in the war directory. 
This prevents from having to use the book marklets.

### bindAddress
Use the `-bindAddress 0.0.0.0` to bind on every IP.


## Launching
There is more than one way to launch Super Dev Mode. 
Super Dev Mode can launched using the DevMode or CodeServer entry points.

<img src="images/superdevmodes.png" /> 


### Launching Super Dev Mode with DevMode
Using the DevMode entry point to launch Super Dev Mode. 

* [Super Dev Mode using DevMode](./superdevmode/SuperDevMode-DevMode.html)


### Launching Super Dev Mode with the CodeServer
Using the CodeServer entry point to launch Super Dev Mode.

* [Super Dev Mode using the CodeServer](./superdevmode/SuperDevMode-CodeServer.html)


### Launching Super Dev Mode with Maven
Using maven to start Super Dev Mode

* [Super Dev Mode using Codehause Maven Plugin](./superdevmode/maven/maven-codehaus.html)
* [Super Dev Mode using Broyer's Maven Plugin](./superdevmode/maven/maven-tbroyer.html)