import learn.Student;
import learn.Vehicle;

import java.util.HashMap;
import java.util.HashMap;

public class Exercise13 {
    public static void main(String[] args) {

        HashMap<Integer, Student> studentHashMap = new HashMap<>();

        Student newStudent = new Student(2204456, "James", "Bond");
        Student newStudent2 = new Student(5635645, "Dave", "Cronny");
        Student newStudent3 = new Student(2289665, "John", "Doe");
        Student newStudent4 = new Student(8464684, "Doe", "John");
        Student newStudent5 = new Student(3470505, "Deja", "Williams");

        studentHashMap.put(newStudent.getStudentId(), newStudent);
        studentHashMap.put(newStudent2.getStudentId(), newStudent2);
        studentHashMap.put(newStudent4.getStudentId(), newStudent4);
        studentHashMap.put(newStudent5.getStudentId(), newStudent5);
        studentHashMap.put(newStudent3.getStudentId(), newStudent3);

//
//        Student students = studentHashMap.get(newStudent5.getStudentId());
//        System.out.println("Students:  " + students);

        studentHashMap.remove(8464684);
        getAllStudents(studentHashMap);
    }
        public static void getAllStudents (HashMap < Integer, Student > studentHashMap ){
            for (Student students : studentHashMap.values()) {
                System.out.println(students);
            }
        }

//for (interger key : studentMap.keyset())
//    sout (studentMap(key))


    }














