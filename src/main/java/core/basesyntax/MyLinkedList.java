package core.basesyntax;

import java.util.List;
import java.util.Objects;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        Node<T> next;
        Node<T> prev;
        T value;

        Node(T value) {
            this.value = value;
        }
    }

    @Override
    public void add(T value) {
        Node newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(T value, int index) {
        Node newNode = new Node<>(value);
        if (index == size) {
          tail.next = newNode;
          newNode.prev = tail;
          tail = newNode;
        } else if (index == 0) {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        } else {
            Node <T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
                newNode.prev = current.prev;
                newNode.next = current;

                if (current.prev != null) {
                    current.prev.next = newNode;
                } else {
                    head = newNode;
                }
                current.prev = newNode;
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (T value : list) {
            add(value);
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            Node <T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.value;
        }
        throw new IndexOutOfBoundsException("Invalid inex: " + index);
    }

    @Override
    public T set(T value, int index) {
        if (index >= 0 && index < size) {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            T oldValue = current.value;
            current.value = value;
            return oldValue;
            }
        throw new IndexOutOfBoundsException("invalid index: " + index);
        }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            T oldValue = current.value;
            if (current.prev != null) {
                current.prev.next = current.next;
            } else {
                head = current.next;
            }

            if (current.next != null) {
                current.next.prev = current.prev;
            } else {
                tail = current.prev;
            }
            size--;
            return oldValue;
        }
        throw new IndexOutOfBoundsException("invalid index: " + index);
    }

    @Override
    public boolean remove(T object) {
        Node<T> current = head;
        while (current != null) {
            if (Objects.equals(current.value, object)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
