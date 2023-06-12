import java.io.*;
import java.util.*;

/*
Пещера представлена кубом, разбитым на N частей по каждому измерению (то есть на N**3 кубических клеток). Каждая клетка может быть 
или пустой, или полностью заполненной камнем. Исходя из положения спелеолога в пещере, требуется найти, какое минимальное количество 
перемещений по клеткам ему требуется, чтобы выбраться на поверхность. Переходить из клетки в клетку можно, только если они обе 
свободны и имеют общую грань.

Формат ввода
В первой строке содержится число N (1 ≤ N ≤ 30). Далее следует N блоков. Блок состоит из пустой строки и N строк по N символов: 
# - обозначает клетку, заполненную камнями, точка - свободную клетку. Начальное положение спелеолога обозначено заглавной буквой S. 
Первый блок представляет верхний уровень пещеры, достижение любой свободной его клетки означает выход на поверхность. Выход 
на поверхность всегда возможен.

Формат вывода
Вывести одно число - длину пути до поверхности.
*/

public class Speleologist {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        int n = Integer.parseInt(str);
        char ch;
        int[][][] vertices = new int[n+2][n+2][n+2];
        int start = 0;
        for (int i = 1; i <= n; i++) {
            reader.readLine();
            for (int j = 1; j <= n; j++) {
                str = reader.readLine();
                for (int k = 1; k <= n; k++) {
                    ch = str.charAt(k-1);
                    if (ch != '#') {
                        vertices[i][j][k] = 1;
                        if (ch == 'S') {
                            start = (i-1) * n * n + (j-1) * n + k;
                        }
                    }
                }
            }
        }
        ArrayList<Integer>[] adjacency = new ArrayList[n * n * n + 1];
        ArrayList<Integer> arrayList;
        int idx;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    arrayList = new ArrayList<>();
                    if (vertices[i][j][k] == 1) {
                        if (vertices[i-1][j][k] == 1) {
                            idx = (i-2) * n * n + (j-1) * n + k;
                            arrayList.add(idx);
                        }
                        if (vertices[i+1][j][k] == 1) {
                            idx = (i) * n * n + (j-1) * n + k;
                            arrayList.add(idx);
                        }
                        if (vertices[i][j-1][k] == 1) {
                            idx = (i-1) * n * n + (j-2) * n + k;
                            arrayList.add(idx);
                        }
                        if (vertices[i][j+1][k] == 1) {
                            idx = (i-1) * n * n + (j) * n + k;
                            arrayList.add(idx);
                        }
                        if (vertices[i][j][k-1] == 1) {
                            idx = (i-1) * n * n + (j-1) * n + k - 1;
                            arrayList.add(idx);
                        }
                        if (vertices[i][j][k+1] == 1) {
                            idx = (i-1) * n * n + (j-1) * n + k + 1;
                            arrayList.add(idx);
                        }
                    }
                    idx = (i-1) * n * n + (j-1) * n + k;
                    adjacency[idx] = arrayList;
                }
            }
        }
        Set<Integer> visitedVertices = new HashSet<>();
        int currentLength = 0;
        List<Integer>[] path = new List[n*n*n];
        ArrayList arrayList2 = new ArrayList<>();
        arrayList2.add(start);
        path[0] = arrayList2;
        visitedVertices.add(start);
        boolean isFound = false;
        while (true) {
            for (int elem: path[currentLength]) {
                if (elem >= 1 && elem <= n*n) {
                    isFound = true;
                    break;
                }
            }
            if (isFound) {
                break;
            }
            arrayList2 = new ArrayList<>();
            for (int elem: path[currentLength]) {
                arrayList = adjacency[elem];
                for (int elem2: arrayList) {
                    if (!visitedVertices.contains(elem2)) {
                        arrayList2.add(elem2);
                        visitedVertices.add(elem2);
                    }
                }
            }
            currentLength++;
            path[currentLength] = arrayList2;
        }
        writer.write(currentLength + "\n");
        writer.flush();
    }
}
