package shop.crud.portlet.portlet;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Reference;
import shop.crud.portlet.constants.ShopPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.ProcessAction;

import org.osgi.service.component.annotations.Component;
import shop.model.Electronics;
import shop.model.ElectronicsEmployee;
import shop.model.Purchase;
import shop.service.*;
import shop.service.persistence.ElectronicsEmployeePK;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * @author skllp
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.instanceable=true",
                "javax.portlet.display-name=Purchase CRUD",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/purchase/view.jsp",
                "javax.portlet.name=" + ShopPortletKeys.PURCHASE,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class PurchasePortlet extends MVCPortlet {

        @Reference
        PurchaseLocalService purchaseLocalService;
        @Reference
        ElectronicsLocalService electronicsLocalService;
        @Reference
        ElectronicsEmployeeLocalService electronicsEmployeeLocalService;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        @ProcessAction(name = "addPurchase")
        public void addPurchase(ActionRequest actionRequest, ActionResponse actionResponse) {

                long electronicsId = ParamUtil.getLong(actionRequest, "electronicsId");
                long employeeId = ParamUtil.getLong(actionRequest, "employeeId");
                Date purchaseDate = ParamUtil.getDate(actionRequest, "purchaseDate", dateFormat);
                long purchaseTypeId = ParamUtil.getLong(actionRequest, "purchaseTypeId");

                if (Validator.isNull(electronicsId) || Validator.isNull(employeeId) || Validator.isNull(purchaseDate) || Validator.isNull(purchaseTypeId)) {
                        SessionErrors.add(actionRequest, "emptyField");
                        return;
                }

                try {
                        Electronics tmpElectronics = electronicsLocalService.getElectronics(electronicsId);
                        long electronicsType = tmpElectronics.getTypeId();
                        electronicsEmployeeLocalService.getElectronicsEmployee(new ElectronicsEmployeePK(employeeId, electronicsType));
                } catch (PortalException e) {
                        SessionErrors.add(actionRequest, "employeeNoElectronicsType");
                        return;
                }

                if (purchaseDate.after(new Date())) {
                        SessionErrors.add(actionRequest, "purchaseWrongDate");
                        return;
                }

                Electronics electronics = null;
                try {
                        electronics = electronicsLocalService.getElectronics(electronicsId);
                } catch (Exception e) {
                        SessionErrors.add(actionRequest, e.getClass().getName());
                }

                if (Validator.isNotNull(electronics) && electronics.getCount() > 0) {
                        Purchase purchase = purchaseLocalService.createPurchase(CounterLocalServiceUtil.increment());

                        purchase.setElectronicsId(electronicsId);
                        purchase.setEmployeeId(employeeId);
                        purchase.setPurchaseDate(purchaseDate);
                        purchase.setPurchaseTypeId(purchaseTypeId);

                        electronics.setCount(electronics.getCount() - 1);
                        electronicsLocalService.updateElectronics(electronics);
                        if (electronics.getCount() < 1) {
                                electronics.setInStock(false);
                        }

                        try {
                                PurchaseLocalServiceUtil.addPurchase(purchase);
                                SessionMessages.add(actionRequest, "purchaseAdded");
                        } catch (Exception e) {
                                SessionErrors.add(actionRequest, e.getClass().getName());
                        }
                } else {
                        SessionErrors.add(actionRequest, "notEnoughElectronics");
                }
        }

        @ProcessAction(name = "updatePurchase")
        public void updatePurchase(ActionRequest actionRequest, ActionResponse actionResponse) {

                long id = ParamUtil.getLong(actionRequest, "id");
                long electronicsId = ParamUtil.getLong(actionRequest, "electronicsId");
                long employeeId = ParamUtil.getLong(actionRequest, "employeeId");
                Date purchaseDate = ParamUtil.getDate(actionRequest, "purchaseDate", dateFormat);
                long purchaseTypeId = ParamUtil.getLong(actionRequest, "purchaseTypeId");

                Purchase purchase = null;
                try {
                        purchase = purchaseLocalService.getPurchase(id);
                } catch (Exception e) {
                        SessionErrors.add(actionRequest, e.getClass().getName());
                }

                try {
                        Electronics tmpElectronics = electronicsLocalService.getElectronics(electronicsId);
                        long electronicsType = tmpElectronics.getTypeId();
                        electronicsEmployeeLocalService.getElectronicsEmployee(new ElectronicsEmployeePK(employeeId, electronicsType));
                } catch (PortalException e) {
                        SessionErrors.add(actionRequest, "employeeNoElectronicsType");
                        return;
                }

                if(Validator.isNotNull(purchase)) {
                        purchase.setElectronicsId(electronicsId);
                        purchase.setEmployeeId(employeeId);
                        purchase.setPurchaseDate(purchaseDate);
                        purchase.setPurchaseTypeId(purchaseTypeId);
                        try {
                                purchaseLocalService.updatePurchase(purchase);
                                SessionMessages.add(actionRequest, "purchaseUpdated");
                        } catch (Exception e) {
                                SessionErrors.add(actionRequest, e.getClass().getName());
                        }
                }
        }

        @ProcessAction(name = "deletePurchase")
        public void deletePurchase(ActionRequest actionRequest, ActionResponse actionResponse){
                long id = ParamUtil.getLong(actionRequest, "id");
                try {
                        purchaseLocalService.deletePurchase(id);
                        SessionMessages.add(actionRequest, "purchaseDeleted");
                } catch (Exception e) {
                        SessionErrors.add(actionRequest, e.getClass().getName());
                }
        }
}