import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

/**
 * Created by Никита on 12.05.2016.
 */
public class TestConsole {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] matrixSize = manualGenerateMatrixSize(args, reader);

        int m = matrixSize[0];
        int n = matrixSize[1];

        int[][] matrix = manualFillMatrix(m, n, reader);
        reader.close();

        HashSet<Integer> blackList = generateBlackList(m, n, matrix);

//        displayLimitedMatrix(m, n, matrix, new HashSet<Integer>());
        displayLimitedMatrix(m, n, matrix, blackList);
    }

    private static void displayLimitedMatrix(int m, int n, int[][] matrix, HashSet<Integer> blackList) {
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (blackList.contains(j)){
                    continue;
                }
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println("");
        }
    }

    private static HashSet<Integer> generateBlackList(int m, int n, int[][] matrix) {
        HashSet<Integer> blackList = new HashSet<Integer>();
        int min = matrix[0][0];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (min > matrix[i][j]){
                    blackList.clear();
                    min = matrix[i][j];
                    blackList.add(j);
                } else if (min == matrix[i][j]){
                    if (blackList.contains(j)){
                        continue;
                    }
                    blackList.add(j);
                }
            }
        }
        return blackList;
    }

    private static int[] manualGenerateMatrixSize(String[] args, BufferedReader reader) throws IOException {
        int[] matrixSize = new int[2];
        if (args.length >= 2) {
            try {
                matrixSize[0] = Integer.parseInt(args[0]);
                matrixSize[1] = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Что-то пошло не так. Введите размеры матрицы вручную");
            }
        } else {
            while (true) {
                if (matrixSize[0] != 0 && matrixSize[1] != 0) {
                    break;
                }
                try {
                    matrixSize[0] = Integer.parseInt(reader.readLine());
                    matrixSize[1] = Integer.parseInt(reader.readLine());
                } catch (NumberFormatException e) {
                    System.out.println("Для ввода размера матрицы используйте числа");
                    matrixSize[0] = 0;
                    matrixSize[1] = 0;
                }
            }
        }
        return matrixSize;
    }

    private static int[][] manualFillMatrix(int m, int n, BufferedReader reader) throws IOException {
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            int j = 0;
            while (j < n) {
                try {
                    matrix[i][j] = Integer.parseInt(reader.readLine());
                } catch (NumberFormatException e){
                    System.out.println("Повторите ввод числа.");
                    continue;
                }
                j++;
            }
        }
        return matrix;
    }
}
