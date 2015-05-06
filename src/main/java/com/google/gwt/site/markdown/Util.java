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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.google.gwt.site.markdown.fs.MDNode;

public class Util {

  public static String getStringFromFile(File file) throws IOException {
    FileInputStream fileInputStream = null;
    try {
      fileInputStream = new FileInputStream(file);
      return IOUtils.toString(fileInputStream, "UTF-8");
    } finally {
      IOUtils.closeQuietly(fileInputStream);
    }
  }

  public static void writeStringToFile(File file, String content) throws IOException {
    FileOutputStream fileOutputStream = null;
    try {
      fileOutputStream = new FileOutputStream(file);
      IOUtils.write(content, fileOutputStream);
    } finally {
      IOUtils.closeQuietly(fileOutputStream);
    }
  }

  /**
   * Resource path depth from markdown. Url path depth from root.<br/>
   * Example: $baseUrlnode.html to ../../../node.html
   * 
   * @param relativeSiteDepth
   *          - site depth to resources
   * @param node
   *          - node being used to calculate its depth
   */
  public static String getBaseUrl(int relativeSiteDepth, MDNode node) {
    int depth = node.getDepth();

    StringBuffer pathBuffer = new StringBuffer();

    depth += relativeSiteDepth;

    pathBuffer.append("./");

    for (int i = 1; i < depth; i++) {
      pathBuffer.append("../");
    }

    String baseUrl = pathBuffer.toString();

    return baseUrl;
  }

}
