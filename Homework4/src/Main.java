import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        startGame();
    }

    /**
     * Сделаны пункты 1, 2 и 4
     * ИИ блокирует в приоритете по горизонтали, потом вертикаль, потом диагонали
     */

    public static void startGame(){
        int size = 3;
        char player = 'X';
        char ai = '0';
        char[][] field = createField(size);
        while (true) {
            printField(field);
            playerTurn(field, player);
            if (checkWin(field, player)) {
                System.out.println("Player won!!!");
                break;
            }
            if (checkDraw(field)){
                System.out.println("It's a draw");
                break;
            }
            aiEnMove(field, ai);
            if (checkWin(field, ai)) {
                System.out.println("You lost");
                break;
            }
        }
    }

    public static char[][] createField(int size){
        char[][] field = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = '•';
            }
        }
        return field;
    }

    public static void printField(char[][] values) {
        for(int i = 0; i < values.length; ++i) {
            for(int j = 0; j < values[i].length; ++j) {
                System.out.print(values[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void printSeparator() {
        for (int i = 0; i < 2; ++i) {
            System.out.println();
        }
    }

    public static void playerTurn(char[][] field, char sign){
        int y, x;
        do {
            x = enterCrd(field.length, 'X');
            y = enterCrd(field.length, 'Y');
        }while (field[x][y] != '•');
        field[x][y] = sign;
        printField(field);
        printSeparator();
    }

    public static int enterCrd(int length, char axis){
        int crd;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.printf("Please enter an %s-coordinate in range of 1-3%n", axis);
            crd = scanner.nextInt() - 1;
        } while (crd < 0 || crd >= length);
        return crd;
    }

    static boolean checkWin(char[][] field, char sign){
        char[] winCond = new char[field.length];
        char[] buffer = new char[field.length];
        for (int i = 0; i < field.length; i++) {
            winCond[i] = sign;
        }
        //horizontals & verticals
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                buffer[j] = field[i][j];
            }
            if (Arrays.equals(buffer, winCond)){
                return true;
            }
            for (int k = 0; k < field[i].length; k++) {
                buffer[k] = field[k][i];
            }
            if (Arrays.equals(buffer, winCond)){
                return true;
            }
        }
        //main diagonal
        for (int i = 0; i < field.length; i++) {
                buffer[i] = field[i][i];
        }
        if (Arrays.equals(buffer, winCond)){
            return true;
        }
        //additional diagonal
        for (int i = 0, j = field.length - 1; i < field.length && j >= 0; i++, j--) {
            buffer[i] = field[i][j];
        }
        if (Arrays.equals(buffer, winCond)){
            return true;
        }
        return false;
    }

    static boolean checkDraw(char[][] field) {
        int freeCells = field.length * field.length;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] != '•') {
                    freeCells--;
                }
            }
        }
        return freeCells == 0;
    }

    static void aiEnMove(char[][] field, char sign){
        int score = 0;
        int tempcount = 0;
        int isEmpty = 0;
        int emptyX = 0;
        int emptyY = 0;
        int tempX = -1;
        int tempY = -1;
        //horizontals
        for (int i = 0; i < field.length; i++) {
            tempcount = 0;
            isEmpty = 0;
            tempX = -1;
            tempY = -1;
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 'X'){
                    tempcount++;
                }
                if (field[i][j] == '•'){
                    isEmpty = 1;
                    tempX = i;
                    tempY = j;
                }
            }
            if (tempcount > score && isEmpty == 1){
                score = tempcount;
                emptyX = tempX;
                emptyY = tempY;
            }
        }
        //verticals
        for (int i = 0; i < field.length; i++) {
            tempcount = 0;
            isEmpty = 0;
            tempX = -1;
            tempY = -1;
            for (int k = 0; k < field[i].length; k++) {
                if (field[k][i] == 'X'){
                    tempcount++;
                }
                if (field[k][i] == '•'){
                    isEmpty = 1;
                    tempX = k;
                    tempY = i;
                }
            }
            if (tempcount > score && isEmpty == 1){
                score = tempcount;
                emptyX = tempX;
                emptyY = tempY;
            }
        }
            //main diagonal
        tempcount = 0;
        isEmpty = 0;
        tempX = -1;
        tempY = -1;
        for (int i = 0; i < field.length; i++) {
            if (field[i][i] == 'X'){
                tempcount++;
            }
            if (field[i][i] == '•'){
                isEmpty = 1;
                tempX = i;
                tempY = i;
            }
        }
        if (tempcount > score && isEmpty == 1){
            score = tempcount;
            emptyX = tempX;
            emptyY = tempY;
        }
        //additional diagonal
        tempcount = 0;
        isEmpty = 0;
        tempX = -1;
        tempY = -1;
        for (int i = 0, j = field.length - 1; i < field.length && j >= 0; i++, j--) {
            if (field[i][j] == 'X'){
                tempcount++;
            }
            if (field[i][j] == '•'){
                isEmpty = 1;
                tempX = i;
                tempY = i;
            }
        }
        if (tempcount > score && isEmpty == 1){
            score = tempcount;
            emptyX = tempX;
            emptyY = tempY;
        }
        field[emptyX][emptyY] = sign;
        printField(field);
        printSeparator();
    }
}
