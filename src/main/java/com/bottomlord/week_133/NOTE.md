# [LeetCode_2045_ 到达目的地的第二短时间](https://leetcode-cn.com/problems/second-minimum-time-to-reach-destination/)
## 解法
### 思路
- 通过edge数组构建一个有向边的映射集合
- bfs遍历求得严格次短路径
- 遍历次短路径，累加time，并判断当前time是否会遇到需要等待红绿灯的情况，如果有就要加上等红灯的时间
  - 是否等红灯，就是当前累加值与红绿灯完整周期之间取余，如果大于半个周期，说明当前时间落入了红灯区域，需要等待
  - 等待的时间，就是完整周期减去取余的是时间
- 遍历结束，得到最终结果
### 代码
```java
class Solution {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int[][] path = new int[n + 1][2];
         for (int i = 0; i <= n; i++) {
            Arrays.fill(path[i], Integer.MAX_VALUE);
        }
        path[1][0] = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{1, 0});
        while (path[n][1] == Integer.MAX_VALUE) {
            int[] arr = queue.poll();
            int index = arr[0], step = arr[1];

            for (Integer next : graph[index]) {
                if (step + 1 < path[next][0]) {
                    path[next][0] = step + 1;
                    queue.offer(new int[]{next, step + 1});
                } else if (step + 1 > path[next][0] && step + 1 < path[next][1]) {
                    path[next][1] = step + 1;
                    queue.offer(new int[]{next, step + 1});
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < path[n][1]; i++) {
            if (ans % (2 * change) >= change) {
                ans = ans + (2 * change - ans % (2 * change));
            }
            ans += time;
        }
        return ans;
    }
}
```
# [LeetCode_1688_比赛中的配对次数](https://leetcode-cn.com/problems/count-of-matches-in-tournament/)
## 解法
### 思路
模拟
- 循环变更n的同时计算出当前循环周期内的配对次数
- 循环退出条件为`n==1`
- 在循环过程中累加配对值，循环结束后返回
### 代码
```java
class Solution {
    public int numberOfMatches(int n) {
        int count = 0;
        while (n > 1) {
            count += n / 2;
            n = n % 2 == 1 ? n / 2 + 1 : n / 2;
        }
        return count;
    }
}
```
# [LeetCode_2124_检查是否所有A都在B之前](https://leetcode-cn.com/problems/check-if-all-as-appears-before-all-bs/)
## 解法
### 思路
- 遍历字符数组，找到可能的ab分界点，记录当前找到的状态为true
- 继续遍历，如果状态为true的同时，还碰到a则返回false，否则遍历结束返回true
### 代码
```java
class Solution {
    public boolean checkString(String s) {
        char[] cs = s.toCharArray();
        boolean half = false;
        for (char c : cs) {
            if (!half && c == 'b') {
                half = true;
                continue;
            }
            
            if (half && c == 'a') {
                return false;
            }
        }
        
        return true;
    }
}
```
# [LeetCode_2129_将标题首字母大写](https://leetcode-cn.com/problems/capitalize-the-title/)
## 解法
### 思路
双指针
### 代码
```java
class Solution {
    public String capitalizeTitle(String title) {
        char[] cs = title.toCharArray();

        int j = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == ' ' || cs[i] == '\0') {
                if (i - j > 2) {
                    cs[j] = toUpper(cs[j]);
                }
                j = i + 1;
                continue;
            }

            cs[i] = toLower(cs[i]);
        }

        if (cs.length - j > 2) {
            cs[j] = toUpper(cs[j]);
        }

        return new String(cs);
    }

    private boolean isUpper(char c) {
        return c >= 65 && c <= 90;
    }

    private boolean isLower(char c) {
        return c >= 97 && c <= 122;
    }

    private char toUpper(char c) {
        return isUpper(c) ? c : (char) (c - 32);
    }

    private char toLower(char c) {
        return isLower(c) ? c : (char) (c + 32);
    }
}
```
# [LeetCode_2133_查是否每一行每一列都包含全部整数](https://leetcode-cn.com/problems/check-if-every-row-and-column-contains-all-numbers/)
## 解法
### 思路
- 2层循环遍历
- 借助布尔数组做是否存在的记录
- 任意一组不符合情况就返回false
- 遍历结束返回true
### 代码
```java
class Solution {
    public boolean checkValid(int[][] matrix) {
        int n = matrix.length;
        int sum = (1 + n) * n / 2;

        for (int i = 0; i < n; i++) {
            boolean[] row = new boolean[n], col = new boolean[n];
            for (int j = 0; j < n; j++) {
                row[matrix[i][j] - 1] = true;
                col[matrix[j][i] - 1] = true;
            }

            if (!full(row) || !full(col)) {
                return false;
            }
        }

        return true;
    }
    
    private boolean full(boolean[] arr) {
        for (boolean b : arr) {
            if (!b) {
                return false;
            }
        }
        
        return true;
    }
}
```
# [LeetCode_2013_检测正方形](https://leetcode-cn.com/problems/detect-squares/)
## 解法
### 思路
- hash表记录add进来的坐标
- count的时候，遍历row对应的col数组
- 基于遍历的candidateCol，找到其他3个坐标，这3个坐标可能有2组，分别累加这些坐标的所有可能性
  - 2组的原因是，得到candidateCol后，代表确定了一条边，这条边的两侧都有可能组成正方形，所以是2组
