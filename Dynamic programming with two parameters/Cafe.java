import java.io.*;
import java.util.*;

/*
Около Петиного университета недавно открылось новое кафе, в котором действует следующая система скидок: при каждой покупке более 
чем на 100 рублей покупатель получает купон, дающий право на один бесплатный обед (при покупке на сумму 100 рублей и меньше такой 
купон покупатель не получает).

Однажды Пете на глаза попался прейскурант на ближайшие N дней. Внимательно его изучив, он решил, что будет обедать в этом кафе все N дней, 
причем каждый день он будет покупать в кафе ровно один обед. Однако стипендия у Пети небольшая, и поэтому он хочет по максимуму 
использовать предоставляемую систему скидок так, чтобы его суммарные затраты были минимальны. 
Требуется найти минимально возможную суммарную стоимость обедов и номера дней, в которые Пете следует воспользоваться купонами.

Формат ввода
В первой строке входного файла записано целое число N (0 ≤ N ≤ 100). В каждой из последующих N строк записано одно целое число, 
обозначающее стоимость обеда в рублях на соответствующий день. Стоимость — неотрицательное целое число, не превосходящее 300.

Формат вывода
В первой строке выдайте минимальную возможную суммарную стоимость обедов. Во второй строке выдайте два числа K1 и K2 — количество купонов, 
которые останутся неиспользованными у Пети после этих N дней и количество использованных им купонов соответственно.

В последующих K2 строках выдайте в возрастающем порядке номера дней, когда Пете следует воспользоваться купонами. Если существует несколько 
решений с минимальной суммарной стоимостью, то выдайте то из них, в котором значение K1 максимально (на случай, 
если Петя когда-нибудь ещё решит заглянуть в это кафе). Если таких решений несколько, выведите любое из них.
*/

public class Cafe {

    public static int inf = (int) Double.POSITIVE_INFINITY;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        int n = Integer.parseInt(str);
        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            str = reader.readLine();
            prices[i] = Integer.parseInt(str);
        }
        // Implementation
        if (n == 0) {
            writer.write(0 + "\n");
            writer.write(0 + " " + 0);
            writer.flush();
            return;
        }
        int[][] matrix = new int[n+1][n+2];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n+1; j++) {
                matrix[i][j] = inf;
            }
        }
        matrix[0][1] = 0;
        int[][] matrixPath = new int[n+1][n+2];
        int price;
        for (int i = 0; i < n; i++) {
            price = prices[i];
            for (int j = 1; j <= n; j++) {
                if (matrix[i][j] != inf) {
                    if (price <= 100) {
                        if (matrix[i+1][j] > matrix[i][j] + price) {
                            matrix[i+1][j] = matrix[i][j] + price;
                            matrixPath[i+1][j] = j;
                        }
                    } else {
                        if (matrix[i+1][j+1] > matrix[i][j] + price) {
                            matrix[i+1][j+1] = matrix[i][j] + price;
                            matrixPath[i+1][j+1] = j;
                        }
                    }
                    if (matrix[i+1][j-1] > matrix[i][j]) {
                        matrix[i+1][j-1] = matrix[i][j];
                        matrixPath[i+1][j-1] = j;
                    }
                }
            }
        }
        // Output
        int minCost = inf;
        for (int i = 1; i <= n + 1; i++) {
            minCost = Math.min(minCost, matrix[n][i]);
        }
        writer.write(minCost + "\n");
        int k1 = 0;
        for (int i = n + 1; i >= 1; i--) {
            if (matrix[n][i] == minCost) {
                k1 = i - 1;
                break;
            }
        }
        List<Integer> days = new ArrayList<>();
        int column = k1 + 1;
        int prev;
        for (int i = n; i > 0; i--) {
            prev = matrixPath[i][column];
            if (prev > column) {
                days.add(i);
            }
            column = prev;
        }
        Collections.reverse(days);
        writer.write(k1 + " " + days.size() + "\n");
        for (int day: days) {
            writer.write(day + " ");
        }
        writer.flush();
    }
}
