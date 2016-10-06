# CodeServer
This covers CodeServer program arguments reference.

## Reference

* [DevMode Guide](http://www.gwtproject.org/doc/latest/DevGuideCompilingAndDebugging.html)
* [CodeServer Javadoc](http://docs.sencha.com/gxt/4.x/javadoc/gwt-2.8.0/gwt-dev-2.8.0/index.html?com/google/gwt/dev/codeserver/CodeServer.html)

### GWT 2.7.0
Program arguments for running `CodeServer` in GWT 2.7.0.

* `CodeServer` entrypoint:

		CodeServer [-[no]allowMissingSrc] [-bindAddress address] [-[no]compileTest] [-compileTestRecompiles count] [-[no]failOnError] [-[no]precompile] [-port port] [-src dir] [-X[no]enforceStrictResources] [-workDir dir] [-launcherDir] [-[no]incremental] [-sourceLevel [auto, 1.6, 1.7]] [-logLevel level] [-XjsInteropMode [NONE, JS, CLOSURE]] [-XmethodNameDisplayMode NONE | ONLY_METHOD_NAME | ABBREVIATED | FULL] [module]
		
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
		  -logLevel                     The level of logging detail: ERROR, WARN, INFO, TRACE, DEBUG, SPAM, or ALL
		  -XjsInteropMode               Specifies JsInterop mode, either NONE, JS, or CLOSURE (defaults to NONE)
		  -XmethodNameDisplayMode       Emit extra information allow chrome dev tools to display Java identifiers in many places instead of JavaScript functions.
		and 
		  module                        The GWT modules that the code server should compile. (Example: com.example.MyApp)


		
### GWT 2.8.0
Program arguments for running `CodeServer` in GWT 2.8.0.

* `CodeServer` entrypoint:
		
		CodeServer [-[no]allowMissingSrc] [-[no]compileTest] [-compileTestRecompiles count] [-[no]failOnError] [-[no]precompile] [-port port] [-src dir] [-workDir dir] [-launcherDir] [-bindAddress host-name-or-address] [-style (DETAILED|OBFUSCATED|PRETTY)] [-setProperty name=value,value...] [-[no]incremental] [-sourceLevel [auto, 1.8]] [-logLevel (ERROR|WARN|INFO|TRACE|DEBUG|SPAM|ALL)] [-[no]generateJsInteropExports] [-XmethodNameDisplayMode (NONE|ONLY_METHOD_NAME|ABBREVIATED|FULL)] [-X[no]closureFormattedOutput] [module]
		
		where 
		  -[no]allowMissingSrc           Allows -src flags to reference missing directories. (defaults to OFF)
		  -[no]compileTest               Exits after compiling the modules. The exit code will be 0 if the compile succeeded. (defaults to OFF)
		  -compileTestRecompiles         The number of times to recompile (after the first one) during a compile test.
		  -[no]failOnError               Stop compiling if a module has a Java file with a compile error, even if unused. (defaults to OFF)
		  -[no]precompile                Precompile modules. (defaults to ON)
		  -port                          The port where the code server will run.
		  -src                           A directory containing GWT source to be prepended to the classpath for compiling.
		  -workDir                       The root of the directory tree where the code server willwrite compiler output. If not supplied, a temporary directorywill be used.
		  -launcherDir                   An output directory where files for launching Super Dev Mode will be written. (Optional.)
		  -bindAddress                   Specifies the bind address for the code server and web server (defaults to 127.0.0.1)
		  -style                         Script output style: DETAILED, OBFUSCATED or PRETTY
		  -setProperty                   Set the values of a property in the form of propertyName=value1[,value2...].
		  -[no]incremental               Compiles faster by reusing data from the previous compile. (defaults to ON)
		  -sourceLevel                   Specifies Java source level (defaults to 1.8)
		  -logLevel                      The level of logging detail: ERROR, WARN, INFO, TRACE, DEBUG, SPAM or ALL (defaults to INFO)
		  -[no]generateJsInteropExports  Generate exports for JsInterop purposes (defaults to OFF)
		  -XmethodNameDisplayMode        EXPERIMENTAL: Specifies method display name mode for chrome devtools: NONE, ONLY_METHOD_NAME, ABBREVIATED or FULL (defaults to NONE)
		  -X[no]closureFormattedOutput   EXPERIMENTAL: Enables Javascript output suitable for post-compilation by Closure Compiler (defaults to OFF)
		and 
		  module                         The GWT modules that the code server should compile. (Example: com.example.MyApp)
