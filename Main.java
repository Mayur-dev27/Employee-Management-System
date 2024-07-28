package services;

import beans.Employee;
import beans.EmployeeName;
import beans.EmployeeResources;
import beans.EmployeeSalary;
import curdoperations.EmployeeCURDOprtn;
import hqlclauses.HQLQueries;
import aggregate.AggregateFunc;
import nativequeries.NativeQueries;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import resources.Helper;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static resources.Helper.printHqlClauses;

public class Main {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("resources/hibernate.cfg.xml");
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(EmployeeName.class);
        configuration.addAnnotatedClass(EmployeeSalary.class);
        configuration.addAnnotatedClass(EmployeeResources.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        EmployeeCURDOprtn employeeCURDOprtn = new EmployeeCURDOprtn(sessionFactory);
        HQLQueries hqlQueries = new HQLQueries(sessionFactory);
        AggregateFunc aggregateFunc = new AggregateFunc(sessionFactory);
        NativeQueries nativeQueries = new NativeQueries(sessionFactory);

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            Helper.printMainMenu();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    crudOperationsMenu(employeeCURDOprtn);
                    break;
                case 2:
                    hqlClausesMenu(new HQLQueries(sessionFactory), scanner);
                    break;
                case 3:
                    aggregateFunctionsMenu(aggregateFunc);
                    break;
                case 4:
                    nativeQueriesMenu(nativeQueries);
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);

