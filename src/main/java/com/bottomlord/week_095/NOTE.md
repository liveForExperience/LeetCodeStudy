# [LeetCode_7_整数反转](https://leetcode-cn.com/problems/reverse-integer/)
## 解法
### 思路
- 因为题目不允许用long，所以在判断int是否溢出的时候，就需要提前做判断，也就是在做进位的时候就判断是否已经超过的最大值/10或者小于了最小值*10
- 计算过程就是原数不断/10，缩小的过程中驱动获取每一个低位的数字，将这个数字放在反转后的数字的头部，过程就是rev * 10 + digit
- 负数取模还是负数，所以不用处理负数的情况
### 代码
```java
class Solution {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }

            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }

        return rev;
    }
}
```
# [LeetCode_562_矩阵中最长的连续1线段](https://leetcode-cn.com/problems/longest-line-of-consecutive-one-in-matrix/)
## 解法
### 思路
dfs
### 代码
```java
class Solution {
    public int longestLine(int[][] mat) {
        int ans = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    ans = Math.max(ans, dfs(mat, i, j, 1, 0));
                    ans = Math.max(ans, dfs(mat, i, j, 0, 1));
                    ans = Math.max(ans, dfs(mat, i, j, 1, 1));
                    ans = Math.max(ans, dfs(mat, i, j, 1, -1));
                }
            }
        }
        return ans;
    }

    private int dfs(int[][] mat, int r, int c, int dx, int dy) {
        if (outOfBound(mat, r, c)) {
            return 0;
        }

        return dfs(mat, r + dx, c + dy, dx, dy) + 1;
    }

    private boolean outOfBound(int[][] mat, int r, int c) {
        int row = mat.length, col = mat[0].length;
        return r < 0 || r >= row || c < 0 || c >= col || mat[r][c] == 0;
    }
}
```
# [LeetCode_1473_粉刷房子III](https://leetcode-cn.com/problems/paint-house-iii/submissions/)
## 失败解法
### 原因
超时
### 思路
dfs+剪枝
### 代码
```java
class Solution {
    private int m, target, minCost;
    private int[] houses;
    private int[][] costs;

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        this.houses = houses;
        this.costs = cost;
        this.m = m;
        this.target = target;
        this.minCost = Integer.MAX_VALUE;

        dfs(0, -1, 0, 0);
        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }

    private void dfs(int index, int preColorIndex, int sumCost, int blockCount) {
        if (index > m || sumCost > minCost || blockCount > target) {
            return;
        }

        if (index == m) {
            if (target == blockCount) {
                minCost = sumCost;
            }
            return;
        }

        if (houses[index] != 0) {
            dfs(index + 1, houses[index] - 1, sumCost, preColorIndex == -1 ? 1 : preColorIndex + 1 == houses[index] ? blockCount : blockCount + 1);
            return;
        }
        
        int[] cost = costs[index];
        for (int i = 0; i < cost.length; i++) {
            if (i == preColorIndex) {
                dfs(index + 1, i, sumCost + cost[i], blockCount);
            } else {
                dfs(index + 1, i, sumCost + cost[i], blockCount + 1);
            }
        }
    }
}
```
## 解法二
### 思路
- 根据解法一的逻辑可以找到状态转移的方程：
    - 如果当前房子已经涂了颜色`house[index] != 0`：
        - 如果当前遍历的颜色和已经涂的颜色不一致，就不要处理，这种情况不符合要求，直接设置成最大值
        - 如果颜色一致，那么就判断一下当前坐标：
            - 如果是第1幢房子，那就是0
            - 如果不是第1幢房子：
                - 如果和前1幢房子的颜色相同，那就和前1幢房子需要的cost相同
                - 如果和前1幢房子的颜色不相同，那就需要用当前1幢房子状态的cost与前一种状态的cost作比较，取较小值
    - 如果当前房子没有涂颜色
        - 那么就要判断当前要涂的颜色和前1幢房子的颜色是否相等：
            - 如果相等：那么就相当于在前一幢房子且街区相同情况的cost基础上再加上当前的cost
            - 如果不相等：那么就相当于再前一幢房子且街区数-1的情况的cost基础上，加上当前的cost
