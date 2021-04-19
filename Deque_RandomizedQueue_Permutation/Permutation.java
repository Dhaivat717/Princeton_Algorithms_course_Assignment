public class Permutation {
    public static void main(String[] args) {
        int noOfElements = Integer.parseInt(args[0]);
        RandomizedQueue rq = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }
        while (noOfElements > 0) {
            StdOut.println(rq.dequeue());
            noOfElements--;
        }
    }
}
