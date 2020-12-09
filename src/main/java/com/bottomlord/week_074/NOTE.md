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