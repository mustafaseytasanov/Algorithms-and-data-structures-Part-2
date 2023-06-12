import java.io.*;
import java.util.*;

/*
По данному числу N определите количество последовательностей из нулей и единиц длины N, 
в которых никакие три единицы не стоят рядом.

Формат ввода
Во входном файле написано натуральное число N, не превосходящее 35.

Формат вывода
Выведите количество искомых последовательностей. Гарантируется, что ответ не превосходит 2**31 - 1.
*/

public class ThreeOnes {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        int n = Integer.parseInt(str);
        List<Integer> numberOfSequences = new ArrayList<>();
        numberOfSequences.add(1);
        numberOfSequences.add(2);
        numberOfSequences.add(4);
        int number;
        for (int i = 3; i <= n; i++) {
            number = numberOfSequences.get(i-3) + numberOfSequences.get(i-2) + numberOfSequences.get(i-1);
            numberOfSequences.add(number);
        }
        writer.write(String.valueOf(numberOfSequences.get(n)));
        writer.flush();
    }
}
