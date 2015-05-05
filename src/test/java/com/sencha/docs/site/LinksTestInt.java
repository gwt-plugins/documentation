package com.sencha.docs.site;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.sencha.docs.site.utils.CrawlStat;
import com.sencha.docs.site.utils.CrawlerOptions;
import com.sencha.docs.site.utils.StatusHandlerCrawlController;

public class LinksTestInt {

  public final static String REMOTE_SITE = "http://127.0.0.1:8080";
  public final static int NUMBER_OF_CRAWLERS = 1;

  @Test
  public void testLinksOnLocalHost() {
    CrawlerOptions options = new CrawlerOptions();
    options.setSite(REMOTE_SITE);
    options.setNumberOfCrawlers(NUMBER_OF_CRAWLERS);

    StatusHandlerCrawlController crawler = new StatusHandlerCrawlController(options);
    try {
      crawler.run();
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }

    List<CrawlStat> crawlStats = crawler.getCrawlStats();
    if (crawlStats == null) {
      return;
    }
    
    int totalBroken = 0;
    for (CrawlStat stat : crawlStats) {
      if (stat.isBroken()) {
        totalBroken++;
        System.out.println(stat); 
      }
    }
    
    assertEquals(0, totalBroken);
  }

}
