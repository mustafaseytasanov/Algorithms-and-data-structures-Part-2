import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
Диего увлекается коллекционированием наклеек. На каждой из них написано число, и каждый 
коллекционер мечтает собрать наклейки со всеми встречающимися числами.

Диего собрал N наклеек, некоторые из которых, возможно, совпадают. Как-то раз к нему 
пришли K коллекционеров. i-й из них собрал все наклейки с номерами не меньшими, чем pi. 
Напишите программу, которая поможет каждому из коллекционеров определить, сколько недостающих 
ему наклеек есть у Диего. Разумеется, гостей Диего не интересуют повторные экземпляры наклеек.

Формат ввода
В первой строке содержится единственное число N (0 ≤ N ≤ 100 000) — количество наклеек у Диего.

В следующей строке содержатся N целых неотрицательных чисел (не обязательно различных) — номера наклеек 
Диего. Все номера наклеек не превосходят 109.

В следующей строке содержится число K (0 ≤ K ≤ 100 000) — количество коллекционеров, пришедших к Диего. 
В следующей строке содержатся K целых чисел pi (0 ≤ pi ≤ 109), где pi — наименьший номер наклейки, 
не интересующий i-го коллекционера.

Формат вывода
Для каждого коллекционера в отдельной строке выведите количество различных чисел на наклейках, 
которые есть у Диего, но нет у этого коллекционера.
*/

public class Diego {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        int n = Integer.parseInt(str);
        str = reader.readLine();
        String[] arr = str.split(" ");
        Set<Integer> stickersSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            stickersSet.add(Integer.parseInt(arr[i]));
        }
        int[] stickers = new int[stickersSet.size()];
        int idx = 0;
        for (int element: stickersSet) {
            stickers[idx] = element;
            idx++;
        }
        str = reader.readLine();
        int k = Integer.parseInt(str);
        str = reader.readLine();
        arr = str.split(" ");
        int[] numbers = new int[k];
        for (int i = 0; i < k; i++) {
            numbers[i] = Integer.parseInt(arr[i]);
        }
        Arrays.sort(stickers);
        int l, r, m;
        for (int number: numbers) {
            l = 0;
            r = stickers.length - 1;
            while (l < r) {
                m = (l + r + 1) / 2;
                if (number > stickers[m]) {
                    l = m;
                } else {
                    r = m - 1;
                }
            }
            if (number > stickers[l]) {
                writer.write(l + 1 + "\n");
            } else {
                writer.write(l + "\n");
            }
        }
        writer.flush();
    }
}
