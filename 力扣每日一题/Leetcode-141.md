---
title: Leetcode-141
date: 2019-6-12 15:52:53
tags: ['Leetcode','双指针']
---

# Leecode-141 [Linked List Cycle](https://leetcode-cn.com/problems/linked-list-cycle/)

## 思路：快慢指针

**题目描述**:

**判断链表有没有环**



**Solution：**

- 使用双指针，一个指针每次移动一个节点，一个指针每次移动两个节点，如果存在环，那么这两个指针一定会相遇。



<!--more-->



## Java

**Solution :**

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null){
            return false;
        }
        ListNode l1 = head,l2 = head.next;
        while(l1 != null & l2 !=null && l2.next != null){
            if (l1 == l2) return true;
            l1 = l1.next;
            l2 = l2.next.next;
        }
        return false;
    }
}
```



![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200310194816.png)



## Python 

**Solution :**

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def hasCycle(self, head: ListNode) -> bool:
        if not head: return False
        l1 = head
        l2 = head.next
        while l1 and l2 and l2.next:
            if l1 == l2:
                return True
            l1 = l1.next
            l2 = l2.next.next
        return False
```



