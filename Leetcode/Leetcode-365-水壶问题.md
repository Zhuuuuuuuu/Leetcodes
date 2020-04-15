---
title: Leetcode-365
date: 2020-3-20 17:52:53
tags: ['Leetcode','深度优先搜索','春招每日一题','广度优先搜索']
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
- BFS
- 裴蜀定理

<!--more-->



## Java

**Solution :BFS**

```java
import java.util.*;

class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
        if(z == 0)    return true;
        if(x + y < z) return false;
        Queue<Map.Entry<Integer,Integer>> queue = new ArrayDeque<>();
        AbstractMap.SimpleEntry<Integer,Integer> start = new AbstractMap.SimpleEntry<>(0,0);

        //在队列尾部添加一个初始值
        queue.add(start);

        //用来记录访问过的状态
        Set<Map.Entry<Integer,Integer>> visited = new HashSet<>();
        visited.add(start);

        while(!queue.isEmpty()){
            Map.Entry<Integer,Integer> entry = queue.poll(); //删除队列中第一个元素，并返回该元素的值
            int curX = entry.getKey();
            int curY = entry.getValue();
            if(curX == z || curY == z || curX + curY == z) return true;
            //把x筒填满
            if(curX == 0){
                addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(x,curY));
            }
            //把y筒填满
            if (curY == 0) {
                addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(curX, y));
            }
            //把x筒倒空
            if (curY < y) {
                addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(0, curY));
            }
            //把y筒倒空
            if (curX < x) {
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

    private void addIntoQueue(Queue<Map.Entry<Integer, Integer>> queue,Set<Map.Entry<Integer, Integer>> visited,Map.Entry<Integer, Integer> newEntry){
        //如果这个状态之前没有访问过
        if(!visited.contains(newEntry)){
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



## 扩展：ArrayDeque类

```
ArrayDeque类的使用详解
ArrayDeque是Deque接口的一个实现，使用了可变数组，所以没有容量上的限制。
同时，ArrayDeque是线程不安全的，在没有外部同步的情况下，不能再多线程环境下使用。
ArrayDeque是Deque的实现类，可以作为栈来使用，效率高于Stack；
也可以作为队列来使用，效率高于LinkedList。
需要注意的是，ArrayDeque不支持null值。
```

```
1.添加元素
        addFirst(E e)在数组前面添加元素
        addLast(E e)在数组后面添加元素
        offerFirst(E e) 在数组前面添加元素，并返回是否添加成功
        offerLast(E e) 在数组后天添加元素，并返回是否添加成功

  2.删除元素
        removeFirst()删除第一个元素，并返回删除元素的值,如果元素为null，将抛出异常
        pollFirst()删除第一个元素，并返回删除元素的值，如果元素为null，将返回null
        removeLast()删除最后一个元素，并返回删除元素的值，如果为null，将抛出异常
        pollLast()删除最后一个元素，并返回删除元素的值，如果为null，将返回null
        removeFirstOccurrence(Object o) 删除第一次出现的指定元素
        removeLastOccurrence(Object o) 删除最后一次出现的指定元素
   

   3.获取元素
        getFirst() 获取第一个元素,如果没有将抛出异常
        getLast() 获取最后一个元素，如果没有将抛出异常
   

    4.队列操作
        add(E e) 在队列尾部添加一个元素
        offer(E e) 在队列尾部添加一个元素，并返回是否成功
        remove() 删除队列中第一个元素，并返回该元素的值，如果元素为null，将抛出异常(其实底层调用的是removeFirst())
        poll()  删除队列中第一个元素，并返回该元素的值,如果元素为null，将返回null(其实调用的是pollFirst())
        element() 获取第一个元素，如果没有将抛出异常
        peek() 获取第一个元素，如果返回null
      

    5.栈操作
        push(E e) 栈顶添加一个元素
        pop(E e) 移除栈顶元素,如果栈顶没有元素将抛出异常
        

    6.其他
        size() 获取队列中元素个数
        isEmpty() 判断队列是否为空
        iterator() 迭代器，从前向后迭代
        descendingIterator() 迭代器，从后向前迭代
        contain(Object o) 判断队列中是否存在该元素
        toArray() 转成数组
        clear() 清空队列
        clone() 克隆(复制)一个新的队列
```

