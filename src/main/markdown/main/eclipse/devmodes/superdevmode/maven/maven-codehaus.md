# Maven using the Codehaus plugin

## Reference

* [GWT Maven Plugin](http://mojo.codehaus.org/gwt-maven-plugin/)

## Options

### launcherDir
Use the `-launcherDir` to compile the output into the war directory. 

* [Example pom.xml](https://github.com/branflake2267/Archetypes/blob/master/archetypes/gwt-basic-rpc-appengine/pom.xml)

		<launcherDir>${project.build.directory}/${project.build.finalName}</launcherDir>

## Launching

1. launch the web server 
2. run `mvn gwt:run-codeserver` 

