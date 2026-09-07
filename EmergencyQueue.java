package hospital;

/**
 * Custom Queue (FIFO) implementation used to manage patients
 * waiting in the Emergency Unit.
 *
 * Implemented manually using a singly linked structure (front/rear
 * pointers) rather than java.util.Queue, since the data structure
 * itself is being assessed.
 *
 * Supports:
 *  - enqueue()       : add a patient to the back of the queue
 *  - dequeue()       : remove and return the patient at the front
 *  - displayQueue() : show all patients currently waiting
 *  - isEmpty()      : check whether the queue is empty
 */
public class EmergencyQueue {

    private static class QueueNode {
        Patient patient;
        QueueNode next;

        QueueNode(Patient patient) {
            this.patient = patient;
            this.next = null;
        }
    }

    private QueueNode front;
    private QueueNode rear;
    private int size;

    public EmergencyQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    /** Add a patient to the back of the waiting queue. */
    public void enqueue(Patient patient) {
        QueueNode newNode = new QueueNode(patient);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    /** Remove and return the patient at the front of the queue (next for treatment). */
    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty. No patient waiting.");
            return null;
        }
        Patient patient = front.patient;
        front = front.next;
        if (front == null) {
            rear = null; // queue is now empty
        }
        size--;
        return patient;
    }

    /** Look at the next patient without removing them. */
    public Patient peek() {
        if (isEmpty()) {
            return null;
        }
        return front.patient;
    }

    /** Display every patient currently waiting, in FIFO order. */
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No patients currently waiting in the emergency queue.");
            return;
        }
        QueueNode current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.patient.toShortString());
            current = current.next;
            position++;
        }
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }
}
