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
# LeetCode_204_计数质数
## 题目
统计所有小于非负整数 n 的质数的数量。

示例:
```
输入: 10
输出: 4
解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
```
## 解法
### 思路
- 定义一个boolean[]作为桶
- 然后从2开始遍历到n，如果桶中当前值下标的元素是false，就认为是质数，count++
- 之所以false可以计数，是因为内层还需要嵌套循环，从2开始和外层元素相乘，得到的结果作为下标将桶对应的元素改为true
### 代码
```java
class Solution {
    public int countPrimes(int n) {
        boolean[] bucket = new boolean[n + 1];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!bucket[i]) {
                count++;
            }
            
            for (int j = 2; j * i < n; j++) {
                bucket[j * i] = true;
            }
        }
        return count;
    }
}
```
# LeetCode_475_供暖器
## 题目
冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。

现在，给出位于一条水平线上的房屋和供暖器的位置，找到可以覆盖所有房屋的最小加热半径。

所以，你的输入将会是房屋和供暖器的位置。你将输出供暖器的最小加热半径。

说明:
```
给出的房屋和供暖器的数目是非负数且不会超过 25000。
给出的房屋和供暖器的位置均是非负数且不会超过10^9。
只要房屋位于供暖器的半径内(包括在边缘上)，它就可以得到供暖。
所有供暖器都遵循你的半径标准，加热的半径也一样。
```
示例 1:
```
输入: [1,2,3],[2]
输出: 1
解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
```
示例 2:
```
输入: [1,2,3,4],[1,4]
输出: 1
解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
```
## 失败解法
### 思路
嵌套遍历：
- 外层遍历houses数组，定义一个min值用来存当前节点能获取到热源的最小距离
- 内层遍历heaters数组，更新外层的min值
### 失败原因
超时
### 代码
```java
class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        int max = 0;
        for (int house : houses) {
            int min = Integer.MAX_VALUE;
            for (int heater: heaters) {
                min = Math.min(Math.abs(heater - house), min);
            }
            max = Math.max(min, max);
        }
        return max;
    }
}
```
## 解法
### 思路
在失败解法的基础上，缩短内层遍历的时间复杂度，因为内层的加热器只需要计算处于房子前后的两个与房子之间的距离，所以只需要遍历到下标第一次大于房子为止，然后开始计算两个点的最小值.
- 需要注意heaters的下标处在头尾的情况
- 需要对两个数组进行排序
### 代码
```java
class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int max = 0;
        for (int house : houses) {
            int index = 0;
            while (index < heaters.length && heaters[index] < house) {
                index++;
            }
            if (index == 0) {
                max = Math.max(Math.abs(house - heaters[0]), max);
            } else if (index == heaters.length) {
                max = Math.max(Math.abs(house - heaters[index - 1]), max);
            } else {
                max = Math.max(Math.min(Math.abs(heaters[index] - house), house - heaters[index - 1]), max);
            }
        }

        return max;
    }
}
```
## 优化代码
### 思路
将循环中的局部变量放在循环体外，从而减少了栈上分配空间回回收的开销
### 代码
```java
class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int max = 0;
        int index = 0;
        for (int house : houses) {
            while (index < heaters.length && heaters[index] < house) {
                index++;
            }
            if (index == 0) {
                max = Math.max(Math.abs(house - heaters[0]), max);
            } else if (index == heaters.length) {
                max = Math.max(Math.abs(house - heaters[index - 1]), max);
            } else {
                max = Math.max(Math.min(Math.abs(heaters[index] - house), house - heaters[index - 1]), max);
            }
        }

        return max;
    }
}
```
# LeetCode_859_亲密字符串
## 题目
给定两个由小写字母构成的字符串 A 和 B ，只要我们可以通过交换 A 中的两个字母得到与 B 相等的结果，就返回 true ；否则返回 false 。

