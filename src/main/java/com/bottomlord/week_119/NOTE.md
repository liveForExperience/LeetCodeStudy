# [LeetCode_476_数字的补数](https://leetcode-cn.com/problems/number-complement/)
## 解法
### 思路
- 求出num中为1的最高位
- 然后左移一位-1，获得掩码
- 用掩码和原数字进行抑或求出补数
### 代码
```java
class Solution {
    public int findComplement(int num) {
        int highBit = 0;
        for (int i = 1; i <= 30; i++) {
            if (num >= 1 << i) {
                highBit = i;
            } else {
                break;
            }
        }

        int mask = highBit == 30 ? 0x7fffffff : (1 << (highBit + 1)) - 1;
        return mask ^ num;
    }
}
```
# [LeetCode_211_添加与搜索单词-数据结构设计](https://leetcode-cn.com/problems/design-add-and-search-words-data-structure/)
## 解法
### 思路
字典树
### 代码
```java
class WordDictionary {
    private DictNode root;
    public WordDictionary() {
        this.root = new DictNode();
    }

    public void addWord(String word) {
        char[] cs = word.toCharArray();
        DictNode node = root;
        for (char c : cs) {
            DictNode child = node.children[c - 'a'];
            if (child == null) {
                child = new DictNode(c);
                node.children[c - 'a'] = child;
            }
            node = child;
        }

        node.isLeaf = true;
    }

    public boolean search(String word) {
        char c = word.charAt(0);
        if (c == '.') {
            for (DictNode child : root.children) {
                boolean result = doSearch(child, word, 1);
                if (result) {
                    return true;
                }
            }
            return false;
        }
        
        return doSearch(root.children[word.charAt(0) - 'a'], word, 1);
    }

    private boolean doSearch(DictNode node, String word, int index) {
        if (node == null) {
            return false;
        }
        
        if (index == word.length()) {
                return node.isLeaf;
        }
        
        if (word.charAt(index) == '.') {
            DictNode[] children = node.children;
            for (DictNode child : children) {
                boolean result = doSearch(child, word, index + 1);
                if (result) {
                    return true;
                }
            }
            
            return false;
        }
        
        return doSearch(node.children[word.charAt(index) - 'a'], word, index + 1);
    }

    private  class DictNode {
        private char c;
        private boolean isLeaf;
        private DictNode[] children;

        public DictNode() {
            this.children = new DictNode[26];
        }

        public DictNode(char c) {
            this.c = c;
            this.children = new DictNode[26];
        }
    }
}
```
# [LeetCode_453_最小操作次数使数组元素相等](https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements/)
## 解法
### 思路
- 题目可以理解成使n-1个元素加1，反过来也可以理解成使1个元素-1
- 如果是每次都-1的话，那么只需要找出数组中的最小值，然后计算每个元素与最小值的差，再求和就可以了
### 代码
```java
class Solution {
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(num, min);
        }

        int sum = 0;
        for (int num : nums) {
            sum += num - min;
        }

        return sum;
    }
}
```
# [LeetCode_66_加一](https://leetcode-cn.com/problems/plus-one/)
## 解法
### 思路
从数组尾部开始遍历，并进行计算，考虑是否需要进位，如果不需要就结束遍历
### 代码
```java
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length, carry = 1;
        for (int i = n - 1; i >= 0; i--) {
            int num = digits[i] + carry;
            digits[i] = num % 10;
            if (num / 10 == 0) {
                carry = 0;
                break;
            }
        }

        if (carry == 1) {
            int[] arr = new int[n + 1];
            arr[0] = 1;
            System.arraycopy(digits, 0, arr, 1, n);
            return arr;
        }

        return digits;
    }
}
```
# [LeetCode_229_求众数II](https://leetcode-cn.com/problems/majority-element-ii/)
## 解法
### 思路
遍历+hash表计数
### 代码
```java
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int maj = nums.length / 3;
        Set<Integer> ans = new HashSet<>();
        Map<Integer, Integer> mapping = new HashMap<>();
        for (int num : nums) {
            mapping.put(num, mapping.getOrDefault(num, 0) + 1);
            if (mapping.get(num) > maj) {
                ans.add(num);
            }
        }

        return new ArrayList<>(ans);
    }
}
```
## 解法二
### 思路
- 众数需要的是超过3分之1的个数，所以一个数组中最多有2个元素
- 初始化4个变量，分为2组，每组的2个变量分别记录值和个数
- 遍历过程中
  - 如果2组中任意个数为零，就初始化对应的候选值变量
  - 如果个数不为零，且值与候选值相等，就累加相应的计数值
  - 否则就依次将计数值减1
  - 这么做的原因是，因为如果数组中存在众数，那么按照如上的算法，一定可以在遍历结束以后，成为最后剩下的值，因为其个数超过了3分之1
