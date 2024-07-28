package resources;


public class Helper {

    public static void printMainMenu() {
        System.out.println("1: CRUD Library Operations");
        System.out.println("2: HQL Clauses");
        System.out.println("3: Aggregate Functions");
        System.out.println("4: Native Queries");
        System.out.println("Enter Your Choice:");
    }

    public static void printCrudLibOptions() {
        System.out.println("1: Create Employee");
        System.out.println("2: Read Employee by EmployeeId");
        System.out.println("3: Update Employee");
        System.out.println("4: Delete Employee");
        System.out.println("5 :To Return in main menu");
        System.out.println("Enter Your choice:");
    }

    public static void printHqlClauses() {
        System.out.println("1: Get Employee by EmployeeID");
        System.out.println("2: Get Employee by Salary Greater than Amount");
        System.out.println("3: Update Salary From EmployeeId");
        System.out.println("4: Delete Employee by Resources");
        System.out.println("5: Get Employee Order by Salary Descending");
        System.out.println("6: Return to Main Menu");
        System.out.println("Enter Your choice:");
    }

    public static void printAggregateFunc() {
        System.out.println("Aggregate Functions Menu:");
        System.out.println("1: Get Total Salary");
        System.out.println("2: Get Average Salary");
        System.out.println("3: Get Count of Employees");
        System.out.println("4: Get Minimum Salary");
        System.out.println("5: Get Maximum Salary");
        System.out.println("6: Return to Main Menu");
        System.out.println("Enter your choice:");
    }


    public static void printNativeQueries() {
        System.out.println("Native Queries Menu:");
        System.out.println("1: Get Employees Details");
        System.out.println("2: Update Salary by Employee ID");
        System.out.println("3: Delete Employee Resources");
        System.out.println("4: Return to Main Menu");
        System.out.println("Enter your choice:");
    }


}
