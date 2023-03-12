# [LeetCode_880_索引处的解码字符串]()
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1653_使字符串平衡的最少删除次数](https://leetcode.cn/problems/minimum-deletions-to-make-string-balanced/)
## 解法
### 思路
- 题目要求所有a的坐标都要比所有b的坐标小
- 如果将数组切分成2部分，将分割线的左侧的b全部删除，将分割线右侧的a全部删除，就能达到题目要求的平衡状态
- 可以预先通过统计b的前缀和统计a的后缀数组，来优化遍历数组时候的重复计算
### 代码
```java
class Solution {
    public int minimumDeletions(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length, preB = 0, sufA = 0;
        int[] preBs = new int[n], sufAs = new int[n];
        for (int i = 0; i < n; i++) {
            preBs[i] = preB + (cs[i] == 'b' ? 1 : 0);
            preB = preBs[i];
        }
        
        for (int i = n - 1; i >= 0; i--) {
            sufAs[i] = sufA + (cs[i] == 'a' ? 1 : 0);
            sufA = sufAs[i];
        }
        
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < cs.length; i++) {
            ans = Math.min(ans, preBs[i] + sufAs[i] - 1);
        }
        return ans;
    }
}
```
## 解法二
### 思路
动态规划：
- dp[i]:以i为字符串结尾，最少需要删除的字符个数
- countB:到目前为止出现的b的个数
- 状态转移方程：
  - cs[i] == 'a'：dp[i] = max(dp[i - 1] + 1, b)
    - dp[i - 1] + 1表示将当前a删除，从而使s[0, i]与s[0, i-1]保持一致，成为平衡的
    - b表示，表示不保留所有的b，将b全部删除
  - cs[i] == 'b'：dp[i] = dp[i - 1]且b++
- 初始状态:
  - dp[0] = 0，无论是什么字符串，因为只有1个，所有无需任何操作就是平衡的
  - b = cs[i] == 'b' ? 1 : 0
- 结果：dp[n - 1]
### 代码
```java
class Solution {
    public int minimumDeletions(String s) {
        int n = s.length(), b = s.charAt(0) == 'b' ? 1 : 0;
        char[] cs = s.toCharArray();
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            if (cs[i] == 'a') {
                dp[i] = Math.min(dp[i - 1] + 1, b);
            } else {
                dp[i] = dp[i - 1];
                b++;
            }
        }
        
        return dp[n - 1];
    }
}
```
## 解法三
### 思路
基于解法二，发现所有状态都只依赖前一个元素，所以讲dp数组压缩成变量dp
### 代码
```java
class Solution {
    public int minimumDeletions(String s) {
int dp = 0, b = 0;
        for (char c : s.toCharArray()) {
            if (c == 'a') {
                dp = Math.min(dp + 1, b);
            } else {
                b++;
            }
        }
        
        return dp;
    }
}
```
# [LeetCode_1092_花括号展开II](https://leetcode.cn/problems/brace-expansion-ii/)
## 解法
### 思路
- 表达式可以区分成3种情况：
  - 没有括号的单独内容，这种内容可以直接放入结果中
  - 有括号，括号中包含逗号的内容，这些内容相当于多个单独元素形成的列表
  - 括号与其他表达式之间没有间隔，这个时候，括号内用逗号分割的元素需要循环地与其他表达式的内容拼接处理
- 通过观察发现这个问题一定是一个重复子问题，可以通过递归，不断简化表达式获得可能的元素
- 而且发现，只要表达式中包含了括号，那么这个表达式就必须继续被处理，最终能够被记录的一定是没有括号的表达式
- 递归的过程中
  - 可以去找表达式中的右括号，如果存在，就说明当前表达式还需要处理，否则就可以放入一个去重的集合中
  - 如果还有括号，那么就把这个表达式区分成3个部分
    - 括号左侧，没有括号的字母部分，可能包含逗号
    - 括号内部，内部可能包含括号和逗号
    - 括号右侧，可能包含逗号和括号和字母等内容
  - 将括号内部通过逗号拆分，然后循环内部元素与左右内容进行拼接，完成情况3的处理，然后继续递归
### 代码
```java
class Solution {
    private TreeSet<String> set = new TreeSet<>();

    public List<String> braceExpansionII(String expression) {
        rescue(expression);
        return new ArrayList<>(set);
    }

    private void rescue(String expression) {
        int ri = expression.indexOf("}");
        if (ri == -1) {
            set.add(expression);
            return;
        }

        int li = ri - 1;
        for (int i = li; i >= 0; i--) {
            if (expression.charAt(i) == '{') {
                li = i;
                break;
            }
        }

        String left = expression.substring(0, li),
               mid = expression.substring(li + 1, ri),
               right = expression.substring(ri + 1);
        String[] midFactors = mid.split(",");

        for (String midFactor : midFactors) {
            rescue(left + midFactor + right);
        }
    }
}
```
## 解法二
### 思路
栈
- 类似逆波兰表达式
### 代码
```java
class Solution {

    public List<String> braceExpansionII(String expression) {
        Stack<Character> opStack = new Stack<>();
        Stack<TreeSet<String>> stack = new Stack<>();

        char[] cs = expression.toCharArray();
        for (int i = 0; i < cs.length;) {
            String token = getNextToken(expression, i);

            if (Objects.equals(token, "{")) {
                if (i == 0) {
                    opStack.push('{');
                } else {
                    if (cs[i - 1] != ',' && cs[i - 1] != '{') {
                        opStack.push('*');
                        opStack.push('{');
                    } else {
                        opStack.push('{');
                    }
                }
            } else if (Objects.equals(token, ",")) {
                while (!opStack.isEmpty() && opStack.peek() == '*') {
                    opStack.pop();
                    TreeSet<String> b = stack.pop(), a = stack.pop(), c = new TreeSet<>();
                    for (String as : a) {
                        for (String bs : b) {
                            c.add(as + bs);
                        }
                    }
                    stack.push(c);
                }
                opStack.push('+');
            } else if (Objects.equals(token, "}")) {
                while (!opStack.isEmpty()) {
                    char op = opStack.pop();
                    if (op == '{') {
                        break;
                    } else if (op == '+') {
                        TreeSet<String> b = stack.pop(), a = stack.pop();
                        a.addAll(b);
                        stack.push(a);
                    } else {
                        TreeSet<String> b = stack.pop(), a = stack.pop(), c = new TreeSet<>();
                        for (String as : a) {
                            for (String bs : b) {
                                c.add(as + bs);
                            }
                        }
                        stack.push(c);
                     }
                }
            } else {
                if (i != 0 && cs[i - 1] == '}') {
                    opStack.push('*');
                }

                stack.push(new TreeSet<>());
                stack.peek().add(token);
            }

            i += token.length();
        }

        while (!opStack.isEmpty()) {
            char op = opStack.pop();
            if (op == '+') {
                TreeSet<String> b = stack.pop(), a = stack.pop();
                a.addAll(b);
                stack.push(a);
            } else if (op == '*') {
                TreeSet<String> b = stack.pop(), a = stack.pop(), c = new TreeSet<>();
                for (String as : a) {
                    for (String bs : b) {
                        c.add(as + bs);
                    }
                }
                stack.push(c);
            }
        }

        if (stack.isEmpty()) {
            return new ArrayList<>();
        }

        return new ArrayList<>(stack.pop());
    }

    private String getNextToken(String expression, int index) {
        StringBuilder sb = new StringBuilder();
        char[] cs = expression.toCharArray();
        for (int i = index; i < cs.length; i++) {
            if (cs[i] == '{') {
                if (sb.length() == 0) {
                    sb.append(cs[i]);
                }
                break;
            } else if (cs[i] == '}') {
                if (sb.length() == 0) {
                    sb.append(cs[i]);
                }
                break;
            } else if (cs[i] == ',') {
                if (sb.length() == 0) {
                    sb.append(cs[i]);
                }
                break;
            } else {
                sb.append(cs[i]);
            }
        }

        return sb.toString();
    }
}
```
# [LeetCode_2578_最小和分割](https://leetcode.cn/problems/split-with-minimum-sum/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int splitNum(int num) {
        int[] arr = new int[10];
        while (num > 0) {
            arr[num % 10]++;
            num /= 10;
        }

        int a = 0, b = 0;
        boolean flag = true;
        for (int i = 0; i < arr.length; i++) {
            while (arr[i]-- > 0) {
                if (flag) {
                    a = a * 10 + i;
                } else {
                    b = b * 10 + i;
                }
                
                flag = !flag;
            }
        }

        return a + b;
    }
}
```
# [LeetCode_2582_递枕头](https://leetcode.cn/problems/pass-the-pillow/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int passThePillow(int n, int time) {
        int cur = 1;
        boolean flag = true;
        while (time-- > 0) {
            if (flag) {
                cur++;
                if (cur == n) {
                    flag = false;
                }
            } else {
                cur--;
                
                if (cur == 1) {
                    flag = true;
                }
            }
        }
        
        return cur;
    }
}
```
# [LeetCode_880_索引处的解码字符串](https://leetcode.cn/problems/decoded-string-at-index/)
## 解法
### 思路
- 通过表达式计算出总长度cnt，注意int值会越界，需要使用long
- 可以将字符串拆分为2部分：[str][num]
- 这样的话从表达式的尾部开始遍历，如果字符是数字，cnt/num，模拟出str的长度，然后再处理k，k %= cnt
  - 比如，cnt是9，num是3，k是7，当cnt/num后得到了3，这样cnt/num得到的值就比k小了，但其实没关系，因为9是被平均分成3个部分的，所以7其实通过与cnt（3）取模得到1以后，得到就是7在原来没有/num的时候得到的字符串是一样的
