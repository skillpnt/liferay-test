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

package shop.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ElectronicsEmployee}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ElectronicsEmployee
 * @generated
 */
public class ElectronicsEmployeeWrapper
	extends BaseModelWrapper<ElectronicsEmployee>
	implements ElectronicsEmployee, ModelWrapper<ElectronicsEmployee> {

	public ElectronicsEmployeeWrapper(ElectronicsEmployee electronicsEmployee) {
		super(electronicsEmployee);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("employeeId", getEmployeeId());
		attributes.put("electronicsTypeId", getElectronicsTypeId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long employeeId = (Long)attributes.get("employeeId");

		if (employeeId != null) {
			setEmployeeId(employeeId);
		}

		Long electronicsTypeId = (Long)attributes.get("electronicsTypeId");

		if (electronicsTypeId != null) {
			setElectronicsTypeId(electronicsTypeId);
		}
	}

	/**
	 * Returns the electronics type ID of this electronics employee.
	 *
	 * @return the electronics type ID of this electronics employee
	 */
	@Override
	public long getElectronicsTypeId() {
		return model.getElectronicsTypeId();
	}

	/**
	 * Returns the employee ID of this electronics employee.
	 *
	 * @return the employee ID of this electronics employee
	 */
	@Override
	public long getEmployeeId() {
		return model.getEmployeeId();
	}

	/**
	 * Returns the primary key of this electronics employee.
	 *
	 * @return the primary key of this electronics employee
	 */
	@Override
	public shop.service.persistence.ElectronicsEmployeePK getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the electronics type ID of this electronics employee.
	 *
	 * @param electronicsTypeId the electronics type ID of this electronics employee
	 */
	@Override
	public void setElectronicsTypeId(long electronicsTypeId) {
		model.setElectronicsTypeId(electronicsTypeId);
	}

	/**
	 * Sets the employee ID of this electronics employee.
	 *
	 * @param employeeId the employee ID of this electronics employee
	 */
	@Override
	public void setEmployeeId(long employeeId) {
		model.setEmployeeId(employeeId);
	}

	/**
	 * Sets the primary key of this electronics employee.
	 *
	 * @param primaryKey the primary key of this electronics employee
	 */
	@Override
	public void setPrimaryKey(
		shop.service.persistence.ElectronicsEmployeePK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected ElectronicsEmployeeWrapper wrap(
		ElectronicsEmployee electronicsEmployee) {

		return new ElectronicsEmployeeWrapper(electronicsEmployee);
	}

}