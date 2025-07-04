
import com.model.Student;
import com.service.Manager;
import com.service.FileSer;
import java.util.*;

public class main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Manager studentService = new Manager();

    public static void main(String[] args) {
        // Load existing data
        studentService.getAllStudents().addAll(FileSer.loadStudents());

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Sort Students");
            System.out.println("7. Save & Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> editStudent();
                case 3 -> deleteStudent();
                case 4 -> searchStudent();
                case 5 -> displayAllStudents();
                case 6 -> sortStudents();
                case 7 -> {
                    FileSer.saveStudents(studentService.getAllStudents());
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        System.out.print("Enter Grade: ");
        double grade = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        studentService.addStudent(new Student(id, name, age, grade));
        System.out.println("Student added successfully!");
    }

    private static void editStudent() {
        System.out.print("Enter student ID to edit: ");
        String id = scanner.nextLine();
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new age: ");
        int newAge = scanner.nextInt();
        System.out.print("Enter new grade: ");
        double newGrade = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (studentService.editStudent(id, newName, newAge, newGrade)) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter student ID to delete: ");
        String id = scanner.nextLine();

        if (studentService.deleteStudent(id)) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void searchStudent() {
        System.out.println("1. Search by ID");
        System.out.println("2. Search by name");
        System.out.print("Choose an option: ");
        int searchChoice = scanner.nextInt();
        scanner.nextLine();

        if (searchChoice == 1) {
            System.out.print("Enter ID: ");
            String id = scanner.nextLine();
            Student student = studentService.searchById(id);
            System.out.println(student != null ? student : "Student not found!");
        } else if (searchChoice == 2) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            List<Student> results = studentService.searchByName(name);
            results.forEach(System.out::println);
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private static void displayAllStudents() {
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found!");
        } else {
            students.forEach(System.out::println);
        }
    }

    private static void sortStudents() {
        System.out.println("1. Sort by name");
        System.out.println("2. Sort by grade (highest first)");
        System.out.print("Choose an option: ");
        int sortChoice = scanner.nextInt();
        scanner.nextLine();

        List<Student> sortedStudents = switch (sortChoice) {
            case 1 -> studentService.sortByName();
            case 2 -> studentService.sortByGrade();
            default -> {
                System.out.println("Invalid choice!");
                yield List.of();
            }
        };
        sortedStudents.forEach(System.out::println);
    }
}