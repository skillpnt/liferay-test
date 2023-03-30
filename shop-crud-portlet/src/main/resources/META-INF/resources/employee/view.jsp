<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="shop.service.EmployeeLocalServiceUtil" %>
<%@ include file="../init.jsp" %>
<portlet:defineObjects />

<%
    PortletURL employeeItrUrl = renderResponse.createRenderURL();
    employeeItrUrl.setParameter("mvcPath", "/employee/view.jsp");
%>

<liferay-portlet:renderURL var="addEmployeeRenderURL">
    <liferay-portlet:param name="mvcPath" value="/employee/add-employee.jsp"/>
</liferay-portlet:renderURL>

<div class="mb-5">
    <p>Employees</p>
    <a href="<%= addEmployeeRenderURL %>" class="btn  btn-primary btn-default">
        Add Employee
    </a>
</div>

<liferay-ui:search-container emptyResultsMessage="employees-not-found" iteratorURL="<%= employeeItrUrl %>" >
    <liferay-ui:search-container-results results="<%= EmployeeLocalServiceUtil.getEmployees(searchContainer.getStart(), searchContainer.getEnd()) %>" >
    </liferay-ui:search-container-results>

    <liferay-ui:search-container-row className="shop.model.Employee" modelVar="employee" keyProperty="employeeId" >
        <portlet:renderURL var="rowURL">
            <portlet:param name="employeeId" value="${employee.employeeId}" />
        </portlet:renderURL>
        <portlet:renderURL var="updateEmployeeRenderURL">
            <portlet:param name="mvcPath" value="/employee/update-employee.jsp"/>
            <portlet:param name="id" value="${employee.employeeId}"/>
            <portlet:param name="employee_lastname" value="${employee.lastName}"/>
            <portlet:param name="employee_firstname" value="${employee.firstName}"/>
            <portlet:param name="patronymic" value="${employee.patronymic}"/>
            <portlet:param name="birthdate" value="${employee.birthdate}"/>
            <portlet:param name="positionId" value="${employee.positionId}"/>
            <portlet:param name="gender" value="${employee.gender}"/>
        </portlet:renderURL>
        <portlet:actionURL name="deleteEmployee" var="deleteEmployeeActionURL">
            <portlet:param name="id" value="${employee.employeeId}"/>
        </portlet:actionURL>

        <liferay-ui:search-container-column-text property="firstName" name="Firstname"/>
        <liferay-ui:search-container-column-text property="lastName" name="Lastname"/>
        <liferay-ui:search-container-column-text property="patronymic" name="Patronymic"/>
        <liferay-ui:search-container-column-text property="birthdate" name="Birthdate" />
        <liferay-ui:search-container-column-text property="positionId" name="Position id"/>
        <liferay-ui:search-container-column-text property="gender" name="Gender"/>

        <liferay-ui:search-container-column-text>
            <a href="<%= updateEmployeeRenderURL %>"
               class="btn  btn-primary btn-default btn-sm px-2 py-1" > Update </a>
            <a href="<%= deleteEmployeeActionURL %>"
               class="btn  btn-primary btn-default btn-sm px-2 py-1"
               onclick="return confirm('Are you sure you want to delete this item?');"> Delete </a>
        </liferay-ui:search-container-column-text>

    </liferay-ui:search-container-row>
    <liferay-ui:search-iterator />
</liferay-ui:search-container>