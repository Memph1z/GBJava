import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Contesters[] contesters = new Contesters[3];
        contesters[0] = new Human();
        contesters[1] = new Cat();
        contesters[2] = new Robot();
        String[] scoreboard = new String[contesters.length];
        fillScore(scoreboard);
        Obstacles[] obstacles = new Obstacles[5];
        obstacles[0] = new Wall(random.nextInt(6));
        obstacles[1] = new Threadmill(random.nextInt(3001));
        obstacles[2] = new Wall(random.nextInt(6));
        obstacles[3] = new Threadmill(random.nextInt(3001));
        obstacles[4] = new Wall(random.nextInt(6));
        takeCourse(contesters,obstacles, scoreboard);
        printScoreboard(contesters, scoreboard);

    }
        public static void takeCourse(Contesters[] cont, Obstacles[] obst, String[] score){
            for (int i = 0; i < cont.length; i++) {
                System.out.println("");
                for (int j = 0; j < obst.length; j++) {
                    if (obst[j] instanceof Wall){
                        if (cont[i].getJumpHeight() < ((Wall) obst[j]).getHeight()){
                            cont[i].failedJump();
                            score[i] = "FAIL";
                            break;
                        }
                        cont[i].jump();
                    } else {
                        if (cont[i].getRunDistance() < ((Threadmill) obst[j]).getLength()){
                            cont[i].failedRun();
                            score[i] = "FAIL";
                            break;
                        }
                        cont[i].run();
                    }
                }
            }
        }
        public static void printScoreboard(Contesters[] cont, String[] score){
            System.out.println();
            System.out.println();
            for (int i = 0; i < score.length; i++) {
                System.out.println(cont[i].getName() + " " + score[i]);
                System.out.println();
            }
        }
        public static void fillScore(String[] score){
            for (int i = 0; i < score.length; i++) {
                score[i] = "SUCCESS!";
            }
        }
}
