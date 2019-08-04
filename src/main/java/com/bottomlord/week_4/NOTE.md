# LeetCode_1030_距离顺序排列矩阵单元格
## 题目
给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。

另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。

返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）

示例 1：
```
输入：R = 1, C = 2, r0 = 0, c0 = 0
输出：[[0,0],[0,1]]
解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
```
示例 2：
```
输入：R = 2, C = 2, r0 = 0, c0 = 1
输出：[[0,1],[0,0],[1,1],[1,0]]
解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
[[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
```
示例 3：
```
输入：R = 2, C = 3, r0 = 1, c0 = 2
输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
```
提示：
```
1 <= R <= 100
1 <= C <= 100
0 <= r0 < R
0 <= c0 < C
```
## 解法一
### 思路
- 获取所有的坐标
- 根据题意排序
### 代码
```java
class Solution {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] ans = new int[R * C][2];
        int index = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                ans[index][0] = i;
                ans[index][1] = j;
                index++;
            }
        }

        Arrays.sort(ans, (int[] x, int[] y) -> Math.abs(x[0] - r0) + Math.abs(x[1] - c0) - Math.abs(y[0] - r0) - Math.abs(y[1] - c0));
        return ans;
    }
}
```
## 解法二
### 思路
- 把矩阵分成四个象限，确定每个象限的区间
    - 第一象限：
        - 行：<= r0
        - 列：< c0
    - 第二象限：
        - 行：< r0
        - 列：>= c0
    - 第三象限：
        - 行：< r0
        - 列：>= c0
    - 第四象限：
        - 行：>= r0
        - 列：> c0
- 每个象限的遍历规则就是循环的每一次遍历所有曼哈顿距离相等的点，而这个点可以看成一个反比例一元一次函数。
- 算出最大的曼哈顿距离max，然后从1开始循环遍历到max，删去超出矩阵边界的点，然后把符合要求的点记录在list中
### 代码
```java
class Solution {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int max = Math.max(Math.max(r0 + c0, R - r0 + c0), Math.max(C - c0 + R - r0, C - c0 + r0));
        List<int[]> ans = new ArrayList<>();
        ans.add(new int[]{r0, c0});

        int x, y;
        for (int i = 1; i <= max; i++) {
            for (int j = 0; j < i; j++) {
                x = r0 - i + j; y = c0 - j;
                if (x >= 0 && y >= 0) {
                    ans.add(new int[]{x, y});
                }

                x = r0 + j; y = c0 - i + j;
                if (x < R && y >= 0) {
                    ans.add(new int[]{x, y});
                }

                x = r0 + i - j; y = c0 + j;
                if (x < R && y < C) {
                    ans.add(new int[]{x, y});
                }

                x = r0 - j; y = c0 + i - j;
                if(x >= 0 && y < C) {
                    ans.add(new int[]{x, y});
                }
            }
        }

        return ans.toArray(new int[0][0]);
    }
}
```
# LeetCode_389_找不同
## 题目
给定两个字符串 s 和 t，它们只包含小写字母。

字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。

请找出在 t 中被添加的字母。

示例:
```
输入：
s = "abcd"
t = "abcde"

输出：
e
```
解释：
```
'e' 是那个被添加的字母。
```
## 解法一
### 思路
- 两个字符串排序
- 遍历字符串，把长的字符串中不同的那个返回
### 代码
```java
class Solution {
    public char findTheDifference(String s, String t) {
        char[] ss = s.toCharArray();
        char[] ts = t.toCharArray();

        Arrays.sort(ss);
        Arrays.sort(ts);
        
        for (int i = 0; i < ss.length; i++) {
            if (ss[i] != ts[i]) {
                return ts[i];
            }
        }
        
        return ts[ts.length - 1];
    }
}
```
## 解法二
### 思路
用桶的思路，空间换时间
- 新建一个26长度的桶
- 下标对应字符，元素对应个数
- 遍历一遍短的字符串，设置好对应字符的个数
- 遍历长的字符串时，一次对对应的元素做--操作，如果遇到需要相减的元素为0时，返回该下标对应的字符
### 代码
```java
class Solution {
    public char findTheDifference(String s, String t) {
        int[] dict = new int[26];
        
        for (char c : s.toCharArray()) {
            dict[c - 'a']++;
        }
        
        for (char c : t.toCharArray()) {
            if (dict[c - 'a'] == 0) {
                return c;
            }
            
            dict[c - 'a']--;
        }
        
        return ' ';
    }
}
```
## 解法三
### 思路
使用异或，通过其相同字符异或为零，且异或符合交换律的特性，循环异或另个字符数组，返回最后的结果即可。
### 代码
```java
public class Solution {
    public char findTheDifference(String s, String t) {
        int ans = 0;

        for (char c : s.toCharArray()) {
            ans ^= c;
        }

        for (char c : t.toCharArray()) {
            ans ^= c;
        }

        return (char) ans;
    }
}

```
# LeetCode_812_最大三角形面积
## 题目
给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。

示例:
```
输入: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
输出: 2
解释: 
这五个点如下图所示。组成的橙色三角形是最大的，面积为2。
```
## 解法
### 思路一
三层for循环，获取所有可能的边长，并通过海伦公式计算三角形的面积，最终返回面积最大的结果
- 需要避免所有边在一条直线上的情况
- 需要避免虚数的情况
### 代码
```java
class Solution {
    public double largestTriangleArea(int[][] points) {
        double max = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                for (int k = 0; k < points.length; k++) {
                    double a = Math.sqrt(Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));
                    double b = Math.sqrt(Math.pow(points[j][0] - points[k][0], 2) + Math.pow(points[j][1] - points[k][1], 2));
                    double c = Math.sqrt(Math.pow(points[k][0] - points[i][0], 2) + Math.pow(points[k][1] - points[i][1], 2));

                    if (a + b == c || b + c == a || a + c == b) {
                        continue;
                    }

                    double p = (a + b + c) /2;
                    double area = Math.sqrt(p * (p - a) * (p - b) * (p - c));
                    
                    if (Double.isNaN(area)) {
                        continue;
                    }
                    
                    max = Math.max(area, max);
                }
            }
        }
        
        return max;
    }
}
```
## 优化代码
### 思路
简化循环体中的计算过程，使用公式
```math
A = 1/2 * [ x1(y2-y3) + x2(y3-y1) + x3(y1-y2) ] 
```
### 代码
```java
public class Solution {
    public double largestTriangleArea(int[][] points) {
        double ans = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    ans = Math.max(ans, 0.5 * Math.abs(points[i][0] * (points[j][1] - points[k][1]) + points[j][0] * (points[k][1] - points[i][1]) + points[k][0] * (points[i][1] - points[j][1])));
                }
            }
        }
        return ans;
    }
}
```
# LeetCode_119_杨辉三角II
## 题目
给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。

在杨辉三角中，每个数是它左上方和右上方的数的和。

