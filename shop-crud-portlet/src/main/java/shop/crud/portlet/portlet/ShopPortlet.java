package shop.crud.portlet.portlet;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.util.portlet.PortletProps;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.osgi.service.component.annotations.Reference;
import shop.crud.portlet.constants.ShopPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import org.osgi.service.component.annotations.Component;
import shop.model.*;
import shop.service.*;
import shop.service.persistence.ElectronicsEmployeePK;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
			SessionErrors.add(actionRequest, "error");
			return;
		}

		//TODO: удалить печать в консоль, добавить сообщения об ошибках
		System.out.println("Size: " + uploadRequest.getSize("fileName"));
		System.out.println("File name: " + uploadRequest.getFileName("fileName"));

		File file = uploadRequest.getFile("fileName");

		try (FileInputStream fis = new FileInputStream(file);
			 BufferedInputStream bis = new BufferedInputStream(fis);
			 ZipInputStream stream = new ZipInputStream(bis)) {

			ZipEntry entry;

			while ((entry = stream.getNextEntry()) != null) {

				List<Map<String, String>> records = new ArrayList<>();
				String fileName = entry.getName();

				if (fileName.endsWith(".csv")) {
					BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
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
					reader.close();
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
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void addElectronics(ActionRequest actionRequest, Map<String, String> item) {

		Electronics electronics = electronicsLocalService.createElectronics(Long.parseLong(item.get("electronicsid")));

		electronics.setName(item.get("name"));
		electronics.setTypeId(Long.parseLong(item.get("typeid")));
		electronics.setPrice(Long.parseLong(item.get("price")));
		electronics.setCount(Integer.parseInt(item.get("count")));
		electronics.setInStock(Boolean.parseBoolean(item.get("instock")));
		electronics.setArchived(Boolean.parseBoolean(item.get("archived")));
		electronics.setDescription(item.get("description"));

		try {
			ElectronicsLocalServiceUtil.addElectronics(electronics);
		} catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void addElectronicsEmployee(ActionRequest actionRequest, Map<String, String> item) {
		ElectronicsEmployee electronicsEmployee = electronicsEmployeeLocalService.createElectronicsEmployee(
				new ElectronicsEmployeePK(
						Long.parseLong(item.get("employeeid")),
						Long.parseLong(item.get("electronicstypeid"))));

		try {
			ElectronicsEmployeeLocalServiceUtil.addElectronicsEmployee(electronicsEmployee);
		} catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void addElectronicsType(ActionRequest actionRequest, Map<String, String> item) {
		ElectronicsType electronicsType = electronicsTypeLocalService.createElectronicsType(Long.parseLong(item.get("electronicstypeid")));
		electronicsType.setName(item.get("name"));

		try {
			ElectronicsTypeLocalServiceUtil.addElectronicsType(electronicsType);
		} catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void addEmployee(ActionRequest actionRequest, Map<String, String> item) {
		Employee employee = employeeLocalService.createEmployee(Long.parseLong(item.get("employeeid")));

		employee.setLastName(item.get("lastname"));
		employee.setFirstName(item.get("firstname"));
		employee.setPatronymic(item.get("patronymic"));
		LocalDate date = LocalDate.parse(item.get("birthdate"));
		employee.setBirthdate(java.sql.Date.valueOf(date));
		employee.setPositionId(Long.parseLong(item.get("positionid")));
		employee.setGender(Boolean.getBoolean(item.get("gender")));

		try {
			EmployeeLocalServiceUtil.addEmployee(employee);
		} catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void addPositionType(ActionRequest actionRequest, Map<String, String> item) {
		PositionType positionType = positionTypeLocalService.createPositionType(Long.parseLong(item.get("positionid")));
		positionType.setName(item.get("name"));

		try {
			PositionTypeLocalServiceUtil.addPositionType(positionType);
		} catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void addPurchase(ActionRequest actionRequest, Map<String, String> item) {
		Purchase purchase = purchaseLocalService.createPurchase(Long.parseLong(item.get("purchaseid")));

		purchase.setElectronicsId(Long.parseLong(item.get("electronicsid")));
		purchase.setEmployeeId(Long.parseLong(item.get("employeeid")));
		LocalDateTime date = LocalDateTime.parse(item.get("purchasedate"));
		purchase.setPurchaseDate(java.sql.Timestamp.valueOf(date));
		purchase.setPurchaseTypeId(Long.parseLong(item.get("purchasetypeid")));

		try {
			PurchaseLocalServiceUtil.addPurchase(purchase);
		} catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void addPurchaseType(ActionRequest actionRequest, Map<String, String> item) {
		purchaseType pType = purchaseTypeLocalService.createpurchaseType(Long.parseLong(item.get("purchasetypeid")));
		pType.setName(item.get("name"));

		try {
			purchaseTypeLocalServiceUtil.addpurchaseType(pType);
		} catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}
}