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
# LeetCode_434_字符串中的单词数
## 题目
统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。

请注意，你可以假定字符串里不包括任何不可打印的字符。

示例:
```
输入: "Hello, my name is John"
输出: 5
在真实的面试中遇到过这道题？
```
## 解法
### 思路
使用正则表达式`\\s+`，同时注意头尾需要`trim`
### 代码
```java
class Solution {
    public int countSegments(String s) {
        s = s.trim();
        if ("".equals(s)) {
            return 0;
        }
        return s.split("\\s+").length;
    }
}
```
## 解法二
### 思路
定义单词个数可以通过当前字符不是`' '`，前一个字符为`' '`来定义
### 代码
```java

```
## 解法三
### 思路
- 遍历字符数组，定义连个函数：
    - 函数move2End：遍历完一个单词
    - 函数move2Start：遍历到下一个单词开头
- 遍历完一个单词，如果下标有增加，计数+1
### 代码
```java
class Solution {
    public int countSegments(String s) {
        char[] cs = s.toCharArray();
        int count = 0;
        for (int i = 0; i < cs.length;) {
            int t = move2Start(i, cs);
            i = move2End(t, cs);
            if (t != i) {
                count++;
            }
        }

        return count;
    }

    private int move2Start(int i, char[] cs) {
        while (i < cs.length && cs[i] == ' ') {
            i++;
        }
        return i;
    }

    private int move2End(int i, char[] cs) {
        while (i < cs.length && cs[i] != ' ') {
            i++;
        }
        return i;
    }
}
```
# LeetCode_840_矩阵中的幻方
## 题目
3 x 3 的幻方是一个填充有从 1 到 9 的不同数字的 3 x 3 矩阵，其中每行，每列以及两条对角线上的各数之和都相等。

给定一个由整数组成的 grid，其中有多少个 3 × 3 的 “幻方” 子矩阵？（每个子矩阵都是连续的）。

示例：
```
输入: [[4,3,8,4],
      [9,5,1,9],
      [2,7,6,2]]
输出: 1
解释: 
下面的子矩阵是一个 3 x 3 的幻方：
438
951
276

而这一个不是：
384
519
762

总的来说，在本示例所给定的矩阵中只有一个 3 x 3 的幻方子矩阵。
```
提示:
```
1 <= grid.length <= 10
1 <= grid[0].length <= 10
0 <= grid[i][j] <= 15
```
## 解法
### 思路
以左上角元素为原点，遍历二维数组
- 每一条的总和一定是15
- 所有9个元素均匀分布在[1,9]的区间中
- 矩阵正中一定是5
### 代码
```java
class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int ans = 0;
        int[] bucket = new int[9];
        for (int i = 0; i + 2 < grid.length; i++) {
            for (int j = 0; j + 2 < grid[i].length; j++) {
                if (grid[i + 1][j + 1] != 5) {
                    continue;
                }

                if (isMagic(grid[i][j], grid[i][j + 1], grid[i][j + 2],
                        grid[i + 1][j], grid[i + 1][j + 1], grid[i + 1][j + 2],
                        grid[i + 2][j], grid[i + 2][j + 1], grid[i + 2][j + 2])) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private boolean isMagic(int... nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(nums[i], max);
        }

        int[] bucket = new int[max + 1];
        for (int num : nums) {
            bucket[num]++;
        }

        for (int i = 1; i < bucket.length; i++) {
            if (bucket[i] != 1) {
                return false;
            }
        }

        return nums[0] + nums[1] + nums[2] == 15 &&
                nums[3] + nums[4] + nums[5] == 15 &&
                nums[6] + nums[7] + nums[8] == 15 &&
                nums[0] + nums[3] + nums[6] == 15 &&
                nums[1] + nums[4] + nums[7] == 15 &&
                nums[2] + nums[5] + nums[8] == 15 &&
                nums[0] + nums[4] + nums[8] == 15 &&
                nums[2] + nums[4] + nums[6] == 15;
    }
}
```
# LeetCode_686_重复叠加字符串匹配
## 题目
给定两个字符串 A 和 B, 寻找重复叠加字符串A的最小次数，使得字符串B成为叠加后的字符串A的子串，如果不存在则返回 -1。

