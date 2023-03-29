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

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import shop.model.Purchase;

/**
 * The persistence utility for the purchase service. This utility wraps <code>shop.service.persistence.impl.PurchasePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PurchasePersistence
 * @generated
 */
public class PurchaseUtil {

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
	public static void clearCache(Purchase purchase) {
		getPersistence().clearCache(purchase);
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
	public static Map<Serializable, Purchase> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Purchase> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Purchase> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Purchase> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Purchase> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Purchase update(Purchase purchase) {
		return getPersistence().update(purchase);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Purchase update(
		Purchase purchase, ServiceContext serviceContext) {

		return getPersistence().update(purchase, serviceContext);
	}

	/**
	 * Returns all the purchases where purchaseDate = &#63;.
	 *
	 * @param purchaseDate the purchase date
	 * @return the matching purchases
	 */
	public static List<Purchase> findByDate(Date purchaseDate) {
		return getPersistence().findByDate(purchaseDate);
	}

	/**
	 * Returns a range of all the purchases where purchaseDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param purchaseDate the purchase date
	 * @param start the lower bound of the range of purchases
	 * @param end the upper bound of the range of purchases (not inclusive)
	 * @return the range of matching purchases
	 */
	public static List<Purchase> findByDate(
		Date purchaseDate, int start, int end) {

		return getPersistence().findByDate(purchaseDate, start, end);
	}

	/**
	 * Returns an ordered range of all the purchases where purchaseDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param purchaseDate the purchase date
	 * @param start the lower bound of the range of purchases
	 * @param end the upper bound of the range of purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching purchases
	 */
	public static List<Purchase> findByDate(
		Date purchaseDate, int start, int end,
		OrderByComparator<Purchase> orderByComparator) {

		return getPersistence().findByDate(
			purchaseDate, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the purchases where purchaseDate = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param purchaseDate the purchase date
	 * @param start the lower bound of the range of purchases
	 * @param end the upper bound of the range of purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching purchases
	 */
	public static List<Purchase> findByDate(
		Date purchaseDate, int start, int end,
		OrderByComparator<Purchase> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByDate(
			purchaseDate, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first purchase in the ordered set where purchaseDate = &#63;.
	 *
	 * @param purchaseDate the purchase date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching purchase
	 * @throws NoSuchPurchaseException if a matching purchase could not be found
	 */
	public static Purchase findByDate_First(
			Date purchaseDate, OrderByComparator<Purchase> orderByComparator)
		throws shop.exception.NoSuchPurchaseException {

		return getPersistence().findByDate_First(
			purchaseDate, orderByComparator);
	}

	/**
	 * Returns the first purchase in the ordered set where purchaseDate = &#63;.
	 *
	 * @param purchaseDate the purchase date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching purchase, or <code>null</code> if a matching purchase could not be found
	 */
	public static Purchase fetchByDate_First(
		Date purchaseDate, OrderByComparator<Purchase> orderByComparator) {

		return getPersistence().fetchByDate_First(
			purchaseDate, orderByComparator);
	}

	/**
	 * Returns the last purchase in the ordered set where purchaseDate = &#63;.
	 *
	 * @param purchaseDate the purchase date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching purchase
	 * @throws NoSuchPurchaseException if a matching purchase could not be found
	 */
	public static Purchase findByDate_Last(
			Date purchaseDate, OrderByComparator<Purchase> orderByComparator)
		throws shop.exception.NoSuchPurchaseException {

		return getPersistence().findByDate_Last(
			purchaseDate, orderByComparator);
	}

	/**
	 * Returns the last purchase in the ordered set where purchaseDate = &#63;.
	 *
	 * @param purchaseDate the purchase date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching purchase, or <code>null</code> if a matching purchase could not be found
	 */
	public static Purchase fetchByDate_Last(
		Date purchaseDate, OrderByComparator<Purchase> orderByComparator) {

		return getPersistence().fetchByDate_Last(
			purchaseDate, orderByComparator);
	}

	/**
	 * Returns the purchases before and after the current purchase in the ordered set where purchaseDate = &#63;.
	 *
	 * @param purchaseId the primary key of the current purchase
	 * @param purchaseDate the purchase date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next purchase
	 * @throws NoSuchPurchaseException if a purchase with the primary key could not be found
	 */
	public static Purchase[] findByDate_PrevAndNext(
			long purchaseId, Date purchaseDate,
			OrderByComparator<Purchase> orderByComparator)
		throws shop.exception.NoSuchPurchaseException {

		return getPersistence().findByDate_PrevAndNext(
			purchaseId, purchaseDate, orderByComparator);
	}

	/**
	 * Removes all the purchases where purchaseDate = &#63; from the database.
	 *
	 * @param purchaseDate the purchase date
	 */
	public static void removeByDate(Date purchaseDate) {
		getPersistence().removeByDate(purchaseDate);
	}

	/**
	 * Returns the number of purchases where purchaseDate = &#63;.
	 *
	 * @param purchaseDate the purchase date
	 * @return the number of matching purchases
	 */
	public static int countByDate(Date purchaseDate) {
		return getPersistence().countByDate(purchaseDate);
	}

	/**
	 * Caches the purchase in the entity cache if it is enabled.
	 *
	 * @param purchase the purchase
	 */
	public static void cacheResult(Purchase purchase) {
		getPersistence().cacheResult(purchase);
	}

	/**
	 * Caches the purchases in the entity cache if it is enabled.
	 *
	 * @param purchases the purchases
	 */
	public static void cacheResult(List<Purchase> purchases) {
		getPersistence().cacheResult(purchases);
	}

	/**
	 * Creates a new purchase with the primary key. Does not add the purchase to the database.
	 *
	 * @param purchaseId the primary key for the new purchase
	 * @return the new purchase
	 */
	public static Purchase create(long purchaseId) {
		return getPersistence().create(purchaseId);
	}

	/**
	 * Removes the purchase with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param purchaseId the primary key of the purchase
	 * @return the purchase that was removed
	 * @throws NoSuchPurchaseException if a purchase with the primary key could not be found
	 */
	public static Purchase remove(long purchaseId)
		throws shop.exception.NoSuchPurchaseException {

		return getPersistence().remove(purchaseId);
	}

	public static Purchase updateImpl(Purchase purchase) {
		return getPersistence().updateImpl(purchase);
	}

	/**
	 * Returns the purchase with the primary key or throws a <code>NoSuchPurchaseException</code> if it could not be found.
	 *
	 * @param purchaseId the primary key of the purchase
	 * @return the purchase
	 * @throws NoSuchPurchaseException if a purchase with the primary key could not be found
	 */
	public static Purchase findByPrimaryKey(long purchaseId)
		throws shop.exception.NoSuchPurchaseException {

		return getPersistence().findByPrimaryKey(purchaseId);
	}

	/**
	 * Returns the purchase with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param purchaseId the primary key of the purchase
	 * @return the purchase, or <code>null</code> if a purchase with the primary key could not be found
	 */
	public static Purchase fetchByPrimaryKey(long purchaseId) {
		return getPersistence().fetchByPrimaryKey(purchaseId);
	}

	/**
	 * Returns all the purchases.
	 *
	 * @return the purchases
	 */
	public static List<Purchase> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the purchases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of purchases
	 * @param end the upper bound of the range of purchases (not inclusive)
	 * @return the range of purchases
	 */
	public static List<Purchase> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the purchases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of purchases
	 * @param end the upper bound of the range of purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of purchases
	 */
	public static List<Purchase> findAll(
		int start, int end, OrderByComparator<Purchase> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the purchases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PurchaseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of purchases
	 * @param end the upper bound of the range of purchases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of purchases
	 */
	public static List<Purchase> findAll(
		int start, int end, OrderByComparator<Purchase> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the purchases from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of purchases.
	 *
	 * @return the number of purchases
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static PurchasePersistence getPersistence() {
		return _persistence;
	}

	private static volatile PurchasePersistence _persistence;

}