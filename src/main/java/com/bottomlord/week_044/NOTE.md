# Interview_1709_第k个数
## 题目
有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。

示例 1:
```
输入: k = 5

输出: 9
```
## 解法
### 思路
动态规划：
- 初始化3个指针：
    - p3：该指针指向的数字只乘以3
    - p5；该指针指向的数字只乘以5
    - p7：该指针指向的数字值乘以7
- `dp[i]`：第i个丑数
- 状态转移方程：
    - `dp[i] = min(dp[p3] * 3, dp[p5] * 5, dp[p6] * 6)`
    - 谁是最小值，谁的指针+1
- 初始化：
    - dp[0] = 1
    - p3 = 0
    - p5 = 0
    - p7 = 0
- 结果：返回`dp[k - 1]`
### 代码
```java
class Solution {
    public int getKthMagicNumber(int k) {
        int p3 = 0, p5 = 0, p7 = 0;
        int[] dp = new int[k];
        dp[0] = 1;
        for (int i = 1; i < k; i++) {
            dp[i] = Math.min(dp[p3] * 3, Math.min(dp[p5] * 5, dp[p7] * 7));
            
            if (dp[p3] * 3 == dp[i]) {
                p3++;
            }

            if (dp[p5] * 5 == dp[i]) {
                p5++;
            }

            if (dp[p7] * 7 == dp[i]) {
                p7++;
            }
        }
        
        return dp[k - 1];
    }
}
```
# Interview_1710_主要元素
## 题目
如果数组中多一半的数都是同一个，则称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。

示例 1：
```
输入：[1,2,5,9,5,9,5,5,5]
输出：5
```
示例 2：
```
输入：[3,2]
输出：-1
```
示例 3：
```
输入：[2,2,1,1,1,2,2]
输出：2
```
说明：
```
你有办法在时间复杂度为 O(N)，空间复杂度为 O(1) 内完成吗？
```
## 解法
### 思路
散列表计数
### 代码
```java
class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > len / 2) {
                return entry.getKey();
            }
        }
        
        return -1;
    }
}
```
## 解法二
### 思路
摩尔投票：
- 因为主要元素`major`出现的个数过半，所以如果`major`出现一次就+1，其他数字出现一次就-1，那么最终`major`的计数值`count`最终还是会至少剩下1个
- 定义两个变量：
    - count
    - major
- 遍历数组
    - 如果count值为0，将当前值设置为major值
    - 如果count值不为0
        - 当前值与major值相等，count++
        - 当前值与major值不等，count--
### 代码
```java
class Solution {
    public int majorityElement(int[] nums) {
        int count = 0, major = -1;
        for (int num : nums) {
            if (count == 0) {
                major = num;
                count++;
            } else {
                if (major == num) {
                    count++;
                } else {
                    count--;
                }
            }
        }

        return count > 0 ? major : -1;
    }
}
```
# LeetCode_221_最大正方形
## 题目
在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。

示例:
```
输入: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

输出: 4
```
## 解法
### 思路
暴力解法：
- 嵌套遍历获得二维数组坐标
- 如果坐标元素为1，将该坐标视为正方形的左上角
- 先确定一次暂存的最大值
- 再确定当前这个坐标理论上的最大边长长度
- 以这个点为起点，理论最大边长为界，遍历整个理论正方形的所有坐标
    - 一层一层的遍历
    - 每确定一层上所有元素都是1，就和暂存最大值作比较并更新
    - 如果遇到0，就终止搜索
