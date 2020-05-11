# Interview_1714_最小K个数
## 题目
设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。

示例：
```
输入： arr = [1,3,5,7,2,4,6,8], k = 4
输出： [1,2,3,4]
```
提示：
```
0 <= len(arr) <= 100000
0 <= k <= min(100000, len(arr))
```
## 解法
### 思路
快排
### 代码
```java
class Solution {
    public int[] smallestK(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }
        
        Arrays.sort(arr);
        int[] ans = new int[k];
        System.arraycopy(arr, 0, ans, 0, k);
        return ans;
    }
}
```
## 解法二
### 思路
小顶堆
### 代码
```java
class Solution {
    public int[] smallestK(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int num : arr) {
            queue.offer(num);
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            if (queue.isEmpty()) {
                return ans;
            }
            
            ans[i] = queue.poll();
        }
        
        return ans;
    }
}
```
## 解法三
### 思路
自己实现快排
- 题目要求返回的数组元素不需要排序，所以只要partition的坐标位置与k-1相等就可以
- 因为确定partition的过程就是将小于当前坐标应有元素的值全部放置到了该坐标左侧
### 代码
```java
class Solution {
    public int[] smallestK(int[] arr, int k) {
        int head = 0, tail = arr.length - 1;
        while (head < tail) {
            int pivot = partition(arr, head, tail);
            if (pivot == k - 1) {
                break;
            } else if (pivot < k - 1) {
                head = pivot + 1;
            } else {
                tail = pivot - 1;
            }
        }

        int[] ans = new int[k];
        System.arraycopy(arr, 0, ans, 0, k);
        return ans;
    }

    private int partition(int[] arr, int head, int tail) {
        int low = arr[head];
        while (head < tail) {
            while (head < tail && arr[tail] >= low) {
                tail--;
            }
            arr[head] = arr[tail];
            
            while (head < tail && arr[head] <= low) {
                head++;
            }
            arr[tail] = arr[head];
        }
        
        arr[head] = low;
        return head;
    }
}
```
# Interview_1715_最长单词
## 题目
给定一组单词words，编写一个程序，找出其中的最长单词，且该单词由这组单词中的其他单词组合而成。若有多个长度相同的结果，返回其中字典序最小的一项，若没有符合要求的单词则返回空字符串。

示例：
```
输入： ["cat","banana","dog","nana","walk","walker","dogwalker"]
输出： "dogwalker"
解释： "dogwalker"可由"dog"和"walker"组成。
```
提示：
```
0 <= len(words) <= 100
1 <= len(words[i]) <= 100
```
## 解法
### 思路
set+递归：
- 对字符串进行排序：
    - 长度降序
    - 字符升序
- 根据字符串数组生成set
- 遍历排序后的字符串数组
- 递归：
    - 退出条件：当字符串长度为0，返回true。代表之前所有字符在字符串数组中都能找到对应的字符串
    - 过程；
        - 从1开始遍历字符串的字符坐标，判断0到i生成的字符串是否在set中存在，且不能是自己
        - 如果存在，继续递归，并从i+1位置开始
### 代码
```java
class Solution {
    public String longestWord(String[] words) {
        Arrays.sort(words, (x1, x2) -> {
            if (x1.length() == x2.length()) {
                return x1.compareTo(x2);
            }

            return x2.length() - x1.length();
        });

        Set<String> set = new HashSet<>(Arrays.asList(words));

        for (String word : words) {
            if (recurse(word, 0, set)) {
                return word;
            }
        }
        
        return "";
    }

    private boolean recurse(String word, int start, Set<String> set) {
        if (start >= word.length()) {
            return true;
        }

        boolean flag = false;
        for (int i = start; i < word.length(); i++) {
            String tmp = word.substring(start, i + 1);
            if (!Objects.equals(tmp, word) && set.contains(word.substring(start, i + 1))) {
                flag = recurse(word, i + 1, set);
            }

            if (flag) {
                return true;
            }
        }

        return false;
    }
}
```