import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/*
Вася изучил алгоритмы, устроился на интересную и высокооплачиваемую работу, построил виллу с зимним садом, в котором растут пальмы 
и решил исполнить свою давнюю мечту — купить огромную круглую кровать.
Чтобы внести кровать в спальню необходимо пронести ее через зимний сад. Стены зимнего сада параллельны оси Y. 
Вход в зимний сад находится снизу, а спальня — сверху. Все пальмы в саду при виде сверху представляют собой круг и имеют одинаковых радиус R.
Определите максимальный диаметр круглой кровати, которую можно пронести через зимний сад в горизонтальном положении.

Формат ввода
В первой строке заданы два целых числа  X_L и X_R (−10**6 ≤ X_L ≤ X_R ≤ 10**6) — x-координаты левой и правой стен зимнего сада.
Во второй строке задано целое число R (1 ≤ R ≤ 10**6) — радиус пальм.
В третей задано целое число N (1≤N≤200) — количество пальм в саду.
В следующих N строках задано по два целых числа x и y (−10**6 ≤ x,y ≤ 10**6) — координаты центра пальмы.

Формат вывода
Выведите максимальный диаметр кровати. Ответ должен отличаться от правильного не более чем на 10**(−3) (т.е. на 0.001).
Если нельзя пронести никакую кровать, выведите 0.
*/

public class Bed {

    static List<Integer>[] coordinates;
    static int n, r;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        String[] arr = str.split(" ");
        int xL = Integer.parseInt(arr[0]);
        int xR = Integer.parseInt(arr[1]);
        str = reader.readLine();
        r = Integer.parseInt(str);
        str = reader.readLine();
        n = Integer.parseInt(str);
        coordinates = new ArrayList[3*n+1];
        int x, y;
        List<Integer> list;
        for (int i = 1; i <= n; i++) {
            str = reader.readLine();
            arr = str.split(" ");
            x = Integer.parseInt(arr[0]);
            y = Integer.parseInt(arr[1]);
            list = new ArrayList<>();
            list.add(x);
            list.add(y);
            coordinates[i] = list;
        }
        // Creating vertices of left and right walls
        for (int i = 1; i <= n; i++) {
            list = new ArrayList<>();
            list.add(xL);
            list.add(coordinates[i].get(1));
            coordinates[n+i] = list;
            list = new ArrayList<>();
            list.add(xR);
            list.add(coordinates[i].get(1));
            coordinates[2*n+i] = list;
        }

        double left = 0, right = xR - xL, m;

        while (right - left > 0.001) {

            m = (left + right + 0.001) / 2;
            if (!implementation(m)) {
                left = m;
            } else {
                right = m - 0.001;
            }

        }

        System.out.println(String.format("%.3f", left));

        writer.flush();
    }

    private static boolean implementation(double maxRadius) {

        Set<Integer>[] adjacency = new Set[3*n+1];
        Set<Integer> set;
        double a2, b2, c;
        int length;
        for (int i = 1; i <= n; i++) {
            length = (coordinates[i].get(0) - coordinates[n+i].get(0) - r);
            if (length < maxRadius) {
                set = adjacency[i];
                if (set == null) {
                    set = new HashSet<>();
                }
                set.add(n+i);
                adjacency[i] = set;
                //
                set = adjacency[n+i];
                if (set == null) {
                    set = new HashSet<>();
                }
                set.add(i);
                adjacency[n+i] = set;
            }
            length = (coordinates[2*n+i].get(0) - coordinates[i].get(0) - r);
            if (length < maxRadius) {
                set = adjacency[i];
                if (set == null) {
                    set = new HashSet<>();
                }
                set.add(2*n+i);
                adjacency[i] = set;
                //
                set = adjacency[2*n+i];
                if (set == null) {
                    set = new HashSet<>();
                }
                set.add(i);
                adjacency[2*n+i] = set;
            }
            if (i < n) {
                for (int j = i+1; j <= n; j++) {
                    a2 = Math.pow(coordinates[j].get(0) - coordinates[i].get(0), 2);
                    b2 = Math.pow(coordinates[j].get(1) - coordinates[i].get(1), 2);
                    c = Math.sqrt(a2 + b2) - 2 * r;
                    if (c < maxRadius) {
                        set = adjacency[i];
                        if (set == null) {
                            set = new HashSet<>();
                        }
                        set.add(j);
                        adjacency[i] = set;
                        //
                        set = adjacency[j];
                        if (set == null) {
                            set = new HashSet<>();
                        }
                        set.add(i);
                        adjacency[j] = set;
                    }
                }
            }
        }
        //
        int vertex;
        ArrayDeque<Integer> neighbors;
        Set<Integer> set2;
        for (int i = n+1; i <= 2*n; i++) {
            // для каждой вершины запускаем бфс
            neighbors = new ArrayDeque<>();
            neighbors.addLast(i);
            while (!neighbors.isEmpty()) {
                vertex = neighbors.getFirst();
                set = adjacency[vertex];
                if (set != null) {
                    set2 = new HashSet<>();
                    for (int element: set) {
                        set2.add(element);
                    }
                    for (int element: set2) {
                        neighbors.add(element);
                        //
                        set = adjacency[element];
                        set.remove(vertex);
                        adjacency[element] = set;
                        //
                        set = adjacency[vertex];
                        set.remove(element);
                        adjacency[vertex] = set;
                        //
                        if (element > 2*n) {
                            return true;
                        }
                    }
                }
                neighbors.remove(vertex);
            }
        }
        return false;
    }
}
