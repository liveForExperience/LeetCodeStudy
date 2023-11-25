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
# [LeetCode_]()
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