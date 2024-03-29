# [LeetCode_273_整数转换英文表示](https://leetcode-cn.com/problems/integer-to-english-words/)
## 解法
### 思路
- 将数字按照每3位一分来拆分，依次为billion，million和thousand
- 每三位中再按照不同位数的值进行映射
- 单词与单词之间需要拼接空格
- 在准备阶段需要先将数字与单词的映射管理梳理出来
### 代码
```java
class Solution {
    private Map<Integer, String> map = new HashMap<>();
    private Map<Integer, String> map2 = new HashMap<>();

    {
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(9, "Nine");
        map.put(10, "Ten");
        map.put(11, "Eleven");
        map.put(12, "Twelve");
        map.put(13, "Thirteen");
        map.put(14, "Fourteen");
        map.put(15, "Fifteen");
        map.put(16, "Sixteen");
        map.put(17, "Seventeen");
        map.put(18, "Eighteen");
        map.put(19, "Nineteen");
        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50, "Fifty");
        map.put(60, "Sixty");
        map.put(70, "Seventy");
        map.put(80, "Eighty");
        map.put(90, "Ninety");
        map2.put(0, "");
        map2.put(1, "Thousand");
        map2.put(2, "Million");
        map2.put(3, "Billion");
    }

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        int index = 0;
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            int cur = num % 1000;
            if (cur != 0) {
                sb.insert(0, " ");
                sb.insert(0, map2.get(index));

                int n = cur % 100;
                if (n == 0) {

                } else if (map.containsKey(n)) {
                    sb.insert(0, " ");
                    sb.insert(0, map.get(n));
                } else {
                    sb.insert(0, " ");
                    sb.insert(0, map.get(n % 10));
                    sb.insert(0, " ");
                    sb.insert(0, map.get(n / 10 * 10));
                }

                if (cur / 100 * 100 != 0) {
                    sb.insert(0, " ");
                    sb.insert(0, map.get(cur / 100) + " Hundred");
                }
            }

            num /= 1000;
            index++;
        }
        
        return sb.toString().trim();
    }
}
```
# [LeetCode_29_两数相除](https://leetcode-cn.com/problems/divide-two-integers/)
## 解法
### 思路
- 因为不用考虑小数部分，所以使用减法来实现
- 特殊情况：
  - 被除数为0，直接返回0
  - 被除数是int最小值且除数是-1，那么商就是int最大值
- 判断正负号
- 被除数通过除数累减的方式，会超时，所以加速累减的过程
- 可以通过左移的方式，将除数*2，然后和被除数比较，只要不大于被除数就一直左移，超过了就累减，然后这个过程不断循环
- 需要注意int最大最小值，所以需要将除数和被除数先转换成long值进行操作，除了int最小值除以-1的特殊情况
### 代码
```java
class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }

        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean negative = dividend < 0 ^ divisor < 0;
        long d = dividend, r = divisor;
        d = Math.abs(d);
        r = Math.abs(r);

        int result = 0;
        while (d >= r) {
            long num = r, count = 1;

            while (d - num >= num) {
                num <<= 1;
                count <<= 1;
            }

            d -= num;
            result += count;
        }

        return negative ? -result : result;
    }
}
```
# [LeetCode_412_FizzBuzz](https://leetcode-cn.com/problems/fizz-buzz/)
## 解法
### 思路
遍历，计算，存储
### 代码
```java
class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            if (i % 3 == 0) {
                sb.append("Fizz");
            }

            if (i % 5 == 0) {
                sb.append("Buzz");
            }

            list.add(sb.length() == 0 ? "" + i : sb.toString());
        }

        return list; 
    }
}
```
## 解法二
### 思路
不new新的StringBuilder对象，直接add字符串常量
### 代码
```java
class Solution {
  public List<String> fizzBuzz(int n) {
    List<String> ans = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      ans.add(get(i));
    }
    return ans;
  }

  private String get(int i) {
    if (i % 3 == 0 && i % 5 == 0) {
      return "FizzBuzz";
    } else if (i % 3 == 0) {
      return "Fizz";
    } else if (i % 5 == 0) {
      return "Buzz";
    } else {
      return "" + i;
    }
  }
}
```
# [LeetCode_1796_字符串中第二大的数字](https://leetcode-cn.com/problems/second-largest-digit-in-a-string/)
## 解法
### 思路
循环模拟
### 代码
```java
class Solution {
  public int secondHighest(String s) {
    char[] cs = s.toCharArray();
    Integer one = null, two = null;
    for (char c : cs) {
      if (!Character.isDigit(c)) {
        continue;
      }

      int num = c - '0';

      if (one == null) {
        one = num;
        continue;
      }

      if (num > one) {
        two = one;
        one = num;
        continue;
      }

      if (num < one && (two == null || num > two)) {
        two = num;
      }
    }

    return two == null ? -1 : two;
  }
}
```
## 解法二
### 思路
- 遍历字符串，使用布尔数组记录遍历到的数字，下标对应值
- 遍历布尔数组，找到第二大的值返回，否则返回-1
### 代码
```java
class Solution {
    public int secondHighest(String s) {
        boolean[] bucket = new boolean[10];
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                bucket[s.charAt(i) - '0'] = true;
            }
        }
        
        int count = 0;
        for (int i = 9; i >= 0; i--) {
            if (bucket[i]) {
                count++;
            }
            
            if (count == 2) {
                return i;
            }
        }
        
        return -1;
    }
}
```
# [LeetCode_1800_最大升序子数组和](https://leetcode-cn.com/problems/maximum-ascending-subarray-sum/)
## 解法
### 思路
- 因为都是正数，所以只要正向遍历即可
- 初始化三个变量
  - pre：用于记录前值元素的值，判断是否升序，初始为-1
  - sum：用于暂存累加值
  - max：用于暂存最大值
