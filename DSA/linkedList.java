package DSA;

public class linkedList {
    public String[] toArray() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }

        String[] arr = new String[count];
        current = head;
        int i = 0;
        while (current != null) {
            arr[i++] = current.data;
            current = current.next;
        }
        return arr;
    }

    static class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
        }
    }

    private Node head;

    public void addLast(String data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null)
            current = current.next;
        current.next = newNode;
    }

    public void printList() {
        Node current = head;
        System.out.print("Expenditure History: ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
