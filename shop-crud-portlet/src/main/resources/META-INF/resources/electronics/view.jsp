<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="shop.service.ElectronicsLocalServiceUtil" %>
<%@ include file="../init.jsp" %>
<portlet:defineObjects />

<%
    PortletURL electronicsItrUrl = renderResponse.createRenderURL();
    electronicsItrUrl.setParameter("mvcPath", "/electronics/view.jsp");
%>

<liferay-portlet:renderURL var="addElectronicsRenderURL" >
    <liferay-portlet:param name="mvcPath" value="/electronics/add-electronics.jsp"/>
</liferay-portlet:renderURL>

<div class="mb-5">
    <p>Electronics</p>
    <a href="<%= addElectronicsRenderURL %>" class="btn  btn-primary btn-default">
        Add Electronics
    </a>
</div>

<liferay-ui:search-container emptyResultsMessage="electronics-not-found" iteratorURL="<%= electronicsItrUrl %>" >
    <liferay-ui:search-container-results results="<%= ElectronicsLocalServiceUtil.getElectronicses(searchContainer.getStart(), searchContainer.getEnd()) %>" >
    </liferay-ui:search-container-results>

    <liferay-ui:search-container-row className="shop.model.Electronics" modelVar="electronics" keyProperty="electronicsId" >
        <portlet:renderURL var="rowURL">
            <portlet:param name="electronicsId" value="${electronics.electronicsId}" />
        </portlet:renderURL>
        <portlet:renderURL var="updateElectronicsRenderURL">
            <portlet:param name="mvcPath" value="/electronics/update-electronics.jsp"/>
            <portlet:param name="id" value="${electronics.electronicsId}"/>
            <portlet:param name="name" value="${electronics.name}"/>
            <portlet:param name="typeId" value="${electronics.typeId}"/>
            <portlet:param name="price" value="${electronics.price}"/>
            <portlet:param name="count" value="${electronics.count}"/>
            <portlet:param name="inStock" value="${electronics.inStock}"/>
            <portlet:param name="archived" value="${electronics.archived}"/>
            <portlet:param name="description" value="${electronics.description}"/>
        </portlet:renderURL>
        <portlet:actionURL name="deleteElectronics" var="deleteElectronicsActionURL">
            <portlet:param name="id" value="${electronics.electronicsId}"/>
        </portlet:actionURL>

        <liferay-ui:search-container-column-text property="name" name="Electronics Name" href="${rowURL}"/>
        <liferay-ui:search-container-column-text property="typeId" name="Type Id"/>
        <liferay-ui:search-container-column-text property="price" name="Price"/>
        <liferay-ui:search-container-column-text property="count" name="Count"/>
        <liferay-ui:search-container-column-text property="inStock" name="In Stock"/>
        <liferay-ui:search-container-column-text property="archived" name="Archived"/>
        <liferay-ui:search-container-column-text property="description" name="Description"/>

        <liferay-ui:search-container-column-text>
            <a href="<%= updateElectronicsRenderURL %>"
               class="btn  btn-primary btn-default btn-sm px-2 py-1" > Update </a>
            <a href="<%= deleteElectronicsActionURL %>"
               class="btn  btn-primary btn-default btn-sm px-2 py-1"
               onclick="return confirm('Are you sure you want to delete this item?');"> Delete </a>
        </liferay-ui:search-container-column-text>

    </liferay-ui:search-container-row>
    <liferay-ui:search-iterator />
</liferay-ui:search-container>