示例:
```
输入: 3
输出: [1,3,3,1]
```
## 解法
### 思路
- 第k行，在list中代表下标k
- 每一行有k + 1个元素
- 每一行的下标0和下标k的元素是1

过程：
- for循环k次，构建杨辉三角，并返回三角的最后一行
### 代码
```java
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> list = new ArrayList<>(rowIndex + 1);
        
        for (int i = 0; i < rowIndex + 1; i++) {
            List<Integer> row = new ArrayList<>(i + 1);
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                    continue;
                }
                
                row.add(list.get(i - 1).get(j - 1) + list.get(i - 1).get(j));
            }
            list.add(row);
        }
        
        return list.get(rowIndex);
    }
}
```
## 解法二
### 思路
因为行数最大为33，所以枚举。。。
### 代码
```java
class Solution {
    static Integer[][] list = new Integer[][]{
            {1},
            {1,1},
            {1,2,1},
            {1,3,3,1},
            {1,4,6,4,1},
            {1,5,10,10,5,1},
            {1,6,15,20,15,6,1},
            {1,7,21,35,35,21,7,1},
            {1,8,28,56,70,56,28,8,1},
            {1,9,36,84,126,126,84,36,9,1},
            {1,10,45,120,210,252,210,120,45,10,1},
            {1,11,55,165,330,462,462,330,165,55,11,1},
            {1,12,66,220,495,792,924,792,495,220,66,12,1},
            {1,13,78,286,715,1287,1716,1716,1287,715,286,78,13,1},
            {1,14,91,364,1001,2002,3003,3432,3003,2002,1001,364,91,14,1},
            {1,15,105,455,1365,3003,5005,6435,6435,5005,3003,1365,455,105,15,1},
            {1,16,120,560,1820,4368,8008,11440,12870,11440,8008,4368,1820,560,120,16,1},
            {1,17,136,680,2380,6188,12376,19448,24310,24310,19448,12376,6188,2380,680,136,17,1},
            {1,18,153,816,3060,8568,18564,31824,43758,48620,43758,31824,18564,8568,3060,816,153,18,1},
            {1,19,171,969,3876,11628,27132,50388,75582,92378,92378,75582,50388,27132,11628,3876,969,171,19,1},
            {1,20,190,1140,4845,15504,38760,77520,125970,167960,184756,167960,125970,77520,38760,15504,4845,1140,190,20,1},
            {1,21,210,1330,5985,20349,54264,116280,203490,293930,352716,352716,293930,203490,116280,54264,20349,5985,1330,210,21,1},
            {1,22,231,1540,7315,26334,74613,170544,319770,497420,646646,705432,646646,497420,319770,170544,74613,26334,7315,1540,231,22,1},
            {1,23,253,1771,8855,33649,100947,245157,490314,817190,1144066,1352078,1352078,1144066,817190,490314,245157,100947,33649,8855,1771,253,23,1},
            {1,24,276,2024,10626,42504,134596,346104,735471,1307504,1961256,2496144,2704156,2496144,1961256,1307504,735471,346104,134596,42504,10626,2024,276,24,1},
            {1,25,300,2300,12650,53130,177100,480700,1081575,2042975,3268760,4457400,5200300,5200300,4457400,3268760,2042975,1081575,480700,177100,53130,12650,2300,300,25,1},
            {1,26,325,2600,14950,65780,230230,657800,1562275,3124550,5311735,7726160,9657700,10400600,9657700,7726160,5311735,3124550,1562275,657800,230230,65780,14950,2600,325,26,1},
            {1,27,351,2925,17550,80730,296010,888030,2220075,4686825,8436285,13037895,17383860,20058300,20058300,17383860,13037895,8436285,4686825,2220075,888030,296010,80730,17550,2925,351,27,1},
            {1,28,378,3276,20475,98280,376740,1184040,3108105,6906900,13123110,21474180,30421755,37442160,40116600,37442160,30421755,21474180,13123110,6906900,3108105,1184040,376740,98280,20475,3276,378,28,1},
            {1,29,406,3654,23751,118755,475020,1560780,4292145,10015005,20030010,34597290,51895935,67863915,77558760,77558760,67863915,51895935,34597290,20030010,10015005,4292145,1560780,475020,118755,23751,3654,406,29,1},
            {1,30,435,4060,27405,142506,593775,2035800,5852925,14307150,30045015,54627300,86493225,119759850,145422675,155117520,145422675,119759850,86493225,54627300,30045015,14307150,5852925,2035800,593775,142506,27405,4060,435,30,1},
            {1,31,465,4495,31465,169911,736281,2629575,7888725,20160075,44352165,84672315,141120525,206253075,265182525,300540195,300540195,265182525,206253075,141120525,84672315,44352165,20160075,7888725,2629575,736281,169911,31465,4495,465,31,1},
            {1,32,496,4960,35960,201376,906192,3365856,10518300,28048800,64512240,129024480,225792840,347373600,471435600,565722720,601080390,565722720,471435600,347373600,225792840,129024480,64512240,28048800,10518300,3365856,906192,201376,35960,4960,496,32,1},
            {1,33,528,5456,40920,237336,1107568,4272048,13884156,38567100,92561040,193536720,354817320,573166440,818809200,1037158320,1166803110,1166803110,1037158320,818809200,573166440,354817320,193536720,92561040,38567100,13884156,4272048,1107568,237336,40920,5456,528,33,1}
    };
    
    public List<Integer> getRow(int rowIndex) {
        return new ArrayList<>(Arrays.asList(list[rowIndex]));
    }
}
```
## 解法三
### 思路
递归，参数中带上一层的list和当前的层数
### 代码
```java
class Solution {
    public List<Integer> getRow(int rowIndex) {
        return rescurse(new ArrayList<>(), 0, rowIndex);
    }

    private List<Integer> rescurse(List<Integer> preList, int row, int rowIndex) {
        if (row > rowIndex) {
            return preList;
        }

        List<Integer> curList = new ArrayList<>(row + 1);
        for (int i = 0; i <= row; i++) {
            if (i == 0 || i == row) {
                curList.add(1);
                continue;
            }

            curList.add(preList.get(i - 1) + preList.get(i));
        }

        return rescurse(curList, row + 1, rowIndex);
    }
}
```
# LeetCode_884_两句话中的不常见单词
## 题目
给定两个句子 A 和 B 。 （句子是一串由空格分隔的单词。每个单词仅由小写字母组成。）

如果一个单词在其中一个句子中只出现一次，在另一个句子中却没有出现，那么这个单词就是不常见的。

返回所有不常用单词的列表。

您可以按任何顺序返回列表。

