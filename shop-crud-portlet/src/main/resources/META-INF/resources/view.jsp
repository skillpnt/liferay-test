<%@ include file="init.jsp" %>
<portlet:defineObjects />


<portlet:actionURL var="uploadURL" name="uploadZip">

</portlet:actionURL>

<b>Upload an Archive</b>

<aui:form action="<%= uploadURL %>" enctype="multipart/form-data" method="post">
    <aui:input type="file" name="fileName" />
    <aui:button type="submit" value="Save" />
</aui:form>
