<#macro page_body></#macro>

<#macro display_page>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>${page_title}</title>

    <link rel="stylesheet" type="text/css" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="/css/main.css" />

    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <!-- GitHub ribbon -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/github-fork-ribbon-css/0.2.1/gh-fork-ribbon.min.css" />
    <!--[if lt IE 9]>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/github-fork-ribbon-css/0.2.1/gh-fork-ribbon.ie.min.css" />
    <![endif]-->
    <style>
      .github-fork-ribbon:before {
        background-color: #090;
      }
    </style>

  </head>

  <body>
    <a class="github-fork-ribbon" href="https://github.com/abcBHM/MD2odt" title="Fork me on GitHub">Fork me on GitHub</a>

    <#include "template-nav.ftl">

    <div class="container" style="padding-bottom: 64px">
      <@page_body/>
    </div>

    <#include "template-footer.ftl">

  </body>
</html>
</#macro>