示例 1：
```
输入：A = "this apple is sweet", B = "this apple is sour"
输出：["sweet","sour"]
```
示例 2：
```
输入：A = "apple apple", B = "banana"
输出：["banana"]
```
提示：
```
0 <= A.length <= 200
0 <= B.length <= 200
A 和 B 都只包含空格和小写字母。
```
## 解法一
### 思路
使用散列表计数，找到值为1的字符串并放入数组，最终返回
### 代码
```java
class Solution {
    public String[] uncommonFromSentences(String A, String B) {
        Map<String, Integer> map = new HashMap<>();
        String[] as = A.split(" ");
        String[] bs = B.split(" ");

        compute(as, map);
        compute(bs, map);

        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            if (entry.getValue() == 1) {
                ans.add(entry.getKey());
            }
        }

        return ans.toArray(new String[0]);
    }

    private void compute(String[] s, Map<String, Integer> map) {
        for (String a : s) {
            if (map.containsKey(a)) {
                map.computeIfPresent(a, (k, v) -> v += 1);
            } else {
                map.put(a, 1);
            }
        }
    }
}
```
## 优化代码
### 思路
使用Map的getOrDefault方法来处理累加的动作，效率惊人
### 代码
```java
class Solution {
    public String[] uncommonFromSentences(String A, String B) {
        Map<String, Integer> map = new HashMap<>();
        for (String a : A.split(" ")) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }

        for (String b : B.split(" ")) {
            map.put(b, map.getOrDefault(b, 0) + 1);
        }

        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            if (entry.getValue() == 1) {
                ans.add(entry.getKey());
            }
        }

        return ans.toArray(new String[0]);
    }
}
```
# LeetCode_1137_第N个泰波那契数
## 题目
泰波那契序列 Tn 定义如下： 

T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2

给你整数 n，请返回第 n 个泰波那契数 Tn 的值。

示例 1：
```
输入：n = 4
输出：4
解释：
T_3 = 0 + 1 + 1 = 2
T_4 = 1 + 1 + 2 = 4
```
示例 2：
```
输入：n = 25
输出：1389537
```
提示：
```
0 <= n <= 37
答案保证是一个 32 位整数，即 answer <= 2^31 - 1。
```
## 解法一
### 思路
- 动态规划 T(N) = T(N - 1) + T(N - 2) + T(N - 3)
- 记忆化搜索
### 代码
```java
class Solution {
    public int tribonacci(int n) {
        int[] memo = new int[n + 1];
        return rescurse(memo, n);
    }

    private int rescurse(int[] memo, int n) {
        if (n == 1 || n == 2) {
            return 1;
        }

        if (n == 0) {
            return 0;
        }

        if (memo[n] == 0) {
            memo[n] = rescurse(memo, n - 1) + rescurse(memo, n - 2) + rescurse(memo, n - 3);
        }
        
        return memo[n];
    }
}
```
## 解法二
### 思路
从3开始循环遍历到n，循环过程中更新3个数，结果存在第3个数上，循环结束返回第三个数。
### 代码
```java
class Solution {
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        int n0 = 0, n1 = 1, n2 = 1;
        for (int i = 3; i <= n; i++) {
            int tmp0 = n1, tmp1 = n2;
            n2 = n0 + n1 + n2;
            n0 = tmp0;
            n1 = tmp1;
        }
        
        return n2;
    }
}
```
# LeetCode_1046_最后一块石头的重量
## 题目
有一堆石头，每块石头的重量都是正整数。

每一回合，从中选出两块最重的石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
```
如果 x == y，那么两块石头都会被完全粉碎；
如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
```
提示：
```
1 <= stones.length <= 30
1 <= stones[i] <= 1000
```
## 解法一
### 思路
- 循环数组，将数字放入大顶堆
- 大顶堆大小如果大于1，就将堆顶的元素相减：
    - 如果结果为0，不处理
    - 如果结果不为0，将差放入大顶堆
- 返回0或者最后的一个元素
### 代码
```java
class Solution {
    public int lastStoneWeight(int[] stones) {
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : stones) {
            queue.offer(num);
        }

        while (queue.size() > 1) {
            int num = queue.poll() - queue.poll();
            if (num != 0) {
                queue.offer(num);
            }
        }

        return queue.isEmpty() ? 0 : queue.poll();
    }
}
```
## 解法二
### 思路
遍历数组，每次都至少处理掉一块石头，在处理的过程中：
1. 对数组进行排序
2. 将下标最大和第二大的元素相减
3. 将差放入元素最大的位置，0放在第二大的位置
### 代码
```java
class Solution {
    public int lastStoneWeight(int[] stones) {
        int len = stones.length;
        for (int i = 0; i < len - 1; i++) {
            Arrays.sort(stones);
            int num = stones[len - 1] - stones[len - 2];
            stones[len - 1] = num;
            stones[len - 2] = 0;
        }
        
        return stones[len - 1];
    }
}
```
# LeetCode_9_回文数
## 题目
判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

示例 1:
```
输入: 121
输出: true
```
示例 2:
```
输入: -121
输出: false
解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
```
示例 3:
```
输入: 10
输出: false
解释: 从右向左读, 为 01 。因此它不是一个回文数。
```
## 解法一
### 思路
转成字符串，通过头尾指针的移动来判断是否字符相等
### 代码
```java
class Solution {
    public boolean isPalindrome(int x) {
        String s = Integer.toString(x);
        char[] cs = s.toCharArray();
        int head =0, tail = cs.length - 1;
        while (head < tail) {
            if (cs[head++] != cs[tail--]) {
                return false;
            }
        }
        
        return true;
    }
}
```
## 解法二
### 思路
通过截取数字，并生成相反数的方式来判断
### 代码
```java
class Solution {
    public boolean isPalindrome(int x) {
        if (x == 0) {
            return true;
        }
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        if (x == x % 10) {
            return true;
        }

        int reserve = 0, cur = x;
        while (cur >= 10) {
            int digit = cur % 10;
            reserve = reserve * 10 + digit;
            cur /= 10;
            if (reserve == cur || reserve == cur / 10) {
                return true;
            }
        }
        return false;
    }
}
```
## 优化代码
### 思路
当cur截取不再大于reserve的时候，其实就可以判断是否是回文数了，因为如果是的话，两者是相等的，或者如果位数是奇数，那就让reserve除以10后也是相等的
### 代码
```java
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        if (x == 0) {
            return true;
        }

        if (x % 10 == 0) {
            return false;
        }

        int reserve = 0, cur = x;
        while (cur > reserve) {
            reserve = 10 * reserve + cur % 10;
            cur = cur / 10;
        }
        return (reserve == cur || cur == reserve / 10);
    }
}
```
# LeetCode_824_山羊拉丁文
## 题目
给定一个由空格分割单词的句子 S。每个单词只包含大写或小写字母。

我们要将句子转换为 “Goat Latin”（一种类似于 猪拉丁文 - Pig Latin 的虚构语言）。

山羊拉丁文的规则如下：
```
如果单词以元音开头（a, e, i, o, u），在单词后添加"ma"。
例如，单词"apple"变为"applema"。
```
```
如果单词以辅音字母开头（即非元音字母），移除第一个字符并将它放到末尾，之后再添加"ma"。
例如，单词"goat"变为"oatgma"。
```
```
根据单词在句子中的索引，在单词最后添加与索引相同数量的字母'a'，索引从1开始。
例如，在第一个单词后添加"a"，在第二个单词后添加"aa"，以此类推。
```
返回将 S 转换为山羊拉丁文后的句子。

