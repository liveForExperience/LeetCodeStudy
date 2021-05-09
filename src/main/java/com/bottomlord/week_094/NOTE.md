# [LeetCode_1011_在D天内送达包裹的能力](https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/)
## 失败解法
### 原因
超时
### 思路
回溯
- 先求平均值和最大元素之间的较大值作为target
- 以这个target作为标准开始回溯，尝试所有可能
### 代码
```java
class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int max = Integer.MIN_VALUE, sum = 0;
        for (int num : weights) {
            max = Math.max(max, num);
            sum += num;
        }
        
        int avg = sum % D == 0 ? sum / D : sum / D + 1;
        int target = Math.max(avg, max);

        boolean find = false;
        while (!find) {
            find = backTrack(weights, 0, 0, target, D);
            if (!find) {
                target++;
            }
        }

        return target;
    }

    private boolean backTrack(int[] weights, int index, int count, int target, int day) {
        if (count > day) {
            return false;
        }

        if (index == weights.length) {
            return true;
        }

        int sum = 0;
        boolean find = false;
        for (int i = index; i < weights.length; i++) {
            sum += weights[i];
            if (sum > target) {
                break;
            }
            find = backTrack(weights, i + 1, count + 1, target, day);

            if (find) {
                return true;
            }
        }

        return false;
    }
}
```
## 解法
### 思路
二分查找：
- 其实不需要依照失败解法的方式，求平均值，因为元素中的最大值一定会大于等于平均值，所以只要求得元素最大值就可以
- 这个最大值可以理解为是可能值的最小值，因为如果小于这个值就无法将所有元素都搬到船上
- 而最大值就是元素总和，可以理解成必须必须在一天之内放到船上的话，就要求得总和
### 代码
```java
class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int sum = 0, max = Integer.MIN_VALUE;
        for (int weight : weights) {
            sum += weight;
            max = Math.max(max, weight);
        }
        
        int head = max, tail = sum;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            
            if (isValid(weights, mid, D)) {
                tail = mid;
            } else {
                head = mid + 1;
            }
        }
        
        return head;
    }
    
    private boolean isValid(int[] weights, int target, int day) {
        int sum = target;
        for (int i = 0; i < weights.length;) {
            int weight = weights[i];
            
            if (sum - weight < 0) {
                sum = target;
                day--;
            } else {
                sum -= weight;
                i++;
            }
            
            if (day <= 0) {
                return false;
            }
        }
        
        return true;
    }
}
```
## 解法二
### 思路
- 解法一中的右边界可以再缩小为以最大值为所有元素的值，然后根据天数求出来的平均值
### 代码
```java
class Solution {
public int shipWithinDays(int[] weights, int D) {
        int max = Integer.MIN_VALUE;
        for (int weight : weights) {
            max = Math.max(max, weight);
        }
        
        int head = max, tail = max * weights.length / D;
        while (head < tail) {
            int mid = head + ((tail - head) >>> 1);
            
            if (isValid(weights, mid, D)) {
                tail = mid;
            } else {
                head = mid + 1;
            }
        }
        
        return head;
    }
    
    private boolean isValid(int[] weights, int target, int day) {
        int cur = target;
        
        for (int i = 0; i < weights.length;) {
            int weight = weights[i];
            
            if (cur - weight < 0) {
                cur = target;
                day--;
            } else {
                cur -= weight;
                i++;
            }
            
            if (day <= 0) {
                return false;
            }
        }
        
        return true;
    }
}
```
# [LeetCode_544_输出比赛匹配对](https://leetcode-cn.com/problems/output-contest-matches/)
## 解法
### 思路
递归：
- 将n转换为从1开始的n个元素的字符串数组
- 递归将字符串数组的头尾元素按照题目要求做拼接
- 最终当数组长度为1，返回这一个元素
### 代码
```java
class Solution {
    public String findContestMatch(int n) {
        String[] arr = new String[n];
        for (int i = 1; i <= n; i++) {
            arr[i - 1] = "" + i;
        }

        return recuse(arr);
    }

    private String recuse(String[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }

        String[] newArr = new String[arr.length / 2];
        for (int i = 0; i < arr.length / 2; i++) {
            newArr[i] = combineStr(arr[i], arr[arr.length - 1 - i]);
        }

        return recuse(newArr);
    }

    private String combineStr(String x, String y) {
        return "(" + x + "," + y + ")";
    }
}
```
# [LeetCode_545_二叉树的边界](https://leetcode-cn.com/problems/boundary-of-binary-tree/)
## 解法
### 思路
- 把过程分成3个部分：
    - 查找左边界
    - 查找叶子节点
    - 查找右边界
