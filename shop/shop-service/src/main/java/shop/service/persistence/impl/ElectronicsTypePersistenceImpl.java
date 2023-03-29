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

import shop.exception.NoSuchElectronicsTypeException;

import shop.model.ElectronicsType;
import shop.model.impl.ElectronicsTypeImpl;
import shop.model.impl.ElectronicsTypeModelImpl;

import shop.service.persistence.ElectronicsTypePersistence;
import shop.service.persistence.ElectronicsTypeUtil;
import shop.service.persistence.impl.constants.shopPersistenceConstants;

/**
 * The persistence implementation for the electronics type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = ElectronicsTypePersistence.class)
public class ElectronicsTypePersistenceImpl
	extends BasePersistenceImpl<ElectronicsType>
	implements ElectronicsTypePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>ElectronicsTypeUtil</code> to access the electronics type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		ElectronicsTypeImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public ElectronicsTypePersistenceImpl() {
		setModelClass(ElectronicsType.class);

		setModelImplClass(ElectronicsTypeImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the electronics type in the entity cache if it is enabled.
	 *
	 * @param electronicsType the electronics type
	 */
	@Override
	public void cacheResult(ElectronicsType electronicsType) {
		entityCache.putResult(
			ElectronicsTypeImpl.class, electronicsType.getPrimaryKey(),
			electronicsType);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the electronics types in the entity cache if it is enabled.
	 *
	 * @param electronicsTypes the electronics types
	 */
	@Override
	public void cacheResult(List<ElectronicsType> electronicsTypes) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (electronicsTypes.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (ElectronicsType electronicsType : electronicsTypes) {
			if (entityCache.getResult(
					ElectronicsTypeImpl.class,
					electronicsType.getPrimaryKey()) == null) {

				cacheResult(electronicsType);
			}
		}
	}

	/**
	 * Clears the cache for all electronics types.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ElectronicsTypeImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the electronics type.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ElectronicsType electronicsType) {
		entityCache.removeResult(ElectronicsTypeImpl.class, electronicsType);
	}

	@Override
	public void clearCache(List<ElectronicsType> electronicsTypes) {
		for (ElectronicsType electronicsType : electronicsTypes) {
			entityCache.removeResult(
				ElectronicsTypeImpl.class, electronicsType);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(ElectronicsTypeImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new electronics type with the primary key. Does not add the electronics type to the database.
	 *
	 * @param electronicsTypeId the primary key for the new electronics type
	 * @return the new electronics type
	 */
	@Override
	public ElectronicsType create(long electronicsTypeId) {
		ElectronicsType electronicsType = new ElectronicsTypeImpl();

		electronicsType.setNew(true);
		electronicsType.setPrimaryKey(electronicsTypeId);

		return electronicsType;
	}

	/**
	 * Removes the electronics type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param electronicsTypeId the primary key of the electronics type
	 * @return the electronics type that was removed
	 * @throws NoSuchElectronicsTypeException if a electronics type with the primary key could not be found
	 */
	@Override
	public ElectronicsType remove(long electronicsTypeId)
		throws NoSuchElectronicsTypeException {

		return remove((Serializable)electronicsTypeId);
	}

	/**
	 * Removes the electronics type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the electronics type
	 * @return the electronics type that was removed
	 * @throws NoSuchElectronicsTypeException if a electronics type with the primary key could not be found
	 */
	@Override
	public ElectronicsType remove(Serializable primaryKey)
		throws NoSuchElectronicsTypeException {

		Session session = null;

		try {
			session = openSession();

			ElectronicsType electronicsType = (ElectronicsType)session.get(
				ElectronicsTypeImpl.class, primaryKey);

			if (electronicsType == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchElectronicsTypeException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(electronicsType);
		}
		catch (NoSuchElectronicsTypeException noSuchEntityException) {
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
	protected ElectronicsType removeImpl(ElectronicsType electronicsType) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(electronicsType)) {
				electronicsType = (ElectronicsType)session.get(
					ElectronicsTypeImpl.class,
					electronicsType.getPrimaryKeyObj());
			}

			if (electronicsType != null) {
				session.delete(electronicsType);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (electronicsType != null) {
			clearCache(electronicsType);
		}

		return electronicsType;
	}

	@Override
	public ElectronicsType updateImpl(ElectronicsType electronicsType) {
		boolean isNew = electronicsType.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(electronicsType);
			}
			else {
				electronicsType = (ElectronicsType)session.merge(
					electronicsType);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			ElectronicsTypeImpl.class, electronicsType, false, true);

		if (isNew) {
			electronicsType.setNew(false);
		}

		electronicsType.resetOriginalValues();

		return electronicsType;
	}

	/**
	 * Returns the electronics type with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the electronics type
	 * @return the electronics type
	 * @throws NoSuchElectronicsTypeException if a electronics type with the primary key could not be found
	 */
	@Override
	public ElectronicsType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchElectronicsTypeException {

		ElectronicsType electronicsType = fetchByPrimaryKey(primaryKey);

		if (electronicsType == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchElectronicsTypeException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return electronicsType;
	}

	/**
	 * Returns the electronics type with the primary key or throws a <code>NoSuchElectronicsTypeException</code> if it could not be found.
	 *
	 * @param electronicsTypeId the primary key of the electronics type
	 * @return the electronics type
	 * @throws NoSuchElectronicsTypeException if a electronics type with the primary key could not be found
	 */
	@Override
	public ElectronicsType findByPrimaryKey(long electronicsTypeId)
		throws NoSuchElectronicsTypeException {

		return findByPrimaryKey((Serializable)electronicsTypeId);
	}

	/**
	 * Returns the electronics type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param electronicsTypeId the primary key of the electronics type
	 * @return the electronics type, or <code>null</code> if a electronics type with the primary key could not be found
	 */
	@Override
	public ElectronicsType fetchByPrimaryKey(long electronicsTypeId) {
		return fetchByPrimaryKey((Serializable)electronicsTypeId);
	}

	/**
	 * Returns all the electronics types.
	 *
	 * @return the electronics types
	 */
	@Override
	public List<ElectronicsType> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the electronics types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ElectronicsTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of electronics types
	 * @param end the upper bound of the range of electronics types (not inclusive)
	 * @return the range of electronics types
	 */
	@Override
	public List<ElectronicsType> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the electronics types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ElectronicsTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of electronics types
	 * @param end the upper bound of the range of electronics types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of electronics types
	 */
	@Override
	public List<ElectronicsType> findAll(
		int start, int end,
		OrderByComparator<ElectronicsType> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the electronics types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ElectronicsTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of electronics types
	 * @param end the upper bound of the range of electronics types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of electronics types
	 */
	@Override
	public List<ElectronicsType> findAll(
		int start, int end,
		OrderByComparator<ElectronicsType> orderByComparator,
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

		List<ElectronicsType> list = null;

		if (useFinderCache) {
			list = (List<ElectronicsType>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ELECTRONICSTYPE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ELECTRONICSTYPE;

				sql = sql.concat(ElectronicsTypeModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<ElectronicsType>)QueryUtil.list(
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
	 * Removes all the electronics types from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ElectronicsType electronicsType : findAll()) {
			remove(electronicsType);
		}
	}

	/**
	 * Returns the number of electronics types.
	 *
	 * @return the number of electronics types
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_ELECTRONICSTYPE);

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
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "electronicsTypeId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ELECTRONICSTYPE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ElectronicsTypeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the electronics type persistence.
	 */
	@Activate
	public void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_argumentsResolverServiceRegistration = _bundleContext.registerService(
			ArgumentsResolver.class,
			new ElectronicsTypeModelArgumentsResolver(),
			MapUtil.singletonDictionary(
				"model.class.name", ElectronicsType.class.getName()));

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

		_setElectronicsTypeUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setElectronicsTypeUtilPersistence(null);

		entityCache.removeCache(ElectronicsTypeImpl.class.getName());

		_argumentsResolverServiceRegistration.unregister();

		for (ServiceRegistration<FinderPath> serviceRegistration :
				_serviceRegistrations) {

			serviceRegistration.unregister();
		}
	}

	private void _setElectronicsTypeUtilPersistence(
		ElectronicsTypePersistence electronicsTypePersistence) {

		try {
			Field field = ElectronicsTypeUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, electronicsTypePersistence);
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

	private static final String _SQL_SELECT_ELECTRONICSTYPE =
		"SELECT electronicsType FROM ElectronicsType electronicsType";

	private static final String _SQL_COUNT_ELECTRONICSTYPE =
		"SELECT COUNT(electronicsType) FROM ElectronicsType electronicsType";

	private static final String _ORDER_BY_ENTITY_ALIAS = "electronicsType.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No ElectronicsType exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		ElectronicsTypePersistenceImpl.class);

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

	private static class ElectronicsTypeModelArgumentsResolver
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

			ElectronicsTypeModelImpl electronicsTypeModelImpl =
				(ElectronicsTypeModelImpl)baseModel;

			long columnBitmask = electronicsTypeModelImpl.getColumnBitmask();

			if (!checkColumn || (columnBitmask == 0)) {
				return _getValue(
					electronicsTypeModelImpl, columnNames, original);
			}

			Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
				finderPath);

			if (finderPathColumnBitmask == null) {
				finderPathColumnBitmask = 0L;

				for (String columnName : columnNames) {
					finderPathColumnBitmask |=
						electronicsTypeModelImpl.getColumnBitmask(columnName);
				}

				_finderPathColumnBitmasksCache.put(
					finderPath, finderPathColumnBitmask);
			}

			if ((columnBitmask & finderPathColumnBitmask) != 0) {
				return _getValue(
					electronicsTypeModelImpl, columnNames, original);
			}

			return null;
		}

		private static Object[] _getValue(
			ElectronicsTypeModelImpl electronicsTypeModelImpl,
			String[] columnNames, boolean original) {

			Object[] arguments = new Object[columnNames.length];

			for (int i = 0; i < arguments.length; i++) {
				String columnName = columnNames[i];

				if (original) {
					arguments[i] =
						electronicsTypeModelImpl.getColumnOriginalValue(
							columnName);
				}
				else {
					arguments[i] = electronicsTypeModelImpl.getColumnValue(
						columnName);
				}
			}

			return arguments;
		}

		private static final Map<FinderPath, Long>
			_finderPathColumnBitmasksCache = new ConcurrentHashMap<>();

	}

}