import java.io.*;

/*
Вам необходимо ответить на запросы узнать сумму всех элементов числовой матрицы N×M в прямоугольнике с 
левым верхним углом (x1, y1) и правым нижним (x2, y2)

Формат ввода
В первой строке находится числа N, M размеры матрицы (1 ≤ N, M ≤ 1000) и K — количество запросов (1 ≤ K ≤ 100000). 
Каждая из следующих N строк содержит по M чисел`— элементы соответствующей строки матрицы (по модулю не превосходят 1000). 
Последующие K строк содержат по 4 целых числа, разделенных пробелом x1 y1 x2 y2 — запрос на сумму элементов матрице 
в прямоугольнике (1 ≤ x1 ≤ x2 ≤ N, 1 ≤ y1 ≤ y2 ≤ M)

Формат вывода
Для каждого запроса на отдельной строке выведите его результат — сумму всех чисел в элементов матрице 
в прямоугольнике (x1, y1), (x2, y2)
*/

public class SumInRectangle {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        String[] arr = str.split(" ");
        int n = Integer.parseInt(arr[0]);
        int m = Integer.parseInt(arr[1]);
        int k = Integer.parseInt(arr[2]);
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            str = reader.readLine();
            arr = str.split(" ");
            for (int j = 0; j < m; j++) {
                matrix[i][j] = Integer.parseInt(arr[j]);
            }
        }
        int[][] prefixSums = new int[n+1][m+1];
        // Sum of rows
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                prefixSums[i][j] = prefixSums[i][j-1] + matrix[i-1][j-1];
            }
        }
        int idx = 0;
        int x1, y1, x2, y2;
        int output;
        while (idx < k) {
            str = reader.readLine();
            arr = str.split(" ");
            y1 = Integer.parseInt(arr[0]);
            x1 = Integer.parseInt(arr[1]);
            y2 = Integer.parseInt(arr[2]);
            x2 = Integer.parseInt(arr[3]);
            output = 0;
            for (int i = y1; i <= y2; i++) {
                output += prefixSums[i][x2] - prefixSums[i][x1 - 1];
            }
            writer.write(output + "\n");
            idx++;
        }
        writer.flush();
    }
}
