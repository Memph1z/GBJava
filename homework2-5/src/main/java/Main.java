import java.util.concurrent.TimeUnit;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;
    private static long newTime;
    private static long currentTime;

    public static void main(String[] args) {
        float[] arr = new float[size];
        singleThread(arr);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        multiThread(arr);
    }

    public static void initArr(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
    }

    public static void singleThread(float[] arr) {
        System.out.println("Начато вычисление в однопоточном режиме...");
        initArr(arr);
        currentTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        newTime = System.currentTimeMillis();
        System.out.println("Операция выполнена за " + (newTime - currentTime) + "мс");
    }

    public static void multiThread(float[] arr) {
        System.out.println("Начато вычисление в многопоточном режиме...");
        initArr(arr);
        float[] firstPart = new float[h];
        float[] secondPart = new float[h];
        currentTime = System.currentTimeMillis();
        Thread firstThread = new Thread(() -> {
            System.arraycopy(arr, 0, firstPart, 0, h);
            for (int i = 0; i < h; i++) {
                firstPart[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.arraycopy(firstPart, 0, arr, 0, h);
        });
        Thread secondThread = new Thread(() -> {
            System.arraycopy(arr, h - 1, secondPart, 0, h);
            for (int i = 0; i < h; i++) {
                secondPart[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.arraycopy(secondPart, 0, arr, h-1, h);
        });

        firstThread.start();
        secondThread.start();

        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        newTime = System.currentTimeMillis();
        System.out.println("Операция выполнена за " + (newTime - currentTime) + "мс");
    }
}
