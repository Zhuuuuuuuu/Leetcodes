---
title: Leetcode-876
date: 2020-3-22 15:52:53
tags: ['Leetcode','快慢指针']
---

# Leecode-876 [Middle of the Linked List](https://leetcode-cn.com/problems/middle-of-the-linked-list/)

## 思路：快慢指针

**题目描述**

```
Input: [1,2,3,4,5,6]
Output: 4
Since the list has two middle nodes with values 3 and 4, we return the second one.
```

```
Input: [1,2,3,4,5]
Output: 3 (Serialization: [3,4,5])
The returned node has value 3.  (The judge's serialization of this node is [3,4,5]).
Note that we returned a ListNode object ans, such that:
ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, and ans.next.next.next = NULL.
```

<!--more-->

**Solution：快慢指针**

![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200326212826.png)

![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200326212837.png)

## Java

**Solution :**

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode middleNode(ListNode head) {
        if(head == null) return null;
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
```

## Python 



**Solution :**

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def middleNode(self, head: ListNode) -> ListNode:
        if head is None:
            return None
        slow = head
        fast = head
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
        return slow
```



