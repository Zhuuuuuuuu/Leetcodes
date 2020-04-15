---
title: Leetcode-020
date: 2020-4-1 15:52:53
tags: Leetcode
---

# Leecode-020 [Valid Parentheses](https://leetcode-cn.com/problems/valid-parentheses/)

## 思路：辅助栈

**题目描述**

- 如果有一个‘(’对应就有一个‘）’

- 如果有一个‘{’对应就有一个‘}’
- 如果有一个‘[’对应就有一个‘]’

```
Example 1:

Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true
```

<!--more-->

**Solution：stack**

- 如果字符串为空，直接返回true

- 先遍历一遍字符串

  - 如果有‘(’ 就把 ‘）’  push进栈
  - 如果有‘{’ 就把 ‘}’    push进栈
  - 如果有‘[’ 就把 ‘]’     push进栈
  - 如果遇到一个闭括号，那么我们检查栈顶的元素。
    - 如果栈顶的元素是一个相同类型的左括号，那么我们将它从栈中弹出并继续处理
    - 否则，表达是无效

  - 如果到最后剩下的栈中仍然有元素，那么表达式无效



## 复杂度分析

**时间复杂度：O(n)，**因为我们一次只遍历给定的字符串中的一个字符并在栈上进行 O(1)的推入和弹出操作。
**空间复杂度：O(n)，**当我们将所有的开括号都推到栈上时以及在最糟糕的情况下，最终要把所有括号推到栈上。例如 ((((((((((。

## Java

**Solution :**

```java
class Solution {
    public boolean isValid(String s) {
        if(s.isEmpty()) return true;
        Stack<Character> stack = new Stack<Character>();
        for(char c:s.toCharArray()){
            if(c == '(') 
                stack.push(')');
            else if(c == '{') 
                stack.push('}');
            else if(c == '[') 
                stack.push(']');
            //如果和栈顶元素不相同
            else if(stack.isEmpty()||c!=stack.pop())  
                return false;
        }
        //如果最后栈中无元素
        if(stack.empty())
            return true;
        return false;
    }
}
```

## Python 



**Solution :**

```python
class Solution:
    def isValid(self, s: str) -> bool:
        if not s:
            return True
        stack = []
        for c in s:
            if c == '(':
                stack.append(')')
            elif c == '{':
                stack.append('}')
            elif c == '[':
                stack.append(']')
            # 如果和栈顶元素类型不相同
            elif(stack==[] or c!=stack.pop()):
                return False
        # 如果最后栈中还存在元素
        if stack == []:
            return True
        return False
```



