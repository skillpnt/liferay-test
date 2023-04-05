/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package shop.service.base;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.util.PortalUtil;

import java.lang.reflect.Field;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import shop.model.ElectronicsType;

import shop.service.ElectronicsTypeService;
import shop.service.ElectronicsTypeServiceUtil;
import shop.service.persistence.ElectronicsEmployeePersistence;
import shop.service.persistence.ElectronicsPersistence;
import shop.service.persistence.ElectronicsTypePersistence;
import shop.service.persistence.EmployeeFinder;
import shop.service.persistence.EmployeePersistence;
import shop.service.persistence.PositionTypePersistence;
import shop.service.persistence.PurchaseFinder;
import shop.service.persistence.PurchasePersistence;
import shop.service.persistence.purchaseTypePersistence;

/**
 * Provides the base implementation for the electronics type remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link shop.service.impl.ElectronicsTypeServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see shop.service.impl.ElectronicsTypeServiceImpl
 * @generated
 */
public abstract class ElectronicsTypeServiceBaseImpl
	extends BaseServiceImpl
	implements AopService, ElectronicsTypeService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>ElectronicsTypeService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>ElectronicsTypeServiceUtil</code>.
	 */
	@Deactivate
	protected void deactivate() {
		_setServiceUtilService(null);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			ElectronicsTypeService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		electronicsTypeService = (ElectronicsTypeService)aopProxy;

		_setServiceUtilService(electronicsTypeService);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ElectronicsTypeService.class.getName();
	}

	protected Class<?> getModelClass() {
		return ElectronicsType.class;
	}

	protected String getModelClassName() {
		return ElectronicsType.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = electronicsTypePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	private void _setServiceUtilService(
		ElectronicsTypeService electronicsTypeService) {

		try {
			Field field = ElectronicsTypeServiceUtil.class.getDeclaredField(
				"_service");

			field.setAccessible(true);

			field.set(null, electronicsTypeService);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Reference
	protected ElectronicsPersistence electronicsPersistence;

	@Reference
	protected ElectronicsEmployeePersistence electronicsEmployeePersistence;

	@Reference
	protected shop.service.ElectronicsTypeLocalService
		electronicsTypeLocalService;

	protected ElectronicsTypeService electronicsTypeService;

	@Reference
	protected ElectronicsTypePersistence electronicsTypePersistence;

	@Reference
	protected EmployeePersistence employeePersistence;

	@Reference
	protected EmployeeFinder employeeFinder;

	@Reference
	protected PositionTypePersistence positionTypePersistence;

	@Reference
	protected PurchasePersistence purchasePersistence;

	@Reference
	protected PurchaseFinder purchaseFinder;

	@Reference
	protected purchaseTypePersistence purchaseTypePersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameService
		classNameService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserService userService;

	private static final Log _log = LogFactoryUtil.getLog(
		ElectronicsTypeServiceBaseImpl.class);

}