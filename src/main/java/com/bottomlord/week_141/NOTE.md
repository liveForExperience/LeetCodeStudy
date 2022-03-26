# [LeetCode_653_两数之和IV-输入BST](https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/)
## 解法
### 思路
dfs
- dfs中序遍历生成升序序列
- 双指针寻找target
- 找到就返回true，否则返回false
### 代码
```java
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);

        int head = 0, tail = list.size() - 1;
        while (head < tail) {
            int sum = list.get(head) + list.get(tail);
            if (sum > k) {
                tail--;
            } else if (sum < k) {
                head++;
            } else {
                return true;
            }
        }

        return false;
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }
}
```
## 解法二
### 思路
dfs
- 2层dfs
  - 第一层用于确定第一个加数
  - 第二层从根节点开始寻找k - val(第一层的node值)是否存在，如果存在就返回true
- 需要注意，因为是bst，所以不存在val * 2 = target的情况，所以需要把这种情况剔除掉
### 代码
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        return dfs(root, k, root);
    }

    private boolean dfs(TreeNode node, int target, TreeNode root) {
        if (node == null) {
            return false;
        }
        
        int val = node.val;
        if (val * 2 != target && search(root, target - val)) {
            return true;
        }
        
        return dfs(node.left, target, root) || dfs(node.right, target, root); 
    }
    
    private boolean search(TreeNode node, int target) {
        if (node == null) {
            return false;
        }
        
        int val = node.val;
        if (val == target) {
            return true;
        }
        
        return val < target ? search(node.right, target) : search(node.left, target);
    }
}
```
# [LeetCode_625_最小因式分解](https://leetcode-cn.com/problems/minimum-factorization/)
## 解法
### 思路
模拟
- 注意溢出，需要先用long声明进行计算，最后和int的极值进行比较后再判断是返回0还是强转为int
### 代码
```java
class Solution {
  public int smallestFactorization(int num) {
    long ans = 0, index = 0, ten = 1;
    while (num >= 10) {
      long cur = 0;
      for (int i = 9; i > 1; i--) {
        if (num % i == 0) {
          cur = i;
          break;
        }
      }

      if (cur == 0) {
        return 0;
      }

      ten *= index == 0 ? 1 : 10;
      ans += index == 0 ? cur : ten * cur;
      index++;
      num /= cur;
    }

    ans += index == 0 ? num : ten * 10 * num;

    return ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE ? 0 : (int) ans;
  }
}
```
# [LeetCode_2038_如果相邻两个颜色均相同则删除当前颜色](https://leetcode-cn.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color/)
## 解法
### 思路
模拟
- 分析题目可以发现，A和B的抽取数量与另一方没有关系，所以只需要计算出连续的字符长度以及连续字符的个数就可知道每一方可以抽取的数量
- 抽取的数量 = 连续长度 - 2
- 最后比较A的抽取数量是否大于B即可
### 代码
```java
class Solution {
    public boolean winnerOfGame(String colors) {
        char[] cs = colors.toCharArray();
        int aNum = 0, bNum = 0;
        for (int i = 0; i < cs.length;) {
            char c = cs[i];
            int count = 0;
            while (i < cs.length && c == cs[i]) {
                count++;
                i++;
            }
            
            if (c == 'A') {
                aNum += Math.max(count - 2, 0);
            } else {
                bNum += Math.max(count - 2, 0);
            }
        }
        
        return aNum > bNum;
    }
}
```
# [LeetCode_440_字典序的第K小数字](https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/)
## 解法
### 思路
- 字典序：可以理解为`1, 10, 100, 11, 110, 111`这样的顺序
- 观察顺序可以发现
  - 一开始是不断*10来生成新的一个数
  - 一直到无法再*10，否则就要超过n的那个数出现，例如n=187，那么就是1，10，100
  - 然后顺序就是，`1,10,100,101,102,103,...109,110,111,112,113...120,121...186,187,19`
  - 那么首先可以归纳出以1为开头的数字，就一共有1+10+88，一共99个
  - 其次可以看到
    - 如果k是大于99的，那么我不用管这99个数字的顺序，我直接k-=99，然后从2开始计算就可以了
    - 如果k是小于99的，那么如果我是从1开始计算的，我就要*10，也就是从10，这第二个数字开始计算
    - 也就是说，我通过从1开始计算，确定了这第k个数字一定是在以1为起始的99个数字里，也就是1是确定的了
    - 那我就看看第二个数字，10开头的值包不包含
      - 如果包含，那么就继续推100这第三个数
      - 如果不包含，那么说明不是10开头，那看看是不是11开头的，然后继续推
- 通过总结出的规律，那么整个算法就可以被拆分出两个部分
  - 一个部分是用来确定从哪个开头开始计算
  - 一个部分是用来确定这个开头可以生成多少有效的数字
- 然后根据生成的数字看看是不是囊括了第k个数
  - 不囊括就增大前缀开头数
  - 囊括就扩展前缀数的位数或者说缩小前缀生成数的范围
### 代码
```java
class Solution {
    public int findKthNumber(int n, int k) {
        long cur = 1;
        k--;
        while (k > 0) {
            long step = step(cur, cur + 1, n);

            if (step > k) {
                cur *= 10;
                k--;
            } else {
                cur++;
                k -= step;
            }
        }

        return (int)cur;
    }

