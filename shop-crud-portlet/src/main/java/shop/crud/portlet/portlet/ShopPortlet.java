package shop.crud.portlet.portlet;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Reference;
import shop.crud.portlet.constants.ShopPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import org.osgi.service.component.annotations.Component;
import shop.model.*;
import shop.service.*;
import shop.service.persistence.ElectronicsEmployeePK;

import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
public class ShopPortlet extends MVCPortlet {

	@Reference
	ElectronicsLocalService electronicsLocalService;
	@Reference
	ElectronicsEmployeeLocalService electronicsEmployeeLocalService;
	@Reference
	ElectronicsTypeLocalService electronicsTypeLocalService;
	@Reference
	EmployeeLocalService employeeLocalService;
	@Reference
	PositionTypeLocalService positionTypeLocalService;
	@Reference
	PurchaseLocalService purchaseLocalService;
	@Reference
	purchaseTypeLocalService purchaseTypeLocalService;

	@ProcessAction(name = "uploadZip")
	public void uploadZip(ActionRequest actionRequest, ActionResponse actionResponse) {

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);

		if (uploadRequest.getSize("fileName")==0) {
			SessionErrors.add(actionRequest, "fileUploadError");
			return;
		}

		if (!uploadRequest.getFileName("fileName").endsWith(".zip")) {
			SessionErrors.add(actionRequest, "fileWrongFormat");
			return;
		}

		File file = uploadRequest.getFile("fileName");

		try (FileInputStream fis = new FileInputStream(file);
			 BufferedInputStream bis = new BufferedInputStream(fis);
			 ZipInputStream stream = new ZipInputStream(bis)) {

			ZipEntry entry;
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

			while ((entry = stream.getNextEntry()) != null) {

				String fileName = entry.getName();

				if (fileName.endsWith(".csv")) {
					List<Map<String, String>> records = new ArrayList<>();

					String[] headers = null;
					String line;
					while ((line = reader.readLine()) != null) {
						String[] values = line.split(",");
						if (headers == null) {
							headers = values;
						} else {
							Map<String, String> item = new HashMap<>();
							for (int i = 0; i < headers.length; i++) {
								item.put(headers[i], values[i]);
							}
							records.add(item);
						}
					}

					switch (fileName) {
						case "shop_electronics.csv": {
							records.forEach(item -> addElectronics(actionRequest, item));
							break;
						}
						case "shop_electronicsemployee.csv": {
							records.forEach(item -> addElectronicsEmployee(actionRequest, item));
							break;
						}
						case "shop_electronicstype.csv": {
							records.forEach(item -> addElectronicsType(actionRequest, item));
							break;
						}
						case "shop_employee.csv": {
							records.forEach(item -> addEmployee(actionRequest, item));
							break;
						}
						case "shop_positiontype.csv": {
							records.forEach(item -> addPositionType(actionRequest, item));
							break;
						}
						case "shop_purchase.csv": {
							records.forEach(item -> addPurchase(actionRequest, item));
							break;
						}
						case "shop_purchasetype.csv": {
							records.forEach(item -> addPurchaseType(actionRequest, item));
							break;
						}
						default:
							break;
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			SessionErrors.add(actionRequest, "errorReadingZip", e.getMessage());
		}
	}

	public void addElectronics(ActionRequest actionRequest, Map<String, String> item) {

		Electronics electronics = null;

		try {
			electronics = electronicsLocalService.createElectronics(Long.parseLong(item.get("id")));

			electronics.setName(item.get("name"));
			electronics.setTypeId(Long.parseLong(item.get("etype")));
			electronics.setPrice(Long.parseLong(item.get("price")));
			electronics.setCount(Integer.parseInt(item.get("count")));
			electronics.setInStock(Boolean.parseBoolean(item.get("inStock")));
			electronics.setArchived(Boolean.parseBoolean(item.get("archive")));
			electronics.setDescription(item.get("description"));

			ElectronicsLocalServiceUtil.addElectronics(electronics);
		} catch (NumberFormatException en) {
			SessionErrors.add(actionRequest, "errorParsingElectronics");
		}  catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void addElectronicsEmployee(ActionRequest actionRequest, Map<String, String> item) {

		try {
			ElectronicsEmployee electronicsEmployee = electronicsEmployeeLocalService.createElectronicsEmployee(
					new ElectronicsEmployeePK(
							Long.parseLong(item.get("employeeId")),
							Long.parseLong(item.get("etype"))));

			ElectronicsEmployeeLocalServiceUtil.addElectronicsEmployee(electronicsEmployee);
		} catch (NumberFormatException en) {
			SessionErrors.add(actionRequest, "errorParsingElectronicsEmployee");
		} catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void addElectronicsType(ActionRequest actionRequest, Map<String, String> item) {

		try {
			ElectronicsType electronicsType = electronicsTypeLocalService.createElectronicsType(Long.parseLong(item.get("id")));
			electronicsType.setName(item.get("name"));

			ElectronicsTypeLocalServiceUtil.addElectronicsType(electronicsType);
		} catch (NumberFormatException en) {
			SessionErrors.add(actionRequest, "errorParsingElectronicsType");
		} catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void addEmployee(ActionRequest actionRequest, Map<String, String> item) {

		try {
			Employee employee = employeeLocalService.createEmployee(Long.parseLong(item.get("id")));

			employee.setLastName(item.get("lastname"));
			employee.setFirstName(item.get("firstname"));
			employee.setPatronymic(item.get("patronymic"));
			LocalDate date = LocalDate.parse(item.get("birthdate"));
			employee.setBirthdate(java.sql.Date.valueOf(date));
			employee.setPositionId(Long.parseLong(item.get("position")));
			employee.setGender(Boolean.parseBoolean(item.get("gender")));

			EmployeeLocalServiceUtil.addEmployee(employee);
		} catch (NumberFormatException en) {
			SessionErrors.add(actionRequest, "errorParsingEmployee");
		} catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void addPositionType(ActionRequest actionRequest, Map<String, String> item) {
		try {
			PositionType positionType = positionTypeLocalService.createPositionType(Long.parseLong(item.get("id")));
			positionType.setName(item.get("name"));

			PositionTypeLocalServiceUtil.addPositionType(positionType);
		} catch (NumberFormatException en) {
			SessionErrors.add(actionRequest, "errorParsingPositionType");
		} catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void addPurchase(ActionRequest actionRequest, Map<String, String> item) {
		try {
			Purchase purchase = purchaseLocalService.createPurchase(Long.parseLong(item.get("id")));

			purchase.setElectronicsId(Long.parseLong(item.get("electroId")));
			purchase.setEmployeeId(Long.parseLong(item.get("employeeId")));
			Timestamp date = Timestamp.valueOf(item.get("purchaseDate"));
			purchase.setPurchaseDate(date);
			purchase.setPurchaseTypeId(Long.parseLong(item.get("type")));

			PurchaseLocalServiceUtil.addPurchase(purchase);
		} catch (NumberFormatException en) {
			SessionErrors.add(actionRequest, "errorParsingPurchase");
		} catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void addPurchaseType(ActionRequest actionRequest, Map<String, String> item) {
		try {
			purchaseType pType = purchaseTypeLocalService.createpurchaseType(Long.parseLong(item.get("id")));
			pType.setName(item.get("name"));

			purchaseTypeLocalServiceUtil.addpurchaseType(pType);
		} catch (NumberFormatException en) {
			SessionErrors.add(actionRequest, "errorParsingPurchaseType");
		} catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}
}