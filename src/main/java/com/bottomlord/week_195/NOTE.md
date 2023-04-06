# [LeetCode_1053_交换一次的先前排列](https://leetcode.cn/problems/previous-permutation-with-one-swap/)
## 解法
### 思路
- 从低位开始找到第一个和前一个元素组成升序的序列
- 然后从当前位置向前，找到比当前元素小的最大元素（因为之前是降序，所以也就是尽可能的低位）
- 需要注意，在内层找到升序后，开始向前查找时候，因为题目要求的是最大的小于arr序列的数，所以如果出现[3,1,1,3]这样的序列，最高位的3应该和第一个1交换，而不能同第二个1交换，所以还需要在遍历过程中记录最大值，当出现相等的值时候，不使用后续找到的坐标
### 代码
```java
class Solution {
    public int[] prevPermOpt1(int[] arr) {
        int n = arr.length;
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                boolean find = false;
                int index = -1, maxValue = 0;
                for (int j = i + 1; j < n; j++) {
                    if (arr[i] > arr[j] && arr[j] > maxValue) {
                        find = true;
                        maxValue = arr[j];
                        index = j;
                    }
                }

                if (find) {
                    int tmp = arr[index];
                    arr[index] = arr[i];
                    arr[i] = tmp;
                    return arr;
                }
            }
        }
        return arr;
    }
}
```
# [LeetCode_1000_合并石头的最低成本](https://leetcode.cn/problems/minimum-cost-to-merge-stones/)
## 解法
### 思路
- 假设石头有7块，成本总数是sum，k为3
- 那么相当于在最后一次的合并的时候，会有3堆相加总数为sum的石头堆
- 这3堆中的第一堆可以通过如下的3种情况得到：
    - stone[0]
    - sum(stone[i])，i = [0, 2]
    - dfs(i, j, p)，i = 0，j = 4，p = 1
- 其中dfs(i,j,p)代表通过把stone[i]到stone[j]的石头合并成p堆的最小成本
- 基于这个定义，那么dfs(0, 6, 1)就是这道题的答案定义
- 通过定义可以知道，这个函数可以把上面的过程通过如下的等式表示出来：
    - dfs(0, 6, 1) = dfs(0, 6, 3) + sum(stone[i])（i = [0, 6]）
    - 而dfs(0, 6, 3)又可以通过如下的3种情况得到，它们通过表达式可以表示为：
        - dfs(0, 0, 1) + dfs(1, 6, 2)
        - dfs(0, 2, 1) + dfs(3, 6, 2)
        - dfs(0, 4, 1) + dfs(5, 6, 2)
    - 而dfs(0, 6, 3)则从如上的3种情况下的最小值中得到
- 继续看这个表达式，我们可以对表达式的一些情况做初始化，然后基于上面推演的状态转移方程来做动态规划
- 那么上面的3种情况，可以分析得到这样的情况，就是通过k-1的步长，将数组拆分成2部分，使得问题变成一个递推子问题来进行处理，这里驱动的逻辑就是找到分拆的点，这个点基于步长来获取
- 初始化的状态是：
    - dp(i，i，1) = 0，代表一块石头为一堆，不需要什么成本
- 还需要考虑特殊情况，也就是无法合并的情况：
    - 答案需要堆数从n变为1，也就是减少n-1堆，而每次合并的时候都会减少k-1堆，那么总的减少数必须是每次减少数量的倍数，否则就无法合并
    - 也就是说，(n - 1) % (k - 1) == 0