- 左边界和右边界的查找逻辑类似但条件相反
- 叶子节点通过dfs来查找
- 还要注意输出的顺序：
    - 左边界是先父节点再子节点
    - 右边界是先子节点再父节点
### 代码
```java
class Solution {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        if (root == null) {
            return ans;
        }

        ans.add(root.val);
        findLeft(root.left, ans);
        if (root.left != null || root.right != null) {
            findLeaf(root, ans);
        }
        findRight(root.right, ans);

        return ans;
    }

    private void findLeft(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            return;
        }

        list.add(node.val);

        findLeft(node.left, list);

        if (node.left == null) {
            findLeft(node.right, list);
        }
    }

    private void findLeaf(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            list.add(node.val);
        }

        findLeaf(node.left, list);
        findLeaf(node.right, list);
    }

    private void findRight(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            return;
        }

        if (node.right == null) {
            findRight(node.left, list);
        }

        findRight(node.right, list);

        list.add(node.val);
    }
}
```
# [LeetCode_938_二叉搜索树的范围和](https://leetcode-cn.com/problems/range-sum-of-bst/)
## 解法
### 思路
- 深度优先搜索，通过比较当前节点值和最大最小值，来判断是否要累加当前节点并返回
    - 如果当前节点是空节点，就返回0
    - 如果当前节点小于最小值，就累加其右子树的节点
    - 如果当前节点大于最大值，就累加其左子树的节点
    - 否则就累加左右子树的节点
### 代码
```java
class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }

        int ans = 0, val = root.val;
        if (val >= low && val <= high) {
            ans += val;
        }
        
        return ans + 
               (val < low ? 0 : rangeSumBST(root.left, low, high)) + 
               (val > high ? 0 : rangeSumBST(root.right, low, high));
    }
}
```
# [LeetoCde_548_将数组分割成和相等的子数组](https://leetcode-cn.com/problems/split-array-with-equal-sum/)
## 失败解法
### 原因
超时
### 思路
- 求前缀和
- 3层循环判断
### 代码
```java
class Solution {
    public boolean splitArray(int[] nums) {
        int len = nums.length;
        int[] sums = new int[len];
        sums[0] = nums[0];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        for (int i = 1; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (sums[i - 1] == sums[j - 1] - sums[i] &&
                        sums[j - 1] - sums[i] == sums[k - 1] - sums[j] &&
                        sums[k - 1] - sums[j] == sums[len - 1] - sums[k]) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
}
```
## 失败解法
### 原因
超时
### 思路
前缀和+3重循环+提前失败
### 代码
```java
class Solution {
    public boolean splitArray(int[] nums) {
        int len = nums.length;
        int[] sums = new int[len];
        sums[0] = nums[0];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        for (int i = 1; i < len; i++) {
            int one = sums[i - 1];
            for (int j = i + 1; j < len; j++) {
                int two = sums[j - 1] - sums[i];
                if (one != two) {
                    continue;
                }
                for (int k = j + 1; k < len; k++) {
                    int three = sums[k - 1] - sums[j],
                        four = sums[len - 1] - sums[k];
                    
                    if (two == three && three == four) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
```
### 失败解法
### 原因
超时
### 思路
引入hashmap
- 先算出第一个和第二个分割点，然后求出能使得两部分相等的值，key为值，value为坐标的集合，存储起来
- 然后遍历所有可能相等的key，找到他们的values，以这些values为起始坐标，第3部分和第4部分相等的值，看下是否与key匹配，如果匹配就返回true
### 代码
```java
class Solution {
    public boolean splitArray(int[] nums) {
        int len = nums.length;
        int[] sums = new int[len];
        sums[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i < len; i++) {
            int one = sums[i - 1];
            for (int j = i + 1; j < len; j++) {
                if (sums[j - 1] - sums[i] == sums[i - 1]) {
                    List<Integer> list = map.getOrDefault(one, new ArrayList<>());
                    list.add(j);
                    map.put(one, list);
                }
            }
        }
        
        for (Integer sum : map.keySet()) {
            List<Integer> list = map.get(sum);
            
            for (Integer start : list) {
                for (int i = start + 1; i < len; i++) {
                    int three = sums[i - 1] - sums[start];
                    if (three != sum) {
                        continue;
                    }
                    
                    for (int j = i + 1; j < len; j++) {
                        if (three == sums[len - 1] - sums[i]) {
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
}
```
## 解法
### 思路
- 数组会被拆分成4个部分
- 如果先求出前2部分相等的情况，然后记录这些情况，同时基于第二个分界坐标，尝试右边的相等两部分，如果相等的值再之前左半部分暂存的里面能找到，就说明可以分成相等的4部分
- 那么只要外层确定把数组一分为二的那个节点坐标，然后内层分2步
    - 第1步求左边相等的情况，用set暂存起来
    - 第2步求右边相等的部分，与set中的值匹配，找到就返回true
