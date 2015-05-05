package com.sencha.docs.site.utils;

import java.util.List;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class StatusHandlerCrawlController {

  private CrawlerOptions options;
  private List<CrawlStat> crawlersLocalData;

  public StatusHandlerCrawlController(CrawlerOptions options) {
    this.options = options;
  }

  public void run() throws Exception {
    String crawlStorageFolder = "target/crawler";

    CrawlConfig config = new CrawlConfig();
    config.setCrawlStorageFolder(crawlStorageFolder);
    config.setPolitenessDelay(0);
    config.setMaxDepthOfCrawling(5);
    config.setMaxPagesToFetch(5000);
    config.setMaxDownloadSize(8388608);//8mb instead of 1mb, larger than the current biggest file
    config.setResumableCrawling(false);
    config.setIncludeHttpsPages(true);

    PageFetcher pageFetcher = new PageFetcher(config);
    RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
    RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

    CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
    controller.addSeed(options.getSite());

    //stopgap measure to confine tests if internet isn't available
    if ("true".equals(System.getProperty("offline"))) {
      controller.start(LocalStatusCrawler.class, options.getNumberOfCrawlers());
    } else {
      controller.start(RemoteStatusCrawler.class, options.getNumberOfCrawlers());
    }

    @SuppressWarnings({"raw", "unchecked"})
    List<CrawlStat> stats = (List) controller.getCrawlersLocalData();
    crawlersLocalData = stats;
  }
  
  public List<CrawlStat> getCrawlStats() {
    return crawlersLocalData;
  }

}
