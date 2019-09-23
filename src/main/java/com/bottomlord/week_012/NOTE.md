# LeetCode_386_字典序排数
## 题目
给定一个整数 n, 返回从 1 到 n 的字典顺序。

例如，

给定 n =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。

请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000。
## 解法
### 思路
以构建10叉树的思路，dfs遍历整个序列，每一层都将元素放入list中，最终返回
- 需要注意，因为第一层是从1开始的，所以会出现`10+0`以及`1+9`都得到10，从而10重复的问题，需要特别去除
### 代码
```java
class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        dfs(ans, 1, n);
        return ans;
    }

    private void dfs(List<Integer> ans, int start, int n) {
        if (start > n) {
            return;
        }

        for (int i = 0; i < 10 && i + start <= n; i++) {
            if (start == 1 && i == 9) {
                continue;
            }
            ans.add(start + i);
            dfs(ans, (start + i) * 10, n);
        }
    }
}
```