# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_940_不同子序列](https://leetcode.cn/problems/distinct-subsequences-ii/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1620_网络信号最好的坐标](https://leetcode.cn/problems/coordinate-with-maximum-network-quality/)
## 解法
### 思路
模拟：
- 遍历towers，结合radius来确定坐标的最大范围
- 遍历所有坐标可能，然后遍历towers，通过计算欧几里得距离，来确定是否在当前tower的范围内，如果是，就累加信号值
- 判断信号值是否大于最大值，如果是就更新坐标和最大值
- 坐标遍历结束，返回结果
### 代码
```java
class Solution {
    public int[] bestCoordinate(int[][] towers, int radius) {
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE, max = 0, ax = 0, ay = 0;
        for (int[] tower : towers) {
            int x = tower[0], y = tower[1];
            maxX = Math.max(maxX, x + radius);
            maxY = Math.max(maxY, y + radius);
        }

        for (int x = 0; x <= maxX; x++) {
            for (int y = 0; y <= maxY; y++) {
                int[] coordinate = {x, y};
                int cur = 0;
                for (int[] tower : towers) {
                    int squareDistance = getSquareDistance(tower, coordinate);

                    if (squareDistance <= radius * radius) {
                        double distance = Math.sqrt(squareDistance);
                        cur += tower[2] / (1 + distance);
                    }
                }
                
                if (cur > max) {
                    ax = x;
                    ay = y;
                    max = cur;
                }
            }
        }
        
        return new int[]{ax, ay};
    }

    private int getSquareDistance(int[] towers, int[] coordinate) {
        return (towers[0] - coordinate[0]) * (towers[0] - coordinate[0]) + (towers[1] - coordinate[1]) * (towers[1] - coordinate[1]);
    }
}
```
# [LeetCode_1106_解析布尔表达式](https://leetcode.cn/problems/parsing-a-boolean-expression/)
## 解法
### 思路
栈
- 遍历字符串
- 把字符串中的字符内容拆分成3个部分，按照不同逻辑进行处理
  - 逗号：忽略跳过
  - 非右括号：放入栈中
  - 右括号：处理栈中的元素，当遇到右括号的时候，栈顶元素中一定包含了至少一组表达式
    - 弹出栈元素
    - 如果是t或f，对其进行计数
    - 如果是左括号就终止弹栈，然后从栈顶获取操作符
    - 根据计算t和f的个数，结合操作符，获取表达式的结果
    - 将表达式结果放入栈中
- 字符串遍历结束，返回栈顶元素
### 代码
```java
class Solution {
  public boolean parseBoolExpr(String expression) {
    Stack<Character> stack = new Stack<>();
    char[] cs = expression.toCharArray();

    for (char c : cs) {
      if (c == ',') {
      } else if (c != ')') {
        stack.push(c);
      } else {
        int t = 0, f = 0;
        while (stack.peek() != '(') {
          char token = stack.pop();
          if (token == 't') {
            t++;
          } else {
            f++;
          }
        }

        stack.pop();
        char operator = stack.pop();

        if (operator == '!') {
          stack.push(t > 0 ? 'f' : 't');
        } else if (operator == '|') {
          stack.push(t > 0 ? 't' : 'f');
        } else {
          stack.push(f > 0 ? 'f' : 't');
        }
      }
    }

    return stack.pop() == 't';
  }
}
```