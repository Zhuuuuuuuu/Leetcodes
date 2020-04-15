---
title: Leetcode-345
date: 2019-8-12 15:52:53
tags: ['Leetcode']
---

# Leecode-345 [Reverse Vowels of a String](https://leetcode-cn.com/problems/reverse-vowels-of-a-string/)

## 思路：双指针

**题目描述：**

将元音字母进行交换

```
Given s = "leetcode", return "leotcede".
```

<img src='https://camo.githubusercontent.com/6e66ca91f2688beacd6f9dd3ef2774abb53dda73/68747470733a2f2f63732d6e6f7465732d313235363130393739362e636f732e61702d6775616e677a686f752e6d7971636c6f75642e636f6d2f65663235666637632d306636332d343230642d386233302d6561666265656133356431312e676966'>

**Solution：双指针**

- 使用双指针，一个指针从头向尾遍历，一个指针从尾向头遍历，当两个指针都遍历到元音字母时，交换这两个原因字符。
- 为了快速判断一个字符是不是元音字符没我们将全部原因字母添加到集合HashSet中，从而以O(1)的时间复杂度进行该操作



- 时间复杂度：O(N)只需要遍历所有元素一次
- 空间复杂度：O(1)只需要使用两个额外的变量

<!--more-->



## Java

**Solution :**

```java
class Solution {
private final static HashSet<Character> vowels = new HashSet<>(
        Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

public String reverseVowels(String s) {
    if (s == null) return null;
    int i = 0, j = s.length() - 1;
    char[] result = new char[s.length()];
    while (i <= j) {
        char ci = s.charAt(i);
        char cj = s.charAt(j);
        if (!vowels.contains(ci)) {
            result[i++] = ci;
        } else if (!vowels.contains(cj)) {
            result[j--] = cj;
        } else {
            result[i++] = cj;
            result[j--] = ci;
        }
    }
    return new String(result);
    }
}
```



## Python 



**Solution :**

```python
class Solution:
    def reverseVowels(self, s: str) -> str:
        vowels = ['a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U']
        string = list(s)
        i,j = 0,len(s) - 1
        while i <= j:
            if s[i] not in vowels:
                i += 1
            elif s[j] not in vowels:
                j -= 1
            else:
                string[i],string[j]= string[j],string[i]
                i += 1
                j -= 1
        return ''.join(string)

```



