# LeetCode_1016_子串能表示从1到N数字的二进制串
## 题目
给定一个二进制字符串 S（一个仅由若干 '0' 和 '1' 构成的字符串）和一个正整数 N，如果对于从 1 到 N 的每个整数 X，其二进制表示都是 S 的子串，就返回 true，否则返回 false。

示例 1：
```
输入：S = "0110", N = 3
输出：true
```
示例 2：
```
输入：S = "0110", N = 4
输出：false
```
提示：
```
1 <= S.length <= 1000
1 <= N <= 10^9
```
## 解法
### 思路
- 遍历1到N
- 将数字转成二进制字符串
- 判断是否contains，如果不是返回false
- 遍历结束返回true
### 代码
```java
class Solution {
    public boolean queryString(String S, int N) {
        for (int i = 1; i <= N; i++) {
            String n = Integer.toBinaryString(i);
            if (!S.contains(n)) {
                return false;
            }
        }
        return true;
    }
}
```
# LeetCode_973_最接近原点的K个点
## 题目
我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。

（这里，平面上两点之间的距离是欧几里德距离。）

你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。

示例 1：
```
输入：points = [[1,3],[-2,2]], K = 1
输出：[[-2,2]]
解释： 
(1, 3) 和原点之间的距离为 sqrt(10)，
(-2, 2) 和原点之间的距离为 sqrt(8)，
由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
```
示例 2：
```
输入：points = [[3,3],[5,-1],[-2,4]], K = 2
输出：[[3,3],[-2,4]]
（答案 [[-2,4],[3,3]] 也会被接受。）
```
提示：
```
1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000
```
## 解法
### 思路
- 生成一个数组，存放`points`中对应下标生成的欧几里得距离
- 生成一个数组，根据计算出来的距离进行排序
- 根据排序结果找到前k个下标并返回
### 代码
```java
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int len = points.length;
        int[][] ans = new int[K][2];
        Integer[] rank = new Integer[len];
        double[] distances = new double[len];
        for (int i = 0; i < len; i++) {
            int x = points[i][0], y = points[i][1];
            distances[i] = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
            rank[i] = i;
        }

        Arrays.sort(rank, Comparator.comparingDouble(x -> distances[x]));
        
        for (int i = 0; i < K; i++) {
            ans[i] = points[rank[i]];
        }
        
        return ans;    }
}
```