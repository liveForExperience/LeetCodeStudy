# [LeetCode_2924_找到冠军II](https://leetcode.cn/problems/find-champion-ii)
## 解法
### 思路
根据edges数组，计算每个节点的入度数，如果入度数为0代表是可能的冠军节点，如果可能的节点数为1，则为冠军，否则就说明无法找到冠军节点。
### 代码
```java
class Solution {
    public int findChampion(int n, int[][] edges) {
        int[] cnt = new int[n];
        for (int[] edge : edges) {
            cnt[edge[1]]++;
        }

        int ans = -1;
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] == 0) {
                if (ans != -1) {
                    return -1;
                }
                
                ans = i;
            }
        }
        
        return ans;
    }
}
```