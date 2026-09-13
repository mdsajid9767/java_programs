package dsa.recursion;

public class RecursionMain {

    public static void printNameNTimes(int n, int i, String name) {
        if (i==n) return;

        System.out.println(name);
        printNameNTimes(n, i+1, name);
    }

    public static void main(String[] args) {
        printNameNTimes(4, 0, "Sajid");
    }
}


