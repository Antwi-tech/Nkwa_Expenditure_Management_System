package DSA;

import java.time.YearMonth;

public class myTreeMap {
    private Node root;

    private static class Node {
        YearMonth key;
        double value;
        Node left, right;

        Node(YearMonth key, double value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(YearMonth key, double value) {
        root = insert(root, key, value);
    }

    private Node insert(Node node, YearMonth key, double value) {
        if (node == null) return new Node(key, value);

        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = insert(node.left, key, value);
        else if (cmp > 0)
            node.right = insert(node.right, key, value);
        else
            node.value += value; // aggregate value if key exists

        return node;
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node node) {
        if (node == null) return;

        printInOrder(node.left);
        System.out.println(node.key + ": GHS " + String.format("%.2f", node.value));
        printInOrder(node.right);
    }
}
