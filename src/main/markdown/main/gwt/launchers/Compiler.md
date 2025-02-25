# Compiler
This covers Compiler program arguments reference.

## Reference

* [Compiler Guide](http://www.gwtproject.org/doc/latest/DevGuideCompilingAndDebugging.html)

### GWT 2.12.1
Program arguments for running `Compiler` in GWT 2.12.1.

* `Compiler` entrypoint:

        Compiler [-logLevel (ERROR|WARN|INFO|TRACE|DEBUG|SPAM|ALL)] [-workDir dir] [-X[no]closureFormattedOutput] [-[no]compileReport] [-X[no]checkCasts] [-X[no]classMetadata] [-[no]draftCompile] [-[no]checkAssertions] [-XfragmentCount numFragments] [-gen dir] [-[no]generateJsInteropExports] [-includeJsInteropExports/excludeJsInteropExports regex] [-XmethodNameDisplayMode (NONE|ONLY_METHOD_NAME|ABBREVIATED|FULL)] [-Xnamespace (NONE|PACKAGE)] [-optimize level] [-[no]saveSource] [-setProperty name=value,value...] [-style (DETAILED|OBFUSCATED|PRETTY)] [-[no]failOnError] [-[no]validateOnly] [-sourceLevel [auto, 1.8, 9, 10, 11, 17]] [-localWorkers count] [-[no]incremental] [-war dir] [-deploy dir] [-extra dir] [-saveSourceOutput dir] module[s] 

        where 
          -logLevel                                         The level of logging detail: ERROR, WARN, INFO, TRACE, DEBUG, SPAM or ALL (defaults to INFO)
          -workDir                                          The compiler's working directory for internal use (must be writeable; defaults to a system temp dir)
          -X[no]closureFormattedOutput                      EXPERIMENTAL: Enables Javascript output suitable for post-compilation by Closure Compiler (defaults to OFF)
          -[no]compileReport                                Compile a report that tells the "Story of Your Compile". (defaults to OFF)
          -X[no]checkCasts                                  EXPERIMENTAL: DEPRECATED: use jre.checks.checkLevel instead. (defaults to OFF)
          -X[no]classMetadata                               EXPERIMENTAL: Include metadata for some java.lang.Class methods (e.g. getName()). (defaults to ON)
          -[no]draftCompile                                 Compile quickly with minimal optimizations. (defaults to OFF)
          -[no]checkAssertions                              Include assert statements in compiled output. (defaults to OFF)
          -XfragmentCount                                   EXPERIMENTAL: Limits of number of fragments using a code splitter that merges split points.
          -gen                                              Debugging: causes normally-transient generated types to be saved in the specified directory
          -[no]generateJsInteropExports                     Generate exports for JsInterop purposes. If no -includeJsInteropExport/-excludeJsInteropExport provided, generates all exports. (defaults to OFF)
          -includeJsInteropExports/excludeJsInteropExports  Include/exclude members and classes while generating JsInterop exports. Flag could be set multiple times to expand the pattern. (The flag has only effect if exporting is enabled via -generateJsInteropExports)
          -XmethodNameDisplayMode                           EXPERIMENTAL: Specifies method display name mode for chrome devtools: NONE, ONLY_METHOD_NAME, ABBREVIATED or FULL (defaults to NONE)
          -Xnamespace                                       Puts most JavaScript globals into namespaces. Default: PACKAGE for -draftCompile, otherwise NONE
          -optimize                                         Sets the optimization level used by the compiler.  0=none 9=maximum.
          -[no]saveSource                                   Enables saving source code needed by debuggers. Also see -debugDir. (defaults to OFF)
          -setProperty                                      Set the values of a property in the form of propertyName=value1[,value2...].
          -style                                            Script output style: DETAILED, OBFUSCATED or PRETTY (defaults to OBFUSCATED)
          -[no]failOnError                                  Fail compilation if any input file contains an error. (defaults to OFF)
          -[no]validateOnly                                 Validate all source code, but do not compile. (defaults to OFF)
          -sourceLevel                                      Specifies Java source level (defaults to 1.8)
          -localWorkers                                     The number of local workers to use when compiling permutations
          -[no]incremental                                  Compiles faster by reusing data from the previous compile. (defaults to OFF)
          -war                                              The directory into which deployable output files will be written (defaults to 'war')
          -deploy                                           The directory into which deployable but not servable output files will be written (defaults to 'WEB-INF/deploy' under the -war directory/jar, and may be the same as the -extra directory/jar)
          -extra                                            The directory into which extra files, not intended for deployment, will be written
          -saveSourceOutput                                 Overrides where source files useful to debuggers will be written. Default: saved with extras.
        and 
          module[s]                                         Specifies the name(s) of the module(s) to compile

