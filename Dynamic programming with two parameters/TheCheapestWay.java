import java.io.*;

/*
В каждой клетке прямоугольной таблицы N × M записано некоторое число. Изначально игрок находится в левой верхней клетке. 
За один ход ему разрешается перемещаться в соседнюю клетку либо вправо, либо вниз (влево и вверх перемещаться запрещено). 
При проходе через клетку с игрока берут столько килограммов еды, какое число записано в этой клетке (еду берут также за 
первую и последнюю клетки его пути).
Требуется найти минимальный вес еды в килограммах, отдав которую игрок может попасть в правый нижний угол.

Формат ввода
Вводятся два числа N и M — размеры таблицы (1≤N≤20, 1≤M≤20). Затем идет N строк по M чисел в каждой — размеры штрафов 
в килограммах за прохождение через соответствующие клетки (числа от 0 до 100).

Формат вывода
Выведите минимальный вес еды в килограммах, отдав которую можно попасть в правый нижний угол.
*/

public class TheCheapestWay {
    static double inf = Double.POSITIVE_INFINITY;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        String[] arr = str.split(" ");
        int n = Integer.parseInt(arr[0]);
        int m = Integer.parseInt(arr[1]);
        int[][] prices = new int[n+1][m+1];
        int[][] minWeight = new int[n+1][m+1];
        int number;
        for (int i = 0; i < n; i++) {
            str = reader.readLine();
            arr = str.split(" ");
            for (int j = 0; j < m; j++) {
                number = Integer.parseInt(arr[j]);
                prices[i+1][j+1] = number;
            }
        }
        for (int i = 2; i < m+1; i++) {
            minWeight[0][i] = (int) inf;
        }
        for (int i = 1; i < n+1; i++) {
            minWeight[i][0] = (int) inf;
        }
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                minWeight[i][j] = Math.min(minWeight[i-1][j], minWeight[i][j-1]) + prices[i][j];
            }
        }
        writer.write(String.valueOf(minWeight[n][m]));
        writer.flush();
    }
}
