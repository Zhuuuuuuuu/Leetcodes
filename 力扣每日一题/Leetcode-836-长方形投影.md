---
title: Leetcode-836
date: 2020-3-18 9:52:53
tags: ['Leetcode','数学算法题']
---

# Leecode-836 [Rectangle Overlap](https://leetcode-cn.com/problems/rectangle-overlap/)

## 思路：投影

**题目描述：判断两个矩阵是否有重叠面积**

![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200318094941.jpg)

<!--more-->

**Solution：**

- 矩形重叠要考虑的情况很多，两个矩形的重叠可能有好多种不同的形态。这道题如果用蛮力做的话，很容易遗漏掉某些情况，导致出错。
- 矩形重叠是二维的问题，所以情况很多，比较复杂。为了简化问题，我们可以考虑将二维问题转化为一维问题。既然题目中的矩形都是平行于坐标轴的，我们将矩形投影到坐标轴上：


![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200318094941.jpg)

- 矩阵投影到坐标轴上，就变成了**区间**
- **两个互相重叠的矩形，它们在 x\* 轴和 y\* 轴上投影出的区间也是互相重叠的**。这样，我们就将矩形重叠问题转化成了区间重叠问题。

**区间重叠是一维的问题，比二维问题简单很多。我们可以穷举出两个区间所有可能的 6 种关系：**

![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200318095134.jpg)

- 可以看到的是，区间的6种关系中，不重叠只有两种情况，判断不重叠更简单。
- 假设两个区间是[s1,e1],[s2,e2]的话，那么区间不重叠的两种情况就是`e1 <= s2` 和 `e2 <= s1`。

![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200318095306.jpg)

- 那么区间不重叠的条件就是e1 <= s2 || e2<= s1，**条件取反就是重叠的情况**

## Java

**Solution :**

```java
class Solution {
    public boolean isRectangleOverlap(int[] rec1,int[] rec2){
        // x轴上重叠的条件
        boolean x_overlap = !(rec1[2] <= rec2[0] || rec2[2] <= rec1[0]);
         // y轴上重叠的条件
        boolean y_overlap = !(rec1[3] <= rec2[1] || rec2[3] <= rec1[1]);
        return x_overlap && y_overlap;
    }
}
```



## Python 

**Solution :**

```python
class Solution:
    def isRectangleOverlap(self, rec1: List[int], rec2: List[int]) -> bool:
        x_overlap = not (rec1[2] <= rec2[0] or rec2[2] <= rec1[0])
        y_overlap = not (rec1[3] <= rec2[1] or rec2[3] <= rec1[1])
        return x_overlap and y_overlap
```



