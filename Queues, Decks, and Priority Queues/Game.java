import java.io.*;
import java.util.*;

/*
В игре в пьяницу карточная колода раздается поровну двум игрокам. Далее они вскрывают по одной 
верхней карте, и тот, чья карта старше, забирает себе обе вскрытые карты, которые кладутся под 
низ его колоды. Тот, кто остается без карт – проигрывает. Для простоты будем считать, что все 
карты различны по номиналу, а также, что самая младшая карта побеждает самую старшую карту 
("шестерка берет туза"). Игрок, который забирает себе карты, сначала кладет под низ своей колоды 
карту первого игрока, затем карту второго игрока (то есть карта второго игрока оказывается внизу 
колоды). Напишите программу, которая моделирует игру в пьяницу и определяет, кто выигрывает. 
В игре участвует 10 карт, имеющих значения от 0 до 9, большая карта побеждает меньшую, карта со 
значением 0 побеждает карту 9.

Формат ввода
Программа получает на вход две строки: первая строка содержит 5 чисел, разделенных пробелами — номера 
карт первого игрока, вторая – аналогично 5 карт второго игрока. Карты перечислены сверху вниз, то есть 
каждая строка начинается с той карты, которая будет открыта первой.

Формат вывода
Программа должна определить, кто выигрывает при данной раздаче, и вывести слово first или second, 
после чего вывести количество ходов, сделанных до выигрыша. Если на протяжении 10**6 ходов игра 
не заканчивается, программа должна вывести слово botva.
*/

public class Game {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        Queue<Integer> player1 = new LinkedList<>();
        String[] arr = str.split(" ");
        for (int i = 0; i < arr.length; i++) {
            player1.add(Integer.parseInt(arr[i]));
        }
        str = reader.readLine();
        Queue<Integer> player2 = new ArrayDeque<>();
        arr = str.split(" ");
        for (int i = 0; i < arr.length; i++) {
            player2.add(Integer.parseInt(arr[i]));
        }
        int steps = 0;
        int number1, number2;
        while (steps < 1_000_000) {
            if (player1.isEmpty() || player2.isEmpty()) {
                break;
            }
            number1 = player1.remove();
            number2 = player2.remove();
            if (number1 == 0 && number2 == 9) {
                player1.add(number1);
                player1.add(number2);
            } else if (number2 == 0 && number1 == 9) {
                player2.add(number1);
                player2.add(number2);
            } else {
                if (number1 > number2) {
                    player1.add(number1);
                    player1.add(number2);
                } else {
                    player2.add(number1);
                    player2.add(number2);
                }
            }
            steps++;
        }
        if (steps == 1_000_000) {
            writer.write("botva");
        } else if (!player1.isEmpty()) {
            writer.write("first " + steps);
        } else {
            writer.write("second " + steps);
        }
        writer.flush();
    }
}
