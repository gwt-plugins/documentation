package com.google.gwt.site;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import com.google.gwt.site.markdown.MDHelperException;
import com.google.gwt.site.markdown.MarkDown;
import com.google.gwt.site.markdown.TranslaterException;
import junit.framework.TestCase;
import org.junit.Test;

public class TestRun extends TestCase {

  /**
   * TODO This is redundant when running mvn install, but useful for testing
   */
  @Test
  public void testRunGxt() throws MDHelperException, TranslaterException, URISyntaxException {
    URL baseUrl = TestRun.class.getResource("/");
    File baseDir = new File(baseUrl.toURI()).getParentFile().getParentFile();
    String basePath = baseDir.getPath();

    String[] args = new String[4];
    args[0] = basePath + "/src/main/markdown/gxt"; // source dir
    args[1] = basePath + "/target/generated-site/gxt"; // output dir
    args[2] = basePath + "/src/main/resources/main-gxt.tpl"; // template file

    args[3] = basePath + "GXT:http://docs.sencha.com/gxt/3/,"
        + "GWT-Driver:http://niloc132.github.io/gwt-driver/apidocs/";

    MarkDown.main(args);
  }

  @Test
  public void testRunExtjs() throws MDHelperException, TranslaterException, URISyntaxException {
    URL baseUrl = TestRun.class.getResource("/");
    File baseDir = new File(baseUrl.toURI()).getParentFile().getParentFile();
    String basePath = baseDir.getPath();

    String[] args = new String[3];
    args[0] = basePath + "/src/main/markdown/extjs"; // source dir
    args[1] = basePath + "/target/generated-site/extjs"; // output dir
    args[2] = basePath + "/src/main/resources/main-extjs.tpl"; // template file

    MarkDown.main(args);
  }

}