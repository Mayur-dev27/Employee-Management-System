package nativequeries;
import beans.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class NativeQueries {
    private final SessionFactory sessionFactory;

    public NativeQueries(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Object[]> getEmployeeDetailsByIdNative(int employeeId) {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT address, joiningDate FROM Employee WHERE employeeId = :employeeId";
            NativeQuery<Object[]> query = session.createNativeQuery(sql);
            query.setParameter("employeeId", employeeId);
            return query.list();
        }
    }

    public void updateSalaryByEmployeeIdNative(int employeeId, double newSalary) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String sql = "UPDATE EmployeeSalary SET basicSalary = :newSalary WHERE employeeId = :employeeId";
            NativeQuery query = session.createNativeQuery(sql);
            query.setParameter("newSalary", newSalary);
            query.setParameter("employeeId", employeeId);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void deleteEmployeeResourcesByEmployeeIdNative(int employeeId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String sql = "DELETE FROM EmployeeResources WHERE employeeId = :employeeId";
            NativeQuery query = session.createNativeQuery(sql);
            query.setParameter("employeeId", employeeId);
            query.executeUpdate();
            transaction.commit();
        }
    }
}
