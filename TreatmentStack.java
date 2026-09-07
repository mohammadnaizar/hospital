package hospital;

/**
 * Custom Stack (LIFO) implementation used to store completed
 * treatment records.
 *
 * Implemented manually using a singly linked structure (top pointer)
 * rather than java.util.Stack, since the data structure itself is
 * being assessed.
 *
 * Supports:
 *  - push()             : add a completed treatment record
 *  - pop()               : remove the most recently completed record
 *  - displayHistory() : show all treatment records, most recent first
 *  - isEmpty()          : check whether the stack is empty
 */
public class TreatmentStack {

    private static class StackNode {
        TreatmentRecord record;
        StackNode next;

        StackNode(TreatmentRecord record) {
            this.record = record;
            this.next = null;
        }
    }

    private StackNode top;
    private int size;

    public TreatmentStack() {
        top = null;
        size = 0;
    }

    /** Push a newly completed treatment record onto the stack. */
    public void push(TreatmentRecord record) {
        StackNode newNode = new StackNode(record);
        newNode.next = top;
        top = newNode;
        size++;
    }

    /** Pop and return the most recently completed treatment record. */
    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("Treatment history is empty. Nothing to remove.");
            return null;
        }
        TreatmentRecord record = top.record;
        top = top.next;
        size--;
        return record;
    }

    /** View the most recent record without removing it. */
    public TreatmentRecord peek() {
        if (isEmpty()) {
            return null;
        }
        return top.record;
    }

    /** Display all treatment records, most recent (top) first. */
    public void displayHistory() {
        if (isEmpty()) {
            System.out.println("No treatment records available.");
            return;
        }
        StackNode current = top;
        int count = 1;
        while (current != null) {
            System.out.println(count + ". " + current.record.toString());
            current = current.next;
            count++;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }
}
