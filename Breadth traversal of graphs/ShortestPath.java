import java.io.*;
import java.util.*;

/*
В неориентированном графе требуется найти минимальный путь между двумя вершинами.

Формат ввода
Первым на вход поступает число N – количество вершин в графе (1 ≤ N ≤ 100). Затем записана матрица 
смежности (0 обозначает отсутствие ребра, 1 – наличие ребра). Далее задаются номера двух вершин – 
начальной и конечной.

Формат вывода
Выведите сначала L – длину кратчайшего пути (количество ребер, которые нужно пройти), а потом сам путь. 
Если путь имеет длину 0, то его выводить не нужно, достаточно вывести длину.

Необходимо вывести путь (номера всех вершин в правильном порядке). Если пути нет, нужно вывести -1.
*/

public class ShortestPath {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        String[] arr;
        int n = Integer.parseInt(str);
        List<Integer>[] adjacency = new List[n+1];
        Set<Integer> visitedVertices = new HashSet<>();
        List<Integer> list, list2;
        int number;
        int idx = 1;
        while (idx <= n) {
            str = reader.readLine();
            arr = str.split(" ");
            list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                number = Integer.parseInt(arr[i]);
                if (number == 1) {
                    list.add(i+1);
                }
            }
            adjacency[idx] = list;
            idx++;
        }
        str = reader.readLine();
        arr = str.split(" ");
        int start = Integer.parseInt(arr[0]);
        int finish = Integer.parseInt(arr[1]);
        List<Integer>[] path = new List[n+1];
        list = new ArrayList<>();
        list.add(start);
        list.add(-1);
        int currentLength = 0;
        path[currentLength] = list;
        visitedVertices.add(start);
        int savedIndex = -1;
        while (currentLength < n) {
            for (int i = 0; i < path[currentLength].size(); i += 2) {
                int elem = path[currentLength].get(i);
                if (elem == finish) {
                    writer.write(currentLength + "\n");
                    savedIndex = i;
                    break;
                }
            }
            if (savedIndex != -1) {
                break;
            }
            list = new ArrayList<>();
            for (int i = 0; i < path[currentLength].size(); i += 2) {
                int elem = path[currentLength].get(i);
                list2 = adjacency[elem];
                for (int elem2: list2) {
                    if (!visitedVertices.contains(elem2)) {
                        list.add(elem2);
                        list.add(i);
                        visitedVertices.add(elem2);
                    }
                }
            }
            currentLength++;
            path[currentLength] = list;
        }
        List<Integer> output = new ArrayList<>();
        if (savedIndex == -1) {
            writer.write(String.valueOf(-1));
        } else {
            int pathIdx = currentLength;
            if (pathIdx != 0) {
                while (pathIdx >= 0) {
                    number = path[pathIdx].get(savedIndex);
                    savedIndex = path[pathIdx].get(savedIndex + 1);
                    output.add(number);
                    pathIdx--;
                }
                for (int i = output.size() - 1; i >= 0; i--) {
                    writer.write(output.get(i) + " ");
                }
            }
        }
        writer.flush();
    }
}