- 如果字符是字母，那么看看k % cnt == 0 是否成立，如果成立，那么当前字符就是目标字符，否则就cnt--，并继续遍历
### 代码
```java
class Solution {
  public String decodeAtIndex(String s, int k) {
    long cnt = 0;
    for (int i = 0; i < s.length(); i++) {
      if (Character.isDigit(s.charAt(i))) {
        cnt = cnt * (s.charAt(i) - '0');
      } else {
        cnt++;
      }
    }

    for (int i = s.length() - 1; i >= 0; i--) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        cnt /= (c - '0');
        k %= cnt;
      } else {
        if (k % cnt == 0) {
          return s.charAt(i) + "";
        }

        cnt--;
      }
    }

    return "";
  }
}
```
# [LeetCode_898_子数组按位或操作](https://leetcode.cn/problems/bitwise-ors-of-subarrays/)
## 失败解法
### 原因
超时
### 思路
2层for循环，使用mask做减枝
### 代码
```java
class Solution {
  public int subarrayBitwiseORs(int[] arr) {
    int max = Integer.MIN_VALUE;
    for (int num : arr) {
      max = Math.max(num, max);
    }

    int mask = 1;
    while (max >= mask) {
      mask <<= 1;
    }

    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < arr.length; i++) {
      int cur = arr[i];
      set.add(cur);

      for (int j = i + 1; j < arr.length && cur != mask; j++) {
        cur |= arr[j];
        set.add(cur);
      }
    }

    return set.size();
  }
}
```
## 解法
### 思路
- 2层循环
- 外层确定坐标i，
- 内层生成所有以外层i为结尾的子数组
- 所有子数组都可以基于i-1为结尾的子数组+当前元素，就可以得到
- 所以可以通过set集合来存储上一批子数组，从而减少重复
- 因为每一批次的子数组生成的元素都会变化，所以需要在每次外层循环的时候更新这个set集合
### 代码
```java
class Solution {
  public int subarrayBitwiseORs(int[] arr) {
    Set<Integer> ans = new HashSet<>(), cur = new HashSet<>();
    for (int i : arr) {
      Set<Integer> tmp = new HashSet<>();
      cur.add(i);
      for (Integer num : cur) {
        tmp.add(num | i);
      }
      cur = tmp;
      ans.addAll(cur);
    }

    return ans.size();
  }
}
```
# [LeetCode_2289_使数组按非递减顺序排序](https://leetcode.cn/problems/steps-to-make-array-non-decreasing/)
## 失败解法
### 原因
超时
### 思路
模拟
### 代码
```java
class Solution {
    public int totalSteps(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        
        boolean flag = true;
        int count = 0;
        while (flag) {
            flag = false;
            List<Integer> cur = new ArrayList<>();
            cur.add(list.get(0));
            
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i) >= list.get(i - 1)) {
                    cur.add(list.get(i));
                } else {
                    flag = true;
                }
            }
            
            if (flag) {
                count++;
            }
            
            list = cur;
        }
        
        return count;
    }
}
```
## 解法
### 思路
单调栈

