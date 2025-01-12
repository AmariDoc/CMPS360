import java.util.Scanner;

public class StudentInformationSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Student Information System!");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        int age = -1;
        while (age <= 0) {
            System.out.print("Enter your age: ");
            age = scanner.nextInt();
            if (age <= 0) {
                System.out.println("Invalid age. Please enter a positive integer.");
            }
        }

        double gpa = -1.0;
        while (gpa < 0.0 || gpa > 4.0) {
            System.out.print("Enter your GPA (0.0 - 4.0): ");
            gpa = scanner.nextDouble();
            if (gpa < 0.0 || gpa > 4.0) {
                System.out.println("Invalid GPA. Please enter a value between 0.0 and 4.0.");
            }
        }

     
        System.out.print("Enter the number of completed credits: ");
        int completedCredits = scanner.nextInt();

        
        int[] studyHoursPerWeek = new int[5];
        for (int i = 0; i < 5; i++) {
            System.out.print("Enter the hours you study per week for subject " + (i + 1) + ": ");
            studyHoursPerWeek[i] = scanner.nextInt();
        }

        
        double totalStudyHoursPerWeek = 0;
        for (int i = 0; i < 5; i++) {
            totalStudyHoursPerWeek += studyHoursPerWeek[i];
        }

        
        double averageStudyHoursPerDay = totalStudyHoursPerWeek / 7;

       
        double totalStudyHoursPerSemester = totalStudyHoursPerWeek * 16;

        int remainingCredits = 120 - completedCredits;

        System.out.println("\n--- Student Summary ---");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("GPA: " + gpa);
        System.out.println("Completed Credits: " + completedCredits);
        System.out.println("Remaining Credits: " + remainingCredits);
        System.out.printf("\nAverage Study Hours Per Day: %.2f\n", averageStudyHoursPerDay);
        System.out.printf("Total Study Hours Per Semester: %.0f\n", totalStudyHoursPerSemester);

        scanner.close();
    }
}
