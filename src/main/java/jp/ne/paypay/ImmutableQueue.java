package jp.ne.paypay;

import java.util.NoSuchElementException;

public class ImmutableQueue<T> implements Queue<T>{
    private Stack<T> addingQueue;
    private Stack<T> removingQueue;
    private static Queue emptyQueue;

    ImmutableQueue(Stack<T> addingQueue, Stack<T> removingQueue){
        this.addingQueue = addingQueue;
        this.removingQueue = removingQueue;
    }
    public static <T> Queue<T> createQueue(){
        if(emptyQueue == null){
            synchronized (EmptyQueue.class) {
                if(emptyQueue == null){
                    emptyQueue = new EmptyQueue<T>();
                }
            }
        }
        return emptyQueue;
    }

    //This method allows nulls as valid values
    public Queue<T> enQueue(T element) {
        return new ImmutableQueue<T>(this.addingQueue.push(element), this.removingQueue);
    }

    private boolean checkEmptyQueue(){
        if(!removingQueue.isEmpty()){
            return removingQueue.getTail().isEmpty() && addingQueue.isEmpty();
        }
        if(!addingQueue.isEmpty()){
            return addingQueue.getTail().isEmpty();
        }
        return true;
    }

    public Queue<T> deQueue() {
        if(checkEmptyQueue()){
            return createQueue();
        }
        if(removingQueue.isEmpty()){
            return new ImmutableQueue<T>(ImmutableStack.<T>createStack(), flush(addingQueue).getTail());
        }
        else {
            return new ImmutableQueue<T>(addingQueue, removingQueue.getTail());
        }
    }

    public T head(){
        return removingQueue.peek();
    }

    private Stack<T> flush(Stack<T> addingQueue){
        Stack<T> current = addingQueue;
        Stack<T> flushedStack = ImmutableStack.createStack();

        while (!current.isEmpty()) {
            flushedStack = flushedStack.push(current.peek());
            current = current.getTail();
        }
        return flushedStack;
    }

    public boolean isEmpty() {
        return false;
    }

    private static class EmptyQueue<T> implements Queue<T>
    {
        public boolean isEmpty() {
            return true;
        }

        public T head() {
            throw new NoSuchElementException();
        }

        public Queue<T> deQueue() {
            throw new NoSuchElementException();
        }

        public Queue<T> enQueue(T elem) {
            Stack<T> stack =  ImmutableStack.createStack();
            return new ImmutableQueue<T>(stack, stack.push(elem));
        }
    }
}