# [LeetCode_401_二进制手表](https://leetcode-cn.com/problems/binary-watch/submissions/)
## 解法
### 思路
- 枚举所有0到59的10进制对应二进制数中为1的位的个数，做关联映射
- 2层循环，外层循环12个小时，内层循环60分钟，判断哪些时间的个数总和是目标值，如果是就转译为字符串
### 代码
```java
class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        for (int i = 1; i < 60; i++) {
            int count = 1, j = i;
            while ((j &= (j - 1)) != 0) {
                count++;
            }
            
            map.put(i, count);
        }
        
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (map.get(i) + map.get(j) == turnedOn) {
                    String min = j < 10 ? "0" + j : "" + j;
                    ans.add(i + ":" + min);
                }
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_offer38_1_字符串的排列](https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/)
## 解法
### 思路
记忆化+回溯
### 代码
```java
class Solution {
    public String[] permutation(String s) {
        Set<String> ans = new HashSet<>();
        backTrack(s, new StringBuilder(), new HashSet<>(), ans);
        return ans.toArray(new String[0]);
    }

    private void backTrack(String s, StringBuilder sb, Set<Integer> memo, Set<String> ans) {
        if (memo.size() == s.length()) {
            ans.add(sb.toString());
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            if (memo.contains(i)) {
                continue;
            }

            int len = sb.length();
            sb.append(s.charAt(i));
            memo.add(i);
            backTrack(s, sb, memo, ans);
            memo.remove(i);
            sb.setLength(len);
        }
    }
}
```
## 解法二
### 思路
在解法一回溯的基础上，使用旋转的方式替换追加的方式来记录当前枚举的字符串状态，同时在回溯的过程中，在每一层用一个布尔数组来记录当前层是否用过遍历到的字符，如果用到了就跳过，否则就用当前遍历到的字符作为当前层的字符
### 代码
```java
class Solution {
    public String[] permutation(String s) {
        List<String> ans = new ArrayList<>();
        backTrack(s.toCharArray(), 0, ans);
        return ans.toArray(new String[0]);
    }
    
    private void backTrack(char[] cs, int index, List<String> ans) {
        if (index == cs.length - 1) {
            ans.add(new String(cs));
            return;
        }
        
        boolean[] used = new boolean[256];
        for (int i = index; i < cs.length; i++) {
            if (used[cs[i]]) {
                continue;
            }
            
            used[cs[i]] = true;
            swap(cs, index, i);
            backTrack(cs, index + 1, ans);
            swap(cs,index, i);
        }
    }
    
    private void swap(char[] cs, int x, int y) {
        char c = cs[x];
        cs[x] = cs[y];
        cs[y] = c;
    }
}
```