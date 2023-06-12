import java.io.*;
import java.util.*;

/*
На клеточном поле, размером NxM (2 ≤ N, M ≤ 250) сидит Q (0 ≤ Q ≤ 10000) блох в различных клетках. "Прием пищи" блохами 
возможен только в кормушке - одна из клеток поля, заранее известная. Блохи перемещаются по полю странным образом, а именно, 
прыжками, совпадающими с ходом обыкновенного шахматного коня. Длина пути каждой блохи до кормушки определяется 
как количество прыжков. Определить минимальное значение суммы длин путей блох до кормушки или, если собраться блохам 
у кормушки невозможно, то сообщить об этом. Сбор невозможен, если хотя бы одна из блох не может попасть к кормушке.

Формат ввода
В первой строке входного файла находится 5 чисел, разделенных пробелом: N, M, S, T, Q. N, M - размеры доски (отсчет 
начинается с 1); S, T - координаты клетки - кормушки (номер строки и столбца соответственно), Q - количество блох на доске. 
И далее Q строк по два числа - координаты каждой блохи.

Формат вывода
Содержит одно число - минимальное значение суммы длин путей или -1, если сбор невозможен.
*/

public class Fleas {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        String[] arr = str.split(" ");
        int n = Integer.parseInt(arr[0]);
        int m = Integer.parseInt(arr[1]);
        int s = Integer.parseInt(arr[2]);
        int t = Integer.parseInt(arr[3]);
        int q = Integer.parseInt(arr[4]);

        int[][] coordinates = new int[q][2];
        for (int i = 0; i < q; i++) {
            str = reader.readLine();
            arr = str.split(" ");
            coordinates[i][0] = Integer.parseInt(arr[0]) + 1;
            coordinates[i][1] = Integer.parseInt(arr[1]) + 1;
        }

        // Implementation
        int[][] matrix = new int[n+4][m+4];
        for (int i = 0; i < n+4; i++) {
            for (int j = 0; j < m+4; j++) {
                matrix[i][j] = -1;
            }
        }
        matrix[s+1][t+1] = 0;

        ArrayList<Integer> iterationNew;
        ArrayList<Integer> iterationOld;
        iterationOld = new ArrayList<>();
        iterationOld.add(s+1);
        iterationOld.add(t+1);

        int x, y;
        int iteration = 0;

        while (!iterationOld.isEmpty()) {

            iteration++;

            iterationNew = new ArrayList<>();
            for (int i = 0; i < iterationOld.size(); i += 2) {
                x = iterationOld.get(i);
                y = iterationOld.get(i+1);

                if ((x < 2) || (y < 2) || (x > n+1) || (y > m+1)) {
                    continue;
                }
                // 1 - 4 cases
                if (matrix[x-2][y-1] == -1) {
                    matrix[x-2][y-1] = iteration;
                    iterationNew.add(x-2);
                    iterationNew.add(y-1);
                }
                if (matrix[x-2][y+1] == -1) {
                    matrix[x-2][y+1] = iteration;
                    iterationNew.add(x-2);
                    iterationNew.add(y+1);
                }
                if (matrix[x+2][y-1] == -1) {
                    matrix[x+2][y-1] = iteration;
                    iterationNew.add(x+2);
                    iterationNew.add(y-1);
                }
                if (matrix[x+2][y+1] == -1) {
                    matrix[x+2][y+1] = iteration;
                    iterationNew.add(x+2);
                    iterationNew.add(y+1);
                }
                // 5 - 8 cases
                if (matrix[x-1][y-2] == -1) {
                    matrix[x-1][y-2] = iteration;
                    iterationNew.add(x-1);
                    iterationNew.add(y-2);
                }
                if (matrix[x-1][y+2] == -1) {
                    matrix[x-1][y+2] = iteration;
                    iterationNew.add(x-1);
                    iterationNew.add(y+2);
                }
                if (matrix[x+1][y-2] == -1) {
                    matrix[x+1][y-2] = iteration;
                    iterationNew.add(x+1);
                    iterationNew.add(y-2);
                }
                if (matrix[x+1][y+2] == -1) {
                    matrix[x+1][y+2] = iteration;
                    iterationNew.add(x+1);
                    iterationNew.add(y+2);
                }
            }

            iterationOld = iterationNew;
        }

        int sum = 0;
        for (int i = 0; i < coordinates.length; i++) {
            x = coordinates[i][0];
            y = coordinates[i][1];

            if (matrix[x][y] == - 1) {
                sum = -1;
                break;
            } else {
                sum += matrix[x][y];
            }

        }

        writer.write(String.valueOf(sum));
        writer.flush();
    }
}
