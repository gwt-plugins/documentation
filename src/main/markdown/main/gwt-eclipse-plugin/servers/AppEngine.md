# Google App Engine

## Maven 
The App Engine Java SDK ZIP is required in the Maven dependencies.

* App Engine Dependencies. Be sure to use the Java SDK too.

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


### Project

* [Example Project](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-basic-rpc-appengine)

### Archetype

* Example Archetype

		mvn archetype:generate -DarchetypeGroupId=com.github.branflake2267.archetypes \
		-DarchetypeRepository=https://oss.sonatype.org/content/repositories/snapshots \
		-DarchetypeArtifactId=gwt-basic-rpc-appengine-archetype \
		-DarchetypeVersion=1.0-SNAPSHOT \
		-DgroupId=com.projectname.project \
		-DartifactId=new-project-name \
		-Dmodule=Project


## Launching
Using and launching the App Engine server runtime.

<iframe width="560" height="315" src="//www.youtube.com/embed/SUueCocqf_U" frameborder="0" allowfullscreen></iframe>

