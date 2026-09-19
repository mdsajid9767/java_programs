package dsa.array_questions;

public class ArrayMain {

    // Largest element
    public static int largestElement(int[] arr) {
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    // Second largest element
    public static int secondLargestElement(int[] arr) {
        int largest = Integer.MIN_VALUE;
        int sLargest = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > largest) {
                sLargest = largest;
                largest = arr[i];
            } else if (arr[i] > sLargest && arr[i] != largest) {
                sLargest = arr[i];
            }
        }
        return sLargest;
    }

    // Check if an Array is Sorted
    public static boolean isSorted(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            if (arr[i] < arr[i - 1])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // System.out.println(largestElement(new int[]{1, 2, 3, 12, 20, 0, 43}));
        // System.out.println(secondLargestElement(new int[]{50, 1, 2, 3, 63, 4, 5}));
        System.out.println(isSorted(new int[]{3, 2, 3, 4, 5}));
    }
}
