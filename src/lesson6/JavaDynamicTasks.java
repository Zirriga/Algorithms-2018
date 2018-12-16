package lesson6;

import com.sun.deploy.util.ArrayUtil;
import kotlin.NotImplementedError;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.util.Arrays.asList;

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
        int[][] lengths = new int[first.length() + 1][second.length() + 1];

        for (int i = 0; i < first.length(); i++)
            for (int j = 0; j < second.length(); j++)
                if (first.charAt(i) == second.charAt(j))
                    lengths[i + 1][j + 1] = lengths[i][j] + 1;
                else
                    lengths[i + 1][j + 1] =
                            Math.max(lengths[i + 1][j], lengths[i][j + 1]);

        // чтение из матрицы
        StringBuffer sb = new StringBuffer();
        for (int x = first.length(), y = second.length();
             x != 0 && y != 0; ) {
            if (lengths[x][y] == lengths[x - 1][y])
                x--;
            else if (lengths[x][y] == lengths[x][y - 1])
                y--;
            else {
                assert first.charAt(x - 1) == second.charAt(y - 1);
                sb.append(first.charAt(x - 1));
                x--;
                y--;
            }
        }

        return sb.reverse().toString();
    }

    //Ресурсоемкость = O(1)
    //Трудоемкость = O(N)

    //only one test failed. я думаю, что тест со стихом неверный. в ожидаемом значении "дд", в первом есть "дд",
    //а во втором нет. проверяла глазами работу алгоритма, я считаю, тчо он верный. проверьте тест на правильность, пожалуйста.
    //или укажите мне на мою ошибку.


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
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list1) {
        int[] nums = new int[list1.size()];
        for (int i = 0; i < nums.length; i++)
            nums[i] = list1.get(i);

        if (nums == null || nums.length == 0)
            return null;

        int maxSubsSize = 0;
        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int num : nums) {
            if (list.size() == 0 || num > list.get(list.size() - 1)) {
                list.add(num);
            } else {
                int i = 0;
                int j = list.size() - 1;

                while (i < j) {
                    int mid = (i + j) / 2;
                    if (list.get(mid) < num) {
                        i = mid + 1;
                    } else {
                        j = mid;
                    }
                }

                list.set(j, num);
            }
        }
        ArrayList<Integer> listReturn = list;
        maxSubsSize = list.size();

        for (int i = 0; i < list.size(); i++) {
            int delChar = list.get(i);
            int[] buffArr = nums;
            int[] anotherBuffArr = ArrayUtil.removeElement(buffArr, delChar);

            ArrayList<Integer> listBuff = new ArrayList<Integer>();

            for (int num : anotherBuffArr) {
                if (listBuff.size() == 0 || num > listBuff.get(listBuff.size() - 1)) {
                    listBuff.add(num);
                } else {
                    int i = 0;
                    int j = listBuff.size() - 1;

                    while (i < j) {
                        int mid = (i + j) / 2;
                        if (listBuff.get(mid) < num) {
                            i = mid + 1;
                        } else {
                            j = mid;
                        }
                    }
                    listBuff.set(j, num);
                }
            }
            if (listBuff.size() == maxSubsSize) {
                for (int i = 0; i < listBuff.size(); i++) {
                    int first = list.get(i);
                    int second = listBuff.get(i);
                    int fistInd = asList(nums).indexOf(first);
                    int secondInd = asList(nums).indexOf(second);

                    if (fistInd == secondInd) continue;

                    if (fistInd < secondInd) {
                        listReturn = list;
                        break;
                    } else {
                        listReturn = listBuff;
                        break;
                    }
                }
            }
        }
        int[] retArray = new int[listReturn.size()];
        listReturn.toArray(retArray); // fill the array
        return retArray;
    }

    public static List removeElements(String[] input, String deleteMe) {
        List result = new List<Integer> {
        };
        for (String item : input)
            if (!deleteMe.equals(item))
                result. (item);

        return result;
    }
    //Ресурсоемкость = O(1)
    //Трудоемкость = O(N)

    //NOT END. пролемы с утилитой ArrayUtil.removeElement(buffArr, delChar); и в последнем кусочке с удалением элементов.

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
