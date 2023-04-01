package shop.crud.portlet.portlet;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.util.portlet.PortletProps;
import shop.crud.portlet.constants.ShopPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.*;

import org.osgi.service.component.annotations.Component;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
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

	@ProcessAction(name = "uploadZip")
	public void uploadZip(ActionRequest actionRequest, ActionResponse actionResponse) {

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);

		if (uploadRequest.getSize("fileName")==0) {
			SessionErrors.add(actionRequest, "error");
			return;
		}

		System.out.println("Size: " + uploadRequest.getSize("fileName"));
		System.out.println("File name: " + uploadRequest.getFileName("fileName"));

		File file = uploadRequest.getFile("fileName");

		byte[] buffer = new byte[2048];
		Path outDir = Paths.get("shop-crud-portlet","src", "main", "resources", "output");

		try (FileInputStream fis = new FileInputStream(file);
			 BufferedInputStream bis = new BufferedInputStream(fis);
			 ZipInputStream stream = new ZipInputStream(bis)) {

			ZipEntry entry;

			while ((entry = stream.getNextEntry()) != null) {

				Path filePath = outDir.resolve(entry.getName());
				//TODO: process files
				try (FileOutputStream fos = new FileOutputStream(filePath.toFile());
					 BufferedOutputStream bos = new BufferedOutputStream(fos, buffer.length)) {

					int len;
					while ((len = stream.read(buffer)) > 0) {
						bos.write(buffer, 0, len);
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}