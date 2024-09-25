# [LeetCode_1376_通知所有员工所需的时间](https://leetcode.cn/problems/time-needed-to-inform-all-employees/)
## 解法
### 思路
自底向上的dfs
- map生成上级与下级列表之间的映射关系
- 递归方式实现bfs，每一层递归的主逻辑是为获取当前manager发送通知的最大耗时：`自身的informTime + max(下属及下属通知的总耗时)`
### 代码
```java
class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTimes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < manager.length; i++) {
            map.computeIfAbsent(manager[i], x -> new ArrayList<>()).add(i);
        }
        
        return bfs(headID, map, informTimes);
    }
    
    private int bfs(int manager, Map<Integer, List<Integer>> map, int[] informTimes) {
        int curTime = informTimes[manager], cost = 0;
        for (Integer emp : map.getOrDefault(manager, new ArrayList<>())) {
            cost = Math.max(cost, bfs(emp, map, informTimes));
        }
        return curTime + cost;
    }
}
```
## 解法二
### 思路
自顶向下的dfs
- 初始化一个time数组
- 遍历n个员工，递归过程主要是通过dfs计算出从顶到到达当前层的耗时cost
- 当前层的总耗时就是`cost + informTime[cur]`
- 遍历结束后，返回最大值，这个最大值可以在遍历过程中通过临时变量动态维护
### 代码
```java
class Solution {
    public int numOfMinutes(int n, int headID, int[] managers, int[] informTimes) {
        int[] costs = new int[n];
        Arrays.fill(costs, -1);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(i, costs, informTimes, managers));
        }
        return ans;
    }

    private int dfs(int cur, int[] costs, int[] informTimes, int[] managers) {
        int manager = managers[cur];

        if (manager == -1) {
            return costs[cur] = informTimes[cur];
        }

        if (costs[manager] == -1) {
            costs[manager] = dfs(manager, costs, informTimes, managers);
        }

        return costs[cur] = costs[manager] + informTimes[cur];
    }
}
```
# [LeetCode_970_强整数](https://leetcode.cn/problems/powerful-integers/)
## 解法
### 思路
暴力枚举
### 代码
```java
class Solution {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        int v1 = 1;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 21; i++) {
            int v2 = 1;
            for (int j = 0; j < 21; j++) {
                if (v1 + v2 <= bound) {
                    set.add(v1 + v2);
                } else {
                    break;
                }

                v2 *= y;
            }

            v1 *= x;

            if (v1 > bound) {
                break;
            }
        }

        return new ArrayList<>(set);
    }
}
```
# [LeetCode_1003_检查替换后的词是否有效](https://leetcode.cn/problems/check-if-word-is-valid-after-substitutions/)
## 解法
### 思路
- 使用String API，通过s.indexOf() != -1作为循环条件，检查字符串中是否存在连续的`abc`子字符串
- 如果存在就将该子字符串从s中剔除，继续循环
- 如果不存在，就退出循环，并判断字符串是否为空
### 代码
```java
class Solution {
    public boolean isValid(String s) {
        int i;
        while ((i = s.indexOf("abc")) != -1) {
            s = s.substring(0, i) + s.substring(i + 3);
        }

        return s.length() == 0;
    }
}
```
## 解法二
### 思路
栈
- 遍历字符串
  - 如果不是c，将字符压入栈中
  - 如果是c，判断如下几件事
    - 栈是否有至少2个元素
    - 弹出的第一个元素是否是b
    - 弹出的第二个元素是否是a
    - 如果都符合，则继续循环，否则就返回false
- 遍历结束，判断栈是否为空
### 代码
```java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == 'c') {
                if (stack.size() < 2) {
                    return false;
                }

                if (stack.pop() != 'b') {
                    return false;
                }

                if (stack.pop() != 'a') {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
}
```
## 解法三
### 思路
使用字符数组作为简易的栈
- 初始化一个变量i作为栈指针
- 初始化字符串s的字符数组cs
- 遍历cs
  - 如果字符不是c，那么将字符压入栈，然后指针i向右移动一位
  - 如果字符是c，那么判断如下三件事
    - 指针是否大于1
    - 最后2个字符是否依次是b和a
    - 如果任意不符合，就返回false
