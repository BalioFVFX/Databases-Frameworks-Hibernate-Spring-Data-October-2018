import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStreamReader);



        try{
            String[] studentInfo = reader.readLine().split(" ");
            Student student = new Student(studentInfo[0], studentInfo[1], studentInfo[2]);

            System.out.println("First name: " + student.getFirstName());
            System.out.println("Last name: " + student.getLastName());
            System.out.println("Faculty number: " + student.getFacultyNumber());
            String[] workerInfo = reader.readLine().split(" ");
            Worker worker = new Worker(workerInfo[0], workerInfo[1],
                    Double.parseDouble(workerInfo[2]),
                    Double.parseDouble(workerInfo[3]));



            System.out.println("First name: " + worker.getFirstName());
            System.out.println("Last name: " + worker.getLastName());
            System.out.printf("Week salary: %.2f\n", worker.getWeekSalary());
            System.out.printf("Hours per day: %.2f\n", worker.getWorkingHours());
            System.out.printf("Salary per hour: %.2f", worker.getSalary());

        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}