        sessionFactory.close();
        scanner.close();
    }

    private static void crudOperationsMenu(EmployeeCURDOprtn employeeCURDOprtn) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            Helper.printCrudLibOptions();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Employee employee = employeeCURDOprtn.createEmpDetails();
                    if (employee != null) {
                        System.out.println("Employee created successfully!");
                    } else {
                        System.out.println("Failed to create employee.");
                    }
                    break;
                case 2:
                    System.out.println("Enter employee ID:");
                    int employeeId = scanner.nextInt();
                    Employee emp = employeeCURDOprtn.getEmployee(employeeId);
                    if (emp != null) {
                        System.out.println("Employee details:");
                        System.out.println("ID: " + emp.getEmployeeId());
                        System.out.println("Name: " + emp.getEmployeeName().getFirstName() + " " + emp.getEmployeeName().getLastName());
                        System.out.println("Joining Date: " + emp.getJoiningDate());
                        System.out.println();
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;

                case 3:
                    System.out.println("Enter employee ID to update:");
                    int updateEmployeeId = scanner.nextInt();
                    System.out.println("Enter updated Basic Salary: ");
                    double basicSalary = scanner.nextDouble();
                    System.out.println("Enter updated Dearness Allowance: ");
                    double dearnessAllowance = scanner.nextDouble();
                    System.out.println("Enter updated House Rent: ");
                    double houseRentAllow = scanner.nextDouble();
                    System.out.println("Enter updated Yearly Bonus: ");
                    double yearlyBonus = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    System.out.println("Enter updated Project Name Employee work on");
                    String projectName = scanner.nextLine();
                    System.out.println("Enter updated Address of Employee");
                    String address = scanner.nextLine();

                    employeeCURDOprtn.updateEmployee(updateEmployeeId, basicSalary, dearnessAllowance, houseRentAllow, yearlyBonus, projectName, address);
                    break;


                case 4:
                    System.out.println("Enter employee ID to delete:");
                    int deleteEmployeeId = scanner.nextInt();
                    Employee deleteEmployee = employeeCURDOprtn.getEmployee(deleteEmployeeId);
                    if (deleteEmployee != null) {
                        employeeCURDOprtn.deleteEmployee(deleteEmployee);
                        System.out.println("Employee deleted successfully!");
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);
        scanner.close();
    }
    private static void hqlClausesMenu(HQLQueries hqlQueries, Scanner scanner) {
        int choice;
        do {
            printHqlClauses();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter employee ID:");
                    int employeeId = scanner.nextInt();
                    List<Object[]> employeeDetails = hqlQueries.getEmployeeNameAndSalaryByEmployeeId(employeeId);
                    if (!employeeDetails.isEmpty()) {
                        for (Object[] row : employeeDetails) {
                            String firstName = (String) row[0];
                            String lastName = (String) row[1];
                            double salary = (Double) row[2];
                            System.out.println("Employee Name: " + firstName + " " + lastName);
                            System.out.println("Employee Salary: " + salary);
                            System.out.println();
                        }
                    } else {
                        System.out.println("No employee found with the given ID.");
                    }
                    break;
                case 2:
                    // Get Employee by Salary Greater than Amount
                    System.out.println("Enter minimum salary amount:");
                    double amount = scanner.nextDouble();
                    List<Object[]> employeesWithSalaryGreaterThan = hqlQueries.getEmployeesNameAndSalaryWithSalaryGreaterThan(amount);
                    if (!employeesWithSalaryGreaterThan.isEmpty()) {
                        for (Object[] row : employeesWithSalaryGreaterThan) {
                            String firstName = (String) row[0];
                            String lastName = (String) row[1];
                            double salary = (Double) row[2];
                            System.out.println("Employee Name: " + firstName + " " + lastName);
                            System.out.println("Employee Salary: " + salary);
                            System.out.println();
                        }
                    } else {
                        System.out.println("No employees found with salary greater than the given amount.");
                    }
                    break;
                case 3:
                    // Update Salary From EmployeeId
                    System.out.println("Enter employee ID to update:");
                    int updateEmployeeId = scanner.nextInt();
                    System.out.println("Enter new salary:");
                    double newSalary = scanner.nextDouble();
                    hqlQueries.updateSalaryByEmployeeId(updateEmployeeId, newSalary);
                    System.out.println("Salary updated successfully.");
                    break;

                case 4:
                    // Delete Employee by Resources
                    System.out.println("Enter employee ID to delete resources:");
                    int deleteEmployeeId = scanner.nextInt();
                    hqlQueries.deleteEmployeeResourcesByEmployeeId(deleteEmployeeId);
                    System.out.println("Employee resources deleted successfully.");
                    break;

                case 5:
                    // Get Employee Order by Salary Descending
                    try {
                        List<Employee> employeesOrderedBySalary = hqlQueries.getEmployeesOrderedBySalary();
                        if (!employeesOrderedBySalary.isEmpty()) {
                            employeesOrderedBySalary.forEach(employee -> System.out.println("Employee: " + employee));
                        } else {
                            System.out.println("No employees found.");
                        }
                    } catch (Exception e) {
                        System.out.println("An error occurred while fetching employees ordered by salary: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;

                case 6:
                    System.out.println("Returning to main menu...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 6);
    }

    private static void aggregateFunctionsMenu(AggregateFunc aggregateFunc) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            Helper.printAggregateFunc();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Get total salary
                    double totalSalary = aggregateFunc.getTotalSalary();
                    System.out.println("Total Salary: " + totalSalary);
                    break;
                case 2:
                    // Get average salary
                    double averageSalary = aggregateFunc.getAverageSalary();
                    System.out.println("Average Salary: " + averageSalary);
                    break;
                case 3:
                    // Get employee count
                    long employeeCount = aggregateFunc.getEmployeeCount();
                    System.out.println("Employee Count: " + employeeCount);
                    break;
                case 4:
                    // Get minimum salary
                    double minSalary = aggregateFunc.getMinSalary();
                    System.out.println("Minimum Salary: " + minSalary);
                    break;
                case 5:
                    // Get maximum salary
                    double maxSalary = aggregateFunc.getMaxSalary();
                    System.out.println("Maximum Salary: " + maxSalary);
                    break;
                case 6:
                    System.out.println("Returning to main menu...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 6);
        scanner.close();
    }
    private static void nativeQueriesMenu(NativeQueries nativeQueries) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            Helper.printNativeQueries();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Get Employee Details by Employee ID
                    System.out.println("Enter employee ID:");
                    int employeeId = scanner.nextInt();
                    List<Object[]> employeeDetails = nativeQueries.getEmployeeDetailsByIdNative(employeeId);
                    if (!employeeDetails.isEmpty()) {
                        Object[] details = employeeDetails.get(0); // Assuming only one row is returned
                        String address = (String) details[0];
                        Date joiningDate = (Date) details[1]; // Assuming joiningDate is of type java.util.Date
                        System.out.println("Employee Details:");
                        System.out.println("Address: " + address);
                        System.out.println("Joining Date: " + joiningDate);
                        System.out.println();
                    } else {
                        System.out.println("Employee not found for ID " + employeeId);
                    }
                    break;

                case 2:
                    System.out.println("Enter employee ID:");
                    int empId = scanner.nextInt();
                    System.out.println("Enter new salary:");
                    double newSalary = scanner.nextDouble();
                    nativeQueries.updateSalaryByEmployeeIdNative(empId, newSalary);
                    System.out.println("Salary updated successfully.");
                    break;
                case 3:
                    System.out.println("Enter employee ID:");
                    int deleteEmployeeId = scanner.nextInt();
                    nativeQueries.deleteEmployeeResourcesByEmployeeIdNative(deleteEmployeeId);
                    System.out.println("Employee resources deleted successfully.");
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);
        scanner.close();
    }

}
