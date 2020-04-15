---
title: Leetcode-008-字符串转数字(atoi)
date: 2020-04-03 19:52:53
tags: Leetcode
---

# Leecode-008 [String to Integer (atoi)](https://leetcode-cn.com/problems/string-to-integer-atoi/)

## 思路：一次遍历

**题目描述：**

**Example 1:**

```
Input: "42"
Output: 42
```

**Example 2:**

```
Input: "   -42"
Output: -42
```

**Example 3:**

```
Input: "4193 with words"
Output: 4193
```

**Example 4:**

```
Input: "words and 987"
Output: 0
```

**Example 5:**

```
Input: "-91283472332"
Output: -2147483648
```

<!--more-->

**Solution：**

以下三点需要考虑：

- 数字前面有空格
- 正负号判断
- 越界处理

- 单独字符串转数字   ans = ans * 10 + digit

## Java

**Solution :**

```java
class Solution {
    public int myAtoi(String str) {
        char[] chars = str.toCharArray();
        int n   = chars.length;
        int idx = 0;

        // 这里' '中是一个空格
        while(idx < n && chars[idx] == ' '){
            //去掉前面的空格
            idx++;
        }

        if(idx == n){
            //如果去掉空格直接到达末尾
            return 0;
        }

        // 正负标识符
        boolean flag = false;
        // 遇到负号
        if(chars[idx] == '-'){
           flag = true;
           idx++; 
        } else if(chars[idx] == '+'){
            // 遇到正号
            idx++;
        } else if (!Character.isDigit(chars[idx])) {
            // 其他符号
            return 0;
        }

        int ans = 0;
        // 一直遍历到最后并且字符是数字的话
        while(idx < n && Character.isDigit(chars[idx])){
            int digit = chars[idx] - '0';
            // 如果越界了（MAX和MIN判断同样）
            if(ans > (Integer.MAX_VALUE - digit)/10){
                // 本来应该是 ans * 10 + digit > Integer.MAX_VALUE
                // 但是 *10 和 + digit 都有可能越界，所有都移动到右边去就可以了。
                return flag?Integer.MIN_VALUE:Integer.MAX_VALUE;
            }
            ans = ans * 10 + digit;
            idx++;
        }
        return flag?-ans:ans;
    }
}
```

## Python 



**Solution :**

```python

```



