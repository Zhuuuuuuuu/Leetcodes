---
title: Leetcode-633
date: 2019-7-12 15:52:53
tags: ['Leetcode','双指针']
---

# Leecode-633 [Sum of Square Numbers](https://leetcode-cn.com/problems/sum-of-square-numbers/)

## 思路：双指针

**题目描述：判断一个非负整数是否为两个整数的平方和。**

```
Input: 5
Output: True
Explanation: 1 * 1 + 2 * 2 = 5
```

可以看成是在元素为 0~target 的有序数组中查找两个数，使得这两个数的平方和为 target，

如果能找到，则返回 true，表示 target 是两个整数的平方和



**注意点：本题和 167. Two Sum II - Input array is sorted 类似，只有一个明显区别：一个是和为 target，一个是平方和为 target。本题同样可以使用双指针得到两个数，使其平方和为 target。**



**Solution：双指针**

- 步骤
  - 本题目的关键时右指针的初始化，实现剪枝，从而降低时间复杂度
  - 设右指针为x,左指针固定为0.为了使 0^2 + x^2 的值尽可能接近 target,我们可以将 x 取为 sqrt(target)。



- 复杂度分析：
  - 因为最多只需要遍历一次0~sqrt(target),所以时间复杂度为o(sqrt(target))
  - 空间复杂度为O（1）,因为使用了两个变量



<!--more-->

## Java

**Solution : 双指针**

```java
class Solution {
    public boolean judgeSquareSum(int c) {
        if (c < 0) return false;
        int i = 0;
        int j = (int)Math.sqrt(c); //这里一定要强转
        while (i <= j){
            int powSum = i*i + j*j;
            if (c == powSum) return true;
            if (c < powSum)  j--;
            if (c > powSum)  i++;
        }
        return false;
    }
}
```



## Python 

**Solution : 双指针**

```python
import math
class Solution:
    def judgeSquareSum(self, c: int) -> bool:
        if c < 0: return False
        i = 0 
        j = int(math.sqrt(c)) #这里一定要强转成int
        while i <= j:
            powSum = i*i + j*j
            if powSum == c:
                return True
            elif powSum > c:
                j -= 1
            elif powSum < c:
                i += 1
        return False
```