- 遍历时候需要注意剔除candidateCol和col一样的情况，因为这种情况相当于是一个长度为0的点，会导致结果过多
### 代码
```java
    class DetectSquares {
  private final Map<Integer, int[]> rowMap;

  public DetectSquares() {
    this.rowMap = new HashMap<>();
  }

  public void add(int[] point) {
    rowMap.computeIfAbsent(point[0], x -> new int[1001])[point[1]]++;
  }

  public int count(int[] point) {
    int row = point[0], col = point[1];
    if (!rowMap.containsKey(row) || rowMap.get(row)[col] <= 0) {
      return 0;
    }

    int[] colSet = rowMap.get(row);

    int count = 0;
    for (int candidateCol = 0; candidateCol < colSet.length; candidateCol++) {
      if (colSet[candidateCol] == 0) {
        continue;
      }

      int candidateLen = Math.abs(candidateCol - col);

      int positiveCandidateRow = row + candidateLen,
              negativeCandidateRow = row - candidateLen;

      if (positiveCandidateRow <= 1000 &&
              rowMap.containsKey(positiveCandidateRow) &&
              rowMap.get(positiveCandidateRow)[candidateCol] > 0) {
        count += colSet[candidateCol] * rowMap.get(positiveCandidateRow)[col] * rowMap.get(positiveCandidateRow)[candidateCol];
      }

      if (negativeCandidateRow >= 0 &&
              rowMap.containsKey(negativeCandidateRow) &&
              rowMap.get(negativeCandidateRow)[candidateCol] > 0) {
        count += colSet[candidateCol] * rowMap.get(negativeCandidateRow)[col] * rowMap.get(negativeCandidateRow)[candidateCol];
      }
    }

    return count;
  }
}
```
# [LeetCode_2138_将字符串拆分为若干长度为k的组](https://leetcode-cn.com/problems/divide-a-string-into-groups-of-size-k/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public String[] divideString(String s, int k, char fill) {
        int n = s.length(), left = n % k == 0 ? 0 : k - (n % k);
        
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < left; i++) {
            sb.append(fill);
        }
        
        char[] cs = sb.toString().toCharArray();

        StringBuilder sb2 = new StringBuilder();
        String[] ans = new String[sb.length() / k];
        int index = 0;
        
        for (int i = 1; i <= cs.length; i++) {
            sb2.append(cs[i - 1]);
            
            if (sb2.length() % k == 0) {
                ans[index++] = sb2.toString();
                sb2 = new StringBuilder();
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_2144_打折购买糖果的最小开销]()
## 解法
### 思路
- 要消耗最小，就必须要让免费领取的值最大，根据题目要求，买的2个糖果的较小值越大，免费送的越大
- 先将数组排序
- 将数组补充为能被3整除的长度，不足则前缀补零
- 倒序遍历数组，每次3个数字，并累加前两个数字
- 遍历结束返回累加值
### 代码
```java
class Solution {
  public int minimumCost(int[] cost) {
    int n = cost.length, left = n % 3 == 0 ? 0 : 3 - (n % 3), sum = 0;
    Arrays.sort(cost);
    int[] arr = new int[n + left];
    System.arraycopy(cost, 0, arr, left, n);

    for (int i = arr.length - 1; i >= 0;) {
      sum += arr[i--];
      sum += arr[i--];
      i--;
    }

    return sum;
  }
}
```
# [LeetCode_1996_游戏中弱角色的数量](https://leetcode-cn.com/problems/the-number-of-weak-characters-in-the-game/)
## 解法
### 思路
- 对数组排序
  - 如果进攻不相等，按照进攻的降序排列
  - 如果进攻相等，按照防守的升序排列
- 这样排序的原因是：
  - 按照正序遍历数组，遍历过程中遍历到的元素，进攻一定不比之前的元素大
    - 如果前面的元素比当前元素大，那只要比较防守就可以了
    - 防守可以通过遍历过程中记录最大值来确定防守值的上限，这样首先所有历史值一定比当前的进攻大，同时当前防守值又比防守上限值小，所以一定有一个比当前元素的进攻和防守都大的元素存在
    - 但是这边有一个问题，就是如果之前的几个元素是和当前值一样的呢？
      - 历史的上限值不能直接用，因为如果这个上限值是相同进攻值的元素生成的，那就不符合题意，不能累加结果了
      - 所以当进攻值相同的时候，如果相同进攻值的历史元素，防守值都肯定比当前元素的防守值小，那么当当前元素的防守值符合小于上限值的情况，那么这个上限值一定不是相同进攻的历史值产生的，所以排序的时候，如果进攻值相等，防守值就要非降序排列
- 然后遍历过程也就很明显了
  - 如果当前防守值比上限值小，就累加
  - 如果不比上限值小，那就更新上限值
- 遍历结束，返回累加值
- 注意
  - 防守上限值初始为0，这样一开始最大的进攻值元素，他的防守值肯定也不会小于这个上限值了
  - 这里的排序，如果进攻和防守颠倒也没有关系，逻辑是一样的，因为都要完全小于某个元素的进攻和防守
### 代码
```java
class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (x, y) -> x[1] == y[1] ? x[0] - y[0] : y[1] - x[1]);
        int maxDef = 0, ans = 0;
        for (int[] property : properties) {
            if (property[0] < maxDef) {
                ans++;
            } else {
                maxDef = property[0];
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_1765_地图中的最高点](https://leetcode-cn.com/problems/map-of-highest-peak/)
## 解法
### 思路
bfs
### 代码
```java
class Solution {
    private int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int[][] highestPeak(int[][] isWater) {
        int row = isWater.length, col = isWater[0].length;
        int[][] matrix = new int[row][col];

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] memo = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                boolean water = isWater[i][j] == 1;
                if (water) {
                    queue.offer(new int[]{i, j});
                    memo[i][j] = true;
                }
            }
        }

        int level = 1;
        while (!queue.isEmpty()) {
            int count = queue.size();
            while (count-- > 0) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                int r = arr[0], c = arr[1];
                
                for (int[] direction : directions) {
                  int newR = r + direction[0], newC = c + direction[1];
                  if (newR < 0 || newR >= row ||
                          newC < 0 || newC >= col ||
                          memo[newR][newC]) {
                    continue;
                  }
                  queue.offer(new int[]{newR, newC});
                  matrix[newR][newC] = level;
                  memo[newR][newC] = true;
                }
            }
            
            level++;
        }

        return matrix;
    }
}
```
# [LeetCode_884_两句话中的不常见单词](https://leetcode-cn.com/problems/uncommon-words-from-two-sentences/)
## 解法
### 思路
- 遍历字符串，生成两个map
- 遍历一个map，比较另一个map
- 遍历的时候，过滤掉个数超过1的
### 代码
```java
class Solution {
    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> map1 = countMap(s1), map2 = countMap(s2);
        List<String> strs = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            if (entry.getValue() != 1) {
                continue;
            }
            
