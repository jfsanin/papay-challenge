package jp.ne.paypay;

public interface Stack <T>{

    boolean isEmpty();
    T peek();
    Stack<T> pop();
    Stack<T> getTail();
    Stack<T> push(T elem);
}
