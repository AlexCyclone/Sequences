import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Input sequence: ");
        System.out.println(nextInSequence(readArray()));
    }

    public static int[] readArray() {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] stringArray = line.split("[ ,.]+");

        int[] array = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            array[i] = Integer.parseInt(stringArray[i]);
        }
        return array;
    }

    public static int nextInSequence(int... array) {
        if (array.length < 3) {
            throw new IllegalArgumentException("There are not enough elements to define");
        }

        int val = nextArithmetic(array);

        if (val != Integer.MIN_VALUE) {
            return val;
        }

        val = nextGeometric(array);
        if (val != Integer.MIN_VALUE) {
            return val;
        }

        val = nextFibonacci(array);
        if (val != Integer.MIN_VALUE) {
            return val;
        }

        val = nextExp(array);
        if (val != Integer.MIN_VALUE) {
            return val;
        }

        throw new IllegalArgumentException("Undefined sequence");
    }

    public static int nextArithmetic(int... array) {
        int k = array[1] - array[0];

        for (int i = 1; i < (array.length - 1); i++) {
            if ((array[i + 1] - array[i]) != k) {
                return Integer.MIN_VALUE;
            }
        }

        return array[array.length - 1] + k;
    }

    public static int nextGeometric(int... array) {
        if (linearSearch(array, 0) >= 0 || (array[1] % array[0]) != 0) {
            return Integer.MIN_VALUE;
        }

        int k = array[1] / array[0];

        for (int i = 1; i < (array.length - 1); i++) {
            if ((array[i + 1] / array[i]) != k || (array[i + 1] % array[i]) != 0) {
                return Integer.MIN_VALUE;
            }
        }

        return array[array.length - 1] * k;
    }

    public static int nextFibonacci(int... array) {
        for (int i = 2; i < array.length; i++) {
            if (array[i] != array[i - 1] + array[i - 2]) {
                return Integer.MIN_VALUE;
            }
        }

        return array[array.length - 2] + array[array.length - 1];
    }

    public static int nextExp(int... array) {
        int k = greaterThan(3, array);
        if (k == -1) {
            return Integer.MIN_VALUE;
        }

        int q = 0;
        int n = 0;
        for (int i = 2; q == 0 && i <= Math.sqrt(array[k]); i++) {
            for (int j = 2; Math.pow(i, j) <= array[k]; j++) {
                if (Math.pow(i, j) == array[k]) {
                    n = i - k;
                    q = j;
                    break;
                }
            }
        }

        if (q == 0) {
            return Integer.MIN_VALUE;
        }

        for (int i = 0; i < array.length - 1; i++) {
            if (Math.pow(i + n, q) != array[i]) {
                return Integer.MIN_VALUE;
            }
        }

        return (int) Math.pow(array.length + n, q);
    }

    private static int linearSearch(int[] array, int value) {
        int i = 0;

        for (int element : array) {
            if (element == value) {
                return i;
            }
            i += 1;
        }

        return -1;
    }

    private static int greaterThan(int value, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] > value) {
                return i;
            }
        }

        return -1;
    }
}