示例 1：
```
输入： A = "ab", B = "ba"
输出： true
```
示例 2：
```
输入： A = "ab", B = "ab"
输出： false
```
示例 3:
```
输入： A = "aa", B = "aa"
输出： true
```
示例 4：
```
输入： A = "aaaaaaabc", B = "aaaaaaacb"
输出： true
```
示例 5：
```
输入： A = "", B = "aa"
输出： false
```
提示：
```
0 <= A.length <= 20000
0 <= B.length <= 20000
A 和 B 仅由小写字母构成。
```
## 解法
### 思路
- 如果两个字符串不相等，直接返回false
- 同时遍历两个字符串，当字符串不相等，记录下两个字符
- 继续遍历，直到找到第二个不相等的字符，如果找到的字符与记录的另一个数组的字符不相等，返回false
- 否则就认为之后字符全都相等，如果出现不相等，返回false
- 最后如果遍历结束，如果没有记录到不同的字符，查看字符串中是否有相等的字符，有的话返回true，否则返回false
- 否则查看字符串A的第一个不同是否与字符串B的第二个不同相等，A的第二个不同是否与B的第一个不同相等
### 代码
```java
class Solution {
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }
        
        int[] bucket = new int[26];
        Character a1 = null, b1 = null, a2 = null, b2 = null;
        int count = 0;
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) != B.charAt(i)) {
                if (count == 0) {
                    count++;
                    a1 = A.charAt(i);
                    b1 = B.charAt(i);
                } else if (count == 1) {
                    count++;
                    a2 = A.charAt(i);
                    b2 = B.charAt(i);
                } else {
                    return false;
                }
            } else {
                bucket[A.charAt(i) - 'a']++;
            }
        }
        
        if (a1 == null || a2 == null) {
            boolean find = false;
            for (int value : bucket) {
                if (value >= 2) {
                    find = true;
                    break;
                }
            }
            
            return find;
        }
        
        return a1 == b2 && a2 == b1;
    }
}
```
# LeetCode_1169_查询无效交易
## 题目
如果出现下述两种情况，交易 可能无效：
```
交易金额超过 ¥1000
或者，它和另一个城市中同名的另一笔交易相隔不超过 60 分钟（包含 60 分钟整）
每个交易字符串 transactions[i] 由一些用逗号分隔的值组成，这些值分别表示交易的名称，时间（以分钟计），金额以及城市。
```
给你一份交易清单 transactions，返回可能无效的交易列表。你可以按任何顺序返回答案。

示例 1：
```
输入：transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
输出：["alice,20,800,mtv","alice,50,100,beijing"]
解释：第一笔交易是无效的，因为第二笔交易和它间隔不超过 60 分钟、名称相同且发生在不同的城市。同样，第二笔交易也是无效的。
```
示例 2：
```
输入：transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
输出：["alice,50,1200,mtv"]
```
示例 3：
```
输入：transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
输出：["bob,50,1200,mtv"]
```
提示：
```
transactions.length <= 1000
每笔交易 transactions[i] 按 "{name},{time},{amount},{city}" 的格式进行记录
每个交易名称 {name} 和城市 {city} 都由小写英文字母组成，长度在 1 到 10 之间
每个交易时间 {time} 由一些数字组成，表示一个 0 到 1000 之间的整数
每笔交易金额 {amount} 由一些数字组成，表示一个 0 到 2000 之间的整数
```
## 解法
### 思路
- 遍历字符串数组
- 将字符串生成对应的类`Transaction`
- 将大于1000金额的字符串放入结果list中
- 将Transaction以name为key，类为value存入`Map<String, List<Transcation>> map`中
- 遍历结束后，找出value的list大于2的list，比较其中两两绝对值的差值小于等于60的，同时也不在也放入list中
- 最后返回list
### 代码
```java
class Solution {
    public List<String> invalidTransactions(String[] transactions) {
        List<String> ans = new ArrayList<>();
        Map<String, List<Transaction>> map = new HashMap<>();
        for (String transactionStr : transactions) {
            Transaction transaction = new Transaction(transactionStr);
            if (transaction.amount > 1000) {
                transaction.valid = false;
            }
            
            if (map.containsKey(transaction.name)) {
                map.get(transaction.name).add(transaction);
            } else {
                List<Transaction> list = new ArrayList<>();
                list.add(transaction);
                map.put(transaction.name, list);
            }
        }

        for (List<Transaction> list : map.values()) {
            if (list.size() >= 2) {
                for (int i = 0; i < list.size(); i++) {
                    for (int j = 0; j < list.size(); j++) {
                        if (i == j) {
                            continue;
                        }

                        Transaction a = list.get(i);
                        Transaction b = list.get(j);
                        
                        if (Math.abs(a.time - b.time) <= 60 && !a.city.equals(b.city)) {
                            a.valid = false;
                            b.valid = false;
                        }
                    }
                }
            }
        }
        
        for (List<Transaction> list : map.values()) {
            for (Transaction t : list) {
                if (!t.valid) {
                    ans.add(t.toString());
                }
            }
        }

        return ans;
    }
    
    class Transaction {
        private String name;
        private Integer time;
        private Integer amount;
        private String city;
        private boolean valid;
        
        Transaction(String transcation) {
            String[] factors = transcation.split(",");
            this.name = factors[0];
            this.time = Integer.parseInt(factors[1]);
            this.amount = Integer.parseInt(factors[2]);
            this.city = factors[3];
            this.valid = true;
        }
        
        public String toString() {
            return name + "," + time + "," + amount + "," + city;
        }
    }
}
```
## 优化代码
### 思路
- 建模的类中增加raw属性用来存储原始字符串，不用再使用toString拼接属性
- 例程中在遍历过程中就将结果放入list，不需要先标记再遍历
### 代码
```java
class Solution {
    public List<String> invalidTransactions(String[] transactions) {
        List<String> ans = new ArrayList<>();
        Map<String, List<Transaction2>> map = new HashMap<>();
        for (String transactionStr : transactions) {
            Transaction2 transaction = new Transaction2(transactionStr);

            if (map.containsKey(transaction.name)) {
                map.get(transaction.name).add(transaction);
            } else {
                List<Transaction2> list = new ArrayList<>();
                list.add(transaction);
                map.put(transaction.name, list);
            }
        }

        for (List<Transaction2> list : map.values()) {
            for (int i = 0; i < list.size(); i++) {
                Transaction2 a = list.get(i);
                if (a.amount > 1000) {
                    ans.add(a.raw);
                } else {
                    for (Transaction2 b : list) {
                        if (Math.abs(a.time - b.time) <= 60 && !a.city.equals(b.city)) {
                            ans.add(a.raw);
                            break;
                        }
                    }
                }
            }
        }

        return ans;
    }

    class Transaction2 {
        private String name;
        private Integer time;
        private Integer amount;
        private String city;
        private String raw;

        Transaction2(String transcation) {
            String[] factors = transcation.split(",");
            this.name = factors[0];
            this.time = Integer.parseInt(factors[1]);
            this.amount = Integer.parseInt(factors[2]);
            this.city = factors[3];
            this.raw = transcation;
        }
    }
}
```
# LeetCode_707_设计链表
## 题目
设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。

