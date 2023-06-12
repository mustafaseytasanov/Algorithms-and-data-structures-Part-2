import java.io.*;

/*
Дана прямоугольная доска N × M (N строк и M столбцов). В левом верхнем углу находится шахматный конь, которого 
необходимо переместить в правый нижний угол доски. В данной задаче конь может перемещаться на две клетки вниз и 
одну клетку вправо или на одну клетку вниз и две клетки вправо.

Необходимо определить, сколько существует различных маршрутов, ведущих из левого верхнего в правый нижний угол.

Формат ввода
Входной файл содержит два натуральных числа N и M (1 <= N, M <= 50).

Формат вывода
В выходной файл выведите единственное число — количество способов добраться конём до правого нижнего угла доски.
*/

public class Knight {
    static double negativeInf = Double.NEGATIVE_INFINITY;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        String[] arr = str.split(" ");
        int n = Integer.parseInt(arr[0]);
        int m = Integer.parseInt(arr[1]);
        int[][] maxWeight = new int[n+4][m+4];
        maxWeight[2][3] = 1;
        maxWeight[3][2] = 1;
        for (int i = 1; i < m+4; i++) {
            maxWeight[0][i] = (int) negativeInf;
        }
        for (int i = 1; i < n+4; i++) {
            maxWeight[i][0] = (int) negativeInf;
        }
        for (int i = 2; i < n+4; i++) {
            for (int j = 2; j < m+4; j++) {
                if (maxWeight[i-2][j-1] > 0) {
                    maxWeight[i][j] += maxWeight[i-2][j-1];
                }
                if (maxWeight[i-1][j-2] > 0) {
                    maxWeight[i][j] += maxWeight[i-1][j-2];
                }
            }
        }
        if (n == 1 && m == 1) {
            writer.write(1 + "\n");
        } else {
            writer.write(maxWeight[n][m] + "\n");
        }
        writer.flush();
    }
}
