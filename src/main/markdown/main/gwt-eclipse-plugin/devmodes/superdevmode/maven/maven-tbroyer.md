#Maven using Broyer's plugin

## Reference

* [GWT Maven Plugin](https://github.com/tbroyer/gwt-maven-plugin)


## Launching Super Dev Mode

1. run `mvn gwt:codeserver -pl *-client -am`
2. run `mvn tomcat7:run -pl *-server -am -Denv=dev`