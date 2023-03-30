<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="../init.jsp" %>
<portlet:defineObjects/>

<liferay-portlet:actionURL name="updateEmployee" var="updateEmployeeActionURL"/>

<%
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String gender = ParamUtil.getString(renderRequest, "positionId");
%>

<p><h1>Update employee</h1></p>
<aui:form action="<%=updateEmployeeActionURL%>" name="employeeForm" method="POST">
    <aui:input name="id" required="true" type="hidden" value='<%=ParamUtil.getLong(renderRequest, "id")%>'/>
    <aui:input name="employee_firstname" required="true" type="text" value='<%=ParamUtil.getString(renderRequest, "employee_firstname")%>'/>
    <aui:input name="employee_lastname" required="true" type="text" value='<%= ParamUtil.getString(renderRequest, "employee_lastname") %>'/>
    <aui:input name="patronymic" required="true" type="text" value='<%= ParamUtil.getString(renderRequest, "patronymic") %>'/>
    <aui:input name="birthdate" required="true" type="date" value='<%= ParamUtil.getString(renderRequest, "birthdate") %>'/>
    <aui:input name="positionId" required="true" type="number" value='<%= ParamUtil.getLong(renderRequest, "positionId") %>'/>
    <aui:select class="select" name="gender">
        <aui:option value="false" selected='<%=gender.equalsIgnoreCase("false")%>'>Male</aui:option>
        <aui:option value="true" selected='<%=gender.equalsIgnoreCase("true")%>'>Female</aui:option>
    </aui:select>
    <aui:button-row>
        <aui:button type="submit" value="Update" name="update"/>
    </aui:button-row>
</aui:form>
