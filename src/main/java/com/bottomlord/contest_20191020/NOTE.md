# Contest_1_缀点成线
## 题目
在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。

请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 <font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace">true</font>，否则请返回 <font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace">false</font>。

示例 1：
```
输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
输出：true
```
示例 2：
```
输入：coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
输出：false
```
提示：
```
2 <= coordinates.length <= 1000
coordinates[i].length == 2
-10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
coordinates 中不含重复的点
```
## 解法
### 思路
使用两组数组来计算二元一次方程的`a`和`b`：`y = a * x + b`
### 代码
```java
class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        double a = (double) (coordinates[0][1] - coordinates[1][1]) / (coordinates[0][0] - coordinates[1][0]);
        double b = coordinates[0][1] - a * coordinates[0][0];
        
        for (int[] coordinate: coordinates) {
            if (coordinate[0] * a + b != coordinate[1]) {
                return false;
            }
        }
        
        return true;
    }
}
```
# Contest_2_删除子文件夹
## 题目
你是一位系统管理员，手里有一份文件夹列表 folder，你的任务是要删除该列表中的所有 子文件夹，并以 任意顺序 返回剩下的文件夹。

我们这样定义「子文件夹」：
```
如果文件夹 folder[i] 位于另一个文件夹 folder[j] 下，那么 folder[i] 就是 folder[j] 的子文件夹。
```
文件夹的「路径」是由一个或多个按以下格式串联形成的字符串：
```
/ 后跟一个或者多个小写英文字母。
例如，/leetcode 和 /leetcode/problems 都是有效的路径，而空字符串和 / 不是。
```
示例 1：
```
输入：folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
输出：["/a","/c/d","/c/f"]
解释："/a/b/" 是 "/a" 的子文件夹，而 "/c/d/e" 是 "/c/d" 的子文件夹。
```
示例 2：
```
输入：folder = ["/a","/a/b/c","/a/b/d"]
输出：["/a"]
解释：文件夹 "/a/b/c" 和 "/a/b/d/" 都会被删除，因为它们都是 "/a" 的子文件夹。
```
示例 3：
```
输入：folder = ["/a/b/c","/a/b/d","/a/b/ca"]
输出：["/a/b/c","/a/b/ca","/a/b/d"]
```
提示：
```
1 <= folder.length <= 4 * 10^4
2 <= folder[i].length <= 100
folder[i] 只包含小写字母和 /
folder[i] 总是以字符 / 起始
每个文件夹名都是唯一的
```
## 解法
### 思路
- 遍历字符串数组
- 将每个字符串根据`/`拆分成字符串数组
- 不断将字符串和`/`append到StringBuilder中，并查看存放前缀的set中是否有存在，如果存在就说明这是个子文件夹，直接停止append，并标记
- 如果append结束后，不是子文件夹，那么就把他放入set中
### 代码
```java
class Solution {
    public List<String> removeSubfolders(String[] folder) {
        List<String> ans = new ArrayList<>();
        Set<String> set = new HashSet<>();
        Arrays.sort(folder);
        
        for (String dir : folder) {
            String[] strs = dir.split("/");
            StringBuilder sb = new StringBuilder();
            sb.append(strs[0]);
            boolean flag = true;
            
            for (int i = 1; i < strs.length; i++) {
                sb.append("/").append(strs[i]);
                
                if (set.contains(sb.toString())) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                ans.add(sb.toString());
                set.add(sb.toString());
            }
        }
        
        return ans;
    }
}
```
# Contest_3_替换子串得到平衡字符串
## 题目
有一个只含有 'Q', 'W', 'E', 'R' 四种字符，且长度为 n 的字符串。

假如在该字符串中，这四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」。

给你一个这样的字符串 s，请通过「替换子串」的方式，使原字符串 s 变成一个「平衡字符串」。

你可以用和「待替换子串」长度相同的 任何 其他字符串来完成替换。

请返回待替换子串的最小可能长度。

如果原字符串自身就是一个平衡字符串，则返回 0。

示例 1：
```
输入：s = "QWER"
输出：0
解释：s 已经是平衡的了。
```
示例 2：
```
输入：s = "QQWE"
输出：1
解释：我们需要把一个 'Q' 替换成 'R'，这样得到的 "RQWE" (或 "QRWE") 是平衡的。
```
示例 3：
```
输入：s = "QQQW"
输出：2
解释：我们可以把前面的 "QQ" 替换成 "ER"。 
```
示例 4：
```
输入：s = "QQQQ"
输出：3
解释：我们可以替换后 3 个 'Q'，使 s = "QWER"。
```
提示：
```
1 <= s.length <= 10^5
s.length 是 4 的倍数
s 中只含有 'Q', 'W', 'E', 'R' 四种字符
```
## 解法
### 思路
- 替换子串之外的所有字符出现的个数都是小于等于`n/4`的
- 使用双指针定义一个窗口来进行计算：
    - 计算字符串中所有字符的个数，使用数组`bucket`记录
    - 判断每个字符的个数是否有大于`n/4`的情况，如果没有就直接返回0，说明这个字符串是符合规定的
    - 如果有，就说明需要确定子串的最小长度
    - 右指针遍历字符数组，此时会出现两个概念：
        - 需要保留的字符串：`bucket`
        - 需要删除的子串：当前遍历的字符会从`bucket`中删除
    - 判断保留的字符串是否有大于`n/4`，如果没有就说明是符合规则的
    - 这是就用左右指针的距离之差和已保存的删除的子串长度进行比较，取最最小值
    - 然后循环判断做指针，如果是符合规则的就进行如下循环：
        - 计算下左右指针的距离与已存在的最短子序列的长度，取最小值
        - 试一下将做指针对应的字符数加回去后，保留的字符串还符合规则，如果不符合，就不再进入下个循环
        - 移动左指针，因为进入循环时当前左指针指向的元素字符串已经符合规则，所以删除子串的窗口就要移动一格继续判断，能不能更小，且当前这个字符的情况就不再考虑了
    - 右指针遍历结束后，返回子串长度
