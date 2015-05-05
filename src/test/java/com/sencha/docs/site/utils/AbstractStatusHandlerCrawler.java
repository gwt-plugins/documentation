package com.sencha.docs.site.utils;

import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.fetcher.CustomFetchStatus;
import edu.uci.ics.crawler4j.url.WebURL;
import org.apache.http.HttpStatus;

public abstract class AbstractStatusHandlerCrawler extends WebCrawler {

  protected final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|tiff?|mid|mp2|mp3|mp4"
      + "|wav|avi|mov|mpeg|ram|m4v|pdf|rm|smil|wmv|swf|wma|zip|rar|gz))$");

  private CrawlStat crawlStat;

  public AbstractStatusHandlerCrawler() {
    crawlStat = new CrawlStat();
  }

  @Override
  public boolean shouldVisit(WebURL url) {
    String href = url.getURL().toLowerCase();
    return !FILTERS.matcher(href).matches();
  }

  @Override
  public void visit(Page page) {
    // Do nothing
  }

  @Override
  protected void handlePageStatusCode(WebURL webUrl, int statusCode, String statusDescription) {
    if (statusCode == HttpStatus.SC_OK) {
      return;
    }

    if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
      System.out.println("File moved, please update page=" + webUrl.getParentUrl() + " url=" + webUrl.getURL());
      return;
    }

    if (statusCode == CustomFetchStatus.FatalTransportError && webUrl.getURL().toLowerCase().startsWith("https:")) {
      System.out.println("Possible problem with https url, verify manually: " + webUrl.getURL());
      return;
    }

    System.out.println(statusCode + "\t" + webUrl);
    crawlStat.addBrokenUrl(webUrl);
  }

  @Override
  public CrawlStat getMyLocalData() {
    return crawlStat;
  }

}
