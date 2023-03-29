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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link shop.service.http.ElectronicsServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ElectronicsSoap implements Serializable {

	public static ElectronicsSoap toSoapModel(Electronics model) {
		ElectronicsSoap soapModel = new ElectronicsSoap();

		soapModel.setElectronicsId(model.getElectronicsId());
		soapModel.setName(model.getName());
		soapModel.setTypeId(model.getTypeId());
		soapModel.setPrice(model.getPrice());
		soapModel.setCount(model.getCount());
		soapModel.setInStock(model.isInStock());
		soapModel.setArchived(model.isArchived());
		soapModel.setDescription(model.getDescription());

		return soapModel;
	}

	public static ElectronicsSoap[] toSoapModels(Electronics[] models) {
		ElectronicsSoap[] soapModels = new ElectronicsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ElectronicsSoap[][] toSoapModels(Electronics[][] models) {
		ElectronicsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ElectronicsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ElectronicsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ElectronicsSoap[] toSoapModels(List<Electronics> models) {
		List<ElectronicsSoap> soapModels = new ArrayList<ElectronicsSoap>(
			models.size());

		for (Electronics model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ElectronicsSoap[soapModels.size()]);
	}

	public ElectronicsSoap() {
	}

	public long getPrimaryKey() {
		return _electronicsId;
	}

	public void setPrimaryKey(long pk) {
		setElectronicsId(pk);
	}

	public long getElectronicsId() {
		return _electronicsId;
	}

	public void setElectronicsId(long electronicsId) {
		_electronicsId = electronicsId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public long getTypeId() {
		return _typeId;
	}

	public void setTypeId(long typeId) {
		_typeId = typeId;
	}

	public long getPrice() {
		return _price;
	}

	public void setPrice(long price) {
		_price = price;
	}

	public int getCount() {
		return _count;
	}

	public void setCount(int count) {
		_count = count;
	}

	public boolean getInStock() {
		return _inStock;
	}

	public boolean isInStock() {
		return _inStock;
	}

	public void setInStock(boolean inStock) {
		_inStock = inStock;
	}

	public boolean getArchived() {
		return _archived;
	}

	public boolean isArchived() {
		return _archived;
	}

	public void setArchived(boolean archived) {
		_archived = archived;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	private long _electronicsId;
	private String _name;
	private long _typeId;
	private long _price;
	private int _count;
	private boolean _inStock;
	private boolean _archived;
	private String _description;

}