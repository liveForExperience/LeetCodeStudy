# [LeetCode_1512_好数对的数目](https://leetcode-cn.com/problems/number-of-good-pairs/)
## 解法
### 思路
2层循环，外层确定i，内层确定j，判断并累加有效对
### 代码
```java
class Solution {
    public int numIdenticalPairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
        }

        return count;
    }
}
```
## 解法二
### 思路
- 初始化一个桶数组，大小为题目数组的最大值+1，这个数组用来统计遍历过程中遇到的元素对应的次数
- 初始化一个变量用来统计有效对
- 正向遍历数组，在遍历过程中，累加当前元素在桶数组中出现的次数，并在累加后对次数做+1操作
  - 累加到总数中是因为，当前元素一定比桶数组中统计的这些相同元素的坐标值更大，那么这些元素有多少个，就能和当前元素组成多少个有效对
  - 对桶数组对应数累加1，就是为下一个出现的相同元素计算做准备
- 遍历结束返回统计值
### 代码
```java
class Solution {
    public int numIdenticalPairs(int[] nums) {
        int  ans = 0;
        int[] bucket = new int[101];
        for (int num : nums) {
            ans += bucket[num]++;
        }
        return ans;
    }
}
```
# [LeetCode_1646_获取生成数组中的最大值](https://leetcode-cn.com/problems/get-maximum-in-generated-array/)
## 解法
### 思路
- 遍历数组，判断当前坐标的奇偶性
  - 偶数，当前坐标元素等于坐标除以2的元素
  - 奇数，当前坐标元素等于坐标处以2以及坐标除2+1的元素的和
- 遍历过程中，同时统计最大值
- 遍历结束后，返回最大值
### 代码
```java
class Solution {
    public int getMaximumGenerated(int n) {
        if (n <= 1) {
            return n;
        }

        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        
        int max = 1;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                nums[i] = nums[i / 2];
            } else {
                nums[i] = nums[i / 2] + nums[i / 2 + 1];
            }
            
            max = Math.max(max, nums[i]);
        }
        
        return max;
    }
}
```
# [LeetCode_1518_换酒问题](https://leetcode-cn.com/problems/water-bottles/)
## 解法
### 思路
- 初始化变量：
  - drink：统计喝了多少瓶酒，初始为numBottle
  - emptyBottle：统计目前有的空瓶数量
- 设置循环
  - 退出条件，空瓶比`numExchange`的数字小，代表不能再兑换新酒了
  - 过程中：
    - drink累加当前兑换的新酒
    - emptyBottle累加兑换的新酒数，作为增加的酒瓶数，
    - 同时累减兑换时用掉的空瓶数
- 循环退出后，返回drink值
### 代码
```java
class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int drink = numBottles, emptyBottles = numBottles;
        
        while (emptyBottles >= numExchange) {
            int newDrink = emptyBottles / numExchange;
            drink += newDrink;
            emptyBottles = emptyBottles + newDrink - newDrink * numExchange;
        }
        
        return drink;
    }
}
```
# [LeetCode_1523_在区间范围内统计奇数数目](https://leetcode-cn.com/problems/count-odd-numbers-in-an-interval-range/)
## 失败解法
### 原因
超时
### 思路
- 遍历统计
### 代码
```java
class Solution {
  public int countOdds(int low, int high) {
    int odd = 0;
    for (int i = low; i <= high; i++) {
      odd += i % 2 == 1 ? 1 : 0;
    }
    return odd;
  }
}
```
## 解法
### 思路
- `num = high - low + 1`算出包含的元素个数
  - 如果个数是奇数：
    - low是奇数，奇数个数是`num / 2 + 1`
    - log是偶数，奇数个数是`num / 2`
  - 如果个数是偶数，奇数个数是`num / 2`
