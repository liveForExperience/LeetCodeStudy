# [LeetCode_1838_最高频元素的频数](https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element/)
## 解法
### 思路
排序+滑动窗口
- 对数组排序，是的数组成为一个升序序列
- 这样就可以通过遍历的方式，判断要变化的目标值，也就是滑动窗口右边界
- 初始化滑动窗口的左右指针，分别为0和1
- 遍历过程中，累加达到右边界需要的变化数：
    - 每次移动右边界的时候，都需要累加右边界变化时增加的数，也就是`(nums[r] - nums[r - 1]) * (r - l)`
    - 然后判断total（累加的变化值）的大小是否大于了K，如果大了，就移动左边界，缩小total直到小于等于K为止
    - 然后比较暂存窗口大小和当前窗口大小的大小，取较大值暂存
- 需要注意
    - total要用long值否则会溢出
    - ans最小值是1
### 代码
```java
class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int l = 0, n = nums.length, ans = 1;
        long total = 0;
        for (int r = 1; r < n; r++) {
            total += (long) (nums[r] - nums[r - 1]) * (r - l);
            while (total > k) {
                total -= (nums[r] - nums[l]);
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
```
# [LeetCode_1342_将数字变成0的操作次数](https://leetcode-cn.com/problems/number-of-steps-to-reduce-a-number-to-zero/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
  public int numberOfSteps(int num) {
    int count = 0;
    while (num > 0) {
      if (num % 2 == 0) {
        num /= 2;
      } else {
        num--;
      }

      count++;
    }
    return count;
  }
}
```
# [LeetCode_1877_数组中最大数对和的最小值](https://leetcode-cn.com/problems/minimize-maximum-pair-sum-in-array/)
## 解法
### 思路
排序后头尾匹配，求最大值
### 代码
```java
class Solution {
  public int minPairSum(int[] nums) {
    Arrays.sort(nums);
    int head = 0, tail = nums.length - 1, max = Integer.MIN_VALUE;
    while (head < tail) {
      max = Math.max(nums[head++] + nums[tail--], max);
    }

    return max;
  }
}
```
# [LeetCode_1346_检查整数及其两倍数是否存在](https://leetcode-cn.com/problems/check-if-n-and-its-double-exist/)
## 错误解法
### 原因
解法错误，0的2倍是本身，不能直接判断
### 思路
- 遍历数组，将元素存储在set中
- 遍历set，查找是否存在是当前元素2倍的元素
- 有就返回true，否则false
### 代码
```java
class Solution {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        for (int num : set) {
            if (set.contains(num * 2)) {
                return true;
            }
        }
        
        return false;
    }
}
```
## 解法
### 思路
在错误解法上增加一个变量记录0出现的个数，如果只出现1次，则遍历到0的时候，直接跳过，否则0出现多余1次，直接返回true
### 代码
```java
class Solution {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int zero = 0;
        for (int num : arr) {
            if (num == 0) {
                zero++;
                if (zero > 1) {
                    return true;
                }
            }
            
            set.add(num);
        }
        
        for (int num : set) {
            if (num == 0) {
                continue;
            }
            
            if (set.contains(num * 2)) {
                return true;
            }
        }
        
