package jp.ne.paypay;

import jp.ne.paypay.ImmutableQueue;
import jp.ne.paypay.Queue;
import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class ImmutableQueueTest {

    @Test
    public void createStack() {
        Queue<Integer> queue = ImmutableQueue.createQueue();
        Assert.assertNotNull(queue);
    }

    @Test
    public void enQueue(){
        Queue<Integer> queue = ImmutableQueue.createQueue();
        Integer expected = 1;
        Assert.assertEquals(expected , queue.enQueue(expected).head());
        queue = queue.enQueue(1).enQueue(2).enQueue(3).enQueue(4).enQueue(5);
        Assert.assertEquals(expected, queue.head());
        Integer expectedMiddle = 3;
        Assert.assertEquals(expectedMiddle, queue.deQueue().deQueue().head());
        Integer expectedEnd = 5;
        Assert.assertEquals(expectedEnd, queue.deQueue().deQueue().deQueue().deQueue().head());
    }

    @Test
    public void deQueue() {
        Queue<Integer> queue = ImmutableQueue.createQueue();
        Integer expected = 1;
        queue = queue.enQueue(expected);
        Assert.assertEquals(expected , queue.head());
        Assert.assertTrue(queue.deQueue().isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void deQueueWithNoElements() {
        Queue<Integer> queue = ImmutableQueue.createQueue();
        queue.deQueue();
    }

    @Test(expected = NoSuchElementException.class)
    public void headWithNoElements() {
        Queue<Integer> queue = ImmutableQueue.createQueue();
        queue.head();
    }

    @Test
    public void isEmpty() {
        Queue<Integer> queue = ImmutableQueue.createQueue();
        Assert.assertTrue(queue.isEmpty());
    }

    @Test
    public void isNotEmpty() {
        Queue<Integer> queue = ImmutableQueue.createQueue();
        Assert.assertFalse(queue.enQueue(2).isEmpty());
    }
}
