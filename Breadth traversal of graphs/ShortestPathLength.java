import java.io.*;
import java.util.*;

/*
В неориентированном графе требуется найти длину минимального пути между двумя вершинами.

Формат ввода
Первым на вход поступает число N – количество вершин в графе (1 ≤ N ≤ 100). Затем записана матрица смежности (0 обозначает 
отсутствие ребра, 1 – наличие ребра). Далее задаются номера двух вершин – начальной и конечной.

Формат вывода
Выведите L – длину кратчайшего пути (количество ребер, которые нужно пройти).
Если пути нет, нужно вывести -1.
*/

public class ShortestPathLength {

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
        int currentLength = 0;
        path[currentLength] = list;
        visitedVertices.add(start);
        boolean isFound = false;
        while (currentLength < n) {
            for (int elem: path[currentLength]) {
                if (elem == finish) {
                    writer.write(String.valueOf(currentLength));
                    isFound = true;
                }
            }
            if (isFound) {
                break;
            }
            list = new ArrayList<>();
            for (int elem: path[currentLength]) {
                list2 = adjacency[elem];
                for (int elem2: list2) {
                    if (!visitedVertices.contains(elem2)) {
                        list.add(elem2);
                        visitedVertices.add(elem2);
                    }
                }
            }
            currentLength++;
            path[currentLength] = list;
        }
        if (!isFound) {
            writer.write(String.valueOf(-1));
        }
        writer.flush();
    }
}
