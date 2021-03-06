# [LeetCode_458_可怜的小猪](https://leetcode-cn.com/problems/poor-pigs/)
## 解法
### 思路
- 按照简单推算：
    - 1只猪在能够品尝n次的情况下，可以推算`n + 1`瓶水
    - 2只猪能够推算`(n + 1) ^ 2`瓶水
    - m只猪能够推算`(n + 1) ^ m`瓶水
- 那么题目变成了求(n+1)的多少次幂是最小的大于水瓶数的值
- 这篇文章很好说明了如何去求m头猪喝水n次能够判断的水瓶个数：[链接](https://www.zhihu.com/question/60227816/answer/1274071217)
### 代码
```java
class Solution {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int n = minutesToTest / minutesToDie + 1;
        return (int)Math.ceil(Math.log(buckets) / Math.log(n));
    }
}
```
# [LeetCode_464_我能赢吗](https://leetcode-cn.com/problems/can-i-win/)
## 解法
### 思路
记忆化回溯
- 用一个数组记录当前哪些数字被使用过，1代表使用，0代表没有使用
- 使用map的key记录当前数组状态，对应的value记录当前这种状态的结果
- 回溯的过程就是遍历所有的数，判断当前是否已经能达到目标值，或者对方下一次不能达到目标值（递归获得对方执行的结果）
- 回溯过程中记得状态恢复，并记录数字使用状态和对应结果的关系，做到剪枝
### 代码
```java
class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }
        
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }
        
        int[] state = new int[maxChoosableInteger + 1];
        
        return backTrack(desiredTotal, state, new HashMap<>());
    }
    
    private boolean backTrack(int desiredTotal, int[] state, Map<String, Boolean> memo) {
        String key = Arrays.toString(state);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        for (int i = 1; i < state.length; i++) {
            if (state[i] == 0) {
                state[i] = 1;
                if (desiredTotal - i <= 0 || !backTrack(desiredTotal - i, state, memo)) {
                    memo.put(key, true);
                    state[i] = 0;
                    return true;
                }
                state[i] = 0;
            }
        }
        
        memo.put(key, false);
        return false;
    }
}
```
## 解法二
### 思路
- 使用位来记录状态，使用Boolean数组记录不同位代表的值对应的状态，总的状态数就是2的可选数次幂
- 因为上一个解法中，使用的是一个数组来记录状态，并转化为字符串存入记忆化map中，所以需要回溯状态，而当前数组就类似于之前map的作用，所以可以省去回溯的动作
### 代码
```java
class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }

        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) {
            return false;
        }
        
        Boolean[] states = new Boolean[(1 << maxChoosableInteger) - 1];
        return backTrack(states, 0, maxChoosableInteger, desiredTotal);
    }
    
    private boolean backTrack(Boolean[] states, int state, int maxChoosableInteger, int desiredTotal) {
        if (states[state] != null) {
            return states[state];
        }

        for (int i = 1; i <= maxChoosableInteger; i++) {
            int cur = 1 << (i - 1);
            if ((cur & state) == 0) {
                if (desiredTotal - i <= 0 || !backTrack(states, state | cur, maxChoosableInteger, desiredTotal - i)) {
                    states[state] = true;
                    return true;
                }
            }

        }

        states[state] = false;
        return false;
    }
}
```
# LeetCode_465_最优账单平衡
## 解法
### 思路
- 如果一个人借出和借入是一样的，那么这个人就可以从借贷关系中脱离出来
- 借出和借入最后集中到同一个人身上后，算是最后一次处理借贷关系
- 然后尝试使用哪种路径可以最快的处理完所有的借贷关系
### 代码
```java
class Solution {
    private int ans = Integer.MAX_VALUE;
    public int minTransfers(int[][] transactions) {
        int len = transactions.length;
        Map<Integer, Integer> sum = new HashMap<>();
        for (int i = 0; i < len; i++) {
            sum.put(transactions[i][0], sum.getOrDefault(transactions[i][0], 0) + transactions[i][2]);
            sum.put(transactions[i][1], sum.getOrDefault(transactions[i][1], 0) - transactions[i][2]);
        }

        List<Integer> list = new ArrayList<>();
        for (Integer key : sum.keySet()) {
            if (sum.get(key) != 0) {
                list.add(sum.get(key));
            }
        }

        dfs(0, 0, list);
        return ans;
    }

    private void dfs(int index, int count, List<Integer> list) {
        if (count > ans) {
            return;
        }

        while (index < list.size() && list.get(index) == 0) {
            index++;
        }

        if (index == list.size()) {
            ans = Math.min(ans, count);
            return;
        }

        for (int i = index + 1; i < list.size(); i++) {
            if (list.get(index) * list.get(i) < 0) {
                list.set(i, list.get(i) + list.get(index));
                dfs(index + 1, count + 1, list);
                list.set(i, list.get(i) - list.get(index));
            }
        }
    }
}
```
# [LeetCode_467_环绕字符串中唯一的子字符串](https://leetcode-cn.com/problems/unique-substrings-in-wraparound-string/)
## 解法
### 思路
- 题目的意思是在p串中找到连续的s和p的子串，s因为是从a到z的连续循环字符串，所以相当于求以某个字母为结尾的连续子串个数的总和
- 通过滑动窗口求
- 过程：
    - 定义窗口右边界r，循环遍历该右边界
    - 定义变量sum，暂存当前窗口的可能序列个数
    - 循环中判断右边界对应的元素与前一个元素是否符合题目要求的连续规则
        - 如果符合，就累加sum值，累加后得到的值，就是当前窗口因为新增了一个元素而能够组成的新的子数组
        - 如果不符合，就重置sum值为1，代表当前右边界只能生成1个子数组
    - 每次循环都判断当前元素对应的字母能够组成的子序列的个数和已存在的该字母的个数大小，取大的值更新
### 代码
```java
class Solution {
    public int findSubstringInWraproundString(String p) {
        if (p == null || p.length() == 0) {
            return 0;
        }
        
        int sum = 1;
        int[] arr = new int[26];
        arr[p.charAt(0) - 'a'] = sum;
        
        for (int r = 1; r < p.length(); r++) {
            if (p.charAt(r) - p.charAt(r - 1) == 1 || p.charAt(r) - p.charAt(r - 1) == -25) {
                sum++;
            } else {
                sum = 1;
            }
            
            arr[p.charAt(r) - 'a'] = Math.max(arr[p.charAt(r) - 'a'], sum);
        }
        
        return Arrays.stream(arr).sum();
    }
}
```
# [LeetCode_468_验证IP地址](https://leetcode-cn.com/problems/validate-ip-address/)
## 解法
### 思路
根据2种ip编码的要求判断字符串
### 代码
```java
class Solution {
    public String validIPAddress(String IP) {
        if (IP.chars().filter(x -> x == '.').count() == 3) {
            return validateIp4(IP) ? "IPv4" : "Neither";
        }

        if (IP.chars().filter(x -> x == ':').count() == 7) {
            return validateIp6(IP) ? "IPv6" : "Neither";
        }

        return "Neither";
    }

    private boolean validateIp4(String ip) {
        String[] strs = ip.split("\\.");
        if (strs.length != 4) {
            return false;
        }

        for (String str : strs) {
            if (str.length() == 0 || str.length() > 3) {
                return false;
            }

            if (str.startsWith("0") && str.length() > 1) {
                return false;
            }

            int num;
            try {
                num = Integer.parseInt(str);
            } catch (Exception e) {
                return false;
            }

            if (num < 0 || num > 255) {
                return false;
            }
        }

        return true;
    }

    private boolean validateIp6(String ip) {
        String[] strs = ip.split(":");
        String hexdigits = "0123456789abcdefABCDEF";
        if (strs.length != 8) {
            return false;
        }

        for (String str : strs) {
            if (str.length() == 0 || str.length() > 4) {
                return false;
            }

            for (char c : str.toCharArray()) {
                if (hexdigits.indexOf(c) == -1) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
```
# [LeetCode_1178_猜字谜](https://leetcode-cn.com/problems/number-of-valid-words-for-each-puzzle/)
## 失败解法
### 原因
超时
### 思路
暴力
### 代码
```java
class Solution {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < puzzles.length; i++) {
            boolean[] dict = new boolean[26];
            
            for (char c : puzzles[i].toCharArray()) {
                dict[c - 'a'] = true;
            }
            int count = 0;
            for (String word : words) {
                boolean matchHead = false, matchAll = true;
                for (char c : word.toCharArray()) {
                    if (c == puzzles[i].charAt(0)) {
                        matchHead = true;
                    }

                    if (!dict[c - 'a']) {
                        matchAll = false;
                        break;
                    }
                }

                if (matchAll && matchHead) {
                    count++;
                }
            }

            ans.add(count);
        }
        
        return ans;
    }
}
```
## 失败解法二
### 原因
超时
### 思路
在解法1的基础上，对words提前做桶计数
### 代码
```java
class Solution {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> ans = new ArrayList<>();
        HashSet<Character>[] wordDict = new HashSet[words.length];
        for (int i = 0; i < words.length; i++) {
            wordDict[i] = new HashSet();
            for (char c : words[i].toCharArray()) {
                wordDict[i].add(c);
            }
        }

        for (int i = 0; i < puzzles.length; i++) {
            boolean[] pDict = new boolean[26];
            for (char c : puzzles[i].toCharArray()) {
                pDict[c - 'a'] = true;
            }

            int count = 0;
            for (HashSet<Character> wDict : wordDict) {
                if (!wDict.contains(puzzles[i].toCharArray()[0])) {
                    continue;
                }

                boolean miss = false;
                for (Character c : wDict) {
                    if (!pDict[c - 'a']) {
                        miss = true;
                        break;
                    }
                }

                if (!miss) {
                    count++;
                }
            }

            ans.add(count);
        }

        return ans;
    }
}
```
## 解法
### 思路
- 使用二进制来存储words以及puzzle的状态
- 首先遍历words，将每一个word存储到一个数字中，然后放入map中进行计数
- 然后遍历puzzle，生成二进制数，根据公式`mask & (sub - 1)`遍历所有当前puzzle的可能二进制值，如果匹配就累加map中的值
### 代码
```java
class Solution {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Map<Integer, Integer> map = new HashMap<>();
        for (String word : words) {
            int mask = 0;
            for (char c : word.toCharArray()) {
                mask |= (1 << (c - 'a'));
            }

            if (Integer.bitCount(mask) <= 7) {
                map.put(mask, map.getOrDefault(mask, 0) + 1);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (String puzzle : puzzles) {
            int mask = 0;
            for (int i = 1; i < puzzle.length(); i++) {
                mask |= (1 << (puzzle.charAt(i) - 'a'));
            }

            int sub = mask, count = 0;
            do {
                int tmp = sub | (1 << (puzzle.charAt(0) - 'a'));
                if (map.containsKey(tmp)) {
                    count += map.get(tmp);
                }
                sub = mask & (sub - 1);
            } while (sub != mask);

            ans.add(count);
        }

        return ans;
    }
}
```
# [LeetCode_469_凸多边形](https://leetcode-cn.com/problems/convex-polygon/)
## 解法
### 思路
- 假设当前连续的三个顶点分别是p1、p2、p3，计算向量p1p2和p2p3的叉乘，得到的结果如果大于0，则表示p3点在向量p1p2的左侧，多边形的顶点是逆时针序列。
- 叉乘的计算公式：`A*B - B*C`
- 然后再依次计算下一个顶点前后所组成向量的叉乘，如果在计算时，出现负值，则此多边形时凹多边形，如果所有顶点计算完毕，其结果都是大于0，则多边形时凸多边形。
### 代码
```java
class Solution {
    public boolean isConvex(List<List<Integer>> points) {
        int n = points.size();
        long cur, pre = 0;
        for (int i = 0; i < n; i++) {
            cur = crossProduct(points.get((i + 1) % n).get(0) - points.get(i).get(0),
                    points.get((i + 1) % n).get(1) - points.get(i).get(1),
                    points.get((i + 2) % n).get(0) - points.get(i).get(0),
                    points.get((i + 2) % n).get(1) - points.get(i).get(1));
            if (cur != 0) {
                if (cur * pre < 0) {
                    return false;
                }
                pre = cur;
            }
        }
        return true;
    }

    private long crossProduct(long x1, long y1, long x2, long y2) {
        return x1 * y2 - x2 * y1;
    }
}
```