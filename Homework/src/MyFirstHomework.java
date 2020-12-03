public class MyFirstHomework {

    public static void main(String [] args) {
        byte b1 = 127;
        short s1 = 32767;
        int i1 = 2147483647;
        long l1 = 9223372036854775807L;
        float f1 = 1.1234567F;
        double d1 = 1.123456789012345D;
        char c1 = 'F';
        boolean b2 = true;

        isLeapYear(1600);
    }

    public static float someMaths(float a, float b, float c, float d) {
        float sum = a * (b + (c / d));
        return sum;
    }

    public static boolean range(int a, int b) {
        int sum2 = a + b;
        boolean isInRange = false;
        if (sum2 >= 10 && sum2 <= 20) {
            isInRange = true;
            return isInRange;
        } else {
            return isInRange;
        }
    }

    public static void positive(int a) {
        if (a >= 0) {
            System.out.println("Число " + a + " положительное!");
        }else {
            System.out.println("Число " + a + " отрицательное!");
        }
    }

    public static boolean negative(int a) {
        boolean isNegative = false;
        if (a < 0) {
            isNegative = true;
            return isNegative;
        }else{
            return isNegative;
        }
    }

    public static void hello(String name) {
        System.out.println("Привет, " + name + "!");
    }

    public static void isLeapYear(int a) {
        if ((a % 400) == 0) {
            System.out.println(a + " год является високосным.");
        }else if ((a % 100) ==0) {
            System.out.println(a + " год не является високосным.");
        }else if ((a % 4) == 0) {
            System.out.println(a + " год является високосным.");
        }else {
            System.out.println(a + " год не является високосным.");
        }
    }


}
