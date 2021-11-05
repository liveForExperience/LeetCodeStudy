# [LeetCode_575_分糖果](https://leetcode-cn.com/problems/distribute-candies/)
## 解法
### 思路
- set统计糖果种类
- 计算每个人能够分到的糖果数量，也就是总糖果数/2
- 获取两个数之间的最小值返回
### 代码
```java
class Solution {
    public int distributeCandies(int[] candyType) {
        Set<Integer> set = Arrays.stream(candyType).boxed().collect(Collectors.toSet());
        return Math.min(set.size(), candyType.length / 2);
    }
}
```
## 解法二
### 思路
使用数组替代解法一的set来统计candy的种类
### 代码
```java
class Solution {
    public int distributeCandies(int[] candyType) {
        boolean[] bucket = new boolean[200001];
        int count = 0;
        for (int num : candyType) {
            if (!bucket[num + 100000]) {
                count++;
                bucket[num + 100000] = true;
            }
        }
        return Math.min(count, candyType.length / 2);
    }
}
```
# [LeetCode_1822](https://leetcode-cn.com/problems/sign-of-the-product-of-an-array/)
## 解法
### 思路
- 遍历数组
- 找到0就返回0
- 统计数组中负号的个数，如果是奇数就返回-1，否则返回1
### 代码
```java
class Solution {
    public int arraySign(int[] nums) {
        boolean flag = true;
        for (int num : nums) {
            if (num == 0) {
                return 0;
            }
            
            if (num < 0) {
                flag = !flag;
            }
        }
        
        return flag ? 1 : -1;
    }
}
```
# [LeetCode_237_删除链表中的节点](https://leetcode-cn.com/problems/delete-node-in-a-linked-list/)
## 解法
### 思路
- 将当前节点的值设置为下一个节点的值
- 将当前节点的next指向下一个节点的next
### 代码
```java
class Solution {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
```
# [LeetCode_1826_有缺陷的传感器](https://leetcode-cn.com/problems/faulty-sensor/)
## 解法
### 思路
- 模拟两个数组各自掉落时候是否符合题目要求
- 如果不是同时符合，那么哪一个符合，就返回对应的值
- 否则返回-1
### 代码
```java
class Solution {
    public int badSensor(int[] sensor1, int[] sensor2) {
        int i = 0, n = sensor1.length;
        while (i < n && sensor1[i] == sensor2[i]) {
            i++;
        }

        if (i >= n - 1) {
            return -1;
        }

        int index = i;
        while (index < n - 1 && sensor1[index] == sensor2[index + 1]) {
            index++;
        }
        
        int index2 = i;
        while (index2 < n - 1 && sensor2[index2] == sensor1[index2 + 1]) {
            index2++;
        }
        
        if (index != index2) {
            if (index == n - 1) {
                return 1;
            }

            if (index2 == n - 1) {
                return 2;
            }
        }

        return -1;
    }
}
```
# [LeetCode_42_接雨水](https://leetcode-cn.com/problems/trapping-rain-water-ii/solution/jie-yu-shui-ii-by-leetcode-solution-vlj3/)
## 解法
### 思路
- 如果遍历每个坐标，那么当前坐标能承接多少水，取决于其左边最高的坐标和右边最高的坐标之间的最小值(此处的最高包含自身)，与当前坐标的差值
- 一种方法是边遍历边计算，但这样的话，很多坐标都重复搜索了
- 另一种方法就是先将每个坐标的左右最高值求出来，存储在数组中，然后再遍历数组，通过公式`min(lmax[i], rmax[i]) - height[i]`求出当前坐标能承接的水量，累加后就是最终要求的值
### 代码
```java
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int[] lmax = new int[n], rmax = new int[n];
        lmax[0] = height[0];
        rmax[n - 1] = height[n - 1];
        
        for (int i = 1; i < n; i++) {
            lmax[i] = Math.max(lmax[i - 1], height[i]);
        }
        
        for (int i = n - 2; i >= 0; i--) {
            rmax[i] = Math.max(rmax[i + 1], height[i]);
        }
        
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.min(lmax[i], rmax[i]) - height[i];
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
单调栈：
- 初始化一个单调栈，该栈应保存高度单调递减的元素的坐标
- 遍历数组，与栈顶元素比较，如果比栈顶元素大，那么说明两者形成了洼地，当前元素，与栈顶元素及所有栈中的元素，都有几率组成一个能够承接水的结构
- 然后发现如上的洼地以后，就不断的将栈中元素出栈，然后生成当前元素与栈中元素坐标之间的区间距离*两个坐标元素高度最小值与栈顶元素的差乘积
- 一直到当前元素比栈中元素小为止，从而在下一个元素遍历到的时候，栈中仍然是单调递减的状态
- 遍历结束，返回累加的结果
### 代码
```java
class Solution {
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        int ans = 0;
        for (int i = 1; i < height.length; i++) {
            while (height[i] > height[stack.peek()]) {
                int top = stack.pop();
                
                if (stack.isEmpty()) {
                    break;
                }
                
                int dis = i - stack.peek() - 1,
                    h = Math.min(height[i], height[stack.peek()]) - height[top];
                
                ans += dis * h;
            }
            
            stack.push(i);
        }

