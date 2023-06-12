import java.io.*;
import java.util.*;

/*
За билетами на премьеру нового мюзикла выстроилась очередь из N человек, каждый из которых хочет купить 1 билет. 
На всю очередь работала только одна касса, поэтому продажа билетов шла очень медленно, приводя «постояльцев» 
очереди в отчаяние. Самые сообразительные быстро заметили, что, как правило, несколько билетов в одни руки кассир 
продаёт быстрее, чем когда эти же билеты продаются по одному. Поэтому они предложили нескольким подряд стоящим людям 
отдавать деньги первому из них, чтобы он купил билеты на всех.

Однако для борьбы со спекулянтами кассир продавала не более 3-х билетов в одни руки, поэтому договориться таким образом 
между собой могли лишь 2 или 3 подряд стоящих человека.

Известно, что на продажу i-му человеку из очереди одного билета кассир тратит Ai секунд, на продажу двух билетов — Bi секунд, 
трех билетов — Ci секунд. Напишите программу, которая подсчитает минимальное время, за которое могли быть обслужены все покупатели.

Обратите внимание, что билеты на группу объединившихся людей всегда покупает первый из них. Также никто в целях ускорения 
не покупает лишних билетов (то есть билетов, которые никому не нужны).

Формат ввода
На вход программы поступает сначала число N — количество покупателей в очереди (1 ≤ N ≤ 5000). Далее идет N троек 
натуральных чисел Ai, Bi, Ci. Каждое из этих чисел не превышает 3600. Люди в очереди нумеруются, начиная от кассы.

Формат вывода
Требуется вывести одно число — минимальное время в секундах, за которое могли быть обслужены все покупатели.
*/

public class Tickets {
    static double inf = Double.POSITIVE_INFINITY;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        int n = Integer.parseInt(str);
        int[] minTime = new int[n+3];
        minTime[0] = minTime[1] = minTime[2] = 0;
        String[] arr;
        int a, b, c;
        int number1, number2, number3;
        int minElement;
        int[][] time = new int[n+3][3];
        for (int i = 0; i < 3; i++) {
            time[i][0] = time[i][1] = time[i][2] = (int) inf;
        }
        int idx = 3;
        while (idx < n+3) {
            str = reader.readLine();
            arr = str.split(" ");
            a = Integer.parseInt(arr[0]);
            b = Integer.parseInt(arr[1]);
            c = Integer.parseInt(arr[2]);
            time[idx][0] = a;
            time[idx][1] = b;
            time[idx][2] = c;
            idx++;
        }
        idx = 3;
        while (idx < n+3) {
            number1 = minTime[idx-1] + time[idx][0];
            number2 = minTime[idx-2] + time[idx-1][1];
            number3 = minTime[idx-3] + time[idx-2][2];
            minElement = Math.min(number1, number2);
            minElement = Math.min(minElement, number3);
            minTime[idx] = minElement;
            idx++;
        }
        writer.write(String.valueOf(minTime[n+2]));
        writer.flush();
    }
}
