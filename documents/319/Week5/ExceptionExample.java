import java.sql.SQLOutput;
import java.util.Scanner;


public class ExceptionExample {

    public static int safeDivision(int a, int b) {
        try {
            return a/b;
        }
        catch (ArithmeticException e) {
            System.out.println("Exception occurred");
            return 0;
        }
    }

    public static int notTooSafeDivision(int a, int b) throws ArithmeticException {
        return a/b;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        //System.out.println(safeDivision(a, b));
        //System.out.println(a/b);
        try {
            System.out.println(notTooSafeDivision(a, b));
        }
        catch (ArithmeticException e) {
            System.out.println("Oops");
        }
        //Rest of the code starts from here

    }
}
