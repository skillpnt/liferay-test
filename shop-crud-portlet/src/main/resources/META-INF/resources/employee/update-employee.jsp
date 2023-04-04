<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.ZoneId" %>
<%@ page import="com.liferay.portal.kernel.exception.PortalException" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="shop.model.*" %>
<%@ page import="shop.service.*" %>
<%@ include file="../init.jsp" %>
<portlet:defineObjects/>

<liferay-portlet:actionURL name="updateEmployee" var="updateEmployeeActionURL"/>
<liferay-portlet:actionURL name="addEmployee" var="addEmployeeActionURL" />

<%
    List<ElectronicsType> options = ElectronicsTypeLocalServiceUtil.getElectronicsTypes(-1, -1);

    Employee employee = null;
    long tmpId = ParamUtil.getLong(renderRequest, "id");
    if (tmpId > 0) {
        try {
            employee = EmployeeLocalServiceUtil.getEmployee(tmpId);
        } catch (PortalException e) {
            session.setAttribute("message", e.getMessage());
        }
    }

    List<PositionType> positions = PositionTypeLocalServiceUtil.getPositionTypes(-1, -1);
%>

<% if (Validator.isNotNull(employee)) { %>
<p><h1>Update employee</h1></p>

<%
    List<Long> employeeTypes = ElectronicsEmployeeLocalServiceUtil.getElectronicsEmployees(-1, -1).stream()
            .filter(type -> type.getEmployeeId() == tmpId)
            .map(ElectronicsEmployeeModel::getElectronicsTypeId)
            .collect(Collectors.toList());


    boolean gender = employee.getGender();
    Date birthdate = employee.getBirthdate();
    String stringDate = new SimpleDateFormat("yyyy-MM-dd").format(birthdate);
%>
<aui:form action="<%=updateEmployeeActionURL%>" name="employeeForm" method="POST">
    <aui:input name="id" required="true" type="hidden" value='<%=employee.getEmployeeId()%>'/>
    <aui:input name="employee_firstname" required="true" type="text" value='<%=employee.getFirstName()%>'/>
    <aui:input name="employee_lastname" required="true" type="text" value='<%=employee.getLastName()%>'/>
    <aui:input name="patronymic" required="true" type="text" value='<%=employee.getPatronymic()%>'/>
    <aui:input name="birthdate" required="true" type="date" value='<%=stringDate%>'/>
    <aui:select class="select" name="positionId" label="Position id">
        <%
            for (PositionType positionType : positions) {
        %>
        <aui:option value="<%= positionType.getPositionId() %>" selected="<%=positionType.getPositionId() == employee.getPositionId()%>"><%= positionType.getName() %></aui:option>
        <% } %>
    </aui:select>
    <aui:select class="select" name="gender">
        <aui:option value="false" selected='<%=!gender%>'>Male</aui:option>
        <aui:option value="true" selected='<%=gender%>'>Female</aui:option>
    </aui:select>

    <label>Electronics types</label>
    <%
        for (ElectronicsType option : options) {
    %>
    <aui:input type="checkbox" checked="<%= employeeTypes.contains(option.getElectronicsTypeId())%>" name="<%= option.getName() %>" value="<%= option.getElectronicsTypeId() %>" />
    <% } %>

    <aui:button-row>
        <aui:button type="submit" value="Update" name="update"/>
    </aui:button-row>
</aui:form>
<% } else { %>
<p><h1>Add new employee</h1></p>

${message}

<aui:form action="<%= addEmployeeActionURL %>" name="employeeForm" method="POST">
    <aui:input name="employee_firstname" type="text" required="true" maxlength="100" placeholder="Firstname"/>
    <aui:input name="employee_lastname" type="text" required="true" maxlength="100" placeholder="Lastname"/>
    <aui:input name="patronymic" type="text" required="true" maxlength="100" placeholder="Patronymic"/>
    <aui:input name="birthdate" type="date" required="true" placeholder="Birthdate"/>
    <aui:select class="select" name="positionId" label="Position id">
        <%
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
<% } %>