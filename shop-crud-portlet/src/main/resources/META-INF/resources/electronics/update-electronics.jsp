<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="shop.model.Electronics" %>
<%@ page import="shop.service.ElectronicsLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.exception.PortalException" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="shop.model.ElectronicsType" %>
<%@ page import="shop.service.ElectronicsTypeLocalServiceUtil" %>
<%@ page import="java.util.List" %>
<%@ include file="../init.jsp" %>

<portlet:defineObjects/>

<liferay-portlet:actionURL name="updateElectronics" var="updateElectronicsActionURL"/>
<liferay-portlet:actionURL name="addElectronics" var="addElectronicsActionURL" />
<%
    Electronics electronics = null;
    long tmpId = ParamUtil.getLong(renderRequest, "id");
    if (tmpId > 0) {
        try {
            electronics = ElectronicsLocalServiceUtil.getElectronics(tmpId);
        } catch (PortalException e) {
            session.setAttribute("message", e.getMessage());
        }
    }
%>

<% if (Validator.isNotNull(electronics)) { %>
<p><h1>Update electronics</h1></p>
<aui:form action="<%=updateElectronicsActionURL%>" name="updateElectronicsForm" method="POST">
    <aui:input name="id" required="true" type="hidden" value='<%= electronics.getElectronicsId()%>'/>
    <aui:input name="name" required="true" maxlength="100" type="text" value='<%= electronics.getName() %>'/>
    <aui:input name="typeId" required="true" type="number" value='<%= electronics.getTypeId() %>'/>
    <aui:select class="select" name="typeId" label="Type">
        <%
            List<ElectronicsType> options = ElectronicsTypeLocalServiceUtil.getElectronicsTypes(-1, -1);
            for (ElectronicsType option : options) {
        %>
        <aui:option selected="<%= (option.getElectronicsTypeId() == electronics.getTypeId()) %>" value="<%= option.getElectronicsTypeId() %>"><%= option.getName() %></aui:option>
        <% } %>
    </aui:select>
    <aui:input name="price" required="true" min="100" type="number" value='<%= electronics.getPrice() %>'/>
    <aui:input name="count" required="true" type="number" value='<%= electronics.getCount() %>'/>
    <aui:input name="inStock"  type="checkbox" value='<%= electronics.getInStock() %>'/>
    <aui:input name="archived" type="checkbox" value='<%= electronics.getArchived() %>'/>
    <aui:input name="description" required="true" maxlength="5000" type="text" value='<%= electronics.getDescription() %>'/>
    <aui:button-row>
        <aui:button type="submit" value="Update" name="update"/>
    </aui:button-row>
</aui:form>
<% } else { %>
<p><h1>Add new electronics</h1></p>

${message}

<aui:form action="<%= addElectronicsActionURL %>" name="electronicsForm" method="POST">
    <aui:input name="name" type="text" required="true" maxlength="100" placeholder="Name"/>
    <aui:select class="select" name="typeId" label="Type">
        <%
            List<ElectronicsType> options = ElectronicsTypeLocalServiceUtil.getElectronicsTypes(-1, -1);
            for (ElectronicsType option : options) {
        %>
        <aui:option value="<%= option.getElectronicsTypeId() %>"><%= option.getName() %></aui:option>
        <% } %>
    </aui:select>
    <aui:input name="price" type="number" required="true" min="100" placeholder="Price"/>
    <aui:input name="count" type="number" required="true" placeholder="Count"/>
    <aui:input name="inStock"  type="checkbox" label="In stock"/>
    <aui:input name="archived" type="checkbox" label="Archived"/>
    <aui:input name="description" type="text" required="true" maxlength="5000" placeholder="Description"/>
    <aui:button-row>
        <aui:button name="submitButton" type="submit" value="Submit" />
    </aui:button-row>
</aui:form>
<% } %>