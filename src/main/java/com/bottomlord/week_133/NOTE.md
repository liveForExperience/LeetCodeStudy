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

```