示例 1:
```
输入: "I speak Goat Latin"
输出: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
```
示例 2:
```
输入: "The quick brown fox jumped over the lazy dog"
输出: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
```
说明:
```
S 中仅包含大小写字母和空格。单词间有且仅有一个空格。
1 <= S.length <= 150。
```
## 解法
### 思路
- split成字符串数组
- 遍历数组，根据首个字符是否是元音字符做不同的处理
- 将处理好的字符串放回数组
- 用String.join方法处理数组并返回
### 代码
```java
class Solution {
    public String toGoatLatin(String S) {
        if (S == null || S.length() == 0) {
            return "";
        }
        
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');

        String[] ss = S.split(" ");
        for (int i = 0; i < ss.length; i++) {
            String str = ss[i];
            StringBuilder sb = new StringBuilder();
            if (!set.contains(str.charAt(0))) {
                sb.append(str.substring(1)).append(str.charAt(0));
            } else {
                sb.append(str);
            }

            sb.append("ma");
            for (int j = 0; j < i + 1; j++) {
                sb.append("a");
            }

            ss[i] = sb.toString();
        }
        
        return String.join(" ", ss);
    }
}
```
# LeetCode_191_位1的个数
## 题目
编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。

示例 1：
```
输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
```
示例 2：
```
输入：00000000000000000000000010000000
输出：1
解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
```
示例 3：
```
输入：11111111111111111111111111111101
输出：31
解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
```
提示：
```
请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
```
## 解法一
### 思路
- 转成二进制字符串
- 算出字符串长度
- 去除所有1的字符，算出长度
- 返回两者的差
### 代码
```java
public class Solution {
    public int hammingWeight(int n) {
        String oldStr = Integer.toBinaryString(n);
        int oldLen = oldStr.length();
        String str = oldStr.replaceAll("1", "");
        return oldLen - str.length();
    }
}
```
## 解法二
### 思路
使用位运算
```
n & (n - 1)
```
如上位运算可以将最低位的1置为0
### 代码
```java
public class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }
}
```
# LeetCode_788_旋转数字
## 题目
我们称一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，且和 X 不同的数。要求每位数字都要被旋转。

如果一个数的每位数字被旋转以后仍然还是一个数字， 则这个数是有效的。0, 1, 和 8 被旋转后仍然是它们自己；2 和 5 可以互相旋转成对方；6 和 9 同理，除了这些以外其他的数字旋转以后都不再是有效的数字。

现在我们有一个正整数 N, 计算从 1 到 N 中有多少个数 X 是好数？

示例:
```
输入: 10
输出: 4
解释: 
在[1, 10]中有四个好数： 2, 5, 6, 9。
注意 1 和 10 不是好数, 因为他们在旋转之后不变。
```
注意:
```
N 的取值范围是 [1, 10000]。
```
## 解法一
### 思路
- 遍历从0到N的数字
- 把数字转成字符串
- 判断：
    - 包含3，4，7不是好数字
    - 只包含0，1，8不是好数字
- 计数，并在循环结束后返回
### 代码
```java
class Solution {
    public int rotatedDigits(int N) {
        int count = 0;
        for (int i = 0; i <= N; i++) {
            String str = Integer.toString(i);
            if (str.contains("3") || str.contains("4") || str.contains("7")) {
                continue;
            }

            if (!str.contains("2") && !str.contains("5") && !str.contains("6") && !str.contains("9")) {
                continue;
            }

            count++;
        }
        return count;
    }
}
```
## 优化代码
### 思路
不完全使用StringAPI，通过对数字的操作来判断
### 代码
```java
class Solution {
    public int rotatedDigits(int N) {
        int ans = 0;
        
        for (int i = 1; i <= N; i++) {
            if (judge(i)) {
                ans++;
            }
        }
        
        return ans;
    }
    
    private boolean judge(int num) {
        String rule = "0182569";
        boolean ok = false;
        
        while (num != 0) {
            int m = num % 10;
            
            if (rule.indexOf((char) (m + '0')) < 0) {
                return false;
            }
            
            if (m == 2 || m == 5 || m == 6 || m == 9) {
                ok = true;
            }
            
            num /= 10;
        }
        
        return ok;
    }
}
```
# LeetCode_538_把二叉搜索树转换成累加树
## 题目
给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。

例如：
```
输入: 二叉搜索树:
              5
            /   \
           2     13

输出: 转换为累加树:
             18
            /   \
          20     13
```
## 解法
### 思路
dfs逆中序遍历
- 递归函数的参数
    - 遍历到的节点
    - 累加的sum
- 过程逻辑
    - 退出条件：当前遍历到的节点为空，返回sum值
    - 过程是逆中序遍历
        1. 带着sum去遍历右子树，返回遍历后累加得到的sum
        2. 暂存当前节点的值，用于累加sum
        3. 将从右子树返回的sum累加到当前节点的值
        4. 将sum与步骤2暂存的值进行累加
        5. 带着在当前层累加好的sum去遍历左子树
    - 返回当前层得到的更新好的sum
### 代码
```java
class Solution {
    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            dfs(root, 0);
        }
        return root;
    }

    private int dfs(TreeNode node, int sum) {
        if (node == null) {
            return sum;
        }
        
        sum = dfs(node.right, sum);
        
        int nodeValue = node.val;
        node.val += sum;
        sum += nodeValue;
        
        sum = dfs(node.left, sum);
        
        return sum;
    }
}
```
# LeetCode_1009_十进制整数的反码
## 题目
每个非负整数 N 都有其二进制表示。例如， 5 可以被表示为二进制 "101"，11 可以用二进制 "1011" 表示，依此类推。注意，除 N = 0 外，任何二进制表示中都不含前导零。

二进制的反码表示是将每个 1 改为 0 且每个 0 变为 1。例如，二进制数 "101" 的二进制反码为 "010"。

给定十进制数 N，返回其二进制表示的反码所对应的十进制整数。

示例 1：
```
输入：5
输出：2
解释：5 的二进制表示为 "101"，其二进制反码为 "010"，也就是十进制中的 2 。
```
示例 2：
```
输入：7
输出：0
解释：7 的二进制表示为 "111"，其二进制反码为 "000"，也就是十进制中的 0 。
```
示例 3：
```
输入：10
输出：5
解释：10 的二进制表示为 "1010"，其二进制反码为 "0101"，也就是十进制中的 5 。
```
提示：
```
0 <= N < 10^9
```
## 解法
### 思路
- 获得前导零的个数x
- 左移位x后取反，然后再右移位x就获得结果
- 注意N为0的特殊情况
### 代码
```java
class Solution {
    public int bitwiseComplement(int N) {
        if (N == 0) {
            return 1;
        }
        
        int x = 32, n = N;
        while (n > 0) {
            x--;
            n >>= 1;
        }

        return ~(N << x) >> x;
    }
}
```
# LeetCode_303_区域和检索_数组不可变
## 题目
给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。

