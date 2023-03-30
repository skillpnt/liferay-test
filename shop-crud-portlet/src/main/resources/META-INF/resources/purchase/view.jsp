<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="shop.service.PurchaseLocalServiceUtil" %>
<%@ include file="../init.jsp" %>
<portlet:defineObjects />

<%
    PortletURL purchaseItrUrl = renderResponse.createRenderURL();
    purchaseItrUrl.setParameter("mvcPath", "/purchase/view.jsp");
%>

<liferay-portlet:renderURL var="addPurchaseRenderURL">
    <liferay-portlet:param name="mvcPath" value="/purchase/add-purchase.jsp"/>
</liferay-portlet:renderURL>

<div class="mb-5">
    <p>Purchases</p>
    <a href="<%= addPurchaseRenderURL %>" class="btn  btn-primary btn-default">
        Add Purchase
    </a>
</div>

<liferay-ui:search-container emptyResultsMessage="purchases-not-found" iteratorURL="<%= purchaseItrUrl %>" >
    <liferay-ui:search-container-results results="<%= PurchaseLocalServiceUtil.getPurchases(searchContainer.getStart(), searchContainer.getEnd()) %>" >
    </liferay-ui:search-container-results>

    <liferay-ui:search-container-row className="shop.model.Purchase" modelVar="purchase" keyProperty="purchaseId" >
        <portlet:renderURL var="rowURL">
            <portlet:param name="purchaseId" value="${purchase.purchaseId}" />
        </portlet:renderURL>
        <portlet:renderURL var="updatePurchaseRenderURL">
            <portlet:param name="mvcPath" value="/purchase/update-purchase.jsp"/>
            <portlet:param name="id" value="${purchase.purchaseId}"/>
            <portlet:param name="electronicsId" value="${purchase.electronicsId}"/>
            <portlet:param name="employeeId" value="${purchase.employeeId}"/>
            <portlet:param name="purchaseDate" value="${purchase.purchaseDate}"/>
            <portlet:param name="purchaseTypeId" value="${purchase.purchaseTypeId}"/>
        </portlet:renderURL>
        <portlet:actionURL name="deletePurchase" var="deletePurchaseActionURL">
            <portlet:param name="id" value="${purchase.purchaseId}"/>
        </portlet:actionURL>

        <liferay-ui:search-container-column-text property="electronicsId" name="Electronics Id"/>
        <liferay-ui:search-container-column-text property="employeeId" name="Employee Id"/>
        <liferay-ui:search-container-column-text property="purchaseDate" name="Purchase Date"/>
        <liferay-ui:search-container-column-text property="purchaseTypeId" name="Purchase Type Id" />

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