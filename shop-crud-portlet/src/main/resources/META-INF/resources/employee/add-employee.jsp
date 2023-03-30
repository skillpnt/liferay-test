<%@ include file="../init.jsp" %>

<liferay-portlet:actionURL name="addEmployee" var="addEmployeeActionURL" />

<p><h1>Add new employee</h1></p>
<aui:form action="<%= addEmployeeActionURL %>" name="employeeForm" method="POST">
    <aui:input name="employee_firstname" type="text" required="true" placeholder="Firstname"/>
    <aui:input name="employee_lastname" type="text" required="true" placeholder="Lastname"/>
    <aui:input name="patronymic" type="text" required="true" placeholder="Patronymic"/>
    <aui:input name="birthdate" type="date" required="true" placeholder="Birthdate"/>
    <aui:input name="positionId" type="number" required="true" placeholder="Position id"/>
    <aui:select class="select" name="gender">
        <aui:option value="false">Male</aui:option>
        <aui:option value="true">Female</aui:option>
    </aui:select>
    <aui:button-row>
        <aui:button name="submitButton" type="submit" value="Submit" />
    </aui:button-row>
</aui:form>
