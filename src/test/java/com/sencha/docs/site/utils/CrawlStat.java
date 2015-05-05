package com.sencha.docs.site.utils;

import java.util.ArrayList;
import java.util.List;

import edu.uci.ics.crawler4j.url.WebURL;

public class CrawlStat {

  private List<WebURL> webUrls;

  public void addBrokenUrl(WebURL webUrl) {
    if (webUrls == null) {
      webUrls = new ArrayList<WebURL>();
    }
    webUrls.add(webUrl);
  }

  public List<WebURL> getWebUrls() {
    return webUrls;
  }
  
  public boolean isBroken() {
    return webUrls != null && webUrls.size() != 0;
  }

  @Override
  public String toString() {
    String s = "";
    if (webUrls != null) {
      for (WebURL url : webUrls) {
        s += "BROKEN: Page=" + url.getParentUrl() + " url=" + url.getURL() + "\n";
      }
    }
    return s;
  }
  
}
