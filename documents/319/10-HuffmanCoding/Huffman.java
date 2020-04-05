import javax.sound.midi.Soundbank;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {
    private Node leftChild, rightChild;
    private int freq;
    private char ch;
    private boolean isLeaf;

    public Node(int freq, char ch) {
        leftChild = rightChild = null;
        this.freq = freq;
        this.ch = ch;
        isLeaf = true;
    }
    public Node(Node leftChild, Node rightChild) {
        freq = leftChild.freq + rightChild.freq;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        isLeaf = false;
    }

    public char getCh() {
        return ch;
    }

    public Boolean isLeaf() {
        return isLeaf;
    }

    public int getFreq() {
        return freq;
    }

    public Node getLeftChild() { return leftChild; }

    public Node getRightChild() {return rightChild; }

    @Override
    public int compareTo(Node node) {
        return freq - node.getFreq();
    }
}

/**
 * Implementation of Huffman code provided here is not efficient due to using String for representing binary values
 * Also in order to avoid complicated codes of writing binary in a file, the output of decodes is also a ascii file.
 * This causes that the encoded file is larger in size than the original file!!
 * The ratio of compression is stated in the output.
 */
public class Huffman {
    /**
     * Increment the value of ch in the HashMap by 1
     */
    private static void incrementFreq(char ch, HashMap map) {
        if(!map.containsKey(ch)) //If ch is not in map
            map.put(ch, 0); //Make a new entry for ch with frequency 0
        Integer lastFreq = (Integer) map.get(ch);
        map.put(ch, lastFreq + 1);
    }

    private static void printFreqMap(ArrayList<Node> lst) {
        System.out.println("Frequency of characters decreasing by their frequency:");
        for(Node elem: lst) {
            char ch = (Character) elem.getCh();
            int f = (Integer) elem.getFreq();
            if(ch != '\n' && ch != ' ')
                System.out.println(ch + " " + f);
            else if(ch == '\n')
                System.out.println("[newline] " + f);

            else
                System.out.println("[space] " + f);
        }
        System.out.println("-----------------------------------------");
    }

    private static void printCodes(HashMap<Character, String> codes, ArrayList<Node> lst) {
        System.out.println("Codes of characters:");
        for(Node elem: lst) {
            char ch = (Character) elem.getCh();
            int f = (Integer) elem.getFreq();
            String binary = codes.get(ch);
            if(ch != '\n' && ch != ' ')
                System.out.println(ch + " " + f + " " + binary);
            else if(ch == '\n')
                System.out.println("[newline] " + f + " " + binary);

            else
                System.out.println("[space] " + f + " " + binary);
        }
        System.out.println("-----------------------------------------");
    }

    private static void traverse(Node node, String code, HashMap<Character, String> codes) {
        if(node.isLeaf()) {
            codes.put(node.getCh(), code);
            return;
        }
        //add 0 to the end of binary value currently traversed
        traverse(node.getRightChild(), code.concat("0"), codes);
        traverse(node.getLeftChild(), code.concat("1"), codes);
    }

    public static String readFile(File inFile) throws FileNotFoundException {
        Scanner sc = new Scanner(inFile);
        StringBuilder inputText = new StringBuilder("");
        while(sc.hasNextLine()) { //Read the input line by line
            String line = sc.nextLine();
            inputText.append(line); //Add all of the lines together
            if(sc.hasNextLine()) //If this is not the last line
                inputText.append('\n'); //Between each two lines there is \n
        }
        return inputText.toString(); //Return the contents of the input file as a String
    }
    public static HashMap<Character, Integer> extractFrequnecy(String inputText)  {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for(int i = 0 ;  i < inputText.length() ; i++)
            incrementFreq(inputText.charAt((i)), freqMap);
        return freqMap;
    }