示例：
```
给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
```
说明:
```
你可以假设数组不可变。
会多次调用 sumRange 方法。
```
## 解法一
### 思路
遍历数组累加
### 代码
```java
class NumArray {
    private int[] nums;
    public NumArray(int[] nums) {
        this.nums = nums;
    }

    public int sumRange(int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += nums[k];
        }
        return sum;
    }
}
```
## 解法二
### 思路
i与j之间元素的和可以表达为如下：
```
sum(i,j) = sum(j+1) - sum(i);
```
那么只要求出每一位下标的累加值就可以了
### 代码
```java
class NumArray {
    private int[] sum;
    public NumArray(int[] nums) {
        sum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }
}
```
# LeetCode_27_移除元素
## 题目
给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

示例 1:
```
给定 nums = [3,2,2,3], val = 3,

函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。

你不需要考虑数组中超出新长度后面的元素。
```
示例 2:
```
给定 nums = [0,1,2,2,3,0,4,2], val = 2,

函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。

注意这五个元素可为任意顺序。

你不需要考虑数组中超出新长度后面的元素。
```
说明:
```
为什么返回数值是整数，但输出的答案是数组呢?

请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

你可以想象内部操作如下:

// nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
int len = removeElement(nums, val);

// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
```
## 解法
### 思路
- 头尾指针
- 头指针遍历数组，如果碰到与val相等的值就和尾指针元素互换，尾指针向头部移动
- 同时检查头指针元素是否还是与val相等，如果是就重复如上操作，直到不等于val，头指针向尾部移动
- 头尾指针想交时返回头指针值
### 代码
```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int head = 0, tail = nums.length - 1;
        while (head <= tail) {
            if (nums[head] == val) {
                if (head != tail) {
                    nums[head] ^= nums[tail];
                    nums[tail] ^= nums[head];
                    nums[head] ^= nums[tail];
                }
                tail--;
            }

            if (nums[head] != val) {
                head++;
            }
        }

        return head;
    }
}
```
# LeetCode_976_三角形的最大周长
## 题目
给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。

如果不能形成任何面积不为零的三角形，返回 0。

示例 1：
```
输入：[2,1,2]
输出：5
```
示例 2：
```
输入：[1,2,1]
输出：0
```
示例 3：
```
输入：[3,2,3,4]
输出：10
```
示例 4：
```
输入：[3,6,2,3]
输出：8
```
提示：
```
3 <= A.length <= 10000
1 <= A[i] <= 10^6
```
## 解法
### 思路
- 排序数组
- 从最大元素开始判断其与其前两个元素之间是否符合小的两个元素相加大于最大的元素
- 如果不匹配，移动指针再次判断
- 返回匹配的3个元素的和
### 代码
```java
class Solution {
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; i--) {
            if (A[i - 2] + A[i - 1] > A[i]) {
                return A[i - 2] + A[i - 1] + A[i];
            }
        }
        return 0;
    }
}
```
# LeetCode_706_设计哈希映射
## 题目
不使用任何内建的哈希表库设计一个哈希映射

具体地说，你的设计应该包含以下的功能
```
put(key, value)：向哈希映射中插入(键,值)的数值对。如果键对应的值已经存在，更新这个值。
get(key)：返回给定的键所对应的值，如果映射中不包含这个键，返回-1。
remove(key)：如果映射中存在这个键，删除这个数值对。
```
示例：
```
MyHashMap hashMap = new MyHashMap();
hashMap.put(1, 1);          
hashMap.put(2, 2);         
hashMap.get(1);            // 返回 1
hashMap.get(3);            // 返回 -1 (未找到)
hashMap.put(2, 1);         // 更新已有的值
hashMap.get(2);            // 返回 1 
hashMap.remove(2);         // 删除键为2的数据
hashMap.get(2);            // 返回 -1 (未找到) 
```
注意：
```
所有的值都在 [1, 1000000]的范围内。
操作的总数目在[1, 10000]范围内。
不要使用内建的哈希库。
```
## 解法
### 思路
使用空间换时间，用桶数组。
- 初始化数组所有值为-1
- key是数组下标
- value是数组key下标对应的元素
- remove时将key下标元素置为-1
### 代码
```java
class MyHashMap {
    int[] bucket;
    public MyHashMap() {
        this.bucket = new int[1000001];
        Arrays.fill(this.bucket, -1);
    }

    public void put(int key, int value) {
        this.bucket[key] = value;
    }

    public int get(int key) {
        return this.bucket[key];
    }

    public void remove(int key) {
        this.bucket[key] = -1;
    }
}
```
## 优化代码
### 思路
省去上一步的fill -1的动作，用包装类代替，然后判断是否为null来返回
### 代码
```java
class MyHashMap {
    Integer[] bucket;
    public MyHashMap() {
        this.bucket = new Integer[1000001];
    }

    public void put(int key, int value) {
        this.bucket[key] = value;
    }

    public int get(int key) {
        return this.bucket[key] != null ? this.bucket[key] : -1;
    }

    public void remove(int key) {
        this.bucket[key] = -1;
    }
}
```
# LeetCode_953_验证外星语词典
## 题目
某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。

给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。

示例 1：
```
输入：words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
输出：true
解释：在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。
```
示例 2：
```
输入：words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
输出：false
解释：在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。
```
示例 3：
```
输入：words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
输出：false
解释：当前三个字符 "app" 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，因为 'l' > '∅'，其中 '∅' 是空白字符，定义为比任何其他字符都小（更多信息）。
```
提示：
```
1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
在 words[i] 和 order 中的所有字符都是英文小写字母。
```
## 解法一
### 思路
- 根据order字符串生成下标对应字符，元素对应顺序值的字典
- 循环判断两个字符串的字符顺序
### 代码
```java
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        if (words.length == 0) {
            return true;
        }
        
        char[] orderC = order.toCharArray();
        int[] dict = new int[26];

        for (int i = 0; i < orderC.length; i++) {
            dict[orderC[i] - 'a'] = i;
        }

        List<List<Integer>> buffer = new ArrayList<>();
        for (int i = 1; i < words.length; i++) {
            List<Integer> pre, cur;
            if (i - 1 < buffer.size()) {
                pre = buffer.get(i - 1);
            } else {
                pre = new ArrayList<>();
                for (char c : words[i - 1].toCharArray()) {
                    pre.add(dict[c - 'a']);
                }
                buffer.add(pre);
            }
            
            cur = new ArrayList<>();
            for (char c: words[i].toCharArray()) {
                cur.add(dict[c - 'a']);
            }
            
            int len = pre.size() <= cur.size() ? pre.size() : cur.size();
            boolean ok = false;
            for (int j = 0; j < len; j++) {
                if (pre.get(j) > cur.get(j)) {
                    return false;
                }
                if (pre.get(j).equals(cur.get(j))) {
                    continue;
                }
                ok = true;
                break;
            }
                        
            if (!ok && pre.size() > cur.size()) {
                return false;
            }
            
            buffer.add(cur);
        }
        return true;
    }
}
```
## 代码优化
### 思路
把状态区分为三种：
- 前一个单词的字符小为true
- 前一个单词的字符大为false
- 字符比较完相同的情况下：
    - 前一个字符串相等或更短为true
    - 前一个字符串长为false
