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

package shop.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ElectronicsTypeService}.
 *
 * @author Brian Wing Shun Chan
 * @see ElectronicsTypeService
 * @generated
 */
public class ElectronicsTypeServiceWrapper
	implements ElectronicsTypeService, ServiceWrapper<ElectronicsTypeService> {

	public ElectronicsTypeServiceWrapper(
		ElectronicsTypeService electronicsTypeService) {

		_electronicsTypeService = electronicsTypeService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _electronicsTypeService.getOSGiServiceIdentifier();
	}

	@Override
	public ElectronicsTypeService getWrappedService() {
		return _electronicsTypeService;
	}

	@Override
	public void setWrappedService(
		ElectronicsTypeService electronicsTypeService) {

		_electronicsTypeService = electronicsTypeService;
	}

	private ElectronicsTypeService _electronicsTypeService;

}