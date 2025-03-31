public class Employee extends Person{

    int salary;



//constructors
    Employee(String first, String last, int salary){
        super(first, last);
        this.salary = salary;

    }
    void showSalary(){
        System.out.println(this.first + "'s Salary is $" + this.salary);

    }




}
