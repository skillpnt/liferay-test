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

import shop.exception.NoSuchpurchaseTypeException;

import shop.model.purchaseType;

import shop.service.persistence.purchaseTypePersistence;
import shop.service.persistence.purchaseTypeUtil;
import shop.service.purchaseTypeLocalServiceUtil;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class purchaseTypePersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED, "shop.service"));

	@Before
	public void setUp() {
		_persistence = purchaseTypeUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<purchaseType> iterator = _purchaseTypes.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		purchaseType purchaseType = _persistence.create(pk);

		Assert.assertNotNull(purchaseType);

		Assert.assertEquals(purchaseType.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		purchaseType newpurchaseType = addpurchaseType();

		_persistence.remove(newpurchaseType);

		purchaseType existingpurchaseType = _persistence.fetchByPrimaryKey(
			newpurchaseType.getPrimaryKey());

		Assert.assertNull(existingpurchaseType);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addpurchaseType();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		purchaseType newpurchaseType = _persistence.create(pk);

		newpurchaseType.setName(RandomTestUtil.randomString());

		_purchaseTypes.add(_persistence.update(newpurchaseType));

		purchaseType existingpurchaseType = _persistence.findByPrimaryKey(
			newpurchaseType.getPrimaryKey());

		Assert.assertEquals(
			existingpurchaseType.getPurchaseTypeId(),
			newpurchaseType.getPurchaseTypeId());
		Assert.assertEquals(
			existingpurchaseType.getName(), newpurchaseType.getName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		purchaseType newpurchaseType = addpurchaseType();

		purchaseType existingpurchaseType = _persistence.findByPrimaryKey(
			newpurchaseType.getPrimaryKey());

		Assert.assertEquals(existingpurchaseType, newpurchaseType);
	}

	@Test(expected = NoSuchpurchaseTypeException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<purchaseType> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"shop_purchaseType", "purchaseTypeId", true, "name", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		purchaseType newpurchaseType = addpurchaseType();

		purchaseType existingpurchaseType = _persistence.fetchByPrimaryKey(
			newpurchaseType.getPrimaryKey());

		Assert.assertEquals(existingpurchaseType, newpurchaseType);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		purchaseType missingpurchaseType = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingpurchaseType);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		purchaseType newpurchaseType1 = addpurchaseType();
		purchaseType newpurchaseType2 = addpurchaseType();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newpurchaseType1.getPrimaryKey());
		primaryKeys.add(newpurchaseType2.getPrimaryKey());

		Map<Serializable, purchaseType> purchaseTypes =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, purchaseTypes.size());
		Assert.assertEquals(
			newpurchaseType1,
			purchaseTypes.get(newpurchaseType1.getPrimaryKey()));
		Assert.assertEquals(
			newpurchaseType2,
			purchaseTypes.get(newpurchaseType2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, purchaseType> purchaseTypes =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(purchaseTypes.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		purchaseType newpurchaseType = addpurchaseType();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newpurchaseType.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, purchaseType> purchaseTypes =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, purchaseTypes.size());
		Assert.assertEquals(
			newpurchaseType,
			purchaseTypes.get(newpurchaseType.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, purchaseType> purchaseTypes =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(purchaseTypes.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		purchaseType newpurchaseType = addpurchaseType();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newpurchaseType.getPrimaryKey());

		Map<Serializable, purchaseType> purchaseTypes =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, purchaseTypes.size());
		Assert.assertEquals(
			newpurchaseType,
			purchaseTypes.get(newpurchaseType.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			purchaseTypeLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<purchaseType>() {

				@Override
				public void performAction(purchaseType purchaseType) {
					Assert.assertNotNull(purchaseType);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		purchaseType newpurchaseType = addpurchaseType();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			purchaseType.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"purchaseTypeId", newpurchaseType.getPurchaseTypeId()));

		List<purchaseType> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		purchaseType existingpurchaseType = result.get(0);

		Assert.assertEquals(existingpurchaseType, newpurchaseType);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			purchaseType.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"purchaseTypeId", RandomTestUtil.nextLong()));

		List<purchaseType> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		purchaseType newpurchaseType = addpurchaseType();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			purchaseType.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("purchaseTypeId"));

		Object newPurchaseTypeId = newpurchaseType.getPurchaseTypeId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"purchaseTypeId", new Object[] {newPurchaseTypeId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPurchaseTypeId = result.get(0);

		Assert.assertEquals(existingPurchaseTypeId, newPurchaseTypeId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			purchaseType.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("purchaseTypeId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"purchaseTypeId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected purchaseType addpurchaseType() throws Exception {
		long pk = RandomTestUtil.nextLong();

		purchaseType purchaseType = _persistence.create(pk);

		purchaseType.setName(RandomTestUtil.randomString());

		_purchaseTypes.add(_persistence.update(purchaseType));

		return purchaseType;
	}

	private List<purchaseType> _purchaseTypes = new ArrayList<purchaseType>();
	private purchaseTypePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}