package com.techelevator;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    private List<Department> departments = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
    private Map<String, Project> projects = new HashMap<>();

    /**
     * The main entry point in the application
     * @param args
     */
    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    private void run() {
        // create some departments
        createDepartments();

        // print each department by name
        printDepartments();

        // create employees
        createEmployees();

        // give Angie a 10% raise, she is doing a great job!

        // print all employees
        printEmployees();

        // create the TEams project
        createTeamsProject();
        // create the Marketing Landing Page Project
        createLandingPageProject();

        // print each project name and the total number of employees on the project
        printProjectsReport();
    }

    /**
     * Create departments and add them to the collection of departments
     */
    private void createDepartments() {
        departments.add(new Department(1,"Marketing"));
        departments.add(new Department(2,"Sales"));
        departments.add(new Department(3,"Engineering"));
    }

    /**
     * Print out each department in the collection.
     */
    private void printDepartments() {
        System.out.println("------------- DEPARTMENTS ------------------------------");
        for (Department department : departments) {
            System.out.println(department.getName());
        }
    }

    /**
     * Create employees and add them to the collection of employees
     */
    private void createEmployees() {
        Employee employee1 = new Employee();
        employee1.setEmployeeId(1);
        employee1.setFirstName("Dean");
        employee1.setLastName("Johnson");
        employee1.setEmail("djohnson@teams.com");
        employee1.setDepartment(getDepartmentByName("Engineering"));
        employee1.setHireDate(LocalDate.now());
        employees.add(employee1);
        employees.add(new Employee(2, "Angie", "Smith", "asmith@teams.com", getDepartmentByName("Engineering"), LocalDate.now()));
        employees.add(new Employee(3, "Margaret", "Thompson", "mthompson@teams.com", getDepartmentByName("Marketing"), LocalDate.now()));
        employees.get(1).raiseSalary(10);
    }

    /**
     * Print out each employee in the collection.
     */
    private void printEmployees() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        System.out.println("\n------------- EMPLOYEES ------------------------------");
        for (Employee employee : employees) {
            System.out.println(employee.getFullName() + " (" + currency.format(employee.getSalary()) + ") " + employee.getDepartment().getName());
        }
    }

    /**
     * Create the 'TEams' project.
     */
    private void createTeamsProject() {

        Project project1 = new Project("TEams", "Project Management Software", todaysDate(), futureDate(30));
        project1.setTeamMembers(employeesMatchDepartment("Engineering", employees));
        projects.put("TEams", project1);
    }

    private String todaysDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/dd/uuuu");
        String todayString = today.format(formatters);
        return todayString;
    }

    private String futureDate(int futureDays) {
        LocalDate today = LocalDate.now().plusDays(futureDays);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/dd/uuuu");
        String futureString = today.format(formatters);
        return futureString;
    }

    /**
     * Create the 'Marketing Landing Page' project.
     */
    private void createLandingPageProject() {
        Project project2 = new Project("Marketing Landing Page", "Lead Capture Landing Page for Marketing", futureDate(31), futureDate(38));
        project2.setTeamMembers(employeesMatchDepartment("Marketing", employees));
        projects.put("Marketing Landing Page", project2);
    }

    private List<Employee> employeesMatchDepartment(String departmentName, List<Employee> employeesList) {
        List<Employee> matchedEmployees = new ArrayList<>();
            for (Employee employee : employees) {
                if (employee.getDepartment().getName().equals(departmentName)) {
                matchedEmployees.add(employee);
            }
        }
        return matchedEmployees;
    }

    /**
     * Print out each project in the collection.
     */
    private void printProjectsReport() {
        System.out.println("\n------------- PROJECTS ------------------------------");
        for (Map.Entry<String, Project> entry: projects.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getTeamMembers().size());
        }
        for (Map.Entry<String, Project> entry: projects.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getStartDate());
            System.out.println(entry.getKey() + ": " + entry.getValue().getDueDate());
        }
    }

    private Department getDepartmentByName(String searchTerm) {
        for (Department department: departments) {
            if (searchTerm.equals(department.getName())) {
                return department;
            }
        }
        return null;
    }
}
