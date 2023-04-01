<%@ page import="shop.model.ElectronicsType" %>
<%@ page import="shop.service.ElectronicsTypeLocalServiceUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="shop.model.PositionType" %>
<%@ page import="shop.service.PositionTypeLocalServiceUtil" %>
<%@ include file="../init.jsp" %>

<liferay-portlet:actionURL name="addEmployee" var="addEmployeeActionURL" />

<%
    List<ElectronicsType> options = ElectronicsTypeLocalServiceUtil.getElectronicsTypes(-1, -1);
%>

<p><h1>Add new employee</h1></p>
<aui:form action="<%= addEmployeeActionURL %>" name="employeeForm" method="POST">
    <aui:input name="employee_firstname" type="text" required="true" maxlength="100" placeholder="Firstname"/>
    <aui:input name="employee_lastname" type="text" required="true" maxlength="100" placeholder="Lastname"/>
    <aui:input name="patronymic" type="text" required="true" maxlength="100" placeholder="Patronymic"/>
    <aui:input name="birthdate" type="date" required="true" placeholder="Birthdate"/>
    <aui:select class="select" name="positionId" label="Position id">
        <%
            List<PositionType> positions = PositionTypeLocalServiceUtil.getPositionTypes(-1, -1);
            for (PositionType positionType : positions) {
        %>
        <aui:option value="<%= positionType.getPositionId() %>"><%= positionType.getName() %></aui:option>
        <% } %>
    </aui:select>
    <aui:select class="select" name="gender">
        <aui:option value="false">Male</aui:option>
        <aui:option value="true">Female</aui:option>
    </aui:select>

    <label>Electronics types</label>
    <%
        for (ElectronicsType option : options) {
    %>
    <aui:input type="checkbox" name="<%= option.getName() %>" value="<%= option.getElectronicsTypeId() %>" label="<%= option.getName() %>" />
    <% } %>

    <!--
    <aui:input name="tvCheckbox" type="checkbox" label="TV" value="1"/>
    <aui:input name="smartphonesCheckbox" type="checkbox" label="Smartphones" value="2"/>
    <aui:input name="laptopsCheckbox" type="checkbox" label="Laptops" value="3"/>
    <aui:input name="fridgesCheckbox" type="checkbox" label="Fridges" value="4"/>
    <aui:input name="vacuumCleanersCheckbox" type="checkbox" label="Vacuum cleaners" value="5"/>
    <aui:input name="printersCheckbox" type="checkbox" label="Printers" value="6"/>
    -->

    <aui:button-row>
        <aui:button name="submitButton" type="submit" value="Submit" />
    </aui:button-row>
</aui:form>
