<%@ page import="java.util.List" %>
<%@ page import="shop.model.Electronics" %>
<%@ page import="shop.service.ElectronicsLocalServiceUtil" %>
<%@ page import="shop.model.purchaseType" %>
<%@ page import="shop.service.purchaseTypeLocalServiceUtil" %>
<%@ page import="shop.model.Employee" %>
<%@ page import="shop.service.EmployeeLocalServiceUtil" %>
<%@ include file="../init.jsp" %>
<portlet:defineObjects />

<liferay-portlet:actionURL name="addPurchase" var="addPurchaseActionURL" />

<p><h1>Add new purchase</h1></p>
<aui:form action="<%= addPurchaseActionURL %>" name="purchaseForm" method="POST">
    <aui:select class="select" name="electronicsId" label="Electronics ID">
        <%
            List<Electronics> options = ElectronicsLocalServiceUtil.getElectronicses(-1, -1);
            for (Electronics option : options) {
        %>
        <aui:option value="<%= option.getElectronicsId() %>"><%= option.getName() %></aui:option>
        <% } %>
    </aui:select>

    <aui:select class="select" name="employeeId" label="Employee ID">
        <%
            List<Employee> options = EmployeeLocalServiceUtil.getEmployees(-1, -1);
            for (Employee option : options) {
        %>
        <aui:option value="<%= option.getEmployeeId() %>"><%= option.getFirstName() + " " + option.getLastName()%></aui:option>
        <% } %>
    </aui:select>

    <aui:select class="select" name="purchaseTypeId" label="Purchase Type">
        <%
            List<purchaseType> options = purchaseTypeLocalServiceUtil.getpurchaseTypes(-1, -1);
            for (purchaseType option : options) {
        %>
        <aui:option value="<%= option.getPurchaseTypeId() %>"><%= option.getName() %></aui:option>
        <% } %>
    </aui:select>

    <aui:input name="purchasedate" type="hidden"/>

    <aui:button-row>
        <aui:button name="submitButton" type="submit" value="Submit" />
    </aui:button-row>
</aui:form>
