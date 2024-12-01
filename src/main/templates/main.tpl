<!doctype html>
<html>
<head>
  $head
  <title>$title</title>
  <meta charset="UTF-8">
</head>
<body>

  <div id="header">
    <a href="https://gwt-plugins.github.io/documentation/"><img class="logo" src="$baseUrl/images/logo.png"/></a>
    <h1 id="title">GWT Plugins Documentation</h1>
  </div>

  <div id="content">
    <div id="tpl-toc">
      <div id="tpl-toc-content">$toc</div>
    </div>
    <div id="middle">
      <div id="tpl-content">
        <div>
          <div class="toc-page">
            Page Contents
          </div>
          <div id="tpl-content-name"></div>
          <div id="tpl-content-articleBody">
            $content
          </div>
          <div class="tpl-content-lastUpdated">Last updated
            <div id="tpl-content-lastUpdated-date"></div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <div id="footer">
  	<div id="footer-social">
    </div>
  </div>

</body>
</html>
