<%@ include file="init.jsp" %>
<liferay-ui:error key="fileUploadError" message="file-upload-error" />
<liferay-ui:error key="fileWrongFormat" message="file-wrong-format" />
<liferay-ui:error key="errorReadingZip" message="file-error-reading-zip" />
<liferay-ui:error key="errorParsingElectronics" message="parsing-error-electronics"/>
<liferay-ui:error key="errorParsingElectronicsEmployee" message="parsing-error-electronicsemployee"/>
<liferay-ui:error key="errorParsingElectronicsType" message="parsing-error-electronicstype"/>
<liferay-ui:error key="errorParsingEmployee" message="parsing-error-employee"/>
<liferay-ui:error key="errorParsingPositionType" message="parsing-error-positiontype"/>
<liferay-ui:error key="errorParsingPurchase" message="parsing-error-purchase"/>
<liferay-ui:error key="errorParsingPurchaseType" message="parsing-error-purchasetype"/>
<portlet:defineObjects />

<portlet:actionURL var="uploadURL" name="uploadZip">

</portlet:actionURL>

<b>Upload an Archive</b>

<aui:form action="<%= uploadURL %>" enctype="multipart/form-data" method="post">
    <aui:input type="file" name="fileName" />
    <aui:button type="submit" value="Save" />
</aui:form>