    private long step(long cur, long next, int n) {
        long step = 0;
        while (cur <= n) {
            step += Math.min(n + 1, next) - cur;
            
            cur *= 10;
            next *= 10;
        }
        
        return step;
    }
}
```
# [LeetCode_634_寻找数组的错位排列](https://leetcode-cn.com/problems/find-the-derangement-of-an-array/)
## 解法
### 思路
- 如果将坐标a的元素进行放置，那么就有n-1种可能
- 如果此时将其放在b坐标上，那么就会有两种情况：
  - b放在a上，那么此时剩下的可能情况就是(n - 2)子问题的结果，再乘以(n - 1)种a的可能，除了a和b之外，其他都是不选择自己。
  - b不放在a上，那么剩下的可能情况就是(n - 1)子问题的结果，再乘以(n - 1)种a的可能，除了b之外，其他都是不选择自己，b是不选择a。
- 找到子问题的递推关系，就可以通过动态规划来处理
- `dp[i]`：i个元素的错排状态个数
- 状态转移方程：`dp[i] = (i - 1) * (dp [i - 1] + dp[i - 2])`
- base case：`dp[1] = 0`，`dp[2] = 1`
- 返回结果就是`dp[n]`
### 代码
```java
class Solution {
    public int findDerangement(int n) {
        if (n == 1) {
            return 0;
        }
        
        int mod = 1000000007;

        long[] dp = new long[n + 1];
        dp[1] = 0;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = (i - 1) * (dp[i - 1] + dp[i - 2]);
            dp[i] %= mod;
        }

        return (int)dp[n];
    }
}
```
## 解法二
### 思路
在解法一的基础上做状态压缩
### 代码
```java
class Solution {
    public int findDerangement(int n) {
        if (n == 1) {
            return 0;
        }

        int mod = 1000000007;
        
        long two = 0, one = 1, cur = one;
        for (int i = 3; i <= n; i++) {
            cur = ((i - 1) * (one + two)) % mod;
            two = one;
            one = cur;
        }
        
        return (int) cur;
    }
}
```
# [LeetCode_172_阶乘后的零](https://leetcode-cn.com/problems/factorial-trailing-zeroes/)
## 解法
### 思路
- 阶乘后结尾0的个数取决于阶乘因数中10的个数
- 而10又可以通过`2*5`获得，所以求得2和5两者作为因数出现的较小值，就可以获得0的个数
- 相同的数，因为2<5，所以包含5的有因数数量一定不多于2的因数数量，所以只要求出5的因数个数即可
- 参考：https://leetcode-cn.com/problems/factorial-trailing-zeroes/solution/by-ac_oier-1y6w/
### 代码
```java
class Solution {
  public int trailingZeroes(int n) {
    int count5 = 0;
    for (int i = 2; i <= n; i++) {
      int num = i;
      while (num % 5 == 0) {
        num /= 5;
        count5++;
      }
    }

    return count5;
  }
}
```
## 解法二
### 思路
- 在解法一的基础上，要找到包含5因数的个数，其实可以通过在`1-n`这个范围中找到5，25，125的倍数来确定
- 有多少个5的倍数，就说明这些数一定至少有1个5作为因数，依次类推，25就是至少有2个因数
- 所以可以通过不断计算5的倍数作为除数x，通过`n / x`来计算出这个个数
- 因为25的倍数，这些数是在之前5的倍数计算过程中出现过的，所以这个重复值要去掉，所以可以通过n不断累除5，并累加商的方式来算这个个数。
- 第一次除5，得到的是5的倍数的个数
- 然后再上一次商的基础上第二次除5，相当于在之前的5的倍数中找25的倍数，等价于n / 25
- 依次类推
### 代码
```java
class Solution {
    public int trailingZeroes(int n) {
        int ans = 0;
        while (n > 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
}
```
# [LeetCode_2206_将数组划分成相等数对](https://leetcode-cn.com/problems/divide-array-into-equal-pairs/)
## 解法
### 思路
- 桶计数，出现一次记录一次
- 遍历桶，如果个数是奇数返回false
- 遍历结束返回true
### 代码
```java
class Solution {
    public boolean divideArray(int[] nums) {
        int[] bucket = new int[501];
        for (int num : nums) {
            bucket[num]++;
        }

        for (int i = 1; i < bucket.length; i++) {
            if (bucket[i] % 2 != 0) {
                return false;
            }
        }
        
        return true;
    }
}
```
# [LeetCode_635_设计日志存储系统](https://leetcode-cn.com/problems/design-log-storage-system/)
## 解法
### 思路

### 代码
```java

```