举个例子，A = "abcd"，B = "cdabcdab"。

答案为 3， 因为 A 重复叠加三遍后为 “abcdabcdabcd”，此时 B 是其子串；A 重复叠加两遍后为"abcdabcd"，B 并不是其子串。

注意:
```
 A 与 B 字符串的长度在1和10000区间范围内。
```
## 解法
### 思路
- 如果A包含B，直接返回1
- 使用两个指针，嵌套循环：
    - 外层循环A数组，查看从A的哪一个字符开始可以循环的匹配B数组元素
    - 内层遍历B数组，为了就是查看是否有不匹配的情况，有的话就中断并开始下一个A的字符的遍历
- 遍历结束后，将A放入StringBuilder并不断`append(A)`，记录append的次数count，并判断是否`contains(B)`
- 如果contains，则返回count
### 代码
```java
class Solution {
    public int repeatedStringMatch(String A, String B) {
        if (A.contains(B)) {
            return 1;
        }

        boolean has = false;
        for (int j = 0; j < A.length(); j++) {
            int ai = j;
            boolean find = true;
            for (int i = 0; i < B.length(); i++) {
                if (A.charAt(ai) != B.charAt(i)) {
                    find = false;
                    break;
                }
    
                if (++ai == A.length()) {
                    ai = 0;
                }
            }
            
            if (find) {
                has = true;
                break;
            }
        }
        
        if (!has) {
            return -1;
        }

        int count = 1;
        StringBuilder sb = new StringBuilder(A);
        while (!sb.toString().contains(B)) {
            sb.append(A);
            count++;
        }
        return count;
    }
}
```
## 解法二
### 思路
- 循环比较数组A和数组B的元素
- 如果元素不同就移动A数组的下标
- 如果元素相同，记录当前下标，作为判断叠加的依据
- 如在叠加过程中发现元素有不同，那么就还原B的元素下标，同时判断A数组在两次叠加之内有不同的元素，那就说明还可能是符合题意得，否则就返回-1
- 如果A下标回到了0，说明有叠加，ans+1
- 如果在切换回0后，字符不同了，但A还没有完全走一次从start到start的过程，那么就把之前累加的1减去，并重新冲start+1的位置继续判断
- 如果在字符相等的情况下，B数组的下标将要越界，那么就返回当前ans + 1作为答案，因为A字符串本身也要计数
### 代码
```java
class Solution {
    public int repeatedStringMatch(String A, String B) {
        char[] ca = A.toCharArray(), cb = B.toCharArray();
        int start = -1, ia = 0, ib = 0, ans = 0;
        while (true) {
            if (ca[ia] == cb[ib]) {
                if (start == -1) {
                    start = ia;
                }

                ib++;

                if (ib >= cb.length) {
                    return ans + 1;
                }
            } else {
                if (ans <= 1) {
                    ib = 0;

                    if (start != -1) {
                        if (start > ia) {
                            ans--;
                        }

                        ia = start;
                        start = -1;
                    }
                } else {
                    return -1;
                }
            }

            ia = (ia + 1) % ca.length;
            if (ia == 0) {
                ans++;
            }
        }
    }
}
```
# LeetCode_949_给定数字能组成的最大时间
## 题目
给定一个由 4 位数字组成的数组，返回可以设置的符合 24 小时制的最大时间。

最小的 24 小时制时间是 00:00，而最大的是 23:59。从 00:00 （午夜）开始算起，过得越久，时间越大。

以长度为 5 的字符串返回答案。如果不能确定有效时间，则返回空字符串。

示例 1：
```
输入：[1,2,3,4]
输出："23:41"
```
示例 2：
```
输入：[5,5,5,5]
输出：""
```
提示：
```
A.length == 4
0 <= A[i] <= 9
```
## 解法
### 思路
- 使用通记录0-9的元素的个数
- 小时和分钟区别处理：
    1. 小时：
        - 第一位0，第二位0-9
        - 第一位1，第二位0-9
        - 第一位2，第二位0-3
    2. 分钟：
        - 第一位0-5
        - 第二位0-9
