package com.bottomlord;

import java.util.List;

/**
 * @author LiveForExperience
 * @date 2019/7/13 17:12
 */
public class Node {
    public int val;
    public List<Node> children;

    public Node(){}

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}
