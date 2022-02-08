# [LeetCode_1405_最长快乐字符串](https://leetcode-cn.com/problems/longest-happy-string/)
## 解法
### 思路
贪心
- 根据字符串个数非升序排序
- 根据题目要求循环拼接字符
- 循环结束返回拼接的字符串
### 代码
```java
class Solution {
    public String longestDiverseString(int a, int b, int c) {
        int[][] pairs = new int[][]{{a, 0}, {b, 1}, {c, 2}};
        StringBuilder sb = new StringBuilder();

        while (true) {
            Arrays.sort(pairs, (x, y) -> y[0] - x[0]);
            boolean hasNext = false;
            for (int[] pair : pairs) {
                if (pair[0] <= 0) {
                    continue;
                }

                char ch = (char)(pair[1] + 'a');
                int len = sb.length();
                if (len >= 2 &&
                    sb.charAt(len - 2) == ch &&
                    sb.charAt(len - 1) == ch) {
                    continue;
                }

                hasNext = true;
                sb.append(ch);
                pair[0]--;
                break;
            }

            if (!hasNext) {
                break;
            }
        }
        
        return sb.toString();
    }
}
```
# [LeetCode_2160_拆分数位后四位数字的最小和](https://leetcode-cn.com/problems/minimum-sum-of-four-digit-number-after-splitting-digits/)
## 解法
### 思路
- 将数字拆分成4个整数
- 非降序排序4个整数
- 返回1和3以及2和4组成的两个数的和
### 代码
```java
class Solution {
    public int minimumSum(int num) {
        int[] arr = new int[4];
        int index = 0;
        while (num > 0) {
            arr[index++] = num % 10;
            num /= 10;
        }
        Arrays.sort(arr);
        return arr[0] * 10 + arr[2] + arr[1] * 10 + arr[3];
    }
}
```
# [LeetCode_1001_网格照明](https://leetcode-cn.com/problems/grid-illumination/)
## 解法
### 思路
- 使用hash表存储lamps数组中x，y所对应的坐标的横竖撇捺4条延伸线的亮灯状态
  - key对应x,y坐标对应的横竖撇捺
  - value对应出现的次数，这个次数在query的时候用来做累减
- 遍历query数组
  - 根据4个map中是否存在该坐标的对应key，来判断是否亮灯
  - 同时对该坐标的四周做灭灯处理，同时处理value，如果value为0，则从map中删除key
- 最终返回亮灯状态对应的数组
- 需要注意：
  - 在query后关闭的，必须是已经打开的开关，如果是因为开关而照亮的灯，则关闭没有作用，不能关闭，所以需要有一个set来记录开过坐标，而因为二维数组是一个n * n的二维数组，所以可以用n * x + y来唯一代表每一个坐标
  - 在query后关闭时，要对新生成的四周一圈的坐标做越界判断，否则可能会导致错误的行列value被减去而应该被减去的value却因为判重逻辑而被跳过
### 代码
```java
class Solution {
    private final int[][] dirs = new int[][]{{0, 0}, {0, -1}, {0, 1}, {-1, 0}, {-1, -1}, {-1, 1}, {1, 0}, {1, -1}, {1, 1}};

    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        Map<Integer, Integer> row = new HashMap<>(), col = new HashMap<>(),
                left = new HashMap<>(), right = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        for (int[] lamp : lamps) {
            int x = lamp[0], y = lamp[1],
                    l = x + y, r = x - y;

            if (set.contains(x * n + y)) {
                continue;
            }

            increase(row, x);
            increase(col, y);
            increase(left, l);
            increase(right, r);
            set.add(x * n + y);
        }

        int[] ans = new int[queries.length];
        int index = 0;
        for (int[] query : queries) {
            int x = query[0], y = query[1],
                    l = x + y, r = x - y;

            if (row.containsKey(x) || col.containsKey(y) || left.containsKey(l) || right.containsKey(r)) {
                ans[index] = 1;
            }

            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1],
                    nl = nx + ny, nr = nx - ny;

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                if (set.contains(nx * n + ny)) {
                    set.remove(nx * n + ny);
                    decrease(row, nx);
                    decrease(col, ny);
                    decrease(left, nl);
                    decrease(right, nr);
                }
            }

            index++;
        }

        return ans;
    }

    private void increase(Map<Integer, Integer> map, Integer num) {
        map.put(num, map.getOrDefault(num, 0) + 1);
    }

    private void decrease(Map<Integer, Integer> map, Integer num) {
        Integer value = map.getOrDefault(num, 0);
        if (value <= 1) {
            map.remove(num);
            return;
        }

        map.put(num, map.get(num) - 1);
    }
}
```