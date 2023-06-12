import java.io.*;
import java.util.*;

/*
Для ускорения работы служб доставки под городом Длинноградом был прорыт тоннель, по которому ходит товарный поезд, 
останавливающийся на промежуточных станциях возле логистических центров. На станциях к концу поезда могут быть присоединены 
вагоны с определенными товарами, а также от его конца может быть отцеплено некоторое количество вагонов или может быть 
проведена ревизия, во время которой подсчитывается количество вагонов с определенным товаром.
Обработайте операции в том порядке, в котором они производились, и ответьте на запросы ревизии.

Формат ввода
В первой строке вводится число N (1 ≤ N ≤ 100000) — количество операций, произведенных над поездом.
В каждой из следующих N строк содержится описание операций. Каждая операция может иметь один из трех типов:

add <количество вагонов> <название товара> — добавить в конец поезда <количество вагонов> с грузом <название товара>. 
Количество вагонов не может превышать 10**9, название товара — одна строка из строчных латинских символов длиной до 20.

delete <количество вагонов> — отцепить от конца поезда <количество вагонов>. Количество отцепляемых вагонов 
не превосходит длины поезда.

get <название товара> — определить количество вагонов с товаром <название товара> в поезде. Название товара — одна 
строка из строчных латинских символов длиной до 20.

Формат вывода
На каждый запрос о количестве вагонов с определенным товаром выведите одно число — количество вагонов с таким товаром. 
Запросы надо обрабатывать в том порядке, как они поступали.
*/

public class Trains {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        long n = Long.parseLong(str);
        long idx = 0;
        Stack<String> stack = new Stack<>();
        Stack<Long> stack2 = new Stack<>();
        String[] arr;
        Map<String, Long> map = new HashMap<>();
        Long number, number2, number3;
        String str2;
        while (idx < n) {
            str = reader.readLine();
            arr = str.split(" ");
            if (arr[0].equals("add")) {
                stack.add(arr[2]);
                stack2.add(Long.parseLong(arr[1]));
                number = map.get(arr[2]);
                if (number == null) {
                    number = Long.valueOf(0);
                }
                number += Long.parseLong(arr[1]);
                map.put(arr[2], number);
            } else if (arr[0].equals("delete")) {
                number = Long.parseLong(arr[1]);
                while (number != 0) {
                    str2 = stack.pop();
                    number2 = stack2.pop();
                    if (number2 < number) {
                        number -= number2;
                        number3 = map.get(str2);
                        map.put(str2, number3 - number2);
                    } else if (number2 == number) {
                        number3 = map.get(str2);
                        map.put(str2, number3 - number2);
                        number = Long.valueOf(0);
                    } else {
                        number3 = map.get(str2);
                        map.put(str2, number3 - number);
                        stack.add(str2);
                        stack2.add(number2 - number);
                        number = Long.valueOf(0);
                    }
                }
            } else { // get
                number = map.get(arr[1]);
                if (number == null) {
                    writer.write(0 + "\n");
                } else {
                    writer.write(number + "\n");
                }
            }
            idx++;
        }
        writer.flush();
    }
}
