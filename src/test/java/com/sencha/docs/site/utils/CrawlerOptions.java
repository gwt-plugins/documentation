package com.sencha.docs.site.utils;

public class CrawlerOptions {

  public String site;
  private int numberOfCrawlers = 1;

  public String getSite() {
    return site;
  }

  public void setSite(String site) {
    this.site = site;
  }

  public int getNumberOfCrawlers() {
    return numberOfCrawlers;
  }

  public void setNumberOfCrawlers(int numberOfCrawlers) {
    this.numberOfCrawlers = numberOfCrawlers;
  }
  
}
