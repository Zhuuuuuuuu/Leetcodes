---
title: Leetcode-1111-有效括号的长度
date: 2020-04-01 15:52:53
tags: ['Leetcode','春招每日一题']
---

# Leecode-1111 [Maximum Nesting Depth of Two Valid Parentheses Strings](https://leetcode-cn.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/)

## **思路：辅助栈**

**题目描述**

有效括号的意思：一句话概括就是每个左括号都可以找到右括号与之配置。

题面中的 depth 其实就是栈的最大深度。“你需要从中选出任意一组有效括号字符串 A 和 B，使 max(depth(A), depth(B)) 的可能取值最小”。这句话的意思就是想要A字符串和B字符串的depth尽可能的接近。（**原因：每个左括号都有一个对应的深度，这个左括号要么是A的，要么是B的。所以只需要按照奇数偶数分配给A和B就行了）**

## **复杂度分析**

时间复杂度很明显是 O(n)的，空间复杂度也是 O(n)（如果算返回的变量的话）。

<!--more-->

**Solution：栈**

- 每个左括号都有一个对应的深度，这个左括号要么是A的，要么是B的
- 所以只需要按照奇数偶数分配给A和B就行了

## Java

**Solution :**

```java
public class Solution {

    public int[] maxDepthAfterSplit(String seq) {

        int len = seq.length();
        int[] res = new int[len];

        // 嵌套深度，栈的当前高度
        int depth = 0;

        // 在 Java 里，seq.charAt(i) 函数会做下标越界检查，
        // 因此先转换成字符数组是常见的做法
        char[] charArray = seq.toCharArray();

        for (int i = 0; i < len; i++) {
            // 遍历到左括号，连续括号个数加 1，
            if (charArray[i] == '(') {
                depth++;
                // % 2 也可以写成 & 1，为了保证语义清楚，写 % 2
                res[i] = depth % 2;
            } else {
                // 遍历到右括号，与当前栈顶左括号分在一组，因此先取模，再 --
                // 这一步希望大家多体会，很有意思
                res[i] = depth % 2;
                depth--;
            }
        }
        return res;
    }
}

```



## Python 



**Solution :**

```python
class Solution:
    def maxDepthAfterSplit(self, seq: str) -> List[int]:
        ans = []
        idx = 0
        for c in seq:
            if c == '(':
                ans.append(idx & 1)
                idx += 1
            else:
            # if c == ')':
                ans.append((idx + 1)&1)
                idx += 1
        return ans
```



