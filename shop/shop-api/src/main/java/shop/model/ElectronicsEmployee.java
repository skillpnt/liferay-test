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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the ElectronicsEmployee service. Represents a row in the &quot;shop_ElectronicsEmployee&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ElectronicsEmployeeModel
 * @generated
 */
@ImplementationClassName("shop.model.impl.ElectronicsEmployeeImpl")
@ProviderType
public interface ElectronicsEmployee
	extends ElectronicsEmployeeModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>shop.model.impl.ElectronicsEmployeeImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ElectronicsEmployee, Long>
		EMPLOYEE_ID_ACCESSOR = new Accessor<ElectronicsEmployee, Long>() {

			@Override
			public Long get(ElectronicsEmployee electronicsEmployee) {
				return electronicsEmployee.getEmployeeId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ElectronicsEmployee> getTypeClass() {
				return ElectronicsEmployee.class;
			}

		};
	public static final Accessor<ElectronicsEmployee, Long>
		ELECTRONICS_TYPE_ID_ACCESSOR =
			new Accessor<ElectronicsEmployee, Long>() {

				@Override
				public Long get(ElectronicsEmployee electronicsEmployee) {
					return electronicsEmployee.getElectronicsTypeId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<ElectronicsEmployee> getTypeClass() {
					return ElectronicsEmployee.class;
				}

			};

}