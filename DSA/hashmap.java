package DSA;

public class hashmap<K, V> {
       static class Node<K, V> {
              K key;
              V value;
              Node<K, V> next;

              Node(K key, V value) {
                     this.key = key;
                     this.value = value;
              }
       }

       private final int SIZE = 16;
       private Node<K, V>[] buckets;

       @SuppressWarnings("unchecked")
       public hashmap() {
              buckets = new Node[SIZE];
       }

       private int getIndex(K key) {
              return Math.abs(key.hashCode()) % SIZE;
       }

       public void put(K key, V value) {
              int index = getIndex(key);
              Node<K, V> head = buckets[index];

              for (Node<K, V> curr = head; curr != null; curr = curr.next) {
                     if (curr.key.equals(key)) {
                            curr.value = value;
                            return;
                     }
              }

              Node<K, V> newNode = new Node<>(key, value);
              newNode.next = head;
              buckets[index] = newNode;
       }

       public V get(K key) {
              int index = getIndex(key);
              Node<K, V> curr = buckets[index];
              while (curr != null) {
                     if (curr.key.equals(key)) return curr.value;
                     curr = curr.next;
              }
              return null;
       }

       public void printAll() {
              for (int i = 0; i < SIZE; i++) {
                     Node<K, V> current = buckets[i];
                     while (current != null) {
                            System.out.println(current.key + " => " + current.value);
                            current = current.next;
                     }
              }
       }
}
