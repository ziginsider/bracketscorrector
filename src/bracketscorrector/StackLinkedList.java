/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bracketscorrector;

/**
 *
 * @author zigin
 */
public class StackLinkedList<T> implements Stack<T>  {

    private int size;
    private Node<T> header;

    private static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }

    /**
     * 
     */
    public StackLinkedList() {
        size = 0;
        header = new Node<>(null, null);
        header.next = header;
    }

    @Override
    public void push(T element) {
        Node<T> newNode = new Node<>(element, header);
        header = newNode;
        size++;
    }

    @Override
    public T pop() {
        Node<T> topElement = header;
        header = topElement.next;
        size--;
        return topElement.element;
    }

    @Override
    public T readTop() {
        return header.element;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }
}
