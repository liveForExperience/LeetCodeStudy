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
# [LeetCode_2008_出租车的最大盈利](https://leetcode.cn/problems/maximum-earnings-from-taxi/)
## 解法
### 失败解法1
```java
class Solution {
    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, Comparator.comparingInt(x -> x[0]));
        return backTrack(0, rides, -1, 0);
    }
    
    private int backTrack(int index, int[][] rides, int end, int sum) {
        if (index >= rides.length) {
            return sum;
        }
        
        int max = sum;
        for (int i = index; i < rides.length; i++) {
            if (rides[i][0] < end) {
                continue;
            }
            
            max = Math.max(backTrack(i + 1, rides, rides[i][1], sum + rides[i][1] - rides[i][0] + rides[i][2]), max);
        }
        
        return max;
    }
}
```
### 失败解法2
```java
class Solution {
    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, Comparator.comparingInt(x -> x[1]));
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i < rides.length; i++) {
            int start = rides[i][0], end = rides[i][1], tip = rides[i][2];
            dp[end] = Math.max(dp[end], end - start + tip);
            for (int j = start; j >= 0; j--) {
                if (dp[j] == -1) {
                    continue;
                }

                dp[end] = Math.max(dp[j] + end - start + tip, dp[end]);
            }
        }

        int ans = 0;
        for (int i = n; i >= 1; i--) {
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }
}
```
### 思路
- 思考过程：
  - 在得到最终ac的答案之前，经历了如下2个步骤：
    - 通过递归暴力计算，超时了
    - 通过时间复杂度为`O(N^2)`的dp求解，超时了
  - 递归的过程可以理解成：每次需要选择下一个路程时，通过遍历所有可能的子路程，一直到底，最终得到最大值结果。这个过程中，每条看似不同的路径，从最大值路径的角度去看，实际都会走相同的最后一段路程，只不过这段路程的开始点会不同。这里就可以优化。
  - 在第2个失败的dp解法中，希望通过记录探索过程中得到的每个路径结尾地点值，来简化下一次的探索过程。但是，每一次新的探索开始后，都需要遍历之前的所有dp元素点，通过比较大小来确定到底应该选择哪个作为当前路径开始的上一段路径。而实际上，当得到了某个结尾点的最大值后，它与它之前的某个路劲结尾点之间的所有点，都可以视为值与上一个结尾点的最大值一致。这样，当新的路径开始的时候，只需要从当前路径开始点开始往前遍历到某个探索过的结尾点，就能确定这个点的值就是最大值，这样就不需要去遍历更早的点了，从而节省了很多搜索过程。
  - 于是在最终ac的算法中，每次在确定好当前路径的最大值后，会把从当前结尾点之前的所有没有探索过的点都填充好，为下一次搜索做准备。
- 算法过程：
  - 根据结尾点值对`rides`数组进行非降序排序，保证填充历史最大值的时候的正确性，如果没有排序，那么你在填充的时候就并不能确定是否所有之前的路径都已经搜索过，最大值都已经确定过。
  - 初始化一维的`dp`数组
    - 坐标：路程点
    - 元素值`dp[i]`：以当前路程点为结尾的路径盈利最大值
    - 长度：`n + 1`，因为路程点从1开始
  - 初始化`dp[0] = 0`，代表没有开始的路程的盈利为0
  - 2层循环
    - 第一层遍历`rides`数组，遍历所有可选的路程，在这层遍历中做如下2件事
      - 第一件事是，基于当前遍历到的路程，从路程的开始点开始向前找到之前在`dp`中记录的，离开始值最近的最大盈利值，找到就可以终止搜索
        - 之所以可以已找到就能终止，是因为思考过程中提到了，我们在后续的处理中会将之前的所有路程点都进行填充，将当前路程点的最大盈利值与向前查找的第一个确定的最大盈利值保持一致，从而使得只要一找到第一个最近的有搜索过的路程点，就一定是可能的最大值
        - 在第一个内层循环中，还会将找到的这个最近的最大盈利值和对应的坐标暂存下来，方便之后填充来用
      - 第二件事就是填充，填充的方式就是从之前确定的第一个没有填充的路程点开始填充
  - 2层循环结束之后，再从dp中找到坐标最大的那个有记录的最大盈利值，将其作为结果即可
