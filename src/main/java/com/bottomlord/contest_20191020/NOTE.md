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

### 代码
```java

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

### 代码
```java

```