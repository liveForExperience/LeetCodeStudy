# [LeetCode_852_山脉数组的峰顶索引](https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/)
## 解法
### 思路
遍历找到比前后元素都大的元素坐标，找到后直接返回
### 代码
```java
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < arr[i] && arr[i + 1] < arr[i]) {
                return i;
            }
        }
        
        return -1;
    }
}
```
## 解法二
### 思路
二分查找
### 代码
```java
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int head = 1, tail = arr.length - 2;
        while (head <= tail) {
            int mid = (tail - head) / 2 + head;
            
            if (arr[mid - 1] < arr[mid] && arr[mid + 1] < arr[mid]) {
                return mid;
            } else if (arr[mid - 1] < arr[mid]) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }
        
        return -1;
    }
}
```
# [LeetCode_279_完全平方数](https://leetcode-cn.com/problems/perfect-squares/)
## 解法
### 思路
动态规划：
- `dp[i]`：组合成总和为i的整数的完全平方数个数的最小值
- 状态转移方程：`dp[i] = min(dp[i], dp[i - j * j] + 1)`
- base case：`dp[i] = i`，相当于设置初始值为最大值，方便做最小值比较
- 返回结果：`dp[n]`
### 代码
```java
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }
}
```
# [LeetCode_877_石子游戏](https://leetcode-cn.com/problems/stone-game/)
## 解法
### 思路
[数学解法](https://leetcode-cn.com/problems/stone-game/solution/shi-zi-you-xi-by-leetcode-solution/)：
- 因为石头堆是偶数个，所以如果将石头堆分成2组，每组的单个元素间隔排列，那么总的石头堆就是的初始状态时，头是一组，尾一定是另一组
- 如果而当先手这选择了头尾的任意一堆后，后手者只能选择上剩下的那组石头堆，因为被拿走一堆后的总石头堆，头尾都只有另一组的石头
- 而后手者拿完他那一轮后，先手者又可以在2组石头堆中选择，而后手只能选剩下的一组的2个
- 所以按照这个特性，先手者只要计算出2组石头的总数，然后每次都选择那一组的石头，就可以确定拿到总数最多的石头
### 代码
```java
class Solution {
    public boolean stoneGame(int[] piles) {
        return true;
    }
}
```
# [LeetCode_486_预测赢家](https://leetcode-cn.com/problems/predict-the-winner/)
## 解法
### 思路
递归：
- 假设先手获取分数为正，后手获取分数为负
- 那么计算过程就是，递归计算每种可能路径，并做好正负转换，累加数值
- 最后看返回的最大值是否大于等于0，如果是就说明先手不会输
- 时间复杂度为2的n次方
### 代码
```java
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        return recuse(nums, 0, nums.length - 1, 1) >= 0;
    }

    private int recuse(int[] nums, int start, int end, int turn) {
        if (start == end) {
            return nums[start] * turn;
        }

        int pickStart = nums[start] * turn + recuse(nums, start + 1, end, -turn),
            pickEnd = nums[end] * turn + recuse(nums, start, end - 1, -turn);

        return turn == 1 ? Math.max(pickEnd, pickStart) : Math.min(pickStart, pickEnd);
    }
}
```
## 解法二
### 思路
动态规划：
- dp[i][j]：在数组坐标i与j的范围内（i<=j，否则无意义），当前玩家与后手玩家之间的分数的最大差值
- base case：当i == j时，dp[i][j] = nums[i]。当只剩下一个元素可以选择时，那么当前选择的玩家与后手玩家的差值就是当前元素值，因为后手选不了了
- 状态转移方程：dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1])
- 返回dp[0][nums.lenght() - 1] >= 0
### 代码
```java
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
        }

        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }

        return dp[0][len - 1] >= 0;
    }
}
```
# [LeetCode_65_有效数字](https://leetcode-cn.com/problems/valid-number/)
## 解法
### 思路
确定有限状态自动机：
- 包含的状态：
    - 初始状态，一种特殊状态
    - 接受状态，一系列状态的集合
    - 一个状态可能既是初始状态，也是接收状态
- 起始的时候，自动机处于初始状态
- 它顺序读取字符串的字符，并根据状态和读入的字符，按照实现约定好的转移规则，从当前状态转移到下一个状态，当状态转移完成后，就读取下一个字符
- 当字符串全部读取完毕后，如果自动机：
    - 处于某个接收状态，那就判定该字符串为被接收
    - 否则该字符串被拒绝
- 如果在状态转移过程中，转移失败了，也就是不存在对应的转移规则，此时计算将提前终止，这种状态也成为被拒绝。
- 概括：对于给定的输入字符串S，判断是否满足条件P
    - S是题目提供的字符串
    - P是构成合法的标识数值的字符串
- 自动机可以理解成一种暴力枚举方法的延伸，它对应了任何一种情况下，对应任何的输入，需要做的事情。它与KMP和正则表达式有着密切的关系。
- 合法数值字符串应当具有的格式：
    - 符号位：+、-
    - 整数部分：若干字符0-9组成的字符串
    - 小数点
    - 效数部分：构成部分与整数部分相同
    - 指数部分：包含开头的字符e（大小写均可）、可选的符号位以及整数部分
- 如上的5个部分，每个部分都不是必须的，但是也受一些额外规则的制约，例如：
    - 如果符号位存在，其后面必须跟着数字或小数点
    - 小数点的前后两侧，至少有一侧是数字
- 列出自动机的状态集合，即，当前处理到字符串的哪个部分：
    0. 初始状态
    1. 符号位
    2. 整数部分
    3. 左侧有整数的小数点
    4. 左侧无整数的小数点
    5. 小数部分
    6. 符号e
    7. 指数部分的符号位
    8. 指数部分的整数部分
