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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

import shop.exception.NoSuchPurchaseException;

import shop.model.Purchase;

/**
 * The persistence interface for the purchase service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PurchaseUtil
 * @generated
 */
@ProviderType
public interface PurchasePersistence extends BasePersistence<Purchase> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PurchaseUtil} to access the purchase persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the purchases where purchaseDate = &#63;.
	 *
	 * @param purchaseDate the purchase date
	 * @return the matching purchases
	 */
	public java.util.List<Purchase> findByDate(Date purchaseDate);

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
	public java.util.List<Purchase> findByDate(
		Date purchaseDate, int start, int end);

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
	public java.util.List<Purchase> findByDate(
		Date purchaseDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Purchase>
			orderByComparator);

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
	public java.util.List<Purchase> findByDate(
		Date purchaseDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Purchase>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first purchase in the ordered set where purchaseDate = &#63;.
	 *
	 * @param purchaseDate the purchase date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching purchase
	 * @throws NoSuchPurchaseException if a matching purchase could not be found
	 */
	public Purchase findByDate_First(
			Date purchaseDate,
			com.liferay.portal.kernel.util.OrderByComparator<Purchase>
				orderByComparator)
		throws NoSuchPurchaseException;

	/**
	 * Returns the first purchase in the ordered set where purchaseDate = &#63;.
	 *
	 * @param purchaseDate the purchase date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching purchase, or <code>null</code> if a matching purchase could not be found
	 */
	public Purchase fetchByDate_First(
		Date purchaseDate,
		com.liferay.portal.kernel.util.OrderByComparator<Purchase>
			orderByComparator);

	/**
	 * Returns the last purchase in the ordered set where purchaseDate = &#63;.
	 *
	 * @param purchaseDate the purchase date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching purchase
	 * @throws NoSuchPurchaseException if a matching purchase could not be found
	 */
	public Purchase findByDate_Last(
			Date purchaseDate,
			com.liferay.portal.kernel.util.OrderByComparator<Purchase>
				orderByComparator)
		throws NoSuchPurchaseException;

	/**
	 * Returns the last purchase in the ordered set where purchaseDate = &#63;.
	 *
	 * @param purchaseDate the purchase date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching purchase, or <code>null</code> if a matching purchase could not be found
	 */
	public Purchase fetchByDate_Last(
		Date purchaseDate,
		com.liferay.portal.kernel.util.OrderByComparator<Purchase>
			orderByComparator);

	/**
	 * Returns the purchases before and after the current purchase in the ordered set where purchaseDate = &#63;.
	 *
	 * @param purchaseId the primary key of the current purchase
	 * @param purchaseDate the purchase date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next purchase
	 * @throws NoSuchPurchaseException if a purchase with the primary key could not be found
	 */
	public Purchase[] findByDate_PrevAndNext(
			long purchaseId, Date purchaseDate,
			com.liferay.portal.kernel.util.OrderByComparator<Purchase>
				orderByComparator)
		throws NoSuchPurchaseException;

	/**
	 * Removes all the purchases where purchaseDate = &#63; from the database.
	 *
	 * @param purchaseDate the purchase date
	 */
	public void removeByDate(Date purchaseDate);

	/**
	 * Returns the number of purchases where purchaseDate = &#63;.
	 *
	 * @param purchaseDate the purchase date
	 * @return the number of matching purchases
	 */
	public int countByDate(Date purchaseDate);

	/**
	 * Caches the purchase in the entity cache if it is enabled.
	 *
	 * @param purchase the purchase
	 */
	public void cacheResult(Purchase purchase);

	/**
	 * Caches the purchases in the entity cache if it is enabled.
	 *
	 * @param purchases the purchases
	 */
	public void cacheResult(java.util.List<Purchase> purchases);

	/**
	 * Creates a new purchase with the primary key. Does not add the purchase to the database.
	 *
	 * @param purchaseId the primary key for the new purchase
	 * @return the new purchase
	 */
	public Purchase create(long purchaseId);

	/**
	 * Removes the purchase with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param purchaseId the primary key of the purchase
	 * @return the purchase that was removed
	 * @throws NoSuchPurchaseException if a purchase with the primary key could not be found
	 */
	public Purchase remove(long purchaseId) throws NoSuchPurchaseException;

	public Purchase updateImpl(Purchase purchase);

	/**
	 * Returns the purchase with the primary key or throws a <code>NoSuchPurchaseException</code> if it could not be found.
	 *
	 * @param purchaseId the primary key of the purchase
	 * @return the purchase
	 * @throws NoSuchPurchaseException if a purchase with the primary key could not be found
	 */
	public Purchase findByPrimaryKey(long purchaseId)
		throws NoSuchPurchaseException;

	/**
	 * Returns the purchase with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param purchaseId the primary key of the purchase
	 * @return the purchase, or <code>null</code> if a purchase with the primary key could not be found
	 */
	public Purchase fetchByPrimaryKey(long purchaseId);

	/**
	 * Returns all the purchases.
	 *
	 * @return the purchases
	 */
	public java.util.List<Purchase> findAll();

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
	public java.util.List<Purchase> findAll(int start, int end);

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
	public java.util.List<Purchase> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Purchase>
			orderByComparator);

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
	public java.util.List<Purchase> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Purchase>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the purchases from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of purchases.
	 *
	 * @return the number of purchases
	 */
	public int countAll();

}