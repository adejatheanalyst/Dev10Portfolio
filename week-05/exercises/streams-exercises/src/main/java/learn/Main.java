package learn;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {

        StudentDataStore ds = new StudentDataStore();
        List<Student> students = ds.all();

        // 0. Print all students
        // iteration solution
//        for (Student student : students) {
//            System.out.println(student);
//        }

        // stream solution
//        students.stream().forEach(System.out::println);

        // 1. Print students from Argentina
students.stream().filter(student -> student.getCountry().equals("Argentina"))
                .forEach(System.out::println);
        // 2. Print students whose last names starts with 'T'.
students.stream().filter(student -> student.getLastName().startsWith("T"))
                .forEach(System.out::println);
        // 3. Print students from Argentina, ordered by GPA
students.stream().filter(student -> student.getCountry().equals("Argentina"))
                .sorted(Comparator.comparing(Student::getGpa).reversed())
                .forEach(System.out::println);
        // 4. Print the bottom 10% (100 students) ranked by GPA.
        long studentCount = students.size();
        System.out.println("Student Count: " + studentCount);
        students.stream()
                .skip(900)
                .limit(100)
                .sorted(Comparator.comparing(Student::getGpa).reversed())
                .forEach(System.out::println);
//         5. Print the 4th - 6th ranked students by GPA from Argentina
students.stream().filter(student -> student.getCountry().equals("Argentina"))
                .sorted(Comparator.comparing(Student::getGpa).reversed())
                .skip(3)
                .limit(3)
                .forEach(System.out::println);
//         6. Is anyone from Maldives?
        students.stream().filter(student -> student.getCountry().equals("Maldives"))
                .forEach(System.out::println);
//         7. Does everyone have a non-null, non-empty email address?
        students.stream().filter(student -> student.getEmailAddress() == null || student.getEmailAddress().isEmpty())
                .forEach(System.out::println);
 //        8. Print students who are currently registered for 5 courses.
        students.stream().filter(student -> student.getRegistrations().size() == 5)
                .forEach(System.out::println);
     //    9. Print students who are registered for the course "Literary Genres".
students.stream().filter(student -> student.getRegistrations().stream()
                .anyMatch(registration -> registration.getCourse().equals("Literary Genres")))
                .forEach(System.out::println);
      //   10. Who has the latest birthday? Who is the youngest?;
        students.stream()
                .sorted(Comparator.comparing(Student::getBirthDate).reversed())
                .limit(1)
                .forEach(System.out::println);
      //   11. Who has the highest GPA? There may be a tie.
        students.stream()
                .sorted(Comparator.comparing(Student::getGpa).reversed())
                .limit(1)
                .forEach(System.out::println);
       //  12. Print every course students are registered for, including repeats.
students.stream()
                .flatMap(student -> student.getRegistrations().stream())
                .forEach(System.out::println);
        // 13. Print a distinct list of courses students are registered for.
students.stream()
                .flatMap(student -> student.getRegistrations().stream())
                .distinct()
                .forEach(System.out::println);
       //  14. Print a distinct list of courses students are registered for, ordered by name.
        students.stream()
                .flatMap(student -> student.getRegistrations().stream())
                .sorted(Comparator.comparing(Registration::getCourse))
                .distinct()
                .forEach(System.out::println);
       //  15. Count students per country.
Map<String, Long> studentCountByCountry = students.stream()
                .collect(Collectors.groupingBy(Student::getCountry, Collectors.counting()));
for (String country : studentCountByCountry.keySet()) {
    System.out.println(country + ": " + studentCountByCountry.get(country));
}
        // 16. Count students per country. Order by most to fewest students.
        Map<String, Long> studentCountByCountry2 = students.stream()
                .collect(Collectors.groupingBy(Student::getCountry, Collectors.counting()));
        for (String country : studentCountByCountry2.keySet()) {
            System.out.println(country + ": " + studentCountByCountry2.get(country));}
        // another method
        students.stream()
                .collect(Collectors.groupingBy(Student::getCountry, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(System.out::println);
       //  17. Count registrations per course.
Map<String, Long> registrationCountByCourse = students.stream()
                .flatMap(s -> s.getRegistrations().stream())
                .map (r -> r.getCourse())
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
for (String course : registrationCountByCourse.keySet()) {
    System.out.println(course + ": " + registrationCountByCourse.get(course));
}
        // 18. How many registrations are not graded (GradeType == AUDIT)?
        Map<GradeType, Long> registrationCountByGradeType = students.stream()
                .flatMap(student -> student.getRegistrations().stream())
                .collect(Collectors.groupingBy(Registration::getGradType, Collectors.counting()));
        for (GradeType gradeType : registrationCountByGradeType.keySet()) {
            System.out.println(gradeType + ": " + registrationCountByGradeType.get(gradeType));
        }
//long auditCount = students.getStream()
//                .flatMap(student -> student.getRegistrations().stream())
//                .filter(registration -> registration.getGradType() == GradeType.AUDIT)
//                .count();

        // 19. Create a new type, StudentSummary with fields for Country, Major, and IQ.
            // Map Students to StudentSummary, then sort and limit by IQ (your choice of low or high).
students.stream()
        .map(student -> new StudentSummary(student.getIq(), student.getMajor(), student.getCountry()))
        .sorted(Comparator.comparing(StudentSummary::getIQ).reversed())
        .forEach(System.out::println);

        // 20. What is the average GPA per country (remember, it's random fictional data).
        Map<String, BigDecimal> averageGpaByCountry = students.stream()
                .collect(Collectors.groupingBy(Student::getCountry,
                        Collectors.mapping(Student::getGpa, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
        for (String country : averageGpaByCountry.keySet()) {
            System.out.println(country + ": " + averageGpaByCountry.get(country));
        }
        Map<String, List<BigDecimal>> averageGpaByCountry2 = new HashMap<>();
            for(Student student : students){
                if (!averageGpaByCountry2.containsKey(student.getCountry())){
                    averageGpaByCountry2.put(student.getCountry(), List.of(student.getGpa()));
                } else {
                    averageGpaByCountry2.get(student.getCountry()).add(student.getGpa());
                }
            }
            for (String country : averageGpaByCountry2.keySet()) {

                BigDecimal sum = new BigDecimal(0.00);
                for (BigDecimal gpa : averageGpaByCountry2.get(country)){
                    sum = sum.add(gpa);
double sumDouble = sum.doubleValue();
                }
                BigDecimal average = sum.divide(new BigDecimal(averageGpaByCountry2.get(country).size()));
                System.out.println(average);
            }

        // 21. What is the maximum GPA per country?
        Map<String, BigDecimal> maxGpaByCountry = students.stream()
                .collect(Collectors.groupingBy(Student::getCountry,
                        Collectors.mapping(Student::getGpa, Collectors.reducing(BigDecimal.ZERO, BigDecimal::max))));
        for (String country : maxGpaByCountry.keySet()) {
            System.out.println(country + ": " + maxGpaByCountry.get(country));
        }

        // 22. Print average IQ per Major ordered by IQ ascending.
        Map<String, Double> averageIqByMajor = students.stream()
                .collect(Collectors.groupingBy(Student::getMajor,
                        Collectors.mapping(Student::getIq, Collectors.averagingDouble(Double::doubleValue))));
        for (String major : averageIqByMajor.keySet()) {
            System.out.println(major + ": " + averageIqByMajor.get(major));
        }
//        // 23. STRETCH GOAL!
//        // Who has the highest pointPercent in "Sacred Writing"?
        students.stream()
                .flatMap(student -> student.getRegistrations().stream())
                .filter(registration -> registration.getCourse().equals("Sacred Writing"))
                .sorted(Comparator.comparing(Registration::getPointPercent).reversed())
                .limit(1)
                .forEach(System.out::println);
    }
}

