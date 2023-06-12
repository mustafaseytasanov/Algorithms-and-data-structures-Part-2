import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
У одного из студентов в комнате живёт кузнечик, который очень любит прыгать по клетчатой одномерной доске. 
Длина доски — N клеток. К его сожалению, он умеет прыгать только на 1, 2, …, k клеток вперёд.
Однажды студентам стало интересно, сколькими способами кузнечик может допрыгать из первой клетки до последней. 
Помогите им ответить на этот вопрос.

Формат ввода
В первой и единственной строке входного файла записано два целых числа — N и k (1 <= N <= 30, 1 <= k <= 10).

Формат вывода
Выведите одно число — количество способов, которыми кузнечик может допрыгать из 
первой клетки до последней.
*/

public class Grasshopper {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        String[] array = str.split(" ");
        int n = Integer.parseInt(array[0]);
        int k = Integer.parseInt(array[1]);
        List<Integer> numberOfWays = new ArrayList<>();
        numberOfWays.add(1);
        for (int i = 0; i < k; i++) {
            numberOfWays.add((int) Math.pow(2, i));            
        }
        int number;
        for (int i = k+1; i < n; i++) {
            number = 0;
            for (int j = 1; j <= k; j++) {
                number += numberOfWays.get(i-j);
            }
            numberOfWays.add(number);
        }
        writer.write(String.valueOf(numberOfWays.get(n-1)));
        writer.flush();
    }
}
