import java.io.*;

/*
Васин жесткий диск состоит из M секторов. Вася последовательно устанавливал на него различные 
операционные системы следующим методом: он создавал новый раздел диска из последовательных секторов, 
начиная с сектора номер ai и до сектора bi включительно, и устанавливал на него очередную систему. 
При этом, если очередной раздел хотя бы по одному сектору пересекается с каким-то ранее созданным разделом, 
то ранее созданный раздел «затирается», и операционная система, которая на него была установлена, 
больше не может быть загружена.

Напишите программу, которая по информации о том, какие разделы на диске создавал Вася, определит, 
сколько в итоге работоспособных операционных систем установлено и работает в настоящий момент на Васином компьютере.

Формат ввода
Сначала вводятся натуральное число M — количество секторов на жестком диске (1 ≤ M ≤ 109) и целое 
число N — количество разделов, которое последовательно создавал Вася (0 ≤ N ≤ 1000).

Далее идут N пар чисел ai и bi, задающих номера начального и конечного секторов раздела (1 ≤ ai ≤ bi ≤ M).

Формат вывода
Выведите одно число — количество работающих операционных систем на Васином компьютере.
*/

public class OperatingSystems {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        str = reader.readLine();
        int n = Integer.parseInt(str);
        int[][] segments = new int[n][2];
        String[] arr;
        int start, end;
        for (int i = 0; i < n; i++) {
            str = reader.readLine();
            arr = str.split(" ");
            start = Integer.parseInt(arr[0]);
            end = Integer.parseInt(arr[1]);
            segments[i][0] = start;
            segments[i][1] = end;
            for (int j = 0; j < i; j++) {
                if ((start >= segments[j][0] && start <= segments[j][1]) ||
                        (end >= segments[j][0] && end <= segments[j][1])) {
                    segments[j][0] = 0;
                } else if (start <= segments[j][0] && end >= segments[j][1]) {
                    segments[j][0] = 0;
                }
            }
        }
        int output = 0;
        for (int i = 0; i < n; i++) {
            if (segments[i][0] != 0) {
                output++;
            }
        }
        writer.write(output + "\n");
        writer.flush();
    }
}
