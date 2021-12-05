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
# [LeetCode_506_相对名次](https://leetcode-cn.com/problems/relative-ranks/)
## 解法
### 思路
- 初始化一个坐标值数组，从0到n-1
- 根据坐标对应的score值倒叙排序
- 根据坐标值数组排序后的顺序，依次初始化对应的字符串数组作为结果
### 代码
```java
class Solution {
    public String[] findRelativeRanks(int[] score) {
        int len = score.length;
        Integer[] arr = new Integer[len];
        for (int i = 0; i < len; i++) {
            arr[i] = i;
        }

        String[] ans = new String[len];
        Arrays.sort(arr, (x, y) -> score[y] - score[x]);
        
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                ans[arr[i]] = "Gold Medal";
            } else if (i == 1) {
                ans[arr[i]] = "Silver Medal";
            } else if (i == 2) {
                ans[arr[i]] = "Bronze Medal";
            } else {
                ans[arr[i]] = i + 1 + "";
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
桶排序
- 根据score数组最大值构建一个int包装类数组
- 遍历score，将score值对应的坐标放入包装类数组中
- 倒叙遍历包装类数组，跳过为null的元素，然后对非null的元素进行计数
- 将前三个元素的坐标值放到结果数组中，做好转义，剩余的则是将计数值放入结果数组中
### 代码
```java
class Solution {
    public String[] findRelativeRanks(int[] score) {
        int max = Arrays.stream(score).max().getAsInt();
        Integer[] bucket = new Integer[max + 1];
        for (int i = 0; i < score.length; i++) {
            bucket[score[i]] = i;
        }
        
        int count = 0;
        String[] ans = new String[score.length];
        for (int i = bucket.length - 1; i >= 0; i--) {
            Integer index = bucket[i];
            
            if (index == null) {
                continue;
            }

            count++;
            ans[index] = count > 3 ? "" + count : count == 1 ? "Gold Medal" : count == 2 ? "Silver Medal" : "Bronze Medal";
        }
        
        return ans;
    }
}
```
# [LeetCode_1005_K次取反后最大化的数组和](https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations/)
## 解法
### 思路
- 因为数字的范围是-100到100，可以用桶排序的方式对数字做计数和排序
- 根据k的次数在负数区间中做取反的模拟
  - 负数计数-1，相反数计数+1
- 如果k还有剩余，计算是偶数个还是计数个
  - 偶数个不处理
  - 奇数个就找到非负整数区间中最小的一个，做翻转处理（可以预先累加到结果里，这样下次求和时候不需要遍历负数区间）
- 遍历数组非负整数区间累加结果并返回
### 代码
```java
class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        int[] bucket = new int[201];
        for (int num : nums) {
            bucket[num + 100]++;
        }

        for (int i = 0; i < 101 && k > 0; i++) {
            if (bucket[i] == 0) {
                continue;
            }

            while (bucket[i] > 0 && k > 0) {
                bucket[i]--;
                bucket[200 - i]++;
                k--;
            }
        }

        int sum = 0;
        if (k % 2 == 1) {
            for (int i = 100; i < bucket.length; i++) {
                if (bucket[i] != 0) {
                    bucket[i]--;
                    sum -= (i - 100);
                    break;
                }
            }
        }

        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == 0) {
                continue;
            }

            int time = bucket[i];
            sum += time * (i - 100);
        }

        return sum;
    }
}
```
# [LeetCode_383_赎金信](https://leetcode-cn.com/problems/ransom-note/)
## 解法
### 思路
桶计数：
- 遍历赎金信的字符串，对字母计数
- 遍历杂志的字符串，对字母计数值做累减，如果总数为0就返回true
- 遍历结束还不是0就返回false
### 代码
```java
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int count = 0;
        int[] bucket = new int[26];
        for (char c : ransomNote.toCharArray()) {
            bucket[c - 'a']++;
            count++;
        }
        
        for (char c : magazine.toCharArray()) {
            if (bucket[c - 'a'] > 0) {
                bucket[c - 'a']--;
                count--;
            }
            
            if (count == 0) {
                return true;
            }
        }
        
        return false;
    }
}
```
# [LeetCode_1897_重新分配字符使所有字符串都相等](https://leetcode-cn.com/problems/redistribute-characters-to-make-all-strings-equal/)
## 解法
### 思路
- 统计字符串中字母出现的个数
- 判断是否都能被字符串个数整除，如果可以就是true，否则false
### 代码
```java
class Solution {
    public boolean makeEqual(String[] words) {
        int n = words.length;
        int[] bucket = new int[26];
        
        for (String word : words) {
            for (char c : word.toCharArray()) {
                bucket[c - 'a']++;
            }
        }

        for (int num : bucket) {
            if (num % n != 0) {
                return false;
            }
        }
        
        return true;
    }
}
```
# [LeetCode_1903_字符串中的最大奇数](https://leetcode-cn.com/problems/largest-odd-number-in-string/)
## 失败解法
### 原因
超时
### 思路
- 2层循环
  - 外层循环数字长度
  - 内层循环数字起始字符
- 内层判断当前层是否有奇数，且保留最大值，内层循环结束后如果有符合的值就返回
- 循环结束，则表明没有符合的，返回空字符串
### 代码
```java
class Solution {
  public String largestOddNumber(String num) {
    int n = num.length();

    boolean flag = false;
    String max = "0";
    for (int i = n; i >= 1; i--) {
      for (int j = 0; j + i <= n; j++) {
        if (num.charAt(j) == '0') {
          continue;
        }

        if (Integer.parseInt(num.charAt(j + i - 1) + "") % 2 == 1) {
          flag = true;
          max = max(num.substring(j, j + i), max);
        }
      }

      if (flag) {
        return max;
      }
    }

    return "";
  }

  private String max(String x, String y) {
    int num = x.compareTo(y);
    return num == 0 ? x : num > 0 ? x : y;
  }
}
```
## 解法
### 思路
- 从头部找到第一个非0字符
- 从字符串尾部找到第一个奇数字符
- 返回区间确定的字符串返回
### 代码
```java
class Solution {
  public String largestOddNumber(String num) {
    Set<Character> set = new HashSet<>();
    set.add('1');
    set.add('3');
    set.add('5');
    set.add('7');
    set.add('9');
    int start = num.length(), end = -1;
    for (int i = 0; i < num.length(); i++) {
      if (num.charAt(i) != '0') {
        start = i;
        break;
      }
    }

    for (int i = num.length() - 1; i >= 0; i--) {
      if (set.contains(num.charAt(i))) {
        end = i;
        break;
      }
    }

    return end >= start ? num.substring(start, end + 1) : "";
  }
}
```