//Definition for singly-linked list.
public class ReverseLinkList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode reverseList(ListNode head) {
        if(head == null)
            return head;

        ListNode next = head.next;
        ListNode prev = head;
        prev.next = null;
        while(next != null){
            head = next;
            next = head.next;
            head.next = prev;
            prev = head;
        }
        return head;
    }
}

