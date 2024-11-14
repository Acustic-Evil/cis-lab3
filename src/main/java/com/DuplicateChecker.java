package com;

import javax.enterprise.event.Observes;
import java.util.HashSet;
import java.util.Set;

public class DuplicateChecker {

    public void onTreeChanged(TreeEvent event) {
        Set<Integer> duplicates = findAllDuplicates(event.getSource().getRoot());
        if (duplicates.isEmpty()) {
            System.out.println("No duplicates found.");
        } else {
            System.out.println("Duplicates found: " + duplicates);
        }
    }

    Set<Integer> findAllDuplicates(BinaryTree.Node node) {
        Set<Integer> nodeValues = new HashSet<>();
        Set<Integer> duplicates = new HashSet<>();
        collectDuplicatesRecursive(node, nodeValues, duplicates);
        return duplicates;
    }

    private void collectDuplicatesRecursive(BinaryTree.Node node, Set<Integer> values, Set<Integer> duplicates) {
        if (node == null) {
            return;
        }
        if (values.contains(node.value)) {
            duplicates.add(node.value); // Добавляем значение в дубликаты, если оно уже встречалось
        } else {
            values.add(node.value); // Добавляем значение в множество уникальных узлов
        }
        collectDuplicatesRecursive(node.left, values, duplicates);
        collectDuplicatesRecursive(node.right, values, duplicates);
    }
}