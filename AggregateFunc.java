package aggregate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class AggregateFunc {
    private SessionFactory sessionFactory;

    public AggregateFunc(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public double getTotalSalary() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT SUM(es.basicSalary) FROM EmployeeSalary es";
            Query<Double> query = session.createQuery(hql, Double.class);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public double getAverageSalary() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT AVG(es.basicSalary) FROM EmployeeSalary es";
            Query<Double> query = session.createQuery(hql, Double.class);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public long getEmployeeCount() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT COUNT(e) FROM Employee e";
            Query<Long> query = session.createQuery(hql, Long.class);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public double getMinSalary() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT MIN(es.basicSalary) FROM EmployeeSalary es";
            Query<Double> query = session.createQuery(hql, Double.class);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public double getMaxSalary() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT MAX(es.basicSalary) FROM EmployeeSalary es";
            Query<Double> query = session.createQuery(hql, Double.class);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}

