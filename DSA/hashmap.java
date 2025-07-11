package DSA;

public class hashmap{
       public static class Node<k, v>{
              k key;
              v value;

              Node<k, v> next;

              Node(k key, v value){
                    this.key = key;
                    this.value = value;
                    this.next = null;
              }

              private final int SIZE = 16; // you can change this
              private Node<k, v>[] buckets;

              @SuppressWarnings("unchecked")
              public void MyHashMap() {
                     buckets = new Node[SIZE]; // array of linked list heads
              }

              private int getIndex(k key) {
                     return Math.abs(key.hashCode()) % SIZE;
              }

              public void put(k key, v value) {
                     int index = getIndex(key);
                     Node<k, v> head = buckets[index];

                     Node<k, v> current = head;
                     while (current != null) {
                            if (current.key.equals(key)) {
                                   current.value = value; // overwrite
                                   return;
                            }
                            current = current.next;
                     }

                     // Insert new node at head
                     Node<k, v> newNode = new Node<>(key, value);
                     newNode.next = head;
                     buckets[index] = newNode;
              }

              public v get(k key) {
                     int index = getIndex(key);
                     Node<k, v> current = buckets[index];

                     while (current != null) {
                            if (current.key.equals(key)) {
                                   return current.value;
                            }
                            current = current.next;
                     }

                     return null; // not found
              }

              public boolean containsKey(k key) {
                     return get(key) != null;
              }

              public void remove(k key) {
                     int index = getIndex(key);
                     Node<k, v> current = buckets[index];
                     Node<k, v> prev = null;

                     while (current != null) {
                            if (current.key.equals(key)) {
                                   if (prev == null) {
                                          buckets[index] = current.next;
                                   } else {
                                          prev.next = current.next;
                                   }
                                   return;
                            }

                            prev = current;
                            current = current.next;
                     }
              }

              public void printAll() {
                     for (int i = 0; i < SIZE; i++) {
                            Node<k, v> current = buckets[i];
                            while (current != null) {
                                   System.out.println(current.key + " : " + current.value);
                                   current = current.next;
                            }


                     }

              }
       }
}
