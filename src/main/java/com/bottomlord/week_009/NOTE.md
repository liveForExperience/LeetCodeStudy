# LeetCode_680_验证回文字符串II
## 题目
给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。

示例 1:
```
输入: "aba"
输出: True
```
示例 2:
```
输入: "abca"
输出: True
解释: 你可以删除c字符。
```
注意:
```
字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
```
## 失败解法
### 思路
暴力解法，一个个试，有true返true，否则返回false
### 失败原因
超时
### 代码
```java
class Solution {
    public boolean validPalindrome(String s) {
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            int head = 0, tail = cs.length - 1;
            boolean flag = true;
            while (head < tail) {
                if (head == i) {
                    head++;   
                }
                
                if (tail == i) {
                    tail--;
                }
                
                if (cs[head] != cs[tail]) {
                    flag = false;
                    break;
                }
                
            head++;
            tail--;
            }
            
            if (flag) {
                return true;
            }
        }
        
        return false;
    }
}
```
## 解法
### 思路
- 其实只需要在两个字符不相等的情况下，判断两种情况：
    - head + 1到tail之间的字符串是否为回文字符串
    - head到tail-1之间的字符串是否为回文字符串
- 如果两种情况中的一种符合就是true，否则就是false
### 代码
```java
class Solution {
    public boolean validPalindrome(String s) {
        char[] cs = s.toCharArray();
        int head = 0, tail = cs.length - 1;
        while (head < tail) {
            if (cs[head] != cs[tail]) {
                return validate(head + 1, tail, cs) || validate(head, tail - 1, cs);
            } else {
                head++;
                tail--;
            }
        }
        
        return true;
    }
    
    private boolean validate(int start, int end, char[] cs) {
        while (start < end) {
            if (cs[start] != cs[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
```
# LeetCode_414_第三大的数
## 题目
给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。

示例 1:
```
输入: [3, 2, 1]

输出: 1

解释: 第三大的数是 1.
```
示例 2:
```
输入: [1, 2]

输出: 2

解释: 第三大的数不存在, 所以返回最大的数 2 .
```
示例 3:
```
输入: [2, 2, 3, 1]

输出: 1
```
```
解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
存在两个值为2的数，它们都排第二。
```
## 解法
### 思路
主要依赖Collections的api
- 遍历元素放入set
- 如果set的元素个数小于3个，返回最大值
- 找两次最大值并把最大值从set中取出
- 最后一次把set的最大值返回
### 代码
```java
class Solution {
    public int thirdMax(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        if (set.size() < 3) {
            return Collections.max(set);
        }
        
        for (int i = 0; i < 2; i++) {
            set.remove(Collections.max(set));
        }
        
        return Collections.max(set);
    }
}
```
## 解法二
### 思路
- 定义三个变量：
    - first：最大值
    - second：第二大值
    - third：第三大值
- 遍历数组并进行判断：
    - 如果大于first，那么三个变量同时依次变更
    - 如果大于second，除first的变量依次变更
    - 如果大于third，third变量变更
### 代码
```java
class Solution {
    public int thirdMax(int[] nums) {
        int first =  nums[0];
        long second = Long.MIN_VALUE;
        long third = Long.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num > first) {
                third = second;
                second = first;
                first = num;
            } else if (num > second && num < first) {
                third = second;
                second = num;
            } else if (num > third && num < second) {
                third = num;
            }
        }
        
        return third == Long.MIN_VALUE ? first : (int) third;
    }
}
```
# LeetCode_532_数组中的K-diff数对
## 题目
给定一个整数数组和一个整数 k, 你需要在数组里找到不同的 k-diff 数对。这里将 k-diff 数对定义为一个整数对 (i, j), 其中 i 和 j 都是数组中的数字，且两数之差的绝对值是 k.

示例 1:
```
输入: [3, 1, 4, 1, 5], k = 2
输出: 2
解释: 数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
尽管数组中有两个1，但我们只应返回不同的数对的数量。
```
示例 2:
```
输入:[1, 2, 3, 4, 5], k = 1
输出: 4
解释: 数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5)。
```
示例 3:
```
输入: [1, 3, 1, 5, 4], k = 0
输出: 1
解释: 数组中只有一个 0-diff 数对，(1, 1)。
```
注意:
```
数对 (i, j) 和数对 (j, i) 被算作同一数对。
数组的长度不超过10,000。
所有输入的整数的范围在 [-1e7, 1e7]。
```
## 失败解法
### 思路
- 嵌套循环数组，使用`Map<Integer,Set> map`记录diff数对，使用`Set<Integer> equal`记录元素相等状况的元素
- 循环map.values，累加set的长度并除以二，同时加上equal的长度
### 失败原因
超时
### 代码
```java
class Solution {
    public int findPairs(int[] nums, int k) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, new HashSet<>());
            }
        }

        Set<Integer> equal = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    if (nums[i] == nums[j]) {
                        equal.add(nums[i]);
                    } else {
                        map.get(nums[i]).add(nums[j]);
                        map.get(nums[j]).add(nums[i]);
                    }
                }
            }
        }

        int ans = 0;
        for (Set<Integer> set : map.values()) {
            ans += set.size();
        }

        return ans / 2 + equal.size();
    }
}
```
## 优化代码
### 思路
- 使用两个set：
    - 一个set在遍历数组的时候用来保存去重后的元素
    - 一个set在遍历过程中查询是否在上一个set中存在当前元素+k或-k的元素，如果有就保存
