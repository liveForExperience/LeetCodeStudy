# [查找和最小的K对数字](https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/)
## 解法
### 思路
嵌套遍历+小顶堆
### 代码
```java
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(k, Comparator.comparingInt(x -> (x[0] + x[1])));
        boolean searched = false;
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int sum = nums1[i] + nums2[j];
                if (sum >= max) {
                    break;
                }

                queue.offer(new int[]{nums1[i], nums2[j]});

                if (!searched && queue.size() == k) {
                    max = Integer.MIN_VALUE;
                    for (int[] nums : queue) {
                        max = Math.max(max, nums[0] + nums[1]);
                    }
                    searched = true;
                }
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        while (!queue.isEmpty() && k-- > 0) {
            int[] nums = queue.poll();
            ans.add(Arrays.asList(nums[0], nums[1]));
        }
        return ans;
    }
}
```
# [;eetCode_842_将数组拆分成斐波那契数列](https://leetcode-cn.com/problems/split-array-into-fibonacci-sequence/)
## 解法
### 思路
- 斐波那契数列：`f[n] = f[n - 1] + f[n - 2]`
- 回溯：
    - 回溯函数变量：
        - 原始字符串：s
        - 遍历的坐标：index
        - 回溯时使用的暂存元素的list：list
        - 前两个数的总和：sum
        - 前一个元素值：pre
    - 退出条件：`index = s.lenght`，说明当前S已经被搜索完毕，此时就要判断list中是否有至少3个元素，如果没有就不符合题目要求
    - 过程：
        - 从index开始遍历s字符串，目的是生成当前元素
        - 每添加一个元素，需要判断2件事：
            - 不允许数字以0开头，除非当前元素是0。所以需要判断当前数字长度是否超过1，且起始元素是0(也就是index对应的字符是0)
            - 不能超过int最大值，所以暂存的当前值需要是long类型，然后再与int的最大值进行比较
        - 如果如上两种情况都不存在，那么就开始判断：
            - 当前元素与sum值之间的关系
                - 如果小于，那说明当前值还需要继续拼接，continue
                - 如果大于，说明当前这个数不符合要求，回溯到上一层，重新计算处理
                - 如果相等，那么就将当前元素放入list中，然后继续递归，寻找下一个元素，`sum = pre + cur`，`pre = cur`
        - 如果遍历完所有元素都还在当前层，说明当前路径不通，直接返回false
### 代码
```java
class Solution {
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> ans = new ArrayList<>();
        backTrack(S, 0, ans, 0, 0);
        return ans;
    }
    
    private boolean backTrack(String s, int index, List<Integer> list, int sum, int pre) {
        if (index == s.length()) {
            return list.size() >= 3;
        }
        
        long curLong = 0L;
        for (int i = index; i < s.length(); i++) {
            if (i > index && s.charAt(index) == '0') {
                return false;
            }
            
            curLong = curLong * 10 + (s.charAt(i) - '0');
            if (curLong > Integer.MAX_VALUE) {
                return false;
            }
            
            int cur = (int) curLong;
            if (list.size() > 1) {
                if (cur > sum) {
                    return false;
                }

                if (cur < sum) {
                    continue;
                }
            }
            
            list.add(cur);
            if (backTrack(s, i + 1, list, pre + cur, cur)) {
                return true;
            } else {
                list.remove(list.size() - 1);
            }
        }
        
        return false;
    }
}
```
# [LeetCode_375_猜数字大小II](https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/)
## 失败解法
### 思路
- 题意：在[1,n]的区间中找到开销最小的情况，而为了使每一种开销可能都是最差的情况，则就是如下这种情况
    - 猜的数字不对
    - 然后在指示下寻找下一个数字时，又选错
    - 直到剩下一个数字可以选择