### 代码
```java
class Solution {
    public int totalSteps(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int n = nums.length, ans = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int cur = 0;
            while (!stack.isEmpty() && nums[i] >= nums[stack.peek()]) {
                int index = stack.pop();
                cur = Math.max(dp[index], cur);
            }

            cur = stack.isEmpty() ? 0 : cur + 1;
            dp[i] = Math.max(dp[i], cur);
            ans = Math.max(ans, dp[i]);
            stack.push(i);
        }
        
        return ans;
    }
}
```
# [LeetCode_1590_使数组和能被P整除](https://leetcode.cn/problems/make-sum-divisible-by-p/)
## 解法
### 思路
- 求数组总和，与p取余得到余数
- 生成前缀和数组
- 在前缀和数组中找到sum[r] - sum[l] === sum (mod p)
- 转换如上公式：sum[r] - x === sum[l] (mod p)
- 处理好负数：((sum[r] - x) mod p + p) mod p == sum[l] mod p
- 再转换公式得：(sum[r] mod p - x mod p + p) mod p == sum[l] mod p
- 通过如上公式可以得出代码逻辑：
  - 遍历前缀和数组，并使用map记录sum[l] mod p的值和坐标
  - 遍历前缀和数组，判断map中是否包含(sum[r] mod p - x mod p + p) mod p
