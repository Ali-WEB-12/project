import java.time.LocalDate;
import java.util.*;
public abstract class Employee extends Person{
    private double Salary;
    private LocalDate dateJoin;
    Scanner sc = new Scanner(System.in);
    
    public Employee() {

    }
    
    public Employee(String name, int contactNumber, double Salary) {
        super(name,contactNumber);
        this.Salary = Salary;
        this.dateJoin = LocalDate.now();
    }

    @Override
    public abstract boolean equals(Object o);

    // public abstract double calculateSalary();

    
    
    @Override
    public String toString(){
        return super.toString() + ", Salary : " + Salary + ", Date Joined : " + dateJoin;
    }

    public double getSalary() {
        return Salary;
    }

    public LocalDate getDateJoin() {
        return dateJoin;
    }   
}
    
    