- 最终返回暂存的最大值
### 代码
```java
class Solution {
    public int maximalSquare(char[][] matrix) {
        int ans = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return ans;
        }

        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }

                ans = Math.max(ans, 1);

                int max = Math.min(row - i, col - j);

                for (int k = 1; k < max; k++) {
                    boolean flag = true;
                    if (matrix[i + k][j + k] == '0') {
                        break;
                    }

                    for (int l = 0; l < k; l++) {
                        if (matrix[i + l][j + k] == '0' || matrix[i + k][j + l] == '0') {
                            flag = false;
                            break;
                        }
                    }

                    if (flag) {
                        ans = Math.max((1 + k) * (1 + k), ans);
                    } else {
                        break;
                    }
                }
            }
        }

        return ans;
    }
}
```
## 解法二
### 思路
动态规划：
- `dp[i][j]`：以坐标(i,j)为右下角坐标的长方形的最大边长
- 状态转移方程：(i,j)的最大边长取决于它的左一个，上一个，和左上一个坐标的最大边长。
    - 因为这3个点确定了当前坐标的：
        - 左一个：确定了除去当前列外左边邻接的最大正方形
        - 上一个：确定了除去当前行外上方邻接的最大正方形
        - 左上一个：确定了除去当前行和当前列，能够形成的最大正方形
        - 它们的最小值能够确定，包括当前坐标的所有为1的并形成正方形形态的坐标的集合
    - `dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1`
- 初始化：所有元素为0
- 返回dp数组中的最大值
### 代码
```java
class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int row = matrix.length, col = matrix[0].length, ans = 0;
        int[][] dp = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }

                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }

                ans = Math.max(ans, dp[i][j]);
            }
        }
        
        return ans * ans;
    }
}
```
# Interview_1711_单词距离
## 题目
有个内含单词的超大文本文件，给定任意两个单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?

示例：
```
输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
输出：1
```
提示：
```
words.length <= 100000
```
## 解法
### 思路
暴力：
- 遍历数组
- 哈希表记录单词与坐标集合
- 循环遍历两个单词的坐标值，找到差值最小的结果返回
### 代码
```java
class Solution {
    public int findClosest(String[] words, String word1, String word2) {
        Map<String, List<Integer>> map = new HashMap<>();
        int len = words.length;
        for (int i = 0; i < len; i++) {
            String word = words[i];
            List<Integer> list = map.getOrDefault(word, new ArrayList<>());
            list.add(i);
            map.put(word, list);
        }
        
        List<Integer> list1 = map.get(word1), list2 = map.get(word2);
        
        if (list1 == null || list2 == null) {
            return len;
        }
        
        int ans = len;
        for (Integer i1 : list1) {
            for (Integer i2 : list2) {
                ans = Math.min(Math.abs(i1 - i2), ans);
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
- 不使用哈希表，将word1和word2的坐标放入对应的动态数组中
- 使用双指针判断两个数组中元素之间的最小值
### 代码
```java
class Solution {
    public int findClosest(String[] words, String word1, String word2) {
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        int len = words.length;
        for (int i = 0; i < len; i++) {
            if (Objects.equals(words[i], word1)) {
                list1.add(i);
            }
            
            if (Objects.equals(words[i], word2)) {
                list2.add(i);
            }
        }

        if (list1.size() == 0 || list2.size() == 0) {
            return len;
        }

        int ans = len, i1 = 0, i2 = 0;
        while (i1 < list1.size() && i2 < list2.size()) {
            int num1 = list1.get(i1), num2 = list2.get(i2);
            ans = Math.min(ans, Math.abs(num1 - num2));

            if (num1 < num2) {
                i1++;
            } else {
                i2++;
            }
        }

        return ans;
    }
}
```
## 解法三
### 思路
将解法二中的两个步骤合并在一次遍历中完成。
### 代码
```java
class Solution {
    public int findClosest(String[] words, String word1, String word2) {
        int len = words.length, i1 = -1, i2 = -1, ans = len;
        for (int i = 0; i < len; i++) {
            if (Objects.equals(words[i], word1)) {
                i1 = i;
                if (i2 >= 0) {
                    ans = Math.min(ans, Math.abs(i2 - i1));
                }
            }

            if (Objects.equals(words[i], word2)) {
                i2 = i;
                if (i1 >= 0) {
                    ans = Math.min(ans, Math.abs(i2 - i1));
                }
            }
        }
        
        return ans;
    }
}
```