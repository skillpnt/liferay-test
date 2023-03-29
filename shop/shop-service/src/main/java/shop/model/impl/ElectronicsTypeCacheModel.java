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

import shop.model.ElectronicsType;

/**
 * The cache model class for representing ElectronicsType in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ElectronicsTypeCacheModel
	implements CacheModel<ElectronicsType>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ElectronicsTypeCacheModel)) {
			return false;
		}

		ElectronicsTypeCacheModel electronicsTypeCacheModel =
			(ElectronicsTypeCacheModel)object;

		if (electronicsTypeId == electronicsTypeCacheModel.electronicsTypeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, electronicsTypeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{electronicsTypeId=");
		sb.append(electronicsTypeId);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ElectronicsType toEntityModel() {
		ElectronicsTypeImpl electronicsTypeImpl = new ElectronicsTypeImpl();

		electronicsTypeImpl.setElectronicsTypeId(electronicsTypeId);

		if (name == null) {
			electronicsTypeImpl.setName("");
		}
		else {
			electronicsTypeImpl.setName(name);
		}

		electronicsTypeImpl.resetOriginalValues();

		return electronicsTypeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		electronicsTypeId = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(electronicsTypeId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long electronicsTypeId;
	public String name;

}