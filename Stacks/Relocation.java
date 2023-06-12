import java.io.*;
import java.util.Stack;

/*
Лайнландия представляет из себя одномерный мир, являющийся прямой, на котором располагаются N городов, 
последовательно пронумерованных от 0 до N - 1 . Направление в сторону от первого города к нулевому названо 
западным, а в обратную — восточным.
Когда в Лайнландии неожиданно начался кризис, все были жители мира стали испытывать глубокое смятение. 
По всей Лайнландии стали ходить слухи, что на востоке живётся лучше, чем на западе.
Так и началось Великое Лайнландское переселение. Обитатели мира целыми городами отправились на восток, 
покинув родные улицы, и двигались до тех пор, пока не приходили в город, в котором средняя цена проживания 
была меньше, чем в родном.

Формат ввода
В первой строке дано одно число N (2 ≤ N ≤ 10**5) — количество городов в Лайнландии. Во второй строке 
дано N чисел a_i (0 ≤ a_i ≤ 10**9) — средняя цена проживания в городах с нулевого по (N - 1)-ый соответственно.

Формат вывода
Для каждого города в порядке с нулевого по (N - 1)-ый выведите номер города, в который переселятся 
его изначальные жители. Если жители города не остановятся в каком-либо другом городе, отправившись 
в Восточное Бесконечное Ничто, выведите -1 .
*/

public class Relocation {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        int n = Integer.parseInt(str);
        str = reader.readLine();
        String[] array = str.split(" ");
        int[][] prices = new int[n][2];
        for (int i = 0; i < n; i++) {
            prices[i][0] = i;
            prices[i][1] = Integer.parseInt(array[i]);
        }
        int[] result = new int[n];
        for (int i = 0; i < result.length; i++) {
            result[i] = -1;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(prices[0][0]);
        stack.push(prices[0][1]);
        int number, idx;
        for (int i = 1; i < n; i++) {
            while (!stack.empty()) {
                number = stack.peek();
                if (prices[i][1] >= number) {
                    //stack.push(prices[i][0]);
                    //stack.push(prices[i][1]);
                    break;
                } else {
                    stack.pop();
                    idx = stack.pop();
                    result[idx] = prices[i][0];
                }
            }
            stack.push(prices[i][0]);
            stack.push(prices[i][1]);
        }
        for (int i = 0; i < result.length; i++) {
            writer.write(result[i] + " ");
        }
        writer.flush();
    }
}
