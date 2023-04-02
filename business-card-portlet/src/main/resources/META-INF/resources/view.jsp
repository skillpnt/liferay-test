<%@ page import="shop.model.Employee" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="shop.model.PositionType" %>
<%@ page import="shop.service.PositionTypeLocalServiceUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="shop.service.EmployeeLocalServiceUtil" %>
<%@ include file="init.jsp" %>
<portlet:defineObjects/>
<%
	Map<Long, Employee> bestEmployees = new HashMap<>((Map) renderRequest.getAttribute("bestEmployees"));
	Map<Long, Long> employeesEarnings = new HashMap<>((Map) renderRequest.getAttribute("employeesEarnings"));
	long shopProfit = (Long) renderRequest.getAttribute("shopProfit");
	int soldTvsCount = (Integer) renderRequest.getAttribute("soldTvsCount");
	Map<Long, Integer> sortedTvSmartphoneMap = new HashMap<>((Map) renderRequest.getAttribute("sortedTvSmartphoneMap"));
%>

<p> Best employees
	<ul id="bestEmployeesList" class="list-group">
		<% for (Map.Entry<Long, Employee> employee : bestEmployees.entrySet()) { %>
			<li class="list-group-item"><%= "Best '" + PositionTypeLocalServiceUtil.getPositionType(employee.getKey()).getName() + "' is "
					+ employee.getValue().getFirstName() + " "
					+ employee.getValue().getLastName() + "! They earned "
					+ employeesEarnings.get(employee.getValue().getEmployeeId())/100 + " Roubles!"%></li>
		<% } %>
	</ul>
</p>

<p>
	<%= "Shop profit: " + shopProfit/100 + " Roubles" %>
</p>

<p>
	<%= "TVs sold this month: " + soldTvsCount %>
</p>

<p> Employees that sell TVs and Smartphones:
<ul id="tvSmartphonesEmployees" class="list-group">
	<% for (Map.Entry<Long, Integer> employee : sortedTvSmartphoneMap.entrySet()) { %>
	<li class="list-group-item"><%=
	EmployeeLocalServiceUtil.getEmployee(employee.getKey()).getFirstName() + " "
			+ EmployeeLocalServiceUtil.getEmployee(employee.getKey()).getLastName() + " sold "
			+ employee.getValue() + " Products!"%></li>
	<% } %>
</ul>
</p>