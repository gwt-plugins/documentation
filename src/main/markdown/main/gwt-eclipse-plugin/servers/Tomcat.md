# Tomcat
This covers using Tomcat as a Eclipse WTP Server runtime to host the application. 

## Reference

* [Find out how to install the Tomcat WTP Server Adapter](http://www.vogella.com/tutorials/EclipseWTP/article.html)
* [Tomcat Maven Archetypes](https://github.com/tbroyer/gwt-maven-archetypes/)

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


## Create project
Create a multi module webapp from a Maven archetype.  

* [TBroyer Multi Module Webapp](https://github.com/tbroyer/gwt-maven-archetypes/)


## Debugging

### Auto Configure GWT Development Mode
The GWT Eclipse Plugin can automatically start GWT Development Mode.

<iframe width="560" height="315" src="//www.youtube.com/embed/LtH4KzmguL0" frameborder="0" allowfullscreen></iframe>

### Manually Configure GWT Development Mode
Manually Configuring the -launcherDir in the GWT Eclipse Plugin. Auto configure has now taken over. 

<iframe width="560" height="315" src="//www.youtube.com/embed/-_YcBeI_Feo" frameborder="0" allowfullscreen></iframe>