### 代码
```java
class Solution {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        int[] sums = new int[n + 1];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            sums[i] = (sums[i - 1] + nums[i - 1]) % p;
        }
        
        if (sums[n] == 0) {
            return 0;
        }

        int sum = sums[n];

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            map.put(sums[i], i);
            int val = (sums[i] - sum + p) % p;
            if (map.containsKey(val)) {
                ans = Math.min(i - map.get(val), ans);
            }
        }

        return ans == Integer.MAX_VALUE || ans == n ? -1 : ans;
    }
}
```
# [interview_1705_字母与数字](https://leetcode.cn/problems/find-longest-subarray-lcci/)
## 解法
### 思路
前缀和+哈希表
### 代码
```java
class Solution {
    public String[] findLongestSubarray(String[] array) {
        int n = array.length;
        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + (Character.isDigit(array[i - 1].charAt(0)) ? 1 : -1);
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            map.computeIfAbsent(sums[i], x -> new ArrayList<>()).add(i);
        }

        int len = 0, start = -1, end = -1;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> list = entry.getValue();
            int left = list.get(0), right = list.get(list.size() - 1), dis = right - left;
            if (dis > len) {
                len = dis;
                start = left;
                end = right;
            } else if (dis == len && list.get(0) < start) {
                start = left;
                end = right;
            }
        }

        return len == 0 ? new String[0] : Arrays.copyOfRange(array, start, end);
    }
}
```