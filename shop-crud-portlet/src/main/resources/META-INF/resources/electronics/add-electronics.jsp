<%@ include file="../init.jsp" %>

<liferay-portlet:actionURL name="addElectronics" var="addElectronicsActionURL" />

<p><h1>Add new electronics</h1></p>
<aui:form action="<%=addElectronicsActionURL %>" name="electronicsForm" method="POST">
    <aui:input name="name" type="text" required="true" placeholder="input-name"/>
    <aui:input name="typeId" type="number" required="true" placeholder="input-type-id"/>
    <aui:input name="price" type="number" required="true" min="100" placeholder="input-price"/>
    <aui:input name="count" type="number" required="true" placeholder="input-count"/>
    <aui:input name="inStock" type="checkbox" placeholder="input-inStock"/>
    <aui:input name="archived" type="checkbox" placeholder="input-archived"/>
    <aui:input name="description" type="text" required="true" placeholder="input-descriprion"/>
    <aui:button-row>
        <aui:button name="submitButton" type="submit" value="Submit" />
    </aui:button-row>
</aui:form>
<!--
<p><h3>Add new employee</h3></p>
<aui:form action="${processActionURL}" name="employeeForm">
    <aui:input name="employee_firstname" type="text" required="true" placeholder="Firstname"/>
    <aui:input name="employee_lastname" type="text" required="true" placeholder="Lastname"/>
    <aui:input name="patronymic" type="text" required="true" placeholder="Patronymic"/>
    <aui:input name="birthdate" type="date" required="true" placeholder="Birthdate"/>
    <aui:input name="positionId" type="number" placeholder="Position id"/>
    <aui:select class="select" id="select-example" name="select-example">
        <aui:option>Male</aui:option>
        <aui:option>Female</aui:option>
    </aui:select>
    <aui:button-row>
        <aui:button name="submitButton" type="submit" value="Submit" />
    </aui:button-row>
</aui:form>

<p><h3>Add new purchase</h3></p>
<aui:form action="${processActionURL}" name="purchaseForm">
    <aui:input name="electronicsId" type="number" required="true" placeholder="electronicsId"/>
    <aui:input name="employeeId" type="number" required="true" placeholder="employeeId"/>
    <aui:input name="purchaseTypeId" type="number" required="true" placeholder="purchaseTypeId"/>
    <aui:button-row>
        <aui:button name="submitButton" type="submit" value="Submit" />
    </aui:button-row>
</aui:form>-->