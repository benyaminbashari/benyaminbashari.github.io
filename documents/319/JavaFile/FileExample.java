import java.io.*;
import java.util.*;

public class FileExample {

    public static void readBufferedReader() {
         try {
            File f = new File("input.txt");//input must in the same folder as FileExample.java
            if(!f.exists()) {
                System.out.println("File does not exist");
                return;
            }
            else if(!f.canRead()) {
                System.out.println("File is not readable");
                return;
            }
            BufferedReader bf = new BufferedReader(new FileReader(f));
            String s = bf.readLine();
            while(s != null) {
                System.out.println(s);
                s = bf.readLine();
            }
            bf.close();
        } catch(IOException e) {
            System.out.println("Unknow Exception");
        }
    }
    public static void readScanner() {
        try {
            File f = new File("input.txt");//input must in the same folder as FileExample.java
            if(!f.exists()) {
                System.out.println("File does not exist");
                return;
            }
            else if(!f.canRead()) {
                System.out.println("File is not readable");
                return;
            }
            Scanner sc = new Scanner(f);

            while(sc.hasNextLine()) {
                String s = sc.nextLine();
                System.out.println(s);
            }
            sc.close();
        } catch(IOException e) {
            System.out.println("Unknow Exception");
        }

    }
    public static void main(String[] args) {
        //readBufferedReader();
        //readScanner();
    }
}
