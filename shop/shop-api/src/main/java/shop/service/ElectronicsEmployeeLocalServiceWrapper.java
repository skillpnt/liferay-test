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

package shop.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ElectronicsEmployeeLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ElectronicsEmployeeLocalService
 * @generated
 */
public class ElectronicsEmployeeLocalServiceWrapper
	implements ElectronicsEmployeeLocalService,
			   ServiceWrapper<ElectronicsEmployeeLocalService> {

	public ElectronicsEmployeeLocalServiceWrapper(
		ElectronicsEmployeeLocalService electronicsEmployeeLocalService) {

		_electronicsEmployeeLocalService = electronicsEmployeeLocalService;
	}

	/**
	 * Adds the electronics employee to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ElectronicsEmployeeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param electronicsEmployee the electronics employee
	 * @return the electronics employee that was added
	 */
	@Override
	public shop.model.ElectronicsEmployee addElectronicsEmployee(
		shop.model.ElectronicsEmployee electronicsEmployee) {

		return _electronicsEmployeeLocalService.addElectronicsEmployee(
			electronicsEmployee);
	}

	/**
	 * Creates a new electronics employee with the primary key. Does not add the electronics employee to the database.
	 *
	 * @param electronicsEmployeePK the primary key for the new electronics employee
	 * @return the new electronics employee
	 */
	@Override
	public shop.model.ElectronicsEmployee createElectronicsEmployee(
		shop.service.persistence.ElectronicsEmployeePK electronicsEmployeePK) {

		return _electronicsEmployeeLocalService.createElectronicsEmployee(
			electronicsEmployeePK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _electronicsEmployeeLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the electronics employee from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ElectronicsEmployeeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param electronicsEmployee the electronics employee
	 * @return the electronics employee that was removed
	 */
	@Override
	public shop.model.ElectronicsEmployee deleteElectronicsEmployee(
		shop.model.ElectronicsEmployee electronicsEmployee) {

		return _electronicsEmployeeLocalService.deleteElectronicsEmployee(
			electronicsEmployee);
	}

	/**
	 * Deletes the electronics employee with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ElectronicsEmployeeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param electronicsEmployeePK the primary key of the electronics employee
	 * @return the electronics employee that was removed
	 * @throws PortalException if a electronics employee with the primary key could not be found
	 */
	@Override
	public shop.model.ElectronicsEmployee deleteElectronicsEmployee(
			shop.service.persistence.ElectronicsEmployeePK
				electronicsEmployeePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _electronicsEmployeeLocalService.deleteElectronicsEmployee(
			electronicsEmployeePK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _electronicsEmployeeLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _electronicsEmployeeLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _electronicsEmployeeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>shop.model.impl.ElectronicsEmployeeModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _electronicsEmployeeLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>shop.model.impl.ElectronicsEmployeeModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _electronicsEmployeeLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _electronicsEmployeeLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _electronicsEmployeeLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public shop.model.ElectronicsEmployee fetchElectronicsEmployee(
		shop.service.persistence.ElectronicsEmployeePK electronicsEmployeePK) {

		return _electronicsEmployeeLocalService.fetchElectronicsEmployee(
			electronicsEmployeePK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _electronicsEmployeeLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the electronics employee with the primary key.
	 *
	 * @param electronicsEmployeePK the primary key of the electronics employee
	 * @return the electronics employee
	 * @throws PortalException if a electronics employee with the primary key could not be found
	 */
	@Override
	public shop.model.ElectronicsEmployee getElectronicsEmployee(
			shop.service.persistence.ElectronicsEmployeePK
				electronicsEmployeePK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _electronicsEmployeeLocalService.getElectronicsEmployee(
			electronicsEmployeePK);
	}

	/**
	 * Returns a range of all the electronics employees.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>shop.model.impl.ElectronicsEmployeeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of electronics employees
	 * @param end the upper bound of the range of electronics employees (not inclusive)
	 * @return the range of electronics employees
	 */
	@Override
	public java.util.List<shop.model.ElectronicsEmployee>
		getElectronicsEmployees(int start, int end) {

		return _electronicsEmployeeLocalService.getElectronicsEmployees(
			start, end);
	}

	/**
	 * Returns the number of electronics employees.
	 *
	 * @return the number of electronics employees
	 */
	@Override
	public int getElectronicsEmployeesCount() {
		return _electronicsEmployeeLocalService.getElectronicsEmployeesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _electronicsEmployeeLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _electronicsEmployeeLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _electronicsEmployeeLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the electronics employee in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ElectronicsEmployeeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param electronicsEmployee the electronics employee
	 * @return the electronics employee that was updated
	 */
	@Override
	public shop.model.ElectronicsEmployee updateElectronicsEmployee(
		shop.model.ElectronicsEmployee electronicsEmployee) {

		return _electronicsEmployeeLocalService.updateElectronicsEmployee(
			electronicsEmployee);
	}

	@Override
	public ElectronicsEmployeeLocalService getWrappedService() {
		return _electronicsEmployeeLocalService;
	}

	@Override
	public void setWrappedService(
		ElectronicsEmployeeLocalService electronicsEmployeeLocalService) {

		_electronicsEmployeeLocalService = electronicsEmployeeLocalService;
	}

	private ElectronicsEmployeeLocalService _electronicsEmployeeLocalService;

}