package hospital;

/**
 * Binary Search Tree (BST) that stores Patient records keyed by Patient ID.
 *
 * Supports:
 *  - insert()          : insert a new patient
 *  - search()           : search for a patient by Patient ID
 *  - delete()            : delete a patient by Patient ID
 *  - inorderTraversal() : display patients in ascending order of Patient ID
 */
public class PatientBST {

    /** Internal BST node. */
    private static class Node {
        Patient patient;
        Node left, right;

        Node(Patient patient) {
            this.patient = patient;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int count;

    public PatientBST() {
        root = null;
        count = 0;
    }

    // ---------------------------------------------------------------
    // INSERT
    // ---------------------------------------------------------------

    public boolean insert(Patient patient) {
        if (search(patient.getPatientId()) != null) {
            return false; // duplicate ID not allowed
        }
        root = insertRec(root, patient);
        count++;
        return true;
    }

    private Node insertRec(Node node, Patient patient) {
        if (node == null) {
            return new Node(patient);
        }
        if (patient.getPatientId() < node.patient.getPatientId()) {
            node.left = insertRec(node.left, patient);
        } else if (patient.getPatientId() > node.patient.getPatientId()) {
            node.right = insertRec(node.right, patient);
        }
        return node;
    }

    // ---------------------------------------------------------------
    // SEARCH
    // ---------------------------------------------------------------

    public Patient search(int patientId) {
        Node result = searchRec(root, patientId);
        return (result == null) ? null : result.patient;
    }

    private Node searchRec(Node node, int patientId) {
        if (node == null || node.patient.getPatientId() == patientId) {
            return node;
        }
        if (patientId < node.patient.getPatientId()) {
            return searchRec(node.left, patientId);
        }
        return searchRec(node.right, patientId);
    }

    // ---------------------------------------------------------------
    // DELETE
    // ---------------------------------------------------------------

    public boolean delete(int patientId) {
        if (search(patientId) == null) {
            return false;
        }
        root = deleteRec(root, patientId);
        count--;
        return true;
    }

    private Node deleteRec(Node node, int patientId) {
        if (node == null) {
            return null;
        }

        if (patientId < node.patient.getPatientId()) {
            node.left = deleteRec(node.left, patientId);
        } else if (patientId > node.patient.getPatientId()) {
            node.right = deleteRec(node.right, patientId);
        } else {
            // Node found - handle the three deletion cases

            // Case 1: no children
            if (node.left == null && node.right == null) {
                return null;
            }
            // Case 2: one child
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            // Case 3: two children -> replace with in-order successor
            // (smallest value in the right subtree)
            Node successor = findMin(node.right);
            node.patient = successor.patient;
            node.right = deleteRec(node.right, successor.patient.getPatientId());
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // ---------------------------------------------------------------
    // IN-ORDER TRAVERSAL (ascending order of Patient ID)
    // ---------------------------------------------------------------

    public void inorderTraversal() {
        if (root == null) {
            System.out.println("No patient records found.");
            return;
        }
        inorderRec(root);
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.println(node.patient.toShortString());
            inorderRec(node.right);
        }
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return root == null;
    }
}
