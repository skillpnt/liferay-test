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
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
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

import shop.exception.NoSuchElectronicsEmployeeException;

import shop.model.ElectronicsEmployee;

import shop.service.ElectronicsEmployeeLocalServiceUtil;
import shop.service.persistence.ElectronicsEmployeePK;
import shop.service.persistence.ElectronicsEmployeePersistence;
import shop.service.persistence.ElectronicsEmployeeUtil;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class ElectronicsEmployeePersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED, "shop.service"));

	@Before
	public void setUp() {
		_persistence = ElectronicsEmployeeUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ElectronicsEmployee> iterator =
			_electronicsEmployees.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		ElectronicsEmployeePK pk = new ElectronicsEmployeePK(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		ElectronicsEmployee electronicsEmployee = _persistence.create(pk);

		Assert.assertNotNull(electronicsEmployee);

		Assert.assertEquals(electronicsEmployee.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ElectronicsEmployee newElectronicsEmployee = addElectronicsEmployee();

		_persistence.remove(newElectronicsEmployee);

		ElectronicsEmployee existingElectronicsEmployee =
			_persistence.fetchByPrimaryKey(
				newElectronicsEmployee.getPrimaryKey());

		Assert.assertNull(existingElectronicsEmployee);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addElectronicsEmployee();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		ElectronicsEmployeePK pk = new ElectronicsEmployeePK(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		ElectronicsEmployee newElectronicsEmployee = _persistence.create(pk);

		_electronicsEmployees.add(_persistence.update(newElectronicsEmployee));

		ElectronicsEmployee existingElectronicsEmployee =
			_persistence.findByPrimaryKey(
				newElectronicsEmployee.getPrimaryKey());

		Assert.assertEquals(
			existingElectronicsEmployee.getEmployeeId(),
			newElectronicsEmployee.getEmployeeId());
		Assert.assertEquals(
			existingElectronicsEmployee.getElectronicsTypeId(),
			newElectronicsEmployee.getElectronicsTypeId());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ElectronicsEmployee newElectronicsEmployee = addElectronicsEmployee();

		ElectronicsEmployee existingElectronicsEmployee =
			_persistence.findByPrimaryKey(
				newElectronicsEmployee.getPrimaryKey());

		Assert.assertEquals(
			existingElectronicsEmployee, newElectronicsEmployee);
	}

	@Test(expected = NoSuchElectronicsEmployeeException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		ElectronicsEmployeePK pk = new ElectronicsEmployeePK(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ElectronicsEmployee newElectronicsEmployee = addElectronicsEmployee();

		ElectronicsEmployee existingElectronicsEmployee =
			_persistence.fetchByPrimaryKey(
				newElectronicsEmployee.getPrimaryKey());

		Assert.assertEquals(
			existingElectronicsEmployee, newElectronicsEmployee);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		ElectronicsEmployeePK pk = new ElectronicsEmployeePK(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		ElectronicsEmployee missingElectronicsEmployee =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingElectronicsEmployee);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		ElectronicsEmployee newElectronicsEmployee1 = addElectronicsEmployee();
		ElectronicsEmployee newElectronicsEmployee2 = addElectronicsEmployee();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newElectronicsEmployee1.getPrimaryKey());
		primaryKeys.add(newElectronicsEmployee2.getPrimaryKey());

		Map<Serializable, ElectronicsEmployee> electronicsEmployees =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, electronicsEmployees.size());
		Assert.assertEquals(
			newElectronicsEmployee1,
			electronicsEmployees.get(newElectronicsEmployee1.getPrimaryKey()));
		Assert.assertEquals(
			newElectronicsEmployee2,
			electronicsEmployees.get(newElectronicsEmployee2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		ElectronicsEmployeePK pk1 = new ElectronicsEmployeePK(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		ElectronicsEmployeePK pk2 = new ElectronicsEmployeePK(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ElectronicsEmployee> electronicsEmployees =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(electronicsEmployees.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		ElectronicsEmployee newElectronicsEmployee = addElectronicsEmployee();

		ElectronicsEmployeePK pk = new ElectronicsEmployeePK(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newElectronicsEmployee.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ElectronicsEmployee> electronicsEmployees =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, electronicsEmployees.size());
		Assert.assertEquals(
			newElectronicsEmployee,
			electronicsEmployees.get(newElectronicsEmployee.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ElectronicsEmployee> electronicsEmployees =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(electronicsEmployees.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		ElectronicsEmployee newElectronicsEmployee = addElectronicsEmployee();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newElectronicsEmployee.getPrimaryKey());

		Map<Serializable, ElectronicsEmployee> electronicsEmployees =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, electronicsEmployees.size());
		Assert.assertEquals(
			newElectronicsEmployee,
			electronicsEmployees.get(newElectronicsEmployee.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			ElectronicsEmployeeLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<ElectronicsEmployee>() {

				@Override
				public void performAction(
					ElectronicsEmployee electronicsEmployee) {

					Assert.assertNotNull(electronicsEmployee);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		ElectronicsEmployee newElectronicsEmployee = addElectronicsEmployee();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			ElectronicsEmployee.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"id.employeeId", newElectronicsEmployee.getEmployeeId()));
		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"id.electronicsTypeId",
				newElectronicsEmployee.getElectronicsTypeId()));

		List<ElectronicsEmployee> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		ElectronicsEmployee existingElectronicsEmployee = result.get(0);

		Assert.assertEquals(
			existingElectronicsEmployee, newElectronicsEmployee);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			ElectronicsEmployee.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"id.employeeId", RandomTestUtil.nextLong()));
		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"id.electronicsTypeId", RandomTestUtil.nextLong()));

		List<ElectronicsEmployee> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		ElectronicsEmployee newElectronicsEmployee = addElectronicsEmployee();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			ElectronicsEmployee.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("id.employeeId"));

		Object newEmployeeId = newElectronicsEmployee.getEmployeeId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"id.employeeId", new Object[] {newEmployeeId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingEmployeeId = result.get(0);

		Assert.assertEquals(existingEmployeeId, newEmployeeId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			ElectronicsEmployee.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("id.employeeId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"id.employeeId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ElectronicsEmployee addElectronicsEmployee() throws Exception {
		ElectronicsEmployeePK pk = new ElectronicsEmployeePK(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		ElectronicsEmployee electronicsEmployee = _persistence.create(pk);

		_electronicsEmployees.add(_persistence.update(electronicsEmployee));

		return electronicsEmployee;
	}

	private List<ElectronicsEmployee> _electronicsEmployees =
		new ArrayList<ElectronicsEmployee>();
	private ElectronicsEmployeePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}