### 代码
```java
class Solution {
    public int countOdds(int low, int high) {
        int num = high - low + 1;
        return num % 2 == 0 ? num / 2 : low % 2 == 1 ? num / 2 + 1 : num / 2;
    }
}
```
# [LeetCode_1528_1_重新排列字符串](https://leetcode-cn.com/problems/shuffle-string/)
## 解法
### 思路
- 初始化一个字符数组，用于暂存新的字符串
- 遍历字符串，根据indices数组指示，组成新的字符串
- 返回字符串
### 代码
```java
class Solution {
    public String restoreString(String s, int[] indices) {
        char[] cs = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            cs[indices[i]] = s.charAt(i);
        }
        return new String(cs);
    }
}
```
# [LeetCode_787_K站中转内最便宜的航班](https://leetcode-cn.com/problems/cheapest-flights-within-k-stops/)
## 失败解法
### 原因
超时
### 思路
dfs
### 代码
```java
class Solution {
private int min = Integer.MAX_VALUE;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> mapping = new HashMap<>();
        for (int[] flight : flights) {
            mapping.computeIfAbsent(flight[0], x -> new ArrayList<>()).add(new int[]{flight[1], flight[2]});
        }

        dfs(src, dst, -1, k, 0, mapping);
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    
    private void dfs(int cur, int dst, int count, int k, int cost, Map<Integer, List<int[]>> mapping) {
        if (count > k) {
            return;
        }
        
        if (cur == dst) {
            min = Math.min(cost, min);
            return;
        }
        
        List<int[]> nexts = mapping.getOrDefault(cur, new ArrayList<>());
        
        for (int[] next : nexts) {
            dfs(next[0], dst, count + 1, k, cost + next[1], mapping);
        }
    }
}
```
## 解法
### 思路
动态规划：
- dp[t][i]：代表乘坐t次航班后到达城市i的最小花费
  - 题目中t的最大值应该是k+1，因为中转次数+1=乘坐航班数
  - dp数组的默认值设置为`INF = 10000 * 101 + 1`，代表无法到达的情况
  - INF的计算是基于最大的k值 + 1和最大的票价值的乘积 + 1获得
- base case：dp[0][src] = 0
  - 代表不坐航班，到达起始城市的花费就是0，因为不用坐航班
- 状态转移方程：
  - dp[t][i] = min(dp[t][i], dp[t - 1][j] + cost)
    - j是从可选航班中找到的能够到达城市i的起始城市
    - cost是从j到i的花费
- 过程：2层循环，分别确定t和i
  - 外层循环k+1次，确定t
  - 内层循环可选航班数组，确定第t次的时候，所有的状态
- 最终结果：遍历所有i = dst的情况，找到最小值
  - 如果最小值是INF，说明没法到达dst，返回-1
- 注意：这里不用int最大值来代表无法到达是因为，如果使用int最大值，会出现溢出状况
### 代码
```java
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int INF = 10000 * 101 + 1;
        int[][] dp = new int[k + 2][n];
        for (int[] arr : dp) {
            Arrays.fill(arr, INF);
        }
        
        dp[0][src] = 0;
        
        for (int t = 1; t <= k + 1; t++) {
            for (int[] flight : flights) {
                int j = flight[0], i = flight[1], cost = flight[2];
                dp[t][i] = Math.min(dp[t][i], dp[t - 1][j] + cost);
            }
        }
        
        int min = INF;
        for (int i = 1; i <= k + 1; i++) {
            min = Math.min(min, dp[i][dst]);
        }
        
        return min == INF ? -1 : min;
    }
}
```
# [LeetCode_797_所有可能的路径](https://leetcode-cn.com/problems/all-paths-from-source-to-target/)
## 解法
### 思路
回溯
### 代码
```java
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(0, graph, new LinkedList<Integer>(){{this.add(0);}}, ans);
        return ans;
    }
    
    private void backTrack(int index, int[][] graph, LinkedList<Integer> list, List<List<Integer>> ans) {
        if (index == graph.length - 1) {
            ans.add(new ArrayList<>(list));
            return;
        }
        
        int[] arr = graph[index];
        for (Integer num : arr) {
            list.addLast(num);
            backTrack(num, graph, list, ans);
            list.removeLast();
        }
    }
}
```
# [LeetCode_1534_统计好三元组](https://leetcode-cn.com/problems/count-good-triplets/)
## 解法
### 思路
模拟，三重循环
### 代码
```java
class Solution {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int n = arr.length, ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (Math.abs(arr[i] - arr[j]) <= a &&
                        Math.abs(arr[j] - arr[k]) <= b &&
                        Math.abs(arr[i] - arr[k]) <= c) {
                        ans++;
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
- 通过2层循环，确定j和k的坐标
- 然后因为`|arr[i] - arr[j]| <= a` 和 `|arr[i] - arr[k]| <= c|`这两个条件，可以推出i的范围就是`[arr[j] - a，arr[j] + a]`和`[arr[k] - c, arr[k] + c]`两个范围的交集
- 所以在第二层循环内部，主要的逻辑就是找到这个交集，然后求出这个交集所代表的数值，在当前的范围内出现的次数
- 次数可以通过频次前缀和来求出，这里的前缀和的坐标对应的就是所有可能出现的数字，值对应的就是出现的频次的累加和
- 求这个前缀和，就是在每次外层循环确定好j之后，且确定k的循环结束后，将j作为下次循环i可能用到的数累加次数1到前缀和数组对应的下表位置中，同时在之后的坐标元素上同样累加1，代表的就是不比当前值大的数出现的次数
- 而内层循环时候，就是先求出两个范围之间的交集，然后通过前缀和数组直接求出出现的频次，进行累加
- 循环结束后，返回累加值即可
### 代码
```java
class Solution {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int n = arr.length, ans = 0;
        int[] sum = new int[1001];
        for (int j = 0; j < n; j++) {
            for (int k = j + 1; k < n; k++) {
                if (Math.abs(arr[j] - arr[k]) > b) {
                    continue;
                }
                
                int lj = arr[j] - a, rj = arr[j] + a,
                    lk = arr[k] - c, rk = arr[k] + c;

                int l = Math.max(0, Math.max(lj, lk)), r = Math.min(1000, Math.min(rj, rk));

                if (l <= r) {
                    if (l == 0) {
                        ans += sum[r];
                    } else {
                        ans += sum[r] - sum[l - 1];
                    }
                }
            }

            for (int t = arr[j]; t <= 1000; t++) {
                sum[t]++;
            }
        }