### 代码
```java
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] dic = new int[26];
        for (int i = 0; i < 26; i++) {
            dic[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < words.length - 1; i++) {
            if (!judge(words[i], words[i + 1], dic)) {
                return false;
            }
        }
        return true;
    }

    private boolean judge(String s1, String s2, int[] dic) {
        int minLen = s1.length() <= s2.length() ? s1.length() : s2.length();
        for (int i = 0; i < minLen; i++) {
            if (dic[s1.charAt(i) - 'a'] < dic[s2.charAt(i) - 'a']) {
                return true;
            }
            if (dic[s1.charAt(i) - 'a'] > dic[s2.charAt(i) - 'a']) {
                return false;
            }
        }
        return s1.length() <= s2.length();
    }
}
```
# LeetCode_705_设计哈希集合
## 题目
不使用任何内建的哈希表库设计一个哈希集合

具体地说，你的设计应该包含以下的功能
```
add(value)：向哈希集合中插入一个值。
contains(value) ：返回哈希集合中是否存在这个值。
remove(value)：将给定值从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
```
示例:
```
MyHashSet hashSet = new MyHashSet();
hashSet.add(1);         
hashSet.add(2);         
hashSet.contains(1);    // 返回 true
hashSet.contains(3);    // 返回 false (未找到)
hashSet.add(2);          
hashSet.contains(2);    // 返回 true
hashSet.remove(2);          
hashSet.contains(2);    // 返回  false (已经被删除)
```
注意：
```
所有的值都在 [1, 1000000]的范围内。
操作的总数目在[1, 10000]范围内。
不要使用内建的哈希集合库。
```
## 解法
### 思路
桶数组，用空间换时间
- 下标代表key，元素代表value
- remove的时候将元素置为1
### 代码
```java
class MyHashSet {
    int[] bucket;
    public MyHashSet() {
        bucket = new int[1000001];
    }

    public void add(int key) {
        bucket[key] = 1;
    }

    public void remove(int key) {
        bucket[key] = 0;
    }

    public boolean contains(int key) {
        return bucket[key] == 1;
    }
}
```
# LeetCode_122_买卖股票的最佳时机II
## 题目
给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。

注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:
```
输入: [7,1,5,3,6,4]
输出: 7
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
```
示例 2:
```
输入: [1,2,3,4,5]
输出: 4
解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
```
示例 3:
```
输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
```
## 失败的解法
### 思路
递归函数的参数:
- prices：每天价格列表
- day：递归当前这层所代表的天数，也就是prices的下标
- has：有没有股票的标识符
- sum：当前天的资金情况
- ansList：递归退出时，记录所有可能的sum

把每一天能做的事情分成三种情况：
- 没股票，可以卖
- 有股票，可以买
- 不买也不卖

更新sum并分不同情况递归到下一层
### 代码
```java
class Solution {
    public int maxProfit(int[] prices) {
        List<Integer> ansList = new ArrayList<>();
        rescurse(prices, 0, false, 0, ansList);
        return Collections.max(ansList);
    }

    private void rescurse(int[] prices, int day, boolean has, int sum, List<Integer> ansList) {
        if (day == prices.length) {
            ansList.add(sum);
        }

        if (!has) {
            rescurse(prices, day + 1, true, sum - prices[day], ansList);
        } else {
            rescurse(prices, day + 1, false, sum + prices[day], ansList);
        }

        rescurse(prices, day + 1, has, sum, ansList);
    }
}
```
但这种解法超出了时间限制
## 解法
### 思路
找到价格列表中临近的两个波峰波谷，将它们的和累加，就能找到最大值
### 代码
```java
class Solution {
    public int maxProfit(int[] prices) {
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            peak = prices[i];
            maxprofit += peak - valley;
           }
        return maxprofit;
    }
}
```
## 解法二
### 思路
只要在遍历价格列表的过程中，如果一个数比前一个数的值大，就把它们的差值累加到结果中，也能得到最大的累加值
### 代码
```java
class Solution {
    public int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxprofit += prices[i] - prices[i - 1];
            }
        }
        return maxprofit;
    }
}
```
# LeetCode_202_快乐数
## 题目
编写一个算法来判断一个数是不是“快乐数”。

一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。

示例: 
```
输入: 19
输出: true
解释: 
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
```
## 解法一
### 思路
之所以会进入无限循环是因为重复的得到一样的平方和，所以，只要记录平方和是否在以前出现过，就能判断是否会出现死循环。
- 使用一个set来记录平方和
- 不断计算平方和，直到如下两种情况退出：
    - set中已存在这个平方和
    - 平方和为1
### 代码
```java
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (true) {
            int sum = 0;
            while (n > 0) {
                sum += Math.pow(n % 10, 2);
                n /= 10;
            }
            
            if (sum == 1) {
                return true;
            }
            
            if (set.contains(sum)) {
                return false;
            }
            set.add(sum);
            n = sum;
        }
    }
}
```
## 解法二
### 思路
在本题的题意下，所有数字，都会进入1，4，3的无限循环中，所以只要判断是否是1或4或3就可以了
### 代码
```java
class Solution {
    public boolean isHappy(int n) {
        while (n != 1) {
            int sum = 0;
            while (n > 0) {
                sum += Math.pow(n % 10, 2);
                n /= 10;
            }
            if (sum == 3 || sum == 4) {
                return false;
            }
            n = sum;
        }
        return true;
    }
}
```
# LeetCode_748_最短完整词
## 题目
如果单词列表（words）中的一个单词包含牌照（licensePlate）中所有的字母，那么我们称之为完整词。在所有完整词中，最短的单词我们称之为最短完整词。

单词在匹配牌照中的字母时不区分大小写，比如牌照中的 "P" 依然可以匹配单词中的 "p" 字母。

我们保证一定存在一个最短完整词。当有多个单词都符合最短完整词的匹配条件时取单词列表中最靠前的一个。

牌照中可能包含多个相同的字符，比如说：对于牌照 "PP"，单词 "pair" 无法匹配，但是 "supper" 可以匹配。

