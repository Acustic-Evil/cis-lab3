package com;

import javax.enterprise.event.Observes;
import java.util.ArrayList;
import java.util.List;

public class LongestPathFinder {

    public void onTreeChanged(@Observes TreeEvent event) {
        List<Integer> longestPath = new ArrayList<>();
        int maxPathLength = findLongestPath(event.getSource().getRoot(), longestPath);
        System.out.println("Calculated longest path length in the tree: " + maxPathLength);
        System.out.println("Nodes in the longest path: " + longestPath);
    }

    int findLongestPath(BinaryTree.Node node, List<Integer> path) {
        if (node == null) {
            return 0;
        }

        List<Integer> leftPath = new ArrayList<>();
        List<Integer> rightPath = new ArrayList<>();

        int leftPathLength = findLongestPath(node.left, leftPath);
        int rightPathLength = findLongestPath(node.right, rightPath);

        if (leftPathLength > rightPathLength) {
            path.addAll(leftPath);
        } else {
            path.addAll(rightPath);
        }

        path.add(node.value);
        return Math.max(leftPathLength, rightPathLength) + 1;
    }
}