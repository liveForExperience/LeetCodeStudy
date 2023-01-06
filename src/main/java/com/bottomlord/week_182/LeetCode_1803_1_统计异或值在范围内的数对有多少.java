package com.bottomlord.week_182;

/**
 * @author chen yue
 * @date 2023-01-06 13:29:38
 */
public class LeetCode_1803_1_统计异或值在范围内的数对有多少 {
    private Tire tire;

    public int countPairs(int[] nums, int low, int high) {
        this.tire = new Tire();
        int ans = 0;
        for (int num : nums) {
            ans += tire.get(num, high + 1) - tire.get(num, low);
            tire.insert(num);
        }
        return ans;
    }

    class Tire {

        private TireNode root;

        public Tire() {
            this.root = new TireNode();
        }

        public void insert(int num) {
            TireNode node = this.root;

            for (int i = 15; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.children[bit] == null) {
                    node.children[bit] = new TireNode();
                }
                node = node.children[bit];
                node.sum++;
            }
        }

        public int get(int num, int limit) {
            int sum = 0;
            TireNode node = this.root;
            for (int i = 15; i >= 0 && node != null; i--) {
                int v = (num >> i) & 1;
                if ((limit >> i & 1) != 0) {
                    if (node.children[v] != null) {
                        sum += node.children[v].sum;
                    }
                    node = node.children[v ^ 1];
                } else {
                    node = node.children[v];
                }
            }

            return sum;
        }
    }

    class TireNode {
        private TireNode[] children;
        private int sum;

        public TireNode() {
            this.children = new TireNode[2];
            this.sum = 0;
        }
    }
}
