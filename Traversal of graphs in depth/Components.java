import java.io.*;
import java.util.*;

/*
Дан неориентированный невзвешенный граф, состоящий из N вершин и M ребер. Необходимо посчитать 
количество его компонент связности и вывести их.

Формат ввода
Во входном файле записано два числа N и M (0 < N ≤ 100000, 0 ≤ M ≤ 100000). В следующих M строках записаны по 
два числа i и j (1 ≤ i, j ≤ N), которые означают, что вершины i и j соединены ребром.

Формат вывода
В первой строчке выходного файла выведите количество компонент связности. Далее выведите сами компоненты связности 
в следующем формате: в первой строке количество вершин в компоненте, во второй - сами вершины в произвольном порядке.
*/

public class Components {

    static Map<Integer, Boolean> isVisited = new HashMap<>();
    static ArrayList<Integer> vertices;
    static ArrayList<ArrayList<Integer>> allOutputData = new ArrayList<>();


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
            isVisited.put(i, false);
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
            // put for 2 vertex
            edges = map.get(v2);
            if (map.get(v2) == null) {
                edges = new ArrayList<>();
            }
            edges.add(v1);
            map.put(v2, edges);
            // continue
            idx++;
        }
        Set<Integer> keySet = isVisited.keySet();
        for (int vertex: keySet) {
            if (isVisited.get(vertex) != null) {
                if (isVisited.get(vertex) == false) {
                    vertices = new ArrayList<>();
                    dfs((HashMap) map, vertex);
                    allOutputData.add(vertices);
                }
            }
        }
        writer.write(allOutputData.size() + "\n");
        for (int i = 0; i < allOutputData.size(); i++) {
            vertices = allOutputData.get(i);
            writer.write(vertices.size() + "\n");
            for (int elem: vertices) {
                writer.write(elem + " ");
            }
            writer.write("\n");
        }
        writer.flush();
    }

    static void dfs(HashMap map, int now) {
        vertices.add(now);
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
