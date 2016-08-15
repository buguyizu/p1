<#--
/*
 * $Id$
 *
 * 2016/08/15
 * for bootstrap
 */
-->
<#if (actionMessages?? && actionMessages?size > 0 && !parameters.isEmptyList)>
<#if parameters.id??>
</#if>
<#if parameters.cssClass??>
<#else>
</#if>
<#if parameters.cssStyle??>
</#if>
    <#list actionMessages as message>
        <#if message??>
            <div class="alert alert-info" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span><span class="sr-only">Message:</span><span><#if parameters.escape>${message!?html}<#else>${message!}</#if></span></div><#rt/>
        </#if>
    </#list>
</#if>