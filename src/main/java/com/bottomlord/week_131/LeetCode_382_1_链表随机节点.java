package com.bottomlord.week_131;

import com.bottomlord.ListNode;

import java.util.Random;

/**
 * @author chen yue
 * @date 2022-01-16 19:02:31
 */
public class LeetCode_382_1_链表随机节点 {
    class Solution {
        private ListNode head;
        private Random random;
        public Solution(ListNode head) {
            this.head = head;
            this.random = new Random();
        }

        public int getRandom() {
            int num = 0, index = 1;
            for (ListNode node = head; node != null; node = node.next) {
                if (random.nextInt(index++) == 0) {
                    num = node.val;
                }
            }
            return num;
        }
    }
}
