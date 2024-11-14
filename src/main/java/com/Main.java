package com;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {
    protected static Weld weld;
    protected static WeldContainer container;

    public static void main(String args[]) {
        weld = new Weld();
        container = weld.initialize();


        BinaryTree tree = container.select(BinaryTree.class).get();
        LongestPathFinder longestPathFinder = container.select(LongestPathFinder.class).get();
        DuplicateChecker duplicateChecker = container.select(DuplicateChecker.class).get();

        tree.addNode(10);
        tree.addNode(5);
        tree.addNode(20);
        tree.addNode(7);
        tree.addNode(5); // Дубликат, для проверки DuplicateChecker
        tree.addNode(21);
        tree.addNode(10);
//        tree.removeNode(10); // Удаление узла

        tree.printTree();

        // Проверка и вывод всех дубликатов
        Set<Integer> finalDuplicates = duplicateChecker.findAllDuplicates(tree.getRoot());
        System.out.println("Final duplicates in the tree: " + finalDuplicates);

        // Вывод длины и узлов самой длинной ветки
        List<Integer> longestPath = new ArrayList<>();
        int maxPathLength = longestPathFinder.findLongestPath(tree.getRoot(), longestPath);
        System.out.println("Final longest path length: " + maxPathLength);
        System.out.println("Nodes in the final longest path: " + longestPath);
    }
}

