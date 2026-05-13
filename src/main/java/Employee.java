public class Employee {
    private Job job;

    public Employee(Job job){
        this.job = job;
    }

    public Job getJob(){return job;}

    public void setJob(Job job){this.job = job;}

    public void printJobInfo(){
        System.out.println("Job: " + job);
    }
}
