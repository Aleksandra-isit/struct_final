import java.util.*;
import java.util.function.Consumer;

public class Tree {
    private class Node {
        private int value;
        private Node right;
        private Node left;
        public Node(int value) {
            this.value = value;
        }
    }
    private Node root;
    private int children = 0;
    public void add(int value){
        root = appendNode(root, value);
    }
    private Node appendNode(Node current, int value){
        if (current == null){
            return  new Node(value);
        }
        if (current.value > value){
            current.left = appendNode(current.left, value);
        }
        else if (current.value < value){
            current.right = appendNode(current.right, value);
        }
        return current;
    }
    public boolean contains(int value){
        return findNode(root, value) != null;
    }
    private Node findNode(Node current, int value){
        if (current == null){
            return  null;
        }
        if (current.value > value){
            return  findNode(current.left,  value);
        }
        else if (current.value < value){
            return  findNode(current.right, value);
        }
        return current;
    }
    public void remove(int value){
        root = removeNode(root, value);
    }
    private Node removeNode(Node current, int value){
        if (current == null){
            return  null;
        }
        if (current.value > value){
            current.left = removeNode(current.left, value);
            return  current;
        }
        else if (current.value < value){
            current.right = removeNode(current.right, value);
            return  current;
        }
        if (current.right == null && current.left == null){
            return  null;
        }
        if (current.left == null && current.right != null){
            return current.right;
        }
        if (current.left != null && current.right == null){
            return current.left;
        }
        Node smallestNodeOnTheRight = findFirst(current.right);
        current.value = smallestNodeOnTheRight.value;
        current.right = removeNode(current.right, smallestNodeOnTheRight.value);
        return current;
    }
    public int findFirst(){
        if (root == null){
            throw new NoSuchElementException();
        }
        return findFirst(root).value;
    }
    private Node findFirst(Node current){
        if (current.left != null){
            return findFirst(current.left);
        }
        return current;
    }
    public int findLast(){
        if (root == null){
            throw new NoSuchElementException();
        }
        return findLast(root).value;
    }
    private Node findLast(Node current){
        if (current.right != null){
            return findLast(current.right);
        }
        return current;
    }
    public void dfs(Consumer<Integer> valueConsumer) {
        dfsInternal(root, valueConsumer);
    }
    private void dfsInternal(Node current, Consumer<Integer> valueConsumer) {
        if (current != null) {
            if (current.left == null && current.right == null){
                children++;
            }
            dfsInternal(current.left, valueConsumer);
            valueConsumer.accept(current.value);
            dfsInternal(current.right, valueConsumer);
        }
    }
    public void bfs(Consumer<Integer> valueConsumer) {
        bfsInternal(valueConsumer);
    }
    private void bfsInternal(Consumer<Integer> valueConsumer) {
        if (root == null) {
            return;
        }

        // FIFO
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            valueConsumer.accept(node.value);

            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }
    public int getChildrenCount() {
        return children;
    }
    public boolean isBalanced(){
        if (root == null)
            throw new NoSuchElementException();
        return isBalanced(root.left) && isBalanced(root.right) && Math.abs(height(root.left) - height(root.right)) <= 1;
    }

    private boolean isBalanced(Node current) {
        if (current == null)
            throw new NoSuchElementException();
        return Math.abs(height(root.left) - height(root.right)) <= 1;
    }

    private int height(Node current) {
        if (current == null)
            throw new NoSuchElementException();
        int leftHeight = 0;
        int rightHeight = 0;
        if (current.left == null && current.right == null) {
            return 1;
        }
        if (current.left != null) {
            leftHeight = height(current.left);
        }
        if (current.right != null) {
            rightHeight = height(current.right);
        }
        return Math.max(leftHeight, rightHeight)+1;
    }
}
