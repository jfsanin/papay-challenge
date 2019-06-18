package jp.ne.paypay;

import java.util.NoSuchElementException;

public class ImmutableStack<T> implements Stack<T>{
    private static Stack emptyStack;
    private T head;
    private Stack<T> tail;

    private ImmutableStack(T head, Stack<T> tail){
        this.head = head;
        this.tail = tail;
    }

    public static <T> Stack<T> createStack(){
        if(emptyStack == null){
            synchronized (EmptyStack.class) {
                if(emptyStack == null){
                    emptyStack = new EmptyStack<T>();
                }
            }
        }
        return emptyStack;
    }

    public boolean isEmpty(){
        return false;
    }

    public T peek(){
        return head;
    }

    public Stack<T> getTail(){
        return tail;
    }

    public Stack<T> pop(){
        return tail;
    }

    public Stack<T> push(T elem){
        return new ImmutableStack<T>(elem, this);
    }


    /*For empty stack we have two options, we can create an empty stack as a stack with head = null and
      tail = null and then we do all the validations for null values, or we can create a special
      empty stack and avoid the validations
     */

    private static class EmptyStack<T> implements Stack<T>{

        public boolean isEmpty() {
            return true;
        }

        public T peek() {
            throw new NoSuchElementException();
        }

        public Stack<T> pop() {
            throw new NoSuchElementException();
        }

        public Stack<T> push(T elem) {
            return new ImmutableStack<T>(elem, this);
        }

        public Stack<T> getTail(){
            throw new IllegalStateException();
        }
    }
}