- 遍历结束后，返回坐标i是否为0
### 代码
```java
class Solution {
    public boolean isValid(String s) {
        int i = 0;
        char[] cs = s.toCharArray();
        for (char c : cs) {
            if (c == 'c') {
                if (i < 2 || cs[--i] != 'b' || cs[--i] != 'a') {
                    return false;
                }
            } else {
                cs[i++] = c;
            }
        }

        return i == 0;
    }
}
```
# [LeetCode_2651_计算列车到站时间](https://leetcode.cn/problems/calculate-delayed-arrival-time/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
  public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
    return (arrivalTime + delayedTime) % 24;
  }
}
```
# [LeetCode_2652_倍数求和](https://leetcode.cn/problems/sum-multiples/)
## 解法
### 思路
遍历判断并累加求和
### 代码
```java
class Solution {
  public int sumOfMultiples(int n) {
    int sum = 0;
    for (int i = 3; i <= n; i++) {
      if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0) {
        sum += i;
      }
    }
    return sum;
  }
}
```
# [LeetCode_2656_K哥元素的最大和](https://leetcode.cn/problems/maximum-sum-with-exactly-k-elements/)
## 解法
### 思路
- 遍历找到最大值
- 根据k和最大值max，求步长为1的等差数列之和：`(max + (max + k - 1)) * k / 2`
### 代码
```java
class Solution {
    public int maximizeSum(int[] nums, int k) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        
        return (max * 2 + k - 1) * k / 2;
    }
}
```
# [LeetCode_2660_保龄球游戏的获胜者](https://leetcode.cn/problems/determine-the-winner-of-a-bowling-game/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
  public int isWinner(int[] player1, int[] player2) {
    int x = 0, y = 0, n = player1.length;
    for (int i = 0; i < n; i++) {
      x += getScore(player1, i);
      y += getScore(player2, i);
    }

    return x == y ? 0 : x > y ? 1 : 2;
  }

  private int getScore(int[] players, int index) {
    if (index == 0) {
      return players[index];
    }

    if (index == 1) {
      return players[index - 1] == 10 ? 2 * players[index] : players[index];
    }

    return isTen(players[index - 1], players[index - 2]) ? 2 * players[index] : players[index];
  }

  private boolean isTen(int pre1, int pre2) {
    return pre1 == 10 || pre2 == 10;
  }
}
```
# [LeetCode_LCP72_补给马车]()
## 失败解法
### 原因
超时
### 思路
模拟
### 代码
```java
class Solution {
    public int[] supplyWagon(int[] supplies) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int supply : supplies) {
            list.add(supply);
        }

        int n = supplies.length / 2;
        while (list.size() > n) {
            int min = Integer.MAX_VALUE, target = -1;
            for (int i = 0; i < list.size() - 1; i++) {
                int sum = list.get(i) + list.get(i + 1);
                if (sum < min) {
                    min = sum;
                    target = i;
                }
            }

            list.set(target, min);
            list.remove(target + 1);
        }

        int[] ans = new int[n];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
```
## 解法
### 思路
使用数组，过程类似
### 代码
```java
class Solution {
  public int[] supplyWagon(int[] supplies) {
    int n = supplies.length / 2;
    for (int i = 0; i < supplies.length - n; i++) {
      int index = 1, min = Integer.MAX_VALUE;
      for (int j = 1; j < supplies.length - i; j++) {
        int sum = supplies[j - 1] + supplies[j];
        if (sum < min) {
          index = j;
          min = sum;
        }
      }

      supplies[index - 1] = min;
      System.arraycopy(supplies, index + 1, supplies, index, supplies.length - index - 1);
    }

    return Arrays.copyOfRange(supplies, 0, n);
  }
}
```
# [LeetCode_962_最大宽度坡](https://leetcode.cn/problems/maximum-width-ramp/)
## 解法
### 思路
- 初始化一个坐标数组indexes
- 通过比较nums数组的大小值，来对对应的indexes数组进行排序
- 然后遍历indexes数组，因为此时，越靠右的元素值一定是越大的，所以宽度变成了，不断更新当前坐标左边最小的坐标值和当前坐标的差，取这个差的最大值作为结果就可以
- 遍历结束，返回暂存的最大值即可
### 代码
```java
class Solution {
    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        Integer[] indexes = new Integer[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }

        Arrays.sort(indexes, Comparator.comparingInt(x -> nums[x]));

        int min = n, ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, indexes[i] - min);
            min = Math.min(min, indexes[i]);
        }

        return ans;
    }
}
```
# [LeetCode_2106_摘水果](https://leetcode.cn/problems/maximum-fruits-harvested-after-at-most-k-steps/)
## 解法
### 思路
滑动窗口
- 摘水果的区间就是一个滑动窗口，他的左右边界对应的坐标为l和r
- startPosition与l及r的关系
  - startPosition在l的左边，那么最小步数就是r - startPosition
  - startPosition在r的右边，那么最小步数就是startPosition - l】
  - startPosition在l和r之间，那么就有2种行动的选择：
    - 先往l走，然后回过头往r走
    - 先往r走，然后回过头往l走
  - 这3种情况，都可以通过如下表达式来表示：`r - l + min(|startPosition - l|, |r - startPosition|)
- 初始化l和r都为0，通过嵌套循环来移动滑动窗口
  - 外层确定窗口的左边界，左边界只有在表达式值大于k的时候移动
  - 内层确定窗口的右边界
- 初始化一个变量sum用于记录窗口中的水果数量
- 遍历结束，也就是r越界，则返回最大的sum作为结果
### 代码
```java
class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int ans = 0, sum = 0, n = fruits.length;
        for (int l = 0, r = 0; r < n; r++) {
            sum += fruits[r][1];
            while (l <= r && fruits[r][0] - fruits[l][0] + Math.min(Math.abs(fruits[r][0] - startPos), Math.abs(startPos - fruits[l][0])) > k) {
                sum -= fruits[l++][1];
            }
            
            ans = Math.max(ans, sum);
        }
        
        return ans;
    }
}
```
# [LeetCode_966_元音拼写检查器](https://leetcode.cn/problems/vowel-spellchecker/)
## 解法
### 思路
- 初始化3个hash表
  - wordSet：用来存储wordList中的字符串元素
  - lowercaseMap：用来存储小写处理后的word与原字符串之间的映射关系
  - lowercaseNoVowelMap：用来存储小写处理，并将元音字符改成*后的字符串与原字符串之间的关系
- 遍历query列表，分别在3个hash表中检查是否包含当前的query字符串，其中lowercaseNoVowelMap在检查时，需要先对query字符串的原因字符做处理
### 代码
```java
class Solution {
    private Set<String> wordSet = new HashSet<>();
    private Map<String, String> lowercaseMap = new HashMap<>(), lowercaseNoVowelMap = new HashMap<>();
    public String[] spellchecker(String[] wordlist, String[] queries) {

        for (String word : wordlist) {
            wordSet.add(word);
            lowercaseMap.putIfAbsent(word.toLowerCase(), word);
            lowercaseNoVowelMap.putIfAbsent(handleVowel(word.toLowerCase()), word);
        }
        
        int n = queries.length;
        String[] ans = new String[n];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = solve(queries[i]);
        }
        return ans;
    }
    
    private String solve(String word) {
        if (wordSet.contains(word)) {
            return word;
        }
        
        if (lowercaseMap.containsKey(word.toLowerCase())) {
            return lowercaseMap.get(word.toLowerCase());
        }
        
        return lowercaseNoVowelMap.getOrDefault(handleVowel(word.toLowerCase()), "");
    }
    
    private String handleVowel(String word) {
        StringBuilder sb = new StringBuilder();
        char[] cs = word.toCharArray();
        for (char c : cs) {
            sb.append(isVowel(c) ? '*' : c);
        }
        return sb.toString();
    }
    
    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
