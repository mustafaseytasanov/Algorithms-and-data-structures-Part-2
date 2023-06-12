import java.io.*;
import java.util.*;

/*
Имеется калькулятор, который выполняет следующие операции:
-умножить число X на 2;
-умножить число X на 3;
-прибавить к числу X единицу.
Определите, какое наименьшее количество операций требуется, чтобы получить из числа 1 число N.

Формат ввода
Во входном файле написано натуральное число N, не превосходящее 10**6.

Формат вывода
В первой строке выходного файла выведите минимальное количество операций. Во второй строке выведите 
числа, последовательно получающиеся при выполнении операций. Первое из них должно быть равно 1, 
а последнее N. Если решений несколько, выведите любое.
*/

public class Calculator {
    static double inf = Double.POSITIVE_INFINITY;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        int n = Integer.parseInt(str);
        // Implementation
        int[][] array = new int[n+1][3];
        for (int i = 1; i <= n; i++) {
            array[i][0] = i;
            array[i][1] = (int) inf;
        }
        array[n][1] = 0;
        int elem1, elem2;
        for (int i = n; i > 1; i--) {
            elem1 = array[i][0];
            elem2 = array[i][1];
            if (array[i-1][1] == (int) inf) {
                array[i-1][1] = elem2 + 1;
                array[i-1][2] = array[i][0];
            } else {
                if (elem2 + 1 < array[i-1][1]) {
                    array[i-1][1] = elem2 + 1;
                    array[i-1][2] = array[i][0];
                }
                //array[i-1][1] = Math.min(elem2 + 1, array[i-1][1]);
            }
            if (elem1 % 2 == 0) {
                if (array[i/2][1] == (int) inf) {
                    array[i/2][1] = elem2 + 1;
                    array[i/2][2] = array[i][0];
                } else {
                    if (elem2 + 1 < array[i/2][1]) {
                        array[i/2][1] = elem2 + 1;
                        array[i/2][2] = array[i][0];
                    }
                    //array[i/2][1] = Math.min(elem2 + 1, array[i/2][1]);
                }
            }
            if (elem1 % 3 == 0) {
                if (array[i/3][1] == (int) inf) {
                    array[i/3][1] = elem2 + 1;
                    array[i/3][2] = array[i][0];
                } else {
                    if (elem2 + 1 < array[i/3][1]) {
                        array[i/3][1] = elem2 + 1;
                        array[i/3][2] = array[i][0];
                    }
                    //array[i/3][1] = Math.min(elem2 + 1, array[i/3][1]);
                }
            }
        }
        writer.write(array[1][1] + "\n");
        writer.write(1 + " ");
        int prev = array[1][2];
        while (array[prev][0] != 0) {
            writer.write(prev + " ");
            prev = array[prev][2];
        }
        //
        writer.flush();
    }
}
