# [LeetCode_1376_通知所有员工所需的时间](https://leetcode.cn/problems/time-needed-to-inform-all-employees/)
## 解法
### 思路
自底向上的dfs
- map生成上级与下级列表之间的映射关系
- 递归方式实现bfs，每一层递归的主逻辑是为获取当前manager发送通知的最大耗时：`自身的informTime + max(下属及下属通知的总耗时)`
### 代码
```java
class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTimes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < manager.length; i++) {
            map.computeIfAbsent(manager[i], x -> new ArrayList<>()).add(i);
        }
        
        return bfs(headID, map, informTimes);
    }
    
    private int bfs(int manager, Map<Integer, List<Integer>> map, int[] informTimes) {
        int curTime = informTimes[manager], cost = 0;
        for (Integer emp : map.getOrDefault(manager, new ArrayList<>())) {
            cost = Math.max(cost, bfs(emp, map, informTimes));
        }
        return curTime + cost;
    }
}
```
## 解法二
### 思路
自顶向下的dfs
- 初始化一个time数组
- 遍历n个员工，递归过程主要是通过dfs计算出从顶到到达当前层的耗时cost
- 当前层的总耗时就是`cost + informTime[cur]`
- 遍历结束后，返回最大值，这个最大值可以在遍历过程中通过临时变量动态维护
### 代码
```java
class Solution {
    public int numOfMinutes(int n, int headID, int[] managers, int[] informTimes) {
        int[] costs = new int[n];
        Arrays.fill(costs, -1);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(i, costs, informTimes, managers));
        }
        return ans;
    }

    private int dfs(int cur, int[] costs, int[] informTimes, int[] managers) {
        int manager = managers[cur];

        if (manager == -1) {
            return costs[cur] = informTimes[cur];
        }

        if (costs[manager] == -1) {
            costs[manager] = dfs(manager, costs, informTimes, managers);
        }

        return costs[cur] = costs[manager] + informTimes[cur];
    }
}
```
# [LeetCode_970_强整数](https://leetcode.cn/problems/powerful-integers/)
## 解法
### 思路
暴力枚举
### 代码
```java
class Solution {
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        int v1 = 1;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 21; i++) {
            int v2 = 1;
            for (int j = 0; j < 21; j++) {
                if (v1 + v2 <= bound) {
                    set.add(v1 + v2);
                } else {
                    break;
                }

                v2 *= y;
            }

            v1 *= x;

            if (v1 > bound) {
                break;
            }
        }

        return new ArrayList<>(set);
    }
}
```
# [LeetCode_1003_检查替换后的词是否有效](https://leetcode.cn/problems/check-if-word-is-valid-after-substitutions/)
## 解法
### 思路
- 使用String API，通过s.indexOf() != -1作为循环条件，检查字符串中是否存在连续的`abc`子字符串
- 如果存在就将该子字符串从s中剔除，继续循环
- 如果不存在，就退出循环，并判断字符串是否为空
### 代码
```java
class Solution {
    public boolean isValid(String s) {
        int i;
        while ((i = s.indexOf("abc")) != -1) {
            s = s.substring(0, i) + s.substring(i + 3);
        }

        return s.length() == 0;
    }
}
```
## 解法二
### 思路
栈
- 遍历字符串
  - 如果不是c，将字符压入栈中
  - 如果是c，判断如下几件事
    - 栈是否有至少2个元素
    - 弹出的第一个元素是否是b
    - 弹出的第二个元素是否是a
    - 如果都符合，则继续循环，否则就返回false
- 遍历结束，判断栈是否为空
### 代码
```java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == 'c') {
                if (stack.size() < 2) {
                    return false;
                }

                if (stack.pop() != 'b') {
                    return false;
                }

                if (stack.pop() != 'a') {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
}
```
## 解法三
### 思路
使用字符数组作为简易的栈
- 初始化一个变量i作为栈指针
- 初始化字符串s的字符数组cs
- 遍历cs
  - 如果字符不是c，那么将字符压入栈，然后指针i向右移动一位
  - 如果字符是c，那么判断如下三件事
    - 指针是否大于1
    - 最后2个字符是否依次是b和a
    - 如果任意不符合，就返回false
- 遍历结束后，返回坐标i是否为0
### 代码
```java
class Solution {
    public boolean isValid(String s) {
        int i = 0;
        char[] cs = s.toCharArray();
        for (char c : cs) {
            if (c == 'c') {
                if (i < 2 || cs[--i] != 'b' || cs[--i] != 'a') {
                    return false;
                }
            } else {
                cs[i++] = c;
            }
        }

        return i == 0;
    }
}
```
# [LeetCode_2651_计算列车到站时间](https://leetcode.cn/problems/calculate-delayed-arrival-time/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
  public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
    return (arrivalTime + delayedTime) % 24;
  }
}
```
# [LeetCode_]()
## 解法
### 思路

### 代码
```java

```