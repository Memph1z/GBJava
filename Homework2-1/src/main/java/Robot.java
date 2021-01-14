public class Robot implements Contesters{
    private final String name = "Робот";
    private final int jumpHeight = 1;
    private final int runDistance = 2000;
    @Override
    public void jump(){
        System.out.println("*Robot Boink*");
    }
    @Override
    public void run(){
        System.out.println("*Robot Tapatapa*");
    }
    @Override
    public void failedRun() {
        System.out.println("Позор расы роботов-бегунов");
    }

    @Override
    public void failedJump() {
        System.out.println("Позор расы роботов-прыгунов");
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