- 遍历数组，用当前元素和pre比较，判断是否升序
  - 是，就累加sum值，并更新pre
  - 否，就比较并更新max，并更新sum和pre
- 遍历结束还需要将max和sum比较，因为更新max的动作是在非升序的时候触发，如果最终升序子数组持续到数组结尾，那么就需要在遍历结束的时候再更新一次
- 最终返回max值
### 代码
```java
class Solution {
    public int maxAscendingSum(int[] nums) {
        int max = 0, pre = -1, sum = 0;
        for (int num : nums) {
            if (num > pre) {
                sum += num;
            } else {
                max = Math.max(max, sum);
                sum = num;
            }
            
            pre = num;
        }
        
        return Math.max(max, sum);
    }
}
```
# [LeetCode_1805_字符串中不同整数的数量](https://leetcode-cn.com/problems/number-of-different-integers-in-a-string/)
## 解法
### 思路
- 遍历字符串，跳过字符
- 然后在遇到数字的时候，如果第一次遇到的是0，也跳过，直到遇到第一个不是0的字符
- 将这串可能连续的数字拼接计算，得到对应的值
- 将值放入set中去重
- 最后返回set的长度
- 补充：
  - 还需要考虑数字为0的情况
  - 需要考虑数字过大越界的情况，索性用字符串存储去重
### 代码
```java
class Solution {
    public int numDifferentIntegers(String word) {
        char[] cs = word.toCharArray();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (!Character.isDigit(c)) {
                continue;
            }

            if (c - '0' == 0) {
                if (i == cs.length - 1 || !Character.isDigit(cs[i + 1])) {
                    set.add("0");
                }
                continue;
            }

            StringBuilder sb = new StringBuilder();
            while (i < cs.length && Character.isDigit(cs[i])) {
                sb.insert(0, cs[i]);
                i++;
            }

            set.add(sb.toString());
        }

        return set.size();
    }
}
```
# [LeetCode_offerII69_山峰数组的顶部](https://leetcode-cn.com/problems/B1IidL/submissions/)
## 解法
### 思路
- 遍历并判断是否同时大于左右元素的坐标元素，有的话就返回
- 如果完成遍历，说明峰值在头或尾，返回头尾较大的那个元素坐标
### 代码
```java
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                return i;
            }
        }
        
        return arr[0] > arr[arr.length - 1] ? 0 : arr.length - 1;
    }
}
```
# [LeetCode_1812_判断国际象棋棋盘中一个格子的颜色](https://leetcode-cn.com/problems/determine-color-of-a-chessboard-square/)
## 解法
### 思路
- 将字符串代表横和纵的元素都转换成棋盘的坐标元素
- 根据观察可以看出，撇或捺对应的格子的颜色都是一样的，而撇和捺的坐标通过横竖坐标可以计算，所以计算出比如撇的第几个，就能知道是白还是黑
### 代码
```java
class Solution {
    public boolean squareIsWhite(String coordinates) {
        int row = coordinates.charAt(0) - 'a',
            col = coordinates.charAt(1) - '0' - 1;

        return (row + col) % 2 != 0; 
    }
}
```
# [LeetCode_38_外观数列](https://leetcode-cn.com/problems/count-and-say/)
## 解法
### 思路
模拟
- 2层循环
  - 外层根据n循环执行次数
  - 内层生成当前次的描述字符串
### 代码
```java
class Solution {
    public String countAndSay(int n) {
        StringBuilder ans = new StringBuilder("1");
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            int len = ans.length(), count = 0;
            char start = ans.charAt(0);
            for (int j = 0; j < len; j++) {
                if (ans.charAt(j) == start) {
                    count++;
                } else {
                    sb.append(count).append(start);
                    start = ans.charAt(j);
                    count = 1;
                }
            }

            sb.append(count).append(start);
            ans = sb;
        }

        return ans.toString();
    }
}
```
## 解法二
### 思路
因为只有30种情况，基于解法一打表缓存后，直接获取结果
### 代码
```java
class Solution {
private static String[] bucket = new String[30];
    static {
        for (int k = 1; k <= 30; k++) {
            StringBuilder ans = new StringBuilder("1");
            for (int i = 2; i <= k; i++) {
                StringBuilder sb = new StringBuilder();
                int len = ans.length(), count = 0;
                char start = ans.charAt(0);
                for (int j = 0; j < len; j++) {
                    if (ans.charAt(j) == start) {
                        count++;
                    } else {
                        sb.append(count).append(start);
                        start = ans.charAt(j);
                        count = 1;
                    }
                }

                sb.append(count).append(start);
                ans = sb;
            }
            
            bucket[k - 1] = ans.toString();
        }
    }

    public String countAndSay(int n) {
        return bucket[n - 1];
    }
}
```
# [LeetCode_230_二叉树中第K小的元素](https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/)
## 解法
### 思路
二叉搜索树的中序遍历得到的列表是升序的，所以第k小就是列表中第k个元素
### 代码
```java
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list.get(k - 1);
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        dfs(node.left, list);
        list.add(node.val);
        dfs(node.right, list);
    }
}
```
## 解法二
### 思路
解法一需要把整棵树都搜索出来，在解法一的基础上只搜索要搜索的k个节点
### 代码
```java
class Solution {
    private int count = 0;
    
    public int kthSmallest(TreeNode root, int k) {
        return dfs(root, k);
    }
    
    private Integer dfs(TreeNode node, int k) {
        if (node == null) {
            return null;
        }
        
        Integer val = dfs(node.left, k);
        if (val != null) {
            return val;
        }
        
        if (++count == k) {
            return node.val;
        }
        
        return dfs(node.right, k);
    }
}
```