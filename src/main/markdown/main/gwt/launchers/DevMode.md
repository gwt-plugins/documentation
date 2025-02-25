# DevMode
This covers DevMode program arguments reference.

## Reference

* [DevMode Guide](http://www.gwtproject.org/doc/latest/DevGuideCompilingAndDebugging.html)

### GWT 2.12.1
Program arguments for running `DevMode` in GWT 2.12.1.

* `DevMode` entrypoint:

        DevMode [-[no]startServer] [-port port-number | "auto"] [-logdir directory] [-logLevel (ERROR|WARN|INFO|TRACE|DEBUG|SPAM|ALL)] [-gen dir] [-bindAddress host-name-or-address] [-codeServerPort port-number | "auto"] [-[no]superDevMode] [-server servletContainerLauncher[:args]] [-startupUrl url] [-war dir] [-deploy dir] [-extra dir] [-modulePathPrefix ] [-workDir dir] [-XmethodNameDisplayMode (NONE|ONLY_METHOD_NAME|ABBREVIATED|FULL)] [-sourceLevel [auto, 1.8, 9, 10, 11, 17]] [-[no]generateJsInteropExports] [-includeJsInteropExports/excludeJsInteropExports regex] [-[no]incremental] [-style (DETAILED|OBFUSCATED|PRETTY)] [-[no]failOnError] [-setProperty name=value,value...] module[s] 

        where 
          -[no]startServer                                  Starts a servlet container serving the directory specified by the -war flag. (defaults to ON)
          -port                                             Specifies the TCP port for the embedded web server (defaults to 8888)
          -logdir                                           Logs to a file in the given directory, as well as graphically
          -logLevel                                         The level of logging detail: ERROR, WARN, INFO, TRACE, DEBUG, SPAM or ALL (defaults to INFO)
          -gen                                              Debugging: causes normally-transient generated types to be saved in the specified directory
          -bindAddress                                      Specifies the bind address for the code server and web server (defaults to 127.0.0.1)
          -codeServerPort                                   Specifies the TCP port for the code server (defaults to 9997 for classic Dev Mode or 9876 for Super Dev Mode)
          -[no]superDevMode                                 Runs Super Dev Mode instead of classic Development Mode. (defaults to ON)
          -server                                           Specify a different embedded web server to run (must implement ServletContainerLauncher)
          -startupUrl                                       Automatically launches the specified URL
          -war                                              The directory into which deployable output files will be written (defaults to 'war')
          -deploy                                           The directory into which deployable but not servable output files will be written (defaults to 'WEB-INF/deploy' under the -war directory/jar, and may be the same as the -extra directory/jar)
          -extra                                            The directory into which extra files, not intended for deployment, will be written
          -modulePathPrefix                                 The subdirectory inside the war dir where DevMode will create module directories. (defaults empty for top level)
          -workDir                                          The compiler's working directory for internal use (must be writeable; defaults to a system temp dir)
          -XmethodNameDisplayMode                           EXPERIMENTAL: Specifies method display name mode for chrome devtools: NONE, ONLY_METHOD_NAME, ABBREVIATED or FULL (defaults to NONE)
          -sourceLevel                                      Specifies Java source level (defaults to 1.8)
          -[no]generateJsInteropExports                     Generate exports for JsInterop purposes. If no -includeJsInteropExport/-excludeJsInteropExport provided, generates all exports. (defaults to OFF)
          -includeJsInteropExports/excludeJsInteropExports  Include/exclude members and classes while generating JsInterop exports. Flag could be set multiple times to expand the pattern. (The flag has only effect if exporting is enabled via -generateJsInteropExports)
          -[no]incremental                                  Compiles faster by reusing data from the previous compile. (defaults to ON)
          -style                                            Script output style: DETAILED, OBFUSCATED or PRETTY (defaults to OBFUSCATED)
          -[no]failOnError                                  Fail compilation if any input file contains an error. (defaults to OFF)
          -setProperty                                      Set the values of a property in the form of propertyName=value1[,value2...].
        and 
          module[s]                                         Specifies the name(s) of the module(s) to host
