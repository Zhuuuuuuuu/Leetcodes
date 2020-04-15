---
title: Leetcode-409
date: 2020-3-19 15:52:53
tags: ['Leetcode','贪心算法']
---

# Leecode-409 [Longest Palindrome](https://leetcode-cn.com/problems/longest-palindrome/)

## 思路：贪心算法

**题目描述：**

- 给定一个字符串，问其中字母能构造出最长多少的回文串

```
Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.
```



**Solution：**

- 回文串是一个正着读和反着读都一样的字符串。以回文中心为分界线，对于回文串中左侧的字符 `ch`，在右侧对称的位置也会出现同样的字符。例如在字符串 `"abba"` 中，回文中心是 `"ab|ba"` 中竖线的位置，而在字符串 `"abcba"` 中，回文中心是 `"ab(c)ba"` 中的字符 `"c"` 本身。
- **我们可以发现，在一个回文串中，只有最多一个字符出现了奇数次，其余的字符都出现偶数次。**

- 如果有任何一个字符 `ch` 的出现次数 `v` 为奇数（即 `v % 2 == 1`），那么可以将这个字符作为回文中心，注意只能最多有一个字符作为回文中心。

- 在代码中，我们用 `ans` 存储回文串的长度，由于在遍历字符时，`ans` 每次会增加 `v / 2 * 2`，因此 `ans` 一直为偶数。但在发现了第一个出现次数为奇数的字符后，我们将 `ans` 增加 `1`，这样 `ans` 变为奇数，在后面发现其它出现奇数次的字符时，我们就不改变 `ans` 的值了。

<!--more-->

## Java

**Solution :**

```java
class Solution {
    public int longestPalindrome(String s) {
        int[] count = new int[128];
        for (char c: s.toCharArray())
            count[c]++;

        int ans = 0;
        for (int v: count) {
            ans += v / 2 * 2;
            if (v % 2 == 1 && ans % 2 == 0)
                ans++;
        }
        return ans;
    }
}
```



## Python 

**Solution :**

```python
class Solution:
    def longestPalindrome(self, s):
        ans = 0
        count = collections.Counter(s)
        for v in count.values():
            ans += v // 2 * 2
            if ans % 2 == 0 and v % 2 == 1:
                ans += 1
        return ans
```



