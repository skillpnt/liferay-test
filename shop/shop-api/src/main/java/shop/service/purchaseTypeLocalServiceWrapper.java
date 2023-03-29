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
 * Provides a wrapper for {@link purchaseTypeLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see purchaseTypeLocalService
 * @generated
 */
public class purchaseTypeLocalServiceWrapper
	implements purchaseTypeLocalService,
			   ServiceWrapper<purchaseTypeLocalService> {

	public purchaseTypeLocalServiceWrapper(
		purchaseTypeLocalService purchaseTypeLocalService) {

		_purchaseTypeLocalService = purchaseTypeLocalService;
	}

	/**
	 * Adds the purchase type to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect purchaseTypeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param purchaseType the purchase type
	 * @return the purchase type that was added
	 */
	@Override
	public shop.model.purchaseType addpurchaseType(
		shop.model.purchaseType purchaseType) {

		return _purchaseTypeLocalService.addpurchaseType(purchaseType);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _purchaseTypeLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new purchase type with the primary key. Does not add the purchase type to the database.
	 *
	 * @param purchaseTypeId the primary key for the new purchase type
	 * @return the new purchase type
	 */
	@Override
	public shop.model.purchaseType createpurchaseType(long purchaseTypeId) {
		return _purchaseTypeLocalService.createpurchaseType(purchaseTypeId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _purchaseTypeLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the purchase type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect purchaseTypeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param purchaseTypeId the primary key of the purchase type
	 * @return the purchase type that was removed
	 * @throws PortalException if a purchase type with the primary key could not be found
	 */
	@Override
	public shop.model.purchaseType deletepurchaseType(long purchaseTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _purchaseTypeLocalService.deletepurchaseType(purchaseTypeId);
	}

	/**
	 * Deletes the purchase type from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect purchaseTypeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param purchaseType the purchase type
	 * @return the purchase type that was removed
	 */
	@Override
	public shop.model.purchaseType deletepurchaseType(
		shop.model.purchaseType purchaseType) {

		return _purchaseTypeLocalService.deletepurchaseType(purchaseType);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _purchaseTypeLocalService.dynamicQuery();
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

		return _purchaseTypeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>shop.model.impl.purchaseTypeModelImpl</code>.
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

		return _purchaseTypeLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>shop.model.impl.purchaseTypeModelImpl</code>.
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

		return _purchaseTypeLocalService.dynamicQuery(
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

		return _purchaseTypeLocalService.dynamicQueryCount(dynamicQuery);
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

		return _purchaseTypeLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public shop.model.purchaseType fetchpurchaseType(long purchaseTypeId) {
		return _purchaseTypeLocalService.fetchpurchaseType(purchaseTypeId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _purchaseTypeLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _purchaseTypeLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _purchaseTypeLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _purchaseTypeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the purchase type with the primary key.
	 *
	 * @param purchaseTypeId the primary key of the purchase type
	 * @return the purchase type
	 * @throws PortalException if a purchase type with the primary key could not be found
	 */
	@Override
	public shop.model.purchaseType getpurchaseType(long purchaseTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _purchaseTypeLocalService.getpurchaseType(purchaseTypeId);
	}

	/**
	 * Returns a range of all the purchase types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>shop.model.impl.purchaseTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of purchase types
	 * @param end the upper bound of the range of purchase types (not inclusive)
	 * @return the range of purchase types
	 */
	@Override
	public java.util.List<shop.model.purchaseType> getpurchaseTypes(
		int start, int end) {

		return _purchaseTypeLocalService.getpurchaseTypes(start, end);
	}

	/**
	 * Returns the number of purchase types.
	 *
	 * @return the number of purchase types
	 */
	@Override
	public int getpurchaseTypesCount() {
		return _purchaseTypeLocalService.getpurchaseTypesCount();
	}

	/**
	 * Updates the purchase type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect purchaseTypeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param purchaseType the purchase type
	 * @return the purchase type that was updated
	 */
	@Override
	public shop.model.purchaseType updatepurchaseType(
		shop.model.purchaseType purchaseType) {

		return _purchaseTypeLocalService.updatepurchaseType(purchaseType);
	}

	@Override
	public purchaseTypeLocalService getWrappedService() {
		return _purchaseTypeLocalService;
	}

	@Override
	public void setWrappedService(
		purchaseTypeLocalService purchaseTypeLocalService) {

		_purchaseTypeLocalService = purchaseTypeLocalService;
	}

	private purchaseTypeLocalService _purchaseTypeLocalService;

}