- 所有情况遍历完如果还没找到，就返回false
### 代码
```java
class Solution {
    public boolean splitArray(int[] nums) {
        int len = nums.length;
        int[] sums = new int[len];
        sums[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        for (int j = 3; j < len - 3; j++) {
            Set<Integer> set = new HashSet<>();
            for (int i = 1; i + 1 < j; i++) {
                if (sums[i - 1] == sums[j - 1] - sums[i]) {
                    set.add(sums[i - 1]);
                }
            }

            for (int k = j + 2; k < len - 1; k++) {
                int tempSum = sums[k - 1] - sums[j];
                if (tempSum == sums[len - 1] - sums[k] && set.contains(tempSum)) {
                    return true;
                }
            }
        }

        return false;
    }
}
```
# [LeetCode_633_平方数之和](https://leetcode-cn.com/problems/sum-of-square-numbers/)
## 解法
### 思路
使用JDK内建函数sqrt
- 遍历c的平方根范围内的所有整数
- 依次计算判断
- 使用long防止溢出
### 代码
```java
class Solution {
    public boolean judgeSquareSum(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - (a * a));
            if (b == (long) b) {
                return true;
            }
        }
        return false;
    }
}
```
## 解法二
### 思路
双指针
- 头指针代表a，初始化为0
- 尾指针代表b，初始化为c的平方根
- 如果相等就返回true
- 如果小于c说明a小了，加1
- 如果大于c说明b大了，减1
### 代码
```java
class Solution {
    public boolean judgeSquareSum(int c) {
        int a = 0, b = (int) Math.sqrt(c);
        while (a <= b) {
            int x = (int)Math.pow(a, 2), y = (int)Math.pow(b, 2);
            if (x + y == c) {
                return true;
            } else if (x + y < c) {
                a++;
            } else {
                b--;
            }
        }
        return false;
    }
}
```
## 解法三
### 思路
费马平方和定理
> 一个非负整数 `c` 如果能够表示为两个整数的平方和，当且仅当 `c` 的所有形如 `4k + 3` 的质因子的幂均为偶数。
- 遍历c所有可能的质因子
- 先求出当前质因子的幂，求的过程中c也不断调整，调整方式是按照质数整除缩小，因为是质数，所以不影响求其他质因数
- 然后判断当前质因子是否是形为4k+3，且幂不是偶数的，如果是就说明不符合，返回false
- 如果可能的质因子都符合定理，再判断c如果是质数，其是否形如4k+3，如果是，那么他的幂就不是偶数，就返回false，反之为true
### 代码
```java
class Solution {
    public boolean judgeSquareSum(int c) {
        for (int base = 2; base * base <= c; base++) {
            if (c % base != 0) {
                continue;
            }

            int exp = 0, cur = c;
            while (cur % base == 0) {
                cur /= base;
                exp++;
            }

            if (c % 4 == 3 && exp % 2 != 0) {
                return false;
            }
        }

        return c % 4 != 3;
    }
}
```
# [LeetCode_403_青蛙过河](https://leetcode-cn.com/problems/frog-jump/)
## 解法
### 思路
- 生成一个map，key为所有的石头坐标，value是对应的跳到这个石头上时候经过的距离，有了这个距离就可以计算出从这个石头出发能够跳到的坐标
- 然后根据题目，初始化坐标0的为止，也就是第1个石头。到这个石头的距离，因为题目说一开始就在这个石头上，所以是0
- 然后遍历石头数组，从map中拿到到达当前石头时的那些距离，遍历3种到下个石头的距离，看看map里存不存在
    - 如果存在，就说明，从当前石头出发，往后跳可能距离，可以到达另一个石头，过程可以继续
