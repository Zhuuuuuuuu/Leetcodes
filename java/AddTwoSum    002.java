/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;
        while (l1 != null && l2 != null){
            int sum = l1.val + l2.val + carry;
            int val = sum % 10;
            carry = sum / 10;
            ListNode newnode = new ListNode(val);
            current.next = newnode;
            current = current.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1 != null){
            int sum = l1.val + carry;
            int val = sum % 10;
            carry = sum / 10;
            current.next = new ListNode(val);
            current = current.next;
            l1 = l1.next;
        }
        while(l2 != null){
            int sum = l2.val + carry;
            int val = sum % 10;
            carry = sum / 10;
            current.next = new ListNode(val);
            current = current.next;
            l2 = l2.next;
        }

        if(1 == carry) current.next = new ListNode(carry);
        return dummy.next;
    }
}