package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     * <p>
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     * <p>
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     * <p>
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) throws IOException {
        return null;
        /*ArrayList<Integer> myListOfNumbers = new ArrayList<>();
        Integer first = 0;
        Integer second = 0;
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputName)));
            String line;
            do {
                line = reader.readLine();
                myListOfNumbers.add((Integer.parseInt(line)));
            } while (line != null);
            int max = myListOfNumbers.indexOf(Collections.max(myListOfNumbers));

            for (int i = 0; i < myListOfNumbers.size(); i++) {
                for (int j = 1; i < myListOfNumbers.size(); j++) {
                    if ((myListOfNumbers.get(i) - myListOfNumbers.get(j)) < max) {
                        max = myListOfNumbers.get(i) - myListOfNumbers.get(j);
                        first = i + 1;
                        second = j + 1;
                    }
                }
            }
        }
        Pair<Integer, Integer> myPair = new Pair<>(first, second);
        return (myPair); */
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     * <p>
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 5
     * <p>
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 х
     * <p>
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 х 3
     * 8   4
     * 7 6 Х
     * <p>
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     * <p>
     * 1 Х 3
     * х   4
     * 7 6 Х
     * <p>
     * 1 Х 3
     * Х   4
     * х 6 Х
     * <p>
     * х Х 3
     * Х   4
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   х
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   Х
     * Х х Х
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        int survivor = 0;
        switch (menNumber) {
            case 1:
                return 1;
            default:
                for (int i = 1; i < menNumber; i++) {
                    survivor = (survivor + choiceInterval) % (i + 1);
                }
                return survivor + 1;
        }
    }
    //Ресурсоемкость = O(1)
    //Трудоемкость = O(N)


    /**
     * Наибольшая общая подстрока.
     * Средняя
     * <p>
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */

    static public String longestCommonSubstring(String firs, String second) {
        int maxSub = 0; // length
        String maxSubString = "";
        if (firs.isEmpty() || second.isEmpty()) return "";
        else {
            int[][] arr = new int[firs.length()][second.length()];
            for (int i = 0; i < firs.length(); i++) {
                for (int j = 0; j < second.length(); j++) {
                    if (firs.charAt(i) == second.charAt(j)) {
                        if (i == 0 || j == 0)
                            arr[i][j] = 1;
                        else
                            arr[i][j] = arr[i - 1][j - 1] + 1;
                    }
                    if (arr[i][j] > maxSub) {
                        maxSub = arr[i][j];
                        maxSubString = firs.substring((i - maxSub + 1), i + 1);
                    }
                }
            }
        }
        return maxSubString;
    }
    //Ресурсоемкость = O(1)
    //Трудоемкость = O(N)

    /**
     * Число простых чисел в интервале
     * Простая
     * <p>
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     * <p>
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */

    static public int calcPrimesNumber(int limit) {
        if (limit <= 1) return 0;

        int counter = 0;

        for (int i = 2; i <= limit; i++) {
            boolean isPrime = true;
            for (int divisor = 2; divisor <= i / 2; divisor++) {
                if (i % divisor == 0) {
                    isPrime = false;
                    break; // num is not a prime, no reason to continue checking
                }
            }
            if (isPrime) counter++;
        }
        return counter;
    }

    /**
     * Балда
     * Сложная
     * <p>
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     * <p>
     * И Т Ы Н
     * К Р А Н
     * А К В А
     * <p>
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     * <p>
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     * <p>
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     * <p>
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    static public Set<String> baldaSearcher(String inputName, Set<String> words) {
        throw new NotImplementedError();
    }
}