    public static Node buildHuffmanTree(ArrayList<Node> lst) {
        Queue<Node> q = new PriorityQueue<>(lst);//build PriorityQueue with elements of lst
        while(q.size() > 1) {
            Node a = q.remove();//Node with the lowest frequency
            Node b = q.remove();//Node with the second lowest frequency
            Node c = new Node(a, b);//Build Node c based on the merge of a and b
            q.add(c);
        }
        Node head = q.remove();
        return head;
    }

    public static void encode(File inFile, File outFile) throws FileNotFoundException {
        String inputText = readFile(inFile);
        HashMap<Character, Integer> freqMap = extractFrequnecy(inputText);

        ArrayList<Node> sortedChars = new ArrayList<>();
        for(Map.Entry elem: freqMap.entrySet()) {
            Node node = new Node((Integer) elem.getValue(), (Character) elem.getKey());
            sortedChars.add(node);
        }
        Collections.sort(sortedChars);
        Collections.reverse(sortedChars);
        printFreqMap(sortedChars);

        Node head = buildHuffmanTree(sortedChars);

        HashMap<Character, String> codes = new HashMap<>();
        traverse(head, "", codes);
        printCodes(codes, sortedChars);


        StringBuilder out = new StringBuilder("");
        for(int i = 0 ; i < inputText.length() ; i++)
            out.append(codes.get(inputText.charAt(i)));

        PrintWriter pw = new PrintWriter(outFile);
        pw.println(codes.size());
        for(Node node: sortedChars) {
            if(node.getCh() != ' ' && node.getCh() != '\n')
                pw.print(node.getCh());
            else if(node.getCh() == ' ')
                pw.print("[space]");
            else
                pw.print("[newline]");
            pw.print(" ");
            pw.print(codes.get(node.getCh()));
            pw.print("\n");
        }
        pw.print(out);
        pw.close();
        System.out.println("Original File: " + inputText.length() + " ASCII chars = " + inputText.length() + "*16 = " + inputText.length()*16 + " bits");
        System.out.println("Compressed File: " + out.length() + "bits");
        System.out.println("Ratio of compression: " + (double) out.length() / (inputText.length()*16));
    }
    public static void decode(File inFile, File outFile) throws FileNotFoundException {
        Scanner sc = new Scanner(inFile);
        int num = sc.nextInt();
        HashMap<String, Character> codes = new HashMap<>();
        for(int i = 0 ; i < num ; i++) {
            String ch = sc.next();
            String bin = sc.next();
            if(ch.charAt(0) != '[')
                codes.put(bin, ch.charAt(0));
            else {
                if(ch.charAt(1) == 's')
                    codes.put(bin, ' ');
                else
                    codes.put(bin, '\n');
            }
        }

        String input = sc.next();
        String tmp = "";
        StringBuilder out = new StringBuilder("");
        for(int i = 0 ; i < input.length() ; i++) {
            tmp = tmp + input.charAt(i);
            if(codes.containsKey(tmp)) {
                out.append(codes.get(tmp));
                tmp = "";
            }
        }
        PrintWriter pw = new PrintWriter(outFile);
        pw.print(out);
        pw.close();
    }

    public static void main(String[] args) {

        String errMsg = "The program should be executed as following:\n " +
                "Java Huffman [command] [fileToRead] [fileToWrite]\n" +
                "Command is either encode or decode\n" +
                "If the command is encode: Java Huffman encode [textFile] [encodedFile]\n" +
                "If the command is decode: java Huffman decode [encodedFile] [textFile]";
        if(args.length < 3) {
            System.out.println();
            System.out.println(errMsg + "\n Length of arguments should be 3");
            return;
        }
        else if(!args[0].equals("encode") && !args[0].equals("decode")) {
            System.out.println(errMsg + "\nc Command is not incode or decode");
            return;
        }
        else {
            try {
                File inFile = new File(args[1]);
                File outFile = new File(args[2]);
                if(!inFile.exists() || !inFile.isFile()) {
                    System.out.println(errMsg + "\nInput File does not exist");
                    return;
                }
                if(args[0].equals("encode"))
                    encode(inFile, outFile);
                else
                    decode(inFile, outFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        



    }
}
