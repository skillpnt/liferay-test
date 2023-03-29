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
 * This class is used by SOAP remote services, specifically {@link shop.service.http.ElectronicsTypeServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ElectronicsTypeSoap implements Serializable {

	public static ElectronicsTypeSoap toSoapModel(ElectronicsType model) {
		ElectronicsTypeSoap soapModel = new ElectronicsTypeSoap();

		soapModel.setElectronicsTypeId(model.getElectronicsTypeId());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static ElectronicsTypeSoap[] toSoapModels(ElectronicsType[] models) {
		ElectronicsTypeSoap[] soapModels =
			new ElectronicsTypeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ElectronicsTypeSoap[][] toSoapModels(
		ElectronicsType[][] models) {

		ElectronicsTypeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ElectronicsTypeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ElectronicsTypeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ElectronicsTypeSoap[] toSoapModels(
		List<ElectronicsType> models) {

		List<ElectronicsTypeSoap> soapModels =
			new ArrayList<ElectronicsTypeSoap>(models.size());

		for (ElectronicsType model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ElectronicsTypeSoap[soapModels.size()]);
	}

	public ElectronicsTypeSoap() {
	}

	public long getPrimaryKey() {
		return _electronicsTypeId;
	}

	public void setPrimaryKey(long pk) {
		setElectronicsTypeId(pk);
	}

	public long getElectronicsTypeId() {
		return _electronicsTypeId;
	}

	public void setElectronicsTypeId(long electronicsTypeId) {
		_electronicsTypeId = electronicsTypeId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _electronicsTypeId;
	private String _name;

}