# [LeetCode_2154_将找到的值乘以2](https://leetcode-cn.com/problems/keep-multiplying-found-values-by-two/)
## 解法
### 思路
- 初始化一个布尔变量
- 2层循环，外层依赖布尔变量做退出判断
- 外层开始时先使布尔值变为false
- 内层遍历数组找original，如果找到就将布尔值变成true，并且将original变成2倍
- 直到外层结束，返回original值
### 代码
```java
class Solution {
    public int findFinalValue(int[] nums, int original) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int num : nums) {
                if (num == original) {
                    original = original * 2;
                    flag = true;
                    break;
                }
            }
        }

        return original;
    }
}
```
# [LeetCode_LCP44_开幕式烟火](https://leetcode-cn.com/problems/sZ59z6/)
## 解法
### 思路
dfs
### 代码
```java
class Solution {
    public int numColor(TreeNode root) {
        Set<Integer> set = new HashSet<>();
        dfs(root, set);
        return set.size();
    }
    
    private void dfs(TreeNode node, Set<Integer> set) {
        if (node == null) {
            return;
        }
        
        set.add(node.val);
        dfs(node.left, set);
        dfs(node.right, set);
    }
}
```
# [LeetCode_LCS01_下载插件](https://leetcode-cn.com/problems/Ju9Xwi/)
## 解法
### 思路
- 下载的时间复杂度是O(1)，扩容的时间复杂度是O(log2)
- 所以选择先进行扩容，再一次性下载
### 代码
```java
class Solution {
    public int leastMinutes(int n) {
       return (int)Math.ceil(Math.log(n) / Math.log(2)) + 1;
    }
}
```
# [LeetCode_LCS02_完成一半题目](https://leetcode-cn.com/problems/WqXACV/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1414_和为K的最少斐波那契数字数目](https://leetcode-cn.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/)
## 解法
### 思路
递归
### 代码
```java
class Solution {
    private int count = 0;

    public int findMinFibonacciNumbers(int k) {
        recuse(k, new HashSet<>());
        return count;
    }

    private void recuse(int k, Set<Integer> set) {
        int fab = fab(k, set);
        count++;
        if (fab == k) {
            return;
        }

        if (set.contains(k - fab)) {
            count++;
            return;
        }

        recuse(k - fab, set);
    }

    private int fab(int k, Set<Integer> set) {
        int x = 0, z = 0, y = 1;
        while (z <= k) {
            set.add(y);
            z = x + y;
            x = y;
            y = z;
        }

        return x;
    }
}
```