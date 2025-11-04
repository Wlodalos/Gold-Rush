package edu.io.player;

import java.util.Stack;

public class Shed {
    private final Stack<Tool> tools = new Stack<>();
    private static final NoTool NO_TOOL = new NoTool();

    public boolean isEmpty() {
        return tools.isEmpty();
    }

    public void add(Tool tool) {
        if (tool == null) {
            throw new IllegalArgumentException("Cannot add null to shed");
        }
        tools.push(tool);
    }

    public Tool getTool() {
        if (isEmpty()) {
            return NO_TOOL;
        }
        return tools.peek();
    }

    public void dropTool() {
        if (!isEmpty()) {
            tools.pop();
        }
    }
}