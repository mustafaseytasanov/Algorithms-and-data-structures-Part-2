import java.io.*;
import java.util.*;

/*
Дан неориентированный граф, возможно, с петлями и кратными ребрами. Необходимо построить компоненту 
связности, содержащую первую вершину.

Формат ввода
В первой строке записаны два целых числа N (1 ≤ N ≤ 10**3) и M (0 ≤ M ≤ 5 * 10**5) — количество вершин и ребер в графе. 
В последующих M строках перечислены ребра — пары чисел, определяющие номера вершин, которые соединяют ребра.

Формат вывода
В первую строку выходного файла выведите число K — количество вершин в компоненте связности. Во вторую строку 
выведите K целых чисел — вершины компоненты связности, перечисленные в порядке возрастания номеров.
*/

public class Depth {

    static Map<Integer, Boolean> isVisited = new HashMap<>();

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
            // put for 2 vertex
            edges = map.get(v2);
            if (map.get(v2) == null) {
                edges = new ArrayList<>();
            }
            edges.add(v1);
            map.put(v2, edges);
            // continue
            isVisited.put(v1, false);
            isVisited.put(v2, false);
            idx++;
        }
        dfs((HashMap) map, 1);
        ArrayList<Integer> visitedVertices = new ArrayList<>();
        Set<Integer> keySet = isVisited.keySet();
        for (int key: keySet) {
            if (isVisited.get(key) == true) {
                visitedVertices.add(key);
            }
        }
        Collections.sort(visitedVertices);
        writer.write(visitedVertices.size() + "\n");
        for (int vertex: visitedVertices) {
            writer.write(vertex + " ");
        }
        writer.flush();
    }

    static void dfs(HashMap map, int now) {
        isVisited.put(now, true);
        ArrayList<Integer> neighbors = (ArrayList<Integer>) map.get(now);
        if (neighbors != null) {
            for (int i = 0; i < neighbors.size(); i++) {
                if (isVisited.get(neighbors.get(i)) == false) {
                    dfs(map, neighbors.get(i));
                }
            }
        }
    }

}
