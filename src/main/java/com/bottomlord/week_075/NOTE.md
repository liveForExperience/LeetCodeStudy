# [LeetCode_379_电话目录管理系统](https://leetcode-cn.com/problems/design-phone-directory/)
## 解法
### 思路
- 优先级队列：保存get时候返回的号码，因为返回时需要有顺序，所以使用优先级队列
- 普通队列：保存release后的号码，在优先级队列为空后，将队列元素放入优先级队列中，在传递的时候，因为release是无序的，所以会使用优先级队列
- 布尔数组：保存号码状态
### 代码
```java
class PhoneDirectory {
    private PriorityQueue<Integer> pQueue;
    private Queue<Integer> queue;
    private boolean[] bucket;
    public PhoneDirectory(int maxNumbers) {
        this.pQueue = new PriorityQueue<>();
        this.queue = new LinkedList<>();
        for (int i = 0; i < maxNumbers; i++) {
            pQueue.offer(i);
        }
        this.bucket = new boolean[maxNumbers];
        Arrays.fill(bucket, true);
    }

    public int get() {
        if (pQueue.isEmpty()) {
            if (queue.isEmpty()) {
                return -1;
            }

            while (!queue.isEmpty()) {
                pQueue.offer(queue.poll());
            }
        }

        if (pQueue.isEmpty()) {
            return -1;
        }

        Integer num = pQueue.poll();
        bucket[num] = false;
        return num;
    }

    public boolean check(int number) {
        if (number > bucket.length) {
            return false;
        }

        return bucket[number];
    }

    public void release(int number) {
        if (check(number)) {
            return;
        }

        queue.offer(number);
        bucket[number] = true;
    }
}
```
# [LeetCode_738_单调递增的数字](https://leetcode-cn.com/problems/monotone-increasing-digits/)
## 失败解法
### 思路
暴力
### 代码
```java
class Solution {
    public int monotoneIncreasingDigits(int N) {
        for (int i = N; i >= 0; i--) {
            if (isValid(i)) {
                return i;
            }
        }

        return 0;
    }

    private boolean isValid(int n) {
        int pre = n % 10;
        n /= 10;

        while (n > 0) {
            int cur = n % 10;
            if (cur > pre) {
                return false;
            }

            pre = cur;
            n /= 10;
        }

        return true;
    }
}
```
## 解法
### 思路
- 从尾部开始判断两个相邻元素之间的关系，如果后一个元素比前一个元素大，那么就需要标记当前元素，标记的目的是为了表示从当前元素开始到最低位，所有值都是9，同时将前一个较大数的值-1
- 然后继续循环判断并在必要情况下更新标记
- 循环结束后，将字符串进行截取，截取范围是0到标记的位置，并拼接从标记位置到字符串结尾
### 代码
```java
class Solution {
    public int monotoneIncreasingDigits(int N) {
        if (N < 10) {
            return N;
        }

        String str = Integer.toString(N);
        char[] cs = str.toCharArray();
        int start9Index = str.length();
        for (int i = cs.length - 1; i > 0; i--) {
            if (cs[i] < cs[i - 1]) {
                cs[i - 1] = (char)(cs[i - 1] - 1);
                start9Index = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        int start = 0;
        for (; start < start9Index; start++) {
            if (cs[start] != '0') {
                break;
            }
        }

        for (int i = start; i < start9Index; i++) {
            sb.append(cs[i]);
        }

        if (start9Index < str.length()) {
            char[] cs9 = new char[str.length() - start9Index];
            Arrays.fill(cs9, '9');
            sb.append(cs9);
        }

        return Integer.parseInt(sb.toString());
    }
}
```
# [LeetCode_385_迷你语法分析器](https://leetcode-cn.com/problems/mini-parser/)
## 解法
### 思路
递归
### 代码
```java
class Solution {
    private int index;
    private char[] cs;
    public NestedInteger deserialize(String s) {
        if (s == null || s.length() == 0) {
            return new NestedInteger();
        }

        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }

        index = 0;
        cs = s.toCharArray();
        return doDeserialize();
    }

    private NestedInteger doDeserialize() {
        NestedInteger ni = new NestedInteger();
        
        boolean negative = false;
        int num = 0;
        while (index < cs.length) {
            index++;

            if (cs[index] == ',') {
            } else if (cs[index] == '[') {
                ni.add(doDeserialize());
            } else if (cs[index] == ']') {
                return ni;
            } else if (cs[index] == '-') {
                negative = true;
            } else {
                num = num * 10 + (negative ? -Integer.parseInt("" + cs[index]) : Integer.parseInt("" + cs[index]));
                if (index < cs.length && (cs[index + 1] == ']' || cs[index + 1] == ',')) {
                    ni.add(new NestedInteger(num));
                    negative = false;
                    num = 0;
                }
            }
        }
        
        return ni;
    }
}
```
# [LeetCode_388_文件的最长绝对路径](https://leetcode-cn.com/problems/longest-absolute-file-path/)
## 解法
### 思路
回溯
### 代码
```java
class Solution {
    private int max;
    public int lengthLongestPath(String input) {
        String[] eles = input.split("\n");
        List<Dir> dirs = new ArrayList<>();
        for (String ele : eles) {
            int level = 0;
            StringBuilder sb = new StringBuilder();
            for (char c : ele.toCharArray()) {
                if (c == '\t') {
                    level++;
                } else {
                    sb.append(c);
                }
            }

            dirs.add(new Dir(level, sb.toString()));
        }

        for (int i = 0; i < dirs.size(); i++) {
            if (dirs.get(i).level == 0) {
                backTrack(dirs, i, 0, new StringBuilder());
            }
        }
        return max == 0 ? 0 : max - 1;
    }

    private void backTrack(List<Dir> dirs, int index, int level, StringBuilder sb) {
        if (index >= dirs.size()) {
            return;
        }

        Dir dir = dirs.get(index);
        sb.append("/").append(dir.name);
        if (isFile(dir.name)) {
            max = Math.max(max, sb.length());
            return;
        }

        for (int i = index + 1; i < dirs.size(); i++) {
            if (dirs.get(i).level == level) {
                break;
            }

            if (dirs.get(i).level == level + 1) {
                int len = sb.length();
                backTrack(dirs, i, level + 1, sb);
                sb.delete(len, sb.length());
            }
        }
    }

    private boolean isFile(String name) {
        for (char c : name.toCharArray()) {
            if (c == '.') {
                return true;
            }
        }
        return false;
    }

    class Dir {
        private int level;
        private String name;

        public Dir(int level, String name) {
            this.level = level;
            this.name = name;
        }
    }
}
```