package lesson1;

import kotlin.NotImplementedError;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     * <p>
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС,
     * каждый на отдельной строке. Пример:
     * <p>
     * 13:15:19
     * 07:26:57
     * 10:00:03
     * 19:56:14
     * 13:15:19
     * 00:40:31
     * <p>
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС. Одинаковые моменты времени выводить друг за другом. Пример:
     * <p>
     * 00:40:31
     * 07:26:57
     * 10:00:03
     * 13:15:19
     * 13:15:19
     * 19:56:14
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) {
        ArrayList<String> arr = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputName)));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputName)));
            String line;
            do {
                line = reader.readLine();
                arr.add(line);
            } while (line != null);


            Integer[] intArr = new Integer[arr.size()];
            for (int i = 0; i < arr.size(); i++) {
                String temp = arr.get(i);
                String name = temp.split(":")[0].trim() + temp.split(":")[1].trim() + temp.split(":")[2].trim();
                intArr[i] = (Integer.parseInt(name));
            }

            Sorts.insertionSort(intArr);

            for (int i = 0; i < arr.size(); i++) {
                String strLine = intArr[i].toString();
                while (strLine.length() != 6) {
                    strLine = "0" + strLine;
                }
                strLine = strLine.substring(0, 1) + ":" + strLine.substring(2, 3) + ":" + strLine.substring(4, 5);

                writer.write(strLine);
                writer.newLine();
            }
            reader.close();
            writer.close();
        } catch (Exception e) {
            System.out.println("Input is incorrect");
        }
    }

    /**
     * Сортировка адресов
     * <p>
     * Средняя
     * <p>
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     * <p>
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     * <p>
     * Людей в городе может быть до миллиона.
     * <p>
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     * <p>
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     * <p>
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     * <p>
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     * <p>
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     * <p>
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) {
        ArrayList<Integer> arr = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputName)));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputName)));
            String line;
            do {
                line = reader.readLine();
                arr.add((int) (Double.parseDouble(line)) * 10);
            } while (line != null);

            Integer[] intArr = new Integer[arr.size()];
            Sorts.insertionSort(intArr);

            for (int i = 0; i < arr.size(); i++) {
                Double strLine = ((double) intArr[i]) / 10;

                writer.write(strLine.toString());
                writer.newLine();
            }
            reader.close();
            writer.close();
        } catch (Exception e) {
            System.out.println("Input is incorrect");
        }
    }

    /**
     * Сортировка последовательности
     * <p>
     * Средняя
     * (Задача взята с сайта acmp.ru)
     * <p>
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     * <p>
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     * <p>
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     * <p>
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) {
        ArrayList<Integer> myListOfNumbers = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputName)));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputName)));
            String line;
            do {
                line = reader.readLine();
                myListOfNumbers.add((Integer.parseInt(line)));
            } while (line != null);

            int mostMatches = 0;
            List<Integer> popularNumbers = new ArrayList<>();

            for (int i = 0; i < myListOfNumbers.size(); i++) {
                int matches = 0;
                int holder = myListOfNumbers.get(i);

                for (int j = 0; j < myListOfNumbers.size(); j++) {
                    if (myListOfNumbers.get(j) == holder) {
                        matches++;
                    }
                }

                if (matches > mostMatches) {
                    mostMatches = matches;
                    popularNumbers.clear();
                    popularNumbers.add(holder);
                }
                if (matches == mostMatches) {
                    if (!popularNumbers.contains(holder)) {
                        popularNumbers.add(holder);
                    }
                }
            }

            Integer smallestMostPopular = Collections.min(popularNumbers);

            for (Iterator<Integer> iterator = myListOfNumbers.listIterator(); iterator.hasNext(); ) {
                int a = iterator.next();
                if (a == smallestMostPopular) {
                    iterator.remove();
                    myListOfNumbers.add(a);
                }
            }
            for (int i = 0; i < myListOfNumbers.size(); i++) {
                writer.write(myListOfNumbers.get(i).toString());
                writer.newLine();
            }
            reader.close();
            writer.close();
        } catch (Exception e) {
            System.out.println("Input is incorrect");
        }
    }


    /**
     * Соединить два отсортированных массива в один
     * <p>
     * Простая
     * <p>
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     * <p>
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     * <p>
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */

    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        for (int i = 0; i < first.length; i++) {
            second[i] = first[i];
        }
        Sorts.insertionSort(second);
    }
}