在链表类中实现这些功能：
```
get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
```
示例：
```
MyLinkedList linkedList = new MyLinkedList();
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
linkedList.get(1);            //返回2
linkedList.deleteAtIndex(1);  //现在链表是1-> 3
linkedList.get(1);            //返回3
```
提示：
```
所有val值都在 [1, 1000] 之内。
操作次数将在  [1, 1000] 之内。
请不要使用内置的 LinkedList 库。
```
## 解法
### 思路
单向链表实现，初始化时先设定一个初始node，方便新增
### 代码
```java
class MyLinkedList {
    private ListNode head;
    private ListNode tail;
    private int len;

    public MyLinkedList() {
        ListNode node = new ListNode(0);
        this.head = node;
        this.tail = node;
        this.len = 0;
    }

    public int get(int index) {
        if (index < 0 || index >= len) {
            return -1;
        }

        ListNode node = this.head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.val;
    }

    public void addAtHead(int val) {
        if (this.len == 0) {
            this.head.val = val;
        } else {
            ListNode node = new ListNode(val);
            node.next = this.head;
            this.head = node;
        }
        this.len++;
    }

    public void addAtTail(int val) {
        if (this.len == 0) {
            this.tail.val = val;
        } else {
            ListNode node = new ListNode(val);
            this.tail.next = node;
            this.tail = node;
        }
        this.len++;
    }

    public void addAtIndex(int index, int val) {
        if (index > len) {
            return;
        }

        if (index == 0) {
            addAtHead(val);
        } else if (index == len) {
            addAtTail(val);
        } else {
            ListNode node = this.head;
            for (int i = 0; i < index - 1; i++) {
                node = node.next;
            }
            ListNode tmp = node.next;
            node.next = new ListNode(val);
            node.next.next = tmp;
            this.len++;
        }
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= len) {
            return;
        }
        
        if (index == 0) {
            this.head = this.head.next;
            this.len--;
            return;
        }

        ListNode node = this.head;
        for (int i = 0; i < index - 1; i++) {
            node = node.next;
        }
        node.next = node.next.next;
        if (index == this.len - 1) {
            this.tail = node;
        }
        this.len--;
    }
}
```
## 优化代码
### 思路
- 初始化链表时，增加一个头节点在头部，并使尾部节点也指向这个节点，有了这个节点，就可以直接通过for循环index次找到目标节点的前一个节点
- 抽象`寻找节点`和`增加节点`的函数逻辑
### 代码
```java
class MyLinkedList {
    private ListNode preHead;
    private ListNode tail;
    private int len;

    public MyLinkedList() {
        ListNode node = new ListNode(0);
        this.preHead = node;
        this.tail = node;
        this.len = 0;
    }

    public int get(int index) {
        if (index < 0 || index >= len) {
            return -1;
        }

        return findNode(index).next.val;
    }

    public void addAtHead(int val) {
        if (this.len == 0) {
            addAtTail(val);
        } else {
            addAfter(this.preHead, val);
        }
    }

    public void addAtTail(int val) {
        addAfter(this.tail, val);
        this.tail = this.tail.next;
    }

    public void addAtIndex(int index, int val) {
        if (index <= 0) {
            addAtHead(val);
        } else if (index == this.len) {
            addAtTail(val);
        } else if (index > 0 && index < this.len) {
            addAfter(findNode(index), val);
        }
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= len) {
            return;
        }

        ListNode node = findNode(index);
        if (index == this.len - 1) {
            this.tail = node;
        }
        node.next = node.next.next;
        this.len--;
    }

    private ListNode findNode(int index) {
        ListNode node = this.preHead;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private void addAfter(ListNode node, int val) {
        ListNode tmp = new ListNode(val);
        tmp.next = node.next;
        node.next = tmp;
        this.len++;
    }
}
```
# LeetCode_665_非递减数列
## 题目
给定一个长度为 n 的整数数组，你的任务是判断在最多改变 1 个元素的情况下，该数组能否变成一个非递减数列。

