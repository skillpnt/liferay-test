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

import shop.service.persistence.ElectronicsEmployeePK;

/**
 * This class is used by SOAP remote services, specifically {@link shop.service.http.ElectronicsEmployeeServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class ElectronicsEmployeeSoap implements Serializable {

	public static ElectronicsEmployeeSoap toSoapModel(
		ElectronicsEmployee model) {

		ElectronicsEmployeeSoap soapModel = new ElectronicsEmployeeSoap();

		soapModel.setEmployeeId(model.getEmployeeId());
		soapModel.setElectronicsTypeId(model.getElectronicsTypeId());

		return soapModel;
	}

	public static ElectronicsEmployeeSoap[] toSoapModels(
		ElectronicsEmployee[] models) {

		ElectronicsEmployeeSoap[] soapModels =
			new ElectronicsEmployeeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ElectronicsEmployeeSoap[][] toSoapModels(
		ElectronicsEmployee[][] models) {

		ElectronicsEmployeeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ElectronicsEmployeeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ElectronicsEmployeeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ElectronicsEmployeeSoap[] toSoapModels(
		List<ElectronicsEmployee> models) {

		List<ElectronicsEmployeeSoap> soapModels =
			new ArrayList<ElectronicsEmployeeSoap>(models.size());

		for (ElectronicsEmployee model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new ElectronicsEmployeeSoap[soapModels.size()]);
	}

	public ElectronicsEmployeeSoap() {
	}

	public ElectronicsEmployeePK getPrimaryKey() {
		return new ElectronicsEmployeePK(_employeeId, _electronicsTypeId);
	}

	public void setPrimaryKey(ElectronicsEmployeePK pk) {
		setEmployeeId(pk.employeeId);
		setElectronicsTypeId(pk.electronicsTypeId);
	}

	public long getEmployeeId() {
		return _employeeId;
	}

	public void setEmployeeId(long employeeId) {
		_employeeId = employeeId;
	}

	public long getElectronicsTypeId() {
		return _electronicsTypeId;
	}

	public void setElectronicsTypeId(long electronicsTypeId) {
		_electronicsTypeId = electronicsTypeId;
	}

	private long _employeeId;
	private long _electronicsTypeId;

}