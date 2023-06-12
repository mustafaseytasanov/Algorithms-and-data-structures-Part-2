import java.io.*;
import java.util.*;

/*
В этой задаче вам необходимо самостоятельно (не используя соответствующие классы и функции стандартной библиотеки) организовать 
структуру данных Heap для хранения целых чисел, над которой определены следующие операции: 
a) Insert(k) – добавить в Heap число k ; 
b) Extract достать из Heap наибольшее число (удалив его при этом).

Формат ввода
В первой строке содержится количество команд N (1 ≤ N ≤ 100000), далее следуют N команд, каждая в своей строке. Команда может 
иметь формат: “0 <число>” или “1”, обозначающий, соответственно, операции Insert(<число>) и Extract. Гарантируется, 
что при выполнении команды Extract в структуре находится по крайней мере один элемент.

Формат вывода
Для каждой команды извлечения необходимо отдельной строкой вывести число, полученное при выполнении команды Extract.
*/

public class Heap {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        int n = Integer.parseInt(str);
        int command = 0;
        List<Integer> heap = new ArrayList<>();
        String[] array;
        int number, idx, temp, number2, number3;
        while (command < n) {
            str = reader.readLine();
            if (str.length() != 1) { // Adding element
                array = str.split(" ");
                number = Integer.parseInt(array[1]);
                heap.add(number);
                idx = heap.size() - 1;
                while ((idx > 0) && (number > heap.get((idx-1) / 2))) {
                    temp = heap.get((idx-1) / 2);
                    heap.set(idx, temp);
                    heap.set((idx-1) / 2, number);
                    idx = (idx-1) / 2;
                    number = heap.get(idx);
                }
            } else { // Removing max element
                writer.write(heap.get(0) + "\n");
                heap.set(0, heap.get(heap.size() - 1));
                heap.remove(heap.size() - 1);
                idx = 0;
                while (true) {
                    if ((2*idx + 1) > heap.size() - 1) { // without children
                        break;
                    } else if ((2*idx + 1) == heap.size() - 1) { // one child
                        number = heap.get(idx);
                        number2 = heap.get(2*idx + 1);
                        if (number < number2) {
                            temp = heap.get(idx);
                            heap.set(idx, number2);
                            heap.set(2*idx + 1, temp);
                        }
                        break;
                    } else { // two children
                        number = heap.get(idx);
                        number2 = heap.get(2*idx + 1);
                        number3 = heap.get(2*idx + 2);
                        if ((number < number2) || (number < number3)) {
                            if (number2 > number3) {
                                heap.set(idx, number2);
                                heap.set(2*idx + 1, number);
                                idx = 2*idx + 1;
                            } else {
                                heap.set(idx, number3);
                                heap.set(2*idx + 2, number);
                                idx = 2*idx + 2;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
            command++;
        }
        writer.flush();
    }
}
