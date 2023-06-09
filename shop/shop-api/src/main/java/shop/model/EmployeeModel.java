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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the Employee service. Represents a row in the &quot;shop_Employee&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>shop.model.impl.EmployeeModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>shop.model.impl.EmployeeImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Employee
 * @generated
 */
@ProviderType
public interface EmployeeModel extends BaseModel<Employee> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a employee model instance should use the {@link Employee} interface instead.
	 */

	/**
	 * Returns the primary key of this employee.
	 *
	 * @return the primary key of this employee
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this employee.
	 *
	 * @param primaryKey the primary key of this employee
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the employee ID of this employee.
	 *
	 * @return the employee ID of this employee
	 */
	public long getEmployeeId();

	/**
	 * Sets the employee ID of this employee.
	 *
	 * @param employeeId the employee ID of this employee
	 */
	public void setEmployeeId(long employeeId);

	/**
	 * Returns the last name of this employee.
	 *
	 * @return the last name of this employee
	 */
	@AutoEscape
	public String getLastName();

	/**
	 * Sets the last name of this employee.
	 *
	 * @param lastName the last name of this employee
	 */
	public void setLastName(String lastName);

	/**
	 * Returns the first name of this employee.
	 *
	 * @return the first name of this employee
	 */
	@AutoEscape
	public String getFirstName();

	/**
	 * Sets the first name of this employee.
	 *
	 * @param firstName the first name of this employee
	 */
	public void setFirstName(String firstName);

	/**
	 * Returns the patronymic of this employee.
	 *
	 * @return the patronymic of this employee
	 */
	@AutoEscape
	public String getPatronymic();

	/**
	 * Sets the patronymic of this employee.
	 *
	 * @param patronymic the patronymic of this employee
	 */
	public void setPatronymic(String patronymic);

	/**
	 * Returns the birthdate of this employee.
	 *
	 * @return the birthdate of this employee
	 */
	public Date getBirthdate();

	/**
	 * Sets the birthdate of this employee.
	 *
	 * @param birthdate the birthdate of this employee
	 */
	public void setBirthdate(Date birthdate);

	/**
	 * Returns the position ID of this employee.
	 *
	 * @return the position ID of this employee
	 */
	public long getPositionId();

	/**
	 * Sets the position ID of this employee.
	 *
	 * @param positionId the position ID of this employee
	 */
	public void setPositionId(long positionId);

	/**
	 * Returns the gender of this employee.
	 *
	 * @return the gender of this employee
	 */
	public boolean getGender();

	/**
	 * Returns <code>true</code> if this employee is gender.
	 *
	 * @return <code>true</code> if this employee is gender; <code>false</code> otherwise
	 */
	public boolean isGender();

	/**
	 * Sets whether this employee is gender.
	 *
	 * @param gender the gender of this employee
	 */
	public void setGender(boolean gender);

}