# [LeetCode_306_累加数](https://leetcode-cn.com/problems/additive-number/)
## 解法
### 思路
回溯+减枝+高精度运算
### 代码
```java
class Solution {
    private String num;
    private int n;
    private final List<List<Integer>> list = new ArrayList<>();
    public boolean isAdditiveNumber(String num) {
        this.num = num;
        this.n = num.length();
        return backTrack(0);
    }

    private boolean backTrack(int index) {
        int size = list.size();
        if (index == n) {
            return list.size() >= 3;
        }

        LinkedList<Integer> cur = new LinkedList<>();
        int max = num.charAt(index) == '0' ? index + 1 : n;

        for (int i = index; i < max; i++) {
            cur.addFirst(num.charAt(i) - '0');
            if (size < 2 || check(list.get(size - 1), list.get(size - 2), cur)) {
                list.add(cur);
                if (backTrack(i + 1)) {
                    return true;
                }
                list.remove(list.size() - 1);
            }
        }

        return false;
    }

    private boolean check(List<Integer> x, List<Integer> y, List<Integer> cur) {
        int num = 0;
        List<Integer> sum = new ArrayList<>();
        for (int i = 0; i < x.size() || i < y.size(); i++) {
            if (i < x.size()) {
                num += x.get(i);
            }

            if (i < y.size()) {
                num += y.get(i);
            }

            sum.add(num % 10);
            num /= 10;
        }
        
        if (num > 0) {
            sum.add(num);
        }

        boolean result = sum.size() == cur.size();

        for (int i = 0; i < sum.size() && result; i++) {
            if (!Objects.equals(sum.get(i), cur.get(i))) {
                result = false;
                break;
            }
        }

        return result;
    }
}
```
# [LeetCode_2037_使每位学生都有座位的最少移动次数](https://leetcode-cn.com/problems/minimum-number-of-moves-to-seat-everyone/)
## 解法
### 思路
- 分别对两个数组排序
- 遍历两个数组，累加差值
### 代码
```java
class Solution {
    public int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);
        int sum = 0;
        for (int i = 0; i < seats.length; i++) {
            sum += Math.abs(seats[i] - students[i]);
        }
        return sum;
    }
}
```
## 解法二
### 思路
自己实现排序
### 代码
```java
class Solution {
    public int minMovesToSeat(int[] seats, int[] students) {
        sort(seats);
        sort(students);
        int sum = 0;
        for (int i = 0; i < seats.length; i++) {
            sum += Math.abs(seats[i] - students[i]);
        }

        return sum;
    }

    private void sort(int[] arr) {
        doSort(0, arr.length - 1, arr);
    }

    private void doSort(int head, int tail, int[] arr) {
        if (head >= tail) {
            return;
        }

        int pivot = partition(head, tail, arr);

        doSort(head, pivot - 1, arr);
        doSort(pivot + 1, tail, arr);
    }

    private int partition(int head, int tail, int[] arr) {
        int num = arr[head];
        while (head < tail) {
            while (head < tail && arr[tail] >= num) {
                tail--;
            }

            arr[head] = arr[tail];

            while (head < tail && arr[head] <= num) {
                head++;
            }

            arr[tail] = arr[head];
        }

        arr[head] = num;
        return head;
    }
}
```
# [LeetCode_2042_检查句子中的数字是否递增](https://leetcode-cn.com/problems/check-if-numbers-are-ascending-in-a-sentence/)
## 解法
### 思路
遍历模拟
### 代码
```java
class Solution {
    public boolean areNumbersAscending(String s) {
        int pre = 0;
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (!isDigit(cs[i])) {
                continue;
            }
            
            int num = 0;
            while (i < cs.length && isDigit(cs[i])) {
                num = num * 10 + (cs[i] - '0');
                i++;
            }
            
            if (num <= pre) {
                return false;
            }

            pre = num;
        }
        
        return true;
    }
    
    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
```
# [LeetCode_1036_逃离大迷宫](https://leetcode-cn.com/problems/escape-a-large-maze/)
## 解法
### 思路
bfs+记忆化+减枝
- 减枝逻辑参考：[题解](https://leetcode-cn.com/problems/escape-a-large-maze/solution/gong-shui-san-xie-bfs-gei-ding-zhang-ai-8w63o/)
### 代码
```java
class Solution {
    private long base = 131;
    private int[][] dirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private int max;
    private Set<Long> set = new HashSet<>();
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        for (int[] block : blocked) {
            set.add(block[0] * base + block[1]);
        }
        int n = blocked.length;
        max = n * (n - 1) / 2;
        return dfs(source, target) && dfs(target, source);
    }

    private boolean dfs(int[] a, int[] b) {
        Set<Long> memo = new HashSet<>();
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(a);
        while (!queue.isEmpty() && memo.size() <= max) {
            int[] arr = queue.poll();
            int x = arr[0], y = arr[1];
            if (x == b[0] && y == b[1]) {
                return true;
            }

            for (int[] dir : dirs) {
                int nx = dir[0] + arr[0], ny = dir[1] + arr[1];
                if (nx < 0 || nx >= 1000000 || ny < 0 || ny >= 1000000) {
                    continue;
                }

                long hash = nx * base + ny;
                if (set.contains(hash) || memo.contains(hash)) {
                    continue;
                }

                memo.add(hash);
                queue.add(new int[]{nx, ny});
            }
        }

        return memo.size() > max;
    }
}
```
# [LeetCode_2047_句子中的有效单词数](https://leetcode-cn.com/problems/number-of-valid-words-in-a-sentence/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int countValidWords(String sentence) {
        char[] cs = sentence.toCharArray();
        int count = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == ' ') {
                continue;
            }

            int start = i;
            while (i < cs.length && cs[i] != ' ') {
                i++;
            }
            int end = i - 1;

            if (check(cs, start, end)) {
                count++;
            }
        }
        
        return count;
    }

    private boolean check(char[] cs, int start, int end) {
        if (cs[start] == '-' || cs[end] == '-') {
            return false;
        }

        int count = 0, count2 = 0;

        for (int i = start; i <= end; i++) {
            char c = cs[i];
            if (c >= 'a' && c <= 'z') {
                continue;
            }

            if (c == '-') {
                count++;

                char pre = cs[i - 1], next = cs[i + 1];
                if (pre < 'a' || pre > 'z' || next < 'a' || next > 'z') {
                    return false;
                }

                if (count > 1) {
                    return false;
                }
                continue;
            }

            if (c == '!' || c == '.' || c == ',') {
                if (i != end) {
                    return false;
                }

                count2++;

                if(count2 > 1) {
                    return false;
                }

                continue;
            }

            return false;
        }

        return true;
    }
}
```
# [LeetCode_2053_数组中第K个独一无二的字符串](https://leetcode-cn.com/problems/kth-distinct-string-in-an-array/)
## 解法
### 思路
- 遍历字符串数组，使用map记录当前字符串的出现个数
- 再次遍历字符串数组，判断当前字符串是否是独一无二的，并根据k找到指定的字符串
### 代码
```java
class Solution {
    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : arr) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        
        int count = 0;
        for (String s : arr) {
            if (map.get(s) == 1) {
                count++;
            }
            
            if (count == k) {
                return s;
            }
        }
        
        return "";
    }
}
```
## 解法二
### 思路
使用2个set来替代解法一中的map
### 代码
```java
class Solution {
    public String kthDistinct(String[] arr, int k) {
        Set<String> set = new HashSet<>(), notOnlyOne = new HashSet<>();
        for (String s : arr) {
            if (!set.add(s)) {
                notOnlyOne.add(s);
            }
        }

        int count = 0;
        for (String s : arr) {
            if (notOnlyOne.contains(s)) {
                continue;
            }
            
            if (k == ++count) {
                return s;
            }
        }
        
        return "";
    }
}
```