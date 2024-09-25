package com.bottomlord;

import java.util.List;

public class DictNode {
    public char c;
    public int sum;
    public DictNode parent;
    public List<DictNode> children;

    public DictNode(char c) {
        this.c = c;
    }
}
