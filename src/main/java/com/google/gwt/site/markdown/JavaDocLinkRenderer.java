package com.google.gwt.site.markdown;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.regex.Pattern;

import org.pegdown.LinkRenderer;
import org.pegdown.ast.ExpLinkNode;
import org.pegdown.ast.WikiLinkNode;

public class JavaDocLinkRenderer extends LinkRenderer {

  private final Map<String, String> javadocLinks;
  private final String depth;

  public JavaDocLinkRenderer(Map<String, String> javadocLinks, int depth) {
    this.javadocLinks = javadocLinks;
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i < depth; i++) {
      sb.append("../");
    }
    this.depth = sb.toString();
  }

  public Rendering render(WikiLinkNode node) {
    System.out.println(node.getText());
    try {
      String url = "./" + URLEncoder.encode(node.getText().replace(' ', '-'), "UTF-8") + ".html";
      if (javadocLinks != null && javadocLinks.size() > 0 && node.getText().contains(":")) {
        try {
          if (node.getText().contains(":")) {
            return renderJavadoc(node);
          }
        } catch (URISyntaxException e) {
          throw new RuntimeException("Unable to parse link", e);
        }
      }
      return new Rendering(url, node.getText());
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException();
    }
  }

  /**
   * [[Key:JavaDocClass]] [[Name Key:JavaDocClass]]
   */
  private Rendering renderJavadoc(WikiLinkNode node) throws URISyntaxException {
    String text = node.getText();

    String name = null;
    String key = null;
    String javaDocClass = null;

    if (text.contains(" ")) {
      String[] splitSpace = text.split(" ");
      name = splitSpace[0];
      text = splitSpace[1];
    }

    String[] splitCol = text.split(":", 2);
    key = splitCol[0];
    javaDocClass = splitCol[1];

    String href = fetchJavaDocClassUrl(key, javaDocClass);

    if (name == null) {
      name = javaDocClass.replaceFirst("^[^A-Z]*", "");
    }

    return new Rendering(href, name).withAttribute("data-full-load","true");
  }

  private String fetchJavaDocClassUrl(String key, String javaDocClass) throws URISyntaxException {
    String path = javadocLinks.get(key) + javaDocClass.replaceAll(Pattern.quote("."), "/").replaceAll(Pattern.quote("$"), ".") + ".html";
    if (new URI(path).isAbsolute()) {
      return path;
    } else {
      return depth + path;
    }
  }

  @Override
  public Rendering render(ExpLinkNode node, String text) {
    try {
      String url = "./" + URLEncoder.encode(node.url.replace(' ', '-'), "UTF-8") + ".html";
      if (javadocLinks != null && javadocLinks.size() > 0 && node.url.contains(":")) {
        try {
          String[] keyAndType = node.url.split(":",2);
          if (keyAndType.length == 2 && javadocLinks.containsKey(keyAndType[0])) {
            return new Rendering(fetchJavaDocClassUrl(keyAndType[0], keyAndType[1]), text).withAttribute("data-full-load","true");
          }
        } catch (URISyntaxException e) {
          throw new RuntimeException("Unable to parse link", e);
        }
      }
      return super.render(node, text);
    } catch (UnsupportedEncodingException e) {
      throw new IllegalStateException();
    }
  }
}
