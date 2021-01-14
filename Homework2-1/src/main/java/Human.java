public class Human implements Contesters{
    private final String name = "Человечек";
    private final int jumpHeight = 2;
    private final int runDistance = 500;
    @Override
    public void jump(){
        System.out.println("*Human Boink*");
    }
    @Override
    public void run(){
        System.out.println("*Human Tapatapa*");
    }

    @Override
    public void failedRun() {
            System.out.println("У человечка были маленькие ножки");
    }

    @Override
    public void failedJump() {
        System.out.println("Не вышел ростом человечек");
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
