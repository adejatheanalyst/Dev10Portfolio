public class Main {
    public static void main(String[] args) {

//Dog dog = new Dog();
//Cat cat = new Cat();
//Plant plant = new Plant();
//
//      dog.eat();
//      cat.eat();
//      dog.speak();
//        System.out.println(dog.lives);
//        System.out.println(cat.lives);
//        System.out.println(dog.isAlive);
//        System.out.println(cat.isAlive);
//        System.out.println(plant.isAlive);
//        plant.photo();
        Person person = new Person("Jerry", "Rice");
        Student student = new Student("Harry", "potter", 3.25);
        Employee employee = new Employee("dEJA", "WILLI", 50000);
        person.showName();
        student.showName();
        System.out.println(student.gpa);
student.showGPA();
employee.showSalary();
employee.showName();

    }

}