        return ans;
    }
}
```
# [LeetCode_407_接雨水II](https://leetcode-cn.com/problems/trapping-rain-water-ii/)
## 解法
### 思路
- 先确定在三维的情况下，怎样能够承接到水：四周一圈扩展出去，所有最高点中的最低点就是当前点能够盛水的高度，再减去当前点的高度，就是承接水的容量
- 那么为了获得四周的最高点，以及最高点间的最低点，就需要通过优先级队列来实现，算法如下：
  - 优先级队列中先把矩阵边缘的所有坐标存储下来，作为最外围的一圈，对这些坐标的高度通过优先级队列进行排序，这样队列顶部就是最矮的那个坐标了
  - 然后做bfs，分别循环4个方向的节点，当坐标符合要求且没有搜索过，就看当前高度是不是比队列顶部要低，是的话，说明形成了洼地，当前这个高度差的水也肯定能承接住
  - 然后将当前节点作为新的周边节点，取新旧节点之间的最大值
### 代码
```java
class Solution {
    public int trapRainWater(int[][] heightMap) {
        int row = heightMap.length, col = heightMap[0].length;
        boolean[][] memo = new boolean[row][col];
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x[2]));

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    queue.offer(new int[]{i, j, heightMap[i][j]});
                    memo[i][j] = true;
                }
            }
        }

        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int ans = 0;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();

            int r = arr[0], c = arr[1], h = arr[2];
            for (int [] dir : dirs) {
                int newR = r + dir[0], newC = c + dir[1];
                if (newR < 0 || newR >= row || newC < 0 || newC >= col || memo[newR][newC]) {
                    continue;
                }

                int newH = heightMap[newR][newC];
                if (newH < h) {
                    ans += h - newH;
                }

                queue.offer(new int[]{newR, newC, Math.max(newH, h)});
                memo[newR][newC] = true;
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_367_有效的完全平方数](https://leetcode-cn.com/problems/valid-perfect-square/)
## 解法
### 思路
- 暴力模拟
### 代码
```java
class Solution {
    public boolean isPerfectSquare(int num) {
        if (num == Integer.MAX_VALUE) {
            return false;
        }

        for (int i = 1; i <= num; i++) {
            if (i * i > num) {
                break;
            }
            
            if (i * i == num) {
                return true;
            }
        }
        
        return false;
    }
}
```
## 解法二
### 思路
二分查找+bigDecimal
### 代码
```java
import java.math.BigDecimal;
import java.math.RoundingMode;

class Solution {
    public boolean isPerfectSquare(int num) {
        BigDecimal l = new BigDecimal(1), r = new BigDecimal(num), n = new BigDecimal(num);
        while (l.compareTo(r) <= 0) {
            BigDecimal mid = r.subtract(l);
            mid = mid.divide(BigDecimal.valueOf(2), RoundingMode.DOWN);
            mid = mid.add(l);

            BigDecimal target = mid.multiply(mid);

            if (target.equals(n)) {
                return true;
            } else if (target.compareTo(n) < 0) {
                l = mid.add(new BigDecimal(1));
            } else {
                r = mid.subtract(new BigDecimal(1));
            }
        }

        return false;
    }
}
```
# [LeetCode_1218_最长定差子序列](https://leetcode-cn.com/problems/longest-arithmetic-subsequence-of-given-difference/)
## 解法
### 思路
动态规划：
- dp[i]：存储以数字i为结尾的子序列，在等差值为n的情况下的最大长度
- 状态转移方程：dp[i] = d[i - n] + 1
- 过程：遍历数组，判断当前值减去等差值后，在dp数组中是否存在
  - 如果不存在，就在dp中记录当前值所在坐标的值为1
  - 如果存在，就在dp中记录，目标值所在坐标的值+1，将这个值放置在当前值所在的坐标位置
- 一边遍历一边记录最大值
- 数组直接初始化为40000长度，然后计算值默认+20000，防止负数或数值过大，数组越界
### 代码
```java
class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        int[] dp = new int[40001];
        int ans = 1;
        for (int num : arr) {
            dp[num + 20000] = dp[num - difference + 20000] + 1;
            ans = Math.max(ans, dp[num + 20000]);
        }
        
        return ans;
    }
}
```