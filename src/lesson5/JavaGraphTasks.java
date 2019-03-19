package lesson5;

import kotlin.NotImplementedError;
import lesson5.impl.GraphBuilder;

import java.util.*;

@SuppressWarnings("unused")
public class JavaGraphTasks {
    /**
     * Эйлеров цикл.
     * Средняя
     *
     * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
     * Если в графе нет Эйлеровых циклов, вернуть пустой список.
     * Соседние дуги в списке-результате должны быть инцидентны друг другу,
     * а первая дуга в списке инцидентна последней.
     * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
     * Веса дуг никак не учитываются.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
     *
     * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
     * связного графа ровно по одному разу
     *
     * Сначала проверим, существует ли эйлеров путь. Затем найдём все простые циклы и объединим их в один -
     * это и будет эйлеровым циклом. Если граф таков, что эйлеров путь не является циклом, то, добавим недостающее ребро, найдём эйлеров цикл, потом удалим лишнее ребро.
     * Эйлеров цикл существует тогда и только тогда, когда степени всех вершин чётны. Эйлеров путь существует
     * тогда и только тогда, когда количество вершин с нечётными степенями равно двум (или нулю, в случае существования эйлерова цикла).
     *
     */
    public static List<Graph.Edge> findEulerLoop(Graph graph) {
        List<Graph.Edge> result = new LinkedList<>();
        for ( Graph.Vertex v : graph.getVertices()) {
            if (graph.getNeighbors(v).size() % 2 != 0) return result;
        }
        Stack<Pair> stack = new Stack<>();
        HashSet<Graph.Edge> passedSet = new HashSet<>();
        stack.push(new Pair(graph.getEdges().iterator().next().getBegin(), graph.getEdges().iterator().next()));
        passedSet.add(stack.peek().edge);
        while (!stack.empty()) {
            int neighbours = 0;
            Graph.Edge nextEdge = stack.peek().edge;
            Graph.Vertex nextVertex = stack.peek().vertex;
            if (nextVertex.equals(nextEdge.getBegin())) {
                nextVertex = stack.peek().edge.getEnd();
            } else {
                nextVertex = stack.peek().edge.getBegin();
            }

            for (Graph.Edge edge : graph.getConnections(nextVertex).values()) {
                if (!passedSet.contains(edge)) {
                    neighbours++;
                    nextEdge = edge;
                }
            }
            if (neighbours == 0) {
                result.add(stack.pop().edge);
            } else {
                stack.push(new Pair(nextVertex, nextEdge));
                passedSet.add(nextEdge);
            }
        }
        return result;
    }
    private static class Pair {
        Graph.Vertex vertex;
        Graph.Edge edge;

        Pair(Graph.Vertex vertex, Graph.Edge edge) {
            this.vertex = vertex;
            this.edge = edge;
        }
    }

    //ресурсоемкость - О(n)(все от графа зависит)
    //трудоемкость - О(n*m)

    /**
     * Минимальное остовное дерево.
     * Средняя
     *
     * Дан граф (получатель). Найти по нему минимальное остовное дерево.
     * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
     * вернуть любое из них. Веса дуг не учитывать.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ:
     *
     *      G    H
     *      |    |
     * A -- B -- C -- D
     * |    |    |
     * E    F    I
     * |
     * J ------------ K
     */
    public static Graph minimumSpanningTree(Graph graph) {
        GraphBuilder result = new GraphBuilder();
        HashMap<Graph.Vertex, Integer> idMap = new HashMap<>();
        int id = 0;
        for (Graph.Vertex v : graph.getVertices()) {
            idMap.put(result.addVertex(v.getName()),id);
            id++;
        }
        for (Graph.Edge edge : graph.getEdges()) {
            Graph.Vertex va = edge.getBegin();
            Graph.Vertex vb = edge.getEnd();
            if (!idMap.get(va).equals(idMap.get(vb))) {
                result.addConnection(va,vb,0);
                int idOld = idMap.get(va);
                int idNew = idMap.get(vb);
                for (Map.Entry<Graph.Vertex,Integer> entry: idMap.entrySet()) {
                    if (entry.getValue() == idOld) {
                        entry.setValue(idNew);
                    }
                }
            }
        }
        return result.build();
    }
    //ресурсоемкость - О(n)(все от графа зависит)
    //трудоемкость - О(n*m)(зависит от граней и от вершин)

    /**
     * Максимальное независимое множество вершин в графе без циклов.
     * Сложная
     *
     * Дан граф без циклов (получатель), например
     *
     *      G -- H -- J
     *      |
     * A -- B -- D
     * |         |
     * C -- F    I
     * |
     * E
     *
     * Найти в нём самое большое независимое множество вершин и вернуть его.
     * Никакая пара вершин в независимом множестве не должна быть связана ребром.
     *
     * Если самых больших множеств несколько, приоритет имеет то из них,
     * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
     *
     * В данном случае ответ (A, E, F, D, G, J)
     *
     * Эта задача может быть зачтена за пятый и шестой урок одновременно
     */
    public static Set<Graph.Vertex> largestIndependentVertexSet(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Наидлиннейший простой путь.
     * Сложная
     *
     * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
     * Простым считается путь, вершины в котором не повторяются.
     * Если таких путей несколько, вернуть любой из них.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ: A, E, J, K, D, C, H, G, B, F, I
     */
    public static Path longestSimplePath(Graph graph) {
        throw new NotImplementedError();
    }
    // переменная для наибольшего пути <= , переменная для записи ответа.
    // начинаем с одной точки, рассматриваем все пути из  нее, нельзя вернуться в точку с уже пройденной буквой, если таковых рядом нет, то завершить цикл (хранить точки в массиве и сравнивать с кажным из них)
    // повторить для всех точек (можно использовать массив со всеми точками и по очереди доставать из него)
    // если один из путей получился == кол-ву точек (длинна массива из предыдущего пункта), то выводим это значение и прекращаем цикл для всех точек. (как бонус)
}
