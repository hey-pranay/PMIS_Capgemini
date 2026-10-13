package interface_and_wrappers;

/*

    Problem 2: Count character types in a password Task: Given a password string, count how many    uppercase letters, lowercase letters, digits, and special characters it contains. Key concept: Built-in static utility methods of Character.  

*/

public class second {
    public static void main(String[] args) {

        String password = "Pass@123_Me";
        int uppercase = 0;
        int lowercase = 0;
        int digit = 0;
        int special = 0;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                uppercase++;
            } else if (Character.isLowerCase(ch)) {
                lowercase++;
            } else if (Character.isDigit(ch)) {
                digit++;
            } else {
                special++;
            }
        }

        System.out.println("Uppercase count : " + uppercase);
        System.out.println("Lowercase count : " + lowercase);
        System.out.println("digit count : " + digit);
        System.out.println("special count : " + special);
    }

}
