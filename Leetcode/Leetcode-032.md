---
title: Leetcode-032
date: 2019-6-12 15:52:53
tags: Leetcode
---

# Leecode-032 [Longest Valid Parentheses]

## 思路：

**Solution：stack**

- leftmost:  指定最左侧的边界-->初始值为-1
- stack：初始为空
- idx:     每个括号的索引
- 原则：遇到正括号就push,反括号就pop
- max:   计算最大长度（反括号的index减去最上层的正括号的index）
  - [如果遇到反括号，pop之后stack为空，那么长度 = 当前idx - leftmost]
  - [如果遇到反括号，pop之前stack为空，那么leftmost = 当前 idx, 长度就是当前idx-leftmost]

<!--more-->



## Java

**Solution：stack**

```java
class Solution {
    public int longestValidParentheses(String s) {
        if(s == null || s.length() < 2) return 0;
        int n = s.length();
        int max = 0;
        int leftmost = -1;
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0;i < n; i++){
            if(s.charAt(i) == '('){ //遇到正括号就push
                stack.push(i);
            }else { //if  反括号就pop
                if(stack.isEmpty()){ //当前stack为空，更新leftmost
                    leftmost = i;
                }else{
                    int j = stack.pop(); //pop出正括号的index
                    if(stack.isEmpty()) max = Math.max(max,i-leftmost); //pop出来之后为空
                    else max = Math.max(max,i-stack.peek()); //pop出来之后不为空
                }
            }
        }
        return max;
    }
}

```





## Python 



**Solution :同思路python版本**

```python
class Solution:
    def longestValidParentheses(self, s: str) -> int:
        if not s: return 0
        n = len(s)
        max_Parentheses = 0
        leftmost = -1
        stack = []
        for i in range(n):
            if s[i] == '(':
                stack.append(i)
            else: #if )
                if not stack:
                    leftmost = i
                else:
                    j = stack.pop()
                    if not stack: max_Parentheses = max(max_Parentheses,i-leftmost)
                    else: max_Parentheses = max(max_Parentheses,i-stack[-1])
        return max_Parentheses
```