- 必需要有一个数字小于等于2
- 必须要有两个数字小于等于5
- 如果有一个数字是2，必须要有2个数字小于等于3，2个数字小于等于5
### 代码
```java
class Solution {
    public String largestTimeFromDigits(int[] A) {
        int lte2 = 0, lte3 = 0, lte5 = 0;
        int[] bucket = new int[10];
        for (int num : A) {
            bucket[num]++;
            if (num <= 2) {
                lte2++;
                lte3++;
                lte5++;
            } else if (num <= 3) {
                lte3++;
                lte5++;
            } else if (num <= 5) {
                lte5++;
            }
        }
        
        if (lte2 < 1 || lte5 < 2) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        int one = -1;
        for (int i = 2; i >= 0; i--) {
            if (bucket[i] > 0) {
                if (i == 2 && (lte3 < 2 || lte5 < 3)) {
                    continue;
                }
                
                bucket[i]--;
                sb.append(i);
                one = i;
                break;
            }
        }

        if (one == -1) {
            return "";
        }

        int two = -1;
        if (one == 2) {
            for (int i = 3; i >= 0; i--) {
                if (bucket[i] > 0) {
                    bucket[i]--;
                    sb.append(i);
                    two = i;
                    break;
                }
            }
        } else {
            for (int i = 9; i >= 0; i--) {
                if (bucket[i] > 0) {
                    bucket[i]--;
                    sb.append(i);
                    two = i;
                    break;
                }
            }
        }

        if (two == -1) {
            return "";
        }

        sb.append(":");

        int three = -1;
        for (int i = 5; i >= 0; i--) {
            if (bucket[i] > 0) {
                bucket[i]--;
                sb.append(i);
                three = i;
                break;
            }
        }

        if (three == -1) {
            return "";
        }

        int four = -1;
        for (int i = 9; i >= 0; i--) {
            if (bucket[i] > 0) {
                bucket[i]--;
                sb.append(i);
                four = i;
                break;
            }
        }

        return four == -1 ? "" : sb.toString();
    }
}
```
## 解法二
### 思路
- 使用暴力方法，枚举所有的可能性，然后分别以小时和分钟判断是否符合规则：
    - 小时：<24
    - 分钟：<60
- 最后比较可能性中的最大值
### 代码
```java
class Solution {
    public String largestTimeFromDigits(int[] A) {
        int ans = -1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i != j) {
                    for (int k = 0; k < 4; k++) {
                        if (i != k && j != k) {
                            int l = 6 - i - j - k;

                            int hour = A[i] * 10 + A[j];
                            int minute = A[k] * 10 + A[l];

                            if (hour < 24 && minute < 60) {
                                ans = Math.max(ans, hour * 60 + minute);
                            }
                        }
                    }
                }
            }
        }

        return ans >= 0 ? String.format("%02d:%02d", ans / 60, ans % 60): "";
    }
}
```
# LeetCode_914_卡牌分组
## 题目
给定一副牌，每张牌上都写着一个整数。