### 代码
```java
class Solution {
    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, Comparator.comparingInt(x -> x[1]));
        long[] dp = new long[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        int index = 0;
        for (int i = 0; i < rides.length; i++) {
            int start = rides[i][0], end = rides[i][1], tip = rides[i][2];
            long cur = end - start + tip, preMax = 0;
            int preMaxIndex = 0;
            for (int j = start; j >= 0; j--) {
                if (dp[j] == -1) {
                    continue;
                }

                preMax = dp[j];
                preMaxIndex = i;
                cur += dp[j];
                break;
            }

            dp[end] = Math.max(dp[end], cur);

            long max = preMax;
            index = Math.max(preMaxIndex, index);
            for (int j = index; j <= end; j++) {
                max = Math.max(dp[j], max);
                dp[j] = max;
                index = j;
            }
        }

        long ans = 0;
        for (int i = n; i >= 1; i--) {
            if (dp[i] != -1) {
                return dp[i];
            }
        }

        return ans;
    }
}
```
# [LeetCode_2182_构造限制重复的字符串](https://leetcode.cn/problems/construct-string-with-repeat-limit)
## 解法
### 思路
- 思考过程：
  - 为了组成字典序最大的字符串，可以先对字符串`s`中字符出现的个数进行统计，然后从字典序最大的字符开始拼接新的字符串
  - 因为题目有最大重复个数，所以可以通过2个指针
    - 1个指针指向不考虑重复的情况下，正常可以添加的字典序最大的字符位置
    - 1个指针用于在连续次数达到最大重复次数时候，指向相对第二大的字符位置，用于在重复次数达到时拼接另一个最大的字符
- 算法过程：
  - 初始化一个桶数组`bucket`，长度为26，对应26个小写字母
  - 初始化一个变量`cnt`，用于记录新拼接的字符串中连续字符的个数
  - 初始化一个字符串变量，对应新的答案字符串
  - 创建一个循环，循环变量有2个指针`i`和`j`，并同时从25（`z`的坐标）开始，向前遍历
    - 先判断`bucket[i]`的个数是否是0，如果是0，代表当前字母不能再作为拼接的字符，将`cnt`重置为0，并左移`i`
    - 如果上一个不满足，则说明当前字母还有对应的个数没有使用，此时就要判断是否达到了重复个数
      - 如果`cnt < repeatLimit`，说明可以拼接，就将当前字母拼接在字符串最后，并自增`cnt`，同时递减`bucket[i]`
      - 否则说明已经达到了`repeatLimit`，就看`j`是否在`i`的左侧，且个数不为0
        - 如果不是，说明`j`对应的字符不能作为第二大的字符，需要继续左移
        - 如果全都符合，就将当前`j`对应的字母拼接在字符串后面，并重置`cnt`，累减`bucket[j]`
  - 循环结束，则返回字符串作为结果
  - 这里循环的时候千万不要这样写`for (i = 0; i < s.toCharArray().length; i++)`，这样每次调用`toCharArray`方法，都会创建一个新的`char`数组，这样会导致很大的内存和时间开销
### 代码
```java
class Solution {
    public String repeatLimitedString(String s, int repeatLimit) {
        int[] bucket = new int[26];
        for (int i = 0; i < s.length(); i++) {
            bucket[s.charAt(i) - 'a']++;
        }

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 25, j = 24; i >= 0 && j >= 0;) {
            if (bucket[i] == 0) {
                cnt = 0;
                i--;
            } else if (cnt < repeatLimit) {
                sb.append((char)('a' + i));
                bucket[i]--;
                cnt++;
            } else if (j >= i || bucket[j] == 0) {
                j--;
            } else {
                bucket[j]--;
                sb.append((char)('a' + j));
                cnt = 0;
            }
        }
        
        return sb.toString();
    }
}
```