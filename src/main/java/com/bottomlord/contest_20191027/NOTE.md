# Contest_1_给出指定方程的正整数解
## 题目
给出一个函数  f(x, y) 和一个目标结果 z，请你计算方程 f(x,y) == z 所有可能的正整数 数对 x 和 y。

给定函数是严格单调的，也就是说：
```
f(x, y) < f(x + 1, y)
f(x, y) < f(x, y + 1)
```
函数接口定义如下：
```
interface CustomFunction {
public:
  // Returns positive integer f(x, y) for any given positive integer x and y.
  int f(int x, int y);
};
如果你想自定义测试，你可以输入整数 function_id 和一个目标结果 z 作为输入，其中 function_id 表示一个隐藏函数列表中的一个函数编号，题目只会告诉你列表中的 2 个函数。  
```
你可以将满足条件的 结果数对 按任意顺序返回。

示例 1：
```
输入：function_id = 1, z = 5
输出：[[1,4],[2,3],[3,2],[4,1]]
解释：function_id = 1 表示 f(x, y) = x + y
```
示例 2：
```
输入：function_id = 2, z = 5
输出：[[1,5],[5,1]]
解释：function_id = 2 表示 f(x, y) = x * y
```
提示：
```
1 <= function_id <= 9
1 <= z <= 100
题目保证 f(x, y) == z 的解处于 1 <= x, y <= 1000 的范围内。
在 1 <= x, y <= 1000 的前提下，题目保证 f(x, y) 是一个 32 位有符号整数。
```
## 解法
### 思路
- 嵌套遍历两个参数的各1000种可能
- 代入f函数，将结果是z的两个参数生成list放入结果中
- 返回最终的结果
### 代码
```java
class Solution {
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            for (int j = 1; j <= 1000; j++) {
                if (customfunction.f(i, j) == z) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
}
```
# Contest_2_循环码排列
## 题目
给你两个整数 n 和 start。你的任务是返回任意 (0,1,2,,...,2^n-1) 的排列 p，并且满足：
```
p[0] = start
p[i] 和 p[i+1] 的二进制表示形式只有一位不同
p[0] 和 p[2^n -1] 的二进制表示形式也只有一位不同
```
示例 1：
```
输入：n = 2, start = 3
输出：[3,2,0,1]
解释：这个排列的二进制表示是 (11,10,00,01)
     所有的相邻元素都有一位是不同的，另一个有效的排列是 [3,1,0,2]
```
示例 2：
```
输出：n = 3, start = 2
输出：[2,6,7,5,4,0,1,3]
解释：这个排列的二进制表示是 (010,110,111,101,100,000,001,011)
```
提示：
```
1 <= n <= 16
0 <= start < 2^n
```
## 解法
### 思路
- 通过生成格雷码可知，最终的结果必定是最高位是1和0两种，且剩余位为镜像排列
- 所以过程可以模拟成dfs的中序遍历，左右子树分别作为互为镜像的两部分，根节点在当前层左中序处理时，将最高位变为1或0，带入右子树
- 为了能起到这个目的，需要有一个初始为最高位为1其余为0的值，然后再递归进入左右子树的时候通过左移这个标志来一位位地改变数
- 因为需要一位位地改变数，所以这个值需要有状态地被记录，方便放入结果list中
- dfs会导致左右子树地值是从最低位开始被变更，这样使得中序可以符合题目要求产生镜像地值，从而也能理解到，这棵二叉树地根节点就是start
### 代码
```java
class Solution {
    private int val;
    public List<Integer> circularPermutation(int n, int start) {
        this.val = start;
        List<Integer> ans = new ArrayList<Integer>(){{add(start);}};
        dfs(1 << (n - 1), ans);
        return ans;
    }
    
    private void dfs(int xor, List<Integer> ans) {
        if (xor == 0) {
            return;
        }
        
        dfs(xor >> 1, ans);
        this.val ^= xor;
        ans.add(this.val);
        dfs(xor >> 1, ans);
    }
}
```
# Contest_3_串联字符串的最大长度
## 题目
给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，那么它就是一个可行解。

请返回所有可行解 s 中最长长度。

示例 1：
```
输入：arr = ["un","iq","ue"]
输出：4
解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
```
示例 2：
```
输入：arr = ["cha","r","act","ers"]
输出：6
解释：可能的解答有 "chaers" 和 "acters"。
```
示例 3：
```
输入：arr = ["abcdefghijklmnopqrstuvwxyz"]
输出：26
```
提示：
```
1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] 中只含有小写英文字母
```
## 解法
### 思路
- 遍历arr生成一个list，list中保存每个字符串的字符去重表集合，同时在遍历时将有重复字符的字符串去除
- 遍历list，将里面的每个set都和其他元素对应的set进行嵌套遍历，如果有相同的字符就跳过，否则就将其addAll入这个set中
- 计算相加后的set长度，并与原最长长度比大小，保留最大值
- 继续遍历其他字符串对应的set，重复如上逻辑
- 最后返回最大值
### 代码
```java
class Solution {
    public int maxLength(List<String> arr) {
        List<Set<Character>> list = new ArrayList<>(arr.size());
        for (String str : arr) {
            Set<Character> set = new HashSet<>();
            char[] cs = str.toCharArray();
            for (char c : cs) {
                set.add(c);
            }

            if (set.size() == cs.length) {
                list.add(set);
            }
        }

        int ans = 0;
        for (int i = 0; i < list.size(); i++) {
            Set<Character> set = new HashSet<>(list.get(i));
            int count = set.size();
            for (int j = 0; j < list.size(); j++) {
                if (i == j) {
                    continue;
                }

                boolean flag = true;
                for (char c : list.get(j)) {
                    if (set.contains(c)) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    set.addAll(list.get(j));
                }
            }

            ans = Math.max(ans, set.size());
        }

        return ans;
    }
}
```
# Contest_4_铺瓷砖
## 题目
你是一位施工队的工长，根据设计师的要求准备为一套设计风格独特的房子进行室内装修。

房子的客厅大小为 n x m，为保持极简的风格，需要使用尽可能少的 正方形 瓷砖来铺盖地面。

假设正方形瓷砖的规格不限，边长都是整数。

请你帮设计师计算一下，最少需要用到多少块方形瓷砖？

示例 1：
```
输入：n = 2, m = 3
输出：3
解释：3 块地砖就可以铺满卧室。
     2 块 1x1 地砖
     1 块 2x2 地砖
```
示例 2：
```
输入：n = 5, m = 8
输出：5
```
示例 3：
```
输入：n = 11, m = 13
输出：6
```
提示：
```
1 <= n <= 13
1 <= m <= 13
```
## 解法
### 思路

### 代码
```java

```