import java.util.Iterator;
import java.util.LinkedList;

public class Deque<Item> implements Iterable<Item> {
    private int size = 0;
    private Node first, last;

    private class Node {
        Item i;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("Please enter a valid element");
        }
        Node oldFirst = first;
        Node first = new Node();
        first.i = item;
        first.next = oldFirst;
        if (size > 0) {
            oldFirst.prev = first;
        } else {
            last = first;
        }
        ++size;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("Please enter a valid element");
        }
        Node oldLast = last;
        last = new Node();
        last.i = item;
        last.next = null;
        last.prev = oldLast;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        ++size;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw java.util.NoSuchElementException("deque is empty");
        }
        Item item = first.i;
        if (size > 1) {
            first = first.next;
            first.prev = null;
        } else {
            first = null;
            last = null;
        }
        --size;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw java.util.NoSuchElementException("deque is empty");
        }
        Item item = last.i;
        if (size > 1) {
            last = last.prev;
            last.next = null;
        } else {
            first = null;
            last = null;
        }
        return item;
    }

    private class listIterator implements Iterator<Item> {
        Node cur = first;

        public boolean hasNext() {
            return cur != null;
        }

        public void remove() {
            throw UnsupportedOperationException("Remove is not supported");
        }

        public Item next() {
            if (cur == null) {
                throw NoSuchElementException();
            }
            Item value = cur.item;
            cur = cur.next;
            return value;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new listIterator();
    }

    // unit testing (required)
    // public static void main(String[] args) {
    //     Deque<Integer> d = new Deque<Integer>();
    //     d.addFirst(1);
    //     d.addLast(2);
        
    // }

}