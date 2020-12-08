import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int[] test = new int[]{1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        int[] empty = new int[8];
        int[] bnum = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int[] arr = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int[] ohSoUniqueArr = new int[]{10, 10, 5, 5, 5, 5, 10};
        int[][] matrixHasYou = negArray();
        int[] moveArr = new int[]{1, 2, 3, 4};


//        System.out.println(Arrays.toString(invert(test)));
//        System.out.println(Arrays.toString(fillUp(empty)));
//        System.out.println(Arrays.toString(multiply(bnum)));
//        minMax(arr);
//        System.out.println(sumTrue(ohSoUniqueArr));
//        printArray(matrixHasYou);
//        moveArray(moveArr, 3);
    }


    /**
     * Первое задание
     */

    public static int[] invert(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 1) {
                a[i] = 0;
            } else {
                a[i] = 1;
            }
        }
        return a;
    }

    /**
     * Второе задание
     */

    public static int[] fillUp(int[] a){
        int i = 0;
        for (int j = 0; j <= 21; j+=3){
            a[i] = j;
            i++;
        }
        return a;
    }

    /**
     * Третье задание
     */

    public static int[] multiply(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] < 6){
                a[i] *= 2;
            }
        }
        return a;
    }

    /**
     * Четвертое задание
     */

    static int[][] negArray() {
        int[][] a = new int[4][4];
        for (int i = a.length -1 ; i >= 0; i--) {
            for (int j = a[i].length - 1; j >= 0; j--) {
                a[i][j] = i - j + 1;
            }
        }
        return a;
    }

    /**
     * Пятое задание
     */

    public static void minMax(int[] a){
        int min = 2147483647;
        int max = 0;
        for (int i = 0; i < a.length; i++){
            if (a[i] > max){
                max = a[i];
            }
        }
        for (int m = 0; m < a.length; m++){
            if (a[m] < min){
                min = a[m];
            }
        }
        System.out.println("Минимальное число: " + min);
        System.out.println("Максимальное число: " + max);
    }

    /**
     * Шестое задание
     */

    public static boolean sumTrue(int[] a) {
        int rightSum = a[(a.length - 1)];
        int leftSum = 0;
        for (int j = a.length - 2; j >= 0; j--){
            leftSum += a[j];
        }
        for (int i = a.length - 2; i >= 0; i--){
            if (leftSum == rightSum){
                return true;
            }
            leftSum -= a[i];
            rightSum += a[i];
        }
        return false;
    }

    /**
     * Седьмое задание
     */

    static void moveArray(int[] a, int n) {
        int buffer = 0;
        if (n > 0) {
            for (int i = 1; i <= n; i++) {
                buffer = a[a.length - 1];
                for (int j = a.length - 1; j > 0; j--) {
                    a[j] = a[j - 1];
                }
                a[0] = buffer;
            }
            System.out.println(Arrays.toString(a));
        }else if (n < 0){
            for (int i = 1; i <= (n * -1); i++){
                buffer = a[0];
                for (int j = 0; j < a.length - 1; j++){
                    a[j] = a[j + 1];
                }
                a[a.length - 1] = buffer;
            }
            System.out.println(Arrays.toString(a));
        }else {
            System.out.println(Arrays.toString(a));
        }
    }

    /**
     * Вспомогательный метод
     */

    static void printArray(int[][] values) {
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                System.out.print(values[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