            if (!map2.containsKey(entry.getKey())) {
                strs.add(entry.getKey());
            }
        }

        for (Map.Entry<String, Integer> entry : map2.entrySet()) {
            if (entry.getValue() != 1) {
                continue;
            }

            if (!map1.containsKey(entry.getKey())) {
                strs.add(entry.getKey());
            }
        }

        return strs.toArray(new String[0]);
    }

    private Map<String, Integer> countMap(String s1) {
        char[] cs = s1.toCharArray();
        Map<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (char c : cs) {
            if (c == ' ') {
                String str = sb.toString();
                if (str.length() == 0) {
                    continue;
                }

                map.put(str, map.getOrDefault(str, 0) + 1);
                sb = new StringBuilder();
            } else {
                sb.append(c);
            }
        }

        if (sb.length() != 0) {
            String str = sb.toString();
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        return map;
    }
}
```
# [LeetCode_2148_元素计数](https://leetcode-cn.com/problems/count-elements-with-strictly-smaller-and-greater-elements/)
## 解法
### 思路
treemap
### 代码
```java
class Solution {
    public int countElements(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int index = 0, sum = 0;
        for (Integer key : map.keySet()) {
            if (index == 0 || index == map.keySet().size() - 1) {
                index++;
                continue;
            }

            sum += map.get(key);

            index++;
        }

        return sum;
    }
}
```
## 解法二
### 思路
- 如果数组长度不足3，则直接返回0，因为不可能有符合题目要求的元素
- 求出数组中的最大和最小值，和这两个值相等的元素，肯定不是题目要求的元素
- 遍历数组，累加不是最大和最小值的元素的个数
- 返回累加值
### 代码
```java
class Solution {
  public int countElements(int[] nums) {
    int len = nums.length;
    if (len < 3) {
      return 0;
    }

    int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    for (int num : nums) {
      max = Math.max(max, num);
      min = Math.min(min, num);
    }

    int ans = 0;
    for (int num : nums) {
      if (num != max && num != min) {
        ans++;
      }
    }

    return ans;
  }
}
```
# [LeetCode_LCP11_期望个数统计](https://leetcode-cn.com/problems/qi-wang-ge-shu-tong-ji/)
## 解法
### 思路
- 初始化set集合用于去重
- 遍历数字数组，加入到set集合
- 遍历结束，返回set的长度
### 代码
```java
class Solution {
    public int expectNumber(int[] scores) {
        Set<Integer> set = new HashSet<>();
        for (int score : scores) {
            set.add(score);
        }
        return set.size();
    }
}
```
## 解法二
### 思路
- 遍历数组，使用桶计数累计元素的出现情况，并累加第一次出现的情况
### 代码
```java
class Solution {
  public int expectNumber(int[] scores) {
    boolean[] bucket = new boolean[1000001];
    int ans = 0;
    for (int score : scores) {
      if (!bucket[score]) {
        bucket[score] = true;
        ans++;
      }
    }

    return ans;
  }
}
```
# [LeetCode_LCP39_无人机方阵](https://leetcode-cn.com/problems/0jQkd0/)
## 解法
### 思路
- 统计source和target数组的颜色值
  - target正值统计
  - source负值统计
- 遍历统计颜色的数组，累加非负整数，并作为结果返回
### 代码
```java
class Solution {
    public int minimumSwitchingTimes(int[][] source, int[][] target) {
        int[] bucket = new int[10001];
        int row = source.length, col = source[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                bucket[target[i][j]]++;
                bucket[source[i][j]]--;
            }
        }