### 代码
```java
class Solution {
    public int balancedString(String s) {
        char[] cs = s.toCharArray();
        int[] bucket = new int[26];
        int l = 0, r = 0, ans = s.length(), len = s.length();
        for (char c : cs) {
            bucket[c - 'A']++;
        }
        
        boolean ok = true;
        
        for (int num : bucket) {
            if (num > len / 4) {
                ok = false;
                break;
            }
        }
        
        if (ok) {
            return 0;
        }
        
        for (; r < len; r++) {
            bucket[cs[r] - 'A']--;
            ok = true;
            
            for (int num : bucket) {
                if (num > len / 4) {
                    ok = false;
                    break;
                }
            }
            
            while (ok) {
                ans = Math.min(ans, r - l + 1);
                bucket[cs[l] - 'A']++;
                if (bucket[cs[l] - 'A'] > len / 4) {
                    ok = false;
                }
                l++;
            }
        }
        
        return ans;
    }
}
```
# Contest_4_规划兼职工作
## 题目
你打算利用空闲时间来做兼职工作赚些零花钱。

这里有 n 份兼职工作，每份工作预计从 startTime[i] 开始到 endTime[i] 结束，报酬为 profit[i]。

给你一份兼职工作表，包含开始时间 startTime，结束时间 endTime 和预计报酬 profit 三个数组，请你计算并返回可以获得的最大报酬。

注意，时间上出现重叠的 2 份工作不能同时进行。

如果你选择的工作在时间 X 结束，那么你可以立刻进行在时间 X 开始的下一份工作。

示例 1：
```
输入：startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
输出：120
解释：
我们选出第 1 份和第 4 份工作， 
时间范围是 [1-3]+[3-6]，共获得报酬 120 = 50 + 70。
```
示例 2：
```
输入：startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
输出：150
解释：
我们选择第 1，4，5 份工作。 
共获得报酬 150 = 20 + 70 + 60。
```
示例 3：
```
输入：startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
输出：6
```
提示：
```
1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
1 <= startTime[i] < endTime[i] <= 10^9
1 <= profit[i] <= 10^4
```
## 解法
### 思路
动态规划：
- dp[i]：以第i份工作为开始时能获得的最大报酬
- base case：dp[0]，从第一件工作开始递归所有的工作
- 状态转移方程：`dp[i] = max(dp[i + 1], profit[i] + dp[next_available_job]`
- 过程：
    - 先获取一个工作的排序数组`jobs`，工作的顺序根据开始时间的升序，如果开始时间相同，就用结束时间的降序
    - 创建一个dp数组，记录jobs顺序的工作为第一个工作时，可以获得的最大利润，初始值为-1
    - 有了jobs，开始递归计算dp，从0开始：
        - 如果开始的工作数等于工作总数，说明递归结束，返回0
        - 如果dp的值不为-1就直接返回dp值，说明不用计算
        - 两种退出条件结束后，开始递归的主逻辑：将`dp[i + 1]`和`profit[i] + dp[next_available_job]`作比较，取最大值
            - 求`dp[i + 1]`的值就继续递归
            - 求`profit[i] + dp[next_available_job]`的值就是循环找当前工作的结束时间小于等于当前工作之后的开始时间的工作，将这个工作对应的dp值通过递归计算出来，然后加上当前工作的profit
            - 取两者的最大值作为`dp[i]`并返回
- 结果：返回递归结果
### 代码
```java
class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int len = profit.length;
        int[] dp = new int[len];
        Integer[] jobs = new Integer[len];
        for (int i = 0; i < len; i++) {
            jobs[i] = i;
        }

        Arrays.fill(dp, -1);
        Arrays.sort(jobs, Comparator.comparing(x -> startTime[(int) x]).thenComparing(x -> endTime[(int) x]));

        return recurse(dp, jobs, profit, startTime, endTime, 0, len);
    }

    private int recurse(int[] dp, Integer[] jobs, int[] profit, int[] startTime, int[] endTime, int startJob, int jobNums) {
        if (startJob == jobNums) {
            return 0;
        }

        if (dp[startJob] != -1) {
            return dp[startJob];
        }

        int next = recurse(dp, jobs, profit, startTime, endTime, startJob + 1, jobNums);
        int cur = profit[jobs[startJob]];
        for (int i = startJob + 1; i < jobNums; i++) {
            if (startTime[jobs[i]] >= endTime[jobs[startJob]]) {
                cur += recurse(dp, jobs, profit, startTime, endTime, i, jobNums);
                break;
            }
        }

        int ans = Math.max(next, cur);
        
        return dp[startJob] = ans;
    }
}
```