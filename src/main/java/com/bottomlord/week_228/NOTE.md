# [LeetCode_53_最大子数组和](https://leetcode.cn/problems/maximum-subarray)
## 解法
### 思路
动态规划
### 代码
```java
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length, ans = Integer.MIN_VALUE;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = nums[i - 1] + Math.max(0, dp[i - 1]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
```
# [LeetCode_1410_HTML实体解析器](https://leetcode.cn/problems/html-entity-parser)
## 解法
### 思路
- 思考过程：
  - 可以与现将`实体`与`特殊字符`的关系通过键值对存储起来
  - 有了这个映射关系，就能够通过遍历字符串来进行解析了
- 算法过程：
  - 初始化1个hash表用于存储映射关系
  - 遍历字符串，当发现当前字符为`&`时就进行匹配和替换
  - 遍历结束，返回字符串即可
### 代码
```java
class Solution {
  public String entityParser(String text) {
    Map<String, String> map = new HashMap<>(6);
    map.put("&quot;", "\"");
    map.put("&apos;", "'");
    map.put("&amp;", "&");
    map.put("&gt;", ">");
    map.put("&lt;", "<");
    map.put("&frasl;", "/");

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < text.length(); i++) {
      if (text.charAt(i) != '&') {
        sb.append(text.charAt(i));
        continue;
      }

      boolean flag = false;
      for (Map.Entry<String, String> entry : map.entrySet()) {
        int len = entry.getKey().length();
        if (i + len <= text.length() && Objects.equals(entry.getKey(), text.substring(i, i + len))) {
          sb.append(entry.getValue());
          i += len - 1;
          flag = true;
          break;
        }
      }

      if (!flag) {
        sb.append(text.charAt(i));
      }
    }

    return sb.toString();
  }
}
```
# [LeetCode_2824_统计和小于目标的下标对数目](https://leetcode.cn/problems/count-pairs-whose-sum-is-less-than-target)
## 解法
### 思路
- 思考过程：
  - 模拟即可
- 算法过程：
  - 2层循环数组，外层坐标`i`起始为0，内层坐标起始为`i + 1`
  - 内层判断内网层元素和是否小于目标`target`，如果符合就累加暂存值
  - 遍历结束返回暂存值
### 代码
```java
class Solution {
    public int countPairs(List<Integer> nums, int target) {
        int ans = 0;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                if (nums.get(i) + nums.get(j) < target) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
```
## 解法二
### 思路
- 思考过程：
  - 二分查找
- 算法过程：
  - 对列表排序
  - 2层循环
    - 外层从0开始遍历
    - 内层使用2分查找，找到最大的、与外层元素相加小于`target`的坐标
    - 内层确定坐标后累加外层坐标到内层坐标的距离`j - i + 1`
  - 遍历结束返回累加值
### 代码
```java
class Solution {
    public int countPairs(List<Integer> nums, int target) {
        Collections.sort(nums);
        int n = nums.size(), ans = 0;
        for (int i = 0; i < n; i++) {
            int head = i, tail = n - 1, cur = i;
            while (head <= tail) {
                int mid = head + (tail - head) / 2;
                int num = nums.get(mid);
                
                if (num + nums.get(i) >= target) {
                    tail = mid - 1;
                } else {
                    cur = mid;
                    head = mid + 1;
                }
            }
            
            ans += cur - i;
        }
        
        return ans;
    }
}
```
## 解法三
### 思路
- 思考过程：
  - 双指针
- 算法过程：
  - 对列表排序
  - 循环列表，初始化头尾指针
  - 判断当前头尾指针元素之和是否小于`target`
    - 小于：`ans`累加头尾坐标差
    - 大于等于：尾指针向前移动，继续判断
  - 循环结束，返回累加值
### 代码
```java
class Solution {
    public int countPairs(List<Integer> nums, int target) {
        Collections.sort(nums);
        int n = nums.size(), ans = 0;
        for (int head = 0, tail = n - 1; head < tail; head++) {
            while (head < tail && nums.get(head) + nums.get(tail) >= target) {
                tail--;
            }
            
            ans += tail - head;
        }
        
        return ans;
    }
}
```
# [LeetCode_1457_二叉树中的伪回文路径](https://leetcode.cn/problems/pseudo-palindromic-paths-in-a-binary-tree)
## 解法
### 思路
- 思考过程：
  - 伪回文串可以理解为只允许存在至多1个值出现的个数为奇数
  - 通过dfs回溯穷举所有路径，对路径上出现的元素个数依次判断
- 算法过程：
  - 初始化一个hash表用于存储路径上的val值
  - 通过dfs+回溯穷举所有路径
  - 在递归的每一层，先将节点值放入map，并累加1
  - 然后判断当前节点是否为叶子结点，如果是，就对map进行伪回文串的判断
    - 如果是，要返回的结果值就是1
    - 如果不是，要返回的结果值就是0
  - 如果不是叶子结点，就继续递归，并将左右子节点返回的结果相加，作为当前层的结果
  - 最后在当前层返回前，对map中当前层节点值的个数进行回溯，也即计数值累减，且如果计数值为0，则将键去除
  - 最后返回结果
