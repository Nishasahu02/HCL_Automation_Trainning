import java.util.Scanner;

class ArrayOperations {

    static int insert(int[] a, int n, int pos, int value) {
        for (int i = n; i > pos; i--) {
            a[i] = a[i - 1];
        }
        a[pos] = value;
        return n + 1;
    }

    static int delete(int[] a, int n, int pos) {
        for (int i = pos; i < n - 1; i++) {
            a[i] = a[i + 1];
        }
        return n - 1;
    }

    static int linearSearch(int[] a, int n, int key) {
        for (int i = 0; i < n; i++) {
            if (a[i] == key)
                return i;
        }
        return -1;
    }

    static int binarySearch(int[] a, int n, int key) {
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (a[mid] == key)
                return mid;
            else if (a[mid] < key)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }

    static int maximum(int[] a, int n) {
        int max = a[0];

        for (int i = 1; i < n; i++) {
            if (a[i] > max)
                max = a[i];
        }

        return max;
    }

    static void countEvenOdd(int[] a, int n) {
        int even = 0, odd = 0;

        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0)
                even++;
            else
                odd++;
        }

        System.out.println("Even = " + even);
        System.out.println("Odd = " + odd);
    }

    static void insertionSort(int[] a, int n) {
        for (int i = 1; i < n; i++) {
            int key = a[i];
            int j = i - 1;

            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }

            a[j + 1] = key;
        }
    }

    static void display(int[] a, int n) {
        System.out.print("Array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] a = new int[100];
        int n;

        System.out.print("Enter number of elements: ");
        n = sc.nextInt();

        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        int choice;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Insertion");
            System.out.println("2. Deletion");
            System.out.println("3. Linear Search");
            System.out.println("4. Binary Search");
            System.out.println("5. Maximum Value");
            System.out.println("6. Count Even/Odd");
            System.out.println("7. Insertion Sort");
            System.out.println("8. Display");
            System.out.println("9. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter position (0-based): ");
                    int pos = sc.nextInt();

                    System.out.print("Enter value: ");
                    int value = sc.nextInt();

                    if (pos >= 0 && pos <= n) {
                        n = insert(a, n, pos, value);
                        display(a, n);
                    } else {
                        System.out.println("Invalid position.");
                    }
                    break;

                case 2:
                    System.out.print("Enter position to delete (0-based): ");
                    pos = sc.nextInt();

                    if (pos >= 0 && pos < n) {
                        n = delete(a, n, pos);
                        display(a, n);
                    } else {
                        System.out.println("Invalid position.");
                    }
                    break;

                case 3:
                    System.out.print("Enter value to search: ");
                    value = sc.nextInt();

                    int result = linearSearch(a, n, value);

                    if (result != -1)
                        System.out.println("Element found at index " + result);
                    else
                        System.out.println("Element not found.");
                    break;

                case 4:
                    System.out.print("Enter value to search: ");
                    value = sc.nextInt();

                    insertionSort(a, n);

                    result = binarySearch(a, n, value);

                    if (result != -1)
                        System.out.println("Element found at index " + result);
                    else
                        System.out.println("Element not found.");

                    break;

                case 5:
                    if (n > 0)
                        System.out.println("Maximum = " + maximum(a, n));
                    else
                        System.out.println("Array is empty.");
                    break;

                case 6:
                    countEvenOdd(a, n);
                    break;

                case 7:
                    insertionSort(a, n);
                    System.out.println("Array sorted successfully.");
                    display(a, n);
                    break;

                case 8:
                    display(a, n);
                    break;

                case 9:
                    System.out.println("Program ended.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 9);
    }
}