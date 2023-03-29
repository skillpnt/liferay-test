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
 * This class is a wrapper for {@link Electronics}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Electronics
 * @generated
 */
public class ElectronicsWrapper
	extends BaseModelWrapper<Electronics>
	implements Electronics, ModelWrapper<Electronics> {

	public ElectronicsWrapper(Electronics electronics) {
		super(electronics);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("electronicsId", getElectronicsId());
		attributes.put("name", getName());
		attributes.put("typeId", getTypeId());
		attributes.put("price", getPrice());
		attributes.put("count", getCount());
		attributes.put("inStock", isInStock());
		attributes.put("archived", isArchived());
		attributes.put("description", getDescription());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long electronicsId = (Long)attributes.get("electronicsId");

		if (electronicsId != null) {
			setElectronicsId(electronicsId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Long typeId = (Long)attributes.get("typeId");

		if (typeId != null) {
			setTypeId(typeId);
		}

		Long price = (Long)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}

		Integer count = (Integer)attributes.get("count");

		if (count != null) {
			setCount(count);
		}

		Boolean inStock = (Boolean)attributes.get("inStock");

		if (inStock != null) {
			setInStock(inStock);
		}

		Boolean archived = (Boolean)attributes.get("archived");

		if (archived != null) {
			setArchived(archived);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}
	}

	/**
	 * Returns the archived of this electronics.
	 *
	 * @return the archived of this electronics
	 */
	@Override
	public boolean getArchived() {
		return model.getArchived();
	}

	/**
	 * Returns the count of this electronics.
	 *
	 * @return the count of this electronics
	 */
	@Override
	public int getCount() {
		return model.getCount();
	}

	/**
	 * Returns the description of this electronics.
	 *
	 * @return the description of this electronics
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the electronics ID of this electronics.
	 *
	 * @return the electronics ID of this electronics
	 */
	@Override
	public long getElectronicsId() {
		return model.getElectronicsId();
	}

	/**
	 * Returns the in stock of this electronics.
	 *
	 * @return the in stock of this electronics
	 */
	@Override
	public boolean getInStock() {
		return model.getInStock();
	}

	/**
	 * Returns the name of this electronics.
	 *
	 * @return the name of this electronics
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the price of this electronics.
	 *
	 * @return the price of this electronics
	 */
	@Override
	public long getPrice() {
		return model.getPrice();
	}

	/**
	 * Returns the primary key of this electronics.
	 *
	 * @return the primary key of this electronics
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the type ID of this electronics.
	 *
	 * @return the type ID of this electronics
	 */
	@Override
	public long getTypeId() {
		return model.getTypeId();
	}

	/**
	 * Returns <code>true</code> if this electronics is archived.
	 *
	 * @return <code>true</code> if this electronics is archived; <code>false</code> otherwise
	 */
	@Override
	public boolean isArchived() {
		return model.isArchived();
	}

	/**
	 * Returns <code>true</code> if this electronics is in stock.
	 *
	 * @return <code>true</code> if this electronics is in stock; <code>false</code> otherwise
	 */
	@Override
	public boolean isInStock() {
		return model.isInStock();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets whether this electronics is archived.
	 *
	 * @param archived the archived of this electronics
	 */
	@Override
	public void setArchived(boolean archived) {
		model.setArchived(archived);
	}

	/**
	 * Sets the count of this electronics.
	 *
	 * @param count the count of this electronics
	 */
	@Override
	public void setCount(int count) {
		model.setCount(count);
	}

	/**
	 * Sets the description of this electronics.
	 *
	 * @param description the description of this electronics
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the electronics ID of this electronics.
	 *
	 * @param electronicsId the electronics ID of this electronics
	 */
	@Override
	public void setElectronicsId(long electronicsId) {
		model.setElectronicsId(electronicsId);
	}

	/**
	 * Sets whether this electronics is in stock.
	 *
	 * @param inStock the in stock of this electronics
	 */
	@Override
	public void setInStock(boolean inStock) {
		model.setInStock(inStock);
	}

	/**
	 * Sets the name of this electronics.
	 *
	 * @param name the name of this electronics
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the price of this electronics.
	 *
	 * @param price the price of this electronics
	 */
	@Override
	public void setPrice(long price) {
		model.setPrice(price);
	}

	/**
	 * Sets the primary key of this electronics.
	 *
	 * @param primaryKey the primary key of this electronics
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the type ID of this electronics.
	 *
	 * @param typeId the type ID of this electronics
	 */
	@Override
	public void setTypeId(long typeId) {
		model.setTypeId(typeId);
	}

	@Override
	protected ElectronicsWrapper wrap(Electronics electronics) {
		return new ElectronicsWrapper(electronics);
	}

}