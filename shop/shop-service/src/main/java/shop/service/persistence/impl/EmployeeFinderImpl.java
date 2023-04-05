package shop.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import shop.model.Employee;
import shop.model.impl.EmployeeImpl;
import shop.service.persistence.EmployeeFinder;

import java.util.Collections;
import java.util.List;

@Component(service = EmployeeFinder.class)
public class EmployeeFinderImpl extends EmployeeFinderBaseImpl implements EmployeeFinder{

    @Reference
    private CustomSQL customSQL;

    public List<Employee> findByEarnings() {
        Session session = null;
        List<Employee> employeeList = Collections.emptyList();
        try {
            session = openSession();
            String sql = customSQL.get(getClass(), "shop.service.persistence.EmployeeFinder.findByEarnings");

            SQLQuery sqlQuery = session.createSQLQuery(sql);
            sqlQuery.setCacheable(false);
            sqlQuery.addEntity("Employee", EmployeeImpl.class);

            employeeList = (List<Employee>) sqlQuery.list();
            return employeeList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeSession(session);
        }
        return employeeList;
    }

    public List<Employee> findByPurchasesCount() {
        Session session = null;
        List<Employee> employeeList = Collections.emptyList();
        try {
            session = openSession();
            String sql = customSQL.get(getClass(), "shop.service.persistence.EmployeeFinder.findByPurchasesCount");

            SQLQuery sqlQuery = session.createSQLQuery(sql);
            sqlQuery.setCacheable(false);
            sqlQuery.addEntity("Employee", EmployeeImpl.class);

            employeeList = (List<Employee>) sqlQuery.list();
            return employeeList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeSession(session);
        }
        return employeeList;
    }

    public List<Employee> getEmployeesThatSellTvAndSmartphones() {
        Session session = null;
        List<Employee> employeeList = Collections.emptyList();
        try {
            session = openSession();
            String sql = customSQL.get(getClass(), "shop.service.persistence.EmployeeFinder.getEmployeesThatSellTvAndSmartphones");

            SQLQuery sqlQuery = session.createSQLQuery(sql);
            sqlQuery.setCacheable(false);
            sqlQuery.addEntity("Employee", EmployeeImpl.class);

            employeeList = (List<Employee>) sqlQuery.list();
            return employeeList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeSession(session);
        }
        return employeeList;
    }
}