动态规划：
- `dp[i][j][k]`：[0,i]区间的所有房子都已粉刷，且最后一个房子粉刷为j颜色，需要的最小花费
- 状态转移方程：参照上面总结的前一种解法的逻辑
- 初始化，将所有状态的cost都设置成int最大值
- 结果，遍历所有dp[m - 1][j][k - 1]的状态，找到最小值
### 代码
```java
class Solution {
private static final int MAX = Integer.MAX_VALUE / 2;
    
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        for (int i = 0; i < houses.length; i++) {
            houses[i]--;
        }
        int[][][] dp = new int[m][n][target];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], MAX);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (houses[i] != -1 && houses[i] != j) {
                    continue;
                }

                for (int k = 0; k <  target; k++) {
                    for (int j1 = 0; j1 < n; j1++) {
                        if (j == j1) {
                            if (i == 0) {
                                if (k == 0) {
                                    if (houses[i] == -1) {
                                        dp[i][j][k] = cost[i][j];
                                    } else {
                                        dp[i][j][k] = 0;
                                    }
                                }
                            } else {
                                if (houses[i] == -1) {
                                    dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j][k] + cost[i][j]);
                                } else {
                                    dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j][k]);
                                }
                            }
                        } else {
                            if (i > 0 && k > 0) {
                                if (houses[i] == -1) {
                                    dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j1][k - 1] + cost[i][j]);
                                } else {
                                    dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j1][k - 1]);
                                }
                            }
                        }
                    }
                }
            }
        }

        int ans = MAX;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, dp[m - 1][i][target - 1]);
        }
        return ans == MAX ? -1 : ans;
    }
}
```
# [LeetCode_740_删除并获得点数](https://leetcode-cn.com/problems/delete-and-earn/)
## 解法
### 思路
- 如果找到一个数值，就应该持续的累加相同的数，这样就能最小化被删除的不能几点数的数子，就可以做到最大化。所以可以通过一个数组将某一个数的点数进行累加并存储
- 然后使用动态规划来计算
    - dp[i]：0到i的范围内，能够获得的最大点数
    - 状态转移方程：`dp[i] = max(dp[i - 2] + sums[i], dp[i-1])`
    - 因为状态转移的时候只考虑前后3个数的关系，所以，可以使用2个变量来记录状态转移的过程，这样就省下了空间
    - 返回`dp[max]`，max是nums中的最大值
