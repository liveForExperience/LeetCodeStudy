package com.bottomlord.week_025;

/**
 * @author ThinkPad
 * @date 2019/12/28 9:22
 */
public class TrieNode {
    public boolean end;
    public int count;
    public TrieNode[] children = new TrieNode[26];
    public String product;
}
