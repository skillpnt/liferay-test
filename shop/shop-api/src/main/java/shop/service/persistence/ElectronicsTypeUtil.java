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

package shop.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import shop.model.ElectronicsType;

/**
 * The persistence utility for the electronics type service. This utility wraps <code>shop.service.persistence.impl.ElectronicsTypePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ElectronicsTypePersistence
 * @generated
 */
public class ElectronicsTypeUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ElectronicsType electronicsType) {
		getPersistence().clearCache(electronicsType);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, ElectronicsType> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ElectronicsType> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ElectronicsType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ElectronicsType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ElectronicsType> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ElectronicsType update(ElectronicsType electronicsType) {
		return getPersistence().update(electronicsType);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ElectronicsType update(
		ElectronicsType electronicsType, ServiceContext serviceContext) {

		return getPersistence().update(electronicsType, serviceContext);
	}

	/**
	 * Caches the electronics type in the entity cache if it is enabled.
	 *
	 * @param electronicsType the electronics type
	 */
	public static void cacheResult(ElectronicsType electronicsType) {
		getPersistence().cacheResult(electronicsType);
	}

	/**
	 * Caches the electronics types in the entity cache if it is enabled.
	 *
	 * @param electronicsTypes the electronics types
	 */
	public static void cacheResult(List<ElectronicsType> electronicsTypes) {
		getPersistence().cacheResult(electronicsTypes);
	}

	/**
	 * Creates a new electronics type with the primary key. Does not add the electronics type to the database.
	 *
	 * @param electronicsTypeId the primary key for the new electronics type
	 * @return the new electronics type
	 */
	public static ElectronicsType create(long electronicsTypeId) {
		return getPersistence().create(electronicsTypeId);
	}

	/**
	 * Removes the electronics type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param electronicsTypeId the primary key of the electronics type
	 * @return the electronics type that was removed
	 * @throws NoSuchElectronicsTypeException if a electronics type with the primary key could not be found
	 */
	public static ElectronicsType remove(long electronicsTypeId)
		throws shop.exception.NoSuchElectronicsTypeException {

		return getPersistence().remove(electronicsTypeId);
	}

	public static ElectronicsType updateImpl(ElectronicsType electronicsType) {
		return getPersistence().updateImpl(electronicsType);
	}

	/**
	 * Returns the electronics type with the primary key or throws a <code>NoSuchElectronicsTypeException</code> if it could not be found.
	 *
	 * @param electronicsTypeId the primary key of the electronics type
	 * @return the electronics type
	 * @throws NoSuchElectronicsTypeException if a electronics type with the primary key could not be found
	 */
	public static ElectronicsType findByPrimaryKey(long electronicsTypeId)
		throws shop.exception.NoSuchElectronicsTypeException {

		return getPersistence().findByPrimaryKey(electronicsTypeId);
	}

	/**
	 * Returns the electronics type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param electronicsTypeId the primary key of the electronics type
	 * @return the electronics type, or <code>null</code> if a electronics type with the primary key could not be found
	 */
	public static ElectronicsType fetchByPrimaryKey(long electronicsTypeId) {
		return getPersistence().fetchByPrimaryKey(electronicsTypeId);
	}

	/**
	 * Returns all the electronics types.
	 *
	 * @return the electronics types
	 */
	public static List<ElectronicsType> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the electronics types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ElectronicsTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of electronics types
	 * @param end the upper bound of the range of electronics types (not inclusive)
	 * @return the range of electronics types
	 */
	public static List<ElectronicsType> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the electronics types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ElectronicsTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of electronics types
	 * @param end the upper bound of the range of electronics types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of electronics types
	 */
	public static List<ElectronicsType> findAll(
		int start, int end,
		OrderByComparator<ElectronicsType> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the electronics types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ElectronicsTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of electronics types
	 * @param end the upper bound of the range of electronics types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of electronics types
	 */
	public static List<ElectronicsType> findAll(
		int start, int end,
		OrderByComparator<ElectronicsType> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the electronics types from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of electronics types.
	 *
	 * @return the number of electronics types
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ElectronicsTypePersistence getPersistence() {
		return _persistence;
	}

	private static volatile ElectronicsTypePersistence _persistence;

}