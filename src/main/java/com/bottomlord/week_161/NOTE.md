# [LeetCode_640_求解方程](https://leetcode.cn/problems/solve-the-equation/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public String solveEquation(String equation) {
        List<String> tokens = new ArrayList<>();
        Set<String> operators = new HashSet<>();
        operators.add("+");
        operators.add("-");
        operators.add("=");

        char[] cs = equation.toCharArray();

        for (int i = 0; i < cs.length; i++) {
            StringBuilder sb = new StringBuilder();
            while (i < cs.length && !operators.contains("" + cs[i])) {
                sb.append(cs[i]);
                i++;
            }

            if (sb.length() != 0) {
                tokens.add(sb.toString());
            }

            if (i < cs.length) {
                tokens.add("" + cs[i]);
            }
        }

        boolean meetEq = false;
        int x = 0, num = 0;
        String preOperator = "+";
        for (String token : tokens) {
            if (operators.contains(token)) {
                if (Objects.equals(token, "=")) {
                    meetEq = true;
                    preOperator = "+";
                    continue;
                }

                preOperator = token;
                continue;
            }

            if (!meetEq) {
                if (Objects.equals(preOperator, "+")) {
                    if (isX(token)) {
                        x += countX(token);
                    } else {
                        num -= getNum(token);
                    }
                } else {
                    if (isX(token)) {
                        x -= countX(token);
                    } else {
                        num += getNum(token);
                    }
                }
            } else {
                if (Objects.equals(preOperator, "+")) {
                    if (isX(token)) {
                        x -= countX(token);
                    } else {
                        num += getNum(token);
                    }
                } else {
                    if (isX(token)) {
                        x += countX(token);
                    } else {
                        num -= getNum(token);
                    }
                }
            }
        }

        if (x == 0) {
            return num != 0 ? "No solution" : "Infinite solutions";
        }
        num /= x;
        return "x=" + num;
    }

    private boolean isX(String token) {
        return token.contains("x");
    }

    private int getNum(String token) {
        return Integer.parseInt(token);
    }

    public int countX(String token) {
        if (token.length() == 1) {
            return 1;
        }
        return Integer.parseInt(token.substring(0, token.length() - 1));
    }
}
```
# [LeetCode_2335_装满杯子需要的最短总时长](https://leetcode.cn/problems/minimum-amount-of-time-to-fill-cups)
## 解法
### 思路
优先级队列
### 代码
```java
class Solution {
    public int fillCups(int[] amount) {
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : amount) {
            queue.offer(num);
        }

        int count = 0;
        while (queue.size() > 1) {
            int one = queue.poll(), two = queue.poll();
            if (one == 0) {
                count += two;
                continue;
            }
            
            if (two == 0) {
                count += one;
                continue;
            }
            
            one --;
            two --;

            if (one != 0) {
                queue.offer(one);
            }

            if (two != 0) {
                queue.offer(two);
            }

            count++;
        }

        if (!queue.isEmpty()) {
            count += queue.poll();
        }

        return count;
    }
}
```
## 解法二
### 思路
贪心：
- 排序
- 最大值如果大于小的2个值的和，那么最终的消耗就是最大值
- 最大值会被事先消耗，剩下的就是`t = a + b - c`
- 按照贪心思想，剩下的值可以理解成都能尽可能的通过填满2个杯子，那么就是`(t + 1) / 2`
- 所以结果就是`t / 2 + c`
### 代码
```java
class Solution {
    public int fillCups(int[] amount) {
        Arrays.sort(amount);
        int a = amount[0], b = amount[1], c = amount[2];
        if (c >= a + b) {
            return c;
        }
        
        return (a + b - c + 1) / 2 + c;
    }
}
```
# [LeetCode_1282_用户分组](https://leetcode.cn/problems/group-the-people-given-the-group-size-they-belong-to/)
## 解法
### 思路
hash表
### 代码
```java
class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            map.computeIfAbsent(groupSizes[i], x -> new ArrayList<>()).add(i);
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int len = entry.getKey();
            List<Integer> candidates = entry.getValue();
            int index = 0;
            
            while (index < candidates.size()) {
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < len; i++) {
                    list.add(candidates.get(index++));
                }
                ans.add(list);
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_768_最多能完成排序的块II](https://leetcode.cn/problems/max-chunks-to-make-sorted-ii/)
## 解法
### 思路
- 完整的数组，一定是一个正确答案，因为直接排序就可以了
- 如果有2部分：
  - 那么必定有一个坐标k，它和它左边的元素形成的子序列，可以通过排序，和排序后的同样坐标区间的元素频次完全相同
  - 只要左边一部分是完全相同的，那么另一边也就可以通过排序获得完全相同的升序序列
- 以此类推，2部分的右边部分，可以继续通过如上方法进行切分
- 模拟如上的方式，可以通过遍历数组，并用hash表记录频次来处理
### 代码
```java
class Solution {
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length, ans = 0;
        int[] sortedArr = new int[n];
        System.arraycopy(arr, 0, sortedArr, 0, n);
        Arrays.sort(sortedArr);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int x = arr[i], y = sortedArr[i];
            map.put(x, map.getOrDefault(x, 0) + 1);
            
            if (map.get(x) == 0) {
                map.remove(x);
            }
            
            map.put(y, map.getOrDefault(y, 0) - 1);
            if (map.get(y) == 0) {
                map.remove(y);
            }
            
            if (map.isEmpty()) {
                ans++;
            }
        }
        
        return ans;
    }
}
```
## 解法二
### 思路

### 代码
```java

```