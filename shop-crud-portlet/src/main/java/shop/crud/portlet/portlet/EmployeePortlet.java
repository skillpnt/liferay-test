package shop.crud.portlet.portlet;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import shop.crud.portlet.constants.ShopPortletKeys;
import shop.model.ElectronicsEmployee;
import shop.model.ElectronicsType;
import shop.model.Employee;
import shop.service.*;
import shop.service.persistence.ElectronicsEmployeePK;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.ProcessAction;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


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

    @Reference
    ElectronicsEmployeeLocalService electronicsEmployeeLocalService;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @ProcessAction(name = "addEmployee")
    public void addEmployee(ActionRequest actionRequest, ActionResponse actionResponse) {
        if (!validateFields(actionRequest)) {
            return;
        }

        String firstname = ParamUtil.getString(actionRequest, "employee_firstname");
        String lastname = ParamUtil.getString(actionRequest, "employee_lastname");
        String patronymic = ParamUtil.getString(actionRequest, "patronymic");
        Date birthdate  = ParamUtil.getDate(actionRequest, "birthdate", dateFormat);
        long positionId = ParamUtil.getLong(actionRequest, "positionId");
        boolean gender = ParamUtil.getBoolean(actionRequest, "gender");

        List<ElectronicsType> electronicsTypeList = ElectronicsTypeLocalServiceUtil.getElectronicsTypes(-1, -1);
        List<Integer> typesList = electronicsTypeList.stream()
                .map(type -> ParamUtil.getInteger(actionRequest, type.getName()))
                .filter(curType -> curType > 0)
                .collect(Collectors.toList());

        Employee employee = employeeLocalService.createEmployee(CounterLocalServiceUtil.increment());

        List<ElectronicsEmployee> electronicsEmployeeList = typesList.stream()
                .map(type -> electronicsEmployeeLocalService.createElectronicsEmployee(new ElectronicsEmployeePK(employee.getEmployeeId(), type)))
                .collect(Collectors.toList());

        employee.setFirstName(firstname);
        employee.setLastName(lastname);
        employee.setPatronymic(patronymic);
        employee.setBirthdate(birthdate);
        employee.setPositionId(positionId);
        employee.setGender(gender);

        try {
            EmployeeLocalServiceUtil.addEmployee(employee);
            electronicsEmployeeList.forEach(ElectronicsEmployeeLocalServiceUtil::addElectronicsEmployee);
            SessionMessages.add(actionRequest, "employeeAdded");
        } catch (Exception e) {
            SessionErrors.add(actionRequest, e.getClass().getName());
        }
    }

    @ProcessAction(name = "updateEmployee")
    public void updateEmployee(ActionRequest actionRequest, ActionResponse actionResponse) {
        if (!validateFields(actionRequest)) {
            return;
        }

        long id = ParamUtil.getLong(actionRequest, "id");
        String firstname = ParamUtil.getString(actionRequest, "employee_firstname");
        String lastname = ParamUtil.getString(actionRequest, "employee_lastname");
        String patronymic = ParamUtil.getString(actionRequest, "patronymic");
        Date birthdate = ParamUtil.getDate(actionRequest, "birthdate", dateFormat);
        long positionId = ParamUtil.getLong(actionRequest, "positionId");
        boolean gender = ParamUtil.getBoolean(actionRequest, "gender");

        List<ElectronicsType> electronicsTypeList = ElectronicsTypeLocalServiceUtil.getElectronicsTypes(-1, -1);
        List<Integer> typesList = electronicsTypeList.stream()
                .map(type -> ParamUtil.getInteger(actionRequest, type.getName()))
                .filter(curType -> curType > 0)
                .collect(Collectors.toList());

        Employee employee = null;
        try {
            employee = employeeLocalService.getEmployee(id);
        } catch (Exception e) {
            SessionErrors.add(actionRequest, e.getClass().getName());
        }

        List<ElectronicsEmployee> electronicsEmployeeList = typesList.stream()
                .map(type -> electronicsEmployeeLocalService.createElectronicsEmployee(new ElectronicsEmployeePK(id, type)))
                .collect(Collectors.toList());

        if(Validator.isNotNull(employee)) {
            employee.setFirstName(firstname);
            employee.setLastName(lastname);
            employee.setPatronymic(patronymic);
            employee.setBirthdate(birthdate);
            employee.setPositionId(positionId);
            employee.setGender(gender);
            try {
                employeeLocalService.updateEmployee(employee);
                electronicsEmployeeLocalService.getElectronicsEmployees(-1, -1).stream()
                        .filter(item -> item.getEmployeeId() == id)
                        .forEach(item -> electronicsEmployeeLocalService.deleteElectronicsEmployee(item));

                electronicsEmployeeList.forEach(ElectronicsEmployeeLocalServiceUtil::addElectronicsEmployee);
                SessionMessages.add(actionRequest, "employeeUpdated");
            } catch (Exception e) {
                SessionErrors.add(actionRequest, e.getClass().getName());
            }
        }
    }

    @ProcessAction(name = "deleteEmployee")
    public void deleteEmployee(ActionRequest actionRequest, ActionResponse actionResponse){
        long id = ParamUtil.getLong(actionRequest, "id");
        try {
            employeeLocalService.deleteEmployee(id);
            List<ElectronicsEmployee> electronicsEmployeeList = electronicsEmployeeLocalService.getElectronicsEmployees(-1, -1);
            electronicsEmployeeList.stream()
                    .filter(item -> item.getEmployeeId() == id)
                    .forEach(item -> electronicsEmployeeLocalService.deleteElectronicsEmployee(item));
            SessionMessages.add(actionRequest, "employeeDeleted");
        } catch (Exception e) {
            SessionErrors.add(actionRequest, e.getClass().getName());
        }
    }

    private boolean validateFields(ActionRequest actionRequest) {
        String firstname = ParamUtil.getString(actionRequest, "employee_firstname");
        String lastname = ParamUtil.getString(actionRequest, "employee_lastname");
        String patronymic = ParamUtil.getString(actionRequest, "patronymic");
        Date birthdate = ParamUtil.getDate(actionRequest, "birthdate", dateFormat);
        long positionId = ParamUtil.getLong(actionRequest, "positionId");
        boolean gender = ParamUtil.getBoolean(actionRequest, "gender");

        if (Validator.isNull(firstname) || Validator.isNull(lastname) || Validator.isNull(patronymic) || Validator.isNull(birthdate) || Validator.isNull(positionId) || Validator.isNull(gender)) {
            SessionErrors.add(actionRequest, "emptyField");
            return false;
        }

        LocalDate tmpDate = birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (tmpDate.isAfter(LocalDate.now()) || tmpDate.isEqual(LocalDate.now())) {
            SessionErrors.add(actionRequest, "employeeWrongDate");
            return false;
        }

        if (firstname.length() > 100) {
            SessionErrors.add(actionRequest, "firstnameTooLong");
            return false;
        }

        if (lastname.length() > 100) {
            SessionErrors.add(actionRequest, "lastnameTooLong");
            return false;
        }

        if (patronymic.length() > 100) {
            SessionErrors.add(actionRequest, "patronymicTooLong");
            return false;
        }
        return true;
    }
}