示例 1：
```
输入：licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
输出："steps"
说明：最短完整词应该包括 "s"、"p"、"s" 以及 "t"。对于 "step" 它只包含一个 "s" 所以它不符合条件。同时在匹配过程中我们忽略牌照中的大小写。
```
示例 2：
```
输入：licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
输出："pest"
说明：存在 3 个包含字母 "s" 且有着最短长度的完整词，但我们返回最先出现的完整词。
```
注意:
```
牌照（licensePlate）的长度在区域[1, 7]中。
牌照（licensePlate）将会包含数字、空格、或者字母（大写和小写）。
单词列表（words）长度在区间 [10, 1000] 中。
每一个单词 words[i] 都是小写，并且长度在区间 [1, 15] 中。
```
## 解法
### 思路
- 使用桶的思想统计牌照的字符元素个数
- 存储满足条件的字符串
- 遍历并按照要求返回最短的字符串
### 代码
```java
class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        char[] plateCs = licensePlate.toCharArray();
        int[] bucket = new int[26];

        for (char c: plateCs) {
            if (c <= 122 && c >= 97) {
                bucket[c - 'a']++;
            }

            if (c <= 90 && c >= 65) {
                bucket[c - 'A']++;
            }
        }

        List<String> ansList = new ArrayList<>();

        for (String word : words) {
            int[] tmpBucket = Arrays.copyOf(bucket, bucket.length);
            for (char c: word.toCharArray()) {
                if (c <= 122 && c >= 97) {
                    tmpBucket[c - 'a']--;
                }

                if (c <= 90 && c >= 65) {
                    tmpBucket[c - 'A']--;
                }
            }

            boolean find = true;
            for (int i : tmpBucket) {
                if (i > 0) {
                    find = false;
                    break;
                }
            }

            if (find) {
                ansList.add(word);
            }
        }

        String ans = "";
        if (!ansList.isEmpty()) {
            ans = ansList.get(0);
            for (String word: ansList) {
                if (word.length() < ans.length()) {
                    ans = word;
                }
            }
        }
        
        return ans;
    }
}
```
# LeetCode_447_回旋镖的数量
## 题目
给定平面上 n 对不同的点，“回旋镖” 是由点表示的元组 (i, j, k) ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。

找到所有回旋镖的数量。你可以假设 n 最大为 500，所有点的坐标在闭区间 [-10000, 10000] 中。

示例:
```
输入:
[[0,0],[1,0],[2,0]]

输出:
2
```
解释:
```
两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
```
## 解法
### 思路
三层循环，寻找所有的组合，然后判断并计数
### 失败代码
```java
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int count = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (j == i) {
                    continue;
                }
                for (int k = j + 1; k < points.length; k++) {
                    if (k == i) {
                        continue;
                    }
                    if (find(points[i], points[j], points[k])) {
                        count += 2;
                    }
                }
            }
        }
        return count;
    }

    private boolean find(int[] one, int[] two, int[] three) {
        return Math.pow(one[0] - two[0], 2) + Math.pow(one[1] - two[1], 2) == Math.pow(one[0] - three[0], 2) + Math.pow(one[1] - three[1], 2);
    }
}
```
超出时间限制
## 解法
### 思路
- 遍历数组，把每个点当作第一个点
- 嵌套一层循环，算出外层和内层点的距离，
- 查询map里是否有相同距离的记录，如果有就*2累加到count里(相当于当前找到的点和之前的点的组合以及颠倒后的组合)
- 放入map,距离为key，value进行累加
- 把map清空
- 外层循环结束，返回count
### 代码
```java
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j <points.length; j++) {
                if (i == j) {
                    continue;
                }

                int distance = (int)(Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));
                count += map.getOrDefault(distance, 0) * 2;
                map.put(distance, map.getOrDefault(distance, 0) + 1);
            }
            
            map.clear();
        }
        return count;
    }
}
```
## 代码优化
### 思路
减少map的函数调用，使用临时变量tmp暂存map的value值
### 代码
```java
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        Map<Double, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j <points.length; j++) {
                if (i == j) {
                    continue;
                }

                double distance = Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2);
                int tmp = map.getOrDefault(distance, 0);
                count += tmp * 2;
                map.put(distance, tmp + 1);
            }

            map.clear();
        }
        return count;
    }
}
```
# LeetCode_485_最大连续1的个数
## 题目
给定一个二进制数组， 计算其中最大连续1的个数。

示例 1:
```
输入: [1,1,0,1,1,1]
输出: 3
解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
```
注意：
```
输入的数组只包含 0 和1。
输入数组的长度是正整数，且不超过 10,000。
```
## 解法一
### 思路
- 使用变量count计数
- 使用大顶堆存储
- 遍历数组，遇到1就累加，遇到0就将count存入堆中，并将count置为0
- 遍历结束，返回set的最大值
### 代码
```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
            }
            
            if (num == 0) {
                queue.offer(count);
                count = 0;
            }
        }
        
        if (count != 0) {
            queue.offer(count);
        }
        
        return queue.isEmpty() ? 0 : queue.poll();
    }
}
```
## 解法二
### 思路
不要用数据结构，直接用一个max变量不就好了。。。
### 代码
```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        
        for (int num : nums) {
            if (num == 1) {
                count++;
            }
            
            if (num == 0) {
                max = Math.max(max, count);
                count = 0;
            }
        }
        
        if (count != 0) {
            max = Math.max(max, count);
        }
        
        return max;
    }
}
```
# LeetCode_100_相同的树
## 题目
给定两个二叉树，编写一个函数来检验它们是否相同。

如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

示例 1:
```
输入:       1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

输出: true
```
示例 2:
```
输入:      1          1
          /           \
         2             2

        [1,2],     [1,null,2]

输出: false
```
示例 3:
```
输入:       1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

输出: false
```
## 解法
### 思路
dfs递归
### 代码
```java
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        
        if (p == null || q == null) {
            return false;
        }
        
        if (p.val != q.val) {
            return false;
        }
        
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
```
# LeetCode_1089_复写零
## 题目
给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。

注意：请不要在超过该数组长度的位置写入元素。

要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。

示例 1：
```
输入：[1,0,2,3,0,4,5,0]
输出：null
解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
```
示例 2：
```
输入：[1,2,3]
输出：null
解释：调用函数后，输入的数组将被修改为：[1,2,3]
```
提示：
```
1 <= arr.length <= 10000
0 <= arr[i] <= 9
```
## 解法
### 思路
- 拷贝原数组
- 遍历拷贝数组，按题意放置元素到原数组
- 直到原数组更新完毕
### 代码
```java
class Solution {
    public void duplicateZeros(int[] arr) {
        int[] copy = Arrays.copyOf(arr, arr.length);
        for (int i = 0, j = 0; j < arr.length; i++) {
            if (copy[i] == 0) {
                arr[j++] = 0;
                
                if (j >= arr.length) {
                    break;
                }
            }

            arr[j++] = copy[i];
        }
    }
}
```
## 解法二
### 思路
题目希望通过操作指针来原地修改数组，但操作指针的过程中有太多的边界情况需要考虑，不简洁
- 使用快慢指针来确定新数组的最后元素的下标
- 需要特别标记如果慢指针指向的是0，那么快指针是在累加的第一次超出边界还是第二次超出边界，因为这关系到原地修改的时候，最后一个元素是0的情况应该怎么处理
- 如果快漫指针一样就直接返回，说明不需要修改
- 然后从慢指针-1的位置原地变更这个数组

