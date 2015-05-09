/*
 * Copyright 2013 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.site.markdown;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

import com.google.gwt.site.markdown.fs.MDNode;
import com.google.gwt.site.markdown.fs.MDParent;
import com.google.gwt.site.markdown.toc.TocCreator;

public class MDTranslater {

  /**
   * Pegdown processor features used
   */
  int options = Extensions.ALL - Extensions.HARDWRAPS;
  private PegDownProcessor pegDownProcessor = new PegDownProcessor(options, Long.MAX_VALUE);

  private final TocCreator tocCreator;
  private final MarkupWriter writer;
  private final String template;
  private Map<String, String> links;
  private MDHelper mdHelper;

  public MDTranslater(TocCreator tocCreator, MarkupWriter writer, String template, Map<String, String> javadocsLinks,
      MDHelper mdHelper) {
    this.tocCreator = tocCreator;
    this.writer = writer;
    this.template = template;
    this.mdHelper = mdHelper;

    if (javadocsLinks != null) {
      links = javadocsLinks;
    }
  }

  public void render(MDParent root) throws TranslaterException {
    renderTree(root, root);
  }

  private void renderTree(MDNode node, MDParent root) throws TranslaterException {
    if (node.isFolder()) {
      renderFolder(node, root);
    } else {
      renderFile(node, root);
    }
  }

  private void renderFile(MDNode node, MDParent root) throws TranslaterException {
    String markDown = getNodeContent(node.getPath());

    String htmlMarkDown = pegDownProcessor.markdownToHtml(markDown, new JavaDocLinkRenderer(links, node.getDepth()));

    String toc = tocCreator.createTocForNode(root, node);

    String head = createHeadForNode(node);

    // TODO should I make this a pom var and include product name
    String title = node.getDisplayName();

    String html = fillTemplate(htmlMarkDown, toc, head, title);

    html = modifyTocPage(html);
    // html = addPrettify(html);
    html = addBaseUrls(node, html);
    html = optimizeForSeoIndexing(html);
    html = addPreAndAddHighlighting(html);

    writer.writeHTML(node, html);
  }

  private void renderFolder(MDNode node, MDParent root) throws TranslaterException {
    MDParent mdParent = node.asFolder();

    List<MDNode> children = mdParent.getChildren();
    for (MDNode mdNode : children) {
      renderTree(mdNode, root);
    }

    if (mdParent.isImageDirs()) {
      writer.copyImageDirs(mdParent);
    }
  }

  /**
   * Add prettify class name to <code>
   * 
   * @param html
   * @return html
   */
  private String addPrettify(String html) {
    html = html.replaceAll("<pre>", "<pre class=\"prettyprint\">");
    return html;
  }

  private String createHeadForNode(MDNode node) {
    int depth = node.getDepth();
    StringBuffer buffer = new StringBuffer();

    for (int i = 1; i < depth; i++) {
      buffer.append("../");
    }

    String baseUrl = buffer.toString();
    StringBuffer headBuffer = new StringBuffer();

    // site resource links
    headBuffer.append("<link href='");
    headBuffer.append(baseUrl);
    headBuffer.append("css/main.css' rel='stylesheet' type='text/css'>");

    headBuffer.append("<script type=\"text/javascript\" src=\"");
    headBuffer.append(baseUrl);
    headBuffer.append("gwtproject/gwtproject.nocache.js\"></script>");

    // headBuffer.append("<link href='");
    // headBuffer.append(baseUrl);
    // headBuffer.append("prettify/prettify.css' type='text/css' rel='stylesheet' />");

    // ace script
    headBuffer.append("<script type='text/javascript' src='");
    headBuffer.append(baseUrl);
    headBuffer.append("ace/ace.js'></script>");

    headBuffer.append("<script type='text/javascript' src='");
    headBuffer.append(baseUrl);
    headBuffer.append("prettify/prettify.js'></script>");

    return headBuffer.toString();
  }

  private String fillTemplate(String html, String toc, String head, String title) {
    return template.replace("$content", html).replace("$toc", toc).replace("$head", head).replace("$title", title);
  }

  private String getNodeContent(String path) throws TranslaterException {
    try {
      return Util.getStringFromFile(new File(path));
    } catch (IOException e1) {
      throw new TranslaterException("can not load content from file: '" + path + "'", e1);
    }
  }

  private String addBaseUrls(MDNode node, String html) {
    String baseUrl = getBaseUrlWithOutLastSlash(node);
    html = html.replaceAll("\\$baseUrl", baseUrl);
    return html;
  }

  private String getBaseUrlWithOutLastSlash(MDNode node) {
    String baseUrl = Util.getBaseUrl(mdHelper.getRelativeSiteDepth(), node);
    baseUrl = baseUrl.replaceFirst("/$", "");
    return baseUrl;
  }

  /**
   * Provide some structure attributes to the html for easy seo.
   * 
   * @See http://schema.org/Article
   * @See http://schema.org/docs/gs.html
   */
  private String optimizeForSeoIndexing(String htmlPage) {
    Document doc = Jsoup.parse(htmlPage);

    // add item scope and type
    Element content = doc.select("#content").first();
    if (content != null) {
      content.attr("itemscope", "");
      content.attr("itemtype", "http://schema.org/Article");
    }

    // add name
    // move name above article body
    Element h1 = doc.select("#tpl-content-articleBody > h1").first();
    if (h1 != null) {
      Element h1clone = h1.clone();
      h1.remove();

      // move h1 to name div, putting name div ontop of the articleBody
      Element moveh1to = doc.select("#tpl-content-name").first();
      moveh1to.attr("itemprop", "name");
      moveh1to.appendChild(h1clone);
    }

    // add articleBody
    Element articleBody = doc.select("#tpl-content-articleBody").first();
    if (articleBody != null) {
      articleBody.attr("itemprop", "articleBody");
    }

    // add lastUpdated
    Element lastUpdated = doc.select("#tpl-content-lastUpdated-date").first();
    if (lastUpdated != null) {
      lastUpdated.attr("itemprop", "datePublished");
      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
      lastUpdated.attr("datetime", getDatePublishedComputerFriendly(cal));
      lastUpdated.html(getDatePublishedPeopleFriendly(cal));
    }
    return doc.html();
  }

  private String getDatePublishedComputerFriendly(Calendar cal) {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
    String html = df.format(cal.getTime());
    return html;
  }

  private String getDatePublishedPeopleFriendly(Calendar cal) {
    DateFormat df = new SimpleDateFormat("MMMM d, yyyy");
    String html = df.format(cal.getTime());
    return html;
  }

  private String addPreAndAddHighlighting(String html) {
    String highlightMode = "chrome";
    if (highlightMode == null || highlightMode.isEmpty() || !html.contains("<pre")) {
      return html;
    }

    Document doc = Jsoup.parse(html);
    Elements elements = doc.select("pre");
    for (int i = 0; i < elements.size(); i++) {
      Element element = elements.get(i);

      String attributeKey = "mode";
      String attributeValue = highlightMode;
      if (!element.hasAttr(attributeKey)) {
        element.attr(attributeKey, attributeValue);
      }

      attributeKey = "theme";
      attributeValue = highlightMode;
      if (!element.hasAttr(attributeKey)) {
        element.attr(attributeKey, attributeValue);
      }
    }

    return doc.html();
  }

  /**
   * Get the page table of contents.
   */
  private String modifyTocPage(String htmlPage) {
    Document doc = Jsoup.parse(htmlPage);

    Elements elements = doc.select(".tpl-content > h1, h2, h3, h4, h5, h6, h7, h8, h9, h10");
    if (elements == null || elements.size() < 3) {
      return turnOffPageToc(doc);
    }

    String pageTocHtml = "";
    for (int i = 0; i < elements.size(); i++) {
      Element element = elements.get(i);

      String html = element.html();
      String hash = html.replaceAll("[^A-Za-z0-9]", "_");
      String tag = element.tagName().toLowerCase();

      element.attr("id", hash);

      String item = "<a class=\"toc-page-" + tag + "\" href='#" + hash + "' title='" + html + "'>" + html + "</a>";
      pageTocHtml += item + "\n";
    }

    // find the page toc node and insert the html
    Element pageTocNode = doc.select(".toc-page").first();
    if (pageTocNode != null) {
      pageTocNode.append(pageTocHtml);
      pageTocNode.attr("style", "display:block;");
    }

    return doc.html();
  }

  private String turnOffPageToc(Document doc) {
    Element pageTocNode = doc.select(".toc-page").first();
    if (pageTocNode != null) {
      pageTocNode.attr("style", "display:none;");
    }
    return doc.html();
  }

}