此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
```
每组都有 X 张牌。
组内所有的牌上都写着相同的整数。
仅当你可选的 X >= 2 时返回 true。
```
示例 1：
```
输入：[1,2,3,4,4,3,2,1]
输出：true
解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
```
示例 2：
```
输入：[1,1,1,2,2,2,3,3]
输出：false
解释：没有满足要求的分组。
```
示例 3：
```
输入：[1]
输出：false
解释：没有满足要求的分组。
```
示例 4：
```
输入：[1,1]
输出：true
解释：可行的分组是 [1,1]
```
示例 5：
```
输入：[1,1,2,2,2,2]
输出：true
解释：可行的分组是 [1,1]，[2,2]，[2,2]
```
提示：
```
1 <= deck.length <= 10000
0 <= deck[i] < 10000
```
## 解法
### 思路
- 如果元素个数<2，返回false
- 生成一个桶统计元素个数
- 遍历桶，算出所有元素的最小公约数
- 遍历桶，用最小公约数与元素取余，如果有不等于0的元素，返回false，否则返回true
### 代码
```java
class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        if (deck.length < 2) {
            return false;
        }

        int[] bucket = new int[10000];
        for (int num: deck) {
            bucket[num]++;
        }

        int min = -1, commonDivisor = Integer.MAX_VALUE;
        for (int num: bucket) {
            if (num > 0) {
                if (min == -1) {
                    min = num;
                }
                
                int tmp = commondivisior(min, num);
                commonDivisor = tmp == 1 ? commonDivisor : Math.min(tmp, commonDivisor);
            }
        }
        
        for (int num : bucket) {
            if (num % commonDivisor != 0) {
                return false;
            }
        }

        return true;
    }

    private int commondivisior(int a, int b) {
        if (a < b) {
            a ^= b;
            b ^= a;
            a ^= b;
        }

        return a % b == 0 ? b : commondivisior(b, a % b);
    }
}
```
# LeetCode_633_平方数之和
## 题目
给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c。

示例1:
```
输入: 5
输出: True
解释: 1 * 1 + 2 * 2 = 5
```
示例2:
```
输入: 3
输出: False
```
## 失败解法
### 思路
两层嵌套循环：
- 外层通过头尾指针确定a和b的值
- 内层通过二分法确定是否存在平方数
### 失败原因
超时
### 代码
```java
class Solution {
    public boolean judgeSquareSum(int c) {
        int head = 0, tail = c;
        while (head <= tail) {
            if (isQuadraticSum(head) && isQuadraticSum(tail)) {
                return true;
            }
            
            head++;
            tail--;
        }
        
        return false;
    }
    
    private boolean isQuadraticSum(int sum) {
        int head = 0, tail = sum / 2 + 1;
        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            double pow = Math.pow(mid, 2);
            if (pow == sum) {
                return true;
            }
            
            if (pow < sum) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }
        return false;
    }
}
```
## 解法
### 思路
失败解法为什么要嵌套循环，在外层直接通过判断相乘判断就可以了啊
### 代码
```java
class Solution {
    public boolean judgeSquareSum(int c) {
        int head = 0, tail = (int) Math.sqrt(c);
        while (head <= tail) {
            int sum = head * head + tail * tail;
            if (sum == c) {
                return true;
            }

            if (sum < c) {
                head++;
            } else {
                tail--;
            }
        }

        return false;
    }
}
```
# LeetCode_58_最后一个单词的长度
## 题目
给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。

如果不存在最后一个单词，请返回 0 。

说明：一个单词是指由字母组成，但不包含任何空格的字符串。

示例:
```
输入: "Hello World"
输出: 5
```
## 解法
### 思路
游标从字符串尾部开始遍历，记录最后一个单词的结尾和起始下标：
- 当前元素是字母：
    - 如果下标为`字符串长度-1`或者后一个字符是`' '`：找到了最后一个单词的结尾字符，记录当前下标+1
    - 如果下标为`0`或者前一个字符是`' '`：找到了最后一个单词的起始字符，记录当前下标
- 如果起始和结尾都找到了，终止遍历
- 返回`终止-起始`的长度
### 代码
```java
class Solution {
    public int lengthOfLastWord(String s) {
        char[] cs = s.toCharArray();
        int start = 0, end = 0;
        boolean findEnd = false, findStart = false;
        for (int i = cs.length - 1; i >= 0; i--) {
            if (cs[i] != ' ') {
                if (i == cs.length - 1) {
                    end = i + 1;
                    findEnd = true;
                } else if (cs[i + 1] == ' ') {
                    end = i + 1;
                    findEnd = true;
                } 
                
                if (i == 0) {
                    start = i;
                    findStart = true;
                } else if (cs[i - 1] == ' ') {
                    start = i;
                    findStart = true;
                }
            }

            if (findStart && findEnd) {
                break;
            }
        }

        return end - start;
    }
}
```
# LeetCode_874_模拟行走机器人
## 题目
机器人在一个无限大小的网格上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令：
```
-2：向左转 90 度
-1：向右转 90 度
1 <= x <= 9：向前移动 x 个单位长度
在网格上有一些格子被视为障碍物。
```
第 i 个障碍物位于网格点  (obstacles[i][0], obstacles[i][1])

