import java.util.Scanner;

class BasicOperations {

    static int addition(int a, int b) {
        return a + b;
    }

    static int subtraction(int a, int b) {
        return a - b;
    }

    static int multiplication(int a, int b) {
        return a * b;
    }

    static double division(int a, int b) {
        return (double) a / b;
    }

    static int remainder(int a, int b) {
        return a % b;
    }

    static int square(int a) {
        return a * a;
    }

    static int cube(int a) {
        return a * a * a;
    }

    static int absolute(int a) {
        return Math.abs(a);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        int a = sc.nextInt();

        System.out.print("Enter second number: ");
        int b = sc.nextInt();

        System.out.println("Addition = " + addition(a, b));
        System.out.println("Subtraction = " + subtraction(a, b));
        System.out.println("Multiplication = " + multiplication(a, b));

        if (b != 0) {
            System.out.println("Division = " + division(a, b));
            System.out.println("Remainder = " + remainder(a, b));
        } else {
            System.out.println("Division and remainder not possible by zero.");
        }

        System.out.println("Square of first number = " + square(a));
        System.out.println("Cube of first number = " + cube(a));
        System.out.println("Absolute value of first number = " + absolute(a));
    }
}