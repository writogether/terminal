package writo.terminal.util;

import java.util.HashMap;
import java.util.Map;

public class StructTree {

    long node;
    Map<Long, StructTree> children;

    public String serialize() {
        StringBuilder res = new StringBuilder("(");
        res.append(node);
        for (StructTree child : children.values()) {
            res.append(child.serialize());
        }
        res.append(')');
        return res.toString();
    }

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

    public static StructTree deserialize(String tree) {
        return deserialize(tree, 0, tree.length());
    }

    public void add(long id) {
        children.put(id, new StructTree(id));
    }

    public StructTree(long id) {
        node = id;
        children = new HashMap<>();
    }

}
