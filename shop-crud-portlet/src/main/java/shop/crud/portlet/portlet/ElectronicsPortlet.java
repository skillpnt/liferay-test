package shop.crud.portlet.portlet;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import shop.crud.portlet.constants.ShopPortletKeys;
import shop.model.Electronics;
import shop.service.ElectronicsLocalService;
import shop.service.ElectronicsLocalServiceUtil;

import org.osgi.service.component.annotations.Component;


/**
 * @author skllp
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=Electronics CRUD",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/electronics/view.jsp",
                "javax.portlet.name=" + ShopPortletKeys.ELECTRONICS,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class ElectronicsPortlet extends MVCPortlet {

    @Reference
    ElectronicsLocalService electronicsLocalService;

    @ProcessAction(name="addElectronics")
    public void addElectronics(ActionRequest actionRequest, ActionResponse actionResponse) {
        if (!validateFields(actionRequest)) {
            return;
        }

        String name = ParamUtil.getString(actionRequest, "name");
        long typeId = ParamUtil.getLong(actionRequest, "typeId");
        long price = ParamUtil.getLong(actionRequest, "price");
        int count = ParamUtil.getInteger(actionRequest, "count");
        boolean archived = ParamUtil.getBoolean(actionRequest, "archived");
        String description = ParamUtil.getString(actionRequest, "description");

        Electronics electronics = electronicsLocalService.createElectronics(CounterLocalServiceUtil.increment());

        electronics.setName(name);
        electronics.setTypeId(typeId);
        electronics.setPrice(price);
        electronics.setCount(count);
        electronics.setInStock(count > 0);
        electronics.setArchived(archived);
        electronics.setDescription(description);

        try {
            ElectronicsLocalServiceUtil.addElectronics(electronics);
            SessionMessages.add(actionRequest, "electronicsAdded");
        } catch (Exception e) {
            SessionErrors.add(actionRequest, e.getClass().getName());
        }
    }

    @ProcessAction(name = "updateElectronics")
    public void updateElectronics(ActionRequest actionRequest, ActionResponse actionResponse) {
        if (!validateFields(actionRequest)) {
            return;
        }

        long id = ParamUtil.getLong(actionRequest, "id");
        String name = ParamUtil.getString(actionRequest, "name");
        long typeId = ParamUtil.getLong(actionRequest, "typeId");
        long price = ParamUtil.getLong(actionRequest, "price");
        int count = ParamUtil.getInteger(actionRequest, "count");
        boolean archived = ParamUtil.getBoolean(actionRequest, "archived");
        String description = ParamUtil.getString(actionRequest, "description");

        Electronics electronics = null;
        try {
            electronics = electronicsLocalService.getElectronics(id);
        } catch (Exception e) {
            SessionErrors.add(actionRequest, e.getClass().getName());
        }

        if (Validator.isNotNull(electronics)) {
            electronics.setName(name);
            electronics.setTypeId(typeId);
            electronics.setPrice(price);
            electronics.setCount(count);
            electronics.setInStock(count > 0);
            electronics.setArchived(archived);
            electronics.setDescription(description);
            try {
                electronicsLocalService.updateElectronics(electronics);
                SessionMessages.add(actionRequest, "electronicsUpdated");
            } catch (Exception e) {
                SessionErrors.add(actionRequest, e.getClass().getName());
            }
        }
    }

    @ProcessAction(name = "deleteElectronics")
    public void deleteElectronics(ActionRequest actionRequest, ActionResponse actionResponse){
        long id = ParamUtil.getLong(actionRequest, "id");
        try {
            electronicsLocalService.deleteElectronics(id);
            SessionMessages.add(actionRequest, "electronicsDeleted");
        } catch (Exception e) {
            SessionErrors.add(actionRequest, e.getClass().getName());
        }
    }

    private boolean validateFields(ActionRequest actionRequest) {
        String name = ParamUtil.getString(actionRequest, "name");
        long typeId = ParamUtil.getLong(actionRequest, "typeId");
        long price = ParamUtil.getLong(actionRequest, "price");
        int count = ParamUtil.getInteger(actionRequest, "count");
        boolean archived = ParamUtil.getBoolean(actionRequest, "archived");
        String description = ParamUtil.getString(actionRequest, "description");

        if (Validator.isNull(name) ||
                Validator.isNull(typeId) ||
                Validator.isNull(price) ||
                Validator.isNull(count) ||
                Validator.isNull(description)) {
            SessionErrors.add(actionRequest, "emptyField");
            return false;
        }

        if (name.length() > 100) {
            SessionErrors.add(actionRequest, "nameTooLong");
            return false;
        }

        if (description.length() > 5000) {
            SessionErrors.add(actionRequest, "descriptionTooLong");
            return false;
        }

        if (archived && count > 0) {
            SessionErrors.add(actionRequest, "electronicsArchivedAndInStock");
            return false;
        }

        return true;
    }
}