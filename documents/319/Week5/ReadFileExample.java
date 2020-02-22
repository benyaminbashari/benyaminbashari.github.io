import java.io.*;
import java.util.Scanner;

public class ReadFileExample {

    public static void readScanner(String path) throws FileNotFoundException {
        File f = new File(path);
        Scanner sc = new Scanner(f);
        while(sc.hasNext())
            System.out.println(sc.next());
    }

    public static void readBuffer(String path) throws IOException {
        File f = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(f));
        while(true) {
            String s = br.readLine();
            if(s == null)
                break;
            else
                System.out.println(s);
        }
    }

    public static void main(String[] args) throws IOException {
        //readScanner("src/read");
        readBuffer("src/read");

    }
}
