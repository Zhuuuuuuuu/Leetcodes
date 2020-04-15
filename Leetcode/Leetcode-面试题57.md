---
title: Leetcode-面试题57
date: 2020-3-7 15:52:53
tags: ['Leetcode','春招每日一题','滑动窗口']
---

# Leecode-[面试题57 - II. 和为s的连续正数序列](https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/)

## 思路：滑动窗口

**题目描述**

输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。

序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。

示例1：

```
输入：target = 9
输出：[[2,3,4],[4,5]]
```

示例2：

```
输入：target = 15
输出：[[1,2,3,4,5],[4,5,6],[7,8]]
```

限制条件：

`1 <= target <= 10^5`



**Solution：**

- <img src = "https://pic.leetcode-cn.com/af4821c41d5b093e6a41ad5602208f9e7057cc1b002cd0053de71bc9a0e35b12.jpg">

<!--more-->



- 滑动窗口可以看成**数组中框起来的一个部分**。在一些数组类题目中，我们可以用滑动窗口来观察可能的候选结果。当滑动窗口从数组的左边滑到了右边，我们就可以从所有的候选结果中找到最优的结果。
- 对于这道题来说，数组就是正整数序列[1,2,3,…,*n*]。我们设**滑动窗口的左边界为i,右边界为j,则滑动窗口框起来的是一个左闭右开的区间[i,j)**。
- 注意：为了编程的方便，滑动窗口一般表示成一个左闭右开的区间。在一开始，i=1,j=1。**滑动窗口为了序列的最左侧，窗口大小为0**。

- **滑动窗口重要的性质是：窗口的左边界和右边界永远只能向右移动**。这是为了保证滑动窗口 的时间复杂度为O(n)。如果左右边界向左移动的话，这叫做“回溯法”。算法的时间复杂度就不止为O(n)了。



## 如何用滑动窗口解这道题

要用滑动窗口解这道题，有两个问题时必须要回答的

- 第一个问题，窗口何时扩大，何时缩小？
- 第二个问题，滑动窗口能找到全部的解吗？

**对于第一个问题，回答非常简单：**

- 当窗口的和小于target的时候，窗口的和需要增加，所以需要扩大窗口，窗口的右边界向右移动
- 当窗口的和大于target的时候，窗口的和需要减少，所以徐要减少窗口，窗口的左边界向右移动
- 当窗口的和恰好的等于target的时候，我们需要记录此时的结果，设此时的窗口大小为【i,j），那么我们已经找到了一个i开头的序列，也是唯一一个i开头的序列，接下来只需要寻找i+1开头的序列，所以窗口的左边界向右移动。

**对于第二个问题，我们可以稍微简单地证明一下：**

<img src = "https://pic.leetcode-cn.com/728c705889a672d5a85709cb3fd157216bb1a41dc377dcc125818d9e18b8dd55.jpg">

我们一开始要找的是 1 开头的序列，只要窗口的和小于 target，窗口的右边界会一直向右移动。假设 1+2+⋯+8 小于 target，再加上一个 9 之后， 发现 1+2+⋯+8+9 又大于 target 了。这说明 1 开头的序列找不到解。此时滑动窗口的最右元素是 9。

接下来，我们需要找 2 开头的序列，我们发现，2 +..+ 8  <1+2+⋯+8<target。这说明 2 开头的序列至少要加到 9。那么，我们只需要把原先 1~9 的滑动窗口的左边界向右移动，变成 2~9 的滑动窗口，然后继续寻找。而右边界完全不需要向左移动。

以此类推，滑动窗口的左右边界都不需要向左移动，所以这道题用滑动窗口一定可以得到所有的解。时间复杂度是 *O*(*n*)。

## Java

**Solution :**

```java
public int[][] findContinuousSequence(int target) {
    int i = 1; // 滑动窗口的左边界
    int j = 1; // 滑动窗口的右边界
    int sum = 0; // 滑动窗口中数字的和
    List<int[]> res = new ArrayList<>();

    while (i <= target / 2) {
        if (sum < target) {
            // 右边界向右移动
            sum += j;
            j++;
        } else if (sum > target) {
            // 左边界向右移动
            sum -= i;
            i++;
        } else {
            // 记录结果
            int[] arr = new int[j-i];
            for (int k = i; k < j; k++) {
                arr[k-i] = k;
            }
            res.add(arr);
            // 左边界向右移动
            sum -= i;
            i++;
        }
    }

    return (int[][])res.toArray(new int[0][]); 
}
```



![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200309111027.png)



## Python 

**Solution :**

```python
def findContinuousSequence(self, target: int) -> List[List[int]]:
    i = 1 # 滑动窗口的左边界
    j = 1 # 滑动窗口的右边界
    sum = 0 # 滑动窗口中数字的和
    res = []

    while i <= target // 2:
        if sum < target:
            # 右边界向右移动
            sum += j
            j += 1
        elif sum > target:
            # 左边界向右移动
            sum -= i
            i += 1
        else:
            # 记录结果
            arr = list(range(i, j))
            res.append(arr)
            # 左边界向右移动
            sum -= i
            i += 1

    return res

```



![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200309113236.png)