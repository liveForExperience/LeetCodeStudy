# [LeetCode_LCP40_心算挑战](https://leetcode.cn/problems/uOAnQW)
## 解法
### 思路
- 对数组`cards`排序
- 圈定`cnt`大个元素对应的子数组
- 如果子数组总和`sum`为偶数，返回结果
- 否则，找到子数组中最小的奇偶数，找出非子数组中的最大奇偶数
- 如果最大奇数和最小偶数都存在，那么计算`sum - minEven + maxOdd`，否则为0
- 如果最大偶数和最小奇数都存在，那么计算`sum - minOdd + maxEven`，否则为0
- 最后计算2种情况的最大值，作为结果返回
### 代码
```java
class Solution {
    public int maxmiumScore(int[] cards, int cnt) {
        Arrays.sort(cards);
        int len = cards.length, start = len - cnt,
            minEven = -1, minOdd = -1, sum = 0;

        for (int i = start; i < cards.length; i++) {
            int num = cards[i];
            sum += num;

            if (minOdd != -1 && minEven != -1) {
                continue;
            }
            
            if (minOdd == -1 && num % 2 == 1) {
                minOdd = num;
            }
            
            if (minEven == -1 && num % 2 == 0) {
                minEven = num;
            }
        }
        
        if (sum % 2 == 0) {
            return sum;
        }
        
        int maxEven = -1, maxOdd = -1;
        for (int i = start - 1; i >= 0; i--) {
            int num = cards[i];
            
            if (maxOdd != -1 && maxEven != -1) {
                continue;
            }

            if (maxOdd == -1 && num % 2 == 1) {
                maxOdd = num;
            }

            if (maxEven == -1 && num % 2 == 0) {
                maxEven = num;
            }
        }
        
        int sum1 = 0, sum2 = 0;
        if (minOdd != -1 && maxEven != -1) {
            sum1 = sum - minOdd + maxEven;
        }
        
        if (minEven != -1 && maxOdd != -1) {
            sum2 = sum - minEven + maxOdd;
        }
        
        return Math.max(sum1, sum2);
    }
}
```
# [LeetCode_3128_直角三角形](https://leetcode.cn/problems/right-triangles/)
## 解法
### 思路
- 初始化2个数组：
  - `rows`：记录纵坐标对应的每一行的1的个数
  - `cols`：记录横坐标对应的每一列的1的个数
- 遍历二维数组`grid`，将遍历到的1分别记录到`rows`和`cols`中
- 再次遍历二维数组`grid`，当遍历到值为1的坐标时，用横坐标和纵坐标分别到`cols`和`rows`中找到对应的个数`r`和`c`，然后分别减1（因为需要剔除自身）
- 然后计算`(r - 1) * (c - 1)`作为以当前坐标为直角点的三角形的个数，并累加到结果变量`cnt`中
- 遍历结束后，返回`cnt`即可
- 此处还有一个优化点，因为有点和没点对应的值是0和1，所以在2次遍历`grid`的过程中，不需要判断值是否为1：
  - 第一次的时候，直接累加`grid[i][j]`，因为有就是1，没有则加0也没有影响
  - 第二次的时候，直接在公式`(r - 1) * (c - 1)`后再乘以`grid[i][j]`，这样，如果当前坐标并不是1，则乘以0也就相当于不累加
- 通过如上的优化可以减少逻辑判断，从而使得循环计算加速
### 代码
```java
class Solution {
    public long numberOfRightTriangles(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[] rows = new int[col], cols = new int[row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                rows[j] += grid[i][j];
                cols[i] += grid[i][j];
            }
        }

        long cnt = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                cnt += (long) (rows[j] - 1) * (cols[i] - 1) * grid[i][j];
            }
        }

        return cnt;
    }
}
```
# [LeetCode_3143_1_正方形中的最多点数](https://leetcode.cn/problems/maximum-points-inside-the-square)
## 解法
### 思路
- 遍历`points`数组，并通过`TreeSet`数据结构来记录信息：
  - key记录纵坐标和横坐标中对应的最大值（因为坐标有负数，还需要对坐标求绝对值）
  - value是一个列表，记录`points`数组坐标对应的标签
  - 键值对表示矩形长度为`key`的·
- 初始化一个长度为26的布尔数组`memo`
- 根据`TreeSet`的key的顺序遍历键值对，将遍历到的键值对的标签字符串记录到`memo`中，如果当前键值对出现了重复的字符串，那么当前键值对遍历结束后，终止循环。
- 在遍历过程中累加所有不重复的字符串标签个数，遍历结束后返回累加值
### 代码
```java
class Solution {
  public int maxPointsInsideSquare(int[][] points, String s) {
    boolean[] memo = new boolean[26];
    TreeMap<Integer, List<Character>> map = new TreeMap<>();
    for (int i = 0; i < points.length; i++) {
      int[] point = points[i];
      int len = Math.max(Math.abs(point[0]), Math.abs(point[1]));
      map.computeIfAbsent(len, x -> new ArrayList<>()).add(s.charAt(i));
    }

    int sum = 0;
    for (Map.Entry<Integer, List<Character>> entry : map.entrySet()) {
      List<Character> list = entry.getValue();
      boolean flag = false;
      int cnt = 0;
      for (Character c : list) {
        if (memo[c - 'a']) {
          flag = true;
          break;
        }

        memo[c - 'a'] = true;
        cnt++;
      }

      if (!flag) {
        sum += cnt;
      } else {
        break;
      }
    }

    return sum;
  }
}
```
## 解法
### 思路
- 通过思考题目可知，点数最大为26。而能达到多少点数取决于出现重复标签那个点所在的最小长度`min`是多少？
- 所以，用一个整数`arr`数组记录标签对应字母出现的最小长度，这个长度也用于与如上提到的`min`进行比较，只要比`min`小，那么当前这个标签字母就是有效的。
- `min`的维护方式，就是只要出现字母重复出现，就确保该值是最小的那个第二次出现字母所属点的长度。
- 通过遍历`points`数组，先通过解法一中同样的方法，计算出当前点的长度`len`以及坐标对应的字符，然后和`arr`中相同字母的长度值对应以及`min`进行比较和更新。
- 遍历结束后，遍历`arr`数组，将元素与`min`值比较，累加所有小于`min`的元素的个数，并将最终个数作为结果返回即可。
### 代码
```java
class Solution {
    public int maxPointsInsideSquare(int[][] points, String s) {
        int[] arr = new int[26];
        Arrays.fill(arr, Integer.MAX_VALUE);
        int dupMin = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            int[] point = points[i];
            int len = Math.max(Math.abs(point[0]), Math.abs(point[1])),
                index = s.charAt(i) - 'a';
            
            if (len < arr[index]) {
                dupMin = Math.min(dupMin, arr[index]);
                arr[index] = len;
            } else if (len < dupMin) {
                dupMin = len;
            }
        }
        
        int cnt = 0;
        for (int len : arr) {
            if (len < dupMin) {
                cnt++;
            }
        }
        return cnt;
    }
}
```