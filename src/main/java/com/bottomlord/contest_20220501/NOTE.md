# [Contest_1_移除指定数字得到的最大结果]()
## 解法
### 思路
使用BigDecimal存储所有枚举出来可能的字符串，然后作比较，留最大值
### 代码
```java
class Solution {
  public String removeDigit(String number, char digit) {
    BigDecimal max = new BigDecimal(0);
    char[] cs = number.toCharArray();
    for (int i = 0; i < cs.length; i++) {
      char c = cs[i];
      if (digit != c) {
        continue;
      }

      StringBuilder sb = new StringBuilder();
      for (int j = 0; j < cs.length; j++) {
        if (j == i) {
          continue;
        }

        sb.append(cs[j]);
      }

      BigDecimal cur = new BigDecimal(sb.toString());
      if (cur.compareTo(max) > 0) {
        max = cur;
      }
    }

    return max.toString();
  }
}
```
## 解法二
### 思路
- 遍历字符串，如果当前字符与digit相同，且比后一个字符小，那么就将当前字符去除，并返回为结果
- 如果不是则继续遍历，找下一个相同的字符
- 如果没有找到比后一个字符小的，则将最后一个和digit相同的字符删除即可
### 代码

```java
class Solution {
  public String removeDigit(String number, char digit) {
    int len = number.length(), lastIndex = len - 1;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < len; i++) {
      char c = number.charAt(i);
      sb.append(c);
      if (c != digit) {
        continue;
      }

      if (i == len - 1) {
        lastIndex = i;
        continue;
      }

      if (c < number.charAt(i + 1)) {
        sb.deleteCharAt(sb.length() - 1);
        sb.append(number.substring(i + 1));
        return sb.toString();
      }

      lastIndex = i;
    }
    sb.deleteCharAt(lastIndex);
    return sb.toString();
  }
}
```
# [Contest_2_必须拿起的最小连续卡牌数](https://leetcode-cn.com/problems/minimum-consecutive-cards-to-pick-up/)
## 解法
### 思路
- 遍历cards，使用map存储所有元素对应的坐标
- 遍历map，将所有坐标进行比较，得到最短距离
### 代码
```java
class Solution {
    public int minimumCardPickup(int[] cards) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < cards.length; i++) {
            int num = cards[i];
            map.computeIfAbsent(num, x -> new ArrayList<>()).add(i);
        }

        int min = Integer.MAX_VALUE;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() < 2) {
                continue;
            }

            List<Integer> list = entry.getValue();
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i + 1) - list.get(i) + 1 < min) {
                    min = list.get(i + 1) - list.get(i) + 1;
                }
            }
        }
        
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
```
## 解法二
### 思路
- 在解法一的基础上，不需要记录所有的坐标，只要将相邻两个坐标进行比较即可
- 还是用map存储，但是value不再是list，而是与当前元素值相同的上一个元素的坐标
  - 遍历的元素如果在map中不存在，就初始化，放入值和坐标
  - 如果map中存在，就用当前坐标和map中存储的坐标计算出差值，然后和暂存的最短距离作比较，留较短的那个值即可
- 遍历结束后返回结果
- 暂存值ans初始化为int最大值，如果遍历结束后，ans还是int最大值，那么就说明没有找到题目要求的距离，返回-1
### 代码
```java
class Solution {
    public int minimumCardPickup(int[] cards) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < cards.length; i++) {
            int card = cards[i];
            if (map.containsKey(card)) {
                ans = Math.min(ans, i - map.get(card) + 1);
            }
            map.put(card, i);
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
```
# [Contest_3_含最多k个可整除元素的子数组](https://leetcode-cn.com/problems/k-divisible-elements-subarrays/)
## 解法
### 思路
2层循环遍历，计算是否有符合情况的，然后统计出来
### 代码
```java
class Solution {
    public int countDistinct(int[] nums, int k, int p) {
        int len = nums.length, count = 0;
        Set<String> set = new HashSet<>();

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (check(nums, i, j, k, p)) {
                    String cur = Arrays.toString(Arrays.copyOfRange(nums, i, j + 1));
                    if (set.add(cur)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private boolean check(int[]nums, int start, int end, int k, int p) {
        int count = 0;
        for (int i = start; i <= end; i++) {
            if (nums[i] % p == 0) {
                count++;
            }

            if (count > k) {
                return false;
            }
        }

        return true;
    }
}
```
# [Contest_4_字符串的总引力](https://leetcode-cn.com/problems/total-appeal-of-a-string/)
## 解法
### 思路
dp：
- 统计以s[i]为结尾的字符串总引力数
- 比较s[i]和s[i - 1]之间的变化关系
  - 如果当前字符在过往没有出现过，那么总引力数就会增加i + 1个（i从0开始计数，所以要+1，代表长度）
  - 如果当前字符在过往出现过，出现的坐标是j，那么0-j区间的总引力数是不增加的，增加的是i-j区间的总引力数
- 所以可以通过一维数组统计字符出现的坐标，并初始化为-1，这样就能够通过该数组将上述的2种状态所产生的新的子串的引力值计算出来
- 然后通过一个变量，curSum来记录上一个s[i-1]对应的总引力值
- 最后每次循环字符的时候，将得到的curSum累加到总值ans中即可
### 代码
```java
class Solution {
    public long appealSum(String s) {
        int len = s.length();
        int[] pos = new int[26];
        Arrays.fill(pos, -1);

        long curSum = 0, ans = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            curSum += i - pos[c - 'a'];
            pos[c - 'a'] = i;
            ans += curSum;
        }

        return ans;
    }
}
```