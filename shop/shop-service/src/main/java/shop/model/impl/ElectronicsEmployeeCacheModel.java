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

package shop.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import shop.model.ElectronicsEmployee;

import shop.service.persistence.ElectronicsEmployeePK;

/**
 * The cache model class for representing ElectronicsEmployee in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ElectronicsEmployeeCacheModel
	implements CacheModel<ElectronicsEmployee>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ElectronicsEmployeeCacheModel)) {
			return false;
		}

		ElectronicsEmployeeCacheModel electronicsEmployeeCacheModel =
			(ElectronicsEmployeeCacheModel)object;

		if (electronicsEmployeePK.equals(
				electronicsEmployeeCacheModel.electronicsEmployeePK)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, electronicsEmployeePK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{employeeId=");
		sb.append(employeeId);
		sb.append(", electronicsTypeId=");
		sb.append(electronicsTypeId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ElectronicsEmployee toEntityModel() {
		ElectronicsEmployeeImpl electronicsEmployeeImpl =
			new ElectronicsEmployeeImpl();

		electronicsEmployeeImpl.setEmployeeId(employeeId);
		electronicsEmployeeImpl.setElectronicsTypeId(electronicsTypeId);

		electronicsEmployeeImpl.resetOriginalValues();

		return electronicsEmployeeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		employeeId = objectInput.readLong();

		electronicsTypeId = objectInput.readLong();

		electronicsEmployeePK = new ElectronicsEmployeePK(
			employeeId, electronicsTypeId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(employeeId);

		objectOutput.writeLong(electronicsTypeId);
	}

	public long employeeId;
	public long electronicsTypeId;
	public transient ElectronicsEmployeePK electronicsEmployeePK;

}