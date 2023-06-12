import java.io.*;
import java.util.*;

/*
Научитесь пользоваться стандартной структурой данных deque для целых чисел.  Напишите программу, 
содержащую описание дека и моделирующую работу дека, реализовав все указанные здесь методы. 
Программа считывает последовательность команд и в зависимости от команды выполняет ту или иную 
операцию. После выполнения каждой команды программа должна вывести одну строчку.

Возможные команды для программы:

push_front n
Добавить (положить) в начало дека новый элемент. Программа должна вывести ok.

push_back n
Добавить (положить) в конец дека новый элемент. Программа должна вывести ok.

pop_front
Извлечь из дека первый элемент. Программа должна вывести его значение.

pop_back
Извлечь из дека последний элемент. Программа должна вывести его значение.

front
Узнать значение первого элемента (не удаляя его). Программа должна вывести его значение.

back
Узнать значение последнего элемента (не удаляя его). Программа должна вывести его значение.

size
Вывести количество элементов в деке.

clear
Очистить дек (удалить из него все элементы) и вывести ok.

exit
Программа должна вывести bye и завершить работу.

Гарантируется, что количество элементов в деке в любой момент не превосходит 100. Перед исполнением 
операций pop_front, pop_back, front, back программа должна проверять, содержится ли в деке хотя бы 
один элемент. Если во входных данных встречается операция pop_front, pop_back, front, back, и при этом 
дек пуст, то программа должна вместо числового значения вывести строку error.

Формат ввода
Вводятся команды управления деком, по одной на строке.

Формат вывода
Требуется вывести протокол работы дека, по одному сообщению на строке
*/

public class DequeStruct {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str;
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int number;
        String[] array;
        while (true) {
            str = reader.readLine();
            if (str.equals("exit")) {
                writer.write("bye");
                break;
            }
            switch (str) {
                case "pop_front":
                    if (deque.isEmpty()) {
                        writer.write("error");
                    } else {
                        number = deque.removeFirst();
                        writer.write(String.valueOf(number));
                    }
                    break;
                case "pop_back":
                    if (deque.isEmpty()) {
                        writer.write("error");
                    } else {
                        number = deque.removeLast();
                        writer.write(String.valueOf(number));
                    }
                    break;
                case "front":
                    if (deque.isEmpty()) {
                        writer.write("error");
                    } else {
                        number = deque.peekFirst();
                        writer.write(String.valueOf(number));
                    }
                    break;
                case "back":
                    if (deque.isEmpty()) {
                        writer.write("error");
                    } else {
                        number = deque.peekLast();
                        writer.write(String.valueOf(number));
                    }
                    break;
                case "size":
                    number = deque.size();
                    writer.write(String.valueOf(number));
                    break;
                case "clear":
                    deque.clear();
                    writer.write("ok");
                    break;
                default:
                    array = str.split(" ");
                    number = Integer.parseInt(array[1]);
                    if (array[0].equals("push_front")) {
                        deque.addFirst(number);
                        writer.write("ok");
                    } else {
                        deque.addLast(number);
                        writer.write("ok");
                    }
            }
            writer.write("\n");
        }
        writer.write("\n");
        writer.flush();
    }
}
