<#--
/*
 * $Id$
 *
 * 2016/08/15
 * for bootstrap
 */
-->
<@s.iterator value="parameters.list">
    <#if parameters.listKey??>
        <#assign itemKey = stack.findValue(parameters.listKey)/>
        <#assign itemKeyStr = stack.findString(parameters.listKey)/>
    <#else>
        <#assign itemKey = stack.findValue('top')/>
        <#assign itemKeyStr = stack.findString('top')>
    </#if>
    <#if parameters.listValueKey??>
        <#-- checks the valueStack for the 'valueKey.' The valueKey is then looked-up in the locale 
             file for it's localized value.  This is then used as a label -->
        <#assign itemValue = stack.findString(parameters.listValueKey)/>
        <#-- FIXME: find a better way to get the value than a call to @s.text -->
        <#assign itemValue><@s.text name="${itemValue}"/></#assign>
    <#elseif parameters.listValue??>
        <#assign itemValue = stack.findString(parameters.listValue)/>
    <#else>
        <#assign itemValue = stack.findString('top')/>
    </#if>
    <#if parameters.listCssClass??>
        <#if stack.findString(parameters.listCssClass)??>
          <#assign itemCssClass= stack.findString(parameters.listCssClass)/>
        <#else>
          <#assign itemCssClass = ''/>
        </#if>
    </#if>
    <#if parameters.listCssStyle??>
        <#if stack.findString(parameters.listCssStyle)??>
          <#assign itemCssStyle= stack.findString(parameters.listCssStyle)/>
        <#else>
          <#assign itemCssStyle = ''/>
        </#if>
    </#if>
    <#if parameters.listTitle??>
        <#if stack.findString(parameters.listTitle)??>
          <#assign itemTitle= stack.findString(parameters.listTitle)/>
        <#else>
          <#assign itemTitle = ''/>
        </#if>
    </#if>
<label class="btn btn-default<#if tag.contains(parameters.nameValue!'', itemKey)> active</#if>" <#include "/${parameters.templateDir}/${parameters.expandTheme}/css.ftl"/>><#rt/>
    <input type="radio"<#rt/>
    <#if parameters.name?has_content>
     name="${parameters.name?html}"<#rt/>
    </#if>
     id="${parameters.id?html}${itemKeyStr?html}"<#rt/>
    <#if tag.contains(parameters.nameValue!'', itemKey)>
     checked="checked"<#rt/>
    </#if>
    <#if itemKey??>
     value="${itemKeyStr?html}"<#rt/>
    </#if>
    <#if parameters.disabled!false>
     disabled="disabled"<#rt/>
    </#if>
    <#if parameters.tabindex?has_content>
     tabindex="${parameters.tabindex?html}"<#rt/>
    </#if>
    <#if itemCssClass?has_content>
     class="${itemCssClass?html}"<#rt/>
    </#if>
    <#if itemCssStyle?has_content>
     style="${itemCssStyle?html}"<#rt/>
    </#if>
    <#if itemTitle?has_content>
     title="${itemTitle?html}"<#rt/>
    <#else>
        <#if parameters.title?has_content>
     title="${parameters.title?html}"<#rt/>
        </#if>
    </#if>
    <#include "/${parameters.templateDir}/${parameters.expandTheme}/css.ftl" />
    <#include "/${parameters.templateDir}/${parameters.expandTheme}/scripting-events.ftl" />
    <#include "/${parameters.templateDir}/${parameters.expandTheme}/common-attributes.ftl" />
    <#include "/${parameters.templateDir}/${parameters.expandTheme}/dynamic-attributes.ftl" />
    /><#rt/>
    ${itemValue}<#t/>
</label>
</@s.iterator>