- 这样一次遍历就可以将所有可能的对保存下来，个数就是第二个set的size
### 代码
```java
class Solution {
    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        
        Set<Integer> save = new HashSet<>();
        Set<Integer> diff = new HashSet<>();
        for (int num : nums) {
            if (save.contains(num + k)) {
                diff.add(num);
            } 
            
            if (save.contains(num - k)) {
                diff.add(num - k);
            }
            
            save.add(num);
        }
        
        return diff.size();
    }
}
```
## 解法二
### 思路
- 先将数组排序
- 处理三种情况：
    - k < 0，结果就是0
    - k == 0 ，那么就判断重复的数字有多少，计算重复数字的个数
    - k > 0，使用两个指针，计算当前两个指针指向的元素的差值：
        - 如果相等k：计数，同时两个指针同时移动到非重复数字的位置
        - 如果小于k：说明大的元素不够大，快指针移动到下一个数字
        - 如果大于k：说明小的元素不够大，慢指针移动到下一个数字
- 返回计数的值        
### 代码
```java
class Solution {
    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }

        Arrays.sort(nums);
        int count = 0;

        if (k == 0) {
            int i = 0;
            while (i < nums.length - 1) {
                if (nums[i] == nums[i + 1]) {
                    count++;
                    i = nextNum(i, nums);
                } else {
                    i++;
                }
            }
            return count;
        }

        int slow = 0, fast = 1;
        while (fast < nums.length) {
            if (nums[fast] - nums[slow] == k) {
                count++;
                slow = nextNum(slow, nums);
                fast = nextNum(fast, nums);
            } else if (nums[fast] - nums[slow] < k) {
                fast = nextNum(fast, nums);
            } else {
                slow = nextNum(slow, nums);
            }
        }
        
        return count;
    }

    private int nextNum(int i, int[] nums) {
        int j = i + 1;
        while (j < nums.length && nums[i] == nums[j]) {
            j++;
        }
        return j;
    }
}
```
# LeetCode_5174_健身计划评估
## 题目
你的好友是一位健身爱好者。前段日子，他给自己制定了一份健身计划。现在想请你帮他评估一下这份计划是否合理。

他会有一份计划消耗的卡路里表，其中 calories[i] 给出了你的这位好友在第 i 天需要消耗的卡路里总量。

为了更好地评估这份计划，对于卡路里表中的每一天，你都需要计算他 「这一天以及之后的连续几天」 （共 k 天）内消耗的总卡路里 T：

如果 T < lower，那么这份计划相对糟糕，并失去 1 分； 
如果 T > upper，那么这份计划相对优秀，并获得 1 分；
否则，这份计划普普通通，分值不做变动。
请返回统计完所有 calories.length 天后得到的总分作为评估结果。

注意：总分可能是负数。

示例 1：
```
输入：calories = [1,2,3,4,5], k = 1, lower = 3, upper = 3
输出：0
解释：calories[0], calories[1] < lower 而 calories[3], calories[4] > upper, 总分 = 0.
```
示例 2：
```
输入：calories = [3,2], k = 2, lower = 0, upper = 1
输出：1
解释：calories[0] + calories[1] > upper, 总分 = 1.
```
示例 3：
```
输入：calories = [6,5,0,0], k = 2, lower = 1, upper = 5
输出：0
解释：calories[0] + calories[1] > upper, calories[2] + calories[3] < lower, 总分 = 0.
```
提示：
```
1 <= k <= calories.length <= 10^5
0 <= calories[i] <= 20000
0 <= lower <= upper
```
## 解法
### 思路
- 记录第一个k天的消耗，并比较和计数，记录k天的第一天的下标
- 移动的时候只需要减去k天的第一天，遍历到的这天就可以，无需重复计算k天的总消耗
### 代码
```java
class Solution {
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int sum = 0, ans = 0;
        for (int i = 0; i < k; i++) {
            sum += calories[i];
        }

        if (sum > upper) {
            ans++;
        } else if (sum < lower) {
            ans--;
        }

        for (int i = 0; i + k < calories.length; i++) {
            sum = sum - calories[i] + calories[i + k];
            if (sum > upper) {
                ans++;
            } else if (sum < lower) {
                ans--;
            }
        }

        return ans;
    }
}
```