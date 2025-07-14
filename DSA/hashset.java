package DSA;

public class hashset {
    public static final int SIZE = 16; // ✅ Fixed: moved and defined only once

    public static class Node {
        public String value;
        public Node next;

        Node(String value) {
            this.value = value;
        }
    }

    public Node[] buckets;

    @SuppressWarnings("unchecked")
    public hashset() {
        buckets = new Node[SIZE];
    }

    private int getIndex(String value) {
        return Math.abs(value.hashCode()) % SIZE;
    }

    public void add(String value) {
        int index = getIndex(value);
        Node curr = buckets[index];

        while (curr != null) {
            if (curr.value.equalsIgnoreCase(value)) return;
            curr = curr.next;
        }

        Node newNode = new Node(value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
    }

    public boolean contains(String value) {
        int index = getIndex(value);
        Node curr = buckets[index];

        while (curr != null) {
            if (curr.value.equalsIgnoreCase(value)) return true;
            curr = curr.next;
        }
        return false;
    }

    public void remove(String value) {
        int index = getIndex(value);
        Node curr = buckets[index];
        Node prev = null;

        while (curr != null) {
            if (curr.value.equalsIgnoreCase(value)) {
                if (prev == null) buckets[index] = curr.next;
                else prev.next = curr.next;
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    public void printAll() {
        for (int i = 0; i < SIZE; i++) {
            Node curr = buckets[i];
            while (curr != null) {
                System.out.println("📂 " + curr.value);
                curr = curr.next;
            }
        }
    }
}