        return ans;
    }
}
```
# [LeetCode_881_救生艇](https://leetcode-cn.com/problems/boats-to-save-people/
## 解法
### 思路
- 初始化一个数组arr，下标对应体重，值对应该体重的人数
- 遍历数组，填充arr
- 从尾部开始遍历arr，如果是0就跳过，否则，处理该体重的所有人
- 处理过程是：
  - 通过limit找到最大能够容纳的另一个人的体重值，然后基于这个值到arr中找尽可能大的，但这个值最大等于当前坐标对应的体重值
  - 如果找到了，就同时在arr的值上-1，并处理下一个
  - 如果没找到，就只在当前arr上处理
- 一直处理到数组遍历结束
### 代码
```java
class Solution {
  public int numRescueBoats(int[] people, int limit) {
    int[] bucket = new int[30001];
    for (int weight : people) {
      bucket[weight]++;
    }

    int count = 0;

    for (int i = bucket.length - 1; i >= 1; i--) {
      if (bucket[i] == 0) {
        continue;
      }

      while (bucket[i] > 0) {
        int target = Math.min(limit - i, i);
        bucket[i]--;
        for (int j = target; j >= 1; j--) {
          if (bucket[j] > 0) {
            bucket[j]--;
            break;
          }
        }

        count++;
      }
    }

    return count;
  }
}
```
## 解法二
### 思路
- 排序数组
- 头尾指针遍历数组
  - 如果头尾指针对应的元素相加不大于limit，就同时移动指针，移动方向为相向移动
  - 如果头尾指针对应的元素相加大于limit，就只移动尾指针，移动方向尾向头部移动
### 代码
```java
class Solution {
  public int numRescueBoats(int[] people, int limit) {
    Arrays.sort(people);
    int n = people.length, head = 0, tail = n - 1, count = 0;
    while (head < tail) {
      if (people[head] + people[tail] <= limit) {
        head++;
      }
      tail--;
      count++;
    }

    return head == tail ? count + 1 : count;
  }
}
```
## 解法三
### 思路
- 结合解法一和解法二，用桶对元素频次计数，处理的时候则通过头尾指针的方式来统计
- 过程：
  - 退出条件，头尾指针相遇
  - 内层2个循环，将频次为零的头尾指针移动掉
  - 然后判断头尾指针对应的坐标值相加是否大于limit，判断是否同时移动头尾指针，然后再在这个循环中累加1
- 麻烦的地方在头尾指针的退出状态，循环的退出条件是2个指针不相遇
  - 如果退出时候头尾指针相遇，那么可能指针对应的坐标还有一些频次没有统计
    - 如果2个坐标值相加不大于limit，那么就根据奇偶来判断是加多少count
    - 如果2个坐标值相加大于limit，那就直接累加剩余频次即可
  - 如果退出的时候头尾指针不相遇，那么在数组中最后一次有频次的那个循环，处理完以后，还会再做一次循环，且会count一次，所以要将这次count减掉
### 代码
```java
class solution {
  public int numRescueBoats(int[] people, int limit) {
    int[] bucket = new int[limit + 1];
    int head = 1, tail = limit, count = 0;

    for (int weight : people) {
      bucket[weight]++;
    }

    while (head < tail) {
      while (head < tail && bucket[head] == 0) {
        head++;
      }

      while (head < tail && bucket[tail] == 0) {
        tail--;
      }

      if (head + tail <= limit) {
        bucket[head]--;
      }

      bucket[tail]--;
      count++;
    }

    if (head == tail) {
      if (2 * head <= limit) {
        count += bucket[head] % 2 == 0 ? bucket[head] / 2 : (bucket[head] + 1) / 2;
      } else {
        count += bucket[head];
      }
    } else {
      count--;
    }

    return count;
  }
}
```
# [LeetCode_1539_第k个缺失的正整数](https://leetcode-cn.com/problems/kth-missing-positive-number/)
## 解法
### 思路
- 初始化一个变量index作为数组指针
- 从1开始遍历，直到1000为止
- 遍历过程中，如果i对应的元素和当前遍历到的元素不一致，就累加值
- 如果累加的值等于k就返回该值
- 如果遍历到的值和index一样就右移index
- 如果i来到数组最后位置，就直接累加值
### 代码
```java
class Solution {
    public int findKthPositive(int[] arr, int k) {
        int index = 0, n = arr.length, count = 0;
        for (int i = 1; i <= 2000; i++) {
            if (index < n && i == arr[index]) {
                index++;
            } else {
                count++;
            }

            if (count == k) {
                return i;
            }
        }
        
        return 2000;
    }
}
```
## 解法二
### 思路
- arr数组里的值减掉arr数组里已经遍历过得元素个数
- 如果大于k，说明第k个缺失的元素就在这个值的范围里，那么这个值就一定是k+遍历过的元素个数-1
- 如果遍历完所有元素，都没有一个元素符合要求，那么这个元素就一定是k+数组长度
### 代码
```java
class Solution {
    public int findKthPositive(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] - i - 1 >= k) {
                return k + i;
            }
        }
        
        return k + arr.length;
    }
}
```
# [LeetCode_295_数据流的中位数](https://leetcode-cn.com/problems/find-median-from-data-stream/)
## 解法
### 思路
- 2个优先级队列对倒
  - 小顶堆存放较大的值
  - 大顶堆存放较小的值
- 默认大顶堆的个数不会小于小顶堆，且两个顶堆的数量差不大于1
- 存放数值的时候
  - 如果小顶堆空，就直接塞给小顶堆，返回
  - 如果值比小顶堆的小，先塞入大顶堆，否则塞入大顶堆
  - 判断大顶堆的个数是不是超过小顶堆了，如果是，就把大顶堆堆顶元素塞给小顶堆
  - 如果大顶堆的个数+1小于小顶堆了，那么也把小顶堆的堆顶元素塞回给大顶堆
- 取中间值的时候，就判断
  - 如果都是空，就返回0
  - 如果大小顶堆长度不一致，说明中间值在小顶堆，返回小顶堆堆顶元素
  - 如果大小顶堆长度一致，返回两个堆顶的平均值
### 代码
```java
class MedianFinder {
    private PriorityQueue<Integer> bigger, smaller;
    public MedianFinder() {
        bigger = new PriorityQueue<>();
        smaller = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void addNum(int num) {
        if (bigger.isEmpty()) {
            bigger.offer(num);
            return;
        }
        
        if (num < bigger.peek()) {
            smaller.offer(num);
        } else {
            bigger.offer(num);
        }

        if (smaller.size() > bigger.size()) {
            bigger.offer(smaller.poll());
            return;
        }
        
        if (smaller.size() + 1 < bigger.size()) {
            smaller.offer(bigger.poll());
        }
    }

    public double findMedian() {
        if (bigger.isEmpty() && smaller.isEmpty()) {
            return 0;
        }
        
        if (bigger.size() == smaller.size()) {
            return (1D * bigger.peek() + smaller.peek()) / 2;
        }
        
        return (double) bigger.peek();
    }
}
```