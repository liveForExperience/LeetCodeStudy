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