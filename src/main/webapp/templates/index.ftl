<#include "template.ftl">

<#assign page_title="Markdown to ODT converter"/>

<#macro page_body>
<div class="container">
  <div class="col-sm-3"></div>
  <div class="col-sm-6">
    <div class="well">
      <#include "upload-form.ftl">
      <div class="note alert alert-info">
        See <a href="https://github.com/abcBHM/MD2odt/tree/master/src/test/resources">there</a> for examples.
      </div>
    </div>
  </div>
  <div class="col-sm-3"></div>
</div>
</#macro>

<@display_page/>