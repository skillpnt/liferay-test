package shop.crud.portlet.portlet;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
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
                "javax.portlet.display-name=Shop",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + ShopPortletKeys.SHOP,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class ElectronicsPortlet extends MVCPortlet {

    @Reference
    ElectronicsLocalService electronicsLocalService;

    @ProcessAction(name = "addElectronics")
    public void addElectronics(ActionRequest actionRequest, ActionResponse actionResponse) {
        String name = ParamUtil.getString(actionRequest, "name");
        long typeId = ParamUtil.getLong(actionRequest, "typeId");
        long price = ParamUtil.getLong(actionRequest, "price");
        int count = ParamUtil.getInteger(actionRequest, "count");
        boolean inStock = ParamUtil.getBoolean(actionRequest, "inStock");
        boolean archived = ParamUtil.getBoolean(actionRequest, "archived");
        String description = ParamUtil.getString(actionRequest, "description");

        Electronics electronics = electronicsLocalService.createElectronics(CounterLocalServiceUtil.increment());

        electronics.setName(name);
        electronics.setTypeId(typeId);
        electronics.setPrice(price);
        electronics.setCount(count);
        electronics.setInStock(inStock);
        electronics.setArchived(archived);
        electronics.setDescription(description);

        try {
            ElectronicsLocalServiceUtil.addElectronics(electronics);
        } catch (Exception e) {
            actionRequest.setAttribute("errorMessage", e.getMessage());
        }
    }

    @ProcessAction(name = "updateElectronics")
    public void updateElectronics(ActionRequest actionRequest, ActionResponse actionResponse) {
        long id = ParamUtil.getLong(actionRequest, "id");
        String name = ParamUtil.getString(actionRequest, "name");
        long typeId = ParamUtil.getLong(actionRequest, "typeId");
        long price = ParamUtil.getLong(actionRequest, "price");
        int count = ParamUtil.getInteger(actionRequest, "count");
        boolean inStock = ParamUtil.getBoolean(actionRequest, "inStock");
        boolean archived = ParamUtil.getBoolean(actionRequest, "archived");
        String description = ParamUtil.getString(actionRequest, "description");

        Electronics electronics = null;
        try {
            electronics = electronicsLocalService.getElectronics(id);
        } catch (Exception e) {
            System.err.println(e.getCause() + e.getMessage());
        }

        if(Validator.isNotNull(electronics)) {
            electronics.setName(name);
            electronics.setTypeId(typeId);
            electronics.setPrice(price);
            electronics.setCount(count);
            electronics.setInStock(inStock);
            electronics.setArchived(archived);
            electronics.setDescription(description);
            electronicsLocalService.updateElectronics(electronics);
        }
    }

    @ProcessAction(name = "deleteElectronics")
    public void deleteElectronics(ActionRequest actionRequest, ActionResponse actionResponse){
        long id = ParamUtil.getLong(actionRequest, "id");
        try {
            electronicsLocalService.deleteElectronics(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}