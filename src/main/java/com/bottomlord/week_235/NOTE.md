# [LeetCode_447_回旋镖的数量](https://leetcode.cn/problems/number-of-boomerangs)
## 解法
### 思路
- 思考过程：
  - 2点之间的距离相等的元祖可以通过2层循环结构依次遍历和比较每个点与其他点的欧式距离来获得。
  - 因为题目的样本空间不大，只有500，所以2层循环的时间复杂度是可以接受的
  - 循环体第一层可以通过map来记录当前点与每个点之间距离是相同的点对的个数
  - 然后根据排列计算`P(N,2), N >= 2`来得到元祖的组合数
- 算法过程：
  - 初始化一个暂存回旋镖个数的变量`sum`
  - 2层循环`points`数组
  - 第一层循环体一开始初始化`map`
  - 第二层循环体：
    - 当遇到和外层相同的点元素则跳过
    - 计算2个点的欧式距离`distance`，欧式距离计算公式:`distance = (x0 - y0)^2 + (x1 - y1)^2`
    - 将距离通过map进行计数
  - 第二层循环结束后，在第一层循环的结尾，对`map`进行遍历，跳过计数值只有1的键值对，剩下的键值对的`value`，对齐计算排列数，然后累加到`sum`中
  - 循环结束后返回答案变量`sum`即可
### 代码
```java
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int sum = 0;
        for (int i = 0; i < points.length; i++) {
            int[] x = points[i];
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                
                int[] y = points[j];
                int distance = distance(x, y);
                
                map.put(distance, map.getOrDefault(distance, 0) + 1);
            }

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int value = entry.getValue();
                if (value < 2) {
                    continue;
                }
                
                sum += value * (value - 1);
            }
        }
        
        return sum;
    }

    private int distance(int[] x, int[] y) {
        return (x[0] - y[0]) * (x[0] - y[0]) + (x[1] - y[1]) * (x[1] - y[1]);
    }
}
```
## 解法二
### 思路
- 思考过程：
  - 解法一中，二层的循环处理了2次
    - 第1次：确定和计算点对之间的距离，并对点对个数进行计数
    - 第2次：遍历点对个数，计算回旋镖元组数
  - 实际，可以将这2步进行合并：相同距离的`点个数`每增加1，其实就意味着可以增加2倍的原`点个数`，即当前新增的点和其他原来的点可以组成新的回旋镖元组，2倍则是因为顺序是敏感的，所以重排顺序后，又是一个元组。
- 算法过程：
  - 整体算法结构和解法一一致，只是将2次2层循环合并成1次
    - 计算得到点对间距离`distance`后，先通过`distance`获取`map`中原有的点个数`count`
    - 将`count * 2`后累加到`sum`中
    - 然后将`distance`对应的个数累加1
### 代码
```java
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int sum = 0;
        for (int i = 0; i < points.length; i++) {
            int[] x = points[i];
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }

                int[] y = points[j];
                int distance = distance(x, y);
                
                int count = map.getOrDefault(distance, 0);
                sum += count * 2;
                map.put(distance, count + 1);
            }
        }

        return sum;
    }

    private int distance(int[] x, int[] y) {
        return (x[0] - y[0]) * (x[0] - y[0]) + (x[1] - y[1]) * (x[1] - y[1]);
    }
}
```