我们是这样定义一个非递减数列的： 对于数组中所有的 i (1 <= i < n)，满足 array[i] <= array[i + 1]。

示例 1:
```
输入: [4,2,3]
输出: True
解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
```
示例 2:
```
输入: [4,2,1]
输出: False
解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
说明:  n 的范围为 [1, 10,000]。
```
## 解法
### 思路
- 遍历数组，当出现降序时：
    - 如果当前元素是第一个元素或者最后第二个元素，没问题
    - 如果前半部分的第二大值小于等于第二部分的最小值，没问题
    - 如果后半部分的第二小值大于第一部分的最大值，没问题
    - 如上任意情况记录出现降序的次数
    - 否则返回false
- 出现第二次降序时候，返回false
### 代码
```java
class Solution {
    public boolean checkPossibility(int[] nums) {
        int count = 0;

        for (int i = 0; i + 1 < nums.length; i++) {
            if (nums[i] > nums[i + 1]) {
                if (count >= 1) {
                    return false;
                }

                if (i == 0 || i == nums.length - 2 || nums[i - 1] <= nums[i + 1] || nums[i] <= nums[i + 2]) {
                    count++;
                    continue;
                }

                return false;
            }
        }

        return true;
    }
}
```
# LeetCode_807_保持城市天际线
## 题目
在二维数组grid中，grid[i][j]代表位于某处的建筑物的高度。 我们被允许增加任何数量（不同建筑物的数量可能不同）的建筑物的高度。 高度 0 也被认为是建筑物。

最后，从新数组的所有四个方向（即顶部，底部，左侧和右侧）观看的“天际线”必须与原始数组的天际线相同。 城市的天际线是从远处观看时，由所有建筑物形成的矩形的外部轮廓。 请看下面的例子。

建筑物高度可以增加的最大总和是多少？

