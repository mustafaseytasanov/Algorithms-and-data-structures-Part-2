import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

/*
Научитесь пользоваться стандартной структурой данных stack для целых чисел. Напишите программу, 
содержащую описание стека и моделирующую работу стека, реализовав все указанные здесь методы. 
Программа считывает последовательность команд и в зависимости от команды выполняет ту или иную 
операцию. После выполнения каждой команды программа должна вывести одну строчку. Возможные команды для программы:

push n
Добавить в стек число n (значение n задается после команды). Программа должна вывести ok.

pop
Удалить из стека последний элемент. Программа должна вывести его значение.

back
Программа должна вывести значение последнего элемента, не удаляя его из стека.

size
Программа должна вывести количество элементов в стеке.

clear
Программа должна очистить стек и вывести ok.

exit
Программа должна вывести bye и завершить работу.

Перед исполнением операций back и pop программа должна проверять, содержится ли в стеке хотя бы один элемент. 
Если во входных данных встречается операция back или pop, и при этом стек пуст, то программа должна вместо 
числового значения вывести строку error.

Формат ввода
Вводятся команды управления стеком, по одной на строке

Формат вывода
Программа должна вывести протокол работы стека, по одному сообщению на строке
*/

public class IntegerStack {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String str;
        Stack<Integer> stack = new Stack<>();
        FileWriter writer = new FileWriter("output.txt");
        int number;
        String[] array;
        while (true) {
            str = reader.readLine();
            if (str.equals("exit")) {
                writer.write("bye");
                break;
            }
            switch (str) {
                case "pop":
                    if (stack.empty()) {
                        writer.write("error");
                    } else {
                        number = stack.pop();
                        writer.write(String.valueOf(number));
                    }
                    break;
                case "back":
                    if (stack.empty()) {
                        writer.write("error");
                    } else {
                        number = stack.peek();
                        writer.write(String.valueOf(number));
                    }
                    break;
                case "size":
                    number = stack.size();
                    writer.write(String.valueOf(number));
                    break;
                case "clear":
                    stack.clear();
                    writer.write(String.valueOf("ok"));
                    break;
                default:
                    array = str.split(" ");
                    number = Integer.parseInt(array[1]);
                    stack.push(number);
                    writer.write(String.valueOf("ok"));
            }
            writer.write("\n");
        }


        writer.flush();
    }
}
