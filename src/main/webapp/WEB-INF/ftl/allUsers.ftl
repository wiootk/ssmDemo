<html>
<head>
    <title>allUsers</title>
</head>
<body>
<#list resultMap.userList as user>
Welcome ${user.name}!&nbsp;&nbsp;id:${user.password}<br/>
</#list>
<p>Our latest product:
    <a href="${resultMap.lastProduct.url}">${resultMap.lastProduct.name}  </a>!
</body>
</html>