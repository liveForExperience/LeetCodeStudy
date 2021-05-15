# [LeetCode_872_叶子相似的树](https://leetcode-cn.com/problems/leaf-similar-trees/)
## 解法
### 思路
- 分别dfs两棵树，搜索收集从左到右的叶子
- 然后比较搜集到的集合是否相等
### 代码
```java
class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        dfs(root1, list1);
        dfs(root2, list2);
        if (list1.size() != list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            if (!Objects.equals(list1.get(i), list2.get(i))) {
                return false;
            }
        }
        
        return true;
    }
    
    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        
        if (node.left == null && node.right == null) {
            list.add(node.val);
        }
        
        dfs(node.left, list);
        dfs(node.right, list);
    }
}
```
# [LeetCode_760_找出变位映射](https://leetcode-cn.com/problems/find-anagram-mappings/)
## 解法
### 思路
2层循环找到对应坐标，存储到结果数组中
### 代码
```java
class Solution {
    public int[] anagramMappings(int[] A, int[] B) {
        int[] ans = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] == B[j]) {
                    ans[i] = j;
                }
            }
        }
        return ans;
    }
}
```
## 解法二
### 思路
- 将B数组的值与坐标生成对应的映射表
- 遍历A数组，根据映射表找到B的坐标并放入结果数组
### 代码
```java
class Solution {
    public int[] anagramMappings(int[] A, int[] B) {
        Map<Integer, Integer> mapping = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            mapping.put(B[i], i);
        }

        for (int i = 0; i < A.length; i++) {
            A[i] = mapping.get(A[i]);
        }
        return A;
    }
}
```
# [LeetCode_1734_解码异或后的排序](https://leetcode-cn.com/problemset/all/)
## 解法
### 思路
- 按照题目的描述，原数组是[1,n]这n个元素组成的，那么用total可以表示为他们累计异或的值
- 根据题目意思，n是奇数，那么n-1就是偶数，且encoded的所有元素都是依次相邻元素的异或值，因此encoded[1] ^ encoded[3] ^ ... encoded[n - 2]，就是除原数组第一个元素外所有元素相异或的值
- 将这两部分的值再相异或，就能得到第一个元素，然后依次遍历encoded数组，就能还原出原始数组了  
### 代码
```java
class Solution {
    public int[] decode(int[] encoded) {
                int n = encoded.length + 1;
        int[] ans = new int[n];

        int total = 0;
        for (int i = 1; i <= n; i++) {
            total ^= i;
        }

        int xor = 0;
        for (int i = 1; i < n - 1; i += 2) {
            xor ^= encoded[i];
        }
        
        int first = total ^ xor;

        ans[0] = first;
        for (int i = 0; i < encoded.length; i++) {
            ans[i + 1] = ans[i] ^ encoded[i];
        }

        return ans;
    }
}
```
# [LeetCode_LCP29_乐团站位](https://leetcode-cn.com/problems/SNJvJP/)
## 失败解法
### 原因
栈溢出
### 思路
- 初始化二维数组
- 模拟填充二维数组
- 当遇到题目要求的坐标时返回
### 代码
```java
class Solution {
    private int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int orchestraLayout(int num, int xPos, int yPos) {
        int[][] matrix = new int[num][num];
        for (int[] row : matrix) {
            Arrays.fill(row, 0);
        }
        recurse(matrix, num, 0, 0, xPos, yPos, 0, 0);
        return matrix[xPos][yPos];
    }

    private void recurse(int[][] matrix, int num, int x, int y, int px, int py, int index, int count) {
        matrix[x][y] = count % 9 + 1;
        
        if (px == x && py == y) {
            return;
        }
        
        int nextX = x + directions[index][0], nextY = y + directions[index][1];
        if (nextX >= num || nextX < 0 || nextY >= num || nextY < 0 || matrix[nextX][nextY] != 0) {
            index = (index + 1) % 4;
            nextX = x + directions[index][0];
            nextY = y + directions[index][1];
        }
        
        recurse(matrix, num, nextX, nextY, px, py, index, count + 1);
    }
}
```
## 解法
### 思路
- 根据入参坐标确定在往内循环的第几层，方法是找到4个边与坐标距离最小的值
- 根据层数，确定到达该层前一层时共累计了多少数值，然后根据坐标确定其在最后一圈累计的个数
- 对最终的个数进行取模就获得最后的答案
- 所有变量使用long类型，防止溢出
### 代码
```java
class Solution {
    public int orchestraLayout(int num, int xPos, int yPos) {
        if (num == 1) {
            return 1;
        }
        
        long layer = Math.min(xPos, Math.min(yPos, Math.min(num -  1 - xPos, num - 1 - yPos)));
        
        long count = num - 1;
        long sum = 0;
        for (int i = 0; i < layer; i++) {
            sum += count * 4;
            count -= 2;
        }
        
        if (layer == xPos) {
            sum += yPos - layer + 1;
        } else if (layer == num - 1 - yPos) {
            sum += count + xPos - layer + 1;
        } else if (layer == num - 1 - xPos) {
            sum += count * 2 + num - 1 - layer - yPos + 1;
        } else {
            sum += count * 3 + num - 1 - layer - xPos + 1;
        }
        
        int mod = (int) (sum % 9);
        return mod == 0 ? 9 : mod;
    }
}
```
# [LeetCode_1310_子数组异或查询](https://leetcode-cn.com/problems/xor-queries-of-a-subarray/)
## 解法
### 思路
和前缀和类似
### 代码
```java
class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int len = arr.length, ansLen = queries.length;
        int[] xors = new int[len];
        xors[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            xors[i] = xors[i - 1] ^ arr[i];
        }

        int[] ans = new int[ansLen];
        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0], end = queries[i][1];
            ans[i] = (start == 0 ? 0 : xors[start - 1]) ^ xors[end];
        }

        return ans;
    }
}
```
# [LeetCode_1269_停在原地的方案数](https://leetcode-cn.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/)
## 失败解法
### 原因
超时
### 思路
dfs+剪枝
### 代码
```java
class Solution {
    private int mod = 1000000007;
    public int numWays(int steps, int arrLen) {
        return dfs(steps, arrLen, 0, 0);
    }

    private int dfs(int steps, int arrLen, int index, int step) {
        if (index < 0 || index >= arrLen) {
            return 0;
        }
        
        if (step == steps) {
            return index == 0 ? 1 : 0;
        }
        
        if (index > steps - step) {
            return 0;
        }
        
        int count = 0;
        for (int i = -1; i < 2; i++) {
            count += dfs(steps, arrLen, index + i, step + 1);
            count %= mod;
        }
        return count % mod;
    }
}
```
## 解法
### 思路
dfs+剪枝+回溯
### 代码
```java
class Solution {
    private int mod = 1000000007;
    public int numWays(int steps, int arrLen) {
        return dfs(steps, arrLen, 0, 0, new HashMap<>());
    }

    private int dfs(int steps, int arrLen, int index, int step, Map<String, Integer> memo) {
        if (index < 0 || index >= arrLen) {
            return 0;
        }
        
        String key = index + ":" + step;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (step == steps) {
            return index == 0 ? 1 : 0;
        }

        if (index > steps - step) {
            return 0;
        }

        int count = 0;
        for (int i = -1; i < 2; i++) {
            count += dfs(steps, arrLen, index + i, step + 1, memo);
            count %= mod;
        }
        
        memo.put(key, count);
        return count % mod;
    }
}
```
# [LeetCode_800_相似的RGB颜色](https://leetcode-cn.com/problems/similar-rgb-color/)
## 解法
### 思路
枚举所有的简写情况，与目标字符串的每个数比较，取差的最小值
### 代码
```java
class Solution {
    public String similarRGB(String color) {
        int[] nums = new int[]{0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77,
                               0x88, 0x99, 0xaa, 0xbb, 0xcc, 0xdd, 0xee, 0xff};

        String[] strs = new String[]{"00", "11", "22", "33", "44", "55", "66", "77",
                                    "88", "99", "aa", "bb", "cc", "dd", "ee", "ff"};

        StringBuilder sb = new StringBuilder("#");
        for (int i = 0; i < color.length(); i += 2) {
            int target = Integer.parseInt(color.substring(i, i + 2), 16);
            int min = Integer.MAX_VALUE;
            String ansSubStr = "";
            for (int j = 0; j < nums.length; j++) {
                int diff = Math.abs(target - nums[j]);
                if (diff < min) {
                    min = diff;
                    ansSubStr = strs[j];
                }
            }

            sb.append(ansSubStr);
        }

        return sb.toString();
    }
}
```
# [LeetCode_12_整数转罗马数字](https://leetcode-cn.com/problems/integer-to-roman/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        if (num / 1000 != 0) {
            int time = num / 1000;
            for (int i = 0; i < time; i++) {
                sb.append("M");
            }
            num %= 1000;
        }

        if (num / 500 != 0) {
            if (num / 900 == 1) {
                sb.append("C").append("M");
                num %= 900;
            } else {
                int time = num / 500;
                for (int i = 0; i < time; i++) {
                    sb.append("D");
                }
                num %= 500;
            }
        }

        if (num / 100 != 0) {
            if (num / 400 == 1) {
                sb.append("C").append("D");
                num %= 400;
            } else {
                int time = num / 100;
                for (int i = 0; i < time; i++) {
                    sb.append("C");
                }
                num %= 100;
            }
        }

        if (num / 50 != 0) {
            if (num / 90 == 1) {
                sb.append("X").append("C");
                num %= 90;
            } else {
                int time = num / 50;
                for (int i = 0; i < time; i++) {
                    sb.append("L");
                }
                num %= 50;
            }
        }

        if (num / 10 != 0) {
            if (num / 40 == 1) {
                sb.append("X").append("L");
                num %= 40;
            } else {
                int time = num / 10;
                for (int i = 0; i < time; i++) {
                    sb.append("X");
                }
                num %= 10;
            }
        }

        if (num == 9) {
            sb.append("I").append("X");
        } else if (num == 4){
            sb.append("I").append("V");
        } else {
            int time = num / 5;
            for (int i = 0; i < time; i++) {
                sb.append("V");
            }
            num %= 5;

            for (int i = 0; i < num; i++) {
                sb.append("I");
            }
        }

        return sb.toString();
    }
}
```
# [LeetCode_13_罗马数字转整数](https://leetcode-cn.com/problems/roman-to-integer/)
## 解法
### 思路
- 使用linkedHashMap有序存储存储罗马字符和数值的映射关系
- 将6个特殊字符放在map的前部
- 通过遍历特殊字符，找到s中对应的位置，累加的同时也做好这些字符出现位置的标记工作
- 最后再遍历一次字符串，跳过标记后的字符，再通过map找到非特殊字符，进行累加
- 返回累加值
### 代码
```java
class Solution {
    public int romanToInt(String s) {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("CM", 900);
        map.put("CD", 400);
        map.put("XC", 90);
        map.put("XL", 40);
        map.put("IX", 9);
        map.put("IV", 4);
        map.put("M", 1000);
        map.put("D", 500);
        map.put("C", 100);
        map.put("L", 50);
        map.put("X", 10);
        map.put("V", 5);
        map.put("I", 1);

        boolean[] bucket = new boolean[s.length()];

        int ans = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            if (Objects.equals(key, "M")) {
                break;    
            }
            
            int index = s.indexOf(entry.getKey());
            while (index != -1) {
                bucket[index] = bucket[index + 1] = true;
                ans += map.get(entry.getKey());
                index = s.indexOf(entry.getKey(), index + 1);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (!bucket[i]) {
                ans += map.get("" + s.charAt(i));
            }
        }

        return ans;
    }
}
```