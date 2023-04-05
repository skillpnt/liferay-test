package business.card.portlet.portlet;

import business.card.portlet.constants.BusinessCardPortletKeys;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import shop.model.*;
import shop.service.*;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;
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
		"javax.portlet.display-name=BusinessCard",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + BusinessCardPortletKeys.BUSINESSCARD,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class BusinessCardPortlet extends MVCPortlet {

	@Reference
	EmployeeLocalService employeeLocalService;
	@Reference
	PurchaseLocalService purchaseLocalService;

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		List<Employee> bestEmployeesList = employeeLocalService.findByEarnings();
		renderRequest.setAttribute("bestEmployeesByEarnings", bestEmployeesList);

		List<Employee> bestEmployeesByPurchaseCount = employeeLocalService.findByPurchasesCount();
		renderRequest.setAttribute("bestEmployeesByPurchaseCount", bestEmployeesByPurchaseCount);

		long profit = purchaseLocalService.getLastMonthProfit();
		renderRequest.setAttribute("shopLastMonthProfit", profit);

		int tvsSold = purchaseLocalService.getLastMonthSoldTvCount();
		renderRequest.setAttribute("lastMonthSoldTvCount", tvsSold);

		List<Employee> employeesThatSellTvAndSmartphones = employeeLocalService.getEmployeesThatSellTvAndSmartphones();
		renderRequest.setAttribute("employeesThatSellTvAndSmartphones", employeesThatSellTvAndSmartphones);
		super.doView(renderRequest, renderResponse);
	}
}