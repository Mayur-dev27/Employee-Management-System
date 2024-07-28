package curdoperations;

import beans.Employee;
import beans.EmployeeName;
import beans.EmployeeResources;
import beans.EmployeeSalary;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EmployeeCURDOprtn {
    private SessionFactory sessionFactory;

    public EmployeeCURDOprtn(SessionFactory sessionFactory){
            this.sessionFactory = sessionFactory;
//        Configuration configuration = new Configuration().configure();
//        sessionFactory = configuration.buildSessionFactory();
    }

    private final Scanner scanner = new Scanner(System.in);

    public Employee createEmpDetails() {
        Employee employee = new Employee();
        EmployeeName employeeName = new EmployeeName();
        EmployeeSalary employeeSalary = new EmployeeSalary();

        // Capture employee details
        System.out.println("Enter First Name of Employee: ");
        employeeName.setFirstName(scanner.nextLine());
        System.out.println("Enter Middle Name of Employee: ");
        employeeName.setMiddleName(scanner.nextLine());
        System.out.println("Enter Last Name of Employee: ");
        employeeName.setLastName(scanner.nextLine());

        // Save employee name first
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employeeName);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        System.out.println("Enter the Basic Salary: ");
        employeeSalary.setBasicSalary(scanner.nextDouble());
        System.out.println("Enter Dearness Allowance: ");
        employeeSalary.setDearnessAllowance(scanner.nextDouble());
        System.out.println("Enter House Rent: ");
        employeeSalary.setHouseRentAllow(scanner.nextDouble());
        System.out.println("Enter Yearly Bonus: ");
        employeeSalary.setYearlyBonus(scanner.nextDouble());
        scanner.nextLine(); // Consume the newline character

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employeeSalary);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        System.out.println("Enter Joining Date in (yy-MM-dd) format:");
        String joiningDateStr = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
        try {
            Date joiningDate = dateFormat.parse(joiningDateStr);
            employee.setJoiningDate(joiningDate);
        } catch (ParseException e) {
            System.out.println("Invalid Date Format!");
            return null;
        }
        System.out.println("Enter the Project Name Employee work on");
        employee.setProjectName(scanner.nextLine());
        System.out.println("Enter the Address of Employee");
        employee.setAddress(scanner.nextLine());

        // Set the employee name and salary
        employee.setEmployeeName(employeeName);
        employee.setEmployeeSalary(employeeSalary);

        List<EmployeeResources> resourcesList = new ArrayList<>();
        while (true) {
            System.out.println("Enter Resource Name (type 'exit' to finish): ");
            String resourceName = scanner.nextLine();
            if (resourceName.equalsIgnoreCase("exit")) {
                break;
            }

            EmployeeResources resources = new EmployeeResources();
            resources.setResourcesName(resourceName);

            // Prompt the user to enter the issue date
            System.out.println("Enter Issue Date in (yy-MM-dd) format:");
            String issueDateStr = scanner.nextLine();
            SimpleDateFormat dateF = new SimpleDateFormat("yy-MM-dd");
            try {
                Date issueDate = dateF.parse(issueDateStr);
                resources.setIssueDate(issueDate);
            } catch (ParseException e) {
                System.out.println("Invalid Date Format!");
                return null;
            }

            resources.setEmployee(employee); // Associate resource with the employee
            resourcesList.add(resources);
        }

// Associate resources list with the employee
        employee.setEmployeeResources(resourcesList);

// Save the employee along with resources to the database
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee); // This will also save associated resources due to cascade
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return employee;
    }

    public Employee getEmployee(int employeeId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Employee.class, employeeId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void updateEmployee(int employeeId, double basicSalary, double dearnessAllowance, double houseRentAllow, double yearlyBonus, String projectName, String address) {
        try (Session session = sessionFactory.openSession()) {
            Employee employee = session.get(Employee.class, employeeId);
            if (employee != null) {
                EmployeeSalary employeeSalary = employee.getEmployeeSalary();
                if (employeeSalary != null) {
                    // Update salary fields
                    employeeSalary.setBasicSalary(basicSalary);
                    employeeSalary.setDearnessAllowance(dearnessAllowance);
                    employeeSalary.setHouseRentAllow(houseRentAllow);
                    employeeSalary.setYearlyBonus(yearlyBonus);
                } else {
                    employeeSalary = new EmployeeSalary();
                    employeeSalary.setBasicSalary(basicSalary);
                    employeeSalary.setDearnessAllowance(dearnessAllowance);
                    employeeSalary.setHouseRentAllow(houseRentAllow);
                    employeeSalary.setYearlyBonus(yearlyBonus);
                    employee.setEmployeeSalary(employeeSalary);
                }
                employee.setProjectName(projectName);
                employee.setAddress(address);
                Transaction transaction = session.beginTransaction();
                session.update(employee);
                transaction.commit();
                System.out.println("Employee details updated successfully!");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteEmployee(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
