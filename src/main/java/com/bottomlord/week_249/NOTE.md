# [LeetCode_924_尽量减少恶意软件的传播](https://leetcode.cn/problems/minimize-malware-spread)
## 解法
### 思路
- 根据邻接表生成联通分量，并统计连通分量的节点个数
- 然后思考如何最大程度的通过减少一个节点来减少传播的节点个数
  - 如果连通分量中没有感染的节点，不用考虑这个连通分量
  - 如果连通分量中只有一个感染节点，那么这个联通分量的节点个数就是可以减缓的个数
  - 如果连通分量中的感染节点大于1个，那么就算去掉某一个感染节点，该联通分量还是无法避免传染，所以也不用考虑
- 算法过程：
  - 初始化参数：
    - index作为联通分量的标识
    - 长度为n的数组`memo`来记录当前坐标对应的节点对应的连通分量的标识
    - map用于统计联通分量标识与联通分量个数的映射关系
    - map用于存储联通分量标识与受污染节点的的个数的映射关系`initialMap`
  - 从0开始遍历n个元素，对每个元素进行判断，如果其对应的联通分量标识不存在，则开始bfs搜索，否则代表已经处理过该节点
  - 每次循环都累加`index`作为一个新的联通分量标识
  - 初始化一个队列来进行bfs，并将当前坐标节点作为坐标在`memo`对应的坐标上记录`index`，将当前坐标作为初始值放入队列中
  - 通过邻接表来寻找出度，如果该出度没有被遍历过，则累加联通分量统计值`cnt`，并为该出度在`memo`中进行记录，紧接着继续放入队列中
  - 遍历结束后，将`index`和`cnt`存入到对应的映射表中
  - 遍历`initial`列表，根据该元素对应的连通分量标识，到`initialMap`中进行污染节点的累加统计
  - 最后遍历所有污染节点，判断当前污染节点对应的联通分量，其污染节点个数是否为1个，如果是，根据连通分量的个数来判断当前的个数是否是最大的，如果是最大的，就记录当前节点作为要删除的污染节点。
  - 遍历结束后返回记录的节点返回即可
### 代码
```java
class Solution {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        int[] memo = new int[n];
        int index = 0;
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (memo[i] != 0) {
                continue;
            }
            
            index++;
            int cnt = 1;
            memo[i] = index;
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(i);
            while (!queue.isEmpty()) {
                int cur = queue.poll();

                for (int next = 0; next < n; next++) {
                    if (memo[next] != 0 || graph[cur][next] != 1) {
                        continue;
                    }

                    cnt++;
                    memo[next] = index;
                    queue.offer(next);
                }
            }
            
            indexMap.put(index, cnt);
        }

        Map<Integer, Integer> initialMap = new HashMap<>();
        for (int i : initial) {
            initialMap.put(memo[i], initialMap.getOrDefault(memo[i], 0) + 1);
        }

        int ans = n, max = 0;
        for (int i : initial) {
            int removed = initialMap.get(memo[i]) == 1 ? indexMap.get(memo[i]) : 0;
            if (removed > max || (removed == max && i < ans)) {
                max = removed;
                ans = i;
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_2007_从双倍数组中还原原数组](https://leetcode.cn/problems/find-original-array-from-doubled-array)
## 解法
### 思路
- 当前数组的最小值一定不是双倍数，所以可以安全的把这个数和它的双倍数从数组中去除掉
- 记录下被删除的数，然后继续循环第一步，直到删除所有元素即可
- 算法过程：
  - 先判断数组长度是否为偶数，如果不是偶数可以之间判定失败
  - 初始化参数：
    - 哈希表`map`，用于存储数组中各数字的个数
    - 优先级队列`queue`，用于快速获取数组中的最小值
  - 遍历数组`changed`，将元素统计到`map`中，同时也将元素放入`queue`中
  - 循环处理n次逻辑，n为`changed`数组的长度除以2，循环体内依次处理：
    - 获取小顶堆堆顶元素`cur`，判断元素是否已经在之前的处理中被逻辑上删除了，如果删除了，就将当前元素弹出队列，并继续循环，同时本次训话的计数回退
    - 否则，当前元素就是目前数组中的最小值，将其放入结果数组中，同时将`cur`和`cur * 2`对应的个数从`map`中减一
    - 还需要判断，如果`cur * 2`在`map`中的计数已经小于0了，说明`changed`数组无法构成有效的双倍数组，直接返回空数组作为结果
  - 循环结束后，返回结果数组即可
### 代码
```java
class Solution {
    public int[] findOriginalArray(int[] changed) {
        if (changed.length % 2 == 1) {
            return new int[0];
        }

        Map<Integer, Integer> map = new HashMap<>();
        Queue<Integer> queue = new PriorityQueue<>();
        for (int num : changed) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            queue.offer(num);
        }

        int n = changed.length / 2, index = 0;
        int[] ans = new int[n];
        while (n-- > 0) {
            int cur = queue.peek();
            if (map.get(cur) == 0) {
                queue.poll();
                n++;
                continue;
            }

            ans[index++] = cur;
            map.put(cur, map.getOrDefault(cur, 0) - 1);
            map.put(cur * 2, map.getOrDefault(cur * 2, 0) - 1);
            
            if (map.get(cur * 2) < 0) {
                return new int[0];
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_39_组合总和](https://leetcode.cn/problems/combination-sum/description)
## 解法
### 思路
- 经典的回溯
- 算法过程：
  - 定义参数：
    - `index`：当前遍历到的数组坐标
    - `sum`：当前累加的总和
    - `list`：暂存的子数组序列
    - `ans`：答案数组
  - 对`candidates`数组进行排序，这样在遍历数组进行回溯时，能根据排序后的单调性，基于`sum > target`，快速判断当前状态无需继续回溯，可以剪枝
  - 然后回溯，回溯的时候，因为每个元素的使用次数无限，所以`index`在下钻的时候不需要+1
  - 每次回溯返回的时候，记得对`sum`和`list`做恢复操作即可
### 代码
```java
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(0, candidates, 0, target, new ArrayList<>(), ans);
        return ans;
    }
    
    private void backTrack(int index, int[] candidates, int sum, int target, List<Integer> list, List<List<Integer>> ans) {
        if (sum > target) {
            return;
        }
        
        if (sum == target) {
            ans.add(new ArrayList<>(list));
            return;
        }
        
        if (index >= candidates.length) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            sum += candidates[i];
            list.add(candidates[i]);
            backTrack(i, candidates, sum, target, list, ans);
            list.remove(list.size() - 1);
            sum -= candidates[i];
        }
    }
}
```