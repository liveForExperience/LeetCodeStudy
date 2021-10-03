# [LeetCode_639_解码方法II](https://leetcode-cn.com/problems/decode-ways-ii/)
## 解法
### 思路
动态规划
- 在[91题](https://leetcode-cn.com/problems/decode-ways/)的基础上，状态转移时候，需要额外考虑为星的情况
- 状态转移方程：
  - 只考虑1个数字
    - 如果当前是星号，那么就有9种可能：dp[i] = 9 * dp[i - 1]
    - 如果当前是0：dp[i] = 0
    - 如果当前是[1,9]：dp[i] = dp[i - 1]
  - 考虑2个数字
    - 如果有2个星，那么就有[11,19]和[21,26]共15种可能：dp[i] = 15 * dp[i - 2]
    - 如果当前是星
      - 如果s[i - 1]是1：dp[i] = 9 * dp[i - 2]
      - 如果s[i - 1]是2：dp[i] = 6 * dp[i - 2]
    - 如果前一个是星
      - 如果s[i]是[7,9]：dp[i] = dp[i - 2]
      - 如果s[i]是[0,6]：dp[i] = 2 * dp[i - 2]
    - 如果都不是星
      - 那么s[i -  1]不是0，且组成的数字在10到26之间：dp[i] = dp[i - 2]
- 计算过程中需要取余1000000007，而且dp数组需要用long类型
### 代码
```java
class Solution {
    private int mod = 1000000007;
    
    public int numDecodings(String s) {
        int n = s.length();
        long[] dp = new long[n + 1];
        s = " " + s;
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i) == '*') {
                dp[i] = 9 * dp[i - 1];
            } else if (s.charAt(i) != '0') {
                dp[i] = dp[i - 1];
            }

            dp[i] %= mod;

            if (s.charAt(i - 1) == s.charAt(i) && s.charAt(i) == '*') {
                dp[i] += 15 * dp[i - 2];
            } else if (s.charAt(i) == '*') {
                if (s.charAt(i - 1) == '1') {
                    dp[i] += 9 * dp[i - 2];
                } else if (s.charAt(i - 1) == '2') {
                    dp[i] += 6 * dp[i - 2];
                }
            } else if (s.charAt(i - 1) == '*') {
                int a = s.charAt(i) - '0';
                if (a >= 7 && a <= 9) {
                    dp[i] += dp[i - 2];
                } else if (a >= 0 && a <= 6) {
                    dp[i] += 2 * dp[i - 2];
                }
            } else {
                int a = s.charAt(i) - '0', b = (s.charAt(i - 1) - '0') * 10 + a;
                if (b >= 10 && b <= 26) {
                    dp[i] += dp[i - 2];
                }
            }

            dp[i] %= mod;
        }

        return (int)dp[n];
    }
}
```
# [LeetCode_91_解法方法](https://leetcode-cn.com/problems/decode-ways/)
## 解法
### 思路
动态规划：
- dp[i]：代表长度为i的字符串能够组成的编码组合个数
- 状态转移方程：
  - 如果当前数字为[1,9]，那么他能单独组成一个字母，所以他的编码个数就和dp[i - 1]一样
  - 如果当前数组与之前的数组能够组成[10, 26]，那么他们2个就能组成一个字母，从而他们的编码个数就和dp[i - 2]一样
  - 所以如果当前这个数字同时满足如上2种情况，那么当前能够组成的编码个数就是dp[i - 1] + dp[i - 2]
- base case：
  - 为了简便计算，可以在字符串之前拼接一个空字符，然后设置这个位置的dp值为1
- 最终返回dp[n]
### 代码
```java
class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        s = " " + s;
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int a = s.charAt(i) - '0', b = (s.charAt(i - 1) - '0') * 10 + a;
            if (a >= 1 && a <= 9) {
                dp[i] = dp[i - 1];
            }

            if (b >= 10 && b <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}
```
# [LeetCode_437_路径总和III](https://leetcode-cn.com/problems/path-sum-iii/)
## 解法
### 思路
嵌套dfs
- 第一层dfs确定计算路径和的起始节点
- 第二层dfs用来计算路径和
### 代码
```java
class Solution {
    private int count = 0;
    public int pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return count;
    }

    private void dfs(TreeNode node, int sum) {
        if (node == null) {
            return;
        }
        
        if (node.val == sum) {
            count++;
        }
        
        doDfs(node.left, node.val, sum);
        doDfs(node.right, node.val, sum);
        
        dfs(node.left, sum);
        dfs(node.right, sum);
    }
    
    private void doDfs(TreeNode node, int val, int sum) {
        if (node == null) {
            return;
        }
        
        val += node.val;
        
        if (val == sum) {
            count++;
        }
        
        doDfs(node.left, val, sum);
        doDfs(node.right, val, sum);
    }
}
```
## 解法
### 思路
dfs+前缀和
- 一层dfs
- 初始化一个map，用于存储当前遍历路径上的所有前缀和，默认key为0，value为1，这个默认值用于从根节点开始累加的累加值正好等于sum的情况，方便计数
- dfs搜索过程中，查找当前前缀和和已有前缀和之间是否存在差值为sum的情况，有的话，就累加这个值，然后继续向下搜索
- 在搜索过程中还需要做回溯时候的状态清除，因为求的是路径上的和，所以过去搜索到的其他路径上的和需要被清理掉
### 代码
```java
class Solution {
  public int pathSum(TreeNode root, int targetSum) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    return dfs(root, targetSum, 0, map);
  }

  private int dfs(TreeNode node, int sum, int curSum, Map<Integer, Integer> map) {
    if (node == null) {
      return 0;
    }

    curSum += node.val;

    int count = map.getOrDefault(curSum - sum, 0);
    map.put(curSum, map.getOrDefault(curSum, 0) + 1);
    count += dfs(node.left, sum, curSum, map) + dfs(node.right, sum, curSum, map);
    map.put(curSum, map.get(curSum) - 1);
    return count;
  }
}
```
# [LeetCode_1732_找到最高海拔](https://leetcode-cn.com/problems/find-the-highest-altitude/)
## 解法
### 思路
- 遍历数组并累加元素
- 累加值不断跟最大值比较，暂存较大值
- 遍历结束返回最大值
### 代码
```java
class Solution {
    public int largestAltitude(int[] gain) {
        int cur = 0, max = 0;
        for (int num : gain) {
            cur += num;
            max = Math.max(cur, max);
        }
        
        return max;
    }
}
```
# [LeetCode_1742_盒子中小球的最大数量](https://leetcode-cn.com/problems/maximum-number-of-balls-in-a-box/)
## 解法
### 思路
- 从low到high遍历数字
- 根据数字计算每一位上数字的和，得到盒子的编号
- map存储编号和球个数的映射关系
### 代码
```java
class Solution {
    public int countBalls(int lowLimit, int highLimit) {
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = lowLimit; i <= highLimit; i++) {
            int n = i, sum = 0;
            while (n > 0) {
                int num = n % 10;
                sum += num;
                n /= 10;
            }
            
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            max = Math.max(max, map.get(sum));
        }
        
        return max;
    }
}
```
# [LeetCode_517_超级洗衣机](https://leetcode-cn.com/problems/super-washing-machines/)
## 解法
### 思路
- 首先确定-1的情况，也就是衣服总和无法被洗衣机数量总和整除的情况
- 其次，因为可以任意选取任意数量的洗衣机，所以最小移动次数就应该是所有机器的最小移动次数中的最大值
- 那么移动的次数其实就可以这么理解：
  - 当前洗衣机及左边所有的衣服的总和与平均时所有衣服的总和的差值，就是经过当前洗衣机的衣服数量
  - 另外还有一个是当前洗衣机本身要生成平均值的差值
  - 如上两个差值之间的最大值，就是经过当前洗衣机的最小值，也就是至少需要的操作数
  - 遍历所有洗衣机，计算这些最小值中的最大值，就是答案要求的最小值
### 代码
```java
class Solution {
    public int findMinMoves(int[] machines) {
        int sum = 0, n = machines.length;
        for (int machine : machines) {
            sum += machine;
        }

        if (sum % n != 0) {
            return -1;
        }

        int average = sum / n;
        for (int i = 0; i < n; i++) {
            machines[i] = machines[i] - average;
        }

        int ans = 0, leftSum = 0;
        for (int machine : machines) {
            leftSum += machine;
            int cur = Math.max(Math.abs(leftSum), machine);
            ans = Math.max(cur, ans);
        }
        
        return ans;
    }
}
```
# [LeetCode_223_矩形面积](https://leetcode-cn.com/problems/rectangle-area/)
## 解法
### 思路
- 如果两个矩形的某一个矩形的一个横或一个竖在另一个矩形的两个边之间，且同时存在横和竖都有这种情况的场景，则表示两个矩形相交
- 先计算出两个矩形的面积并相加
- 然后判断是否有相交，如果有，就算出重合面积，然后减去重合面积即可
- 重合面积为，左边取最大值，右边取最小值，上边取最小值，下标取最大值
### 代码
```java
class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int x1 = Math.max(ax1, bx1),
            x2 = Math.min(ax2, bx2),
            y1 = Math.max(ay1, by1),
            y2 = Math.min(ay2, by2);

        boolean isCross = isCross(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2);
        return area(ax1, ay1, ax2, ay2) + area(bx1, by1, bx2, by2) - (isCross ? area(x1, y1, x2, y2) : 0);
    }

    private boolean isCross(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        return (ax1 >= bx1 && ax1 <= bx2 || ax2 >= bx1 && ax2 <= bx2 || bx1 >= ax1 && bx1 <= ax2 || bx2 >= ax1 && bx2 <= ax2) &&
                (ay1 >= by1 && ay1 <= by2 || ay2 >= by1 && ay2 <= by2 || by1 >= ay1 && by1 <= ay2 || by2 >= ay1 && by2 <= ay2);
    }

    private int area(int x1, int y1, int x2, int y2) {
        return Math.abs((x1 - x2) * (y1 - y2));
    }
}
```
# [LeetCode_1436_旅行终点站](https://leetcode-cn.com/problems/destination-city/)
## 解法
### 思路
- 两个set分别存储始发地和目的地
- 遍历list后分别存储始发地和目的地
- 遍历目的地set，如果在始发地里没有则返回该城市
### 代码
```java
class Solution {
  public String destCity(List<List<String>> paths) {
    Set<String> departs = new HashSet<>(),
            dests = new HashSet<>();

    for (List<String> path : paths) {
      departs.add(path.get(0));
      dests.add(path.get(1));
    }

    for (String dest : dests) {
      if (!departs.contains(dest)) {
        return dest;
      }
    }

    return null;
  }
}
```
# [LeetCode_166_分数到小数](https://leetcode-cn.com/problems/fraction-to-recurring-decimal/)
## 解法
### 思路
- 结果可以分为3种情况
  - 整数
  - 有限小数
  - 无线循环小数
- 计算是否整除，如果整除直接返回
- 如果不能整除，则开始处理是小数的情况
  - 判断被除数和除数是否有且只有一个为负数，如果是的话先添加负号
  - 计算整数部分
  - 初始化一个map，key为小数部分的被除数，value为起始的index，这样存储的原因是，如果出现无线循环小数，key可以用来判断是否有循环，value用来确定左括号的位置
  - 每次取余后，判断余数是否为0或者在map中是否已存在，如果有任意情况符合，则退出求小数部分的循环
  - 最后判断余数是否为0来确定退出循环时候是有限的情况还是无限的情况
  - 如果是无限的情况，就根据map中存储的value值插入左括号，在最后插入右括号
### 代码
```java
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        long num = numerator, de = denominator;
        if (num % de == 0) {
            return String.valueOf(num / de);
        }

        StringBuilder sb = new StringBuilder();
        if (num < 0 ^ de < 0) {
            sb.append("-");
        }

        num = Math.abs(num);
        de = Math.abs(de);

        sb.append(num / de).append(".");

        StringBuilder sb1 = new StringBuilder();
        long reminder = num % de;
        int index = 0;
        Map<Long, Integer> mapping = new HashMap<>();

        while (reminder != 0 && !mapping.containsKey(reminder)) {
            mapping.put(reminder, index);
            reminder *= 10;
            sb1.append(reminder / de);
            reminder %= de;
            index++;
        }
        
        if (reminder != 0) {
            sb1.insert(mapping.get(reminder), "(");
            sb1.append(")");
        }
        
        return sb.append(sb1).toString();
    }
}
```
# [LeetCode_405_数字转换为十六进制数](https://leetcode-cn.com/submissions/detail/25953989/)
## 解法
### 思路
- 十六进制对应4位的二进制
- 所以可以每四位生成一个字符串
- 初始化0到25对应的16进制值
- 如果num为0，返回0
- 否则开始循环，直到num为0为止
- 每次将最低4位转换为字符串，然后右移4位，处理接下来的4位直到循环结束
### 代码
```java
class Solution {
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        
        char[] dict = new char[] {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.insert(0, dict[num & 15]);
            num >>>= 4;
        }
        
        return sb.toString();
    }
}
```
# [LeetCode_1748_唯一元素的和](https://leetcode-cn.com/problems/sum-of-unique-elements/)
## 解法
### 思路
桶计数之后，遍历值为1的桶，对坐标进行累加，最后返回
### 代码
```java
class Solution {
    public int sumOfUnique(int[] nums) {
        int[] bucket = new int[101];
        for (int num : nums) {
            bucket[num]++;
        }

        int sum = 0;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == 1) {
                sum += i;
            }
        }
        
        return sum;
    }
}
```
# [LeetCode_1752_检查数组是否经旋转和轮转得到](https://leetcode-cn.com/problems/check-if-array-is-sorted-and-rotated/)
## 解法
### 思路
- 找到数组中最小值及坐标
- 从该坐标开始循环直到再次到最小值位置，如果一直是升序就是true，否则就是false
### 代码
```java
class Solution {
    public boolean check(int[] nums) {
        int n = nums.length, min = Integer.MAX_VALUE, index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
                index = i;
            }
        }
        
        int pre = min;
        for (int i = (index + 1) % n; i != index; i = (i + 1) % n) {
            if (nums[i] < pre && (nums[i] != min || nums[(i + 1) % n] != min)) {
                return false;
            }
            
            pre = nums[i];
        }
        
        return true;
    }
}
```