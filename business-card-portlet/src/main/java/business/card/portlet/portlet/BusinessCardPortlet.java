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
	@Reference
	ElectronicsLocalService electronicsLocalService;
	@Reference
	PositionTypeLocalService positionTypeLocalService;
	@Reference
	ElectronicsTypeLocalService electronicsTypeLocalService;
	@Reference
	ElectronicsEmployeeLocalService electronicsEmployeeLocalService;

	public void getBestEmployees(RenderRequest renderRequest) {
		List<Employee> employees = employeeLocalService.getEmployees(-1, -1);
		List<Purchase> purchases = purchaseLocalService.getPurchases(-1, -1);

		Map<Long, Long> employeesEarnings = new HashMap<>();
		Map<Long, Employee> bestEmployees = new HashMap<>();

		Map<Long, Integer> employeesPurchasesCount = new HashMap<>();
		Map<Long, Employee> bestEmployeesByPurchasesCount = new HashMap<>();

		employees.forEach(employee -> {
			long sum = purchases.stream()
					.filter(item -> item.getEmployeeId() == employee.getEmployeeId())
					.mapToLong(item -> {
						try {
							return electronicsLocalService.getElectronics(item.getElectronicsId()).getPrice();
						} catch (PortalException e) {
							throw new RuntimeException(e);
						}
					})
					.sum();

			List<Purchase> employeePurchases = purchases.stream()
					.filter(purchase -> purchase.getEmployeeId() == employee.getEmployeeId())
					.collect(Collectors.toList());

			int totalPurchases = employeePurchases.size();
			employeesPurchasesCount.merge(employee.getEmployeeId(), totalPurchases, Integer::sum);

			employeesEarnings.put(employee.getEmployeeId(), sum);

			if (!bestEmployees.containsKey(employee.getPositionId())) {
				bestEmployees.put(employee.getPositionId(), employee);
			} else {
				if (employeesEarnings.get(employee.getEmployeeId()) >
						employeesEarnings.get(bestEmployees.get(employee.getPositionId()).getEmployeeId()))
					bestEmployees.put(employee.getPositionId(), employee);
			}

			if (!bestEmployeesByPurchasesCount.containsKey(employee.getEmployeeId())) {
				bestEmployeesByPurchasesCount.put(employee.getPositionId(), employee);
			} else {
				if (employeesPurchasesCount.get(employee.getEmployeeId()) >=
						employeesPurchasesCount.get(bestEmployeesByPurchasesCount.get(employee.getEmployeeId()))) {
					bestEmployeesByPurchasesCount.put(employee.getPositionId(), employee);
				}
			}
		});

		renderRequest.setAttribute("bestEmployees", bestEmployees);
		renderRequest.setAttribute("employeesEarnings", employeesEarnings);

		renderRequest.setAttribute("bestEmployeesByCount", bestEmployeesByPurchasesCount);
		renderRequest.setAttribute("bestEmployeesCount", employeesPurchasesCount);
		getShopProfit(renderRequest, employeesEarnings);
	}

	public void getShopProfit(RenderRequest renderRequest, Map<Long, Long> employeesEarnings) {
		long sum = employeesEarnings.values().stream().mapToLong(Long::longValue).sum();
		renderRequest.setAttribute("shopProfit", sum);
	}

	public void getSoldTvsCount(RenderRequest renderRequest) {
		ElectronicsType tvType = electronicsTypeLocalService.getElectronicsTypes(-1, -1).stream()
				.filter(type -> type.getName().equals("TV"))
				.findAny()
				.orElse(null);

		if (Validator.isNotNull(tvType)) {
			List<Long> tvIds = electronicsLocalService.getElectronicses(-1, -1).stream()
					.filter(electronic -> electronic.getTypeId() == tvType.getElectronicsTypeId())
					.map(Electronics::getElectronicsId)
					.collect(Collectors.toList());

			int soldTvsCount = (int) purchaseLocalService.getPurchases(-1, -1).stream()
					.filter(purchase -> tvIds.contains(purchase.getElectronicsId()) &&
							purchase.getPurchaseDate().after(Date.from(ZonedDateTime.now().minusMonths(1).toInstant())))
					.count();

			renderRequest.setAttribute("soldTvsCount", soldTvsCount);
		} else {
			SessionErrors.add(renderRequest, "error");
		}
	}

	public void getEmployeesTvSmartphonesByOrderByCountAsc(RenderRequest renderRequest) {
		List<Long> tvSmartphonesTypesIds = electronicsTypeLocalService.getElectronicsTypes(-1, -1).stream()
				.filter(type -> type.getName().equals("TV") || type.getName().equals("Smartphones"))
				.map(ElectronicsType::getElectronicsTypeId)
				.collect(Collectors.toList());

		List<Long> tvSmartphonesEmployees = electronicsEmployeeLocalService.getElectronicsEmployees(-1, -1).stream()
				.filter(ee -> ee.getElectronicsTypeId() == tvSmartphonesTypesIds.get(0) ||
						ee.getElectronicsTypeId() == tvSmartphonesTypesIds.get(1))
				.map(ElectronicsEmployee::getEmployeeId)
				.distinct()
				.collect(Collectors.toList());

		List<Purchase> purchases = purchaseLocalService.getPurchases(-1, -1);
		Map<Long, Integer> tvSmartphonesPurchases = new HashMap<>();

		for (long employeeId : tvSmartphonesEmployees) {

			List<Purchase> employeePurchases = purchases.stream()
					.filter(purchase -> purchase.getEmployeeId() == employeeId)
					.collect(Collectors.toList());

			int totalPurchases = employeePurchases.size();

			tvSmartphonesPurchases.merge(employeeId, totalPurchases, Integer::sum);
		}

		Map<Long, Integer> sortedMap = tvSmartphonesPurchases.entrySet().stream()
				.sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		renderRequest.setAttribute("sortedTvSmartphoneMap", sortedMap);
	}

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		getBestEmployees(renderRequest);
		getSoldTvsCount(renderRequest);
		getEmployeesTvSmartphonesByOrderByCountAsc(renderRequest);
		super.doView(renderRequest, renderResponse);
	}
}