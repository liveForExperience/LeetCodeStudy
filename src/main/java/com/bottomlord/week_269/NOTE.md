# [LeetCode_2708_一个小组的最大实力值](https://leetcode.cn/problems/maximum-strength-of-a-group)
## 解法
### 思路
- 将`nums`分成3部分
  - 正数
  - 0
  - 负数
- 因为题目要得到乘积最大值，所以
  - 正数元素累乘的结果是随元素个数增加而线性增加的，直接累乘正数即可
  - 负数元素部分，简单想，需要累乘偶数个，否则如果是奇数个会导致结果为负
- 但是在一些具体场景中，还有一些出入：
  - 如果只有0，那么就直接返回0作为结果
  - 如果没有正数，且负数元素只有1个：
    - 那么有0，就返回0
    - 没有0就返回唯一的那个负数
- 分别将正数和负数存储到列表中，如果有0，则通过一个布尔变量来记录
- 为了得到乘积最大值，负数列表需要被排序
- 然后在需要取偶数个的时候，取最小的`n - 1`个即可
### 代码
```java
class Solution {
    public long maxStrength(int[] nums) {
        List<Integer> ps = new ArrayList<>(), ns = new ArrayList<>();
        boolean hasZero = false;
        for (int num : nums) {
            if (num > 0) {
                ps.add(num);
            } else if (num < 0) {
                ns.add(num);
            } else {
                hasZero = true;
            }
        }

        if (ps.isEmpty() && ns.isEmpty()) {
            return 0;
        }

        long sum = 1L;
        for (Integer p : ps) {
            sum *= p;
        }
        
        if (ps.isEmpty() && ns.size() == 1) {
            return hasZero ? 0 : ns.get(0);
        }

        int i = ns.size() - 1;
        if (ns.size() % 2 == 1) {
            Collections.sort(ns);
            i = ns.size() - 2;
        }
        
        for (; i >= 0; i--) {
            sum *= ns.get(i);
        }

        return sum;
    }
}
```
## 解法二
### 思路
- 暂存2个变量
  - `min`：遍历到当前得到的最小值
  - `max`：遍历到当前得到的最大值
- 之所以暂存这两个变量，是因为数组中存在正负数，当希望一次遍历得到结果的目标时，每一次遍历到的元素，一定会经历选择和不选择
  - 不选择很好处理，就保持暂存变量原状即可
  - 如果是选择，那么其实对可能结果的影响路径就有3条
    - 不考虑以前的暂存变量，只以当前元素作为可能的结果
    - 与历史的暂存变量累乘，得到可能的最小值，这个最小值又可能在未来通过乘以一个负数得到最终的最大值
    - 与历史的暂存变量累乘，得到可能的最大值
- 为了维护`max`和`min`，可以通过如上的推导获得：
  - `max(num, max, max * num, min * num)`
  - `min(num, min, max * num, min * num)`
- 在遍历完所有变量后，那个暂存的最大值就是最终可能的最大
### 代码
```java
class Solution {
    public long maxStrength(int[] nums) {
        long max = nums[0], min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            long curMax = max, curMin = min;
            min = Math.min(Math.min(min, num), Math.min(curMin * num, curMax * num));
            max = Math.max(Math.max(max, num), Math.max(curMin * num, curMax * num));
        }
        
        return max;
    }
}
```
# [LeetCode_2860_让所有学生保持开心的分组方法数](https://leetcode.cn/problems/happy-students)
## 解法
### 思路
- 将`nums`按从小到大排序
- 遍历`nums`，所遍历的元素作为排序后的`nums`中连续子数组的结束元素，这个连续子数组可以视为被选择的同学，`i + 1`就是被选择同学的个数，`n - i`就是没被选择同学的个数
- 根据遍历到的元素的坐标和值，可以判定当前元素是否符合题目的要求，即：
  - `nums[i] < i + 1`
  - `nums[i + 1] > i + 1`
- 另外需要考虑两种特殊情况：
  - 不选任何学生：此时只要考虑是否符合`nums[0] > 0`，如果符合就在暂存值上累加1
  - 选择所有学生：此时只要考虑`nums[n - 1] < n`，如果符合就在暂存值上累加1
- 遍历过程中，一旦匹配如上列举的情况，就累加可能值。遍历结束后再匹配如上2种特殊情况，经过处理后返回暂存值即可。
### 代码
```java
class Solution {
    public int countWays(List<Integer> nums) {
        int ans = 0, n = nums.size();
        Collections.sort(nums);
        for (int i = 0; i < n - 1; i++) {
            if (nums.get(i) < i + 1 &&  nums.get(i + 1) > i + 1) {
                ans++;
            }
        }
        
        if (nums.get(n - 1) < n) {
            ans++;
        }

        if (nums.get(0) > 0) {
            ans++;
        }

        return ans;
    }
}
```
# [LeetCode_3174_清除数字](https://leetcode.cn/problems/clear-digits)
## 解法
### 思路
- 初始化一个`StringBuilder`对象`sb`，用于记录最终的字符串（因为题目保证示例一定能够有效删除所有的数字字符，所以这个结果中一定至多只有字母字符）
- 遍历字符串
  - 遇到字母字符，就将该字符拼接在`sb`后
  - 遇到数字字符，就将`sb`最后的字符删去
- 遍历结束后返回`sb`转为字符串的对象作为结果即可
### 代码
```java
class Solution {
  public String clearDigits(String s) {
    char[] cs = s.toCharArray();
    StringBuilder sb = new StringBuilder();
    for (char c : cs) {
      if (Character.isDigit(c)) {
        sb.deleteCharAt(sb.length() - 1);
      } else {
        sb.append(c);
      }
    }
    return sb.toString();
  }
}
```