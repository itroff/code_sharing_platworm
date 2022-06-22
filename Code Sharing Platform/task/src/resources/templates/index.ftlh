<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Code</title>
    <link rel="stylesheet" href="/style.css">
    <link rel="stylesheet"
           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<span id="load_date">
    ${snippet.formattedDate()}
</span>
<#if snippet.timeRestrict == true>
<p>
    The code will be available for <b>
    <span id="time_restriction">${snippet.time}</span> </b> seconds
</p>
</#if>
<#if snippet.viewsRestrict == true>
<p>
    <b><span id="views_restriction">${snippet.views}</span></b>
    more views allowed
</p>
</#if>
<pre id="code_snippet">
    <code>
    ${snippet.code}
    </code>
</pre>
</body>
</html>