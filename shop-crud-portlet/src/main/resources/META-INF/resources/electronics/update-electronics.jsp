<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ include file="../init.jsp" %>
<portlet:defineObjects/>

<liferay-portlet:actionURL name="updateElectronics" var="updateElectronicsActionURL"/>


<p><h1>Add new electronics</h1></p>
<aui:form action="<%=updateElectronicsActionURL%>" name="electronicsForm" method="POST">
    <aui:input name="id" type="hidden" value='<%=ParamUtil.getLong(renderRequest, "id")%>'/>
    <aui:input name="name" type="text" value='<%=ParamUtil.getString(renderRequest, "name")%>'/>
    <aui:input name="typeId" type="number" value='<%= ParamUtil.getLong(renderRequest, "typeId") %>'/>
    <aui:input name="price" type="number" value='<%= ParamUtil.getLong(renderRequest, "price") %>'/>
    <aui:input name="count" type="number" value='<%= ParamUtil.getInteger(renderRequest, "count") %>'/>
    <aui:input name="inStock" type="checkbox" value='<%= ParamUtil.getBoolean(renderRequest, "inStock") %>'/>
    <aui:input name="archived" type="checkbox" value='<%= ParamUtil.getBoolean(renderRequest, "archived") %>'/>
    <aui:input name="description" type="text" value='<%= ParamUtil.getString(renderRequest, "description") %>'/>
    <aui:button-row>
        <aui:button type="submit" value="Update" name="update"/>
    </aui:button-row>
</aui:form>
