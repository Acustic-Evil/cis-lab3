package com;


import javax.enterprise.event.Event;
import javax.inject.Inject;

public class BinaryTree {
    private Node root;

    @Inject
    private Event<TreeEvent> event;

    public void addNode(int value) {
//        System.out.println("Adding node with value: " + value);
        root = addRecursive(root, value);
        fireEvent("Node added with value: " + value);
    }

    public void removeNode(int value) {
//        System.out.println("Removing the last node with value: " + value);
        root = removeLastRecursive(root, value);
    }

    public Node getRoot() {
        return root;
    }

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            System.out.println("-----------------------------------\nNode created with value: " + value);
            return new Node(value);
        }

        if (value <= current.value) {
            current.left = addRecursive(current.left, value);
        } else {
            current.right = addRecursive(current.right, value);
        }
        return current;
    }

    private Node removeLastRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }

        // Обходим дерево рекурсивно, чтобы найти последний узел с заданным значением.
        current.left = removeLastRecursive(current.left, value);
        current.right = removeLastRecursive(current.right, value);

        // После обхода левого и правого поддерева проверяем текущий узел
        if (current.value == value && isLeaf(current)) {
            System.out.println("Removing leaf node with value: " + value);
            return null; // Удаляем узел, если он лист и соответствует значению
        }
        return current;
    }

    // Проверка, является ли узел листом
    private boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

//    private int findSmallestValue(Node root) {
//        return root.left == null ? root.value : findSmallestValue(root.left);
//    }

    private void fireEvent(String message) {
        event.fire(new TreeEvent(this, message));
    }

    // Метод для печати дерева
    public void printTree() {
        System.out.println("Final tree structure:");
        printRecursive(root, 0);
    }

    private void printRecursive(Node node, int level) {
        if (node != null) {
            printRecursive(node.right, level + 1); // Печать правого поддерева
            System.out.println(getIndentation(level) + node.value); // Отображение текущего узла с отступом
            printRecursive(node.left, level + 1); // Печать левого поддерева
        }
    }

    private String getIndentation(int level) {
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indentation.append("  ");
        }
        return indentation.toString();
    }

    public static class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
        }
    }
}