- 整个数组遍历完以后，看一下最后一个石头对应的距离是不是空的
    - 如果空的，说明没有可以跳到当前石头的位置
    - 如果不是空的，说明能够跳到最后的石头上
### 代码
```java
class Solution {
    private int[] diffs = new int[]{-1, 0, 1};

    public boolean canCross(int[] stones) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            map.put(stones[i], new HashSet<>());
            if (i == 0) {
                map.get(stones[i]).add(0);
            }
        }

        for (int stone : stones) {
            Set<Integer> distances = map.get(stone);
            
            for (Integer distance : distances) {
                for (int diff : diffs) {
                    if (distance + diff <= 0) {
                        continue;
                    }
                    
                    if (map.containsKey(stone + distance + diff)) {
                        map.get(stone + distance + diff).add(distance + diff);
                    }
                }
            }
        }
        
        return !map.get(stones[stones.length - 1]).isEmpty();
    }
}
```
# [LeetCode_549_二叉树中最长的连续序列](https://leetcode-cn.com/problems/binary-tree-longest-consecutive-sequence-ii/solution/)
## 解法
### 思路
递归：
- 过程中记录升序和降序的个数
- 升序和降序通过判断左子节点和右子节点与当前节点值的关系来更新
- 先左再右，更新外升序和降序的值以后，将2个值相加就是整个序列的长度
    - 如果左小，右大，或者左大，右小，那就是左右相连，l + r - 1，减1是因为初始的时候的时候当前值被左右子树都计算进去了
    - 如果左小，右小，或者左大，右大，那就是左右的值比较，去除最大的，然后仍然是l + r - 1
### 代码
```java
class Solution {
    private int ans = 0;
    
    public int longestConsecutive(TreeNode root) {
        dfs(root);
        return ans;
    }
    
    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int inc = 1, dec = 1;
        if (node.left != null) {
            int[] result = dfs(node.left);
            if (node.val == node.left.val - 1) {
                inc = result[0] + 1;
            }

            if (node.val == node.left.val + 1) {
                dec = result[1] + 1;
            }
        }

        if (node.right != null) {
            int[] result = dfs(node.right);
            if (node.val == node.right.val - 1) {
                inc = Math.max(inc, result[0] + 1);
            }

            if (node.val == node.right.val + 1) {
                dec = Math.max(dec, result[1] + 1);
            }
        }

        ans = Math.max(ans, inc + dec - 1);
        return new int[]{inc, dec};
    }
}
```
# [LeetCode_552_学生出勤记录](https://leetcode-cn.com/problems/student-attendance-record-ii/)
## 解法
### 思路
动态规划：
- `dp[i]` ：n为i的所有可能字符串中，有效的个数
- 状态转移方程：需要按照状态分成3种情况
    - P：如果前一个状态是这种情况，那直接就是有效的，`dp[i] += dp[i - 1]`
    - L：如果前一个状态是迟到，那么有一种情况是无效的，也就是`LLL`，所以需要去掉这种情况，`dp[i] += dp[i-1] - dp[i - 4]`
    - A：如果要考虑缺勤的情况，那么只有2中可能是有效的
        - 没有缺勤，那么就是之前P和L的情况中已经包含的情况
        - 有1个A，那么这个A就可以出现在日期的每个位置，那每个i位置上的可能值就是`dp[i - 1] * dp[n - i]`，代表i-1个单词和n-i个单词的可能性的组合