```
# [LeetCode_1010_总持续时间可被60整除的歌曲](https://leetcode.cn/problems/pairs-of-songs-with-total-durations-divisible-by-60/)
## 解法
### 思路
- 先对times数组做取模60的操作，将判断数值的范围缩小到60个
- 初始化2个hash表：
  - map：存储times元素值与坐标的关系，value是一个列表，因为值会重复
  - indexMap：存储判断过程中，map的value列表扫描到的坐标，因为i与j的关系是`i<j`，所以从左往右判断的时候，判断过的坐标元素就不再需要考虑了，通过这个映射关系可以省略重复的判断过程
- 遍历times数组，初始化2个hash表
  - map：通过times[i]元素，填充坐标到value列表
  - indexMap：通过times[i]元素，初始化value为坐标0
- 遍历times数组，通过`(60 - times[i]) % 60`的操作，获得目标值，然后在map中寻找是否存在目标值对应的坐标列表
  - 如果不存在就跳过
  - 如果存在，就从indexMap中找到起始坐标，开始遍历，寻找到第一个j>i的坐标，记录到indexMap中，并累加`list.size - j`的长度
- 遍历结束后，返回累加的结果
### 代码
```java
class Solution {
    public int numPairsDivisibleBy60(int[] times) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < times.length; i++) {
            times[i] = times[i] % 60;
            indexMap.put(times[i], 0);
            map.computeIfAbsent(times[i], x -> new ArrayList<>()).add(i);
        }

        int ans = 0;
        for (int i = 0; i < times.length; i++) {
            int target = (60 - times[i]) % 60;
            if (!map.containsKey(target)) {
                continue;
            }

            List<Integer> indexes = map.get(target);
            for (int j = indexMap.get(target); j < indexes.size(); j++) {
                if (indexes.get(j) > i) {
                    indexMap.put(target, j);
                    ans += indexes.size() - j;
                    break;
                }
            }
        }

        return ans;
    }
}
```