例子：
```
输入： grid = [[3,0,8,4],[2,4,5,7],[9,2,6,3],[0,3,1,0]]
输出： 35
解释： 
The grid is:
[ [3, 0, 8, 4], 
  [2, 4, 5, 7],
  [9, 2, 6, 3],
  [0, 3, 1, 0] ]

从数组竖直方向（即顶部，底部）看“天际线”是：[9, 4, 8, 7]
从水平水平方向（即左侧，右侧）看“天际线”是：[8, 7, 9, 3]

在不影响天际线的情况下对建筑物进行增高后，新数组如下：

gridNew = [ [8, 4, 8, 7],
            [7, 4, 7, 7],
            [9, 4, 8, 7],
            [3, 3, 3, 3] ]
```
说明:
```
1 < grid.length = grid[0].length <= 50。
 grid[i][j] 的高度范围是： [0, 100]。
一座建筑物占据一个grid[i][j]：换言之，它们是 1 x 1 x grid[i][j] 的长方体。
```
## 解法
### 思路
- 为了保持天际线，增加高度后的二维数组，横竖上的最高值组成的数组不能变
- 过程：
    - 找到每一列的最高值组成横的天际线数组
    - 找到每一行的最高值组成列的天际线数组
    - 累加当前建筑下标所在的行和列的最大值之间的最小值与当前建筑的高度的差
### 代码
```java
class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int count = 0;
        int[] rowMaxArr = new int[grid.length];
        int[] colMaxArr = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            int rowMax = 0;
            int colMax = 0;
            for (int j = 0; j < grid[i].length; j++) {
                rowMax = Math.max(rowMax, grid[i][j]);
                colMax = Math.max(colMax, grid[j][i]);
            }
            rowMaxArr[i] = rowMax;
            colMaxArr[i] = colMax;
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                count += Math.min(rowMaxArr[i], colMaxArr[j]) - grid[i][j];
            }
        }
        
        return count;
    }
}
```
# LeetCode_950_按递增顺序显示卡牌
## 题目
牌组中的每张卡牌都对应有一个唯一的整数。你可以按你想要的顺序对这套卡片进行排序。

最初，这些卡牌在牌组里是正面朝下的（即，未显示状态）。

现在，重复执行以下步骤，直到显示所有卡牌为止：
```
从牌组顶部抽一张牌，显示它，然后将其从牌组中移出。
如果牌组中仍有牌，则将下一张处于牌组顶部的牌放在牌组的底部。
如果仍有未显示的牌，那么返回步骤 1。否则，停止行动。
返回能以递增顺序显示卡牌的牌组顺序。
```
答案中的第一张牌被认为处于牌堆顶部。

示例：
```
输入：[17,13,11,2,3,5,7]
输出：[2,13,3,11,5,17,7]
解释：
我们得到的牌组顺序为 [17,13,11,2,3,5,7]（这个顺序不重要），然后将其重新排序。
重新排序后，牌组以 [2,13,3,11,5,17,7] 开始，其中 2 位于牌组的顶部。
我们显示 2，然后将 13 移到底部。牌组现在是 [3,11,5,17,7,13]。
我们显示 3，并将 11 移到底部。牌组现在是 [5,17,7,13,11]。
我们显示 5，然后将 17 移到底部。牌组现在是 [7,13,11,17]。
我们显示 7，并将 13 移到底部。牌组现在是 [11,17,13]。
我们显示 11，然后将 17 移到底部。牌组现在是 [13,17]。
我们展示 13，然后将 17 移到底部。牌组现在是 [17]。
我们显示 17。
由于所有卡片都是按递增顺序排列显示的，所以答案是正确的。
```
提示：
```
1 <= A.length <= 1000
1 <= A[i] <= 10^6
对于所有的 i != j，A[i] != A[j]
```
## 题目
### 思路
- 排序deck为升序
- 将数组下标分成多个阶段处理：
    - 每个阶段的奇数位都是升序排列的
    - 每个阶段的奇数位的值都大于上个阶段奇数位的最大值
    - 将每个阶段的偶数位作为当前阶段的下个阶段
    - 注意前后阶段要连贯，也就是如果当前阶段是以奇数位结束的，那么下个阶段的第一个数应该以上一个阶段的偶数来处理，也就是需要将其再放入下个阶段
### 代码
```java
class Solution {
    public int[] deckRevealedIncreasing(int[] deck) {
        int[] ans = new int[deck.length];
        Arrays.sort(deck);

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < deck.length; i++) {
            list.add(i);
        }

        rescurse(ans, deck, list, true, 0);
        return ans;
    }

    private void rescurse(int[] ans, int[] deck, List<Integer> list, boolean take, int di) {
        if (list.isEmpty()) {
            return;
        }

        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (take) {
                ans[list.get(i)] = deck[di++];
            } else {
                newList.add(list.get(i));
            }
            take = !take;
        }
        rescurse(ans, deck, newList, take, di);
    }
}
```