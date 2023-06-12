import java.io.*;
import java.util.*;

/*
Дан ориентированный граф. Необходимо построить топологическую сортировку.

Формат ввода
В первой строке входного файла два натуральных числа N и M (1 ≤ N, M ≤ 100 000) — количество вершин и рёбер 
в графе соответственно. Далее в M строках перечислены рёбра графа. Каждое ребро задаётся парой чисел — 
номерами начальной и конечной вершин соответственно.

Формат вывода
Выведите любую топологическую сортировку графа в виде последовательности номеров вершин (перестановка чисел от 1 до N). 
Если топологическую сортировку графа построить невозможно, выведите -1.
*/

public class TopologicalSort {

    static Map<Integer, Integer> isVisited = new HashMap<>();
    static List<Integer> list = new ArrayList<>();
    static boolean isExistsCircle = false;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        String[] arr = str.split(" ");
        int n = Integer.parseInt(arr[0]);
        int m = Integer.parseInt(arr[1]);
        int idx = 0;
        Map<Integer, ArrayList> map = new HashMap<>();
        int v1, v2;
        ArrayList<Integer> edges;
        for (int i = 1; i <= n; i++) {
            isVisited.put(i, 0);
        }
        while (idx < m) {
            str = reader.readLine();
            arr = str.split(" ");
            v1 = Integer.parseInt(arr[0]);
            v2 = Integer.parseInt(arr[1]);
            // put for 1 vertex
            edges = map.get(v1);
            if (map.get(v1) == null) {
                edges = new ArrayList<>();
            }
            edges.add(v2);
            map.put(v1, edges);
            // continue
            idx++;
        }
        Set<Integer> keySet = isVisited.keySet();
        for (int vertex: keySet) {
            if (isVisited.get(vertex) != null) {
                if (isVisited.get(vertex) == 0) {
                    dfs((HashMap) map, vertex);
                    isVisited.put(vertex, 2);
                    list.add(vertex);
                }
            }
        }

        Collections.reverse(list);
        if (isExistsCircle) {
            writer.write(String.valueOf(-1));
        } else {
            for (int element: list) {
                writer.write(element + " ");
            }
        }
        writer.flush();
    }

    static void dfs(HashMap map, int now) {
        isVisited.put(now, 1);
        ArrayList<Integer> neighbors = (ArrayList<Integer>) map.get(now);
        if (neighbors != null) {
            for (int i = 0; i < neighbors.size(); i++) {
                if (isVisited.get(neighbors.get(i)) == 0) {
                    now = neighbors.get(i);
                    dfs(map, now);
                    isVisited.put(now, 2);
                    list.add(now);
                } else if (isVisited.get(neighbors.get(i)) == 1) {
                    isExistsCircle = true;
                }
            }
        }
    }

}
