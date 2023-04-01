<%@ page import="shop.model.ElectronicsType" %>
<%@ page import="shop.service.ElectronicsTypeLocalServiceUtil" %>
<%@ page import="java.util.List" %>
<%@ include file="../init.jsp" %>

<liferay-portlet:actionURL name="addElectronics" var="addElectronicsActionURL" />

<p><h1>Add new electronics</h1></p>
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
    <aui:input name="archived" type="checkbox" label="Archived"/>
    <aui:input name="description" type="text" required="true" maxlength="5000" placeholder="Description"/>
    <aui:button-row>
        <aui:button name="submitButton" type="submit" value="Submit" />
    </aui:button-row>
</aui:form>