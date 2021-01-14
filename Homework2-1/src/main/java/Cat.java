import java.lang.reflect.Array;

public class Cat implements Contesters{
    private final String name = "Котик";
    private final int jumpHeight = 3;
    private final int runDistance = 300;
    @Override
    public void jump(){
        System.out.println("*Cat Boink*");
    }
    @Override
    public void run(){
        System.out.println("*Cat Tapatapa*");
    }
    @Override
    public void failedRun() {
        System.out.println("Котенька споткнулся...");
    }

    @Override
    public void failedJump() {
        System.out.println("Котенька еще маленький...");
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public int getRunDistance() {
        return runDistance;
    }

    public String getName() {
        return name;
    }
}