如果机器人试图走到障碍物上方，那么它将停留在障碍物的前一个网格方块上，但仍然可以继续该路线的其余部分。

返回从原点到机器人的最大欧式距离的平方。

示例 1：
```
输入: commands = [4,-1,3], obstacles = []
输出: 25
解释: 机器人将会到达 (3, 4)
```
示例 2：
```
输入: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
输出: 65
解释: 机器人在左转走到 (1, 8) 之前将被困在 (1, 4) 处
```
提示：
```
0 <= commands.length <= 10000
0 <= obstacles.length <= 10000
-30000 <= obstacle[i][0] <= 30000
-30000 <= obstacle[i][1] <= 30000
答案保证小于 2 ^ 31
```
## 解法
### 思路
- 根据障碍物坐标二维数组，生成`Map<Integer, List<Integer>> map`
- 确定一个二维数组定义4个方向，方向为`上,右,下,左`，对应数字`0,1,2,3`
- 遍历命令数组，当移动时要途径障碍物时就停留在该方向上的前一个方格，并执行下一个命令
- 记录每一步的欧式距离，并保留最大值
- 遍历完命令数组，返回最大值
### 代码
```java
class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
       Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] obstacle: obstacles) {
            if (map.containsKey(obstacle[0])) {
                map.get(obstacle[0]).add(obstacle[1]);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(obstacle[1]);
                map.put(obstacle[0], list);
            }
        }

        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int direction = 0, max = 0;
        int[] position = new int[2];

        for (int command : commands) {
            if (command == -2) {
                direction = (direction + 3) % 4;
            } else if (command == -1) {
                direction = (direction + 1) % 4;
            } else {
                for (int i = 0; i < command; i++) {
                    int x = position[0] + directions[direction][0];
                    int y = position[1] + directions[direction][1];
                    if (map.containsKey(x) && map.get(x).contains(y)) {
                        break;
                    } else {
                        position[0] = x;
                        position[1] = y;
                        max = Math.max(max, x * x + y * y);
                    }
                }
            }
        }
        
        return max; 
    }
}
```
# LeetCode_605_种花问题
## 题目
假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。

给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。

示例 1:
```
输入: flowerbed = [1,0,0,0,1], n = 1
输出: True
```
示例 2:
```
输入: flowerbed = [1,0,0,0,1], n = 2
输出: False
```
注意:
```
数组内已种好的花不会违反种植规则。
输入的数组长度范围为 [1, 20000]。
n 是非负整数，且不会超过输入数组的大小。
```
## 解法
### 思路
- 遍历数组，判断当前坐标是否可以种花:
    - 判断当前下标是否为0
    - 如果当前下标为0：
        - 判断是否是头尾下标，是则要判断越界，以及对应的前后下标是否为0
        - 其他下标，判单前后下标是否为0
- 种花与否的移动方式：
    - 可以：下标+2且`n--`
    - 不可以：下标+1
### 代码
```java
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; ) {
            if (n == 0) {
                break;
            }
            
            if (flowerbed[i] == 1) {
                i += 2;
            } else if (flowerbed[i] == 0) {
                if (i == 0) {
                    if (flowerbed.length == 1 || flowerbed[1] == 0) {
                        flowerbed[i] = 1;
                        i += 2;
                        n--;
                    } else {
                        i++;
                    }
                } else if (i == flowerbed.length - 1 && flowerbed[i - 1] == 0) {
                    flowerbed[i] = 1;
                    i += 2;
                    n--;
                } else if (flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
                    flowerbed[i] = 1;
                    i += 2;
                    n--;
                } else {
                    i++;
                }
            }
        }
        return n == 0;
    }
}
```