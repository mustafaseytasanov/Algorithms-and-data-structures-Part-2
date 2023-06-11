import java.io.*;

/*
На клетчатой плоскости закрашено K клеток. Требуется найти минимальный по площади прямоугольник, 
со сторонами, параллельными линиям сетки, покрывающий все закрашенные клетки.

Формат ввода
Во входном файле, на первой строке, находится число K (1 ≤ K ≤ 100). На следующих K строках находятся 
пары чисел Xi и Yi – координаты закрашенных клеток (|Xi|, |Yi| ≤ 109).

Формат вывода
Выведите в выходной файл координаты левого нижнего и правого верхнего углов прямоугольника.
*/

public class MinRectangle {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        int k = Integer.parseInt(str);
        int idx = 0;
        String[] arr;
        int[][] coordinates = new int[k][2];
        while (idx < k) {
            str = reader.readLine();
            arr = str.split(" ");
            coordinates[idx][0] = Integer.parseInt(arr[0]);
            coordinates[idx][1] = Integer.parseInt(arr[1]);
            idx++;
        }
        int minX = coordinates[0][0], minY = coordinates[0][1], maxX = coordinates[0][0],
                maxY = coordinates[0][1];
        for (int i = 1; i < k; i++) {
            minX = Math.min(minX, coordinates[i][0]);
            maxX = Math.max(maxX, coordinates[i][0]);
            minY = Math.min(minY, coordinates[i][1]);
            maxY = Math.max(maxY, coordinates[i][1]);
        }
        writer.write(minX + " " + minY + " " + maxX + " " + maxY);
        writer.flush();
    }
}
