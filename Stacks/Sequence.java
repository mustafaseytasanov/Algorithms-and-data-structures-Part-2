import java.io.*;
import java.util.Stack;

/*
Рассмотрим последовательность, состоящую из круглых, квадратных и фигурных скобок. Программа дожна 
определить, является ли данная скобочная последовательность правильной. Пустая последовательность 
явлется правильной. Если A – правильная, то последовательности (A), [A], {A} – правильные. Если A и B – правильные 
последовательности, то последовательность AB – правильная.

Формат ввода
В единственной строке записана скобочная последовательность, содержащая не более 100000 скобок.

Формат вывода
Если данная последовательность правильная, то программа должна вывести строку yes, иначе строку no.
*/

public class Sequence {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String str = reader.readLine();
        FileWriter writer = new FileWriter("output.txt");
        if (str == null || str.equals("")) {
            writer.write("yes");
        } else {
            String result = function(str);
            writer.write(result);
        }
        writer.flush();
    }

    public static String function(String str) {
        Stack<Character> stack = new Stack<>();
        char symbol;
        for (int i = 0; i < str.length(); i++) {
            symbol = str.charAt(i);
            if (symbol == ')') {
                if (stack.empty()) {
                    return "no";
                }
                if (stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.push(symbol);
                }
            } else if (symbol == ']') {
                if (stack.empty()) {
                    return "no";
                }
                if (stack.peek() == '[') {
                    stack.pop();
                } else {
                    stack.push(symbol);
                }

            } else if (symbol == '}') {
                if (stack.empty()) {
                    return "no";
                }
                if (stack.peek() == '{') {
                    stack.pop();
                } else {
                    stack.push(symbol);
                }
            } else {
                stack.push(symbol);
            }
        }
        if (stack.size() == 0) {
            return "yes";
        } else {
            return "no";
        }
    }

}
