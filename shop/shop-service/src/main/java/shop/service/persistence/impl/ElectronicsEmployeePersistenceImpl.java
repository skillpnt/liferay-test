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

package shop.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import shop.exception.NoSuchElectronicsEmployeeException;

import shop.model.ElectronicsEmployee;
import shop.model.impl.ElectronicsEmployeeImpl;
import shop.model.impl.ElectronicsEmployeeModelImpl;

import shop.service.persistence.ElectronicsEmployeePK;
import shop.service.persistence.ElectronicsEmployeePersistence;
import shop.service.persistence.ElectronicsEmployeeUtil;
import shop.service.persistence.impl.constants.shopPersistenceConstants;

/**
 * The persistence implementation for the electronics employee service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ElectronicsEmployeePersistence.class)
public class ElectronicsEmployeePersistenceImpl
	extends BasePersistenceImpl<ElectronicsEmployee>
	implements ElectronicsEmployeePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ElectronicsEmployeeUtil</code> to access the electronics employee persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ElectronicsEmployeeImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ElectronicsEmployeePersistenceImpl() {
		setModelClass(ElectronicsEmployee.class);

		setModelImplClass(ElectronicsEmployeeImpl.class);
		setModelPKClass(ElectronicsEmployeePK.class);
	}

	/**
	 * Caches the electronics employee in the entity cache if it is enabled.
	 *
	 * @param electronicsEmployee the electronics employee
	 */
	@Override
	public void cacheResult(ElectronicsEmployee electronicsEmployee) {
		entityCache.putResult(
			ElectronicsEmployeeImpl.class, electronicsEmployee.getPrimaryKey(),
			electronicsEmployee);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the electronics employees in the entity cache if it is enabled.
	 *
	 * @param electronicsEmployees the electronics employees
	 */
	@Override
	public void cacheResult(List<ElectronicsEmployee> electronicsEmployees) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (electronicsEmployees.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ElectronicsEmployee electronicsEmployee : electronicsEmployees) {
			if (entityCache.getResult(
					ElectronicsEmployeeImpl.class,
					electronicsEmployee.getPrimaryKey()) == null) {

				cacheResult(electronicsEmployee);
			}
		}
	}

	/**
	 * Clears the cache for all electronics employees.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ElectronicsEmployeeImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the electronics employee.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ElectronicsEmployee electronicsEmployee) {
		entityCache.removeResult(
			ElectronicsEmployeeImpl.class, electronicsEmployee);
	}

	@Override
	public void clearCache(List<ElectronicsEmployee> electronicsEmployees) {
		for (ElectronicsEmployee electronicsEmployee : electronicsEmployees) {
			entityCache.removeResult(
				ElectronicsEmployeeImpl.class, electronicsEmployee);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ElectronicsEmployeeImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new electronics employee with the primary key. Does not add the electronics employee to the database.
	 *
	 * @param electronicsEmployeePK the primary key for the new electronics employee
	 * @return the new electronics employee
	 */
	@Override
	public ElectronicsEmployee create(
		ElectronicsEmployeePK electronicsEmployeePK) {

		ElectronicsEmployee electronicsEmployee = new ElectronicsEmployeeImpl();

		electronicsEmployee.setNew(true);
		electronicsEmployee.setPrimaryKey(electronicsEmployeePK);

		return electronicsEmployee;
	}

	/**
	 * Removes the electronics employee with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param electronicsEmployeePK the primary key of the electronics employee
	 * @return the electronics employee that was removed
	 * @throws NoSuchElectronicsEmployeeException if a electronics employee with the primary key could not be found
	 */
	@Override
	public ElectronicsEmployee remove(
			ElectronicsEmployeePK electronicsEmployeePK)
		throws NoSuchElectronicsEmployeeException {

		return remove((Serializable)electronicsEmployeePK);
	}

	/**
	 * Removes the electronics employee with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the electronics employee
	 * @return the electronics employee that was removed
	 * @throws NoSuchElectronicsEmployeeException if a electronics employee with the primary key could not be found
	 */
	@Override
	public ElectronicsEmployee remove(Serializable primaryKey)
		throws NoSuchElectronicsEmployeeException {

		Session session = null;

		try {
			session = openSession();

			ElectronicsEmployee electronicsEmployee =
				(ElectronicsEmployee)session.get(
					ElectronicsEmployeeImpl.class, primaryKey);

			if (electronicsEmployee == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchElectronicsEmployeeException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(electronicsEmployee);
		}
		catch (NoSuchElectronicsEmployeeException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected ElectronicsEmployee removeImpl(
		ElectronicsEmployee electronicsEmployee) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(electronicsEmployee)) {
				electronicsEmployee = (ElectronicsEmployee)session.get(
					ElectronicsEmployeeImpl.class,
					electronicsEmployee.getPrimaryKeyObj());
			}

			if (electronicsEmployee != null) {
				session.delete(electronicsEmployee);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (electronicsEmployee != null) {
			clearCache(electronicsEmployee);
		}

		return electronicsEmployee;
	}

	@Override
	public ElectronicsEmployee updateImpl(
		ElectronicsEmployee electronicsEmployee) {

		boolean isNew = electronicsEmployee.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(electronicsEmployee);
			}
			else {
				electronicsEmployee = (ElectronicsEmployee)session.merge(
					electronicsEmployee);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ElectronicsEmployeeImpl.class, electronicsEmployee, false, true);

		if (isNew) {
			electronicsEmployee.setNew(false);
		}

		electronicsEmployee.resetOriginalValues();

		return electronicsEmployee;
	}

	/**
	 * Returns the electronics employee with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the electronics employee
	 * @return the electronics employee
	 * @throws NoSuchElectronicsEmployeeException if a electronics employee with the primary key could not be found
	 */
	@Override
	public ElectronicsEmployee findByPrimaryKey(Serializable primaryKey)
		throws NoSuchElectronicsEmployeeException {

		ElectronicsEmployee electronicsEmployee = fetchByPrimaryKey(primaryKey);

		if (electronicsEmployee == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchElectronicsEmployeeException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return electronicsEmployee;
	}

	/**
	 * Returns the electronics employee with the primary key or throws a <code>NoSuchElectronicsEmployeeException</code> if it could not be found.
	 *
	 * @param electronicsEmployeePK the primary key of the electronics employee
	 * @return the electronics employee
	 * @throws NoSuchElectronicsEmployeeException if a electronics employee with the primary key could not be found
	 */
	@Override
	public ElectronicsEmployee findByPrimaryKey(
			ElectronicsEmployeePK electronicsEmployeePK)
		throws NoSuchElectronicsEmployeeException {

		return findByPrimaryKey((Serializable)electronicsEmployeePK);
	}

	/**
	 * Returns the electronics employee with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param electronicsEmployeePK the primary key of the electronics employee
	 * @return the electronics employee, or <code>null</code> if a electronics employee with the primary key could not be found
	 */
	@Override
	public ElectronicsEmployee fetchByPrimaryKey(
		ElectronicsEmployeePK electronicsEmployeePK) {

		return fetchByPrimaryKey((Serializable)electronicsEmployeePK);
	}

	/**
	 * Returns all the electronics employees.
	 *
	 * @return the electronics employees
	 */
	@Override
	public List<ElectronicsEmployee> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the electronics employees.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ElectronicsEmployeeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of electronics employees
	 * @param end the upper bound of the range of electronics employees (not inclusive)
	 * @return the range of electronics employees
	 */
	@Override
	public List<ElectronicsEmployee> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the electronics employees.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ElectronicsEmployeeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of electronics employees
	 * @param end the upper bound of the range of electronics employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of electronics employees
	 */
	@Override
	public List<ElectronicsEmployee> findAll(
		int start, int end,
		OrderByComparator<ElectronicsEmployee> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the electronics employees.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ElectronicsEmployeeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of electronics employees
	 * @param end the upper bound of the range of electronics employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of electronics employees
	 */
	@Override
	public List<ElectronicsEmployee> findAll(
		int start, int end,
		OrderByComparator<ElectronicsEmployee> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<ElectronicsEmployee> list = null;

		if (useFinderCache) {
			list = (List<ElectronicsEmployee>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ELECTRONICSEMPLOYEE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ELECTRONICSEMPLOYEE;

				sql = sql.concat(ElectronicsEmployeeModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ElectronicsEmployee>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the electronics employees from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ElectronicsEmployee electronicsEmployee : findAll()) {
			remove(electronicsEmployee);
		}
	}

	/**
	 * Returns the number of electronics employees.
	 *
	 * @return the number of electronics employees
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_ELECTRONICSEMPLOYEE);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getCompoundPKColumnNames() {
		return _compoundPKColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "electronicsEmployeePK";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ELECTRONICSEMPLOYEE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ElectronicsEmployeeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the electronics employee persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class,
			new ElectronicsEmployeeModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", ElectronicsEmployee.class.getName()));

		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = _createFinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_setElectronicsEmployeeUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setElectronicsEmployeeUtilPersistence(null);

		entityCache.removeCache(ElectronicsEmployeeImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	private void _setElectronicsEmployeeUtilPersistence(
		ElectronicsEmployeePersistence electronicsEmployeePersistence) {

		try {
			Field field = ElectronicsEmployeeUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, electronicsEmployeePersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = shopPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = shopPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = shopPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private BundleContext _bundleContext;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_ELECTRONICSEMPLOYEE =
		"SELECT electronicsEmployee FROM ElectronicsEmployee electronicsEmployee";

	private static final String _SQL_COUNT_ELECTRONICSEMPLOYEE =
		"SELECT COUNT(electronicsEmployee) FROM ElectronicsEmployee electronicsEmployee";

	private static final String _ORDER_BY_ENTITY_ALIAS = "electronicsEmployee.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ElectronicsEmployee exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ElectronicsEmployeePersistenceImpl.class);

	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"employeeId", "electronicsTypeId"});

	private FinderPath _createFinderPath(
		String cacheName, String methodName, String[] params,
		String[] columnNames, boolean baseModelResult) {

		FinderPath finderPath = new FinderPath(
			cacheName, methodName, params, columnNames, baseModelResult);

		if (!cacheName.equals(FINDER_CLASS_NAME_LIST_WITH_PAGINATION)) {
			_serviceRegistrations.add(
				_bundleContext.registerService(
					FinderPath.class, finderPath,
					MapUtil.singletonDictionary("cache.name", cacheName)));
		}

		return finderPath;
	}

	private Set<ServiceRegistration<FinderPath>> _serviceRegistrations =
		new HashSet<>();
	private ServiceRegistration<ArgumentsResolver>
		_argumentsResolverServiceRegistration;

	private static class ElectronicsEmployeeModelArgumentsResolver
		implements ArgumentsResolver {

		@Override
		public Object[] getArguments(
			FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
			boolean original) {

			String[] columnNames = finderPath.getColumnNames();

			if ((columnNames == null) || (columnNames.length == 0)) {
				if (baseModel.isNew()) {
					return new Object[0];
				}

				return null;
			}

			ElectronicsEmployeeModelImpl electronicsEmployeeModelImpl =
				(ElectronicsEmployeeModelImpl)baseModel;

			long columnBitmask =
				electronicsEmployeeModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(
					electronicsEmployeeModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						electronicsEmployeeModelImpl.getColumnBitmask(
							columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(
					electronicsEmployeeModelImpl, columnNames, original);
			}

			return null;
		}

		private static Object[] _getValue(
			ElectronicsEmployeeModelImpl electronicsEmployeeModelImpl,
			String[] columnNames, boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] =
						electronicsEmployeeModelImpl.getColumnOriginalValue(
							columnName);
				}
				else {
					arguments[i] = electronicsEmployeeModelImpl.getColumnValue(
						columnName);
				}
			}

			return arguments;
		}

		private static final Map<FinderPath, Long>
			_finderPathColumnBitmasksCache = new ConcurrentHashMap<>();

	}

}