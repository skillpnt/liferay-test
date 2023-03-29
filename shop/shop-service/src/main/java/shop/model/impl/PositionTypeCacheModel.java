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

import shop.model.PositionType;

/**
 * The cache model class for representing PositionType in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PositionTypeCacheModel
	implements CacheModel<PositionType>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof PositionTypeCacheModel)) {
			return false;
		}

		PositionTypeCacheModel positionTypeCacheModel =
			(PositionTypeCacheModel)object;

		if (positionId == positionTypeCacheModel.positionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, positionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{positionId=");
		sb.append(positionId);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PositionType toEntityModel() {
		PositionTypeImpl positionTypeImpl = new PositionTypeImpl();

		positionTypeImpl.setPositionId(positionId);

		if (name == null) {
			positionTypeImpl.setName("");
		}
		else {
			positionTypeImpl.setName(name);
		}

		positionTypeImpl.resetOriginalValues();

		return positionTypeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		positionId = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(positionId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long positionId;
	public String name;

}