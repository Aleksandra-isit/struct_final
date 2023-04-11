import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.add(2);
        tree.add(1);
        tree.add(10);
        tree.add(5);
        tree.add(3);
        tree.add(6);
        tree.add(12);
        tree.add(11);
        tree.add(15);

        System.out.println(tree.contains(3));
        System.out.println(tree.contains(10));
        System.out.println(tree.findFirst());
        System.out.println(tree.findLast());

        List<Integer> dfsOrderItems = new ArrayList<>();
        tree.dfs(dfsOrderItems::add);
        System.out.println(dfsOrderItems);
        System.out.println(tree.getChildrenCount());
        System.out.println(tree.isBalanced());
    }
}