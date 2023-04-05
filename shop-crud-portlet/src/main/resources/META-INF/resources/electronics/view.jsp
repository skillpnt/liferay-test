<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="shop.service.ElectronicsLocalServiceUtil" %>
<%@ page import="shop.model.ElectronicsType" %>
<%@ page import="shop.service.ElectronicsTypeLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.exception.PortalException" %>
<%@ include file="../init.jsp" %>
<liferay-ui:success key="electronicsDeleted" message="electronics-deleted" />
<liferay-ui:success key="electronicsAdded" message="electronics-added" />
<liferay-ui:success key="electronicsUpdated" message="electronics-updated" />
<liferay-ui:error key="electronicsArchivedAndInStock" message="electronics-archived-in-stock" />
<liferay-ui:error key="emptyField" message="field-empty" />
<liferay-ui:error key="nameTooLong" message="electronics-name-too-long" />
<liferay-ui:error key="descriptionTooLong" message="electronics-description-too-long" />
<liferay-ui:error key="electronicsDoesNotExist" message="electronics-does-not-exist" />
<portlet:defineObjects />

<liferay-portlet:renderURL varImpl="iteratorURL" >
    <liferay-portlet:param name="mvcPath" value="/electronics/view.jsp"/>
</liferay-portlet:renderURL>
<liferay-portlet:renderURL var="addElectronicsRenderURL" >
    <liferay-portlet:param name="mvcPath" value="/electronics/update-electronics.jsp"/>
</liferay-portlet:renderURL>

<div class="mb-5">
    <p>Electronics</p>
    <a href="<%= addElectronicsRenderURL %>" class="btn  btn-primary btn-default">
        Add Electronics
    </a>
</div>

<liferay-ui:search-container emptyResultsMessage="electronics-not-found"
                             iteratorURL="<%= iteratorURL %>"
                             total="<%= ElectronicsLocalServiceUtil.getElectronicsesCount()%>">
    <liferay-ui:search-container-results results="<%= ElectronicsLocalServiceUtil.getElectronicses(searchContainer.getStart(), searchContainer.getEnd()) %>" >
    </liferay-ui:search-container-results>

    <liferay-ui:search-container-row className="shop.model.Electronics" modelVar="electronics" keyProperty="electronicsId" >
        <portlet:renderURL var="updateElectronicsRenderURL">
            <portlet:param name="mvcPath" value="/electronics/update-electronics.jsp"/>
            <portlet:param name="id" value="${electronics.electronicsId}"/>
        </portlet:renderURL>
        <portlet:actionURL name="deleteElectronics" var="deleteElectronicsActionURL">
            <portlet:param name="id" value="${electronics.electronicsId}"/>
        </portlet:actionURL>

        <%
            String typeName = null;
            try {
                typeName = ElectronicsTypeLocalServiceUtil.getElectronicsType(electronics.getTypeId()).getName();
            } catch (PortalException e) {
                typeName = "Error";
            }
        %>
        <liferay-ui:search-container-column-text property="name" name="Electronics Name"/>
        <liferay-ui:search-container-column-text value="<%= typeName %>" name="Type"/>
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

<aui:script use="liferay-search-container">
</aui:script>