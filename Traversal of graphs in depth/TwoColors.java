import java.io.*;
import java.util.*;

/*
Во время контрольной работы профессор Флойд заметил, что некоторые студенты обмениваются записками. Сначала он хотел 
поставить им всем двойки, но в тот день профессор был добрым, а потому решил разделить студентов на две группы: 
списывающих и дающих списывать, и поставить двойки только первым.

У профессора записаны все пары студентов, обменявшихся записками. Требуется определить, сможет ли он разделить студентов 
на две группы так, чтобы любой обмен записками осуществлялся от студента одной группы студенту другой группы.

Формат ввода
В первой строке находятся два числа N и M — количество студентов и количество пар студентов, обменивающихся записками 
(1 ≤ N ≤ 10**2, 0 ≤ M ≤ N(N−1)/2).

Далее в M строках расположены описания пар студентов: два числа, соответствующие номерам студентов, обменивающихся записками 
(нумерация студентов идёт с 1). Каждая пара студентов перечислена не более одного раза.

Формат вывода
Необходимо вывести ответ на задачу профессора Флойда. Если возможно разделить студентов на две группы - выведите YES; 
иначе выведите NO.
*/

public class TwoColors {

    static Map<Integer, Integer> isVisited = new HashMap<>();
    static int color = 1;

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
                if (isVisited.get(vertex) == 0) {
                    dfs((HashMap) map, vertex);
                }
            }
            //System.out.println();
        }
        ArrayList<Integer> neighbors;
        boolean output = true;
        for (int vertex: keySet) {
            if (map.get(vertex) != null) {
                 neighbors = map.get(vertex);
                 if (isVisited.get(vertex) == 1) {
                     for (int i = 0; i < neighbors.size(); i++) {
                         if (isVisited.get(neighbors.get(i)) != 2) {
                             output = false;
                             break;
                         }
                     }
                 } else if (isVisited.get(vertex) == 2) {
                     for (int i = 0; i < neighbors.size(); i++) {
                         if (isVisited.get(neighbors.get(i)) != 1) {
                             output = false;
                             break;
                         }
                     }
                 }
            }
        }

        if (output) {
            writer.write("YES");
        } else {
            writer.write("NO");
        }
        writer.flush();
    }

    static void dfs(HashMap map, int now) {
        isVisited.put(now, color);
        //System.out.print(now + " : " + color + ", ");
        ArrayList<Integer> neighbors = (ArrayList<Integer>) map.get(now);
        if (neighbors != null) {
            for (int i = 0; i < neighbors.size(); i++) {
                if (isVisited.get(neighbors.get(i)) == 0) {
                    color = 3 - color;
                    dfs(map, neighbors.get(i));
                    color = 3 - color;
                }
            }
        }
    }

}
