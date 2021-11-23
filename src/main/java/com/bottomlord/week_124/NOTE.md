# [LeetCode_384_打乱数组](https://leetcode-cn.com/problems/shuffle-an-array/)
## 解法
### 思路
使用random函数暴力解
### 代码
```java
class Solution {
    int[] origin;
    public Solution(int[] nums) {
        this.origin = nums;
    }

    public int[] reset() {
        return origin;
    }

    public int[] shuffle() {
        List<Integer> list = new ArrayList<>();
        for (int num : origin) {
            list.add(num);
        }

        int[] nums = new int[origin.length];
        Random ran = new Random();
        for (int i = 0; i < origin.length; i++) {
            int index = ran.nextInt(list.size());
            nums[i] = list.get(index);
            list.remove(index);
        }

        return nums;
    }
}
```
## 解法二
### 思路
- 解法一中，shuffle函数里，对存储数组的list的remove操作，时间复杂度较高，可以对这一操作进行优化
- 可以在remove的过程中，将使用的数字放置到list的顶部，然后随机就从之后的坐标中选择
### 代码
```java
class Solution {
    int[] origin;
    public Solution(int[] nums) {
        this.origin = nums;
    }

    public int[] reset() {
        return origin;
    }

    public int[] shuffle() {
        int[] copy = new int[origin.length], nums = new int[origin.length];
        System.arraycopy(origin, 0, copy, 0, origin.length);

        Random ran = new Random();
        for (int i = 0; i < origin.length; i++) {
            int j = ran.nextInt(origin.length - i);
            nums[i] = copy[i + j];
            int tmp = copy[i];
            copy[i] = copy[i + j];
            copy[i + j] = tmp;
        }

        return nums;
    }
}
```
# [LeetCode_859_亲密字符串](https://leetcode-cn.com/problems/buddy-strings/)
## 解法
### 思路
模拟：
- 遍历字符串s和goal
- 计算不同的字符个数，并记录不同的字符
- 如果不同的字符大于2个，就返回false
- 如果4个不同的字符并不同也返回false
- 如果两个字符串完全相等，就看字符串中是否有相同的字符，如果有是true，否则也是false
- 2个字符串需要完全相等
### 代码
```java
class Solution {
    public boolean buddyStrings(String s, String goal) {
        int len = s.length(), count = 0;
        if (len != goal.length()) {
            return false;
        }

        char d1 = ' ', d2 = ' ';
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                count++;

                if (count > 2) {
                    return false;
                }

                if (count == 1) {
                    d1 = s.charAt(i);
                    d2 = goal.charAt(i);
                    continue;
                }

                if (s.charAt(i) != d2 || goal.charAt(i) != d1) {
                    return false;
                }
            }
        }

        if (count == 1) {
            return false;
        }
        
        if (count == 0) {
            int[] bucket = new int[26];
            for (char c : goal.toCharArray()) {
                bucket[c - 'a']++;
            }
            for (int i : bucket) {
                if (i >= 2) {
                    return true;
                }
            }
            
            return false;
        }
        
        return true;
    }
}
```