### 代码
```java
class Solution {
  public int pseudoPalindromicPaths (TreeNode root) {
    return dfs(root, new HashMap<>());
  }

  private int dfs(TreeNode node, Map<Integer, Integer> map) {
    if (node == null) {
      return 0;
    }

    int val = node.val;
    map.put(val, map.getOrDefault(val, 0) + 1);

    int ans;
    if (node.left == null && node.right == null) {
      ans = isFakePalindrome(map) ? 1 : 0;
    } else {
      ans = dfs(node.left, map) + dfs(node.right, map);
    }

    removeVal(map, val);
    return ans;
  }

  private boolean isFakePalindrome(Map<Integer, Integer> map) {
    if (map == null || map.isEmpty()) {
      return true;
    }

    int odd = 0;
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      int num = entry.getValue();
      if (num % 2 != 0) {
        odd++;
      }

      if (odd > 1) {
        return false;
      }
    }

    return true;
  }

  private void removeVal(Map<Integer, Integer> map, int val) {
    map.put(val, map.get(val) - 1);
    if (map.get(val) == 0) {
      map.remove(val);
    }
  }
}
```
# [LeetCode_828_统计子串中的唯一字符](https://leetcode.cn/problems/count-unique-characters-of-all-substrings-of-a-given-string)
## 解法
### 思路
- 思考过程：
  - 题目要计算字符串`s`的子字符串`t`中唯一字符的出现个数，反过来思考就是：计算每个字符作为唯一字符出现在子字符串中的个数。
  - 也即可以解释为：唯一字符`t[j]`，它在`s`中出现的前一个坐标是`i`，后一个坐标是`k`，那么`t[j]`作为唯一字符所出现在子字符串中的个数是`len(j - i) * len(k - j)`
  - 所以可以先将每个字符在`s`中出现的坐标预存起来，再遍历这些字符出现的坐标列表，就能通过如上的公式计算出个数，这些个数累加即可
  - 为了方便处理计算，可以将`-1`作为默认的`i`，`len(s)`作为默认的`k`
- 算法过程：
  - 初始化hash表，`key`为字符，`value`为存储字符坐标列表
  - 遍历`s`字符串，将字符与坐标存储起来，第一次出现字符的时候，先将`-1`预存到列表的第一个位置，然后再存储字符的坐标
  - 预存结束后，遍历map集合，取出键值对的value，将`len(s)`先存入到列表中，然后遍历这个列表，通过公式计算每个坐标对应的字符作为唯一字符出现在子字符串中的个数，然后累加起来
  - 遍历结束返回累加结果即可
### 代码
```java
class Solution {
    public int uniqueLetterString(String s) {
        int len = s.length();
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, new ArrayList<>());
                map.get(c).add(-1);
            }            map.get(c).add(i);
        }

        int ans = 0;
        for (Map.Entry<Character, List<Integer>> entry : map.entrySet()) {
            List<Integer> list = entry.getValue();
            list.add(len);

            for (int i = 1; i < list.size() - 1; i++) {
                ans += (list.get(i) - list.get(i - 1)) * (list.get(i + 1) - list.get(i));
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
- 在解法一的基础上，使用2个数组来替换map
  - preArr：存储26个字符当前坐标的前一个出现的坐标
  - curArr：存储26个字符当前坐标
- 初始化2个数组的所有元素为-1
- 遍历`s`的时候，直接开始套用公式计算
  - 如果`curArr[i] == -1`，说明当前字符第一次出现，这时候直接将坐标存储在`curArr`中，不做其他处理
  - 如果`curArr[i] > -1`，说明已经出现过至少1个字符，那么加上当前字符，至少有2个字符，那么组成的2个区间就可以计算出出现个数了
- 在循环过程中，更新`curArr`和`preArr`的坐标值
- 第一次循环结束后，还需要将元素与右边界的这个区间加进去，所以需要将出现过的字符坐标，也即`curArr[i] > -1`的坐标套入公式中进行计算，并累加到结果中
- 第二次循环结束后，返回累加值即可
### 代码
```java
class Solution {
    public int uniqueLetterString(String s) {
        int len = s.length(), ans = 0;
        int[] preArr = new int[26], curArr = new int[26];
        Arrays.fill(preArr, -1);
        Arrays.fill(curArr, -1);

        for (int i = 0; i < len; i++) {
            int ci = s.charAt(i) - 'A';
            if (curArr[ci] > - 1) {
                ans += (i - curArr[ci]) * (curArr[ci] - preArr[ci]);
            }
            
            preArr[ci] = curArr[ci];
            curArr[ci] = i;
        }

        for (int i = 0; i < curArr.length; i++) {
            if (curArr[i] > -1) {
                ans += (len - curArr[i]) * (curArr[i] - preArr[i]);
            }
        }
        
        return ans;
    }
}
```