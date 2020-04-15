---
title: Leetcode-042-接住雨水的深度
date: 2020-04-04 12:52:53
tags: ['Leetcode','春招每日一题']
---

# Leecode-042 [Trapping Rain Water](https://leetcode-cn.com/problems/trapping-rain-water/)

## 思路：单调栈

**题目描述**

求一个数组 组成的高度 能接到雨水的面积

![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200405214055.gif)

<!--more-->

**Solution：**

- 看gif图可以发现，遍历到某些柱子的时候，会由于和之前的某个柱子形成凹形的坑，接住雨水。

- 这道题目可以用单调栈来做。**单调栈就是比普通的栈多一个性质，即维护一个栈内元素单调。**

- 比如当前某个单调递减的栈元素从栈底到栈顶分别是：[10,9,8,3,2] 如果要入栈元素是5，需要把栈顶元素pop出去，直到满足单调递减为止，再入栈元素5，变成[10,9,8] ，然后变为[10,9,8,5] 



下面演示一下`[4, 3, 1, 0, 1, 2, 4]`是怎么接雨水的，下图是最终的接雨水效果，蓝色部分是雨水。

![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200405214422.png)

**图示最上方是每个柱子的高度，左侧是单调栈的元素。0是栈顶，图中有红色边框的柱子是存在单调栈里面的元素。**

遍历到图示中箭头所指向的位置时，栈内元素是`[4, 3, 1, 0]`。由于当前的柱体的`1`大于栈顶元素`0`，那就可以接住雨水。

接住雨水的量的高度是栈顶元素和左右两边形成的高度差min,宽度是1。



![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200405214532.png)





**到下一个柱体高度是2的时候，栈内元素是[4,3,1,1]。由于当柱体的2大于栈顶元素1,那就可以接住雨水，由于栈顶元素有相等的情况，所以把1全部pop出去，变成[4,3]。此时最后一个pop出去的是1**

**高度是此时栈顶元素和当前2柱体高度的min再减去当前的1,宽度是1那个数字和当前2柱体所在位置的差值，可以计算出来此次接住雨水是1*3。**

![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200405215058.png)

**在下一个柱体高度为4时，栈内元素是[4,3,2]，先把2 给pop出来，栈顶元素3所在位置和当前的4可以接住雨水,与水量是1*4.**

![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200405215405.png)

**但是由于栈顶元素3仍然小于当前元素4,再pop出来3。栈顶元素4所在位置和当前的4可以接住雨水，雨水量是1*5。**



### 复杂度

这样每个部分的雨水量都可以算出来，**加在一起就可以了。**

**由于每个柱体最多入栈出栈一次，所以时间复杂度是 O(n)**

## Java

**Solution :**

```java
class Solution {
    public int trap(int[] height) {
        if(height == null){
            return 0;
        }

        Stack<Integer> stack = new Stack();
        int ans = 0;
        for (int i = 0; i < height.length; i++){
            while(!stack.isEmpty() && height[stack.peek()] < height[i]){
                int curIdx = stack.pop();
                // 如果栈顶元素一直相等，那么全部pop出去,只留第一个
                while(!stack.isEmpty() && height[stack.peek()] == height[curIdx]){
                    stack.pop();
                }

                if(!stack.isEmpty()){
                    int stackTop = stack.peek();
                    // stackTop 此时指的是的是此次接住的雨水左边界的位置。
                    // 右边界是当前的柱体，即i
                    // Math.min(height[stackTop],height[i]) 是左右柱子高度min,减去height[curIdx]就是接住雨水的高度
                    // i - stackTop - 1 就是接住雨水的宽度
                    ans += (Math.min(height[stackTop],height[i]) - height[curIdx]) * (i - stackTop - 1);
                }
            }
            stack.add(i);
        }
        return ans;
    }
}
```



## Python 



**Solution :**

```python

```



