import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private int rollNo;
    private double score;

    public Student(String name, int rollNo, double score) {
        this.name = name;
        this.rollNo = rollNo;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public double getScore() {
        return score;
    }

    public String getGrade() {
        if (score >= 90) return "A+";
        if (score >= 80) return "A";
        if (score >= 70) return "B";
        if (score >= 60) return "C";
        if (score >= 50) return "D";
        return "F";
    }
}

public class StudentGradeManagement {
    private static final Scanner sc = new Scanner(System.in);
    private static final ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n===== STUDENT GRADE MANAGEMENT =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Show Grade Statistics");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Enter a valid menu number: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> displayStudents();
                case 3 -> showStatistics();
                case 4 -> System.out.println("Exiting program...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 4);

        sc.close();
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter roll number: ");
        while (!sc.hasNextInt()) {
            System.out.print("Enter a valid roll number: ");
            sc.next();
        }
        int rollNo = sc.nextInt();

        for (Student s : students) {
            if (s.getRollNo() == rollNo) {
                System.out.println("Roll number already exists.");
                sc.nextLine();
                return;
            }
        }

        System.out.print("Enter score (0-100): ");
        while (!sc.hasNextDouble()) {
            System.out.print("Enter a valid numeric score: ");
            sc.next();
        }
        double score = sc.nextDouble();
        sc.nextLine();

        if (score < 0 || score > 100) {
            System.out.println("Score must be between 0 and 100.");
            return;
        }

        students.add(new Student(name, rollNo, score));
        System.out.println("Student added successfully!");
    }

    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        System.out.println("\n--------------- STUDENT REPORT ---------------");
        System.out.printf("%-12s %-22s %-10s %-10s%n",
                "Roll No", "Name", "Score", "Grade");
        System.out.println("------------------------------------------------");

        for (Student s : students) {
            System.out.printf("%-12d %-22s %-10.2f %-10s%n",
                    s.getRollNo(), s.getName(),
                    s.getScore(), s.getGrade());
        }
    }

    private static void showStatistics() {
        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        double total = 0;
        double highest = students.get(0).getScore();
        double lowest = students.get(0).getScore();
        String topStudent = students.get(0).getName();
        String lowestStudent = students.get(0).getName();

        for (Student s : students) {
            double score = s.getScore();
            total += score;

            if (score > highest) {
                highest = score;
                topStudent = s.getName();
            }

            if (score < lowest) {
                lowest = score;
                lowestStudent = s.getName();
            }
        }

        double average = total / students.size();

        System.out.println("\n========== GRADE STATISTICS ==========");
        System.out.println("Total Students : " + students.size());
        System.out.printf("Average Score  : %.2f%n", average);
        System.out.printf("Highest Score  : %.2f (%s)%n",
                highest, topStudent);
        System.out.printf("Lowest Score   : %.2f (%s)%n",
                lowest, lowestStudent);
        System.out.println("======================================");
    }
}
