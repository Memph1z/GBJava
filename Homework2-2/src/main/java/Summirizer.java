public class Summirizer {
    public void sumArray(String[][] array){
        try {
            doLengthCheck(array);
        }catch (MyArraySizeException e){
            e.printStackTrace();
        }
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                }catch (NumberFormatException e){
                    throw new MyArrayDataException("The cell [" + i + "][" + j + "] doesn't contain a number", e);
                }
            }
        }
        System.out.println("The sum of all cells is: " + sum);
    }

    private void doLengthCheck(String[][] array) throws MyArraySizeException{
        doLengthCheck(array.length);
        for (int i = 0; i < array.length; i++) {
            doLengthCheck(array[i].length);
        }
    }
    private void doLengthCheck(int length) throws MyArraySizeException{
        if (length != 4){
            throw new MyArraySizeException("The array length is invalid. Expected [4][4].");
        }
    }
}
