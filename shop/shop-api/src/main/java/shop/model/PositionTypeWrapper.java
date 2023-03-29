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
 * This class is a wrapper for {@link PositionType}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PositionType
 * @generated
 */
public class PositionTypeWrapper
	extends BaseModelWrapper<PositionType>
	implements ModelWrapper<PositionType>, PositionType {

	public PositionTypeWrapper(PositionType positionType) {
		super(positionType);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("positionId", getPositionId());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long positionId = (Long)attributes.get("positionId");

		if (positionId != null) {
			setPositionId(positionId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	/**
	 * Returns the name of this position type.
	 *
	 * @return the name of this position type
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the position ID of this position type.
	 *
	 * @return the position ID of this position type
	 */
	@Override
	public long getPositionId() {
		return model.getPositionId();
	}

	/**
	 * Returns the primary key of this position type.
	 *
	 * @return the primary key of this position type
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the name of this position type.
	 *
	 * @param name the name of this position type
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the position ID of this position type.
	 *
	 * @param positionId the position ID of this position type
	 */
	@Override
	public void setPositionId(long positionId) {
		model.setPositionId(positionId);
	}

	/**
	 * Sets the primary key of this position type.
	 *
	 * @param primaryKey the primary key of this position type
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected PositionTypeWrapper wrap(PositionType positionType) {
		return new PositionTypeWrapper(positionType);
	}

}