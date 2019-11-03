package com.bottomlord.week_017;

import com.bottomlord.ListNode;

import java.util.Random;

public class LeetCode_382_1_链表随机节点 {
    class Solution {
        private ListNode head;
        public Solution(ListNode head) {
            this.head = head;
        }

        public int getRandom() {
            ListNode ans = head, cur = head.next;
            int count = 2;
            Random r = new Random();
            while (cur != null) {
                int ran = r.nextInt(count);
                if (ran == 0) {
                    ans = cur;
                }
                count++;
                cur = cur.next;
            }

            return ans.val;
        }
    }
}
