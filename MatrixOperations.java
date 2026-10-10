import java.util.Scanner;

class MatrixOperations {

    static void display(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void addition(int[][] a, int[][] b) {
        int n = a.length;
        int m = a[0].length;

        System.out.println("Addition:");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print((a[i][j] + b[i][j]) + " ");
            }
            System.out.println();
        }
    }

    static void subtraction(int[][] a, int[][] b) {
        int n = a.length;
        int m = a[0].length;

        System.out.println("Subtraction:");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print((a[i][j] - b[i][j]) + " ");
            }
            System.out.println();
        }
    }

    static void multiplication(int[][] a, int[][] b) {

        int r1 = a.length;
        int c1 = a[0].length;
        int c2 = b[0].length;

        int[][] result = new int[r1][c2];

        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < c1; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        System.out.println("Multiplication:");
        display(result);
    }

    static void transpose(int[][] a) {

        System.out.println("Transpose:");

        for (int j = 0; j < a[0].length; j++) {
            for (int i = 0; i < a.length; i++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean isSquare(int[][] a) {
        return a.length == a[0].length;
    }

    static boolean isDiagonal(int[][] a) {

        if (!isSquare(a))
            return false;

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (i != j && a[i][j] != 0)
                    return false;
            }
        }

        return true;
    }

    static boolean isIdentity(int[][] a) {

        if (!isSquare(a))
            return false;

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {

                if (i == j && a[i][j] != 1)
                    return false;

                if (i != j && a[i][j] != 0)
                    return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter rows of matrix A: ");
        int r1 = sc.nextInt();

        System.out.print("Enter columns of matrix A: ");
        int c1 = sc.nextInt();

        int[][] a = new int[r1][c1];

        System.out.println("Enter Matrix A:");
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c1; j++) {
                a[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter rows of matrix B: ");
        int r2 = sc.nextInt();

        System.out.print("Enter columns of matrix B: ");
        int c2 = sc.nextInt();

        int[][] b = new int[r2][c2];

        System.out.println("Enter Matrix B:");
        for (int i = 0; i < r2; i++) {
            for (int j = 0; j < c2; j++) {
                b[i][j] = sc.nextInt();
            }
        }

        if (r1 == r2 && c1 == c2) {
            addition(a, b);
            subtraction(a, b);
        } else {
            System.out.println("Addition and subtraction not possible.");
        }

        if (c1 == r2) {
            multiplication(a, b);
        } else {
            System.out.println("Multiplication not possible.");
        }

        transpose(a);

        if (isSquare(a))
            System.out.println("Matrix A is a square matrix.");
        else
            System.out.println("Matrix A is not a square matrix.");

        if (isDiagonal(a))
            System.out.println("Matrix A is a diagonal matrix.");
        else
            System.out.println("Matrix A is not a diagonal matrix.");

        if (isIdentity(a))
            System.out.println("Matrix A is an identity matrix.");
        else
            System.out.println("Matrix A is not an identity matrix.");
    }
}