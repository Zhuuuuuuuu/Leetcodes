---
title: Leetcode-151-翻转字符串里的单词
date: 2020-04-10 09:52:53
tags: ['Leetcode','双指针']
---

# Leetcode-151-[Reverse Words in a String](https://leetcode-cn.com/problems/reverse-words-in-a-string/)

## 思路：

**题目描述：**

翻转字符串里面的单词

```
Example 1:

Input: "the sky is blue"
Output: "blue is sky the"

Example 2:

Input: "  hello world!  "
Output: "world! hello"
Explanation: Your reversed string should not contain leading or trailing spaces.


Example 3:

Input: "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.

```

<!--more-->

**Solution1：双指针**

分为以下三部分：

- 翻转整个数组
- 翻转每个单词
- 去除单词前后空格



**Solution2:使用自带的库**

## Java

**Solution :**

```java
class Solution {
    public String reverseWords(String s) {
        if(s == null) return null;
        char[] arr_s = s.toCharArray();
        int n = arr_s.length;

        // 翻转整个数组
        reverse(arr_s,0,n-1);
        // 翻转每个单词
        word_reverse(arr_s,n);
        // 去除多余空格
        return clean_space(arr_s,n);
    }

    private void reverse(char[] arr_s,int i,int j){
        while (i < j){
            // 交换前后单词顺序
            char t = arr_s[i];
            arr_s[i++] = arr_s[j];
            arr_s[j--] = t;
        }
    }

    private void word_reverse(char[] arr_s, int n) {
        int i = 0;
        int j = 0;
        while (j < n) {
            // 找到第一个首字母(如果这个单词是以空格开头)
            while (i < n && arr_s[i] == ' ') {
                i++;
            }
            j = i;
            // 一直遍历到这个单词末位置
            while (j < n && arr_s[j] != ' ') {
                j++;
            }
            reverse(arr_s, i, j - 1);
            i = j;  // 刷新i的位置，继续遍历
        }
    }

    // 双指针去掉空格
    private String clean_space(char[] arr_s,int n){
        int i = 0;
        int j = 0;
        while(j < n){
            // 去掉前面的空格
            while (j < n && arr_s[j] == ' ') j++;
            // 排除单词的长度
            while (j < n && arr_s[j] != ' ') arr_s[i++] = arr_s[j++];
            // 单词结束还需要去除后面空格
            while (j < n && arr_s[j] == ' ') j++;
            if (j < n) arr_s[i++] = ' ';      
        }
        return new String(arr_s).substring(0,i);
    }
}
```



```java
class Solution{
    public String reverseWords(String s){
        // 去掉空格（运用正则表达式）
        String[] words = s.trim().split(" +");
        // 翻转字符
        Collections.reverse(Arrays.asList(words));
        // 返回字符串结果
        return String.join(" ",words);
    }
}
```



## Python 



**Solution :**

```python

```



