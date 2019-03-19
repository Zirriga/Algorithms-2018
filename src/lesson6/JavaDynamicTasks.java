package lesson6;

import com.sun.deploy.util.ArrayUtil;
import kotlin.NotImplementedError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.max;

import java.lang.*;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    public static String longestCommonSubSequence(String first, String second) {

        int m = second.length();
        int n = first.length();
        int[][] L = new int[m + 1][n + 1];

        //Building L[m][n] as in algorithm
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (first.charAt(j - 1) == second.charAt(i - 1))
                    L[i][j] = L[i - 1][j - 1] + 1;
                else
                    L[i][j] = max(L[i - 1][j], L[i][j - 1]);  // o = (m*n)
            }
        }

        //To print LCS
        int index = L[m][n];

        char[] LCS = new char[index];

        //Stroing characters in LCS
        //Start from the right bottom corner character
        int i = m, j = n;
        while (i > 0 && j > 0) {
            //if current character in s1 and s2 are same, then include this character in LCS[]
            if (first.charAt(j - 1) == second.charAt(i - 1)) {
                LCS[index - 1] = second.charAt(i - 1); // Put current character in result
                i--;
                j--;
                index--; // reduce values of i, j and index
            }
            // compare values of L[i-1][j] and L[i][j-1] and go in direction of greater value.
            else if (L[i - 1][j] > L[i][j - 1])
                i--;
            else
                j--;
        }
        String retStr = new String(LCS);
        return retStr;
    }

    //Ресурсоемкость = O = (mn)
    //Трудоемкость = O(mn)


    /**
     * Наибольшая возрастающая подпоследовательность
     * Средняя
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     * <p>
     * <p>
     * МОИ ЗАМЕТКИ:
     * <p>
     * переменная для сохранения длинны последовательности
     * переменная для последовательности (массив)
     * после программы сравнение длины, меняем только если больше
     * <p>
     * проходим по всемму массиву с числами так:
     * записываем первое, сравниваем с каждым последующим и записываем, если оно больше. в конце цикла записываем в ответ, если оно больше предыдущего ответа (+ переменная подсчета кол-ва)
     */

    public static List<Integer> longestIncreasingSubSequence(List<Integer> list1) {
// L[i] - The longest increasing sub-sequence
// ends with arr[i]
        int n = list1.size();
        ArrayList<ArrayList<Integer>>L = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            L.add(new ArrayList<Integer>());
        }
        if (list1.isEmpty()) return list1;
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(Integer.valueOf(list1.get(0)));
// L[0] is equal to arr[0]
        L.set(0, temp);

        for (int i = 1; i < n; i++) {
// do for every j less than i
            for (int j = 0; j < i; j++) {
                if ((list1.get(i) > list1.get(j)) && (L.get(i).size() < L.get(j).size() + 1) && (L.get(i).size() != L.get(j).size()))
                    L.set(i, new ArrayList<Integer>(L.get(j)));
            }
// L[i] ends with arr[i]
            L.get(i).add(Integer.valueOf(list1.get(i)));
        }

        ArrayList<Integer> max = new ArrayList<Integer>(L.get(0));

        for (ArrayList<Integer> x : L) {
            if (x.size() > max.size()) {
                max = x;
            }
        }
        return max;
    }


    //Ресурсоемкость =
    //Трудоемкость =


    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Сложная
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
