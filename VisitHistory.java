package hospital;

/**
 * Singly Linked List implementation used to store a patient's
 * previous hospital visits.
 *
 * Supports:
 *  - addVisit()      : add a new visit to the end of the list
 *  - removeVisit()    : remove a visit by Visit ID
 *  - searchVisit()     : search for a visit by Visit ID
 *  - displayVisits()  : print all visits in order
 */
public class VisitHistory {

    /** Internal node of the singly linked list. */
    private static class VisitNode {
        int visitId;
        String visitDate;
        String doctorName;
        String diagnosis;
        String treatment;
        VisitNode next;

        VisitNode(int visitId, String visitDate, String doctorName, String diagnosis, String treatment) {
            this.visitId = visitId;
            this.visitDate = visitDate;
            this.doctorName = doctorName;
            this.diagnosis = diagnosis;
            this.treatment = treatment;
            this.next = null;
        }
    }

    private VisitNode head;
    private int size;

    public VisitHistory() {
        head = null;
        size = 0;
    }

    /** Add a new visit at the end of the list. O(n) */
    public void addVisit(int visitId, String visitDate, String doctorName, String diagnosis, String treatment) {
        VisitNode newNode = new VisitNode(visitId, visitDate, doctorName, diagnosis, treatment);
        if (head == null) {
            head = newNode;
        } else {
            VisitNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /** Remove a visit by its Visit ID. Returns true if removed. */
    public boolean removeVisit(int visitId) {
        if (head == null) {
            return false;
        }
        if (head.visitId == visitId) {
            head = head.next;
            size--;
            return true;
        }
        VisitNode current = head;
        while (current.next != null && current.next.visitId != visitId) {
            current = current.next;
        }
        if (current.next == null) {
            return false; // not found
        }
        current.next = current.next.next;
        size--;
        return true;
    }

    /** Search for a visit by Visit ID. Returns a formatted string or null if not found. */
    public String searchVisit(int visitId) {
        VisitNode current = head;
        while (current != null) {
            if (current.visitId == visitId) {
                return formatVisit(current);
            }
            current = current.next;
        }
        return null;
    }

    /** Display all visits in the list, in order. */
    public void displayVisits() {
        if (head == null) {
            System.out.println("   No visit history recorded.");
            return;
        }
        VisitNode current = head;
        int count = 1;
        while (current != null) {
            System.out.println("   " + count + ") " + formatVisit(current));
            current = current.next;
            count++;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    private String formatVisit(VisitNode node) {
        return "Visit ID:" + node.visitId +
                " | Date:" + node.visitDate +
                " | Doctor:" + node.doctorName +
                " | Diagnosis:" + node.diagnosis +
                " | Treatment:" + node.treatment;
    }
}
