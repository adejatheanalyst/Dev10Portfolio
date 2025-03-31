package learn;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class StudentDataStore extends FileDataStore {

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yyyy");
    private final Random random = new Random(512);

    public StudentDataStore() {
        super("students.csv", 8, true);
    }

    public List<Student> all() throws IOException {

        List<Student> result = new ArrayList<>();

        runPerLine(fields -> {

            Student student = new Student();

            student.setId(Integer.parseInt(fields[0]));
            student.setFirstName(fields[1]);
            student.setLastName(fields[2]);
            student.setEmailAddress(fields[3]);
            student.setBirthDate(LocalDate.parse(fields[4], dtf));
            student.setCountry(fields[5]);
            student.setGpa(new BigDecimal(fields[6]));
            student.setMajor(getMajor(fields[7]));
            student.setIq(80.0 + random.nextInt(50));

            setRegistrations(student);

            result.add(student);
        });

        return result;
    }

    private String getMajor(String majorId) {

        int value = Integer.parseInt(majorId);
        switch (value) {
            case 1:
                return "American Studies";
            case 2:
                return "Sociology";
            case 3:
                return "Philosophy";
            case 4:
                return "Political Science";
            case 5:
                return "Economics";
            case 6:
                return "Business";
            case 7:
                return "Anthropology";
            case 8:
                return "Education";
            case 9:
                return "Communications";
            case 10:
                return "Pre-Med";
            case 11:
                return "Mathematics";
            default:
                return "Computer Science";
        }
    }

    private String[] courses = new String[]{
            "Research Methods: Research in Liberal Arts Disciplines",
            "Greco-Roman Tradition",
            "Ancient Philosophy",
            "Sacred Writing",
            "Intro to College English",
            "World Views",
            "Post-Classical History",
            "Modern Philosophy",
            "Principles of Mathematics and Logic",
            "Renaissance to Baroque Art",
            "Literary Genres",
            "Modern History-19th and 20th Centuries",
            "Science: History and Methodology",
            "Literary Themes",
            "Knowledge",
            "Applied Themes in English",
            "Applied Ethics in Humanities",
            "Physical Activity and Autonomy"
    };

    private GradeType[] gradeTypes = new GradeType[]{
            GradeType.A_THROUGH_F,
            GradeType.PASS_FAIL,
            GradeType.AUDIT
    };

    private void setRegistrations(Student student) {
        HashSet<String> registeredCourses = new HashSet<>();
        List<Registration> registrations = new ArrayList<>();
        for (int i = 0; i < random.nextInt(4) + 2; i++) {
            String course = null;
            do {
                course = courses[random.nextInt(courses.length)];
            } while (registeredCourses.contains(course));
            registeredCourses.add(course);

            Registration reg = new Registration();
            reg.setCourse(course);
            reg.setGradType(gradeTypes[random.nextInt(gradeTypes.length)]);
            reg.setPointPercent(75 + random.nextInt(25));
            registrations.add(reg);
        }
        student.setRegistrations(registrations);
    }
}
