<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="org.apache.commons.beanutils.BeanComparator"%>
<%@ page import="shop.service.PurchaseLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="shop.model.Purchase" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.liferay.portal.kernel.util.ListUtil" %>
<%@ page import="shop.crud.portlet.portlet.PurchaseDateComparator" %>
<%@ page import="shop.model.ElectronicsEmployee" %>
<%@ page import="shop.service.ElectronicsLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.exception.PortalException" %>
<%@ page import="shop.service.EmployeeLocalServiceUtil" %>
<%@ page import="shop.model.Employee" %>
<%@ include file="../init.jsp" %>
<portlet:defineObjects />
<theme:defineObjects />
<liferay-ui:success key="purchaseDeleted" message="purchase-deleted" />
<liferay-ui:success key="purchaseAdded" message="purchase-added" />
<liferay-ui:success key="purchaseUpdated" message="purchase-updated" />
<liferay-ui:error key="notEnoughElectronics" message="electronics-not-enough" />
<liferay-ui:error key="emptyField" message="field-empty" />
<liferay-ui:error key="purchaseWrongDate" message="purchase-wrong-date" />
<liferay-ui:error key="employeeNoElectronicsType" message="employee-no-electronics-type" />

<liferay-portlet:renderURL varImpl="iteratorURL" >
    <liferay-portlet:param name="mvcPath" value="/purchase/view.jsp"/>
</liferay-portlet:renderURL>
<liferay-portlet:renderURL var="addPurchaseRenderURL">
    <liferay-portlet:param name="mvcPath" value="/purchase/update-purchase.jsp"/>
</liferay-portlet:renderURL>

<div class="mb-5">
    <p>Purchases</p>
    <a href="<%= addPurchaseRenderURL %>" class="btn  btn-primary btn-default">
        Add Purchase
    </a>
</div>

<liferay-ui:search-container emptyResultsMessage="purchases-not-found"
                             iteratorURL="<%= iteratorURL %>"
                             total="<%= PurchaseLocalServiceUtil.getPurchasesCount()%>">
    <liferay-ui:search-container-results>
    <%
        String sortByType = ParamUtil.getString(request, "orderByType");
        List<Purchase> purchaseList = PurchaseLocalServiceUtil.getPurchases(-1, -1);
        List<Purchase> sortableList = new ArrayList<>(purchaseList);

        if (sortByType.equalsIgnoreCase("asc")) {
            Collections.sort(sortableList, new PurchaseDateComparator());
        } else {
            Collections.sort(sortableList, Collections.reverseOrder(new PurchaseDateComparator()));
        }

        results.clear();
        results.addAll(ListUtil.subList(sortableList, searchContainer.getStart(), searchContainer.getEnd()));
    %>
    </liferay-ui:search-container-results>

    <liferay-ui:search-container-row className="shop.model.Purchase" modelVar="purchase" keyProperty="purchaseId" >
        <portlet:renderURL var="updatePurchaseRenderURL">
            <portlet:param name="mvcPath" value="/purchase/update-purchase.jsp"/>
            <portlet:param name="id" value="${purchase.purchaseId}"/>
        </portlet:renderURL>
        <portlet:actionURL name="deletePurchase" var="deletePurchaseActionURL">
            <portlet:param name="id" value="${purchase.purchaseId}"/>
        </portlet:actionURL>

        <%
            String electronicsName = "";
            String employeeName = "";
            try {
                electronicsName = ElectronicsLocalServiceUtil.getElectronics(purchase.getElectronicsId()).getName();
            } catch (PortalException e) {
                electronicsName = "Error";
            }

            try {
                Employee employee = EmployeeLocalServiceUtil.getEmployee(purchase.getEmployeeId());
                employeeName = employee.getFirstName() + " " + employee.getLastName();
            } catch (PortalException e) {
                electronicsName = "Error";
            }
        %>
        <liferay-ui:search-container-column-text property="electronicsId" name="Electronics Id"/>
        <liferay-ui:search-container-column-text value="<%= electronicsName %>" name="Electronics Name"/>
        <liferay-ui:search-container-column-text property="employeeId" name="Employee Id"/>
        <liferay-ui:search-container-column-text value="<%= employeeName %>" name="Employee Name"/>
        <liferay-ui:search-container-column-date property="purchaseDate" name="Purchase Date" orderable="<%= true %>" orderableProperty="purchaseDate" />
        <liferay-ui:search-container-column-text property="purchaseTypeId" name="Purchase Type Id"/>

        <liferay-ui:search-container-column-text>
            <a href="<%= updatePurchaseRenderURL %>"
               class="btn  btn-primary btn-default btn-sm px-2 py-1" > Update </a>
            <a href="<%= deletePurchaseActionURL %>"
               class="btn  btn-primary btn-default btn-sm px-2 py-1"
               onclick="return confirm('Are you sure you want to delete this item?');"> Delete </a>
        </liferay-ui:search-container-column-text>

    </liferay-ui:search-container-row>
    <liferay-ui:search-iterator />
</liferay-ui:search-container>

<aui:script use="liferay-search-container">
</aui:script>