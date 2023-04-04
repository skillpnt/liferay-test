<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="shop.service.PurchaseLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.exception.PortalException" %>
<%@ page import="shop.model.Purchase" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="shop.model.Electronics" %>
<%@ page import="java.util.List" %>
<%@ page import="shop.service.ElectronicsLocalServiceUtil" %>
<%@ page import="shop.service.EmployeeLocalServiceUtil" %>
<%@ page import="shop.model.Employee" %>
<%@ page import="shop.model.purchaseType" %>
<%@ page import="shop.service.purchaseTypeLocalServiceUtil" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="../init.jsp" %>
<portlet:defineObjects/>

<liferay-portlet:actionURL name="updatePurchase" var="updatePurchaseActionURL"/>
<liferay-portlet:actionURL name="addPurchase" var="addPurchaseActionURL" />

<%
    Purchase purchase = null;
    long tmpId = ParamUtil.getLong(renderRequest, "id");
    if (tmpId > 0) {
        try {
            purchase = PurchaseLocalServiceUtil.getPurchase(tmpId);
        } catch (PortalException e) {
            session.setAttribute("message", e.getMessage());
        }
    }
    List<Electronics> electronicsList = ElectronicsLocalServiceUtil.getElectronicses(-1, -1);
    List<Employee> employees = EmployeeLocalServiceUtil.getEmployees(-1, -1);
    List<purchaseType> purchaseTypes = purchaseTypeLocalServiceUtil.getpurchaseTypes(-1, -1);
%>

<% if (Validator.isNotNull(purchase)) {%>
<p><h1>Update purchase</h1></p>
<aui:form action="<%=updatePurchaseActionURL%>" name="purchaseForm" method="POST">
    <aui:input name="id" required="true" type="hidden" value='<%=purchase.getPurchaseId()%>'/>
    <aui:select class="select" name="electronicsId" label="Electronics ID">
        <%
            for (Electronics option : electronicsList) {
        %>
        <aui:option value="<%= option.getElectronicsId() %>" selected="<%=option.getElectronicsId() == purchase.getElectronicsId()%>">
            <%= option.getName() %>
        </aui:option>
        <% } %>
    </aui:select>

    <aui:select class="select" name="employeeId" label="Employee ID">
        <%
            for (Employee option : employees) {
        %>
        <aui:option value="<%= option.getEmployeeId() %>" selected="<%= option.getEmployeeId() == purchase.getEmployeeId()%>">
            <%= option.getFirstName() + " " + option.getLastName()%>
        </aui:option>
        <% } %>
    </aui:select>

    <aui:select class="select" name="purchaseTypeId" label="Purchase Type">
        <%
            for (purchaseType option : purchaseTypes) {
        %>
        <aui:option value="<%= option.getPurchaseTypeId() %>" selected="<%= option.getPurchaseTypeId() == purchase.getPurchaseTypeId()%>">
            <%= option.getName() %>
        </aui:option>
        <% } %>
    </aui:select>

    <%
        Date purchaseDate = purchase.getPurchaseDate();
        String stringDate = new SimpleDateFormat("yyyy-MM-dd").format(purchaseDate);
    %>
    <aui:input name="purchaseDate" type="date" value="<%=stringDate%>"/>
    <aui:button-row>
        <aui:button type="submit" value="Update" name="update"/>
    </aui:button-row>
</aui:form>
<% } else { %>
<p><h1>Add new purchase</h1></p>

${message}

<aui:form action="<%= addPurchaseActionURL %>" name="purchaseForm" method="POST">
    <aui:select class="select" name="electronicsId" label="Electronics ID">
        <%
            for (Electronics option : electronicsList) {
        %>
        <aui:option value="<%= option.getElectronicsId() %>"><%= option.getName() %></aui:option>
        <% } %>
    </aui:select>

    <aui:select class="select" name="employeeId" label="Employee ID">
        <%
            for (Employee option : employees) {
        %>
        <aui:option value="<%= option.getEmployeeId() %>"><%= option.getFirstName() + " " + option.getLastName()%></aui:option>
        <% } %>
    </aui:select>

    <aui:select class="select" name="purchaseTypeId" label="Purchase Type">
        <%
            for (purchaseType option : purchaseTypes) {
        %>
        <aui:option value="<%= option.getPurchaseTypeId() %>"><%= option.getName() %></aui:option>
        <% } %>
    </aui:select>

    <aui:input name="purchaseDate" type="date" label="Purchase Date"/>

    <aui:button-row>
        <aui:button name="submitButton" type="submit" value="Submit" />
    </aui:button-row>
</aui:form>
<% } %>
