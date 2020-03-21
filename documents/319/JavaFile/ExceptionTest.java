import java.util.Scanner;

class ExceptionTest {
    /**
    * Read an ineteger n from System.in (number of elements)
    * Read n integer numbers from System.in
    * Find the Smallest number and print it
    */
    public static void wrongMethod() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for(int i = 1 ; i <= n ; i++) //This is a common mistake, indices of "a" starts from 0 to n-1
            a[i] = sc.nextInt();
        
        int minNumber = Integer.MAX_VALUE;
        for(int i = 1 ; i <= n ; i++) 
            minNumber = Math.min(minNumber, a[i]);

        System.out.println(minNumber);
    }

    public static void rightMethod() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for(int i = 0 ; i < n ; i++) 
            a[i] = sc.nextInt();
        
        int minNumber = Integer.MAX_VALUE;
        for(int i = 0 ; i < n ; i++) 
            minNumber = Math.min(minNumber, a[i]);

        System.out.println(minNumber);
    }

    public static void main(String[] args) {
        //rightMethod();
        //wrongMethod();
    }
}