package writo.terminal.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Data structure for recreate tree of story.
 */
public class StructTree {

    long node;
    Map<Long, StructTree> children;

    /**
     * Convert tree into string for sake of storing, which make use of lisp-like S-exp.
     * e.g., (1(2)(3)) means root node 1 with children nodes 2 and 3.
     */
    public String serialize() {
        StringBuilder res = new StringBuilder("(");
        res.append(node);
        for (StructTree child : children.values()) {
            res.append(child.serialize());
        }
        res.append(')');
        return res.toString();
    }

    /**
     * Recursive impl for `deserialize(String)`.
     * @param tree String repr for the whole tree.
     * @param s parsing start (inclusive).
     * @param t parsing end (exclusive).
     * @return StructTree from given part of `tree`.
     */
    static StructTree deserialize(String tree, int s, int t) {
        int loc = s + 1;
        while (Character.isDigit(tree.charAt(loc))) {
            loc++;
        }
        long node = Long.parseLong(tree.substring(s + 1, loc));
        StructTree root = new StructTree(node);
        int depth = 0;
        int from = loc;
        while (loc < t) {
            if (tree.charAt(loc) == '(') depth++;
            else if (tree.charAt(loc) == ')') depth--;
            if (depth == 0) {
                StructTree child = deserialize(tree, from, loc + 1);
                from = loc + 1;
                root.children.put(child.node, child);
            }
            loc++;
        }
        return root;
    }

    /**
     * Reconstruct Tree-shape data structure from S-exp.
     */
    public static StructTree deserialize(String tree) {
        return deserialize(tree, 0, tree.length());
    }

    /**
     * Seeking node with given id in the tree.
     */
    public StructTree find(long id) {
        if (node == id) return this;
        if (children.containsKey(id)) return children.get(id);
        for (var child : children.values()) {
            var node = child.find(id);
            if (null != node) return child.children.get(id);
        }
        return null;
    }

    public void add(long id) {
        children.put(id, new StructTree(id));
    }

    public StructTree(long id) {
        node = id;
        children = new HashMap<>();
    }

}
