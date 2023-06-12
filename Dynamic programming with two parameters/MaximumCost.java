import java.io.*;
import java.util.Arrays;

/*
В левом верхнем углу прямоугольной таблицы размером N×M находится черепашка. В каждой клетке таблицы записано некоторое число. 
Черепашка может перемещаться вправо или вниз, при этом маршрут черепашки заканчивается в правом нижнем углу таблицы.
Подсчитаем сумму чисел, записанных в клетках, через которую проползла черепашка (включая начальную и конечную клетку). Найдите 
наибольшее возможное значение этой суммы и маршрут, на котором достигается эта сумма.

Формат ввода
В первой строке входных данных записаны два натуральных числа N и M, не превосходящих 100 — размеры таблицы. Далее идет N строк, 
каждая из которых содержит M чисел, разделенных пробелами — описание таблицы. Все числа в клетках таблицы целые и могут принимать 
значения от 0 до 100.

Формат вывода
Первая строка выходных данных содержит максимальную возможную сумму, вторая — маршрут, на котором достигается эта сумма. Маршрут 
выводится в виде последовательности, которая должна содержать N-1 букву D, означающую передвижение вниз и M-1 букву R, означающую 
передвижение направо. Если таких последовательностей несколько, необходимо вывести ровно одну (любую) из них.
*/

public class MaximumCost {
    static double negativeInf = Double.NEGATIVE_INFINITY;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        String[] arr = str.split(" ");
        int n = Integer.parseInt(arr[0]);
        int m = Integer.parseInt(arr[1]);
        int[][] prices = new int[n+1][m+1];
        int[][] maxWeight = new int[n+1][m+1];
        int[][][] prev = new int[n+1][m+1][2];
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
            maxWeight[0][i] = (int) negativeInf;
        }
        for (int i = 1; i < n+1; i++) {
            maxWeight[i][0] = (int) negativeInf;
        }
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m+1; j++) {
                if (maxWeight[i-1][j] > maxWeight[i][j-1]) {
                    maxWeight[i][j] = maxWeight[i - 1][j] + prices[i][j];
                    prev[i][j][0] = i-1;
                    prev[i][j][1] = j;
                } else {
                    maxWeight[i][j] = maxWeight[i][j-1] + prices[i][j];
                    prev[i][j][0] = i;
                    prev[i][j][1] = j-1;
                }
            }
        }
        int idx1 = n, idx2 = m;
        int number1, number2;
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            if (idx1 == 1 && idx2 == 1)
                break;
            number1 = prev[idx1][idx2][0];
            number2 = prev[idx1][idx2][1];
            if (idx1 - number1 == 1) {
                stringBuilder.insert(0, "D ");
            } else {
                stringBuilder.insert(0, "R ");
            }
            idx1 = number1;
            idx2 = number2;
        }

        writer.write(maxWeight[n][m] + "\n");
        writer.write(stringBuilder.toString());
        writer.flush();
    }
}
