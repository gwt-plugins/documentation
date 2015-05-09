/*
 * Copyright 2013 Daniel Kurka
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.site.webapp.client;

import static com.google.gwt.query.client.GQuery.$;
import static com.google.gwt.query.client.GQuery.body;
import static com.google.gwt.query.client.GQuery.window;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.query.client.Properties;
import com.google.gwt.query.client.js.JsUtils;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;

public class GWTProjectEntryPoint implements EntryPoint {

  private static final RegExp isSameOriginRexp;
  private static Properties history = JsUtils.prop(window, "history");
  private static boolean isPushstateCapable = history.get("pushState") != null;
  static {
    // XHR must match all: protocol, host and port.
    // Note: in chrome this could be simpler since it has window.location.origin
    String origin = Window.Location.getProtocol() + "//" + Window.Location.getHostName();
    String port = Window.Location.getPort();
    if (port != null && port.length() > 0) {
      origin += ":" + port;
    }
    // We discard links with a different origin, hash links and protocol-agnostic urls.
    isSameOriginRexp = RegExp.compile("^" + origin + "|^(?!(https?|mailto|ftp|javascript):|#|//).+", "i");
  }
  private String currentPage;
  /**
   * Used for recursion exit
   */
  private int loadCount;

  @Override
  public void onModuleLoad() {
    injectScripts();

    enhanceMenu();

    openMenu();

    maybeBindPopState();

    currentPage = Window.Location.getPath();
  }

  private void openMenu() {
    $("#tpl-toc a.selected").removeClass("selected");

    String path = Window.Location.getPath();

    $("#tpl-toc a[href$='" + path + "']").addClass("selected").parentsUntil("#tpl-toc").filter("li.folder")
        .addClass("open").children("ul").slideDown(200);
  }

  private void enhanceMenu() {
    $("li.folder > a").click(new Function() {
      @Override
      public boolean f(Event e) {
        toggleMenu($(e).parent());
        return false;
      }
    });

    $("#tpl-toc li.folder > ul").hide();

    $("#tpl-toc a").each(new Function() {
      String wd = Window.Location.getPath().substring(0, Window.Location.getPath().lastIndexOf("/"));

      @Override
      public void f(Element elt) {
        // if relative, make absolute on our first hit
        String href = $(elt).attr("href");
        if (isSameOriginRexp.test(href) && !href.startsWith("/")) {
          // remove ..'s and corresponding pieces of the path in wd
          $(elt).attr("href", canonicalize(wd, href));
        }
      }

      private String canonicalize(String path1, String path2) {
        assert !path1.endsWith("/");
        while (path2.startsWith("../")) {
          assert !path1.endsWith("/");
          // remove the next path segment and its /
          path1 = path1.substring(0, path1.lastIndexOf("/"));
          path2 = path2.substring("../".length());
        }
        return path1 + "/" + path2;
      }
    });

    $("a", body).live(Event.ONCLICK, new Function() {
      @Override
      public boolean f(Event e) {
        // do not load links that are marked as full page reload
        String async = $(e).attr("data-full-load");
        if ("true".equals(async)) {
          return true;
        }

        String href = $(e).attr("href");

        // do not load javadoc async
        // if(href.contains("/TODO/javadoc/")) {
        // return true;
        // }

        if (isPushstateCapable && isSameOriginRexp.test(href)) {
          loadPage(href);
          return false;
        }
        return true;
      }
    });

    $("#tpl-toc li.folder > ul[expand='true']").each(new Function() {
      @Override
      public void f(Element e) {
        toggleMenu($(e.getParentElement()).first());
      }
    });
  }

  private void toggleMenu(GQuery menu) {
    menu.toggleClass("open").children("ul").slideToggle(200);
  }

  private void loadPage(String pageUrl) {
    if (pageUrl != null) {
      JsUtils.runJavascriptFunction(history, "pushState", null, null, pageUrl);
    }

    final String path = Window.Location.getPath();
    final String hash = Window.Location.getHash();

    if (!path.equals(currentPage)) {
      currentPage = path;

      $("#tpl-content").load(path + " #tpl-content > div", null, new Function() {
        @Override
        public void f() { 
          onPageLoad(path, hash);
        }
      });

    } else {
      scrollTo(hash);
    }
  }

  protected void onPageLoad(String path, String hash) {
    //prettyPrint();
    scrollTo(hash);
    openMenu();
    
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        displayHighlighting();
      }
    });
  }

  private void scrollTo(String hash) {
    if (hash == null || hash.length() == 0) {
      Window.scrollTo(0, 0);
    } else {
      GQuery anchor = $(hash);
      if (anchor.isEmpty()) {
        anchor = $("[name='" + hash.substring(1) + "']");
      }

      anchor.scrollIntoView();
    }
  }

  private void maybeBindPopState() {
    if (!isPushstateCapable) {
      return;
    }

    // Note: gQuery will support $(window).on("popstate", function) in future releases.
    window.<Properties> cast().setFunction("onpopstate", new Function() {
      @Override
      public void f() {
        loadPage(null);
      }
    });
  }

  private native void prettyPrint() /*-{
                                    $wnd.prettyPrint();
                                    }-*/;

  /**
   * inject javascript resources
   */
  private void injectScripts() {
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        injectAceScript();
      }
    });
  }

  /**
   * Load script then run display highlighting
   */
  private void injectAceScript() {
    loadCount++;
    if (loadCount > 20) {
      // failed to load....
      return;
    }

    isAceReady();
  }

  private void isAceReady() {
    Scheduler.get().scheduleFixedDelay(new RepeatingCommand() {
      @Override
      public boolean execute() {
        boolean isReady = isAceReadyJsni();

        if (isReady) {
          displayHighlighting();
        }

        loadCount++;

        // don't go on forever
        if (loadCount > 100) {
          isReady = true;
        }
        return !isReady;
      }
    }, 50);
  }

  private static void displayHighlighting() {
    NodeList<com.google.gwt.dom.client.Element> codeEditorDivs = $(".code-editor").select().get();
    if (codeEditorDivs != null) {
      for (int i = 0; i < codeEditorDivs.getLength(); i++) {
        com.google.gwt.dom.client.Element codeEditorDiv = codeEditorDivs.getItem(i);
        displayHighlighting(codeEditorDiv, false);
      }
    }

    NodeList<com.google.gwt.dom.client.Element> codePres = Document.get().getElementsByTagName("pre");
    if (codePres != null) {
      for (int i = 0; i < codePres.getLength(); i++) {
        com.google.gwt.dom.client.Element preElement = codePres.getItem(i);
        displayHighlighting(preElement, true);
      }
    }
  }
  
  /**
  * <pre mode='javascript'/> will set the ace highlighter lanugage, default is javascript
  */
 private static void displayHighlighting(com.google.gwt.dom.client.Element editorDivElement, boolean readOnly) {
   com.google.gwt.dom.client.Element parent = editorDivElement.getParentElement();

   // ace editor wants a specific height, Overall state height by style="height:100px;";
   int height = getHeight(editorDivElement);
   int width = editorDivElement.getOffsetWidth();

   editorDivElement.getStyle().setHeight(height, Unit.PX);
   editorDivElement.getStyle().setWidth(width, Unit.PX);

   String mode = editorDivElement.getAttribute("mode");
   if (mode != null && !mode.isEmpty()) {
     mode = "ace/mode/" + mode.toLowerCase();
   } else {
     mode = "ace/mode/javascript";
   }

   String theme = editorDivElement.getAttribute("theme");
   if (theme != null && !theme.isEmpty()) {
     theme = "ace/theme/" + theme.toLowerCase();
   } else {
     theme = "ace/theme/chrome";
   }

   String maxLines = "Infinity";
   if (parent.getAttribute("maxLines") != null && !parent.getAttribute("maxLines").isEmpty()) {
     maxLines = parent.getAttribute("maxLines");
   }

   displayHighlighting(editorDivElement, mode, theme, readOnly, maxLines);
 }
 
 /**
  * Ace highlighter wants a specific height
  */
 private static int getHeight(com.google.gwt.dom.client.Element element) {
   int offsetHeight = element.getOffsetHeight();
   if (offsetHeight >= 300) {
     offsetHeight = 300;
   }
   return offsetHeight;
 }

 private static native void displayHighlighting(com.google.gwt.dom.client.Element element, String mode, String theme,
     boolean readOnly, String maxLinesAttr) /*-{
   var editor = $wnd.ace.edit(element);
   editor.getSession().setMode(mode);
   editor.getSession().setUseWorker(false);
   editor.setTheme(theme);
   editor.setShowPrintMargin(false);
   editor.renderer.setShowGutter(!readOnly);
   editor.setHighlightActiveLine(false);
   editor.setDisplayIndentGuides(true);
   editor.setReadOnly(readOnly);
   editor.renderer.setPadding(10);
   editor.renderer.setScrollMargin(10, 10, 0, 0);
   editor.setOptions({
     maxLines : maxLinesAttr
   });
 }-*/;
 
   private native boolean isAceReadyJsni() /*-{
   return !!$wnd.ace;
  }-*/;
 
}
