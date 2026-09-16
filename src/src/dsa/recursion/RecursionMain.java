package dsa.recursion;

public class RecursionMain {

    // print name N time using recursion
    public static void printNameNTimes(int n, int i, String name) {
        if (i == n) return;

        System.out.println(name);
        printNameNTimes(n, i + 1, name);
    }

    // print 1 to N using recursion
    public static void print1toN(int n, int i) {
        if (i > n) return;

        System.out.println(i);
        print1toN(n, i + 1);
    }

    // print N to 1 using recursion
    public static void printNto1(int n) {
        if (n < 1) return;

        System.out.println(n);
        printNto1(n - 1);
    }

    // sum of first N numbers
    public static int sum(int n) {
        if (n == 1) return 1;

        return n + sum(n - 1);
    }

    // Factorial of a given number
    public static int factorial(int n) {
        if (n == 1) return 1;

        return n * factorial(n - 1);
    }

    // Check if String is Palindrome or Not
    public static boolean palindrome(String str, int i, int len) {
        if (str.charAt(i) != str.charAt(len - 1)) return false;

        if (i >= len / 2) return true;
        return palindrome(str, i + 1, len - 1);
    }

    // Fibonacci Number
    public static int fibonacci(int n) {
        if (n <= 1) return n;

        int last = fibonacci(n - 1);
        int secondLast = fibonacci(n - 2);

        return last + secondLast;
    }

    public static void main(String[] args) {
        printNameNTimes(4, 0, "Sajid");
        print1toN(5, 1);
        printNto1(5);
        System.out.println(factorial(5));
        System.out.println(palindrome("madam", 0, 5));
        System.out.println(fibonacci(4));
    }
}


