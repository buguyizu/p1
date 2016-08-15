<#--
/*
 * $Id$
 *
 * 2016/08/15
 * for bootstrap
 */
-->
<#if (actionErrors?? && actionErrors?size > 0)>
<#if parameters.id??>
</#if>
<#if parameters.cssClass??>
<#else>
</#if>
<#if parameters.cssStyle??>
</#if>
    <#list actionErrors as error>
        <#if error??>
            <div class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span><span class="sr-only">Error:</span><span><#if parameters.escape>${error!?html}<#else>${error!}</#if></span></div><#rt/>
        </#if>
    </#list>
</#if>