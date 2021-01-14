public interface Contesters {
    String name = "";
    void run();
    void jump();
    int getJumpHeight();
    int getRunDistance();
    String getName();
    void failedJump();
    void failedRun();
}
