# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路
bfs模拟
- 遍历二维数组，找到第一个标记为1的，被感染的坐标，从该点开始广度优先搜索
- 开始遍历前
  - 将当前找到的坐标标记为-idx，idx代表当前找到的区块的个数
  - 初始化一个set集合，该集合用于记录与感染坐标相邻的未感染的坐标值
  - 因为需要用set来记录坐标，且坐标值不会大于2的16次方，所以，可以通过二进制位移的方式，将x存储在整数的高16位，y存储在整数的低16位
- 搜索过程中做如下几件事：
  - 如果遇到相邻被感染的坐标，就放入bfs驱动队列，并将当前坐标标记为-idx
  - 如果遇到相邻未被感染的坐标，就将其放入set集合中，放入前做一下坐标值处理
- 二维数组中的所有区块都遍历过以后
  - 将set集合中set大小最大的那个区块大小累加到隔离栏数中，并将该set中的坐标的值改为2，代表被隔离了
  - 将set集合中其他set中的坐标还原为1，用于在下一次bfs处理中判断
- 退出条件：
  - 如果set集合的长度为0，代表找不到没有被隔离的感染区块了
  - 如果set集合的长度为1，代表处理本次被隔离的感染区块外，没有其他未被隔离的感染区块了
### 代码
```java
class Solution {

    private final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int containVirus(int[][] isInfected) {
        int ans = 0, r = isInfected.length, c = isInfected[0].length;

        while (true) {
            List<Set<Integer>> neighbourhoods = new ArrayList<>();
            List<Integer> qList = new ArrayList<>();
            int idx;

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (isInfected[i][j] != 1) {
                        continue;
                    }
                    idx = -(neighbourhoods.size() + 1);
                    int q = 0;
                    Set<Integer> neighbourhood = new HashSet<>();

                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.offer(new int[]{i, j});
                    isInfected[i][j] = idx;

                    while (!queue.isEmpty()) {
                        int[] arr = queue.poll();
                        if (arr == null) {
                            continue;
                        }

                        int x = arr[0], y = arr[1];

                        for (int[] dir : dirs) {
                            int nx = dir[0] + x, ny = dir[1] + y;
                            if (nx < 0 || nx >= r || ny < 0 || ny >= c || isInfected[nx][ny] < 0) {
                                continue;
                            }

                            if (isInfected[nx][ny] == 1) {
                                queue.offer(new int[]{nx, ny});
                                isInfected[nx][ny] = idx;
                            } else if (isInfected[nx][ny] == 0) {
                                neighbourhood.add(getHash(nx, ny));
                                q++;
                            }
                        }
                    }

                    neighbourhoods.add(neighbourhood);
                    qList.add(q);
                }
            }

            if (neighbourhoods.size() == 0) {
                break;
            }

            idx = -1;
            int maxLen = 0;
            for (int i = 0; i < neighbourhoods.size(); i++) {
                Set<Integer> neighbourhood = neighbourhoods.get(i);
                if (neighbourhood.size() > maxLen) {
                    idx = -i - 1;
                    maxLen = neighbourhood.size();
                }
            }

            ans += qList.get(-idx - 1);

            if (neighbourhoods.size() == 1) {
                break;
            }

            for (int i = 0; i < neighbourhoods.size(); i++) {
                if (i == -idx - 1) {
                    continue;
                }

                for (Integer num : neighbourhoods.get(i)) {
                    isInfected[num >> 16][num & ((1 << 16) - 1)] = 1;
                }
            }

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (isInfected[i][j] == idx) {
                        isInfected[i][j] = 2;
                    } else if (isInfected[i][j] < 0) {
                        isInfected[i][j] = 1;
                    }
                }
            }
        }

        return ans;
    }

    private int getHash(int x, int y) {
        return (x << 16) ^ y;
    }
}
```
# [LeetCode_1806_还原排列的最少操作步数](https://leetcode.cn/problems/minimum-number-of-operations-to-reinitialize-a-permutation/)
## 解法
### 思路
暴力模拟
### 代码
```java
class Solution {
    public int reinitializePermutation(int n) {
        int[] perm = new int[n], arr = new int[n];
        for (int i = 0; i < n; i++) {
            perm[i] = i;
            arr[i] = i;
        }

        int ans = 0;
        while (true) {
            int[] cur = new int[n];

            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    cur[i] = arr[i / 2];
                } else {
                    cur[i] = arr[n / 2 + (i - 1) / 2];
                }
            }

            ans++;
            if (Arrays.equals(cur, perm)) {
                break;
            }
            
            arr = cur;
        }

        return ans;
    }
}
```
## 解法二
### 思路
- 找规律发现，一个坐标值根据题目要求进行变化，一定会最终变回到原始值
- 这就形成了一个环
- 且发现最大环的长度是所有小环长度的公倍数
- 那么最大的环就是题目要求的操作数结果
### 代码
```java
class Solution {
    public int reinitializePermutation(int n) {
        int i = 1, path = 0;
        while (true) {
            i = i % 2 == 0 ? i / 2 : n / 2 + (i - 1) / 2;
            path++;
            
            if (i == 1) {
                return path;
            }
        }
    }
}
```
# [LeetCode_753_破解保险箱](https://leetcode.cn/problems/cracking-the-safe/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1807_替换字符串中的扩号内容](https://leetcode.cn/problems/evaluate-the-bracket-pairs-of-a-string/)
## 解法
### 思路
- 使用哈希表和Regex API进行处理
### 代码
```java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> str : knowledge) {
            map.put("(" + str.get(0) + ")", str.get(1));
        }

        String regex = "\\([a-z]+\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        StringBuffer stringBuffer = new StringBuffer();

        while (matcher.find()) {
            String substring = s.substring(matcher.start(), matcher.end());
            matcher.appendReplacement(stringBuffer, matcher.group().replace(substring, map.getOrDefault(substring, "?")));
        }

        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }
}
```
# [LeetCode_1819_序列中不同最大公约数的数目](https://leetcode.cn/problems/number-of-different-subsequences-gcds/)
## 解法
### 思路
- 如果算出一个序列中的最大公约数p，那么在序列中的值就是ci * p，那么任意增加一个序列中的值A = ck * p，它的最大公约数也一定是p
- 通过布尔数组记录nums元素
- 那么就可以遍历1到最大值作为最大公约数的候选值
- 内部遍历时，通过找到当前所有候选最大公约数值的倍数，看看nums中是否存在倍数，如果存在，就计算候选公约数与num之间的公约数，如果计算出来的公约数与候选公约数一致，那就累加计数
- 遍历结束后返回计数值
- 迭代方式的gcd：
```java
class Solution {
    public int gcd(int num1, int num2) {
        while (num2 != 0) {
            int temp = num1;
            num1 = num2;
            num2 = temp % num2;
        }
        return num1;
    }
}
```
### 代码
```java
class Solution {
    public int countDifferentSubsequenceGCDs(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        boolean[] hasNum = new boolean[max + 1];
        for (int num : nums) {
            hasNum[num] = true;
        }

        int ans = 0;
        for (int i = 1; i <= max; i++) {
            int gcd = 0;
            for (int j = i; j <= max; j += i) {
                if (!hasNum[j]) {
                    continue;
                }

                if (gcd == 0) {
                    gcd = j;
                } else {
                    gcd = gcd(j, gcd);
                }

                if (gcd == i) {
                    ans++;
                    break;
                }
            }
        }

        return ans;
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}
```