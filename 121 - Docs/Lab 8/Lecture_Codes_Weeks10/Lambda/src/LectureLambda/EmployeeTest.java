package LectureLambda;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
/**
 *
 * @author Hooman
 */
public class EmployeeTest 
{
    
    public static void main(String[] args) 
    {
        Employee[] employees = new Employee[7]; 

        // initialize array with Employees
        employees[0] = new Employee("John", "Smith",  1800.00,"IT" );
        employees[1] = new Employee("Karen", "Price", 7500,"Sales");
        employees[2] = new Employee("Sue", "Jones",  5500,"IT"); 
        employees[3] = new Employee("Bob", "Lewis",  8500,"Sales");
        employees[4] = new Employee("Rick", "Bridges", 10500,"Marketing");
        employees[5] = new Employee("Bob", "Jones",  4500,"Sales");
        employees[6] = new Employee("Rick", "Smith", 3500,"Marketing");

        List <Employee> listE =Arrays.asList(employees);
        // first print method
        System.out.println("1. Complete Employee list");
        for(Employee e: listE)
        {
            System.out.println(e);
        }

        // first print method
        System.out.println("2. Complete Employee list");
        listE.stream().forEach(e->System.out.println(e));

        // first print method
        System.out.println("3. Complete Employee list");
        listE.stream().forEach(System.out::println);

        // predicate
        Predicate<Employee> p1= e->
        { 
            if(e.getSalary()>=4000 && e.getSalary() <=6000)
                return true;
            else
                return false;
        };

        Predicate<Employee> p2= e-> e.getSalary()>=4000 && e.getSalary() <=6000;

        System.out.println("1. Earning between 4000 to 6000");  
        listE.stream()
            .filter(p1)
            .sorted(Comparator.comparing(Employee::getSalary))
            .forEach(System.out::println); 

        System.out.println("2. Earning between 4000 to 6000");
        listE.stream()
            .filter(p2)
            .sorted(Comparator.comparing(Employee::getSalary))
            .forEach(System.out::println); 

        System.out.printf("1. First employee earning between 4000 to 6000 is  %s %n",
        listE.stream()
            .filter(p2)
            .findFirst()       
            .get()
            .getFirstName());   

        System.out.printf("2. First employee earning between 4000 to 6000 is  %s %n",
        listE.stream()
            .filter(p2)
            .findFirst()
            .map(Employee::getFirstName)
            .get());

              // map to only some field 
        System.out.printf("3. First employee earning between 4000 to 6000 is  %s %n",
        listE.stream()
            .filter(p2)
            .findFirst()
            .map(e->String.format("%s %s", e.getFirstName(),e.getLastName()))
            .get());

            //---------------------Function--------------------------------------------------
        Function<Employee, String> byFirst = e->e.getFirstName();         
        Function<Employee, String> byLast = Employee::getLastName;          

        Comparator<Employee> lastThenFirst = Comparator.comparing(byLast).thenComparing(byFirst);

        System.out.println("Sort employee by last name and then first name");
        listE.stream()
            .sorted(lastThenFirst)
            .forEach(System.out::println); 

        System.out.println("Sort employee by last name and then first name (reverse)");
        listE.stream()
            .sorted(lastThenFirst.reversed())
            .forEach(System.out::println);         

        // to map
        System.out.println("1. employee by department");
        Map<String,List<Employee>> groupByDepartment =
            listE.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        Map<String,List<Employee>> groupByDepartment1=
            listE.stream().collect(Collectors.groupingBy(e->e.getDepartment()));

        groupByDepartment.forEach((d,e)-> 
        {
            System.out.println(d);
            e.forEach(e1->System.out.printf("   %s %n",e1));
        });

        groupByDepartment1.forEach((d,e)-> 
        {
            System.out.println(d);
            e.forEach(e1->System.out.printf("   %s %s %n",e1.getFirstName(), e1.getLastName()));
        });

        //data aggregation by Lambda
        System.out.println("Count employee by department");
        Map<String,Long> groupByDepartment2 =
            listE.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
        groupByDepartment2.forEach((d,c)->
        {
            System.out.printf("%s has %d employee(s)%n", d,c);
        });

        System.out.println("Total salary employee by department");
        Map<String,Double> groupByDepartment3 =
            listE.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.summingDouble(Employee::getSalary)));
        groupByDepartment3.forEach((d,c)->
        {
            System.out.printf("%s Total Salary -> %.2f %n", d,c);
        });


        // sum of Employees' salaries
        System.out.printf("Sum of Employees' salaries is  %.2f %n",
            listE.stream()
            .mapToDouble(Employee::getSalary)
            .sum());         

        System.out.printf("Sum of Employees' salaries (reduce method) is  %.2f %n",
            listE.stream()
            .mapToDouble(Employee::getSalary)
            .reduce(0,(x,y)->x+y));   

        System.out.printf("Average of Employees' salaries is  %.2f %n",
            listE.stream()
            .mapToDouble(Employee::getSalary)
            .average()
            .getAsDouble());             
    }   
}
