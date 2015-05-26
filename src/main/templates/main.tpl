<!doctype html>
<!doctype html>
<html>
<head>
  $head
  <title>$title</title>
  <meta charset="UTF-8">
</head>
<body>

  <div id="header">
    <a href="http://www.sencha.com"><img class="logo" src="$baseUrl/images/logo.png"/></a>
    <h1 id="title">GWT Plugins Documentation</h1>
    <div class="google-search">
    <script>
      (function() {
        var cx = '011138278718949652927:g-vjyw-9k8u';
        var gcse = document.createElement('script');
        gcse.type = 'text/javascript';
        gcse.async = true;
        gcse.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') +
            '//www.google.com/cse/cse.js?cx=' + cx;
        var s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(gcse, s);
      })();
    </script>
    <gcse:search linkTarget="_self" enableAutoComplete="true"></gcse:search>
    </div>
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
    	<div class="g-plusone" data-size="medium" data-annotation="none"></div>
    </div>
  </div>

  <!-- GooglePlus -->
  <script type="text/javascript">
    (function() {
      var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
      po.src = 'https://apis.google.com/js/platform.js';
      var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
    })();
  </script>

  <!-- Google Analytics -->
  <script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
    (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
    m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
    ga('create', 'UA-62291716-2', 'auto');
	ga('send', 'pageview');
  </script>
  
</body>
</html>