        int sum = 0;
        for (int num : bucket) {
            sum += Math.max(num, 0);
        }
        return sum;
    }
}
```
# [LeetCode_LCP40_心算挑战](https://leetcode-cn.com/problems/uOAnQW/)
## 解法
### 思路
回溯
### 代码
```java
class Solution {
    private int sum = 0;
    public int maxmiumScore(int[] cards, int cnt) {
        Arrays.sort(cards);
        int maxOddIndex = -1, maxEvenIndex = -1;
        for (int i = cards.length - 1; i >= 0; i--) {
            if (cards[i] % 2 == 0 && maxEvenIndex == -1) {
                maxEvenIndex = i;
            }

            if (cards[i] % 2 != 0 && maxOddIndex == -1) {
                maxOddIndex = i;
            }
            
            if (maxEvenIndex != -1 && maxOddIndex != -1) {
                break;
            }
        }
        
        if (maxEvenIndex + 1 >= cnt) {
            backTrack(cards, maxEvenIndex, 0, cnt, 0);
        }
        
        if (maxOddIndex + 1 >= cnt) {
            backTrack(cards, maxOddIndex, 0, cnt, 0);
        }
        
        return sum;
    }

    private boolean backTrack(int[] cards, int index, int time, int cnt, int sum) {
        if (time == cnt) {
            if (sum % 2 == 0) {
                this.sum = Math.max(this.sum, sum);
                return true;
            } else {
                return false;
            }
        }

        if (index < 0) {
            return false;
        }

        for (int i = index; i >= 0; i--) {
            boolean result = backTrack(cards, i - 1, time + 1, cnt, sum + cards[i]);
            if (result) {
                return true;
            }
        }

        return false;
    }
}
```
## 解法二
### 思路
贪心
- 对数组进行桶计数
- 遍历桶，将元素区分为奇数和偶数列表
- 再次遍历桶，尽可能将提前遇到的元素的值进行累加，直到cnt值用完
- 判断累加值是否是偶数，如果是偶数就返回
- 如果不是偶数，那就试着替换结果中的最后一个元素
  - 如果最后一个是奇数，就替换成剩下没有遍历的最大的偶数
  - 如果最后一个是偶数，就替换成剩下没有遍历的最大的奇数
- 为了完成上述步骤
  - 需要在第二次遍历桶的过程中，尝试记录下最后的奇数和偶数
  - 而且为了快速知道剩下最大的奇数或偶数是什么，所以可以用链表实现的列表来存储奇偶数，每次某个数用完，就将它从链表中去除，这样在做尝试替换的时候，就可以直接从链表的尾部将那个要的值取出来了
### 代码
```java
class Solution {
    public int maxmiumScore(int[] cards, int cnt) {
        int[] bucket = new int[1001];
        LinkedList<Integer> oddList = new LinkedList<>(), evenList = new LinkedList<>();
        for (int card : cards) {
            bucket[card]++;
        }

        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] == 0) {
                continue;
            }

            if (i % 2 == 0) {
                evenList.add(i);
            } else {
                oddList.add(i);
            }
        }

        int sum = 0, minOdd = -1, minEven = -1;
        for (int i = bucket.length - 1; i >= 0; i--) {
            int num = bucket[i];
            if (num == 0) {
                continue;
            }

            int count = Math.min(cnt, num);
            sum += count * i;

            bucket[i] -= count;
            if (i % 2 == 0) {
                minEven = i;
                if (bucket[i] == 0) {
                    evenList.removeFirst();
                }
            } else {
                minOdd = i;
                if (bucket[i] == 0) {
                    oddList.removeFirst();
                }
            }

            cnt -= count;

            if (cnt == 0) {
                break;
            }
        }

        if (sum % 2 == 0) {
            return sum;
        }

        int maxWhenChangeEven = 0, maxWhenChangeOdd = 0;
        if (minEven > 0 && !oddList.isEmpty()) {
            maxWhenChangeEven = sum - minEven + oddList.getFirst();
        }

        if (minOdd > 0 && !evenList.isEmpty()) {
            maxWhenChangeOdd = sum - minOdd + evenList.getFirst();
        }

        return Math.max(maxWhenChangeEven, maxWhenChangeOdd);
    }
}
```