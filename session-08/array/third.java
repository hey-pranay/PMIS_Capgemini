/*

problem 3: reverse an array in-place 

problem statement : reverse the elements of an array without using a second auxiliary array by using the two-pointer approach. 

*/

package array;

public class third {

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };

        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }

        System.out.print("Reversed array: ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}
