<#include "template.ftl">

<#assign page_title="Markdown to ODT converter - error"/>

<#macro page_body>
  <div class="alert alert-danger error-output">
      ${message}
  </div>
</#macro>

<@display_page/>