package com.bottomlord.week_074;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode_649_1_Dota2参议院 {
    public String predictPartyVictory(String senate) {
        int len = senate.length();

        Queue<Integer> radiantQueue = new LinkedList<>(),
                       direQueue = new LinkedList<>();

        for (int i = 0; i < len; i++) {
            if (senate.charAt(i) == 'R') {
                radiantQueue.offer(i);
            } else {
                direQueue.offer(i);
            }
        }

        while (!radiantQueue.isEmpty() && !direQueue.isEmpty()) {
            int rIndex = radiantQueue.poll(), dIndex = direQueue.poll();

            if (rIndex < dIndex) {
                radiantQueue.offer(rIndex + len);
            } else {
                direQueue.offer(dIndex + len);
            }
        }

        return radiantQueue.isEmpty() ? "Dire" : "Radiant";
    }
}
