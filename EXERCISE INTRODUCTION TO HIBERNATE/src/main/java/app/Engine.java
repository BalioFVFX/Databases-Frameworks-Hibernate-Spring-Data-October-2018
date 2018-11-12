package app;

import app.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Engine implements Runnable {

    EntityManager entityManager;

    public Engine(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void run() {

       entityManager.getTransaction().begin();

       // Insert problem here:

       entityManager.getTransaction().commit();
    }

    // Problem 02. Remove Objects
    void problem02(){
        List<Town> towns = entityManager.createQuery("SELECT e FROM Town e").getResultList();

        towns.forEach(town -> {
            if(town.getName().length() > 5) {
                town.setName(town.getName().toLowerCase());
                entityManager.persist(town);
            }
        });
    }

    // Problem 03. Contains Employee
    void problem03 (){
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        try{
            Employee emp = entityManager.createQuery("FROM Employee WHERE CONCAT(first_name, ' ', last_name) = :name", Employee.class)
                    .setParameter("name", input)
                    .getSingleResult();
            System.out.println("Yes");
        }
        catch(NoResultException n){
            System.out.println("No");
        }

    }

    // Problem 04. Employees with Salary Over 50 000
    void problem04(){
        List<Employee> employees = entityManager.createQuery("FROM Employee WHERE salary > 50000").getResultList();

        employees.forEach(emp -> {
            System.out.println(emp.getFirstName());
        });
    }

    // Problem 05. Employees from Department

    void problem05(){
        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee e JOIN Department d on d.id = e.department WHERE d.name = :name ORDER BY e.salary", Employee.class).setParameter("name", "Research and Development").getResultList();

        employees.forEach(emp -> {
            System.out.println(String.format(
                    "%s  %s from Research and Development - $%s",
                    emp.getFirstName(),
                    emp.getLastName(),
                    emp.getSalary().toString()
            ));
        });
    }

    // Problem 06. Adding a New Address and Updating Employee

    void problem06(){
        Scanner scanner = new Scanner(System.in);
        final String addressText = "Vitoshka 15";
        final String lastNameInput =  scanner.nextLine();

        List<Employee> employees = entityManager.createQuery("FROM Employee e WHERE e.lastName = :lastName",
                Employee.class)
                .setParameter("lastName", lastNameInput)
                .getResultList();



        employees.forEach(emp ->{
            Address address = new Address();
            address.setTown(emp.getAddress().getTown());
            address.setText(addressText);
            entityManager.persist(address);
            emp.setAddress(address);
            entityManager.persist(emp);
        });
    }

    // Problem 07. Addresses with Employee Count

    void problem07(){

        List<Address> result = entityManager.createQuery("select a from Address  a order by a.employees.size desc, a.town.id asc")
                .setMaxResults(10)
                .getResultList();

        for (Address address: result){
            System.out.println(String.format("%s, %s - %d",
                    address.getText(), address.getTown().getName(), address.getEmployees().size()));
        }
    }

    // Problem 08. Get Employee with Project

    void problem08(){
        Scanner scanner = new Scanner(System.in);

        int employeeIdInput = scanner.nextInt();

        try{
            Employee employee = entityManager.createQuery("FROM Employee e WHERE e.id = :empId", Employee.class)
                    .setParameter("empId", employeeIdInput)
                    .getSingleResult();

            ArrayList<String> result = new ArrayList<String>();

            result.add(employee.getFirstName());
            result.add(employee.getLastName() + " - ");
            result.add(employee.getJobTitle() + "\n");
            employee.getProjects()
                    .stream()
                    .sorted((p1,p2) -> p1.getName().compareTo(p2.getName()))
                    .forEach(p -> result.add(p.getName() + "\n"));

            System.out.println("------------- RESULT ------------------");

            result.forEach(System.out::print);
        }
        catch(NoResultException exception){
            System.out.println("There is no employee with id " + employeeIdInput);
        }
    }

    // Problem 09. Find Latest 10 Projects

    void problem09(){
        List<Project> projects = entityManager.createQuery("FROM Project p ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultList().stream().sorted((p1, p2) -> p1.getName().compareTo(p2.getName())).collect(Collectors.toList());

        System.out.println("--------------RESULTS---------------------");
        projects.forEach(p -> {
            System.out.println(String.format(
                    "Project name: %s\nProject Description: %s\nProject Start Date: %s\nProject End Date: %s",
                    p.getName(),
                    p.getDescription(),
                    p.getStartDate(),
                    p.getEndDate()
            ));
        });
    }

    // Problem 10. Increase Salaries
    void problem10(){
        List<Employee> employees = entityManager.createQuery("FROM Employee e", Employee.class)
                .getResultStream().filter(e -> e.getDepartment().getName().equals("Engineering") ||
                        e.getDepartment().getName().equals("Tool Design") ||
                        e.getDepartment().getName().equals("Marketing") ||
                        e.getDepartment().getName().equals("Information Services")).collect(Collectors.toList());

        employees.forEach(emp -> {
            BigDecimal newSalary = emp.getSalary().add(emp.getSalary().multiply(new BigDecimal(0.12)));
            emp.setSalary(newSalary);
            entityManager.persist(emp);
        });


        System.out.println("---------------RESULT---------------");
        employees.forEach(emp -> {
            System.out.println(emp.getFirstName() + " " + emp.getLastName() + "($" + emp.getSalary() +")");
        });
    }

    // Problem 11. Remove Towns

    void problem11(){
        Scanner scanner = new Scanner(System.in);

        String townInput = scanner.nextLine();


        int removedAddresses = 0;
        try{
            Town town = entityManager.createQuery("FROM Town t WHERE t.name = :tName", Town.class)
                    .setParameter("tName", townInput)
                    .getSingleResult();


            List<Address> addresses = entityManager.createQuery("FROM Address a WHERE a.town = :selectedTown",
                    Address.class)
                    .setParameter("selectedTown", town)
                    .getResultList();



            entityManager.remove(town);

            for (Address address: addresses) {
                entityManager.createQuery("UPDATE Employee e SET e.address = NULL WHERE e.address = :delAddress")
                        .setParameter("delAddress", address)
                        .executeUpdate();
                entityManager.remove(address);
                removedAddresses++;
            }

        }
        catch(NoResultException exception){

        }
        System.out.println("-----------RESULT---------------");
        System.out.println(removedAddresses + " addresses in " + townInput + " deleted");
    }

    // Problem 12. Find Employees by First Name

    void problem12(){
        Scanner scanner = new Scanner(System.in);
        String pattern = scanner.nextLine();

        List<Employee> employees = entityManager.createQuery("FROM Employee e WHERE e.firstName LIKE CONCAT(:pattern, '%')",
                Employee.class)
                .setParameter("pattern", pattern)
                .getResultList();

        System.out.println("---------RESULT--------------");
        employees.forEach(emp -> {
            System.out.println(emp.getFirstName() + " " + emp.getLastName() + " - " + emp.getJobTitle() + " ($" + emp.getSalary() + ")");
        });
    }

    // Problem 13. Employees Maximum Salaries

    void problem13(){
        List<Department> res = entityManager.createQuery("select d FROM Department d", Department.class).getResultList();


        res.forEach(r -> {
            BigDecimal maxSalary = new BigDecimal(0);
            for (Employee emp : r.getEmployees()) {
                if (emp.getSalary().compareTo(new BigDecimal(70000)) >= 0
                        || emp.getSalary().compareTo(new BigDecimal(30000)) <= 0)
                    if (emp.getSalary().compareTo(maxSalary) >= 0) {
                        maxSalary = emp.getSalary();
                    }

            };
            if(!maxSalary.equals(BigDecimal.ZERO))
            System.out.println(r.getName() + " - " + maxSalary);
        });

    }
}
