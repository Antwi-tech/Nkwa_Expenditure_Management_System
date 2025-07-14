package DSA;

public class priorityqueue<T extends Comparable<T>> {
    private arrayList<T> heap;

    public priorityqueue() {
        heap = new arrayList<>();
    }

    public void add(T value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    public T peek() {
        if (heap.size() == 0) return null;
        return heap.get(0);
    }

    public T poll() {
        if (heap.size() == 0) return null;

        T root = heap.get(0);
        T last = heap.get(heap.size() - 1);

        heap.set(0, last);
        heapifyDown(0);
        return root;
    }

    public int size() {
        return heap.size();
    }

    private void heapifyUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap.get(i).compareTo(heap.get(parent)) >= 0) break;
            heap.swap(i, parent);
            i = parent;
        }
    }

    private void heapifyDown(int i) {
        int size = heap.size();
        while (i < size) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = i;

            if (left < size && heap.get(left).compareTo(heap.get(smallest)) < 0)
                smallest = left;
            if (right < size && heap.get(right).compareTo(heap.get(smallest)) < 0)
                smallest = right;

            if (smallest == i) break;

            heap.swap(i, smallest);
            i = smallest;
        }
    }

    public void printAll() {
        for (int i = 0; i < heap.size(); i++) {
            System.out.println(heap.get(i));
        }
    }
}
