import java.io.*;

/*
Красотой строки назовем максимальное число идущих подряд одинаковых букв. (красота строки abcaabdddettq равна 3)

Сделайте данную вам строку как можно более красивой, если вы можете сделать не более k операций замены символа.

Формат ввода
В первой строке записано одно целое число k (0 ≤ k ≤ 109)

Во второй строке дана непустая строчка S (|S| ≤ 2 ⋅ 105). Строчка S состоит только из маленьких латинских букв.

Формат вывода
Выведите одно число — максимально возможную красоту строчки, которую можно получить.
*/

public class BeautifulString {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        FileWriter writer = new FileWriter("output.txt");
        String str = reader.readLine();
        int k = Integer.parseInt(str);
        String inputString = reader.readLine();
        char[] letters = new char[]
                {'a', 'b', 'c', 'd', 'e', 'f', 'j',
                'h', 'i', 'g', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u',
                'v', 'w', 'x', 'y', 'z'};
        // Implementation
        char currentLetter, currentLetter2;
        int p1, p2;
        int kk;
        int length;
        int maxLength = 0;
        for (char letter: letters) {
            kk = k;
            length = 0;
            p1 = 0;
            p2 = 0;
            while (p1 < inputString.length()) {
                currentLetter = inputString.charAt(p1);
                if (currentLetter != letter) {
                    if (kk > 0) {
                        kk--;
                        length++;
                        p1++;
                    } else {
                        while (true) {
                            currentLetter2 = inputString.charAt(p2);
                            p2++;
                            length--;
                            if (currentLetter2 != letter) {
                                length++;
                                p1++;
                                break;
                            }
                        }
                    }
                } else {
                    length++;
                    p1++;
                }
                maxLength = Math.max(maxLength, length);
            }
        }
        writer.write(String.valueOf(maxLength));
        writer.flush();
    }
}
