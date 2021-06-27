package tree;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MirrorImageBinaryTree {

    public static void main(String[] args) {
        Node tree = new Node("1");
        tree.leftNode = new Node("3");
        tree.rightNode = new Node("2");
        tree.rightNode.leftNode = new Node("5");
        tree.rightNode.rightNode = new Node("4");

        Node mirrorImageTree = mirrorImage(tree);
        mirrorImageTree.forEach(System.out::println);
    }

    public static Node mirrorImage(Node node) {
        if (node == null) {
            return null;
        }
        Node mirrorImageTree = new Node(node.value);
        mirrorImageTree.leftNode = mirrorImage(node.rightNode);
        mirrorImageTree.rightNode = mirrorImage(node.leftNode);
        return mirrorImageTree;
    }

    @Data
    static class Node {

        private Object value;
        private Node leftNode;
        private Node rightNode;

        Node(Object value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("Node(%s)", value);
        }

        @SuppressWarnings("unchecked")
        <T> T value() {
            return (T) value;
        }

        boolean hasLeft() {
            return leftNode != null;
        }

        boolean hasRight() {
            return rightNode != null;
        }

        void forEach(Consumer<Node> action) {
            Queue<Node> queue = new LinkedList<>();
            queue.add(this);
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                action.accept(node);
                if (node.hasLeft()) {
                    queue.add(node.leftNode);
                }
                if (node.hasRight()) {
                    queue.add(node.rightNode);
                }
            }
        }

    }

}