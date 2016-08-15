<#--
/*
 * $Id$
 *
 * 2016/08/15
 * for bootstrap
 */
-->
<#if fieldErrors??><#t/>
    <#assign eKeys = fieldErrors.keySet()><#t/>
    <#assign eKeysSize = eKeys.size()><#t/>
    <#assign doneStartUlTag=false><#t/>
    <#assign doneEndUlTag=false><#t/>
    <#assign haveMatchedErrorField=false><#t/>
    <#if (fieldErrorFieldNames?size > 0) ><#t/>
        <#list fieldErrorFieldNames as fieldErrorFieldName><#t/>
            <#list eKeys as eKey><#t/>
                <#if (eKey = fieldErrorFieldName)><#t/>
                    <#assign haveMatchedErrorField=true><#t/>
                    <#assign eValue = fieldErrors[fieldErrorFieldName]><#t/>
                    <#if (haveMatchedErrorField && (!doneStartUlTag))><#t/>
                        <#if parameters.id?has_content>
                        </#if>
                        <#if parameters.cssClass?has_content>
                            <#else>
                        </#if>
                        <#if parameters.cssStyle?has_content>
                        </#if>
                        <#assign doneStartUlTag=true><#t/>
                    </#if><#t/>
                    <#list eValue as eEachValue><#t/>
                        <div class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span><span class="sr-only">Error:</span><span><#if parameters.escape>${eEachValue!?html}<#else>${eEachValue!}</#if></span></div><#rt/>
                    </#list><#t/>
                </#if><#t/>
            </#list><#t/>
        </#list><#t/>
        <#if (haveMatchedErrorField && (!doneEndUlTag))><#t/>
            <#assign doneEndUlTag=true><#t/>
        </#if><#t/>
        <#else><#t/>
        <#if (eKeysSize > 0)><#t/>
            <#if parameters.cssClass?has_content>
                <#else>
            </#if>
            <#if parameters.cssStyle?has_content>
            </#if>
            <#list eKeys as eKey><#t/>
                <#assign eValue = fieldErrors[eKey]><#t/>
                <#list eValue as eEachValue><#t/>
                    <div class="alert alert-danger" role="alert"><span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span><span class="sr-only">Error:</span><span><#if parameters.escape>${eEachValue!?html}<#else>${eEachValue!}</#if></span></div><#rt/>
                </#list><#t/>
            </#list><#t/>
        </#if><#t/>
    </#if><#t/>
</#if><#t/>