- 如上分析可以看到，A的情况不适合放在dp中做状态转移，但可以在状态方程求出来以后，根据状态来求
- 将dp求得L和P的情况的结果`dp[n]`和A的情况的结果累加，就是最终的结果
### 代码
```java
class Solution {
    public int checkRecord(int n) {
        long mod = 1000000007;
        long[] dp = new long[n <= 5 ? 6 : n + 1];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;
        dp[3] = 7;
        for (int i = 4; i <= n; i++) {
            dp[i] = ((2 * dp[i - 1]) % mod + (mod - dp[i - 4])) % mod;
        }
        long sum = dp[n];

        for (int i = 1; i <= n; i++) {
            sum += (dp[i - 1] * dp[n - i]) % mod;
        }

        return (int) (sum % mod);
    }
}
```
# [LeetCode_137_只出现一次的数字II](https://leetcode-cn.com/problems/single-number-ii/)
## 解法
### 思路
- 用map计数
- 遍历map找到计数为1的key
### 代码
```java
class Solution {
    public int singleNumber(int[] nums) {
        return Arrays.stream(nums).boxed().collect(Collectors.toMap(k -> k, v -> 1, Integer::sum)).entrySet().stream().filter(entry -> entry.getValue() == 1).map(Map.Entry::getKey).findFirst().orElse(0);

    }
}
```
## 解法二
### 思路
- 因为都是int值，所以每一个元素都是32位，且只有一个数字是单个出现，其他都是3个的，那么每一位上，要么是3的倍数，要么被3除余1
- 依次遍历每个数字的32个位，求出每一位上1出现的个数，然后与3取模，求出余数作为结果值当前位上的值
### 代码
```java
class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int total = 0;
            for (int num : nums) {
                total += ((num & (1 << i)) != 0) ? 1 : 0;
            }

            ans |= (total % 3) << i;
        }
        return ans;
    }
}
```
# [LeetCode_554_砖墙](https://leetcode-cn.com/problems/brick-wall/)
## 解法
### 思路
- 求出每一行的前缀和元素
- 对前缀和计数
- 找到前缀和不是每一行总长度，且计数值最大的前缀和
- 用墙的总行数与这个前缀和的个数做差，获得结果
### 代码
```java
class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        int rowLen = wall.get(0).stream().mapToInt(x -> x).sum();
        
        Map<Integer, Integer> map = new HashMap<>();
        for (List<Integer> list : wall) {
            int sum = 0;
            for (Integer num : list) {
                sum += num;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max && entry.getKey() < rowLen) {
                max = entry.getValue();
            }
        }

        return wall.size() - max;
    }
}
```
# [LeetCode_555_分割连接字符串](https://leetcode-cn.com/problems/split-concatenated-strings/)
## 失败解法
### 原因
超时
### 思路
- 回溯求出所有可能的字符串组合
- 遍历所有字符串，依次尝试所有旋转，求出最大字符串
### 代码
```java
class Solution {
    public String splitLoopedString(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }

        Set<String> set = new HashSet<>();
        backTrack(strs, 0, new StringBuilder(), set);

        String ans = null;
        for (String str : set) {
            for (int i = 0; i < str.length(); i++) {
                int index = 0;
                StringBuilder sb = new StringBuilder();
                while (index < str.length()) {
                    sb.append(str.charAt((i + index) % str.length()));
                    index++;
                }

                ans = max(sb.toString(), ans);
            }
        }

        return ans;
    }

    private void backTrack(String[] strs, int index, StringBuilder sb, Set<String> list) {
        if (index == strs.length) {
            list.add(sb.toString());
            return;
        }

        int len = sb.length();
        sb.append(strs[index]);
        backTrack(strs, index + 1, sb, list);
        sb.setLength(len);
        sb.append(reverse(strs[index]));
        backTrack(strs, index + 1, sb, list);
        sb.setLength(len);
    }

    private String reverse(String str) {
        char[] cs = str.toCharArray();
        int len = cs.length;
        for (int i = 0; i < len / 2; i++) {
            char tmp = cs[i];
            cs[i] = cs[len - i - 1];
            cs[len - i - 1] = tmp;
        }
        return new String(cs);
    }

    private String max(String a, String b) {
        if (a == null) {
            return b;
        }

        if (b == null) {
            return a;
        }

        return a.compareTo(b) > 0 ? a : b;
    }
}
```
## 解法
### 思路
- 如果不是包含起始字符的字符串，其他字符串只要保持本身位最大字符串即可组成最大字典序的字符串
- 将所有字符串处理成最大字典序的字符串后，这些字符串作为可能结果中，非起始字符串的其他字符串拼接元素
- 然后遍历原始字符串，将这个字符串作为起始字符串，生成以原始字符串和反转后的字符串，然后以他们为包含起始字符的字符串，依次模拟每一个字符，然后与其他字符串拼接后做字典序比较，保留较大的字符串作为结果
### 代码
```java
class Solution {
    public String splitLoopedString(String[] strs) {
        String[] revs = new String[strs.length];
        String[] origin = Arrays.copyOfRange(strs, 0, strs.length);

        for (int i = 0; i < strs.length; i++) {
            String str = strs[i], rev = reverse(str);
            strs[i] = max(str, rev);
            revs[i] = rev;
        }

        String ans = String.join("", strs);
        for (int i = 0; i < strs.length; i++) {
            String str = origin[i], rev = revs[i];
            int index = 1;
            StringBuilder sb = new StringBuilder();
            while (index < strs.length) {
                sb.append(strs[(i + index) % strs.length]);
                index++;
            }
            String other = sb.toString();

            for (int j = 0 ; j < str.length(); j++) {
                String cur = str.substring(j) + other + str.substring(0, j);
                ans = max(ans, cur);
            }

            for (int j = 0 ; j < rev.length(); j++) {
                String cur = rev.substring(j) + other + rev.substring(0, j);
                ans = max(ans, cur);
            }
        }

        return ans;
    }

    private String reverse(String str) {
        char[] cs = str.toCharArray();
        int len = cs.length;
        for (int i = 0; i < len / 2; i++) {
            char tmp = cs[i];
            cs[i] = cs[len - i - 1];
            cs[len - i - 1] = tmp;
        }
        return new String(cs);
    }

    private String max(String a, String b) {
        if (a == null) {
            return b;
        }

        if (b == null) {
            return a;
        }

        return a.compareTo(b) > 0 ? a : b;
    }
}
```
# [LeetCode_608_员工的重要性](https://leetcode-cn.com/problems/employee-importance/)
## 解法
### 思路
dfs累加，用一个数组暂存累加值
### 代码
```java
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        if (employees == null || employees.size() == 0) {
            return 0;
        }

        Map<Integer, Integer> importanceMap = employees.stream().collect(Collectors.toMap(e -> e.id, e -> e.importance, (x,y) -> y));
        Map<Integer, List<Integer>> subOrdinatesMap = employees.stream().collect(Collectors.toMap(e -> e.id, e -> e.subordinates, (x,y) -> y));
        int[] ans = new int[1];
        dfs(id, subOrdinatesMap, importanceMap, ans);
        return ans[0];
    }
    
    private void dfs(Integer id, Map<Integer, List<Integer>> subOrdinatesMap, Map<Integer, Integer> importanceMap, int[] ans) {
        ans[0] += importanceMap.get(id);
        
        List<Integer> sList = subOrdinatesMap.get(id);
        if (sList == null) {
            return;
        }

        for (Integer sId : sList) {
            dfs(sId, subOrdinatesMap, importanceMap, ans);
        }
    }
}
```
## 解法二
### 思路
省去解法一那个数组
### 代码
```java
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        if (employees == null || employees.size() == 0) {
            return 0;
        }
        
        return dfs(id,
                employees.stream().collect(Collectors.toMap(e -> e.id, e -> e.importance, (x, y) -> y)),
                employees.stream().collect(Collectors.toMap(e -> e.id, e -> e.subordinates, (x, y) -> y)));
    }
    
    private int dfs(Integer id, Map<Integer, Integer> iMap, Map<Integer, List<Integer>> sMap) {
        int sum = iMap.getOrDefault(id, 0);
        
        List<Integer> sList = sMap.getOrDefault(id, new ArrayList<>());
        for (Integer sId : sList) {
            sum += dfs(sId, iMap, sMap);
        }
        
        return sum;
    }
}
```
## 解法三
### 思路
将前两个解法中的2个map合并成1个map
### 代码
```java
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        return dfs(id, employees.stream().collect(Collectors.toMap(e -> e.id, e -> e, (x, y) -> y)));
    }
    
    private int dfs(Integer id, Map<Integer, Employee> map) {
        Employee e = map.get(id);
        
        int sum = e.importance;
        
        if (e.subordinates == null) {
            return sum;
        }
        
        for (Integer sId : e.subordinates) {
            sum += dfs(sId, map);
        }
        return sum;
    }
}
```
# [LeetCode_556_下一个更大元素III](https://leetcode-cn.com/problems/next-greater-element-iii/)
## 解法
### 思路
- 将数字转换成字符数组
- 从右侧开始遍历，找到第一个cs[i] > cs[i - 1]的字符组合
- 如果没有找到就直接返回-1
- 否则就重新从i坐标开始往右寻找比当前值大的最小值，如果有连续相等的数，就取最后的那个数，和当前值交换，并同时将i - 1到len-1元素进行颠倒
- 这样处理后就相当于找到了第一个比可以通过转换字符获得的比当前值大的数
- 这种处理就相当于进行最小的进位
- 当转换完以后，还要和int的最大值进行比较，如果大于，则同样返回-1
### 代码
```java
class Solution {
    public int nextGreaterElement(int n) {
        char[] cs = ("" + n).toCharArray();
        boolean find = false;
        for (int i = cs.length - 2; i >= 0; i--) {
            if (cs[i] >= cs[i + 1]) {
                continue;
            }

            int index = i + 1;
            for (int j = i + 1; j < cs.length; j++) {
                if (cs[j] < cs[i]) {
                    break;
                }
                
                if (cs[j] <= cs[index] && cs[j] > cs[i]) {
                    index = j;
                }
            }
            
            swap(cs, i, index);
            int count = (cs.length - 1- i) / 2;
            
            for (int j = 0; j < count; j++) {
                swap(cs, i + 1 + j, cs.length - 1 - j);
            }
            
            find = true;
            break;
        }

        if (!find) {
            return -1;
        }

        long sum = 0;
        for (char c : cs) {
            sum = sum * 10 + (c - '0');
        }

        return sum > Integer.MAX_VALUE ? -1 : (int) sum;
    }
    
    private void swap(char[] cs, int x, int y) {
        char c = cs[x];
        cs[x] = cs[y];
        cs[y] = c;
    }
}
```
# [LeetCode_1482_制作m束花所需的最少天数](https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets/)
## 失败解法
### 原因
超时
### 思路
- 求出以每一个花的坐标作为一束花对应的子数组的起始坐标，求出这个子数组中的最大值，存储
- 对存储的数组进行dfs，找到最小值
### 代码
```java
class Solution {
    private int ans = Integer.MAX_VALUE;

    public int minDays(int[] bloomDay, int m, int k) {
        int len = bloomDay.length;
        if (len < m * k) {
            return -1;
        }

        int[] bucket = new int[len - k + 1];
        for (int i = 0; i < len - k + 1; i++) {
            bucket[i] = Arrays.stream(Arrays.copyOfRange(bloomDay, i, i + k)).max().getAsInt();
        }

        dfs(bucket, 0, Integer.MIN_VALUE, 0, m, k);

        return ans;
    }

    private void dfs(int[] bucket, int index, int max, int count, int m, int k) {
        if (count == m) {
            ans = Math.min(ans, max);
            return;
        }

        if (max > ans) {
            return;
        }

        if (index >= bucket.length) {
            return;
        }

        for (int i = index; i < bucket.length; i++) {
            dfs(bucket, i + k, Math.max(max, bucket[i]), count + 1, m, k);
        }
    }
}
```
## 解法
### 思路
二分查找
### 代码
```java
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int len = bloomDay.length;
        if (m * k > len) {
            return -1;
        }
        
        int l = 0, r = Arrays.stream(bloomDay).max().getAsInt();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (match(bloomDay, m, k, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }
    
    private boolean match(int[] bloomDay, int m, int k, int limit) {
        int flowers = 0, branch = 0, len = bloomDay.length;

        for (int i = 0; i < len && branch < m; i++) {
            if (bloomDay[i] <= limit) {
                flowers++;
                if (flowers == k) {
                    branch++;
                    flowers = 0;
                }
            } else {
                flowers = 0;
            }
        }

        return branch >= m;
    }
}
```