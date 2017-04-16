<#include "template.ftl">

<#assign page_title="Markdown to ODT converter"/>

<#macro page_body>
<#escape x as x?html>

<h2>Last entries</h2>

<#list entries as entry>
  <div class="panel-group">
  <div class="panel panel-default">

    <div class="panel-heading log-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" href="#i${entry.id}">
          <#if entry.exception??>
            <span class="glyphicon glyphicon-remove" style="color: red"></span>
          <#else>
            <span class="glyphicon glyphicon-ok" style="color: green"></span>
          </#if>
          <span>
            <b>id</b>: ${entry.id},
            <b>date</b>: ${entry.startTime?number_to_datetime?string('dd-MM-yyyy HH:mm:ss')},
            <b>duration</b>: ${entry.endTime - entry.startTime} ms
          </span>
        </a>
      </h4>
    </div>

    <div id="i${entry.id}" class="panel-collapse collapse">
      <div class="panel-body">
        <p class="log">${entry.log}</p>

        <#if entry.exception??>
          <p class="error-log">${entry.exception}</p>
        </#if>
      </div>
    </div>

  </div>
  </div>
<#else>
  Nothing :(
</#list>

</#escape>
</#macro>

<@display_page/>