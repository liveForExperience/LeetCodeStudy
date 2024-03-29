# [LeetCode_1774_统计同构字符串的个数](https://leetcode.cn/problems/count-number-of-homogenous-substrings/)
## 解法
### 思路
- 将字符串切分成一个个同构字符子串
- 每一个字符子串计算从1开始到子串长度的同构字符串个数，然后累加起来
- 计算的方式是sum(n - i + 1)，n是字符串串长度，i是小于n大于0的子串长度
- 处理过程就是遍历字符串，累加时记得取模1000000007
### 代码
```java
class Solution {
    public int countHomogenous(String s) {
        int index = 0, n = s.length(), ans = 0, mod = 1000000007;
        while (index < n) {
            char c = s.charAt(index);

            int count = 0;
            while (index < n && c == s.charAt(index)) {
                index++;
                count++;
            }

            for (int i = 1; i <= count; i++) {
                ans += (count - i) + 1;
                ans %= mod;
            }
        }

        return ans;
    }
}
```
# [LeetCode_2027_转换字符串的最少操作次数](https://leetcode.cn/problems/minimum-moves-to-convert-string/)
## 解法
### 思路
- 遍历字符串，当遇到x的时候就修改当前及之后的2个字符为o
- 然后继续遍历
### 代码
```java
class Solution {
    public int minimumMoves(String s) {
        char[] cs = s.toCharArray();
        int count = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == 'X') {
                cs[i] = 'O';

                if (i + 1 < cs.length) {
                    cs[i + 1] = 'O';
                }

                if (i + 2 < cs.length) {
                    cs[i + 2] = 'O';
                }

                i += 2;
                count++;
            }
        }

        return count;
    }
}
```
# [LeetCode_1750_删除字符串两端相同字符后的最短长度](https://leetcode.cn/problems/minimum-length-of-string-after-deleting-similar-ends/)
## 解法
### 思路
双指针模拟
### 代码
```java
class Solution {
    public int minimumLength(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            char lc = s.charAt(left),
                 rc = s.charAt(right);

            if (lc != rc) {
                return right - left + 1;
            }

            while (left <= right && s.charAt(left) == lc) {
                left++;
            }
            
            while (left <= right && s.charAt(right) == rc) {
                right--;
            }
        }

        return right - left + 1;
    }
}
```
# [LeetCode_2451_差值数组不同的字符串](https://leetcode.cn/problems/odd-string-difference/)
## 解法
### 思路
模拟
- 2层循环
  - 第一层遍历差值比较的坐标位置
  - 第二层遍历words字符串数组
- 第二层内部使用map来记录diff和对应的字符串列表
- 然后判断是否存在只有1个字符串的diff，如果有就返回座位结果
### 代码
```java
class Solution {
    public String oddString(String[] words) {
        if (words == null || words.length == 0) {
            return null;
        }

        int wordLen = words[0].length();
        for (int i = 1; i < wordLen; i++) {
            Map<Integer, List<String>> map = new HashMap<>();
            
            for (String word : words) {
                map.computeIfAbsent(getDiff(word, i), x -> new ArrayList<>()).add(word);
            }

            for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
                if (entry.getValue().size() == words.length) {
                    break;
                }
                
                if (entry.getValue().size() == 1) {
                    return entry.getValue().get(0);
                }
            }
        }

        return null;
    }
    
    private int getDiff(String word, int index) {
        return word.charAt(index) - word.charAt(index - 1);
    }
}
```
# [LeetCode_2455_可被三整除的偶数的平均值](https://leetcode.cn/problems/average-value-of-even-numbers-that-are-divisible-by-three/)
## 解法
### 思路
- 遍历数组，判断能否被6整除，如果可以就累加和以及整除的个数
- 返回总和与个数相除的商，但注意个数如果是0，则直接返回0
### 代码
```java
class Solution {
    public int averageValue(int[] nums) {
        int sum = 0, count = 0;
        for (int num : nums) {
            if (num % 6 == 0) {
                sum += num;
                count++;
            }
        }
        
        return count == 0 ? 0 : sum / count;
    }
}
```
# [LeetCode_2460_对数组执行操作](https://leetcode.cn/problems/apply-operations-to-an-array/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int[] applyOperations(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n];
        int head = 0;
        int tail = n - 1;
        
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] != 0) {
                if (nums[i] == nums[i + 1]) {
                    nums[i] *= 2;
                    nums[i + 1] = 0;
                    arr[tail--] = 0;
                }
                
                arr[head++] = nums[i];
            }
        }
        
        if (nums[n - 1] != 0) {
            arr[head] = nums[n - 1]; 
        }
        
        return arr;
    }
}
```
# [LeetCode_2465_不同的平均值数目](https://leetcode.cn/problems/number-of-distinct-averages/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int distinctAverages(int[] nums) {
        int n = nums.length, deleted = 0;
        Set<Double> set = new HashSet<>();
        boolean[] bucket = new boolean[n];

        while (deleted < n) {
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE,
                minIndex = -1, maxIndex = -1;
            for (int i = 0; i < n; i++) {
                if (bucket[i]) {
                    continue;
                }
                
                int num = nums[i];
                if (num < min) {
                    min = num;
                    minIndex = i;
                }

                if (num > max) {
                    max = num;
                    maxIndex = i;
                }
            }
            
            bucket[maxIndex] = true;
            bucket[minIndex] = true;
            set.add(1D * (max + min) / 2);
            deleted += 2;
        }
        
        return set.size();
    }
}
```
# [LeetCode_2469_温度转换](https://leetcode.cn/problems/convert-the-temperature/)
## 解法
### 思路
过于简单，模拟
### 代码
```java
class Solution {
    public double[] convertTemperature(double celsius) {
        return new double[]{celsius + 273.15, celsius * 1.8 + 32};
    }
}
```
# [LeetCode_2475_数组中不等三元组的数目](https://leetcode.cn/problems/number-of-unequal-triplets-in-array/)
## 解法
### 思路
3层循环
### 代码
```java
class Solution {
    public int unequalTriplets(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int n1 = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int n2 = nums[j];
                
                if (n1 == n2) {
                    continue;
                }
                
                for (int k = j + 1; k < nums.length; k++) {
                    int n3 = nums[k];
                    if (n1 == n3 || n2 == n3) {
                        continue;
                    }
                    
                    ans++;
                }
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_2485_找出中枢整数](https://leetcode.cn/problems/find-the-pivot-integer/)
## 解法
### 思路
前缀和
### 代码
```java
class Solution {
    public int pivotInteger(int n) {
        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + i;
        }

        for (int i = 1; i < sums.length; i++) {
            if (sums[i] == sums[n] - sums[i - 1]) {
                return i;
            }
        }
        
        return -1;
    }
}
```
## 解法二
### 思路
- (1 + x) * x / 2 = (x + n) * (n - x + 1) / 2
- x2 = (n2 + n) / 2
- 开根号求x值，如果可以开出整数，那么就返回这个值，否则返回-1
### 代码
```java
class Solution {
    public int pivotInteger(int n) {
        double x = Math.sqrt((n * n + n) / 2.0);
        return x - (int) x > 0 ?  -1 : (int) x;
    }
}
```