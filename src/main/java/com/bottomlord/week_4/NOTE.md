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

```