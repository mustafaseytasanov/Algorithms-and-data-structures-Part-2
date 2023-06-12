import java.io.*;
import java.util.*;

/*
Метрополитен состоит из нескольких линий метро. Все станции метро в городе пронумерованы натуральными числами от 1 до N. 
На каждой линии расположено несколько станций. Если одна и та же станция расположена сразу на нескольких линиях, 
то она является станцией пересадки и на этой станции можно пересесть с любой линии, которая через нее проходит, 
на любую другую (опять же проходящую через нее).

Напишите программу, которая по данному вам описанию метрополитена определит, с каким минимальным числом пересадок 
можно добраться со станции A на станцию B. Если данный метрополитен не соединяет все линии в одну систему, то может 
так получиться, что со станции A на станцию B добраться невозможно, в этом случае ваша программа должна это определить.

Формат ввода
Сначала вводится число N — количество станций метро в городе (2≤N≤100). Далее следует число M — количество линий метро (1≤M≤20). 
Далее идет описание M линий. Описание каждой линии состоит из числа P_i — количество станций на этой линии (2≤P_i≤50) и P_i чисел, 
задающих номера станций, через которые проходит линия (ни через какую станцию линия не проходит дважды).

Затем вводятся два различных числа: A — номер начальной станции, и B — номер станции, на которую нам нужно попасть. При этом 
если через станцию A проходит несколько линий, то мы можем спуститься на любую из них. Так же если через станцию B 
проходит несколько линий, то нам не важно, по какой линии мы приедем.

Формат вывода
Выведите минимальное количество пересадок, которое нам понадобится. Если добраться со станции A на станцию B невозможно, 
программа должна вывести одно число –1 (минус один).
*/

public class Metro {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        int n = Integer.parseInt(str);
        str = reader.readLine();
        int m = Integer.parseInt(str);
        ArrayList<Integer>[] lines = new ArrayList[m];
        ArrayList<Integer> line;
        Set<Integer>[] linesSet = new Set[m];
        Set<Integer> set;
        String[] arr;
        int p;
        for (int i = 0; i < m; i++) {
            line = new ArrayList<>();
            set = new HashSet<>();
            str = reader.readLine();
            arr = str.split(" ");
            p = Integer.parseInt(arr[0]);
            for (int j = 1; j <= p; j++) {
                line.add(Integer.parseInt(arr[j]));
                set.add(Integer.parseInt(arr[j]));
            }
            lines[i] = line;
            linesSet[i] = set;
        }
        str = reader.readLine();
        arr = str.split(" ");
        int a = Integer.parseInt(arr[0]);
        int b = Integer.parseInt(arr[1]);
        // Implementation
        Map<Integer, Set<Integer>> adjacencyMap = new HashMap<>();
        Map<Integer, Integer> allVertices = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m - 1; j++) {
                for (int k = j + 1; k < m; k++) {
                    if (linesSet[j].contains(i) && linesSet[k].contains(i)) {
                        allVertices.put(i*100 + j, -1);
                        set = adjacencyMap.get(i*100 + j);
                        if (set == null) {
                            set = new HashSet<>();
                        }
                        set.add(i*100 + k);
                        adjacencyMap.put(i*100 + j, set);
                        //
                        allVertices.put(i*100 + k, -1);
                        set = adjacencyMap.get(i*100 + k);
                        if (set == null) {
                            set = new HashSet<>();
                        }
                        set.add(i*100 + j);
                        adjacencyMap.put(i*100 + k, set);
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            line = lines[i];
            for (int j = 0; j < line.size() - 1; j++) {
                set = adjacencyMap.get(line.get(j) * 100 + i);
                if (set == null) {
                    set = new HashSet<>();
                }
                set.add(line.get(j + 1) * 100 + i);
                adjacencyMap.put(line.get(j) * 100 + i, set);
                allVertices.put(line.get(j) * 100 + i, -1);
                //
                set = adjacencyMap.get(line.get(j + 1) * 100 + i);
                if (set == null) {
                    set = new HashSet<>();
                }
                set.add(line.get(j) * 100 + i);
                adjacencyMap.put(line.get(j + 1) * 100 + i, set);
                allVertices.put(line.get(j + 1) * 100 + i, -1);
            }
        }
        // Vertices visiting
        ArrayDeque<Integer>[] dequeues = new ArrayDeque[25];
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 20; i++) {
            if (allVertices.get(a*100 + i) != null) {
                allVertices.put(a*100 + i, 0);
                deque.addLast(a*100 + i);
            }
        }
        dequeues[0] = deque;
        //
        int weight = 0;
        int number, idx;
        Set<Integer> set2;
        int[] array;
        while (dequeues[weight] != null) {
            deque = dequeues[weight];
            while (!deque.isEmpty()) {
                number = deque.removeFirst();
                set = adjacencyMap.get(number);
                array = new int[set.size()];
                idx = 0;
                for (int elem: set) {
                    array[idx++] = elem;
                }
                for (int neighbor: array) {
                    if (Math.abs(neighbor - number) < 100) {
                        if (allVertices.get(neighbor) == -1) {
                            allVertices.put(neighbor, weight + 1);
                            if (dequeues[weight + 1] == null) {
                                dequeues[weight + 1] = new ArrayDeque<>();
                            }
                            dequeues[weight + 1].addLast(neighbor);
                        } else {
                            if (allVertices.get(neighbor) > weight + 1) {
                                allVertices.put(neighbor, weight + 1);
                                if (dequeues[weight + 1] == null) {
                                    dequeues[weight + 1] = new ArrayDeque<>();
                                }
                                dequeues[weight + 1].addLast(neighbor);
                            }
                        }
                    } else {
                        if (allVertices.get(neighbor) == -1) {
                            allVertices.put(neighbor, weight);
                            deque.addLast(neighbor);
                        } else {
                            if (allVertices.get(neighbor) > weight) {
                                allVertices.put(neighbor, weight);
                                dequeues[weight].addLast(neighbor);
                            }
                        }
                    }
                    set2 = adjacencyMap.get(neighbor);
                    set2.remove(number);
                    adjacencyMap.put(neighbor, set2);
                    //
                    set2 = adjacencyMap.get(number);
                    set2.remove(neighbor);
                    adjacencyMap.put(number, set2);
                }
            }
            weight++;
        }
        // Output
        List<Integer> allPossibleAnswers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (allVertices.get(b*100 + i) != null) {
                number = allVertices.get(b*100 + i);
                allPossibleAnswers.add(number);
            }
        }
        int minOutput = allPossibleAnswers.get(0);
        for (int i = 1; i < allPossibleAnswers.size(); i++) {
            minOutput = Math.min(allPossibleAnswers.get(i), minOutput);
        }
        writer.write(String.valueOf(minOutput));
        writer.flush();
    }
}
