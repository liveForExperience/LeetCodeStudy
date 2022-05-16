# [Contest_1_移除字母异位词后的结果数组](https://leetcode.cn/problems/find-resultant-array-after-removing-anagrams/)
## 解法
### 思路
- 初始化结果list，将第一个字符串放入结果list中
- 从第二个坐标开始遍历字符串数组，依次比较当前字符串和之前字符串之间是否是异位词
- 判断异位词的方式是比较他们的字母出现次数是否一致，如果一致就是异位词
- 如果不是异位词就放入结果list中
- 遍历结束返回结果list
### 代码
```java
class Solution {
    public List<String> removeAnagrams(String[] words) {
        int n = words.length;
        List<String> ans = new ArrayList<>();
        ans.add(words[0]);
        for (int i = 1; i < n; i++) {
            if (!check(words[i - 1], words[i])) {
                ans.add(words[i]);
            }
        }
        
        return ans;
    }

    private boolean check(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        
        char[] csa = a.toCharArray(), csb = b.toCharArray();
        int[] bucket = new int[26];
        for (char c : csa) {
            bucket[c - 'a']++;
        }
        
        for (char c : csb) {
            bucket[c - 'a']--;
        }

        for (int i : bucket) {
            if (i != 0){
                return false;
            }
        }
        
        return true;
    }
}
```
# [Contest_2_不含特殊楼层的最大连续楼层数](https://leetcode.cn/problems/maximum-consecutive-floors-without-special-floors/)
## 失败解法
### 原因
超时
### 思路
- 用set存储特殊楼层
- 遍历数字依次判断是否set中存在
- 用count记录连续次数，当遇到特殊楼层就重置，并和最大值比较，暂存最大值
- 循环结束，返回最大值
### 代码
```java
class Solution {
    public int maxConsecutive(int bottom, int top, int[] special) {
        Set<Integer> set = new HashSet<>();
        for (int num : special) {
            set.add(num);
        }

        int count = 0, max = count;
        for (int i = bottom; i <= top; i++) {
            if (set.contains(i)) {
                max = Math.max(max, count);
                count = 0;
            } else {
                count++;
            }
        }

        max = Math.max(max, count);

        return max;
    }
}
```
## 解法
### 思路
- 将特殊楼层数组进行排序
- 根据特殊楼层数组之间的空隙来判断最大楼层数
### 代码
```java
class Solution {
    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int max = 0, n = special.length;
        for (int i = 0; i < n; i++) {
            if (special[i] > bottom) {
                max = Math.max(special[i] - bottom, max);
            }

            bottom = special[i] + 1;
        }

        if (top > special[n - 1]) {
            max = Math.max(top - special[n - 1], max);
        }
        
        return max;
    }
}
```
# [Contest_3_按位与结果大于零的最大组合](https://leetcode.cn/problems/largest-combination-with-bitwise-and-greater-than-zero/)
## 解法
### 思路
- 遍历int32位
- 找到某一位上1的个数最多的位
- 返回这个最大的个数值作为结果
### 代码
```java
class Solution {
    public int largestCombination(int[] candidates) {
        int max = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0, mask = 1 << i;
            for (int candidate : candidates) {
                if ((candidate & mask) != 0) {
                    count++;
                }
            }
            max = Math.max(count, max);
        }
        
        return max;
    }
}
```
# [Contest_4_统计区间中的整数数目](https://leetcode.cn/problems/count-integers-in-intervals/)
## 解法
### 思路
线段树
- 根据题目了解到整个样本空间的范围是1-1e9
- 基于样本空间，初始化1-1e9的线段树
- 节点中存储左右子树指针和sum值
- 初始化sum值为0
- add时候，根据L和R，在线段树中找到对应的分段，求得sum值，并层层回溯到根节点
- 每次add都会更新到新的分段，并通过上级节点层层反馈给根节点
### 代码
```java
class CountIntervals {
    CountIntervals left, right;
    int l, r, sum;

    public CountIntervals() {
        l = 1;
        r = (int) 1e9;
    }

    CountIntervals(int l, int r) {
        this.l = l;
        this.r = r;
    }

    public void add(int L, int R) {
        if (sum == r - l + 1) {
            return;
        }

        if (L <= l && r <= R) {
            sum = r - l + 1;
            return;
        }

        int mid = (l + r) / 2;
        if (left == null) {
            left = new CountIntervals(l, mid);
        }

        if (right == null) {
            right = new CountIntervals(mid + 1, r);
        }

        if (L <= mid) {
            left.add(L, R);
        }

        if (R > mid) {
            right.add(L, R);
        }

        sum = left.sum + right.sum;
    }

    public int count() {
        return sum;
    }
}
```
## 解法二
### 思路
TreeMap
- 初始化treemap和sum
- 将区间存储到treemap中，key为区间的左边界
- add的时候，根据R在map中找到floorentry，也就是左边界比R小的最大区间
- 不停判断这些区间的右边界是否比L大，如果是大的，说明新的区间和找到的这个区间之间是有交集的，就将这个老区间删除掉，并更新左右边界，相当于合并
- 一直到找不到这个符合的entry为止
- 然后将之前更新的新的左右边界放入到map中
- 在更新的过程中还需要将原来的那些边界形成的区间在sum中减去
- 在添加到map的时候，则增加进去
### 代码
```java
class CountIntervals {

    private TreeMap<Integer, int[]> map;
        private int sum;

        public CountIntervals() {
            this.map = new TreeMap<>();
            this.sum = 0;
        }

        public void add(int L, int R) {
            Map.Entry<Integer, int[]> entry = map.floorEntry(R);
            int l = L, r = R;
            while (entry != null && l <= entry.getValue()[1]) {
                l = Math.min(l, entry.getValue()[0]);
                r = Math.max(r, entry.getValue()[1]);
                map.remove(entry.getKey());
                sum -= entry.getValue()[1] - entry.getValue()[0] + 1;
                entry = map.floorEntry(R);
            }

            map.put(l, new int[]{l, r});
            sum += r - l + 1;
        }

        public int count() {
            return sum;
        }
}
```