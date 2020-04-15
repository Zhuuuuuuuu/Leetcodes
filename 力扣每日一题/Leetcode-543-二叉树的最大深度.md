---
title: Leetcode-543
date: 2020-3-10 15:52:53
tags: ['Leetcode','春招每日一题','深度优先搜索']
---

# Leecode-543 [Diameter of Binary Tree](https://leetcode-cn.com/problems/diameter-of-binary-tree/)

## 思路：递归

**题目描述**：**找出二叉树的最大深度**

```
          1
         / \
        2   3
       / \     
      4   5
```

Return **3**, which is the length of the path [4,2,1,3] or [5,2,1,3].



**Solution：**

很详细的题解如下：

点击链接：[Leetcode题解-543][https://leetcode-cn.com/problems/diameter-of-binary-tree/solution/shi-pin-jie-shi-di-gui-dai-ma-de-yun-xing-guo-chen/]



<!--more-->

## Java

**Solution :**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int res = 0; //全局变量记录最大值
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return max;
    }

    public int dfs(TreeNode root){
        if(root == null) return 0;
        int left  = dfs(root.left);
        int right = dfs(root.right);
        res = Math.max(res,left + right);
        return Math.max(left,right) + 1;
    }
}
```



## Python 

**Solution :**

```python
class Solution:
    def diameterOfBinaryTree(self, root: TreeNode) -> int:
        self.ans = 1
        def depth(root):
            if not root: return 0
            L = depth(root.left)
            R = depth(root.right)
            self.ans = max(self.ans, L + R + 1)
            return max(L, R) + 1
        depth(root)
        return self.ans - 1

```



