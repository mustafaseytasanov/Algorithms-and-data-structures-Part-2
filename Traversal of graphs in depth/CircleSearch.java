import java.io.*;
import java.util.*;

/*
Дан неориентированный граф. Требуется определить, есть ли в нем цикл, и, если есть, вывести его.

Формат ввода
В первой строке дано одно число n — количество вершин в графе ( 1 ≤ n ≤ 500 ). Далее в n строках 
задан сам граф матрицей смежности.

Формат вывода
Если в иcходном графе нет цикла, то выведите «NO». Иначе, в первой строке выведите «YES», во второй 
строке выведите число k — количество вершин в цикле, а в третьей строке выведите k различных 
чисел — номера вершин, которые принадлежат циклу в порядке обхода (обход можно начинать с любой вершины цикла). 
Если циклов несколько, то выведите любой.
*/

public class CircleSearch {

    static Map<Integer, Integer> isVisited = new HashMap<>();
    static boolean isExistsCircle = false;
    static ArrayList<Integer>[] adjacencyList;
    static ArrayList<Integer> possibleCircle = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        int n = Integer.parseInt(str);
        adjacencyList = new ArrayList[n+1];
        String[] arr;
        int number;
        Set<Integer> set;
        for (int i = 1; i <= n; i++) {
            isVisited.put(i, 0);
        }
        ArrayList<Integer> list;
        for (int i = 1; i <= n; i++) {
            list = new ArrayList<>();
            str = reader.readLine();
            arr = str.split(" ");
            for (int j = 0; j < n; j++) {
                number = Integer.parseInt(arr[j]);
                if (number == 1) {
                    list.add(j+1);
                }
            }
            adjacencyList[i] = list;
        }

        Set<Integer> keySet = isVisited.keySet();
        for (int vertex: keySet) {
            if (isVisited.get(vertex) != null) {
                if (isVisited.get(vertex) == 0) {
                    isVisited.put(vertex, 1);
                    possibleCircle.add(vertex);
                    dfs(vertex);
                    isVisited.put(vertex, 2);
                    possibleCircle.remove(possibleCircle.size() - 1);
                }
            }
        }
        if (!isExistsCircle) {
            System.out.println("NO");
        }
        writer.flush();
    }

    static void dfs(int now) {

        isVisited.put(now, 1);
        ArrayList<Integer> neighbors = adjacencyList[now];
        if (neighbors != null) {
            for (int elem: neighbors) {

                if (possibleCircle.size() >= 2) {
                    if (possibleCircle.get(possibleCircle.size() - 2) == elem) {
                        continue;
                    }
                }
                if (isVisited.get(elem) == 0) {
                    now = elem;
                    isVisited.put(now, 1);
                    possibleCircle.add(now);
                    dfs(now);
                    isVisited.put(now, 2);
                    possibleCircle.remove(possibleCircle.size() - 1);
                } else if ((isVisited.get(elem) == 1) && (!isExistsCircle)) {
                    isExistsCircle = true;
                    System.out.println("YES");
                    ArrayList<Integer> circle = new ArrayList<>();
                    for (int i = possibleCircle.size() - 1; i >= 0; i--) {
                        circle.add(possibleCircle.get(i));
                        if (possibleCircle.get(i) == elem) {
                            break;
                        }
                    }
                    System.out.println(circle.size());
                    for (int vert: circle) {
                        System.out.print(vert + " ");
                    }
                    System.out.println();
                    return;
                }
            }
        }
    }
}
