# [LeetCode_275_H指数II](https://leetcode.cn/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts)
## 解法
### 思路
- 思考过程：
  - `citations`数组在本题中是默认升序排序的，而题目要求解的h值在求解过程中同时具备单调性，值越大，对应要查找的元素坐标值也就越大
  - 那么在如上状态下就可以通过二分查找的方式来解这个问题
- 算法过程：
  - 定义二分的头尾指针，对应`citations`数组的坐标值`h`
  - 二分后获取中间值，该值作为坐标找到对应的值是论文的引用次数
  - 然后用元素总个数减去坐标值，就能得到大于`h`的论文个数
    - 如果`h < n - mid`，说明引用次数太小，需要将搜索区间缩小到右侧
    - 如果`h > n - mid`，说明引用次数足够大，这个值的右侧不需要再搜索，将搜索区间缩小到左侧
    - 如果`h == n - mid`，说明引用次数足够大，和`h > n - mid`代表的方式一致
  - 搜索结束后，head值对应的能够满足引用次数的最小坐标值，用`n`减去该值就是结果值
### 代码
```java
class Solution {
    public int hIndex(int[] citations) {
        int head = 0, n = citations.length, tail = n - 1;

        while (head <= tail) {
            int mid = head + (tail - head) / 2;
            
            int h = citations[mid];
            if (h < n - mid) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }

        return n - head;
    }
}
```
# [LeetCode_2003_每棵子树内缺失的最小基因值](https://leetcode.cn/problems/smallest-missing-genetic-value-in-each-subtree)
## 解法
### 思路
- 思考过程：
  - 首先答案数组的默认值可以设置为1，因为缺失的最小基因值一定是1
  - 其次，基因值是不重复的，可以通过遍历`nums`数组找到基因值为1的唯一节点
  - 找到这个节点后，从该节点开始向下dfs遍历，记录所有该子树中的基因值
  - 因为父节点缺少的基因值一定是大于或者等于子树缺少的基因值的，所以通过找到以节点`i`为根的子树所有基因值之后，节点`i`缺少的基因值也就可以从找到的基因值集合中求出。
  - 那么`i`的子树节点缺少的基因值呢？因为我们从1开始找寻节点，并搜索子树，所以举例，`i`这个基因值为1的节点，以它为根的所有节点，一定是缺少基因值1的（因为基因值唯一），所以它们对应的`ans[j]`一定是等于1的，也就不需要搜索了。
  - 然后，节点`i`的答案确定以后，就可以基于`父节点的答案一定大于等于子节点`这个结论，向上搜索并判断父节点，也即基于父节点作为根节点，向下dfs搜索，并记录基因值，在这个搜索过程中，可以通过之前搜索过的基因值记录进行减枝，然后搜索完成后，该节点缺少的基因值，就可以根据之前一样的计算方式，在搜索过的基因值中寻找缺失的最小值
  - 然后从1开始向上的祖先路径搜索和计算完成后，整棵树的答案也就得到了。为什么呢？就像基因值为`1`的节点的子树所有节点一样，不在该节点所在祖先根节点路劲中的所有节点，他们必定是不包含1这个基因值的，所以他们也和子树节点一样是不需要搜索和判断，直接赋值1就可以了。
- 算法过程：
  - 初始化答案数组`ans`，元素初始化都填充为1
  - 遍历`nums`数组，找到值为1的节点作为起始节点`node`
    - 如果没有找到，那么就不用继续计算了，他们的答案一定都是1
  - 如果找到了起始节点，那么就基于`parents`数组构建树
  - 同时初始化一个最小基因值，该值设置为2
  - 然后从`node`开始进行dfs搜索，搜索过程中将基因值`min`记录到共享set集合`memo`中
  - 每次搜索完一个节点，就判断当前`min`是否在`memo`中，如果存在，就递增`min`，否则就将`min`赋值给`ans[node]`，同时继续搜索`node = parent[node]`
  - 直到`parent[node] == -1`为止，说明祖先路径已经完成了搜索
  - 返回`ans`数组即可
### 代码
```java
class Solution {
  public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
    int n = nums.length, node = -1;
    int[] ans = new int[n];
    Arrays.fill(ans, 1);
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 1) {
        node = i;
        break;
      }
    }

    if (node == -1) {
      return ans;
    }

    List<Integer>[] g = new ArrayList[n];
    Arrays.setAll(g, x -> new ArrayList<>());
    for (int i = 0; i < parents.length; i++) {
      if (parents[i] == -1) {
        continue;
      }

      g[parents[i]].add(i);
    }

    int min = 2;
    Set<Integer> memo = new HashSet<>();
    while (node != -1) {
      dfs(node, nums, g, memo);
      while (memo.contains(min)) {
        min++;
      }
      ans[node] = min;
      node = parents[node];
    }

    return ans;
  }

  private void dfs(int node, int[] nums, List<Integer>[] g, Set<Integer> memo) {
    memo.add(nums[node]);

    for (Integer son : g[node]) {
      if (memo.contains(nums[son])) {
        continue;
      }

      dfs(son, nums, g, memo);
    }
  }
}
```
# [LeetCode_421_数组中两个数的最大异或值](https://leetcode.cn/problems/maximum-xor-of-two-numbers-in-an-array^)
## 解法
### 思路
- 思考过程：
  - 因为`a ^ b = target` => `a ^ b ^ b = target ^ b` => `a = target ^ b`，所以可以通过hash表来存储已有值，然后通过遍历`b`来查找hash表中的`a` 
  - 从高位开始判断，如果在已确定的所有位是最大值的情况下，当前位也是1的话，是否可能通过数组中的某2个数组成，如果可以，那么这个值就是目前为止的最大值，这样依次向低位判断，追加低位的情况，直到得到可能得最大值即可
- 算法过程
  - 初始化几个变量：
    - mask：用来在逐位处理的过程中，将低位转换成0
    - ans：暂存的结果变量，在从高位开始向低位的循环过程中，暂存可能的结果
  - 从高位开始遍历，因为题目设置的是32位的整数，所以可以从第32位开始，这个32位可以通过`1 << 31`来实现，所以循环方法体可以写成`for (int i = 31; i >= 0; i--)`，另外，也可以计算出当前数组中的最大值，用该值的最高位来作为循环的开始，但因为数据范围不大，所以也可以不优化
  - 从高位向低位循环的过程中
    - 初始化一个set集合`memo`，用来记录遍历的所有`nums`数组元素，通过这个hash集合，就能够实现遍历`b`找`a`的逻辑
    - 通过`target = ans | 1 << i`的处理来假定一个可能的结果值
    - 然后将当前遍历的元素`num`通过`mask`将低位遮盖住
    - 通过`memo.contains(target ^ num)`来寻找要找的目标值`a`，也即之前循环并保存的值
    - 如果找到就终止当前这一位的处理，将`ans`替换为`target`
  - 就这样循环完所有位之后，返回`ans`即可
### 代码
```java
class Solution {
    public int findMaximumXOR(int[] nums) {
        int mask = 0, ans = 0;
        for (int i = 31; i >= 0; i--) {
            Set<Integer> memo = new HashSet<>();
            mask |= 1 << i;

            int target = ans | 1 << i;
            
            for (int num : nums) {
                num &= mask;

                if (memo.contains(target ^ num)) {
                    ans = target;
                    break;
                }   

                memo.add(num);
            }
        }

        return ans;
    }
}
```