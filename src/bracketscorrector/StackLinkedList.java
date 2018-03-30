/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bracketscorrector;

/**
 * StackImplementations
 * 
 * @author Alex Kisel
 * @since 2018-03-30
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

    public StackLinkedList() {
        size = 0;
        header = new Node<>(null, null);
        header.next = header;
    }

    /**
     * Set element from top of stack
     * 
     * @param element new element for stack
     */
    @Override
    public void push(T element) {
        Node<T> newNode = new Node<>(element, header);
        header = newNode;
        size++;
    }

    /**
     * Get element from top of stack and deletes it in stack
     * 
     * @return element upper element
     */
    @Override
    public T pop() {
        Node<T> topElement = header;
        header = topElement.next;
        size--;
        return topElement.element;
    }

    /**
     * Get element from top of stack
     * 
     * @return element upper element
     */
    @Override
    public T readTop() {
        return header.element;
    }

    /**
     * Checks if stack empty
     * 
     * @return true if stack is empty 
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if stack full
     * 
     * @return true if stack full
     */
    @Override
    public boolean isFull() {
        return false;
    }
}