- 在计算过程中有提到：dfs(0, 6, 1) = dfs(0, 6, 3) + sum(stone[i])（i = [0, 6]），这个sum可以通过前缀和来简化，所以可以提前准备一个前缀和
### 代码
```java
class Solution {

    private int[][][] memo;
    private int[] sums;
    private int k;

    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) != 0) {
            return -1;
        }

        this.memo = new int[n][n][k + 1];
        this.sums = new int[n + 1];
        this.k = k;
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + stones[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        return dfs(0, n - 1, 1);
    }

    private int dfs(int i, int j, int p) {
        if (memo[i][j][p] != -1) {
            return memo[i][j][p];
        }

        if (p == 1) {
            return memo[i][j][p] = i == j ? 0 : dfs(i, j, k) + sums[j + 1] - sums[i];
        }

        int ans = Integer.MAX_VALUE;
        for (int index = i; index < j; index += k - 1) {
            ans = Math.min(ans, dfs(i, index, 1) + dfs(index + 1, j, p - 1));
        }
        return memo[i][j][p] = ans;
    }
}
```
# [LeetCode_2600_K件物品的最大和](https://leetcode.cn/problems/k-items-with-the-maximum-sum/)
## 解法
### 思路
模拟：
- 依次根据1，0，-1所对应的变量值，也就是其个数，对k做累减，直到k为0为止
- 并根据不同变量所对应的值，进行累加
- 当k为0时，返回累加值即可
### 代码
```java
class Solution {
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        int sum = 0;
        sum += numOnes = Math.min(k, numOnes);
        k -= numOnes;
        k -= Math.min(k, numZeros);
        return sum -= numNegOnes = Math.min(k, numNegOnes);
    }
}
```
# [LeetCode_2605_从两个数字数组里生成最小数字](https://leetcode.cn/problems/form-smallest-number-from-two-digit-arrays/)
## 解法
### 思路
模拟
- 从2个数组中分别找到最小值
- 然后从2个数字中选择较小值为10位，较大值为个位
- 还需要当心2个数组中有相同数字的情况，如果出现这种情况，就返回相同数中最小的那个即可
- 返回这个结果
### 代码
```java
class Solution {
    public int minNumber(int[] nums1, int[] nums2) {
        boolean[] memo = new boolean[10];

        int a = 10, b = 10, c = 10;
        for (int num : nums1) {
            memo[num] = true;
            a = Math.min(num, a);
        }

        for (int num : nums2) {
            if (memo[num]) {
                c = Math.min(c, num);
            }
            b = Math.min(num, b);
        }

        if (c != 10) {
            return c;
        }
        
        return Math.min(a, b) * 10 + Math.max(a, b);
    }
}
```
# [LeetCode_2609_最长平衡字符串](https://leetcode.cn/problems/find-the-longest-balanced-substring-of-a-binary-string/)
## 解法
### 思路
- 外层循环遍历字符串
- 内层分成2次循环
  - 先遍历为0的字符，记录个数
  - 再遍历为1的字符，记录个数
- 取0和1个数的最小值，乘以2以后作为当前平衡字符串的长度，再和之前记录的平衡字符串长度进行比较，取较大值
- 遍历结束，返回暂存最大值作为结果
### 代码
```java
class Solution {
    public int findTheLongestBalancedSubstring(String s) {
        int zero = 0, one = 0, ans = 0, index = 0, n = s.length();
        while (index < n) {
            while (index < n && s.charAt(index) == '0') {
                zero++;
                index++;
            }
            
            while (index < n && s.charAt(index) == '1') {
                one++;
                index++;
            }
            
            ans = Math.max(ans, Math.min(zero, one) * 2);
            
            zero = 0;
            one = 0;
        }
        
        return ans;
    }
}
```
# [LeetCode_1017_负二进制转换](https://leetcode.cn/problems/convert-to-base-2/)
## 解法
### 思路
- 根据短除法依次从低位开始计算出二进制每一位的值
  - 如果是偶数位，那就不处理
  - 如果是奇数位，那么就需要考虑进位的问题
    - 以2的3次方距离，二进制情况下是8，但是负二进制下就变成了-8，如果要获得8，那么就需要用在更高位得到1，这样就变成了(2的4次方)减去(2的3次方)，即16-8，这样就需要进位
- 通过暂存进位值生成二进制数
### 代码
```java
class Solution {
  public String baseNeg2(int n) {
    if (n == 0) {
      return "0";
    }

    StringBuilder sb = new StringBuilder();
    int carry = 0;
    boolean even = true;
    while (n > 0 || carry != 0) {
      if (n > 0) {
        int cur = (n % 2 + carry) % 2;
        sb.insert(0, cur);
        carry = (n % 2 + carry) / 2;
        if (!even && cur == 1) {
          carry++;
        }
        n /= 2;
      } else {
        carry--;
        sb.insert(0, 1);

        if (!even) {
          carry++;
        }
      }

      even = !even;
    }

    return sb.toString();
  }
}
```