### 代码
```java
class Solution {
    public int deleteAndEarn(int[] nums) {
        int max = Arrays.stream(nums).max().orElse(0);
        int[] sums = new int[max + 1];
        for (int num : nums) {
            sums[num] += num;
        }
        
        int one = sums[0], two = sums[1];
        for (int i = 2; i < sums.length; i++) {
            int tmp = two;
            two = Math.max(one + sums[i], two);
            one = tmp;
        }
        
        return two;
    }
}
```
# [LeetCode_1720_解码异或后的数组](https://leetcode-cn.com/problems/decode-xored-array/)
## 解法
### 思路
- 根据encoded生成结果数组nums
- 初始化`nums[0] = first`
- 遍历nums数组，从给的first元素开始，依次和encode的对应元素进行异或，还原出原始元素
- 遍历结束后返回
### 代码
```java
class Solution {
    public int[] decode(int[] encoded, int first) {
        int len = encoded.length;
        int[] nums = new int[len + 1];
        nums[0] = first;
        for (int i = 1; i < len + 1; i++) {
            nums[i] = encoded[i - 1] ^ nums[i - 1];
        }
        return nums;
    }
}
```
# [LeetCode_604_迭代压缩字符串](https://leetcode-cn.com/problems/design-compressed-string-iterator/)
## 失败解法
### 原因
超时
### 思路
- 初始化时候解压字符串
- 初始化一个指针用于做next和hasNext操作
### 代码
```java
class StringIterator {
    private char[] cs;
    private int index;
    public StringIterator(String compressedString) {
        char[] ccs = compressedString.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ccs.length;) {
            char c = ccs[i++];
            StringBuilder numSb = new StringBuilder();
            for (; i < ccs.length; i++) {
                if (ccs[i] > '9' || ccs[i] < '0') {
                    break;
                }

                numSb.append(ccs[i]);
            }

            for (int j = 0; j < Integer.parseInt(numSb.toString()); j++) {
                sb.append(c);
            }
        }

        cs = sb.toString().toCharArray();

        index = 0;
    }

    public char next() {
        return index >= cs.length ? ' ' : cs[index++];
    }

    public boolean hasNext() {
        return index < cs.length;
    }
}
```
## 解法
### 思路
- 初始化2个数组，1个用于保存字符，一个用于保存字符剩余的数字，他们的坐标互相对应
- 初始化一个坐标，用于记录当前处理到哪个字符
### 代码
```java
class StringIterator {
    private List<Character> cs;
    private List<Integer> counts;
    private int index;
    public StringIterator(String compressedString) {
        cs = new ArrayList<>();
        counts = new ArrayList<>();
        index = 0;
        
        for (int i = 0; i < compressedString.length();) {
            char c = compressedString.charAt(i++);
            StringBuilder sb = new StringBuilder();
            while (i < compressedString.length()) {
                char c1 = compressedString.charAt(i);
                if (c1 < '0' || c1 > '9') {
                    break;
                }
                i++;
                sb.append(c1);
            }
            
            cs.add(c);
            counts.add(Integer.parseInt(sb.toString()));
        }
    }

    public char next() {
        if (index >= cs.size()) {
            return ' ';
        }
        char c = cs.get(index);
        counts.set(index, counts.get(index) - 1);
        if (counts.get(index) == 0) {
            index++;
        }
        
        return c;
    }

    public boolean hasNext() {
        return index < cs.size();
    }
}
```
## 解法二
### 思路
- 使用3个临时变量代替解法一的2个list
- 一个int临时变量作为原字符串的指针游标
- 一个int临时变量作为当前字符的剩余个数
- 一个char临时变量用于保存当前使用到的字符
- 无需再做初始化操作，只需要用一个str指针指向原字符串
- 在next的时候根据num是否为0来判断是否要解析新的字符及其出现的次数
### 代码
```java
class StringIterator {
    private String str;
    private int index, num;
    Character c;
    public StringIterator(String compressedString) {
        str = compressedString;
    }

    public char next() {
        if (!hasNext()) {
            return ' ';
        }
        
        if (num == 0) {
            c = str.charAt(index++);
            while (index < str.length()) {
                char numc = str.charAt(index);
                if (numc < '0' || numc > '9') {
                    index--;
                    break;
                }
                
                num = num * 10 + (numc - '0');
                index++;
            }
        }
        
        num--;
        char ans = c;
        if (num == 0) {
            c = null;
            index++;
        }
        
        return ans;
    }

    public boolean hasNext() {
        return index < str.length() || num != 0;
    }
}
```
# [LeetCode_716_最大栈](https://leetcode-cn.com/problems/max-stack/)
## 解法
### 思路
两个栈：
- 一个栈用于支持栈的基本操作
- 一个栈用于处理最大值
### 代码
```java
class MaxStack {
    private final Stack<Integer> basicStack, maxStack;
    public MaxStack() {
        basicStack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(int x) {
        basicStack.push(x);
        maxStack.push(maxStack.isEmpty() ? x : (x > maxStack.peek() ? x : maxStack.peek()));
    }

    public int pop() {
        maxStack.pop();
        return basicStack.pop();
    }

    public int top() {
        return basicStack.peek();
    }

    public int peekMax() {
        return maxStack.peek();
    }

    public int popMax() {
        Stack<Integer> tmp = new Stack<>();
        int x = maxStack.peek();
        while (basicStack.peek() != x) {
            tmp.push(pop());
        }

        pop();

        while (!tmp.isEmpty()) {
            push(tmp.pop());
        }

        return x;
    }
}
```
# [LeetCode_1486_数组异或操作](https://leetcode-cn.com/problems/xor-operation-in-an-array/)
## 解法
### 思路
模拟计算
### 代码
```java
class Solution {
    public int xorOperation(int n, int start) {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans ^= start + i * 2;
        }
        return ans;
    }
}
```
## 解法二
### 思路
[数学解法](https://leetcode-cn.com/problems/xor-operation-in-an-array/solution/xiao-ming-zuo-shu-xue-by-xiaohu9527-0pu7/)
### 代码
```java
class Solution {
    public int xorOperation(int n, int start) {
        int e = n & start & 1, s = start >> 1;
        int xor = sumXor(s - 1) ^ sumXor(s + n - 1);
        return xor << 1 | e;
    }

    private int sumXor(int x) {
        if (x % 4 == 0) {
            return x;
        }
        if (x % 4 == 1) {
            return 1;
        }
        if (x % 4 == 2) {
            return x + 1;
        }
        return 0;
    }
}
```
# [LeetCode_734_句子相似性](https://leetcode-cn.com/problems/sentence-similarity/)
## 解法
### 思路
- 使用map建立相似单词的映射关系
- 遍历字符串数组，判断是否再映射关系中能找到相似单词
### 代码
```java
class Solution {
    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        int len1 = sentence1.length, len2 = sentence2.length;
        if (len1 != len2) {
            return false;
        }
        
        Map<String, Set<String>> mapping = new HashMap<>();
        for (List<String> pair : similarPairs) {
            String word1 = pair.get(0), word2 = pair.get(1);
            Set<String> set1 = mapping.getOrDefault(word1, new HashSet<>());
            Set<String> set2 = mapping.getOrDefault(word2, new HashSet<>());
            set1.add(word2);
            set2.add(word1);
            mapping.put(word1, set1);
            mapping.put(word2, set2);
        }

        for (int i = 0; i < sentence1.length; i++) {
            String word1 = sentence1[i], word2 = sentence2[i];
            if (Objects.equals(word1, word2)) {
                continue;
            }
            
            Set<String> set1 = mapping.get(word1), set2 = mapping.get(word2);
            if ((set1 != null && set1.contains(word2)) || (set2 != null && set2.contains(word1))) {
                continue;
            }
            
            return false;
        }
        
        return true;
    }
}
```
# [LeetCode_751_IP到CIDR](https://leetcode-cn.com/problems/ip-to-cidr/)
## 解法
### 思路
- 计算当前ip的低位有多少连续的0，有多少个，就可以生成多少个由缩写表示的ip
- 过程中，先计算起始ip的数值，用10进制表示，然后通过jdk的api算出该值的低位有多少个0
- 然后计算可以通过表达式表示多少个ip，也就是n个0等于2的n次幂，这个值还不能大于题目要求的n值，也就是简写表达式代表的IP值不能超过n个
- 然后计算多少个0之后，就能求出当前表达式，放入结果列表中
- 10进制值更新为表达式对应的最后的值，继续之前的步骤
- 这里有个特殊情况，题目任务0.0.0.0代表0的个数是0，和jdk求出的32不同，需要做特别处理
### 代码
```java
class Solution {
    public List<String> ipToCIDR(String ip, int n) {
        int start = toInt(ip);
        List<String> ans = new ArrayList<>();
        while (n > 0) {
            int tailingZeros = start == 0 ? 0 : Integer.numberOfTrailingZeros(start);
            int bitsInCidr = 1, mask = 0;
            while (bitsInCidr < n && mask < tailingZeros) {
                bitsInCidr <<= 1;
                mask++;
            }

            if (bitsInCidr > n) {
                bitsInCidr >>= 1;
                mask--;
            }

            ans.add(toString(start, 32 - mask));
            start += bitsInCidr;
            n -= bitsInCidr;
        }

        return ans;
    }

    private String toString(int start, int range) {
        int wordLen = 8;
        StringBuilder sb = new StringBuilder();
        for (int i = 3; i >= 0; i--) {
            sb.append((start >> (i * wordLen)) & 255);
            sb.append(".");
        }

        sb.setLength(sb.length() - 1);
        return sb.append("/").append(range).toString();
    }

    private int toInt(String str) {
        String[] words = str.split("\\.");
        int ans = 0;
        for (String word : words) {
            ans = ans * 256 + Integer.parseInt(word);
        }
        return ans;
    }
}
```
# [LeetCode_1723_完成所有工作的最短时间](https://leetcode-cn.com/problems/find-minimum-time-to-finish-all-jobs/)
## 解法
### 思路
二分+回溯+剪枝
- 枚举可能的最短时间，这个最短时间可以通过二分查找的方式确定
    - 初始值右边界：可能只有一个工人，需要依次执行
    - 初始值左边界：工人数量和工作数量相同，只需要花费工作时间最长的那个工作对应的时间即可
- 回溯判定每一次界定的可能最短时间是否可行
    - 如果在当前工作时间的可能状态下，回溯失败，当前工人没有被分配工作，那可以直接退出，确定失败
        - 当前工人没有被分配工作，那么说明当前的上限无法实现。因为如果当前工人无法分配，那换一个工人也无法分配
    - 如果在当前工作时间的可能状态下，回溯失败，当前工人达到了在花费当前工作时间的情况下达到了上限，也可以直接退出，确定失败
        - 首先工作的耗时是降序排列的，也就是说当前的耗时比之后的都长，在当前符合，且之后耗时更短的情况下，回溯返回仍然是失败的，那么当前的耗时给之后的工人也肯定是失败的，因为当前的耗时更长
### 代码
```java
class Solution {
    public int minimumTimeRequired(int[] jobs, int k) {
        Arrays.sort(jobs);
        int sum = 0;
        for (int i = 0; i < jobs.length / 2; i++) {
            int tmp = jobs[i];
            jobs[i] = jobs[jobs.length - 1 - i];
            jobs[jobs.length - 1 - i] = tmp;

            sum += jobs[i] + jobs[jobs.length - 1 - i];
        }
        
        if (jobs.length % 2 == 1) {
            sum += jobs[jobs.length / 2];
        }
        
        int l = jobs[jobs.length - 1], r = sum;
        while (l < r) {
            int mid = l + (r - l) / 2;
            
            if (backTrack(jobs, new int[k], 0, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        
        return r;
    }
    
    private boolean backTrack(int[] jobs, int[] workloads, int index, int limit) {
        if (index >= jobs.length) {
            return true;
        }
        
        int workload = jobs[index];
        
        for (int i = 0; i < workloads.length; i++) {
            if (workloads[i] + jobs[index] <= limit) {
                workloads[i] += workload;
                if (backTrack(jobs, workloads, index + 1, limit)) {
                    return true;
                }
                workloads[i] -= workload;
            }
            
            if (workloads[i] == 0 || workloads[i] + workload == limit) {
                break;
            }
        }
        
        return false;
    }
}
```