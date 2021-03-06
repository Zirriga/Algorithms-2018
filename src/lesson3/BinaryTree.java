package lesson3;

import com.sun.xml.internal.bind.v2.TODO;
import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        } else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        } else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */
    @Override
    public boolean remove(Object o) {
        Node<T> temp = delete(root, o);
        if (temp == null) {
            return false;
        } else {
            size--;
            return true;
        }
    }

    private Node<T> delete(Node<T> root, Object o) {
        T value = (T)o;
        if (root == null) return null;
        if (value.equals(root.value)) {
            if (root.left != null && root.right != null) {
                Node<T> min = getMinRight(root.right);
                root.value = min.value;
                root.right = delete(root.right, root.value);
            } else if (root.left == null) {
                return root.right;
            } else {
                return root.left;
            }
        } else if (value.compareTo(root.value) > 0) {
            root.right = delete(root.right, value);
        } else {
            root.left = delete(root.left, value);
        }
        return root;
    }

    Node<T> getMinRight(Node<T> node) {
        if (node.left == null) {
            return node;
        } else {
            Node<T> temp = getMinRight(node.left);
            return temp;
        }
    }

    @Override
    public boolean contains(Object o) {
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        } else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        } else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;

        /**
         * Поиск следующего элемента
         * Средняя
         */
        private Node<T> findNext() {
            Node<T> next = null;
            if (current == null) {
                Node<T> min = root;
                while (min.left != null) min = min.left;
                next = min;
                return next;
            }
            Node<T> tempRoot = root;
            while (tempRoot != null) {
                if (tempRoot.value.compareTo(current.value) > 0) {
                    next = tempRoot;
                    tempRoot = tempRoot.left;
                } else {
                    tempRoot = tempRoot.right;
                }
            }
            return next;
        }
        //Трудоемкость R=O(logN)
        //Ресурсоемкость T=O(N)

        @Override
        public boolean hasNext() {
            return findNext() != null;
        }

        @Override
        public T next() {
            current = findNext();
            if (current == null) throw new NoSuchElementException();
            return current.value;
        }

        /**
         * Удаление следующего элемента
         * Сложная
         */
        @Override
        public void remove() {
            // TODO
            throw new NotImplementedError();
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        // TODO
        //int ourT = T; запишем это значение, потом обойдем все дерево и выберем те, которые больше или равны ему. запишем  в лист.
        throw new NotImplementedError();
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
