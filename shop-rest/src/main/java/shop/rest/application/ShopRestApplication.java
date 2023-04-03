package shop.rest.application;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONSerializer;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;
import shop.exception.NoSuchElectronicsException;
import shop.exception.NoSuchEmployeeException;
import shop.model.Electronics;
import shop.model.ElectronicsModel;
import shop.model.Employee;
import shop.service.ElectronicsLocalService;
import shop.service.EmployeeLocalService;

/**
 * @author skllp
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/shop",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=Shop.Rest"
	},
	service = Application.class
)
public class ShopRestApplication extends Application {

	@Reference
	EmployeeLocalService employeeLocalService;
	@Reference
	ElectronicsLocalService electronicsLocalService;

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Path("/employee")
	@Produces("application/json")
	public Response getAllEmployees() {
		try {
			List<Employee> employees = employeeLocalService.getEmployees(-1, -1);
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
			for (Employee employee : employees) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
				jsonObject.put("employeeId", employee.getEmployeeId());
				jsonObject.put("firstName", employee.getFirstName());
				jsonObject.put("lastName", employee.getLastName());
				jsonObject.put("birthdate", employee.getBirthdate().getTime());
				jsonArray.put(jsonObject);
			}
			return Response.ok(jsonArray.toString()).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@GET
	@Path("/employee/{id}")
	@Produces("application/json")
	public Response getEmployee(@PathParam("id") long id) {
		Employee employee = null;
		try {
			employee = employeeLocalService.getEmployee(id);
		} catch (NoSuchEmployeeException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}

		JSONObject json = JSONFactoryUtil.createJSONObject();
		json.put("employeeId", employee.getEmployeeId());
		json.put("firstName", employee.getFirstName());
		json.put("lastName", employee.getLastName());
		json.put("patronymic", employee.getPatronymic());
		json.put("birthdate", employee.getBirthdate().getTime());
		json.put("positionId", employee.getPositionId());
		json.put("gender", employee.isGender());

		JSONSerializer serializer = JSONFactoryUtil.createJSONSerializer();
		String jsonString = serializer.serialize(json);

		return Response.ok(jsonString).build();
	}

	@GET
	@Path("/electronics")
	@Produces("application/json")
	public Response getAllElectronicsInStock() {
		try {
			List<Electronics> electronics = electronicsLocalService.getElectronicses(-1, -1).stream()
					.filter(ElectronicsModel::getInStock)
					.collect(Collectors.toList());

			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
			for (Electronics product : electronics) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
				jsonObject.put("electronicsId", product.getElectronicsId());
				jsonObject.put("name", product.getName());
				jsonObject.put("price", product.getPrice());
				jsonObject.put("description", product.getDescription());
				jsonArray.put(jsonObject);
			}
			return Response.ok(jsonArray.toString()).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}
	}

	@GET
	@Path("/electronics/{id}")
	@Produces("application/json")
	public Response getElectronics(@PathParam("id") long id) {
		Electronics electronics = null;
		try {
			electronics = electronicsLocalService.getElectronics(id);
		} catch (NoSuchElectronicsException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} catch (Exception e) {
			return Response.serverError().build();
		}

		JSONObject json = JSONFactoryUtil.createJSONObject();
		json.put("electronicsId", electronics.getElectronicsId());
		json.put("name", electronics.getName());
		json.put("typeId", electronics.getTypeId());
		json.put("price", electronics.getPrice());
		json.put("count", electronics.getCount());
		json.put("inStock", electronics.getInStock());
		json.put("archived", electronics.getArchived());
		json.put("description", electronics.getDescription());

		JSONSerializer serializer = JSONFactoryUtil.createJSONSerializer();
		String jsonString = serializer.serialize(json);

		return Response.ok(jsonString).build();
	}
}