# [LeetCode_786_第K个最小的素数分数](https://leetcode-cn.com/problems/k-th-smallest-prime-fraction/)
## 解法
### 思路
优先级队列
### 代码
```java
class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> Double.compare(y[0] * 1.0 / y[1], x[0] * 1.0 / x[1]));
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                queue.offer(new int[]{arr[i], arr[j]});
                if (queue.size() > k) {
                    queue.poll();
                }
            }
        }

        return queue.poll();
    }
}
```
## 解法二
### 思路
- 因为序列是严格递增的，所以必然有：
  - arr[0] / arr[1]
  - arr[0] / arr[2] < arr[1] / arr[2]
  - ......
  - arr[0] / arr[n - 1] ... < arr[n - 2] / arr[n - 1]
- 将如上每一行的第一个元素放入优先级队列中，这样就把n-1个最小的元素放入到优先级队列中
- 然后依次的取出队列顶端的元素，这个元素必定是前x个最小的元素
- 之后将这个元素所在行的后一个元素放入到队列中
- 不断循环，直到找到第k个为止
### 代码
```java
class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int len = arr.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingDouble(x -> arr[x[0]] * 1.0 / arr[x[1]]));
        for (int i = 1; i < len; i++) {
            queue.offer(new int[]{0, i});
        }

        while (k-- > 1) {
            int[] a = queue.poll();
            if (a == null) {
                continue;
            }

            if (a[0] + 1 < a[1]) {
                queue.offer(new int[]{a[0] + 1, a[1]});
            }
        }

        int[] a = queue.poll();
        return new int[]{arr[a[0]], arr[a[1]]};
    }
}
```
## 解法三
### 思路
二分法：
- 如果在0和1之间确定一个值x，然后求出分数值比x小的所有元素
- 那么如果正好有k个元素小于x值，那么这k个值里的最大值就是要求的分数组合
- 否则，比k多，说明这个x大了，往其左边找，比k少，说明这个x小了，往其右边找
- 另外，因为数组是单调递增的，所以确认过的分子，在分母增大的过程中，一定也是小于x值的，所以可以不用重复计算比较
### 代码
```java
class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
                double head = 0, tail = 1;
        while (true) {
            double mid = head + (tail - head) / 2;
            int count = 0, x = 0, y = 1, i = -1;
            for (int j = 1; j < arr.length; j++) {
                while (arr[i + 1] * 1.0 / arr[j] < mid) {
                    i++;
                    if (arr[i] * 1.0 / arr[j] > x * 1.0 / y) {
                        x = arr[i];
                        y = arr[j];
                    }
                }
                count += i + 1;
            }

            if (count == k) {
                return new int[]{x, y};
            }

            if (count > k) {
                tail = mid;
            } else {
                head = mid;
            }
        }
    }
}
```
# [LeetCode_400_第N位数字](https://leetcode-cn.com/problems/nth-digit/)
## 解法
### 思路
- 举例：
  - 1位数有9个
  - 2位数有90个，数字有90*2=180
  - 3位数有900个，数字有900*3=2700
  - ...
  - digit位有`pow(10, digit - 1) * 9个`，数字就是`digit * pow(10, digit - 1) * 9`
- 根据如上的例子，就能找到第N个数字在哪位数字中
- 在求该数字在第几位的时候，N累减遍历到的位数对应的个数，在最后找到对应位数的时候，N就对应在当前位的第几个数
- 然后`pow(10, digit - 1)`就能找到当前位的第一个数，然后根据N就能找到第N个数字的位置`(N-1) / digit + pow(10, digit - 1)`
- 然后根据N和digit之间取余的值来确定要求的数字是求出的值得第几个位上的数字
### 代码
```java
class Solution {
    public int findNthDigit(int n) {
        int digit = 1;
        while (Math.pow(10, digit - 1) * 9 * digit < n) {
            n -= Math.pow(10, digit - 1) * 9 * digit;
            digit++;
        }

        int num = (n - 1) / digit + (int) Math.pow(10, digit - 1);

        int index = n % digit == 0 ? digit - 1 : n % digit - 1;
        return Integer.toString(num).charAt(index) - '0';
    }
}
```
# [LeetCode_1446_连续字符](https://leetcode-cn.com/problems/consecutive-characters/)
## 解法
### 思路
循环+模拟
### 代码
```java
class Solution {
    public int maxPower(String s) {
        char[] cs = s.toCharArray();
        int max = 0;
        for (int i = 0; i < cs.length;) {
            char c = cs[i];
            int count = 0;
            while (i < cs.length && c == cs[i]) {
                count++;
                i++;
            }
            
            max = Math.max(count, max);
        }
        
        return max;
    }
}
```
# [LeetCode_23_合并K个升序链表](https://leetcode-cn.com/problems/merge-k-sorted-lists/)
## 解法
### 思路
使用优先级队列做多项归并排序
### 代码
```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode root = new ListNode(0);
        ListNode node = root;

        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x.val));
        
        for (ListNode list : lists) {
            if (list == null) {
                continue;
            }
            
            queue.offer(list);
        }
        
        if (queue.isEmpty()) {
            return null;
        }
        
        while (!queue.isEmpty()) {
            ListNode cur = queue.poll();
            if (cur.next != null) {
                queue.offer(cur.next);
            }
            
            node.next = cur;
            node = cur;
        }
        
        return root.next;
    }
}
```