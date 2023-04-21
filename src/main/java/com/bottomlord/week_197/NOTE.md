# [LeetCode_1026_节点与其祖先之间的最大差值](https://leetcode.cn/problems/maximum-difference-between-node-and-ancestor/)
## 解法
### 思路
dfs
- 返回值使用一个数组，记录子树中的最大和最小值
- 和当前节点值做比较
- 退出条件是节点为空，为空时候，做一下特殊处理，比如最大最小值都设置成int最大值
### 代码
```java
class Solution {
    private int max;

    public int maxAncestorDiff(TreeNode root) {
        this.max = 0;
        dfs(root);
        return max;
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        }

        int val = node.val;
        int[] left = dfs(node.left), right = dfs(node.right);

        if (left[0] == Integer.MAX_VALUE) {
            left[0] = val;
        }
        
        if (left[1] == Integer.MAX_VALUE) {
            left[1] = val;
        }

        if (right[0] == Integer.MAX_VALUE) {
            right[0] = val;
        }

        if (right[1] == Integer.MAX_VALUE) {
            right[1] = val;
        }
        
        int min = Math.min(left[0], right[0]), max = Math.max(left[1], right[1]);
        this.max = Math.max(this.max, Math.max(Math.abs(val - min), Math.abs(val - max)));
        return new int[]{Math.min(min, val), Math.max(max, val)};
    }
}
```
# [LeetCode_1043_分割数组以得到最大和](https://leetcode.cn/problems/partition-array-for-maximum-sum/)
## 解法
### 思路
dfs+记事本
- dfs入参：
  - 数组
  - 当前坐标
  - 不包含当前坐标元素的子数组长度
  - 不包含当前坐标元素的子数组最大值
  - 目标k值
  - 记事本（key为：坐标+长度+最大值，value为最大和）
- 退出条件：坐标越界
- 主逻辑
  - 判断记事本是否有记录，有的话直接返回，从而减枝
  - 以当前坐标元素为子数组的结尾元素，计算[长度 * 最大和 + dfs下一个子数组得到的和]
  - 判断当前长度+1是否大于k，如果不是，就获取另一种情况的最大和，即继续扩展子数组的情况
  - 得到的结果，如果有2种情况就取最大值，存在记事本里
### 代码
```java
class Solution {
    private int k;
    private int[] arr;
    private Map<String, Integer> map;
    public int maxSumAfterPartitioning(int[] arr, int k) {
        this.arr = arr;
        this.k = k;
        this.map = new HashMap<>();
        return dfs(0, 0, 0);
    }

    private int dfs(int index, int len, int curMax) {
        if (index >= arr.length) {
            return len * curMax;
        }

        String key = index + ":" + len + ":" + curMax;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        int ans = len * curMax + dfs(index + 1, 1, arr[index]);
        if (len + 1 <= k) {
            ans = Math.max(ans, dfs(index + 1, len + 1, Math.max(curMax, arr[index])));
        }
        
        map.put(key, ans);
        return ans;
    }
}
```
## 解法二
### 思路
动态规划：
- dp[i]：[0,i]范围内得到的最大和
- 状态转移方程：
  - max(dp[i], dp[i - k] + k * max)
- 初始化：dp[0] = arr[0]
- 返回dp[n - 1]
### 代码
```java
class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n];
        
        for (int i = 0; i < n; i++) {
            int max = arr[i];
            for (int len = 1; len <= k && i - len + 1 >= 0; len++) {
                max = Math.max(max, arr[i - len + 1]);
                dp[i] = Math.max(dp[i], (i - len >= 0 ? dp[i - len] : 0) + len * max);
            }
        }
        
        return dp[n - 1];
    }
}
```
# [LeetCode_26236_数组归约运算](https://leetcode.cn/problems/array-reduce-transformation/)
## 解法
### 思路
模拟
### 代码
```javascript
var reduce = function(nums, fn, init) {
    if (nums.length > 0) {
        for (let i = 0; i < nums.length; i++) {
            init = fn(init, nums[i]);
        }
    }

    return init;
};
```
# [LeetCode_2629_复合函数](https://leetcode.cn/problems/function-composition/)
## 解法
### 思路
模拟
### 代码
```javascript
var compose = function(functions) {
	return function(x) {
        if (functions.length == 0 ) {
            return x;
        }

        for (let i = functions.length - 1; i >= 0; i--) {
            x = functions[i](x);
        }

        return x;
    }
};
```
# [LeetCode_2634_过滤数组中的元素](https://leetcode.cn/problems/filter-elements-from-array/)
## 解法
### 思路
模拟，fn中传入元素和坐标
### 代码
```javascript
var filter = function(arr, fn) {
    let newArr = [];
    for (let i = 0; i < arr.length; i++) {
        if (fn(arr[i], i)) {
            newArr.push(arr[i]);
        }
    }
    return newArr;
};
```
# [LeetCode_2635_转换数组中的每个元素](https://leetcode.cn/problems/apply-transform-over-each-element-in-array/)
## 解法
### 思路
模拟
### 代码
```javascript
var map = function(arr, fn) {
    for (let i = 0; i < arr.length; i++) {
        arr[i] = fn(arr[i], i);
    }
    return arr;
};
```