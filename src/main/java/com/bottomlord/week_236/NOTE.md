# [LeetCode_2744_最大字符串配对数目](https://leetcode.cn/problems/find-maximum-number-of-string-pairs)
## 解法
### 思路
- 思考过程：
  - 使用mao存储`words`字符串与坐标间的关系，因为不会重复，所以无需记录个数
  - 使用布尔数组存储成对的字符串，避免重复统计
  - 通过一次循环在`map`中查找倒序的字符串是否存在，存在就统计并记录在布尔数组中
- 算法过程：
  - 初始化`map`，存储`words`中的所有字符串及对应坐标
  - 初始化布尔数组`memo`，长度与`words`一致
  - 初始化一个变量`cnt`，用于记录出现成对的个数
  - 遍历`words`数组，通过倒序生成字符串后，到`map`查找是否存在，如果存在就记录坐标，需要个过滤掉坐标相同的情况
  - 在遍历过程中如果发现之前记录过，就跳过
  - 遍历结束后，返回`cnt`作为答案
### 代码
```java
class Solution {
    public int maximumNumberOfStringPairs(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        boolean[] memo = new boolean[words.length];
        int cnt = 0;
        for (int i = 0; i < words.length; i++) {
            if (memo[i]) {
                continue;
            }
            
            String word = words[i];
            StringBuilder sb = new StringBuilder();
            for (int j = word.length() - 1; j >= 0; j--) {
                sb.append(word.charAt(j));
            }
            
            if (map.containsKey(sb.toString()) && map.get(sb.toString()) != i) {
                memo[i] = true;
                memo[map.get(sb.toString())] = true;
                cnt++;
            }
        }
        
        return cnt;
    }
}
```
# [LeetCode_2719_统计整数数目](https://leetcode.cn/problems/count-of-integers)
## 解法
### 思路
- 思考过程：
  - 根据题目发现这是一道较为明显的数位dp的问题，该问题需要在一定范围内查找符合条件的数字的数量，范围可能非常大，需要通过数位的方式来计算
  - 题目要求`[num1,num2]`范围内数位值总和在`[min_sum,max_sum]`范围内的有效整数个数。假设`fun(1, num)`能够求得`[1,num]`范围内的有效整数个数，那么`fun(1, num2) - fun(1, num1 - 1)`就是能够得到题目答案的公式
  - 于是我们可以聚焦在`fun(1,num)`的构建上
  - 数位dp问题，可以通过基于数位的dfs来记录和推导
  - 然后通过一个二维的数组来作为记事本，借此模拟dp推导
    - `dp[i][j]`：第`i`位到最后坐标的数位总和为j的整数个数
  - 这里还有2个难点
    - 题目的数字`num1`和`num2`是字符串，要计算`num1 - 1`需要做特殊处理：将从最低位开始的第一个非0字符减1，再将该字符之前的所有0都变为9
    - 在dfs推导过程中，每一个数位并不是任何情况都可以选择`0-9`的，特殊的那个情况就是，当所有数位上的数都是贴着上界值，也就是这边推导的`num1`或`num2`时，就需要基于`num1`或`num2`的当前数位作为最大值来遍历，所以dfs的时候需要传递一个布尔值作为判断是否是紧贴上限的依据
- 算法过程：
  - 通过思考过程中的方法将2个`num`转换为对应的字符串值
  - 初始化二维dp数组，长度对应`23`和`230`，也即最大的字符串长度和所有数位的最大值大小，初始默认值为-1
  - 定义一个dfs函数，入参为
    - 当前坐标`i`
    - 累加值`sum`
    - 是否紧贴上界的依据`flag`
  - dfs的退出条件
    - 当前坐标到达边界，则返回1，代表当前这次搜索完成，可以累加1个有效的整数
    - 当前`i`和`sum`值对应的坐标，在dp数组中有值且不是-1，flag也不& 是true时，直接返回dp值
      - 之所以必须不是true，是因为，如果是true，则代表当前数字是紧贴着上界的，那么推到就要与数字相关，每个数位的值都要基于前一个数位的值来判断，不再是独立的子问题，所以不能是true的情况
  - 基于是否`flag`为true，判断当前递归层遍历的上界元素值大小，然后基于上界对当前数位可能的数字进行遍历和递归
    - 递归参数变化：
      - i = i + 1
      - sum = sum + cur（当前数位的元素）
      - flag = flag && cur == up（当前的元素取值上界）
    - 递归返回的值进行累加
  - 如果当前层的flag不为true，则可以对`dp`数组进行更新，更新为累计值
  - 最后通过对2个数的函数返回值相减，得到的差作为结果返回
  - 需要注意
    - dfs初始化的时候flag为true，因为此时最高位一定不能大于`num`
    - 必须从最高位开始向最低位遍历，且高位对应的坐标也必须是相对的大值，个位则为0。因为2个`num`公用一个`dp`数组，如果从不从高位坐标开始向低位0移动，那么就会导致dp坐标i对应的语义出现偏差，从而使dp数组的值无法复用。i的含义是还剩i位到0的数位个数。
### 代码
```java
class Solution {
    private final int mod = 1000000007;
    private int minSum, maxSum;
    private final int[][] dp = new int[23][401];
    private String num;
    public int count(String num1, String num2, int min_sum, int max_sum) {
        for (int[] nums : dp) {
            Arrays.fill(nums, -1);
        }
        this.minSum = min_sum;
        this.maxSum = max_sum;
        return (doCount(num2) - doCount(subOne(num1)) + mod) % mod;
    }

    private int doCount(String num) {
        this.num = new StringBuilder(num).reverse().toString();;
        return dfs(num.length() - 1, 0, true);
    }

    private String subOne(String num) {
        char[] cs = num.toCharArray();
        int i;
        for (i = cs.length - 1; i >= 0; i--) {
            if (cs[i] == '0') {
                continue;
            }

            cs[i]--;
            break;
        }

        for (int j = i + 1; j < cs.length; j++) {
            cs[j] = '9';
        }

        return new String(cs);
    }

    private int dfs(int i, int sum, boolean flag) {
        if (sum > maxSum) {
            return 0;
        }

        if (i == -1) {
            return sum >= minSum ? 1 : 0;
        }

        if (!flag && dp[i][sum] != -1) {
            return dp[i][sum];
        }

        int up = flag ? (num.charAt(i) - '0') : 9, cnt = 0;
        for (int j = 0; j <= up; j++) {
            cnt = (cnt + dfs(i - 1, sum + j, flag && j == up)) % mod;
        }

        if (!flag) {
            dp[i][sum] = cnt;
        }

        return cnt;
    }
}
```