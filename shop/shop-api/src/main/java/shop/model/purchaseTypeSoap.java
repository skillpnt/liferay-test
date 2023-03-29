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
 * This class is used by SOAP remote services, specifically {@link shop.service.http.purchaseTypeServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class purchaseTypeSoap implements Serializable {

	public static purchaseTypeSoap toSoapModel(purchaseType model) {
		purchaseTypeSoap soapModel = new purchaseTypeSoap();

		soapModel.setPurchaseTypeId(model.getPurchaseTypeId());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static purchaseTypeSoap[] toSoapModels(purchaseType[] models) {
		purchaseTypeSoap[] soapModels = new purchaseTypeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static purchaseTypeSoap[][] toSoapModels(purchaseType[][] models) {
		purchaseTypeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new purchaseTypeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new purchaseTypeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static purchaseTypeSoap[] toSoapModels(List<purchaseType> models) {
		List<purchaseTypeSoap> soapModels = new ArrayList<purchaseTypeSoap>(
			models.size());

		for (purchaseType model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new purchaseTypeSoap[soapModels.size()]);
	}

	public purchaseTypeSoap() {
	}

	public long getPrimaryKey() {
		return _purchaseTypeId;
	}

	public void setPrimaryKey(long pk) {
		setPurchaseTypeId(pk);
	}

	public long getPurchaseTypeId() {
		return _purchaseTypeId;
	}

	public void setPurchaseTypeId(long purchaseTypeId) {
		_purchaseTypeId = purchaseTypeId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _purchaseTypeId;
	private String _name;

}