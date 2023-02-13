# [LeetCode_1234_替换子串得到平衡字符串](https://leetcode.cn/problems/replace-the-substring-for-balanced-string/)
## 解法
### 思路
双指针
- 初始化字母出现的个数
- 右指针用于确定区间的右边界，寻找右边的过程中，对出现个数做递减，同时判断个数是否全部匹配。这个动作的意义可以理解成找到窗口右边满足要求的最大长度
- 左指针用于确定区间的左边界，然后基于右边界，找到窗口左侧的最大长度，从而确定一个可能
### 代码
```java
class Solution {
    public int balancedString(String s) {
        int l = 0, r = 0, n = s.length(), ans = n;
        int[] bucket = new int[26];
        for (char c : s.toCharArray()) {
            bucket[c - 'A']++;
        }
        
        boolean ok = true;

        for (int num : bucket) {
            if (num > n / 4) {
                ok = false;
                break;
            }
        }
        
        if (ok) {
            return 0;
        }
        
        for (; r < s.length(); r++) {
            bucket[s.charAt(r) - 'A']--;
            ok = true;
            
            for (int num : bucket) {
                if (num > n / 4) {
                    ok = false;
                    break;
                }
            }

            while (ok) {
                ans = Math.min(ans, r - l + 1);
                bucket[s.charAt(l) - 'A']++;

                if (bucket[s.charAt(l) - 'A'] > n / 4) {
                    ok = false;
                }

                l++;
            }
        }

        return ans;
    }
}
```