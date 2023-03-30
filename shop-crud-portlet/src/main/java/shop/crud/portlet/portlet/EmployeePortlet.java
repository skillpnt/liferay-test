package shop.crud.portlet.portlet;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import shop.crud.portlet.constants.ShopPortletKeys;
import shop.model.Employee;
import shop.service.EmployeeLocalService;
import shop.service.EmployeeLocalServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.ProcessAction;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @author skllp
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=Employee CRUD",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/employee/view.jsp",
                "javax.portlet.name=" + ShopPortletKeys.EMPLOYEE,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class EmployeePortlet extends MVCPortlet {

    @Reference
    EmployeeLocalService employeeLocalService;

    @ProcessAction(name = "addEmployee")
    public void addEmployee(ActionRequest actionRequest, ActionResponse actionResponse) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String firstname = ParamUtil.getString(actionRequest, "employee_firstname");
        String lastname = ParamUtil.getString(actionRequest, "employee_lastname");
        String patronymic = ParamUtil.getString(actionRequest, "patronymic");
        Date birthdate  = ParamUtil.getDate(actionRequest, "birthdate", dateFormat);
        long positionId = ParamUtil.getLong(actionRequest, "positionId");
        boolean gender = ParamUtil.getBoolean(actionRequest, "gender");

        Employee employee = employeeLocalService.createEmployee(CounterLocalServiceUtil.increment());

        employee.setFirstName(firstname);
        employee.setLastName(lastname);
        employee.setPatronymic(patronymic);
        employee.setBirthdate(birthdate);
        employee.setPositionId(positionId);
        employee.setGender(gender);

        try {
            EmployeeLocalServiceUtil.addEmployee(employee);
        } catch (Exception e) {
            actionRequest.setAttribute("errorMessage", e.getMessage());
        }
    }

    @ProcessAction(name = "updateEmployee")
    public void updateEmployee(ActionRequest actionRequest, ActionResponse actionResponse) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        long id = ParamUtil.getLong(actionRequest, "id");
        String firstname = ParamUtil.getString(actionRequest, "employee_firstname");
        String lastname = ParamUtil.getString(actionRequest, "employee_lastname");
        String patronymic = ParamUtil.getString(actionRequest, "patronymic");
        Date birthdate = ParamUtil.getDate(actionRequest, "birthdate", dateFormat);
        long positionId = ParamUtil.getLong(actionRequest, "positionId");
        boolean gender = ParamUtil.getBoolean(actionRequest, "gender");

        Employee employee = null;
        try {
            employee = employeeLocalService.getEmployee(id);
        } catch (Exception e) {
            System.err.println(e.getCause() + e.getMessage());
        }

        if(Validator.isNotNull(employee)) {
            employee.setFirstName(firstname);
            employee.setLastName(lastname);
            employee.setPatronymic(patronymic);
            employee.setBirthdate(birthdate);
            employee.setPositionId(positionId);
            employee.setGender(gender);
            employeeLocalService.updateEmployee(employee);
        }
    }

    @ProcessAction(name = "deleteEmployee")
    public void deleteEmployee(ActionRequest actionRequest, ActionResponse actionResponse){
        long id = ParamUtil.getLong(actionRequest, "id");
        try {
            employeeLocalService.deleteEmployee(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}