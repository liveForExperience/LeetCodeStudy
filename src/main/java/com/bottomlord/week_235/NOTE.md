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
# [LeetCode_2707_字符串中的额外字符](https://leetcode.cn/problems/extra-characters-in-a-string)
## 解法
### 思路
- 思考过程：
  - 通过观察题目可以发现这是一个递推子问题：
    - 假设[0, i - 1]区间的最小空闲字符长度是`f[i - 1]`，那么`f[i]`作为[0,i]区间的最小空闲字符个数，至少就是`f[i - 1] + 1`
    - 然后，可以在区间[0, i]中，寻找`s[j,i]`，如果`s[j,i]`出现在`dictionary`中，那么`f[i]`的另一个空闲字符个数就出现了，也就是`f[j]`
    - 此时就可以通过比较来获取[0, i]区间中的个数，即`f[i] = min(f[i], f[j])`
  - 通过如上的推倒，就可以得到一个动态规划方程
- 算法过程：
  - 初始化数组`f[]`，长度为`s.length + 1`，其中`f[0] = 0`，元素坐标对应的是字符串的长度，0代表长度为0的字符串，其最小空闲字符自然就是0
  - 初始化set集合，用于存储`dictionary`中的字符串元素，并提供`O(1)`复杂度的查询能力
  - 从1开始遍历`s.length`
    - 首先`f[i] = f[i - 1] + 1`，给当前i长度的字符串最小空闲字符长度值赋上默认值
    - 然后遍历字符串坐标区间`[0, i]`，确定坐标`j`，从而找到能在`set`找到的字符串`s[j, i]`
    - 如果找到`s[j,i]`，那么进行对`f[i]`的比较更新，即`f[i] = min(f[j], f[i])`
  - 遍历结束，返回`f[n]`作为结果
### 代码
```java
class Solution {
  public int minExtraChar(String s, String[] dictionary) {
    int n = s.length();
    int[] f = new int[n + 1];
    Set<String> set = new HashSet<>(Arrays.asList(dictionary));

    for (int i = 1; i <= n; i++) {
      f[i] = f[i - 1] + 1;
      for (int j = 0; j < i; j++) {
        if (set.contains(s.substring(j, i))) {
          f[i] = Math.min(f[i], f[j]);
        }
      }
    }

    return f[n];
  }
}
```
# [LeetCode_2645_构造有效字符串的最少插入数](https://leetcode.cn/problems/minimum-additions-to-make-valid-string)
## 解法
### 思路
- 思考过程：
  - 使用2个指针
    - 一个指针`i`作用在`word`字符串上
    - 一个指针`j`作用在题目要求的`abc`字符串上
  - 2个指针同时移动
    - 如果指针指向的字符相同，就同时移动
    - 如果指针指向的字符不同，那么就增加计数，并只移动指针`j`
- 算法过程：
  - 初始化指针`i`和`j`
  - 初始化计数值`cnt`
  - 循环遍历`word`
    - 如果`i`和`j`指向的字符不同，就增加`cnt`并只移动`j`指针
  - 循环结束后，如果`j`不是指向`c`，那么就继续移动到`c`并记录移动的步数到`cnt`
  - 最后返回`cnt`作为结果
  - 需要注意，在模拟`j`在`abc`上移动的时候，每次要对`j`进行取模，循环结束后累加`j`距离`c`的时候，也需要通过`(3 - j) % 3`来获取这个距离
### 代码
```java
class Solution {
    public int addMinimum(String word) {
        int j = 0, cnt = 0;
        for (int i = 0; i < word.length();) {
            if ((word.charAt(i) - 'a') == j) {
                i++;
            } else {
                cnt++;
            }
            
            j = (j + 1) % 3;
        }

        return cnt + (3 - j) % 3;
    }
}
```