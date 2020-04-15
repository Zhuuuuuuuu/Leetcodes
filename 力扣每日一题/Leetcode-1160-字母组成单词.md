---
title: Leetcode-1160
date: 2020-3-17 15:52:53
tags: ['Leetcode','哈希表']
---

# Leecode-1160 [Find Words That Can Be Formed by Characters](https://leetcode-cn.com/problems/find-words-that-can-be-formed-by-characters/)

## 思路：哈希表

**题目描述：**

- 给定一个字符串，看这个字符串中的字母能否组成数组中的字符串
- 如果能，返回能组成字符串的长度

示例1：

```
Input: words = ["cat","bt","hat","tree"], chars = "atach"
Output: 6
Explanation: 
The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.
```

示例2：

```
Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
Output: 10
Explanation: 
The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.
```



**Solution：**

![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200318091832.gif)

- 直接统计字母表chars中每个字母出现的次数
- 同时统计words中每个单词中字母出现的次数
- 如果该单词中的每个字母出现的次数都小于等于词汇表中对应字母出现的次数，就将该单词长度加入到答案中。

<!--more-->



## Java

**Solution :**

Java 的 HashMap。但是我们注意到题目有一个额外的条件：所有字符串中都仅包含小写英文字母。这意味着我们可以用一个长度为 26 的数组来进行计数。这也是很多字符串计数问题的常用技巧。

```java
class Solution {
    public int countCharacters(String[] words, String chars) {
          int[] chars_count = count(chars);  //统计字母表中出现的次数
          int res = 0;
          for(String word:words){
              int[] word_count = count(word); //统计单词中字母出现次数
              if(contains(chars_count,word_count)){
                  res += word.length();
              }
          }
          return res;
    }


    //检查字母表的字母出现次数是否覆盖单词的字母出现次数
    boolean contains(int[] chars_count,int[] word_count){
        for(int i = 0; i < 26; i++){  //26个字母一一对比
            if(chars_count[i] < word_count[i]){
                return false;
            }
        }
        return true;
    }

   
    //统计26个字母出现的次数
    int[] count(String word){
        int[] counter = new int[26];
        for(int i = 0;i < word.length();i++){
            char c = word.charAt(i);
            counter[c-'a']++;  //counter[c-'a']+=1;
        }
        return counter;
    }
}
```



## Python 

**Solution :**

Python 中有个 `Counter` 类就是专门用来计数的。

```python
class Solution:
    def countCharacters(self, words: List[str], chars: str) -> int:
        chars_count = collections.Counter(chars)  #统计chars中字母出现次数
        ans = 0
        for word in words:  #遍历每个单词
            word_count = collections.Counter(word)  #统计单词中字母出现次数
            for c in word_count:      #遍历每个单词的字母
                if chars_count[c] < word_count[c]:
                    break
            else:
                ans += len(word)
        return ans
```



