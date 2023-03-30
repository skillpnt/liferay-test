<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ include file="../init.jsp" %>
<portlet:defineObjects/>

<liferay-portlet:actionURL name="updatePurchase" var="updatePurchaseActionURL"/>

<p><h1>Update purchase</h1></p>
<aui:form action="<%=updatePurchaseActionURL%>" name="purchaseForm" method="POST">
    <aui:input name="id" required="true" type="hidden" value='<%=ParamUtil.getLong(renderRequest, "id")%>'/>
    <aui:input name="electronicsId" type="text" required="true" placeholder="electronicsId"/>
    <aui:input name="employeeId" type="text" required="true" placeholder="employeeId"/>
    <aui:input name="purchasedate" type="hidden"/>
    <aui:input name="purchaseTypeId" type="text" required="true" placeholder="purchaseTypeId"/>
    <aui:button-row>
        <aui:button type="submit" value="Update" name="update"/>
    </aui:button-row>
</aui:form>