- 遍历结束以后，再验证2个候选值，看其个数是否超过3分之1，这是为了剔除那种只有一个众数，另一个候选值是最后阶段个数较多，从而幸存下来的情况
### 代码
```java
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int candidate1 = 0, count1 = 0,
            candidate2 = 0, count2 = 0;
        
        for (int num : nums) {  

            if (num == candidate1) {
                count1++;
                continue;
            }
            
            if (num == candidate2) {
                count2++;
                continue;
            }

            if (count1 == 0) {
                candidate1 = num;
                count1++;
                continue;
            }
            
            if (count2 == 0) {
                candidate2 = num;
                count2++;
                continue;
            }
            
            count1--;
            count2--;
        }
        
        if (candidate1 == candidate2) {
            return Collections.singletonList(candidate1);
        }

        List<Integer> ans = new ArrayList<>();
        count1 = count2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            }
            
            if (num == candidate2) {
                count2++;
            }
        }
        
        if (count1 > nums.length / 3) {
            ans.add(candidate1);
        }
        
        if (count2 > nums.length / 3) {
            ans.add(candidate2);
        }
        
        return ans;
    }
}
```
# [LeetCode_492_构造矩形](https://leetcode-cn.com/problems/construct-the-rectangle/)
## 解法
### 思路
双指针
### 代码
```java
class Solution {
    public int[] constructRectangle(int area) {
        int head = 1, tail = area;
        int[] ans = new int[2];
        while (head <= tail) {
            int product = head * tail;

            if (product == area) {
                ans[1] = head;
                ans[0] = tail;
                head++;
                tail--;
            } else if (product > area) {
                tail--;
            } else {
                head++;
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路
- 因为是乘积，所以其实只要求出其中一个乘数就可以了
- 通过jdk提供的api直接求出平方根的下限整数，他要么是其中一个乘数，且一定是宽，要么就是过大的宽值
- 不断累减这个值，然后和area做取模的运算，遇到第一个可以整除的数，就找到了最大的宽，因为最大的宽只能是平方数的平方根
- 和解法一相比，解法一是从最小的可能开始判断，其实这样就额外做了一些判断，而当前算法就可以直接从可能的最大值开始判断，减少了判断的次数
### 代码
```java
class Solution {
  public int[] constructRectangle(int area) {
    int num = (int) Math.sqrt(area);

    while (area % num != 0) {
      num--;
    }

    return new int[]{area / num, num};
  }
}
```
# [LeetCode_638_大礼包](https://leetcode-cn.com/problems/shopping-offers/)
## 解法
### 思路
回溯：
- 先算出不使用大礼包的总金额
- 然后遍历大礼包集合，将不符合要求的礼包跳过
- 然后将need的个数减去礼包的中的个数，递归求使用当前大礼包后，剩下的need需要的最小值
- 递归结束返回后，和当前的最小值作比较，取较小的那个
- 然后回溯当前的变化，遍历到下一个礼包继续递归
### 代码
```java
class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int ans = 0;

        for (int i = 0; i < price.size(); i++) {
            ans += price.get(i) * needs.get(i);
        }

        for (int i = 0; i < special.size(); i++) {
            List<Integer> bag = special.get(i);

            boolean flag = false;
            for (int j = 0; j < bag.size() - 1; j++) {
                if (bag.get(j) > needs.get(j)) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                continue;
            }

            for (int j = 0; j < needs.size(); j++) {
                needs.set(j, needs.get(j) - bag.get(j));
            }
            
            ans = Math.min(ans, shoppingOffers(price, special, needs) + bag.get(bag.size() - 1));
            
            for (int j = 0; j < needs.size(); j++) {
                needs.set(j, needs.get(j) + bag.get(j));
            }
        }
        
        return ans;
    }
}
```