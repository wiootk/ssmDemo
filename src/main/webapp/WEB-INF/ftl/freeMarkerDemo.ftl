一：简单输出 ${textDemo}
二：计算||条件判断||输出特殊字符
a == ${a};b == ${b}
${a} + ${b} = ${a + b}
<#if (a > b)>
a 大于 b
<#elseif (a == b)>
a 等于 b
<#elseif (a < b)>
a 小于 b
</#if>
<#assign c = a + b>
<#noparse><#assign c = a + b>; ${c} = </#noparse>${c}
${r'<#assign c = a + b>'}; ${r'${c}'} = ${c}

    三：list 遍历
<#list list as item>
${item_index + 1}.${item}
</#list>
    四：map 遍历
<#list map?keys as key>
${key} == ${map[key]}
</#list>
    五：复杂map遍历
<#assign cmap = userinfo.cMap>
<#list cmap?keys as key>
${key} == ${cmap[key]}
</#list>
    <br>

<#assign stu={"name":"zhangsan","age":"21","sex":"man"}>
<#list stu?keys as key>
${key} = ${stu[key]};
</#list>
    <br>
    六：map嵌套遍历

<#list outerMap?keys as key>
    <#assign innerMap = outerMap[key]>
    <#if innerMap?is_hash>
        <#list innerMap?keys as _key>
        ${_key} == ${innerMap[_key]}
        </#list>
    <#elseif innerMap?is_string>
    ${key} == ${innerMap}
    <#-- ${key} == ${outerMap[key]} -->
    </#if>
</#list>
    <br>
    七：map list 嵌套遍历<br>
<#list mMap?keys as key>
    <#assign list = mMap[key]>
<#--<#if key=="innerList">-->
<#--${"string"?ends_with("List")?string}-->
    <#if key?string?ends_with("List")>
        <#list list as item>
        ${item_index + 1}.${item}
        </#list>
    <#else>
    ${key} == ${list}
    </#if>
</#list>
<#--
    十 调用静态方法
${T.staticMethod()}
    十一 枚举
${E.Summer.getSeason()}
${E.Autumn.getSeason()}
-->