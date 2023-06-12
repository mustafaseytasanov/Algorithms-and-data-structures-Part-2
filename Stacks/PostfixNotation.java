import java.io.*;
import java.util.Stack;

/*
В постфиксной записи (или обратной польской записи) операция записывается после двух операндов. Например, 
сумма двух чисел A и B записывается как A B +. Запись B C + D * обозначает привычное нам (B + C) * D, а 
запись A B C + D * + означает A + (B + C) * D. Достоинство постфиксной записи в том, что она не требует 
скобок и дополнительных соглашений о приоритете операторов для своего чтения.

Формат ввода
В единственной строке записано выражение в постфиксной записи, содержащее цифры и операции +, -, *. Цифры 
и операции разделяются пробелами. В конце строки может быть произвольное количество пробелов.

Формат вывода
Необходимо вывести значение записанного выражения.
*/

public class PostfixNotation {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String str = reader.readLine();
        char ch;
        int idx = 0;
        int value1, value2;
        Stack<String> stack = new Stack<>();
        while (idx < str.length()) {
            ch = str.charAt(idx);
            if (ch == ' ') {
                break;
            }
            if ((ch == '+') || (ch == '-') || (ch == '*')) {
                value2 = Integer.parseInt(stack.pop());
                value1 = Integer.parseInt(stack.pop());
                if (ch == '+') {
                    stack.push(String.valueOf(value1 + value2));
                } else if (ch == '-') {
                    stack.push(String.valueOf(value1 - value2));
                } else {
                    stack.push(String.valueOf(value1 * value2));
                }
            } else {
                stack.push(String.valueOf(ch));
            }
            idx += 2;
        }
        FileWriter writer = new FileWriter("output.txt");
        writer.write(stack.pop());
        writer.flush();
    }
}
