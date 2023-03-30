<%@ include file="../init.jsp" %>

<liferay-portlet:actionURL name="addPurchase" var="addPurchaseActionURL" />

<p><h1>Add new purchase</h1></p>
<aui:form action="<%= addPurchaseActionURL %>" name="purchaseForm" method="POST">
    <aui:input name="electronicsId" type="text" required="true" placeholder="electronicsId"/>
    <aui:input name="employeeId" type="text" required="true" placeholder="employeeId"/>
    <aui:input name="purchasedate" type="hidden"/>
    <aui:input name="purchaseTypeId" type="text" required="true" placeholder="purchaseTypeId"/>
    <aui:button-row>
        <aui:button name="submitButton" type="submit" value="Submit" />
    </aui:button-row>
</aui:form>
