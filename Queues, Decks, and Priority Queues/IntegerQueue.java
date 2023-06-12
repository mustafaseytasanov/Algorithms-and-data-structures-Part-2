import java.io.*;
import java.util.*;

/*
Научитесь пользоваться стандартной структурой данных queue для целых чисел. Напишите программу, 
содержащую описание очереди и моделирующую работу очереди, реализовав все указанные здесь методы. 

Программа считывает последовательность команд и в зависимости от команды выполняет ту или иную операцию. 
После выполнения каждой команды программа должна вывести одну строчку.

Возможные команды для программы:

push n
Добавить в очередь число n (значение n задается после команды). Программа должна вывести ok.

pop
Удалить из очереди первый элемент. Программа должна вывести его значение.

front
Программа должна вывести значение первого элемента, не удаляя его из очереди.

size
Программа должна вывести количество элементов в очереди.

clear
Программа должна очистить очередь и вывести ok.

exit
Программа должна вывести bye и завершить работу.

Перед исполнением операций front и pop программа должна проверять, содержится ли в очереди хотя бы 
один элемент. Если во входных данных встречается операция front или pop, и при этом очередь пуста, 
то программа должна вместо числового значения вывести строку error.

Формат ввода
Вводятся команды управления очередью, по одной на строке

Формат вывода
Требуется вывести протокол работы очереди, по одному сообщению на строке
*/

public class IntegerQueue {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str;
        Queue<Long> queue = new LinkedList<>();
        long number;
        String[] array;
        while (true) {
            str = reader.readLine();
            if (str.equals("exit")) {
                writer.write("bye");
                break;
            }
            switch (str) {
                case "pop":
                    if (queue.isEmpty()) {
                        writer.write("error");
                    } else {
                        number = queue.remove();
                        writer.write(String.valueOf(number));
                    }
                    break;
                case "front":
                    if (queue.isEmpty()) {
                        writer.write("error");
                    } else {
                        number = queue.peek();
                        writer.write(String.valueOf(number));
                    }
                    break;
                case "size":
                    number = queue.size();
                    writer.write(String.valueOf(number));
                    break;
                case "clear":
                    queue.clear();
                    writer.write("ok");
                    break;
                default:
                    array = str.split(" ");
                    number = Long.parseLong(array[1]);
                    queue.add(number);
                    writer.write("ok");
            }
            writer.write("\n");
        }
        writer.write("\n");
        writer.flush();
    }
}