- 确定初始状态和接收状态：
    - 初始状态是0
    - 接收状态的集合是[2,3,5,8]
- 转移规则：
    [图示](https://assets.leetcode-cn.com/solution-static/65/1.png)
- 处理：
    - 初始化各种集合：
        - 状态集合
        - 字符类型集合：整数，小数点，符号E，正负符号，非法字符
    - 维护字符类型与状态之间的关系
        - `<State, <CharType, State>>`
        - 初始状态：
            - 整数：整数状态
            - 小数点：无整数小数点状态
            - 整数符号：整数符号
        - 整数符号状态：
            - 整数：整数状态
            - 小数点：小数点状态
        - 整数状态：
            - 整数：整数状态
            - 符号e：符号e状态
        - 无整数小数点状态：
            - 整数：小数状态
        - 小数点状态：
            - 整数：小数状态
            - 符号e：符号e状态
        - 小数状态：
            - 整数：小数状态
            - 符号e：符号e状态
        - 符号e状态：
            - 整数：指数整数状态
            - 符号：指数符号状态
        - 指数符号状态：
            - 整数：指数整数状态
        - 指数整数状态：
            - 整数：指数整数状态
### 代码
```java
class Solution {
    public boolean isNumber(String s) {
        Map<State, Map<CharType, State>> transfer = new HashMap<>();
        Map<CharType, State> stateInitialMap = new HashMap<>();
        stateInitialMap.put(CharType.NUMBER, State.STATE_INT);
        stateInitialMap.put(CharType.SIGN, State.STATE_INT_SIGN);
        stateInitialMap.put(CharType.POINT, State.STATE_POINT_WITHOUT_INT);
        transfer.put(State.STATE_INITIAL, stateInitialMap);

        Map<CharType, State> stateIntMap = new HashMap<>();
        stateIntMap.put(CharType.NUMBER, State.STATE_INT);
        stateIntMap.put(CharType.POINT, State.STATE_POINT);
        stateIntMap.put(CharType.EXPRESSION, State.STATE_EXPRESSION);
        transfer.put(State.STATE_INT, stateIntMap);

        Map<CharType, State> stateIntSignMap = new HashMap<>();
        stateIntSignMap.put(CharType.NUMBER, State.STATE_INT);
        stateIntSignMap.put(CharType.POINT, State.STATE_POINT_WITHOUT_INT);
        transfer.put(State.STATE_INT_SIGN, stateIntSignMap);

        Map<CharType, State> statePointWithoutIntMap = new HashMap<>();
        statePointWithoutIntMap.put(CharType.NUMBER, State.STATE_FRACTION);
        transfer.put(State.STATE_POINT_WITHOUT_INT, statePointWithoutIntMap);

        Map<CharType, State> statePointMap = new HashMap<>();
        statePointMap.put(CharType.NUMBER, State.STATE_FRACTION);
        statePointMap.put(CharType.EXPRESSION, State.STATE_EXPRESSION);
        transfer.put(State.STATE_POINT, statePointMap);

        Map<CharType, State> stateExpressionMap = new HashMap<>();
        stateExpressionMap.put(CharType.NUMBER, State.STATE_EXPRESSION_NUMBER);
        stateExpressionMap.put(CharType.SIGN, State.STATE_EXPRESSION_SIGN);
        transfer.put(State.STATE_EXPRESSION, stateExpressionMap);

        Map<CharType, State> stateExpressionNumMap = new HashMap<>();
        stateExpressionNumMap.put(CharType.NUMBER, State.STATE_EXPRESSION_NUMBER);
        transfer.put(State.STATE_EXPRESSION_NUMBER, stateExpressionNumMap);

        Map<CharType, State> stateExpressionSignMap = new HashMap<>();
        stateExpressionSignMap.put(CharType.NUMBER, State.STATE_EXPRESSION_NUMBER);
        transfer.put(State.STATE_EXPRESSION_SIGN, stateExpressionSignMap);

        Map<CharType, State> stateFractionMap = new HashMap<>();
        stateFractionMap.put(CharType.NUMBER, State.STATE_FRACTION);
        stateFractionMap.put(CharType.EXPRESSION, State.STATE_EXPRESSION);
        transfer.put(State.STATE_FRACTION, stateFractionMap);

        int len = s.length();
        State state = State.STATE_INITIAL;

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            CharType charType = toCharType(c);

            Map<CharType, State> charTypeStateMap = transfer.get(state);
            if (!charTypeStateMap.containsKey(charType)) {
                return false;
            }

            state = charTypeStateMap.get(charType);
        }

        return state == State.STATE_INT || state == State.STATE_POINT || state == State.STATE_FRACTION || state == State.STATE_EXPRESSION_NUMBER || state == State.STATE_END;
    }

    private CharType toCharType(char c) {
        if (c <= '9' && c >= '0') {
            return CharType.NUMBER;
        } else if (c == '+' || c == '-') {
            return CharType.SIGN;
        } else if (c == 'e' || c == 'E') {
            return CharType.EXPRESSION;
        } else if (c == '.') {
            return CharType.POINT;
        } else {
            return CharType.ILLEGAL;
        }
    }

    enum State {
        /**
         * 状态
         */
        STATE_INITIAL,
        STATE_INT,
        STATE_INT_SIGN,
        STATE_POINT,
        STATE_POINT_WITHOUT_INT,
        STATE_FRACTION,
        STATE_EXPRESSION,
        STATE_EXPRESSION_SIGN,
        STATE_EXPRESSION_NUMBER,
        STATE_END;
    }

    enum CharType {
        /**
         * 字符串类型
         */
        NUMBER,
        SIGN,
        EXPRESSION,
        POINT,
        ILLEGAL;
    }
}
```