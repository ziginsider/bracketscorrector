/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bracketscorrector;

/**
 * Methods for stack
 * 
 * @author Alex Kisel
 * @since 2018-03-30
 */
public interface Stack<T> {
    void push(T element);
    T pop();
    T readTop();
    boolean isEmpty();
    boolean isFull();
}
