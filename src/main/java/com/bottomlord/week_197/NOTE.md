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
# [LeetCode_2637_有时间限制的Promise对象](https://leetcode.cn/problems/promise-time-limit/
## 解法
### 思路
使用Promise的race函数，传入fn和另一个Promise，新的promise中传入一个setTimeout定时器，设置要求的t毫秒，看哪个先返回，新的promise种使用reject回调来实现题目要求的错误提示。
### 代码
```javascript
var timeLimit = function(fn, t) {
	return async function(...args) {
        return Promise.race([
            fn(...args),
            new Promise((resolve, reject) => {
                setTimeout(() => {
                    reject('Time Limit Exceeded');
                }, t);
            })
        ]);
    }
};
```
# [LeetCode_2639_查询网格图中每一列的宽度](https://leetcode.cn/problems/find-the-width-of-columns-of-a-grid/)
## 解法
### 思路
- 初始化结果数组，长度为列的长度
- 遍历矩阵，通过Integer的toStringAPI获取长度，并更新结果数组
- 遍历结束后返回结果数组
### 代码
```java
class Solution {
    public int[] findColumnWidth(int[][] grid) {
        int c = grid[0].length;
        int[] ans = new int[c];
        for (int[] arr : grid) {
            for (int j = 0; j < c; j++) {
                ans[j] = Math.max(ans[j], Integer.toString(arr[j]).length());
            }
        }
        return ans;
    }
}
```
# [LeetCode_2643_一最多的行](https://leetcode.cn/problems/row-with-maximum-ones/)
## 解法
### 思路
模拟
- 遍历矩阵并计算每一行的1的个数
- 每一行结束，讲统计值与暂存的最大值进行比较，如果更大，就更新最大值和行坐标
- 遍历结束返回结果数组
### 代码
```java
class Solution {
    public int[] rowAndMaximumOnes(int[][] mat) {
        int[] ans = new int[]{0, 0};
        for (int i = 0; i < mat.length; i++) {
            int cnt = 0;
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    cnt++;
                }
            }
            
            if (cnt > ans[1]) {
                ans[0] = i;
                ans[1] = cnt;
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_2644_找出可整除性得分最大的整数](https://leetcode.cn/problems/find-the-maximum-divisibility-score/)
## 解法
### 思路
模拟
- 嵌套循环
  - 外层遍历divisors
  - 内层遍历nums
- 每一个divisor都与内层的num做取模运算，判断是否能整除遍历到的num，如果能就累加个数
- 内层遍历结束，统计的个数与暂存的最大值比较
  - 如果大于最大值，就更新最大值和结果值为当前divisor
  - 如果等于最大值，就将当前divisor与结果值作比较，取较小值
- 遍历结束，返回结果值
### 代码
```java
class Solution {
    public int maxDivScore(int[] nums, int[] divisors) {
        int max = 0, ans = Integer.MAX_VALUE;
        for (int divisor : divisors) {
            int cnt = 0;
            for (int num : nums) {
                if (num % divisor == 0) {
                    cnt++;
                }
            }

            if (cnt > max) {
                max = cnt;
                ans = divisor;
            }

            if (cnt == max) {
                ans = Math.min(divisor, ans);
            }
        }

        return ans;
    }
}
```
# [LeetCode_2648_生成斐波那契数列](https://leetcode.cn/problems/generate-fibonacci-sequence/)
## 解法
### 思路
模拟
- 使用pre和cur变量记录斐波那契数列的前两个元素
- 初始化pre=0，cur=1
- 通过死循环来保证生成器通过next函数能不断获得新的值
- 通过yield关键字来输出暂停后的cur值，这个cur值是通过之前的pre+cur获得，之前的pre则赋值为之前的cur
### 代码
```javascript
var fibGenerator = function*() {
    let pre = 0;
    let cur = 1;
    yield pre;
    yield cur;
    while(true) {
        [cur, pre] = [pre + cur, cur];
        yield cur;
    };
};
```
# [LeetCode_1027_最长等差数列](https://leetcode.cn/problems/longest-arithmetic-subsequence/)
## 解法
### 思路
动态规划
- dp[i][j]：
  - 此处的二维数组，因为第二位的大小不定，所以实际使用Map[]来表示
  - dp[i][j]的含义是，以i坐标为结尾的子序列，等差值为j的情况下的最长子序列长度
- 状态转移方程：dp[i][j] = dp[k][j] + 1，k < i & nums[i] - nums[k] == j
- 结果取转移过程中获取的最大值
### 代码
```java
class Solution {
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        Map[] dp = new HashMap[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap();
        }
        
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int diff = nums[i] - nums[j];
                int cur = ((Integer)dp[j].getOrDefault(diff, 0)) + 1;
                dp[i].put(diff, cur);
                max = Math.max(max, cur);
            }
        }
        
        return max + 1;
    }
}
```
# [LeetCode_1105_填充书架](https://leetcode.cn/problems/filling-bookcase-shelves/)
## 解法
### 思路
动态规划：
- dp[i]：前i本书所需要的最小的书架高度
- 状态转移方程：
  - dp[i] = Math.min(dp[i], dp[j] + k)，0 < j <= k <= i，sum(width[j]) <= k
  - 假设前j本书都放置在最高的一层，剩余的书放置在新的一层，计算剩余的书需要的书架高度k
- 返回dp[n]作为结果
### 代码
```java
class Solution {
    public int minHeightShelves(int[][] books, int shelfWidth) {
      int n = books.length;
      int[] dp = new int[n + 1];
      Arrays.fill(dp, 1000000);
      dp[0] = 0;
      for (int i = 1; i <= n; i++) {
        int width = 0, maxHeight = 0;
        for (int j = i; j > 0; j--) {
          width += books[j - 1][0];
          if (width > shelfWidth) {
            break;
          }

          maxHeight = Math.max(maxHeight, books[j - 1][1]);
          dp[i] = Math.min(dp[i], dp[j - 1] + maxHeight);
        }
      }
      return dp[n];
    }
}
```