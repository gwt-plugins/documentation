# Google App Engine

## Maven 
The App Engine Java SDK zip is required in the Maven dependencies.

```
<dependency>
	<groupId>com.google.appengine</groupId>
	<artifactId>appengine-api-1.0-sdk</artifactId>
	<version>${appengine.version}</version>
</dependency>
<!-- Eclipse will extract this into the .m2 repository -->
<dependency>
	<groupId>com.google.appengine</groupId>
	<artifactId>appengine-java-sdk</artifactId>
	<version>${appengine.version}</version>
	<type>zip</type>
	<scope>provided</scope>
</dependency>
```