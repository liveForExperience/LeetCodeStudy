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