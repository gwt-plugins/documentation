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

import java.util.HashMap;
import java.util.logging.Logger;

public class MarkDown {

  private final static Logger logger = Logger.getLogger(MarkDown.class.getName());

  /**
   * TODO change this into key value properties
   */
  public static void main(String[] args) throws MDHelperException, TranslaterException {
    if (args.length < 3) {
      System.out.println("Usage MarkDown <sourceDir> <outputDir> <templateFile>");
      throw new IllegalArgumentException("Usage MarkDown <sourceDir> <outputDir> <templateFile>");
    }

    String sourceDir = args[0];
    logger.info("source directory: '" + sourceDir + "'");

    String outputDir = args[1];
    logger.info("output directory: '" + outputDir + "'");

    String templateFile = args[2];
    logger.info("template file: '" + templateFile + "'");

    HashMap<String, String> links = new HashMap<String, String>();
    if (args.length > 3) {
      for (String link : args[3].split(",")) {
        String[] linkSplit = link.split(":", 2);
        String key = linkSplit[0].replaceAll("\n|\r", "").trim();
        String value = linkSplit[1].replaceAll("\n|\r", "").trim();

        links.put(key, value);
      }
      logger.info("javadocs links: '" + links + "'");
    }

    MDHelper helper = new MDHelper();
    helper
      .setOutputDirectory(outputDir)
      .setSourceDirectory(sourceDir)
      .setTemplateFile(templateFile)
      .setJavadocLinks(links)
      .create()
      .translate();

  }

}