总之很复杂，错了好多遍，希望能找到更简洁的思路
### 代码
```java
class Solution {
    public void duplicateZeros(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        boolean one = false;
        int fast = 0, slow = 0;
        for (; fast < arr.length; slow++, fast++) {
            if (arr[slow] == 0) {
                fast++;
            }
            
            if (fast >= arr.length) {
                one = true;
                slow++;
                break;
            }
        }

        slow--;
        fast = arr.length - 1;

        if (fast == slow) {
            return;
        }

        for (; slow >= 0; slow--) {
            if (arr[slow] == 0) {
                if (one) {
                    one = false;
                } else {
                    arr[fast--] = 0;
                }
            }

            if (fast <= 0) {
                return;
            }

            arr[fast--] = arr[slow];
        }
    }
}
```
# LeetCode_530_二叉搜索树的最小绝对差
## 题目
给定一个所有节点为非负值的二叉搜索树，求树中任意两节点的差的绝对值的最小值。

示例 :
```
输入:

   1
    \
     3
    /
   2

输出:
1
```
解释:
```
最小绝对差为1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
注意: 树中至少有2个节点。
```
## 解法
### 思路
- 通过二叉搜索树的dfs中序遍历得到升序序列的特性，用一个list记录遍历的元素
- 遍历list获取两元素之间的最小值
### 代码
```java
class Solution {
    public int getMinimumDifference(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < list.size(); i++) {
            min = Math.min(min, Math.abs(list.get(i) - list.get(i - 1)));
        }
        return min;
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
优化代码，把外层的遍历list的动作放在递归构成中。

需要使用两个对象属性：
- pre：记录上一个节点的值，初始为null，根节点不需要计算min值
- min：记录最小值，初始为int最大值
### 代码
```java
class Solution {
    private Integer pre = Integer.MAX_VALUE;
    private Integer min = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return min;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        dfs(node.left);

        if (pre != null) {
            min = Math.min(min, Math.abs(node.val - pre));
        }

        pre = node.val;
        dfs(node.right);
    }
}
```
# LeetCode_520_检测大写字母
## 题目
给定一个单词，你需要判断单词的大写使用是否正确。

我们定义，在以下情况时，单词的大写用法是正确的：
```
全部字母都是大写，比如"USA"。
单词中所有字母都不是大写，比如"leetcode"。
如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
否则，我们定义这个单词没有正确使用大写字母。
```
示例 1:
```
输入: "USA"
输出: True
```
示例 2:
```
输入: "FlaG"
输出: False
注意: 输入是由大写和小写拉丁字母组成的非空单词。
```
## 解法
### 思路
- 遍历字符数组
- 通过首字母来判断当前适用哪种规则
    - 小写：之后的所有字符都是小写
    - 大写：
        - 第二个字符是大写：之后的所有字符都是大写
        - 第二个字符是小写：之后的字符都是小写
 - 遍历结束，返回判断结果
### 代码
```java
class Solution {
    public boolean detectCapitalUse(String word) {
        if (word.length() <= 1) {
            return true;
        }
        
        boolean allBig = false, allSmall = false;
        int index = 0;
        
        char[] cs = word.toCharArray();
        char firstLetter = cs[0];
        
        if (isSmall(firstLetter)) {
            allSmall = true;
            index = 1;
        }
        
        if (isBig(firstLetter)) {
            char secontLetter = cs[1];
            if (isBig(secontLetter)) {
                allBig = true;
            }
            if (isSmall(secontLetter)) {
                allSmall = true;
            }
            index = 2;
        }
        
        for (int i = index; i < cs.length; i++) {
            if (allBig && isSmall(cs[i])) {
                return false;
            }
            
            if (allSmall && isBig(cs[i])) {
                return false;
            }
        }
        
        return true;
    }

    private boolean isBig(char c) {
        return c <= 90 && c >= 65;
    }

    private boolean isSmall(char c) {
        return c <= 122 && c >= 97;
    }
}
```
# LeetCode_690_员工的重要性
## 题目
给定一个保存员工信息的数据结构，它包含了员工唯一的id，重要度 和 直系下属的id。

比如，员工1是员工2的领导，员工2是员工3的领导。他们相应的重要度为15, 10, 5。那么员工1的数据结构是[1, 15, [2]]，员工2的数据结构是[2, 10, [3]]，员工3的数据结构是[3, 5, []]。注意虽然员工3也是员工1的一个下属，但是由于并不是直系下属，因此没有体现在员工1的数据结构中。

现在输入一个公司的所有员工信息，以及单个员工id，返回这个员工和他所有下属的重要度之和。

示例 1:
```
输入: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
输出: 11
解释:
员工1自身的重要度是5，他有两个直系下属2和3，而且2和3的重要度均为3。因此员工1的总重要度是 5 + 3 + 3 = 11。
```
## 解法
### 思路
类似树结构，每一个节点有指向下一层节点的指针。

使用bfs
### 代码
```java
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        int ans = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(id);

        while (!queue.isEmpty()) {
            int len = queue.size();
            while (len-- > 0) {
                Integer eId = queue.poll();
                Employee employee = null;
                
                Iterator<Employee> iterator = employees.iterator();
                while (iterator.hasNext()) {
                    Employee e = iterator.next();
                    if (Objects.equals(e.id, eId)) {
                        employee = e;
                        iterator.remove();
                        break;
                    }                
                }

                if (employee == null) {
                    continue;
                }

                ans += employee.importance;

                for (Integer sId: employee.subordinates) {
                    queue.offer(sId);
                }
            }
        }

        return ans;
    }
}
```
## 优化代码
### 思路
减少遍历的次数，可以用一个map来存储id和employee的映射关系
### 代码
```java
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        int ans = 0;

        Map<Integer, Employee> map = new HashMap<>();
        for (Employee e: employees) {
            map.put(e.id, e);
        }
        
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(id);

        while (!queue.isEmpty()) {
            int len = queue.size();
            while (len-- > 0) {
                Integer eId = queue.poll();
                Employee employee = map.get(eId);

                if (employee == null) {
                    continue;
                }

                ans += employee.importance;

                for (Integer sId: employee.subordinates) {
                    queue.offer(sId);
                }
            }
        }

        return ans;
    }
}
```
## 解法二
### 思路
使用dfs递归，每一层返回当前层以及子节点的importance的总和
### 代码
```java
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        int ans = 0;

        Map<Integer, Employee> map = new HashMap<>();
        for (Employee e : employees) {
            map.put(e.id, e);
        }
        
        return dfs(map.get(id), map);
    }
    
    private int dfs(Employee e, Map<Integer, Employee> map) {
        if (e == null) {
            return 0;
        }
        
        if (e.subordinates == null || e.subordinates.size() == 0) {
            return e.importance;
        }
        
        int sum = e.importance;
        for (Integer id : e.subordinates) {
            sum += dfs(map.get(id), map);
        }
        
        return sum;
    }
}
```