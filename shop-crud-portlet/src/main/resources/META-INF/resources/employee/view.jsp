<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="shop.service.EmployeeLocalServiceUtil" %>
<%@ page import="shop.service.PositionTypeLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.exception.PortalException" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="../init.jsp" %>
<portlet:defineObjects />
<liferay-ui:success key="employeeDeleted" message="employee-deleted" />
<liferay-ui:success key="employeeAdded" message="employee-added" />
<liferay-ui:success key="employeeUpdated" message="employee-updated" />
<liferay-ui:error key="emptyField" message="field-empty" />
<liferay-ui:error key="employeeWrongDate" message="employee-wrong-date" />
<liferay-ui:error key="firstnameTooLong" message="employee-firstname-too-long" />
<liferay-ui:error key="lastnameTooLong" message="employee-lastname-too-long" />
<liferay-ui:error key="patronymicTooLong" message="employee-patronymic-too-long" />

<%
    PortletURL employeeItrUrl = renderResponse.createRenderURL();
    employeeItrUrl.setParameter("mvcPath", "/employee/view.jsp");
%>

<liferay-portlet:renderURL var="addEmployeeRenderURL">
    <liferay-portlet:param name="mvcPath" value="/employee/update-employee.jsp"/>
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
        <%
            String positionTypeName = null;
            try {
                positionTypeName = PositionTypeLocalServiceUtil.getPositionType(employee.getPositionId()).getName();
            } catch (PortalException e) {
                positionTypeName = "Error";
            }

            String tmpGender = "";
            if (employee.getGender()) tmpGender = "Female";
            else tmpGender = "Male";

            Date tmpBirthdate = employee.getBirthdate();
            String displayDate = new SimpleDateFormat("dd-MM-yyyy").format(tmpBirthdate);
        %>

        <portlet:renderURL var="updateEmployeeRenderURL">
            <portlet:param name="mvcPath" value="/employee/update-employee.jsp"/>
            <portlet:param name="id" value="${employee.employeeId}"/>
        </portlet:renderURL>
        <portlet:actionURL name="deleteEmployee" var="deleteEmployeeActionURL">
            <portlet:param name="id" value="${employee.employeeId}"/>
        </portlet:actionURL>

        <liferay-ui:search-container-column-text property="firstName" name="Firstname"/>
        <liferay-ui:search-container-column-text property="lastName" name="Lastname"/>
        <liferay-ui:search-container-column-text property="patronymic" name="Patronymic"/>
        <liferay-ui:search-container-column-text value="<%= displayDate %>" name="Birthdate" />
        <liferay-ui:search-container-column-text value="<%= positionTypeName %>" name="Position"/>
        <liferay-ui:search-container-column-text value="<%= tmpGender %>" name="Gender"/>

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