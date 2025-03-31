import java.sql.SQLOutput;

public class Student extends Person{
    double gpa;
// constructor
    Student(String first, String last, double gpa){
        super(first, last);
        this.gpa = gpa;
    }
void showGPA(){
    System.out.println(this.first + "'s GPA is " + this.gpa);
}


}
