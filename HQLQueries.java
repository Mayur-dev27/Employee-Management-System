package hqlclauses;

import beans.Employee;
import beans.EmployeeName;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

public class HQLQueries {
    private SessionFactory sessionFactory;

    public HQLQueries(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Object[]> getEmployeeNameAndSalaryByEmployeeId(int employeeId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT emp.employeeName.firstName, emp.employeeName.lastName, sal.basicSalary " +
                    "FROM Employee emp " +
                    "JOIN emp.employeeName empName " +
                    "JOIN emp.employeeSalary sal " +
                    "WHERE emp.employeeId = :employeeId";
            Query<Object[]> query = session.createQuery(hql);
            query.setParameter("employeeId", employeeId);
            return query.list();
        }
    }

    public List<Object[]> getEmployeesNameAndSalaryWithSalaryGreaterThan(double amount) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT emp.employeeName.firstName, emp.employeeName.lastName, sal.basicSalary " +
                    "FROM Employee emp " +
                    "JOIN emp.employeeSalary sal " +
                    "WHERE sal.basicSalary > :amount";
            Query<Object[]> query = session.createQuery(hql);
            query.setParameter("amount", amount);
            return query.list();
        }
    }

    public void updateSalaryByEmployeeId(int employeeId, double newSalary) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "UPDATE EmployeeSalary es SET es.basicSalary = :newSalary WHERE es.employee.employeeId = :employeeId";
            Query query = session.createQuery(hql);
            query.setParameter("newSalary", newSalary);
            query.setParameter("employeeId", employeeId);
            int updatedEntities = query.executeUpdate();
            transaction.commit();
            System.out.println("Number of entities updated: " + updatedEntities);
        }
    }


    public void deleteEmployeeResourcesByEmployeeId(int employeeId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "DELETE FROM EmployeeResources WHERE employeeId = :employeeId";
            Query query = session.createQuery(hql);
            query.setParameter("employeeId", employeeId);
            query.executeUpdate();
            transaction.commit();
        }
    }
    public List<Employee> getEmployeesOrderedBySalary() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Employee e ORDER BY e.employeeSalary.basicSalary DESC";
            Query<Employee> query = session.createQuery(hql, Employee.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // Return an empty list if an exception occurs
        }
    }

}
