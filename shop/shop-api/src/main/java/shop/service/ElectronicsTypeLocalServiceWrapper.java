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
 * Provides a wrapper for {@link ElectronicsTypeLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ElectronicsTypeLocalService
 * @generated
 */
public class ElectronicsTypeLocalServiceWrapper
	implements ElectronicsTypeLocalService,
			   ServiceWrapper<ElectronicsTypeLocalService> {

	public ElectronicsTypeLocalServiceWrapper(
		ElectronicsTypeLocalService electronicsTypeLocalService) {

		_electronicsTypeLocalService = electronicsTypeLocalService;
	}

	/**
	 * Adds the electronics type to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ElectronicsTypeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param electronicsType the electronics type
	 * @return the electronics type that was added
	 */
	@Override
	public shop.model.ElectronicsType addElectronicsType(
		shop.model.ElectronicsType electronicsType) {

		return _electronicsTypeLocalService.addElectronicsType(electronicsType);
	}

	/**
	 * Creates a new electronics type with the primary key. Does not add the electronics type to the database.
	 *
	 * @param electronicsTypeId the primary key for the new electronics type
	 * @return the new electronics type
	 */
	@Override
	public shop.model.ElectronicsType createElectronicsType(
		long electronicsTypeId) {

		return _electronicsTypeLocalService.createElectronicsType(
			electronicsTypeId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _electronicsTypeLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the electronics type from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ElectronicsTypeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param electronicsType the electronics type
	 * @return the electronics type that was removed
	 */
	@Override
	public shop.model.ElectronicsType deleteElectronicsType(
		shop.model.ElectronicsType electronicsType) {

		return _electronicsTypeLocalService.deleteElectronicsType(
			electronicsType);
	}

	/**
	 * Deletes the electronics type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ElectronicsTypeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param electronicsTypeId the primary key of the electronics type
	 * @return the electronics type that was removed
	 * @throws PortalException if a electronics type with the primary key could not be found
	 */
	@Override
	public shop.model.ElectronicsType deleteElectronicsType(
			long electronicsTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _electronicsTypeLocalService.deleteElectronicsType(
			electronicsTypeId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _electronicsTypeLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _electronicsTypeLocalService.dynamicQuery();
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

		return _electronicsTypeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>shop.model.impl.ElectronicsTypeModelImpl</code>.
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

		return _electronicsTypeLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>shop.model.impl.ElectronicsTypeModelImpl</code>.
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

		return _electronicsTypeLocalService.dynamicQuery(
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

		return _electronicsTypeLocalService.dynamicQueryCount(dynamicQuery);
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

		return _electronicsTypeLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public shop.model.ElectronicsType fetchElectronicsType(
		long electronicsTypeId) {

		return _electronicsTypeLocalService.fetchElectronicsType(
			electronicsTypeId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _electronicsTypeLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the electronics type with the primary key.
	 *
	 * @param electronicsTypeId the primary key of the electronics type
	 * @return the electronics type
	 * @throws PortalException if a electronics type with the primary key could not be found
	 */
	@Override
	public shop.model.ElectronicsType getElectronicsType(long electronicsTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _electronicsTypeLocalService.getElectronicsType(
			electronicsTypeId);
	}

	/**
	 * Returns a range of all the electronics types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>shop.model.impl.ElectronicsTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of electronics types
	 * @param end the upper bound of the range of electronics types (not inclusive)
	 * @return the range of electronics types
	 */
	@Override
	public java.util.List<shop.model.ElectronicsType> getElectronicsTypes(
		int start, int end) {

		return _electronicsTypeLocalService.getElectronicsTypes(start, end);
	}

	/**
	 * Returns the number of electronics types.
	 *
	 * @return the number of electronics types
	 */
	@Override
	public int getElectronicsTypesCount() {
		return _electronicsTypeLocalService.getElectronicsTypesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _electronicsTypeLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _electronicsTypeLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _electronicsTypeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the electronics type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ElectronicsTypeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param electronicsType the electronics type
	 * @return the electronics type that was updated
	 */
	@Override
	public shop.model.ElectronicsType updateElectronicsType(
		shop.model.ElectronicsType electronicsType) {

		return _electronicsTypeLocalService.updateElectronicsType(
			electronicsType);
	}

	@Override
	public ElectronicsTypeLocalService getWrappedService() {
		return _electronicsTypeLocalService;
	}

	@Override
	public void setWrappedService(
		ElectronicsTypeLocalService electronicsTypeLocalService) {

		_electronicsTypeLocalService = electronicsTypeLocalService;
	}

	private ElectronicsTypeLocalService _electronicsTypeLocalService;

}