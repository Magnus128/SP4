import java.util.ArrayList;
import java.util.Arrays;

public class Employee {
    public Job job;
    public int salary;
    public String name;

    public static ArrayList<Employee> employeesList = new ArrayList<>();


    public Employee(Job job, int salary, String name){
        this.job = job;
        this.salary = salary;
        this.name = name;

    }


    public Job getJob(){return job;}

    public void setJob(Job job){this.job = job;}

    public void printJobInfo(){
        System.out.println("Job: " + job);
    }
}
