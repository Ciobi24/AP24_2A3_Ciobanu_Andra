<!DOCTYPE html>
<html>
<head>
    <title>Repository Report</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        h1 {
            color: #333;
        }

        h2 {
            color: #666;
        }
    </style>
</head>
<body>
<h1>Repository Report</h1>
<#if repository??>
    <h1>${repository.getDirectory()}</h1>
    <#list repository.documents as person, value>
        <h2>${person}</h2>
        <ul>
            <#list value as document>
                    <#if document?has_content>
                   <li>${document}</li>
                    </#if>
            </#list>
        </ul>
    </#list>
<#else>
    <p>No data available.</p>
</#if>
</body>
</html>