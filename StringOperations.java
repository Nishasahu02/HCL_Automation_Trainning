import java.util.Scanner;

class StringOperations {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String str = sc.nextLine();

        System.out.println("Length = " + str.length());
        System.out.println("Is Empty = " + str.isEmpty());

        if (!str.isEmpty()) {
            System.out.println("Character at index 0 = " + str.charAt(0));
        }

        System.out.println("toString = " + str.toString());

        System.out.println("Equals 'Hello' = " + str.equals("Hello"));

        System.out.println("compareTo 'Hello' = " + str.compareTo("Hello"));

        System.out.println("Contains 'a' = " + str.contains("a"));

        System.out.println("Index of 'a' = " + str.indexOf('a'));

        System.out.println("Last index of 'a' = " + str.lastIndexOf('a'));

        System.out.println("Starts with 'A' = " + str.startsWith("A"));

        System.out.println("Ends with 'a' = " + str.endsWith("a"));

        System.out.println("Matches letters only = " + str.matches("[a-zA-Z ]+"));

        if (str.length() >= 3) {
            System.out.println("Substring = " + str.substring(0, 3));
        }

        System.out.println("Lowercase = " + str.toLowerCase());

        System.out.println("Trim = " + str.trim());

        System.out.println("Replace a with @ = " + str.replace('a', '@'));

        String[] words = str.split(" ");

        System.out.println("Split:");
        for (String word : words) {
            System.out.println(word);
        }

        System.out.println("Join = " + String.join("-", words));

        System.out.println("ValueOf = " + String.valueOf(100));

        // StringBuffer
        StringBuffer sb = new StringBuffer("Hello");
        sb.append(" World");

        System.out.println("StringBuffer = " + sb);

        // StringBuilder
        StringBuilder builder = new StringBuilder("Hello");
        builder.append(" Java");

        System.out.println("StringBuilder = " + builder);
    }
}