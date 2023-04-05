<%@ include file="init.jsp" %>
<liferay-ui:error key="fileUploadError" message="file-upload-error" />
<liferay-ui:error key="fileWrongFormat" message="file-wrong-format" />
<liferay-ui:error key="errorReadingZip" />
<portlet:defineObjects />


<portlet:actionURL var="uploadURL" name="uploadZip">

</portlet:actionURL>

<b>Upload an Archive</b>

<aui:form action="<%= uploadURL %>" enctype="multipart/form-data" method="post">
    <aui:input type="file" name="fileName" />
    <aui:button type="submit" value="Save" />
</aui:form>
