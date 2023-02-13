# [LeetCode_1234_替换子串得到平衡字符串](https://leetcode.cn/problems/replace-the-substring-for-balanced-string/)
## 解法
### 思路
双指针
- 初始化字母出现的个数
- 右指针用于确定区间的右边界，寻找右边的过程中，对出现个数做递减，同时判断个数是否全部匹配。这个动作的意义可以理解成找到窗口右边满足要求的最大长度
- 左指针用于确定区间的左边界，然后基于右边界，找到窗口左侧的最大长度
- 左右指针确定后就更新最小窗口
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
# [LeetCode_2562_找出数组的串联值](https://leetcode.cn/problems/find-the-array-concatenation-value/)
## 解法
### 思路
双指针+模拟
### 代码
```java
class Solution {
    public long findTheArrayConcVal(int[] nums) {
        int l = 0, r = nums.length - 1;
        long ans = 0;

        while (l <= r) {
            long num = 0;
            int index = 0;
            while (nums[r] > 0) {
                int cur = 1;
                for (int i = 0; i < index; i++) {
                    cur *= 10;
                }
                
                num = (nums[r] % 10L) * cur + num;
                nums[r] /= 10;
                index++;
            }

            while (nums[l] > 0) {
                int cur = 1;
                for (int i = 0; i < index; i++) {
                    cur *= 10;
                }

                num = (nums[l] % 10L) * cur + num;
                nums[l] /= 10;
                index++;
            }

            ans += num;
            l++;
            r--;
        }

        return ans;
    }
}
```