public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] a;
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        a = (Item[]) new Object[2];

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (size - 1 == a.length) {
            resize(2 * a.length);
        }
        a[size] = item;
        size += 1;
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    // remove and return a random item
    public Item dequeue() {
        int i = (int) Math.floor(Math.random() * (size));
        Item item = a[i];
        a[i] = a[size - 1];
        a[size - 1] = null;
        size -= 1;
        if (a.length / 4 == size) {
            resize(a.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int i = (int) Math.floor(Math.random() * (size));
        return a[i];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();

    }

    private class RandomizedQueueIterator implements Iterator<item> {
        private item[] ia;
        private int i;

        public void RandomizedQueueIterator() {
            List<Item> list = Arrays.asList(a);
            Collections.shuffle(list);
            list.toArray(ia);
        }

        public boolean hasNext() {
            return i < size;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported");
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("Element is greater than size of queue");
            return ia[i];
            i++;
        }

    }

    // unit testing (required)
    // public static void main(String[] args){

    // }
}
