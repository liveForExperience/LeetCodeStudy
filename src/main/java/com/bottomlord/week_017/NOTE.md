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
## 解法二
### 思路
分治算法：
- 参数：
    - `head`：需要进行调整顺序的数组的起始指针
    - `tail`：需要进行调整顺序的数组的结尾指针
    - `k`：需要调整的数组长度
- 递归：
    - 退出条件：`head` >= `tail`
    - 过程：
        - 以头指针的值作为基准`headDis`
        - 进入`while(head < tail)`的循环：
            - 先判断`head<tail`且尾指针的值是大于`headDis`，如果是就将尾指针不断前移，否则就终止循环并将这个值放在头指针的位置
            - 再判断头指针的值是否小于等于`headDis`，如果是就移动头指针，因为之前尾指针如果没有越界，一定会把小的值交换到头指针，所以这个时候头指针的值一定会移动，直到找到一个比`headDis`大的值，然后再和`tail`的元素交换，而交换来的就是原来的`headDis`
            - 然后继续循环，这样每一个循环都是将tail部分比`headDis`小的值换到前面，把`head`部分比`headDis`大的部分再换回去，直到头尾指针相遇
        - 循环条件失效退出后，判断当前的k值于新头指针移动的距离：
            - 如果小于等于，说明包括前k的元素的数组已经找到，现在需要对这个数组进行排序，确保前K个在数组之前
            - 如果大于，说明还没有找到足够多的前N个元素，需要继续移动头指针，而需要移动的值就是`k - (新head - head + 1)`
- 结果：返回前K个元素的copy
### 代码
```java
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        divideAndConquer(points, 0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    private void divideAndConquer(int[][] points, int head, int tail, int k) {
        if (head >= tail) {
            return;
        }

        int oldHead = head, oldTail = tail, headDis = distance(points, head);
        while (head < tail) {
            while (head < tail && distance(points, tail) >= headDis) {
                tail--;
            }
            swap(points, head, tail);

            while (head < tail && distance(points, head) <= headDis) {
                head++;
            }
            swap(points, head, tail);
        }

        if (k <= head - oldHead + 1) {
            divideAndConquer(points, oldHead, head, k);
        } else {
            divideAndConquer(points, head + 1, oldTail, k - (head - oldHead + 1));
        }
    }

    private int distance(int[][] points, int x) {
        return points[x][0] * points[x][0] + points[x][1] * points[x][1];
    }

    private void swap(int[][] points, int x, int y) {
        int[] tmp = points[x];
        points[x] = points[y];
        points[y] = tmp;
    }
}
```