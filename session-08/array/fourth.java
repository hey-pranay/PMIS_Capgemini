
/*

problem 4: find the second largest element 

problem statement : given an array of integers, find the second largest distinct element in a single traversal without sorting the array. 

*/

package array;

public class fourth {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };

        if (arr.length < 2) {
            System.out.println("array must contain more than two elements");
        }

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];
            } else if (arr[i] > secondLargest && arr[i] != largest) {
                secondLargest = arr[i];
            }
        }
        if (secondLargest == Integer.MIN_VALUE) {
            System.out.println("No second largest distinct element found");
        } else {
            System.out.println("Second largest element: " + secondLargest);
        }

    }
}
