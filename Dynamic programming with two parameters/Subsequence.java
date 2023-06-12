import java.io.*;
import java.util.*;

/*
Даны две последовательности, требуется найти и вывести их наибольшую общую подпоследовательность.

Формат ввода
В первой строке входных данных содержится число N – длина первой последовательности (1 ≤ N ≤ 1000). Во второй 
строке заданы члены первой последовательности (через пробел) – целые числа, не превосходящие 10000 по модулю.

В третьей строке записано число M – длина второй последовательности (1 ≤ M ≤ 1000). В четвертой строке задаются 
члены второй последовательности (через пробел) – целые числа, не превосходящие 10000 по модулю.

Формат вывода
Требуется вывести наибольшую общую подпоследовательность данных последовательностей, через пробел.
*/

public class Subsequence {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        //
        String str = reader.readLine();
        str = str.trim();
        int n = Integer.parseInt(str);
        str = reader.readLine();
        str = str.trim();
        String[] arr = str.split(" ");
        int[] array1 = new int[n+1];
        for (int i = 1; i <= n; i++) {
            array1[i] = Integer.parseInt(arr[i-1]);
        }
        str = reader.readLine();
        str = str.trim();
        int m = Integer.parseInt(str);
        str = reader.readLine();
        str = str.trim();
        arr = str.split(" ");
        int[] array2 = new int[m+1];
        for (int i = 1; i <= m; i++) {
            array2[i] = Integer.parseInt(arr[i-1]);
        }
        // Implementation
        int[][] matrix = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (array2[i] == array1[j]) {
                    matrix[i][j] = matrix[i-1][j-1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]);
                }
            }
        }
        // Output recovering
        int row = m, col = n;
        List<Integer> output = new ArrayList<>();
        int number = matrix[row][col];
        while (matrix[row][col] != 0) {

            if (matrix[row-1][col-1] == matrix[row][col-1]
                    && matrix[row-1][col-1] == matrix[row-1][col]
                    && matrix[row-1][col-1] < matrix[row][col]) {
                row--;
                col--;
            } else {
                if (matrix[row - 1][col] > matrix[row][col - 1]) {
                    row--;
                } else {
                    col--;
                }
            }
            if (matrix[row][col] < number) {
                output.add(array2[row+1]);
                number = matrix[row][col];
            }
        }
        Collections.reverse(output);
        for (int element: output) {
            writer.write(element + " ");
        }
        writer.flush();
    }
}
