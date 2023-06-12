import java.io.*;
import java.util.*;

/*
Отсортируйте данный массив. Используйте пирамидальную сортировку.

Формат ввода
Первая строка входных данных содержит количество элементов в массиве N, N ≤ 10**5. Далее задаются N целых чисел, 
не превосходящих по абсолютной величине 10**9.

Формат вывода
Выведите эти числа в порядке неубывания.
*/

public class HeapSort {
    static List<Integer> heap = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        int n = Integer.parseInt(str);
        str = reader.readLine();
        String[] array = str.split(" ");
        for (int i = 0; i < n; i++) {
            heap.add(Integer.parseInt(array[i]));
        }
        // Creating heap for max elements
        int heapSize = heap.size();
        int lastIndex = ((heapSize - 1) - 1) / 2;
        while (lastIndex >= 0) {
            creatingHeapForMax(lastIndex, heapSize);
            lastIndex--;
        }
        int temp = heap.get(0);
        heap.set(0, heap.get(heapSize - 1));
        heap.set(heapSize - 1, temp);
        heapSize--;
        // Sorting
        while (heapSize > 1) {
            creatingHeapForMax(0, heapSize);
            temp = heap.get(0);
            heap.set(0, heap.get(heapSize - 1));
            heap.set(heapSize - 1, temp);
            heapSize--;
        }
        for (int i = 0; i < n; i++) {
            writer.write(heap.get(i) + " ");
        }
        writer.flush();
    }

    public static void creatingHeapForMax(int index, int heapSize) {
        int currentIndex = index;
        int number, number2, number3, temp;
        while (currentIndex*2 + 1 <= heapSize - 1)  {
            if (currentIndex*2 + 1 == heapSize - 1) {
                if (heap.get(currentIndex) < heap.get(currentIndex*2+1)) {
                    temp = heap.get(currentIndex);
                    heap.set(currentIndex, heap.get(currentIndex*2+1));
                    heap.set(currentIndex*2+1, temp);
                }
                break;
            } else {
                number = heap.get(currentIndex);
                number2 = heap.get(currentIndex*2 + 1);
                number3 = heap.get(currentIndex*2 + 2);
                if ((number < number2) || (number < number3)) {
                    if (number2 > number3) {
                        heap.set(currentIndex, number2);
                        heap.set(currentIndex*2 + 1, number);
                        currentIndex = currentIndex*2 + 1;
                    } else {
                        heap.set(currentIndex, number3);
                        heap.set(currentIndex*2 + 2, number);
                        currentIndex = currentIndex*2 + 2;
                    }
                } else {
                    break;
                }
            }
        }
    }

}
