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

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

import shop.exception.NoSuchElectronicsEmployeeException;

import shop.model.ElectronicsEmployee;

/**
 * The persistence interface for the electronics employee service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ElectronicsEmployeeUtil
 * @generated
 */
@ProviderType
public interface ElectronicsEmployeePersistence
	extends BasePersistence<ElectronicsEmployee> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ElectronicsEmployeeUtil} to access the electronics employee persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the electronics employee in the entity cache if it is enabled.
	 *
	 * @param electronicsEmployee the electronics employee
	 */
	public void cacheResult(ElectronicsEmployee electronicsEmployee);

	/**
	 * Caches the electronics employees in the entity cache if it is enabled.
	 *
	 * @param electronicsEmployees the electronics employees
	 */
	public void cacheResult(
		java.util.List<ElectronicsEmployee> electronicsEmployees);

	/**
	 * Creates a new electronics employee with the primary key. Does not add the electronics employee to the database.
	 *
	 * @param electronicsEmployeePK the primary key for the new electronics employee
	 * @return the new electronics employee
	 */
	public ElectronicsEmployee create(
		shop.service.persistence.ElectronicsEmployeePK electronicsEmployeePK);

	/**
	 * Removes the electronics employee with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param electronicsEmployeePK the primary key of the electronics employee
	 * @return the electronics employee that was removed
	 * @throws NoSuchElectronicsEmployeeException if a electronics employee with the primary key could not be found
	 */
	public ElectronicsEmployee remove(
			shop.service.persistence.ElectronicsEmployeePK
				electronicsEmployeePK)
		throws NoSuchElectronicsEmployeeException;

	public ElectronicsEmployee updateImpl(
		ElectronicsEmployee electronicsEmployee);

	/**
	 * Returns the electronics employee with the primary key or throws a <code>NoSuchElectronicsEmployeeException</code> if it could not be found.
	 *
	 * @param electronicsEmployeePK the primary key of the electronics employee
	 * @return the electronics employee
	 * @throws NoSuchElectronicsEmployeeException if a electronics employee with the primary key could not be found
	 */
	public ElectronicsEmployee findByPrimaryKey(
			shop.service.persistence.ElectronicsEmployeePK
				electronicsEmployeePK)
		throws NoSuchElectronicsEmployeeException;

	/**
	 * Returns the electronics employee with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param electronicsEmployeePK the primary key of the electronics employee
	 * @return the electronics employee, or <code>null</code> if a electronics employee with the primary key could not be found
	 */
	public ElectronicsEmployee fetchByPrimaryKey(
		shop.service.persistence.ElectronicsEmployeePK electronicsEmployeePK);

	/**
	 * Returns all the electronics employees.
	 *
	 * @return the electronics employees
	 */
	public java.util.List<ElectronicsEmployee> findAll();

	/**
	 * Returns a range of all the electronics employees.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ElectronicsEmployeeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of electronics employees
	 * @param end the upper bound of the range of electronics employees (not inclusive)
	 * @return the range of electronics employees
	 */
	public java.util.List<ElectronicsEmployee> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the electronics employees.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ElectronicsEmployeeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of electronics employees
	 * @param end the upper bound of the range of electronics employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of electronics employees
	 */
	public java.util.List<ElectronicsEmployee> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ElectronicsEmployee>
			orderByComparator);

	/**
	 * Returns an ordered range of all the electronics employees.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ElectronicsEmployeeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of electronics employees
	 * @param end the upper bound of the range of electronics employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of electronics employees
	 */
	public java.util.List<ElectronicsEmployee> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ElectronicsEmployee>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the electronics employees from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of electronics employees.
	 *
	 * @return the number of electronics employees
	 */
	public int countAll();

	public Set<String> getCompoundPKColumnNames();

}