package shop.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import shop.service.persistence.PurchaseFinder;

import java.util.List;

@Component(service = PurchaseFinder.class)
public class PurchaseFinderImpl extends PurchaseFinderBaseImpl implements PurchaseFinder {

    @Reference
    private CustomSQL customSQL;

    public long getLastMonthProfit() {
        Session session = null;
        long profit = 0;
        try {
            session = openSession();
            String sql = customSQL.get(getClass(), "shop.service.persistence.PurchaseFinder.getLastMonthProfit");

            SQLQuery sqlQuery = session.createSQLQuery(sql);
            sqlQuery.setCacheable(false);
            sqlQuery.addScalar("profit", Type.LONG);

            List<Object> result = sqlQuery.list();
            if (result != null && !result.isEmpty()) {
                profit = (Long) result.get(0);
            }

            return profit;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeSession(session);
        }
        return profit;
    }


    public int getLastMonthSoldTvCount() {
        Session session = null;
        int count = 0;
        try {
            session = openSession();
            String sql = customSQL.get(getClass(), "shop.service.persistence.PurchaseFinder.getLastMonthSoldTvCount");

            SQLQuery sqlQuery = session.createSQLQuery(sql);
            sqlQuery.setCacheable(false);
            sqlQuery.addScalar("tv_count", Type.INTEGER);

            List<Object> result = sqlQuery.list();
            if (result != null && !result.isEmpty()) {
                count = (Integer) result.get(0);
            }

            return count;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeSession(session);
        }
        return count;
    }
}
