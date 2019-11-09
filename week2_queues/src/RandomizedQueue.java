import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
        size = 0;
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        rq.enqueue("no1");
        rq.enqueue("no2");
        rq.enqueue("no3");
        rq.enqueue("no4");
        rq.enqueue("no5");
        rq.enqueue("no6");
        rq.dequeue();
        rq.dequeue();
        rq.dequeue();
        rq.dequeue();
        StdOut.println(rq.dequeue());

        for (String s : rq) {
            StdOut.println(s);
        }

    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        items[size++] = item;
        if (size == items.length) {
            Item[] newItems = (Item[]) new Object[items.length * 2];
            System.arraycopy(items, 0, newItems, 0, items.length);
            items = newItems;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) throw new NoSuchElementException();
        int index = StdRandom.uniform(size);
        Item item = items[index];
        items[index] = items[size - 1];
        items[size - 1] = null;
        size--;
        if (size > 0 && size == items.length / 4) {
            Item[] newItems = (Item[]) new Object[items.length / 2];
            for (int i = 0; i < size; i++) {
                newItems[i] = items[i];
            }
            items = newItems;
        }
        return item;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) throw new NoSuchElementException();
        int index = StdRandom.uniform(size);
        return items[index];
    }

    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private Item[] randomList;
        private int index = 0;

        public RandomIterator() {
            randomList = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                randomList[i] = items[i];
            }
            StdRandom.shuffle(randomList);
        }

        public boolean hasNext() {
            return index < size;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return randomList[index++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
