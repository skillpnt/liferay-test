package shop.crud.portlet.portlet;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
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
import shop.model.Purchase;
import shop.service.*;

import java.text.SimpleDateFormat;
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

        @ProcessAction(name = "addPurchase")
        public void addPurchase(ActionRequest actionRequest, ActionResponse actionResponse) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                long electronicsId = ParamUtil.getLong(actionRequest, "electronicsId");
                long employeeId = ParamUtil.getLong(actionRequest, "employeeId");
                Date purchaseDate = ParamUtil.getDate(actionRequest, "purchaseDate", dateFormat);
                long purchaseTypeId = ParamUtil.getLong(actionRequest, "purchaseTypeId");

                Electronics electronics = null;
                try {
                        electronics = electronicsLocalService.getElectronics(electronicsId);
                } catch (Exception e) {
                        System.err.println(e.getCause() + e.getMessage());
                }

                if (electronics.getCount() > 0) {
                        Purchase purchase = purchaseLocalService.createPurchase(CounterLocalServiceUtil.increment());

                        purchase.setElectronicsId(electronicsId);
                        purchase.setEmployeeId(employeeId);
                        purchase.setPurchaseDate(purchaseDate);
                        purchase.setPurchaseTypeId(purchaseTypeId);

                        try {
                                PurchaseLocalServiceUtil.addPurchase(purchase);
                        } catch (Exception e) {
                                actionRequest.setAttribute("errorMessage", e.getMessage());
                        }

                        if (Validator.isNotNull(electronics)) {
                                electronics.setCount(electronics.getCount() - 1);
                                if (electronics.getCount() <= 1) {
                                        electronics.setInStock(false);
                                }
                                electronicsLocalService.updateElectronics(electronics);
                        }
                } else {
                        actionRequest.setAttribute("errorMessage", "Not enough items in stock!");
                }
        }

        @ProcessAction(name = "updatePurchase")
        public void updatePurchase(ActionRequest actionRequest, ActionResponse actionResponse) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                long id = ParamUtil.getLong(actionRequest, "id");
                long electronicsId = ParamUtil.getLong(actionRequest, "electronicsId");
                long employeeId = ParamUtil.getLong(actionRequest, "employeeId");
                Date purchaseDate = ParamUtil.getDate(actionRequest, "purchaseDate", dateFormat);
                long purchaseTypeId = ParamUtil.getLong(actionRequest, "purchaseTypeId");

                Purchase purchase = null;
                try {
                        purchase = purchaseLocalService.getPurchase(id);
                } catch (Exception e) {
                        System.err.println(e.getCause() + e.getMessage());
                }

                if(Validator.isNotNull(purchase)) {
                        purchase.setElectronicsId(electronicsId);
                        purchase.setEmployeeId(employeeId);
                        purchase.setPurchaseDate(purchaseDate);
                        purchase.setPurchaseTypeId(purchaseTypeId);
                        purchaseLocalService.updatePurchase(purchase);
                }
        }

        @ProcessAction(name = "deletePurchase")
        public void deletePurchase(ActionRequest actionRequest, ActionResponse actionResponse){
                long id = ParamUtil.getLong(actionRequest, "id");
                try {
                        purchaseLocalService.deletePurchase(id);
                } catch (Exception e) {
                        System.err.println(e.getMessage());
                }
        }
}