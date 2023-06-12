import java.io.*;
import java.util.Stack;

/*
К тупику со стороны пути 1 (см. рисунок) подъехал поезд. Разрешается отцепить от поезда один или сразу несколько первых 
вагонов и завезти их в тупик (при желании, можно даже завезти в тупик сразу весь поезд). После этого часть из этих 
вагонов вывезти в сторону пути 2. После этого можно завезти в тупик еще несколько вагонов и снова часть оказавшихся 
вагонов вывезти в сторону пути 2. И так далее (так, что каждый вагон может лишь один раз заехать с пути 1 в тупик, 
а затем один раз выехать из тупика на путь 2). Заезжать в тупик с пути 2 или выезжать из тупика на путь 1 запрещается. 
Нельзя с пути 1 попасть на путь 2, не заезжая в тупик.

Известно, в каком порядке изначально идут вагоны поезда. Требуется с помощью указанных операций сделать так, чтобы 
вагоны поезда шли по порядку (сначала первый, потом второй и т.д., считая от головы поезда, едущего по пути 2 в сторону 
от тупика). Напишите программу, определяющую, можно ли это сделать.

Формат ввода
Вводится число N — количество вагонов в поезде (1 ≤ N ≤ 100). Дальше идут номера вагонов в порядке от головы поезда,
едущего по пути 1 в сторону тупика. Вагоны пронумерованы натуральными числами от 1 до N, каждое из которых встречается 
ровно один раз.

Формат вывода
Если сделать так, чтобы вагоны шли в порядке от 1 до N, считая от головы поезда, когда поезд поедет по пути 2 из тупика, 
можно, выведите сообщение YES, если это сделать нельзя, выведите NO.

*/

public class Wagons {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        int n = Integer.parseInt(str);
        str = reader.readLine();
        String[] array = str.split(" ");
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(array[i]);
        }
        Stack<Integer> stack = new Stack<>();
        int idx = 0;
        int[] result = new int[n];
        int number, number2;
        for (int i = 0; i < numbers.length; i++) {
            number = numbers[i];
            while ((!stack.empty()) && (stack.peek() < number)) {
                number2 = stack.pop();
                result[idx] = number2;
                idx++;
            }
            stack.push(number);
        }
        while (!stack.empty()) {
            result[idx] = stack.pop();
            idx++;
        }
        boolean isSorted = true;
        for (int i = 1; i < n; i++) {
            if (result[i] < result[i-1]) {
                isSorted = false;
            }
        }
        if (isSorted) {
            writer.write("YES");
        } else {
            writer.write("NO");
        }
        writer.flush();
    }
}