        return false;
    }
}
```
## 解法二
### 思路
数组代替set
- 元素大小在-1000到1000之间，所以数组长度初始化为2001
- 遍历数组，将当前元素*2+1000的值作为2倍后的值，将当前元素+1000的值作为当前值
- 然后在数组中查找
    - 当前值/2的元素是否已经计数，这里还有个前提，当前值必须是偶数，否则不可能是被*2得到的数
    - 2倍值的元素是否已经计数，这里有个前提，如果求出的*2的数大于-1000到1000的范围，也就是0到2000的范围，那么这种元素根本不可能出现在数组中，就不需要考虑
    - 如果如上有计数，就返回true
### 代码
```java
class Solution {
  public boolean checkIfExist(int[] arr) {
    int[] count = new int[2001];
    for (int num : arr) {
      int d = num * 2 + 1000;
      if (d >= 0 && d <= 2000 && count[d] > 0) {
        return true;
      }

      if (num % 2 == 0 && count[num / 2 + 1000] > 0) {
        return true;
      }

      count[num +1000]++;
    }

    return false;
  }
}
```
# [LeetCode_1351_统计有序矩阵中的负数](https://leetcode-cn.com/problems/count-negative-numbers-in-a-sorted-matrix/)
## 解法
### 思路
- 遍历二维数组，按行遍历，从尾部开始遍历，直到找到第一个非负整数位置，计数累加
- 一行行计算累加，最后返回
### 代码
```java
class Solution {
    public int countNegatives(int[][] grid) {
                int col = grid[0].length;
        int count = 0;
        for (int[] rows : grid) {
            for (int c = col - 1; c >= 0; c--) {
                if (rows[c] >= 0) {
                    break;
                }
                count++;
            }
        }
        return count;
    }
}
```
## 解法二
### 思路
- 一行行遍历判断
- 特殊情况：
  - 第一个元素是负数，那么整行都是负数
  - 最后一个元素是非负数，那么整行都不是负数
- 通常情况：通过二分查找找到第一个负数，然后直接累加这个负数到尾部的长度
### 代码
```java
class Solution {
    public int countNegatives(int[][] grid) {
        int col = grid[0].length, count = 0;
        for (int[] row : grid) {
            if (row[0] < 0) {
                count += col;
                continue;
            }
            
            if (row[col - 1] >= 0) {
                continue;
            }
            
            int head = 0, tail = col - 1;
            while (head <= tail) {
                int mid = head + (tail - head) / 2;
                if (row[mid] < 0) {
                    tail = mid - 1;
                } else {
                    head = mid + 1;
                }
            }
            
            count += col - head;
        }
        
        return count;
    }
}
```
# [LeetCode_offer52_1_两个链表的第一个公共节点](https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/)
## 解法
### 思路
- 初始化两个了链表的头结点
- 然后同时以1的步长一起遍历两个链表
- 任意一个链表走到头，就从另一个链表的头节点继续走
- 如果同时是null，说明没有公共节点，否则，遍历到第一个相同的节点，就是公共节点
- 注意特殊判断，任意一个链表为空的情况，直接返回null
### 代码
```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode a = headA, b = headB;
        while (a != b) {
            a = a.next;
            b = b.next;
            
            if (a == null && b == null) {
                return null;
            }
            
            if (a == null) {
                a = headB;
            }
            
            if (b == null) {
                b = headA;
            }
        }
        
        return a;
    }
}
```
## 解法二
### 思路
基于解法一的思路简化代码
### 代码
```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
                ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        
        return a;
    }
}
```
# [LeetCode_138_复制带随机指针的链表](https://leetcode-cn.com/problems/copy-list-with-random-pointer/)
## 解法
### 思路
- 遍历原链表进行复制，将next指针指向新的节点，random指针指向原来的节点
- 遍历过程中存储原节点和新节点的映射关系
- 遍历新节点，通过映射表将原节点替换成新节点
### 代码
```java
class Solution {
  public Node copyRandomList(Node head) {
    Node node = head;
    Node newHead = new Node(0);
    Node newNode = newHead;

    Map<Node, Node> mapping = new HashMap<>();

    while (node != null) {
      newNode.next = new Node(node.val);
      newNode = newNode.next;
      newNode.random = node.random;
      mapping.put(node, newNode);
      node = node.next;
    }

    node = newHead.next;
    while (node != null) {
      node.random = mapping.get(node.random);
      node = node.next;
    }

    return newHead.next;
  }
}
```
# [LeetCode_1360_日期之间隔几天](https://leetcode-cn.com/problems/number-of-days-between-two-dates/)
## 解法
### 思路
- 用前缀和算出年、月分别对应的日子的总和
- 然后解析两个日期后分别带入到前缀和里求出总和的差作为结果即可
- 需要注意闰年的情况
### 代码
```java
class Solution {
    public int daysBetweenDates(String date1, String date2) {
        int[] yearSum = new int[2101];
        int[] monthSum = new int[13];
        int[] month = new int[]{0, 31,28,31,30,31,30,31,31,30,31,30,31};

        for (int i = 1971; i <= 2100; i++) {
            yearSum[i] = yearSum[i - 1] + (isLeapYear(i) ? 366 : 365);
        }

        for (int i = 1; i <= 12; i++) {
            monthSum[i] = monthSum[i - 1] + month[i];
        }

        String[] dates1 = date1.split("-");
        int year1 = Integer.parseInt(dates1[0]);
        int month1 = Integer.parseInt(dates1[1]);
        int day1 = Integer.parseInt(dates1[2]);

        String[] dates2 = date2.split("-");
        int year2 = Integer.parseInt(dates2[0]);
        int month2 = Integer.parseInt(dates2[1]);
        int day2 = Integer.parseInt(dates2[2]);

        int sum1 = yearSum[year1 - 1] + monthSum[month1 - 1] + day1;
        if (isLeapYear(year1) && month1 > 2) {
            sum1++;
        }

        int sum2 = yearSum[year2 - 1] + monthSum[month2 - 1] + day2;
        if (isLeapYear(year2) && month2 > 2) {
            sum2++;
        }

        return Math.abs(sum1 - sum2);
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 100 == 0 && year % 400 == 0);
    }
}
```
# [LeetCode_1893_检查是否区域内所有整数都被覆盖](https://leetcode-cn.com/problems/check-if-all-the-integers-in-a-range-are-covered/)
## 解法
### 思路
- 遍历ranges，过滤掉所有不在left和right区间内的数组
- 然后将第一个可能的数组初始化为可能窗口
- 在如下的三种情况下更新窗口
  - 左边界比窗口左边界小，同时右边界比窗口右边界大，更新窗口
  - 左边界在窗口范围内，同时注意窗口的右边界判断时要+1，因为如果窗口是[2,4]，当前数组是[5,6]，那么同样可以组成一个[2,6]的窗口。那么左边界在窗口内，同时右边界比窗口右边界大，则更新窗口右边界
  - 同理右边界在窗口范围内，窗口的左边界判断时要-1，同时左边界比窗口左边界小，那么就更新窗口左边界
- 最后判断窗口是否能包含left和right组成的区间
### 代码
```java
class Solution {
    public boolean isCovered(int[][] ranges, int left, int right) {
        int l = Integer.MAX_VALUE, r = Integer.MIN_VALUE;
        boolean init = false;
        for (int[] range : ranges) {
            if (range[1] < left || range[0] > right) {
                continue;
            }
            
            if (!init) {
                l = range[0];
                r = range[1];
                init = true;
                continue;
            }
            
            if (range[0] <= l && range[1] >= r) {
                l = range[0];
                r = range[1];
                continue;
            }
            
            if (range[0] >= l && range[0] <= r + 1 && range[1] > r) {
                r = range[1];
                continue;
            }

            if (range[1] <= r && range[1] >= l - 1 && range[0] < l) {
                l = range[0];
            }
        }

        return l <= left && r >= right;
    }
}
```
# [LeetCode_1385_两个数组间的距离值](https://leetcode-cn.com/problems/find-the-distance-value-between-two-arrays/)
## 解法
### 思路
2层循环硬算
### 代码
```java
class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int count = 0;
        for (int num1 : arr1) {
            boolean miss = false;
            int left = num1 - d, right = num1 + d;
            for (int num2 : arr2) {
                if (num2 >= left && num2 <= right) {
                    miss = true;
                    break;
                }
            }

            count += miss ? 0 : 1;
        }

        return count;
    }
}
```
# [LeetCode_1389_按既定顺序创建目标数组](https://leetcode-cn.com/problems/create-target-array-in-the-given-order/)
## 解法
### 思路
- 初始化链表
- 将元素插入
- 转成数组返回
### 代码
```java
class Solution {
    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(index[i], nums[i]);
        }
        
        int[] ans = new int[list.size()];
        int i = 0;
        for (int num : list) {
            ans[i++]= num;
        }
        
        return ans;
    }
}
```
# [LeetCode_1736_换隐藏数字得到的最晚时间](https://leetcode-cn.com/problems/latest-time-by-replacing-hidden-digits/)
## 解法
### 思路
- 小时位的两个数需要关联判断
    - 如果第一位是？
      - 第二位也是？，就是23
      - 第二位大于等于4，就是1
      - 第二位小于4，就是2
    - 第二位是？
      - 第一位是1，就是9
      - 第一位是2，就是3
- 分钟位的数，只要设置最大值5和9就可以
### 代码
```java
class Solution {
  public String maximumTime(String time) {
    char[] cs = time.toCharArray();
    for (int i = 0; i < cs.length; i++) {
      if (cs[i] != '?') {
        continue;
      }

      if (i == 0) {
        if (cs[1] == '?') {
          cs[0] = '2';
          cs[1] = '3';
          i++;
        } else if (cs[1] >= '0' + 4) {
          cs[0] = '1';
          i++;
        } else {
          cs[0] = '2';
          i++;
        }
        continue;
      }

      if (i == 1) {
        if (cs[0] == '0' || cs[0] == '1') {
          cs[1] = '9';
        } else {
          cs[1] = '3';
        }
        continue;
      }

      if (i == 3) {
        cs[3] = '5';
      } else {
        cs[4] = '9';
      }
    }

    return new String(cs);
  }
}
```
# [LeetCode_1349_找出数组中的幸运数](https://leetcode-cn.com/problems/find-lucky-integer-in-an-array/)
## 解法
### 思路
- 遍历，map存储
- 遍历map，找到符合的最大值
### 代码
```java
class Solution {
    public int findLucky(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        int max = Integer.MIN_VALUE;
        for (int num : map.keySet()) {
            if (num == map.get(num)) {
                max = Math.max(max, num);
            }
        }
        
        return max == Integer.MIN_VALUE ? -1 : max;
    }
}
```
## 解法二
### 思路
- 初始化存储用的桶数组，用这个桶来计数
- 从桶数组的最后开始向前遍历，找到第一个符合要求的数就返回，否则返回-1
- 还要注意向前遍历桶的时候，值为0的元素位置要跳过
### 代码
```java
class Solution {
    public int findLucky(int[] arr) {
        int[] bucket = new int[501];
        for (int num : arr) {
            bucket[num]++;
        }
        
        for (int i = bucket.length - 1; i >= 1; i--) {
            if (i == bucket[i]) {
                return i;
            }
        }
        
        return -1;
    }
}
```
# [LeetCode_1399_统计最大组的数目](https://leetcode-cn.com/problems/count-largest-group/)
## 解法
### 思路
- 最大是四位数，所以初始化一个长度为37的数组arr
- 然后遍历元素，统计数位和
- 最后遍历arr，找到最大元素值并统计出现的个数
### 代码
```java
class Solution {
    public int countLargestGroup(int n) {
        int[] arr = new int[37];
        for (int i = 1; i <= n; i++) {
            arr[cal(i)]++;
        }
        
        int max = 0, count = 0;
        for (int num : arr) {
            if (num == 0) {
                continue;
            }
            
            if (num > max) {
                max = num;
                count = 1;
            } else if (num == max) {
                count++;
            }
        }
        
        return count;
    }


    private int cal(int n) {
        int sum = 0;
        while (n != 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}
```
# [LeetCode_1403_费递增序列的最小子序列](https://leetcode-cn.com/problems/minimum-subsequence-in-non-increasing-order/)
## 解法
### 思路
- 降序排序原数组
- 求出元素组的总和
- 遍历降序数组并累加，当子序列大于总和一半的时候返回该子数组
### 代码
```java
class Solution {
    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        int sum = Arrays.stream(nums).sum();
        int count = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            count += nums[i];
            ans.add(nums[i]);
            if (count > sum / 2) {
                break;
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
- 遍历数组，使用桶计数，并计算元素总和
- 遍历桶，跳过元素值为0的坐标
- 累加元素值直到超过总数的一半，同时过程中将遍历到的元素放入list中
- 超过总数一半后就终止遍历，返回list
### 代码
```java
class Solution {
    public List<Integer> minSubsequence(int[] nums) {
        int[] arr = new int[101];
        int sum = 0;
        for (int num : nums) {
            sum += num;
            arr[num]++;
        }
        
        int count = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (arr[i] > 0 && count * 2 <= sum) {
                arr[i]--;
                count += i;
                ans.add(i);
            }
        }
        
        return ans;
    }
}
```