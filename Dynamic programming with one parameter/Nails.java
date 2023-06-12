import java.io.*;
import java.util.Arrays;

/*
В дощечке в один ряд вбиты гвоздики. Любые два гвоздика можно соединить ниточкой. Требуется соединить некоторые пары гвоздиков 
ниточками так, чтобы к каждому гвоздику была привязана хотя бы одна ниточка, а суммарная длина всех ниточек была минимальна.

Формат ввода
В первой строке входных данных записано число N — количество гвоздиков (2 ≤ N ≤ 100). В следующей строке заданы N чисел — координаты 
всех гвоздиков (неотрицательные целые числа, не превосходящие 10000).

Формат вывода
Выведите единственное число — минимальную суммарную длину всех ниточек.
*/

public class Nails {
    static int[] coordinates;
    static int[] arrayDP;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        int n = Integer.parseInt(str);
        str = reader.readLine();
        String[] arr = str.split(" ");
        coordinates = new int[n];
        for (int i = 0; i < n; i++) {
            coordinates[i] = Integer.parseInt(arr[i]);
        }
        Arrays.sort(coordinates);
        // Implementation
        arrayDP = new int[n];
        for (int i = 0; i < n; i++) {
            arrayDP[i] = -1;
        }
        arrayDP[1] = coordinates[1] - coordinates[0];
        if (n > 2) {
            arrayDP[2] = coordinates[2] - coordinates[0];
        }
        if (n > 3) {
            arrayDP[3] = (coordinates[1] - coordinates[0]) + (coordinates[3] - coordinates[2]);
        }
        int result = dp(n-1);
        writer.write(String.valueOf(result));
        //
        writer.flush();
    }

    public static int dp(int index) {

        if (index >= 4) {
            if (arrayDP[index] == -1) {
                arrayDP[index] = Math.min(dp(index - 1), dp(index - 2)) +
                        (coordinates[index] - coordinates[index - 1]);
            }
        }
        return arrayDP[index];
    }
}
