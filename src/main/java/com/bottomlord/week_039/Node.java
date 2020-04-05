package com.bottomlord.week_039;

/**
 * @author ChenYue
 * @date 2020/4/5 13:56
 */
public class Node {
    public int key;
    public int value;
    public int freq;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.freq = 1;
    }
}
