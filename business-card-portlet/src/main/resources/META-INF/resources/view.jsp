<%@ page import="shop.model.Employee" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="shop.model.PositionType" %>
<%@ page import="shop.service.PositionTypeLocalServiceUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="shop.service.EmployeeLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.kernel.exception.PortalException" %>
<%@ include file="init.jsp" %>
<portlet:defineObjects/>
<%
	List<Employee> bestEmployeesByEarnings = (List<Employee>)renderRequest.getAttribute("bestEmployeesByEarnings");
	List<Employee> bestEmployeesByPurchaseCount = (List<Employee>)renderRequest.getAttribute("bestEmployeesByPurchaseCount");
	List<Employee> employeesThatSellTvAndSmartphones = (List<Employee>)renderRequest.getAttribute("employeesThatSellTvAndSmartphones");
	long profit = (Long)renderRequest.getAttribute("shopLastMonthProfit");
	int tvsSold = (Integer)renderRequest.getAttribute("lastMonthSoldTvCount");
%>

<% if (Validator.isNotNull(bestEmployeesByEarnings)) { %>
<p> Best employees
	<ul class="list-group">
		<% for (Employee employee : bestEmployeesByEarnings) {
			String posName = "";
			try {
				posName = PositionTypeLocalServiceUtil.getPositionType(employee.getPositionId()).getName();
			} catch (PortalException e) {
				posName = "Error!";
			}
		%>
			<li class="list-group-item"><%= "Best earning '"
					+ posName
					+ "' is "
					+ employee.getFirstName()
					+ " "
					+ employee.getLastName()%></li>
		<% } %>
	</ul>
</p>
<% } %>

<% if (Validator.isNotNull(bestEmployeesByPurchaseCount)) { %>
<p> Best employees by purchase count
<ul class="list-group">
	<% for (Employee employee : bestEmployeesByPurchaseCount) {
		String posName = "";
		try {
			posName = PositionTypeLocalServiceUtil.getPositionType(employee.getPositionId()).getName();
		} catch (PortalException e) {
			posName = "Error!";
		}
	%>
	<li class="list-group-item"><%= "Best selling '"
			+ posName
			+ "' is "
			+ employee.getFirstName()
			+ " "
			+ employee.getLastName()%></li>
	<% } %>
</ul>
</p>
<% } %>

<p>
	Shop profit from last month: <%=profit/100%> Roubles.
</p>
<p>
	TVs sold last month: <%= tvsSold %>
</p>

<% if(Validator.isNotNull(employeesThatSellTvAndSmartphones)) { %>
<p> Employees that sell TV and Smartphones:
<ul class="list-group">
	<% for (Employee employee : employeesThatSellTvAndSmartphones) {
		String posName = "";
	%>
	<li class="list-group-item"><%=
			employee.getFirstName()
			+ " "
			+ employee.getLastName()
			+ " "
			+ employee.getPatronymic()
	%></li>
	<% } %>
</ul>
</p>
<% } %>