- 基于题意分析，这个找到一种开销的最差情况的过程可以被拆分成分治的状态：`nums[i] + max(f(start, i - 1), f(i + 1, end))`
- f()就是整体计算开销的递归方法
- f()的过程：
    - 退出条件：`start >= end`，代表当前计算开销的过程结束了，最后那个该被找到的值找到了
    - 然后开始循环遍历数组`[start, end]`，顺序选取数字作为当前第一个猜错的数字，并继续递归
    - 通过计算公式`nums[i] + max(f(start, i - 1), f(i + 1, end))`得出以当前这个数为第一个猜错数字后，最大的开销值
    - 然后用这个开销值与其他遍历到的开销值作比较，暂存一个最小值
    - 递归的最后，将计算出来的最小值返回到上一层
### 代码
```java
class Solution {
    public int getMoneyAmount(int n) {
        return recurse(1, n);
    }
    
    private int recurse(int start, int end) {
        if (start >= end) {
            return 0;
        }
        
        int min = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            int sum = i + Math.max(recurse(start, i - 1), recurse(i + 1, end));
            min = Math.min(min, sum);
        }
        
        return min;
    }
}
```
## 失败解法二
### 思路
在失败解法上做记忆化搜索
### 代码
```java
class Solution {
    public int getMoneyAmount(int n) {
        return recurse(1, n, new HashMap<>());
    }

    private int recurse(int start, int end, Map<String, Integer> memo) {
        if (start >= end) {
            return 0;
        }

        if (memo.containsKey(start + " " + end)) {
            return memo.get(start + " " + end);
        }

        int min = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            int sum = i + Math.max(recurse(start, i - 1, memo), recurse(i + 1, end, memo));
            min = Math.min(min, sum);
        }

        memo.put(start + " " + end, min);
        return min;
    }
}
```
## 解法
### 思路
在`[start,end]`区间中，如果遍历的范围是`[start, (start + end) / 2]`，那么开销值较大的永远是右边区间，所以可以直接从`(start + end) / 2`范围开始遍历，免去不必要的开销
### 代码
```java
class Solution {
    public int getMoneyAmount(int n) {
        return recurse(1, n, new HashMap<>());
    }

    private int recurse(int start, int end, Map<String, Integer> memo) {
        if (start >= end) {
            return 0;
        }
        
        if (memo.containsKey(start + " " + end)) {
            return memo.get(start + " " + end);
        }

        int min = Integer.MAX_VALUE;
        for (int i = (start + end) / 2; i <= end; i++) {
            int sum = i + Math.max(recurse(start, i - 1, memo), recurse(i + 1, end, memo));
            min = Math.min(min, sum);
        }

        memo.put(start + " " + end, min);
        return min;
    }
}
```
# [LeetCode_649_Dota2](https://leetcode-cn.com/problems/dota2-senate/)
## 解法
### 思路
循环队列：
- 初始化两个队列，分别用来存储两个阵营参议院的下下标
- 遍历一次字符串，将两个阵营的参议院坐标分别压入对应队列中
- 然后再开始一个循环
    - 退出条件：有一个队列为空
    - 过程：同时弹出两个队列的头元素，比较下标大小，小的那个保留，重新压入队列中，但此时在原来的坐标基础上加上总参议员的个数
    - 最终返回那个有剩余参议院对应的字符串
### 代码
```java
class Solution {
    public String predictPartyVictory(String senate) {
        int len = senate.length();
        
        Queue<Integer> radiantQueue = new LinkedList<>(),
                       direQueue = new LinkedList<>();
        
        for (int i = 0; i < len; i++) {
            if (senate.charAt(i) == 'R') {
                radiantQueue.offer(i);
            } else {
                direQueue.offer(i);
            }
        }
        
        while (!radiantQueue.isEmpty() && !direQueue.isEmpty()) {
            int rIndex = radiantQueue.poll(), dIndex = direQueue.poll();
            
            if (rIndex < dIndex) {
                radiantQueue.offer(rIndex + len);
            } else {
                direQueue.offer(dIndex + len);
            }
        }
        
        return radiantQueue.isEmpty() ? "Dire" : "Radiant";
    }
}
```