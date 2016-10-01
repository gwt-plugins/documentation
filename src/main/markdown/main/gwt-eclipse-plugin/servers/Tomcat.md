# Tomcat
This covers using Tomcat as a Eclipse WTP Server runtime to host the application. 

## Reference

* [Find out how to install the Tomcat WTP Server Adapter](http://www.vogella.com/tutorials/EclipseWTP/article.html)


## Maven
Setting up maven with a Tomcat dependency.

* Tomcat plugin

		<!-- http://tomcat.apache.org/maven-plugin-2.2/context-goals.html - goals -->
		<plugin>
		  <groupId>org.apache.tomcat.maven</groupId>
		  <artifactId>tomcat7-maven-plugin</artifactId>
		  <version>2.2</version>
		  <configuration>
		    <webappDirectory>src/main/webapp</webappDirectory>
		  </configuration>
		</plugin>


## Project

* [Example Project](https://github.com/branflake2267/Archetypes/tree/master/archetypes/gwt-basic-rpc-tomcat)

## Archetype
Creating a project from a template with Tomcat. 

* Example Archetype

		mvn archetype:generate -DarchetypeGroupId=com.github.branflake2267.archetypes \
		-DarchetypeRepository=https://oss.sonatype.org/content/repositories/snapshots \
		-DarchetypeArtifactId=gwt-basic-rpc-tomcat-archetype \
		-DarchetypeVersion=1.0-SNAPSHOT \
		-DgroupId=com.projectname.project \
		-DartifactId=new-project-name \
		-Dmodule=Project

## Installing

<iframe width="560" height="315" src="//www.youtube.com/embed/6w87lVYT-MM" frameborder="0" allowfullscreen></iframe>

## Launching
Using and launching the Tomcat server runtime.

<iframe width="560" height="315" src="//www.youtube.com/embed/d6xsX9x9WC4" frameborder="0" allowfullscreen></iframe>
