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

package shop.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import shop.exception.NoSuchPurchaseException;

import shop.model.Purchase;

import shop.service.PurchaseLocalServiceUtil;
import shop.service.persistence.PurchasePersistence;
import shop.service.persistence.PurchaseUtil;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class PurchasePersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED, "shop.service"));

	@Before
	public void setUp() {
		_persistence = PurchaseUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Purchase> iterator = _purchases.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Purchase purchase = _persistence.create(pk);

		Assert.assertNotNull(purchase);

		Assert.assertEquals(purchase.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Purchase newPurchase = addPurchase();

		_persistence.remove(newPurchase);

		Purchase existingPurchase = _persistence.fetchByPrimaryKey(
			newPurchase.getPrimaryKey());

		Assert.assertNull(existingPurchase);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addPurchase();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Purchase newPurchase = _persistence.create(pk);

		newPurchase.setElectronicsId(RandomTestUtil.nextLong());

		newPurchase.setEmployeeId(RandomTestUtil.nextLong());

		newPurchase.setPurchaseDate(RandomTestUtil.nextDate());

		newPurchase.setPurchaseTypeId(RandomTestUtil.nextLong());

		_purchases.add(_persistence.update(newPurchase));

		Purchase existingPurchase = _persistence.findByPrimaryKey(
			newPurchase.getPrimaryKey());

		Assert.assertEquals(
			existingPurchase.getPurchaseId(), newPurchase.getPurchaseId());
		Assert.assertEquals(
			existingPurchase.getElectronicsId(),
			newPurchase.getElectronicsId());
		Assert.assertEquals(
			existingPurchase.getEmployeeId(), newPurchase.getEmployeeId());
		Assert.assertEquals(
			Time.getShortTimestamp(existingPurchase.getPurchaseDate()),
			Time.getShortTimestamp(newPurchase.getPurchaseDate()));
		Assert.assertEquals(
			existingPurchase.getPurchaseTypeId(),
			newPurchase.getPurchaseTypeId());
	}

	@Test
	public void testCountByDate() throws Exception {
		_persistence.countByDate(RandomTestUtil.nextDate());

		_persistence.countByDate(RandomTestUtil.nextDate());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Purchase newPurchase = addPurchase();

		Purchase existingPurchase = _persistence.findByPrimaryKey(
			newPurchase.getPrimaryKey());

		Assert.assertEquals(existingPurchase, newPurchase);
	}

	@Test(expected = NoSuchPurchaseException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<Purchase> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"shop_Purchase", "purchaseId", true, "electronicsId", true,
			"employeeId", true, "purchaseDate", true, "purchaseTypeId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Purchase newPurchase = addPurchase();

		Purchase existingPurchase = _persistence.fetchByPrimaryKey(
			newPurchase.getPrimaryKey());

		Assert.assertEquals(existingPurchase, newPurchase);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Purchase missingPurchase = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingPurchase);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		Purchase newPurchase1 = addPurchase();
		Purchase newPurchase2 = addPurchase();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPurchase1.getPrimaryKey());
		primaryKeys.add(newPurchase2.getPrimaryKey());

		Map<Serializable, Purchase> purchases = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(2, purchases.size());
		Assert.assertEquals(
			newPurchase1, purchases.get(newPurchase1.getPrimaryKey()));
		Assert.assertEquals(
			newPurchase2, purchases.get(newPurchase2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Purchase> purchases = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(purchases.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		Purchase newPurchase = addPurchase();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPurchase.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Purchase> purchases = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, purchases.size());
		Assert.assertEquals(
			newPurchase, purchases.get(newPurchase.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Purchase> purchases = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(purchases.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		Purchase newPurchase = addPurchase();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPurchase.getPrimaryKey());

		Map<Serializable, Purchase> purchases = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, purchases.size());
		Assert.assertEquals(
			newPurchase, purchases.get(newPurchase.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			PurchaseLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Purchase>() {

				@Override
				public void performAction(Purchase purchase) {
					Assert.assertNotNull(purchase);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		Purchase newPurchase = addPurchase();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Purchase.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"purchaseId", newPurchase.getPurchaseId()));

		List<Purchase> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Purchase existingPurchase = result.get(0);

		Assert.assertEquals(existingPurchase, newPurchase);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Purchase.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"purchaseId", RandomTestUtil.nextLong()));

		List<Purchase> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		Purchase newPurchase = addPurchase();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Purchase.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("purchaseId"));

		Object newPurchaseId = newPurchase.getPurchaseId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"purchaseId", new Object[] {newPurchaseId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPurchaseId = result.get(0);

		Assert.assertEquals(existingPurchaseId, newPurchaseId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Purchase.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("purchaseId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"purchaseId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected Purchase addPurchase() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Purchase purchase = _persistence.create(pk);

		purchase.setElectronicsId(RandomTestUtil.nextLong());

		purchase.setEmployeeId(RandomTestUtil.nextLong());

		purchase.setPurchaseDate(RandomTestUtil.nextDate());

		purchase.setPurchaseTypeId(RandomTestUtil.nextLong());

		_purchases.add(_persistence.update(purchase));

		return purchase;
	}

	private List<Purchase> _purchases = new ArrayList<Purchase>();
	private PurchasePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}