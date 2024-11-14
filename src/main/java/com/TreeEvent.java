package com;

public class TreeEvent {
    private final BinaryTree source;
    private final String message;

    public TreeEvent(BinaryTree source, String message) {
        this.source = source;
        this.message = message;
    }

    public BinaryTree getSource() {
        return source;
    }

    public String getMessage() {
        return message;
    }
}
