---
title: Leetcode-365
date: 2020-3-20 17:52:53
tags: ['Leetcode','深度优先搜索','春招每日一题']
---

# Leecode-365 [Water and Jug Problem](https://leetcode-cn.com/problems/water-and-jug-problem/)

## 思路：DFS/裴蜀定理

**题目描述**

有两个容量分别为 *x*升 和 *y*升 的水壶以及无限多的水。判断能否通过使用这两个水壶，从而可以得到恰好 *z*升 的水？

只允许以下操作：

- 装满任意一个水壶
- 清空任意一个水壶
- 从一个水壶向另外一个水壶倒水，直至倒满或者倒空



**Solution：**

- DFS
- 裴蜀定理

![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200326215210.jpg)

<!--more-->

## Java

**Solution :**

```java
import java.util.*;

class Solution {
  public boolean canMeasureWater(int x, int y, int z) {
    if (z == 0) {
      return true;
    }
    if (x + y < z) {
      return false;
    }
    Queue<Map.Entry<Integer, Integer>> queue = new ArrayDeque<>();
    AbstractMap.SimpleEntry<Integer, Integer> start = new AbstractMap.SimpleEntry<>(0, 0);
    queue.add(start);
    Set<Map.Entry<Integer, Integer>> visited = new HashSet<>();
    visited.add(start);
    while (!queue.isEmpty()) {
      Map.Entry<Integer, Integer> entry = queue.poll();
      int curX = entry.getKey();
      int curY = entry.getValue();
      if (curX == z || curY == z || curX + curY == z) {
        return true;
      }
      if (curX == 0) {
        // 把第一个桶填满
        addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(x, curY));
      }
      if (curY == 0) {
        // 把第二个桶填满
        addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(curX, y));
      }
      if (curY < y) {
        // 把第一个桶倒空
        addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(0, curY));
      }
      if (curX < x) {
        // 把第二个桶倒空
        addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(curX, 0));
      }

      // y - curY是第二个桶还可以再加的水的升数，但是最多只能加curX升水。
      int moveSize = Math.min(curX, y - curY);
      // 把第一个桶里的curX升水倒到第二个桶里去。
      addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(curX - moveSize, curY + moveSize));
      // 反过来同理，x - curX是第一个桶还可以再加的升数，但是最多只能加curY升水。
      moveSize = Math.min(curY, x - curX);
      // 把第一个桶里的curX升水倒到第二个桶里去。
      addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(curX + moveSize, curY - moveSize));
    }
    return false;
  }

  private void addIntoQueue(Queue<Map.Entry<Integer, Integer>> queue,
                            Set<Map.Entry<Integer, Integer>> visited,
                            Map.Entry<Integer, Integer> newEntry) {
    if (!visited.contains(newEntry)) {
      visited.add(newEntry);
      queue.add(newEntry);
    }
  }
}
```



## Python 



**Solution : DFS**

```python
class Solution:
    def canMeasureWater(self, x: int, y: int, z: int) -> bool:
        stack = [(0,0)]
        self.seen = set()
        while stack:
            remain_x , remain_y = stack.pop()
            #如果找到了z
            if remain_x == z or remain_y == z or remain_x + remain_y == z:
                return True
            #如果之前存过了，就跳出本次循环
            if(remain_x,remain_y) in self.seen:
                continue
            #把本次的x,y中的水记录下来
            self.seen.add((remain_x,remain_y))
            #把x壶倒满
            stack.append((x,remain_y))
            #把y壶倒满
            stack.append((remain_x,y))
            #把x壶倒空
            stack.append((0,remain_y))
            #把y壶倒空
            stack.append((remain_x,0))
            #把x壶中水倒入给y,直至y壶倒满或者x壶倒空
            stack.append((remain_x - min(remain_x,y - remain_y),remain_y + min(remain_x,y - remain_y)))
            #把y壶中水倒入给x，直至x壶倒满或者y壶倒空
            stack.append((remain_x + min(x - remain_x,remain_y),remain_y - min(x - remain_x,remain_y)))
        return False
```

​	首先对题目进行建模。观察题目可知，在任意一个时刻，此问题的状态可以由两个数字决定：X 壶中的水量，以及 Y 壶中的水量。

​	在任意一个时刻，我们可以且仅可以采取以下几种操作：

**把 X 壶的水灌进 Y 壶，直至灌满或倒空；**
**把 Y 壶的水灌进 X 壶，直至灌满或倒空；**
**把 X 壶灌满；**
**把 Y 壶灌满；**
**把 X 壶倒空；**
**把 Y 壶倒空。**

​	因此，本题可以使用深度优先搜索来解决。搜索中的每一步以 remain_x, remain_y 作为状态，即表示 X 壶和 Y 壶中的水量。在每一步搜索时，我们会依次尝试所有的操作，递归地搜索下去。这可能会导致我们陷入无止境的递归，**因此我们还需要使用一个哈希结合（HashSet）存储所有已经搜索过的 remain_x, remain_y 状态，保证每个状态至多只被搜索一次。**

​	**由于深度优先搜索导致的递归远远超过了 Python 的默认递归层数（可以使用 sys 库更改递归层数，但不推荐这么做），代码使用栈来模拟递归，避免了真正使用递归而导致的问题。**



**Solution : 裴蜀定理**

[裴蜀定理](https://baike.baidu.com/item/裴蜀定理/5186593?fromtitle=贝祖定理&fromid=5185441)

每次操作只会让桶里的水总量增加 `x`，增加 `y`，减少 `x`，或者减少 `y`。

你可能认为这有问题：如果往一个不满的桶里放水，或者把它排空呢？那变化量不就不是 `x` 或者 `y` 了吗？接下来来解释这一点：

- 首先要清楚，在题目所给的操作下，两个桶不可能同时有水且不满。因为观察所有题目中的操作，操作的结果都至少有一个桶是空的或者满的；

- 其次，对一个不满的桶加水是没有意义的。因为如果另一个桶是空的，那么这个操作的结果等价于直接从初始状态给这个桶加满水；而如果另一个桶是满的，那么这个操作的结果等价于从初始状态分别给两个桶加满；

- 再次，把一个不满的桶里面的水倒掉是没有意义的。因为如果另一个桶是空的，那么这个操作的结果等价于回到初始状态；而如果另一个桶是满的，那么这个操作的结果等价于从初始状态直接给另一个桶倒满。

**因此，我们可以认为每次操作只会给水的总量带来 x 或者 y 的变化量。因此我们的目标可以改写成：找到一对整数 a, b，使得**

​													**ax+by=z**

**而贝祖定理告诉我们，ax+by=z 有解当且仅当 z是 x,y 的最大公约数的倍数。因此我们只需要找到 x,y 的最大公约数并判断 z 是否是它的倍数即可。**

```python
class Solution:
    def canMeasureWater(self, x: int, y: int, z: int) -> bool:
        if x + y < z:
            return False
        if x == 0 or y == 0:
            return z == 0 or x + y == z
        return z % math.gcd(x, y) == 0

```

